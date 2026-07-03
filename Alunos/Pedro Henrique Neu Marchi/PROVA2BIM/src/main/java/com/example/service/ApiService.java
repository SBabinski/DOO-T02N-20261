package com.example.service;

import com.example.model.Serie;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

import java.util.ArrayList;
import java.util.List;

public class ApiService {

    private static final String URL_BASE = "https://api.tvmaze.com/search/shows?q=";

    private final HttpClient client;
    private final ObjectMapper mapper;

    public ApiService() {
        this.client = HttpClient.newHttpClient();
        this.mapper = new ObjectMapper();
    }

    public List<Serie> buscarSeries(String nomeBusca) throws Exception {

        if (nomeBusca == null || nomeBusca.isBlank()) {
            return new ArrayList<>();
        }
        if (nomeBusca.length() > 100) {
        nomeBusca = nomeBusca.substring(0, 100);
        }

        String nomeFormatado = URLEncoder.encode(nomeBusca.trim(), StandardCharsets.UTF_8);

        String url = URL_BASE + nomeFormatado;

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() != 200) {
            throw new Exception("Erro ao buscar séries. Código HTTP: " + response.statusCode());
        }

        JsonNode root = mapper.readTree(response.body());

        List<Serie> series = new ArrayList<>();

        for (JsonNode item : root) {

            JsonNode show = item.path("show");

            int id = show.path("id").asInt();

            String nome = textoOuPadrao(show.path("name"), "Não informado");

            String idioma = textoOuPadrao(show.path("language"), "Não informado");

            List<String> generos = lerGeneros(show.path("genres"));

            double nota = lerNota(show);

            String status = textoOuPadrao(show.path("status"), "Não informado");

            String estreia = textoOuPadrao(show.path("premiered"), "Não informado");

            String termino = textoOuPadrao(show.path("ended"), "Não informado");

            String emissora = lerEmissora(show);

            Serie serie = new Serie(
                    id,
                    nome,
                    idioma,
                    generos,
                    nota,
                    status,
                    estreia,
                    termino,
                    emissora
            );

            series.add(serie);
        }

        return series;
    }

    private String textoOuPadrao(JsonNode node, String padrao) {

        if (node == null || node.isMissingNode() || node.isNull()) {
            return padrao;
        }

        String texto = node.asText();

        if (texto == null || texto.isBlank()) {
            return padrao;
        }

        return texto;
    }

    private List<String> lerGeneros(JsonNode generosNode) {

        List<String> generos = new ArrayList<>();

        if (generosNode != null && generosNode.isArray()) {
            for (JsonNode genero : generosNode) {
                generos.add(genero.asText());
            }
        }

        return generos;
    }

    private double lerNota(JsonNode show) {

        JsonNode notaNode = show.path("rating").path("average");

        if (notaNode != null && notaNode.isNumber()) {
            return notaNode.asDouble();
        }

        return 0.0;
    }

    private String lerEmissora(JsonNode show) {

        JsonNode network = show.path("network");

        if (!network.isMissingNode() && !network.isNull()) {
            String nomeNetwork = network.path("name").asText("");

            if (!nomeNetwork.isBlank()) {
                return nomeNetwork;
            }
        }

        JsonNode webChannel = show.path("webChannel");

        if (!webChannel.isMissingNode() && !webChannel.isNull()) {
            String nomeWebChannel = webChannel.path("name").asText("");

            if (!nomeWebChannel.isBlank()) {
                return nomeWebChannel;
            }
        }

        return "Não informado";
    }
}