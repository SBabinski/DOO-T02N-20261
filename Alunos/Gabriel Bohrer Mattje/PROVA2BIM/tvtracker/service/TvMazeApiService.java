package com.tvtracker.service;

import com.tvtracker.model.SeriesStatus;
import com.tvtracker.model.TvSeries;
import com.tvtracker.util.SimpleJsonParser;

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

/**
 * Serviço para consumo da API TvMaze.
 * Documentação: https://www.tvmaze.com/api
 */
public class TvMazeApiService {

    private static final String BASE_URL = "https://api.tvmaze.com";
    private static final int TIMEOUT_SECONDS = 10;

    private final HttpClient httpClient;

    public TvMazeApiService() {
        this.httpClient = HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(TIMEOUT_SECONDS))
                .build();
    }

    /**
     * Busca séries pelo nome usando a API TvMaze.
     * Endpoint: GET /search/shows?q={query}
     */
    public List<TvSeries> searchByName(String query) throws IOException, InterruptedException {
        if (query == null || query.isBlank()) {
            return new ArrayList<>();
        }

        String encodedQuery = URLEncoder.encode(query.trim(), StandardCharsets.UTF_8);
        String url = BASE_URL + "/search/shows?q=" + encodedQuery;

        String responseBody = sendGetRequest(url);
        return parseSearchResults(responseBody);
    }

    /**
     * Busca uma série pelo ID.
     * Endpoint: GET /shows/{id}
     */
    public TvSeries getShowById(int id) throws IOException, InterruptedException {
        String url = BASE_URL + "/shows/" + id;
        String responseBody = sendGetRequest(url);
        return parseShow(responseBody);
    }

    /**
     * Envia uma requisição GET e retorna o corpo da resposta.
     */
    private String sendGetRequest(String url) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .timeout(Duration.ofSeconds(TIMEOUT_SECONDS))
                .header("Accept", "application/json")
                .header("User-Agent", "TvTracker-Java/1.0")
                .GET()
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() != 200) {
            throw new IOException("Erro na API TvMaze. Status: " + response.statusCode());
        }

        return response.body();
    }

    /**
     * Analisa os resultados de busca da API (array de objetos com campo "show").
     */
    private List<TvSeries> parseSearchResults(String json) {
        List<TvSeries> results = new ArrayList<>();
        if (json == null || json.isBlank()) return results;

        List<String> items = SimpleJsonParser.splitJsonArray(json);
        for (String item : items) {
            try {
                String showJson = SimpleJsonParser.extractObject(item, "show");
                if (showJson != null) {
                    TvSeries series = parseShow(showJson);
                    if (series != null) results.add(series);
                }
            } catch (Exception e) {
                System.err.println("Erro ao analisar resultado: " + e.getMessage());
            }
        }
        return results;
    }

    /**
     * Converte um objeto JSON de série da API TvMaze para TvSeries.
     */
    private TvSeries parseShow(String json) {
        if (json == null || json.isBlank()) return null;
        try {
            TvSeries series = new TvSeries();

            // ID
            Integer id = SimpleJsonParser.extractInt(json, "id");
            if (id == null) return null;
            series.setId(id);

            // Nome
            series.setName(SimpleJsonParser.extractString(json, "name"));

            // Idioma
            series.setLanguage(SimpleJsonParser.extractString(json, "language"));

            // Gêneros
            series.setGenres(SimpleJsonParser.extractStringArray(json, "genres"));

            // Nota (rating.average)
            String ratingObj = SimpleJsonParser.extractObject(json, "rating");
            if (ratingObj != null) {
                Double avg = SimpleJsonParser.extractDouble(ratingObj, "average");
                series.setRating(avg != null ? avg : 0.0);
            }

            // Status
            String statusStr = SimpleJsonParser.extractString(json, "status");
            series.setStatus(SeriesStatus.fromApiString(statusStr));

            // Data de estreia
            series.setPremiereDate(SimpleJsonParser.extractString(json, "premiered"));

            // Data de término
            series.setEndDate(SimpleJsonParser.extractString(json, "ended"));

            // Emissora (network.name ou webChannel.name)
            String networkObj = SimpleJsonParser.extractObject(json, "network");
            if (networkObj != null) {
                series.setNetworkName(SimpleJsonParser.extractString(networkObj, "name"));
            } else {
                String webChannelObj = SimpleJsonParser.extractObject(json, "webChannel");
                if (webChannelObj != null) {
                    series.setNetworkName(SimpleJsonParser.extractString(webChannelObj, "name"));
                }
            }

            // Resumo (remove tags HTML)
            String summary = SimpleJsonParser.extractString(json, "summary");
            series.setSummary(SimpleJsonParser.stripHtml(summary));

            // Imagem
            String imageObj = SimpleJsonParser.extractObject(json, "image");
            if (imageObj != null) {
                String medium = SimpleJsonParser.extractString(imageObj, "medium");
                String original = SimpleJsonParser.extractString(imageObj, "original");
                series.setImageUrl(medium != null ? medium : original);
            }

            return series;
        } catch (Exception e) {
            System.err.println("Erro ao analisar série: " + e.getMessage());
            return null;
        }
    }
}
