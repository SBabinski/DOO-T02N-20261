package tvtracker.api;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import tvtracker.json.Json;
import tvtracker.json.JsonException;
import tvtracker.model.Show;

public class TvMazeClient {

    private static final String SEARCH_URL = "https://api.tvmaze.com/search/shows?q=";
    private static final Duration TIMEOUT = Duration.ofSeconds(10);

    private final HttpClient httpClient;

    public TvMazeClient() {
        this.httpClient = HttpClient.newBuilder()
                .connectTimeout(TIMEOUT)
                .build();
    }

    /**
     * Busca séries cujo nome combina (total ou parcialmente) com o termo
     * informado, usando o endpoint de busca do TVMaze.
     *
     * @param query termo de busca digitado pelo usuário
     * @return lista de séries encontradas (pode ser vazia, nunca nula)
     * @throws ApiException se houver falha de rede, timeout, resposta HTTP
     *                       de erro ou um JSON em formato inesperado
     */
    @SuppressWarnings("unchecked")
    public List<Show> search(String query) throws ApiException {
        if (query == null || query.isBlank()) {
            return new ArrayList<>();
        }

        String encodedQuery = URLEncoder.encode(query.trim(), StandardCharsets.UTF_8);
        URI uri = URI.create(SEARCH_URL + encodedQuery);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(uri)
                .timeout(TIMEOUT)
                .GET()
                .build();

        String body;
        try {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() != 200) {
                throw new ApiException("A API do TVMaze respondeu com código " + response.statusCode()
                        + ". Tente novamente em alguns instantes.");
            }
            body = response.body();
        } catch (IOException e) {
            throw new ApiException("Não foi possível conectar à API do TVMaze. "
                    + "Verifique sua conexão com a internet e tente novamente.", e);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new ApiException("A busca foi interrompida.", e);
        }

        try {
            Object parsed = Json.parse(body);
            if (!(parsed instanceof List)) {
                throw new ApiException("Resposta inesperada da API do TVMaze (formato não reconhecido).");
            }
            List<Object> rawList = (List<Object>) parsed;
            List<Show> shows = new ArrayList<>();
            for (Object item : rawList) {
                if (!(item instanceof Map)) {
                    continue;
                }
                Map<String, Object> entry = (Map<String, Object>) item;
                Object showObj = entry.get("show");
                if (showObj instanceof Map) {
                    shows.add(parseShow((Map<String, Object>) showObj));
                }
            }
            return shows;
        } catch (JsonException e) {
            throw new ApiException("A resposta da API do TVMaze não pôde ser interpretada.", e);
        }
    }

  
    @SuppressWarnings("unchecked")
    private Show parseShow(Map<String, Object> raw) {
        int id = (int) Math.round(toDouble(raw.get("id")));
        String name = toStringOrNull(raw.get("name"));
        String language = toStringOrNull(raw.get("language"));

        List<String> genres = new ArrayList<>();
        Object genresObj = raw.get("genres");
        if (genresObj instanceof List) {
            for (Object g : (List<Object>) genresObj) {
                if (g != null) {
                    genres.add(String.valueOf(g));
                }
            }
        }

        Double rating = null;
        Object ratingObj = raw.get("rating");
        if (ratingObj instanceof Map) {
            Object avg = ((Map<String, Object>) ratingObj).get("average");
            if (avg != null) {
                rating = toDouble(avg);
            }
        }

        String status = toStringOrNull(raw.get("status"));
        String premiered = toStringOrNull(raw.get("premiered"));
        String ended = toStringOrNull(raw.get("ended"));

        String network = extractChannelName(raw.get("network"));
        if (network == null) {
            network = extractChannelName(raw.get("webChannel"));
        }
        if (network == null) {
            network = "—";
        }

        String summary = toStringOrNull(raw.get("summary"));
        summary = stripHtml(summary);

        String imageUrl = null;
        Object imageObj = raw.get("image");
        if (imageObj instanceof Map) {
            Object medium = ((Map<String, Object>) imageObj).get("medium");
            imageUrl = toStringOrNull(medium);
        }

        return new Show(id, name, language, genres, rating, status, premiered, ended,
                network, summary, imageUrl);
    }

    @SuppressWarnings("unchecked")
    private String extractChannelName(Object channelObj) {
        if (channelObj instanceof Map) {
            Object nameObj = ((Map<String, Object>) channelObj).get("name");
            return toStringOrNull(nameObj);
        }
        return null;
    }

    private String stripHtml(String html) {
        if (html == null) {
            return null;
        }
        return html.replaceAll("<[^>]+>", "").trim();
    }

    private String toStringOrNull(Object o) {
        return o == null ? null : String.valueOf(o);
    }

    private double toDouble(Object o) {
        if (o instanceof Number) {
            return ((Number) o).doubleValue();
        }
        return 0;
    }
}
