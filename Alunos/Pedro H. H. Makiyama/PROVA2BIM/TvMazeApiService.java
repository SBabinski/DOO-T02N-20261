package com.pedrohhm.service;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.pedrohhm.exception.ApiConnectionException;
import com.pedrohhm.exception.TvMazeException;
import com.pedrohhm.model.SearchResult;
import com.pedrohhm.model.TvSeries;

public class TvMazeApiService {

    private static final String BASE_URL = "https://api.tvmaze.com";

    private final HttpClient client = HttpClient.newHttpClient(); 

    private final ObjectMapper mapper = new ObjectMapper()
        .registerModule(new JavaTimeModule())
        .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

    public List<TvSeries> searchSeries(String name) {

        try {

            String url = buildUrl(name);

            HttpResponse<String> response = sendRequest(url);

            validateResponse(response);

            return parseResponse(response.body());

        }
        catch (IOException e) {

            throw new ApiConnectionException("Não foi possível conectar à API.", e);
        }

        catch (InterruptedException e) {

            Thread.currentThread().interrupt();

            throw new ApiConnectionException(
                "A conexão foi interrompida.",
                e
            );
        }

        catch (TvMazeException | ApiConnectionException e) {

            throw e;
        }

        catch (Exception e) {

            throw new RuntimeException("Erro ao buscar séries.", e);
        }
    }

    private String buildUrl(String seriesName) {

        return BASE_URL
                + "/search/shows?q="
                + URLEncoder.encode(seriesName, StandardCharsets.UTF_8);
    }

    private HttpResponse<String> sendRequest(String url)
            throws IOException, InterruptedException {

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        return client.send(request, HttpResponse.BodyHandlers.ofString());
    }

    private void validateResponse(HttpResponse<?> response) {

        switch (response.statusCode()) {

            case 200:
                return;

            case 404:
                throw new TvMazeException("Nenhuma série encontrada.");

            case 429:
                throw new TvMazeException("Limite de requisições excedido.");

            case 500:
                throw new TvMazeException("Erro interno da API.");

            default:
                throw new TvMazeException(
                    "A API retornou um erro inesperado (" +
                    response.statusCode() + ")."
                );
        }
    }

    private List<TvSeries> parseResponse(String body)
            throws IOException {

        SearchResult[] searchResults =
                mapper.readValue(body, SearchResult[].class);

        if (searchResults == null || searchResults.length == 0) {

            throw new TvMazeException(
                "Nenhuma série encontrada."
            );
        }

        return new ArrayList<>(
            Arrays.stream(searchResults)
                .map(SearchResult::getShow)
                .toList()
        );
    }
}
