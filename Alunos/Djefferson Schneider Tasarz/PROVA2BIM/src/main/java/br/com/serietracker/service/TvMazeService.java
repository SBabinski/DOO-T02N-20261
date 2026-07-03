package br.com.serietracker.service;

import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.serietracker.model.Serie;

public class TvMazeService {

    private final ObjectMapper mapper = new ObjectMapper();

    public List<Serie> buscarSeries(String nome) {

        List<Serie> series = new ArrayList<>();

        try {

            String nomeCodificado =
                    URLEncoder.encode(
                            nome,
                            StandardCharsets.UTF_8
                    );

            String url =
                    "https://api.tvmaze.com/search/shows?q="
                            + nomeCodificado;

            HttpClient client =
                    HttpClient.newHttpClient();

            HttpRequest request =
                    HttpRequest.newBuilder()
                            .uri(URI.create(url))
                            .GET()
                            .build();

            HttpResponse<String> response =
                    client.send(
                            request,
                            HttpResponse.BodyHandlers.ofString()
                    );

            JsonNode root =
                    mapper.readTree(response.body());

            for (JsonNode item : root) {

                JsonNode show =
                        item.get("show");

                int id =
                        show.get("id").asInt();

                String nomeSerie =
                        show.get("name").asText();

                String idioma =
                        show.get("language").asText();

                String estado =
                        show.get("status").asText();

                String estreia =
                        show.hasNonNull("premiered")
                                ? show.get("premiered").asText()
                                : "";

                String termino =
                        show.hasNonNull("ended")
                                ? show.get("ended").asText()
                                : "";

                double nota = 0;

                if (show.has("rating")
                        && show.get("rating").hasNonNull("average")) {

                    nota =
                            show.get("rating")
                                    .get("average")
                                    .asDouble();
                }

                List<String> generos =
                        new ArrayList<>();

                for (JsonNode genero :
                        show.get("genres")) {

                    generos.add(
                            genero.asText()
                    );
                }

                String emissora = "";

                if (show.hasNonNull("network")) {

                    emissora =
                            show.get("network")
                                    .get("name")
                                    .asText();
                }

                Serie serie =
                        new Serie(
                                id,
                                nomeSerie,
                                idioma,
                                generos,
                                nota,
                                estado,
                                estreia,
                                termino,
                                emissora
                        );

                series.add(serie);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return series;
    }
}