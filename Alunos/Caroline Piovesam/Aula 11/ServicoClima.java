package com.caroline;

import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ServicoClima {

    private final HttpClient httpClient;
    private final ObjectMapper objectMapper;
    private final String apiKey;

    public ServicoClima() {
        this.httpClient = HttpClient.newHttpClient();
        this.objectMapper = new ObjectMapper();

        // NÃOO APAGAR!! busca a chave no launch.json
        this.apiKey = System.getenv("VISUALCROSSING_API_KEY");

        if (this.apiKey == null || this.apiKey.isEmpty()) {
            throw new IllegalStateException(
                    "ERRO: A variável de ambiente VISUALCROSSING_API_KEY não foi configurada no launch.json!");
        }
    }

    public RespostaClima obterDadosClima(String cidade) throws ConexaoException, Exception {
        String cidadeCodificada = URLEncoder.encode(cidade, StandardCharsets.UTF_8);

        String url = String.format(
                "https://weather.visualcrossing.com/VisualCrossingWebServices/rest/services/timeline/%s?unitGroup=metric&key=%s&contentType=json&lang=pt",
                cidadeCodificada, apiKey);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 200) {
            return objectMapper.readValue(response.body(), RespostaClima.class);
        } else if (response.statusCode() == 400 || response.statusCode() == 404) {
            throw new ConexaoException("Cidade não encontrada ou nome incorreto.");
        } else if (response.statusCode() == 401 || response.statusCode() == 403) {
            throw new ConexaoException("Chave de API inválida ou sem permissão de acesso.");
        } else {
            throw new ConexaoException("Erro ao buscar dados do clima: Código HTTP " + response.statusCode());
        }
    }
}