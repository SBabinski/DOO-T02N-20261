package com.prova.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.prova.model.Serie;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class TvMazeApiService {
    private static final String URL_BASE = "https://api.tvmaze.com/search/shows?q=";

    private static final HttpClient client = HttpClient.newHttpClient();
    private static final ObjectMapper mapper = new ObjectMapper();

    public List<Serie> buscarSeriesPorNome(String nome) throws IOException, InterruptedException {
        String nomeFormatado = URLEncoder.encode(nome, StandardCharsets.UTF_8);
        String url = URL_BASE + nomeFormatado;

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() != 200) {
            throw new IOException("Erro ao buscar series. Codigo HTTP: " + response.statusCode());
        }

        JsonNode raiz = mapper.readTree(response.body());
        List<Serie> seriesEncontradas = new ArrayList<>();

        for (JsonNode resultado : raiz) {
            JsonNode show = resultado.get("show");

            int id = show.path("id").asInt();
            String nomeSerie = show.path("name").asText("Nao informado");
            String idioma = show.path("language").asText("Nao informado");
            String estado = show.path("status").asText("Nao informado");
            String dataEstreia = show.path("premiered").asText("Nao informado");
            String dataTermino = show.path("ended").asText("Nao informado");

            Double nota = null;
            JsonNode notaNode = show.path("rating").path("average");
            if (!notaNode.isMissingNode() && !notaNode.isNull()) {
                nota = notaNode.asDouble();
            }

            List<String> generos = new ArrayList<>();
            JsonNode generosNode = show.path("genres");
            if (generosNode.isArray()) {
                for (JsonNode genero : generosNode) {
                    generos.add(genero.asText());
                }
            }
            String emissora = "Nao informado";

            JsonNode network = show.path("network");
            if (!network.isMissingNode() && !network.isNull()) {
                emissora = network.path("name").asText("Nao informado");
            } else {
                JsonNode webChannel = show.path("webChannel");
                if (!webChannel.isMissingNode() && !webChannel.isNull()) {
                    emissora = webChannel.path("name").asText("Nao informado");
                }
            }

            Serie serie = new Serie(
                    id, nomeSerie, idioma, generos, nota,
                    estado, dataEstreia, dataTermino, emissora);
            seriesEncontradas.add(serie);
        }
        return seriesEncontradas;
    }
}
