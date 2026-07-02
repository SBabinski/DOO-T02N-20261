package tvtracker.service;

import java.net.URI;
import java.net.URLEncoder;
import java.net.http.*;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.*;
import tvtracker.model.TvShow;

public class TvMazeService {

    private static final String BASE_URL = "https://api.tvmaze.com";
    private static final Duration TIMEOUT = Duration.ofSeconds(10);

    private final HttpClient httpClient;

    public TvMazeService() {
        this.httpClient = HttpClient.newBuilder()
                .connectTimeout(TIMEOUT)
                .build();
    }

    public List<TvShow> searchShows(String query) throws Exception {
        if (query == null || query.isBlank()) {
            return Collections.emptyList();
        }

        String encodedQuery = URLEncoder.encode(query.trim(), StandardCharsets.UTF_8);
        String url = BASE_URL + "/search/shows?q=" + encodedQuery;

        String responseBody = doGet(url);
        return parseSearchResults(responseBody);
    }

    public TvShow getShowById(int id) throws Exception {
        String url = BASE_URL + "/shows/" + id;
        String responseBody = doGet(url);
        return parseShow(JsonParser.parseObject(responseBody));
    }

    private String doGet(String url) throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .timeout(TIMEOUT)
                .header("Accept", "application/json")
                .GET()
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        int status = response.statusCode();
        if (status == 404) {
            return "[]";
        }
        if (status < 200 || status >= 300) {
            throw new Exception("Erro na API TVMaze: HTTP " + status);
        }
        return response.body();
    }

    @SuppressWarnings("unchecked")
    private List<TvShow> parseSearchResults(String json) {
        List<TvShow> shows = new ArrayList<>();
        try {
            List<Object> results = JsonParser.parseArray(json);
            for (Object item : results) {
                if (item instanceof Map<?, ?> resultMap) {
                    Map<String, Object> typedMap = (Map<String, Object>) resultMap;
                    Map<String, Object> showMap = JsonParser.getMap(typedMap, "show");
                    if (showMap != null) {
                        TvShow show = parseShow(showMap);
                        if (show != null) shows.add(show);
                    }
                }
            }
        } catch (Exception e) {
            System.err.println("Erro ao parsear resultados: " + e.getMessage());
        }
        return shows;
    }

    @SuppressWarnings("unchecked")
    private TvShow parseShow(Map<String, Object> showMap) {
        if (showMap == null) return null;
        try {
            TvShow show = new TvShow();

            show.setId((int) JsonParser.getInt(showMap, "id"));
            show.setName(JsonParser.getString(showMap, "name"));
            show.setLanguage(JsonParser.getString(showMap, "language"));
            show.setStatus(JsonParser.getString(showMap, "status"));
            show.setPremiered(JsonParser.getString(showMap, "premiered"));
            show.setEnded(JsonParser.getString(showMap, "ended"));

            // Rating
            Map<String, Object> ratingMap = JsonParser.getMap(showMap, "rating");
            if (ratingMap != null) {
                show.setRating(JsonParser.getDouble(ratingMap, "average"));
            }

            // Gêneros
            List<Object> genresList = JsonParser.getList(showMap, "genres");
            List<String> genres = new ArrayList<>();
            for (Object g : genresList) {
                if (g != null) genres.add(g.toString());
            }
            show.setGenres(genres);

            // Rede/emissora - tenta "network" e depois "webChannel"
            Map<String, Object> networkMap = JsonParser.getMap(showMap, "network");
            if (networkMap != null) {
                show.setNetwork(JsonParser.getString(networkMap, "name"));
            } else {
                Map<String, Object> webMap = JsonParser.getMap(showMap, "webChannel");
                if (webMap != null) {
                    show.setNetwork(JsonParser.getString(webMap, "name") + " (web)");
                }
            }

            // Remover tags HTML
            String summary = JsonParser.getString(showMap, "summary");
            if (summary != null) {
                summary = summary.replaceAll("<[^>]*>", "").trim();
            }
            show.setSummary(summary);

            // Imagem
            Map<String, Object> imageMap = JsonParser.getMap(showMap, "image");
            if (imageMap != null) {
                String imgUrl = JsonParser.getString(imageMap, "medium");
                if (imgUrl == null) imgUrl = JsonParser.getString(imageMap, "original");
                show.setImageUrl(imgUrl);
            }

            return show;
        } catch (Exception e) {
            System.err.println("Erro ao parsear série: " + e.getMessage());
            return null;
        }
    }
}
