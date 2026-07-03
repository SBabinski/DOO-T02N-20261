package provaDOO.services;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import provaDOO.model.Serie;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class TvMazeService {

    private static final String URL =
            "https://api.tvmaze.com/singlesearch/shows?q=";

    private final ObjectMapper mapper =
            new ObjectMapper();

    public Serie buscarSerie(String nomeSerie)
            throws Exception {

        String url =
                URL +
                nomeSerie.replace(" ", "%20");

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
                        HttpResponse.BodyHandlers.ofString());

        if(response.statusCode() != 200) {
            throw new Exception("Série não encontrada.");
        }

        JsonNode json =
                mapper.readTree(response.body());

        Serie serie = new Serie();

        serie.setId(
                json.get("id").asLong());

        serie.setNome(
                json.get("name").asText());

        serie.setIdioma(
                json.get("language").asText());

        serie.setStatus(
                json.get("status").asText());

        serie.setEstreia(
                json.get("premiered").asText());

        if(json.get("ended") != null &&!json.get("ended").isNull()) {

            serie.setTermino(
                    json.get("ended").asText());

        } else {

            serie.setTermino(
                    "Em andamento");
        }

        if(json.get("rating") != null &&
                json.get("rating").get("average") != null &&
                !json.get("rating").get("average").isNull()) {

            serie.setNota(
                    json.get("rating")
                            .get("average")
                            .asDouble());
        }

        if(json.get("network") != null &&
                json.get("network").get("name") != null) {

            serie.setEmissora(
                    json.get("network")
                            .get("name")
                            .asText());
        }

        java.util.List<String> generos =
                new java.util.ArrayList<>();

        for(JsonNode genero :
                json.get("genres")) {

            generos.add(
                    genero.asText());
        }

        serie.setGeneros(generos);

        return serie;
    }
    
    public Serie buscarSeriePorId(Long id)
            throws Exception {

        String url =
                "https://api.tvmaze.com/shows/"
                + id;

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
                        HttpResponse.BodyHandlers.ofString());

        if(response.statusCode() != 200) {

            throw new Exception(
                    "Série não encontrada."
            );
        }

        JsonNode json =
                mapper.readTree(response.body());

        Serie serie = new Serie();

        serie.setId(
                json.get("id").asLong());

        serie.setNome(
                json.get("name").asText());

        serie.setIdioma(
                json.get("language").asText());

        String status =
                json.get("status").asText();

        switch (status) {

            case "Running":
                status = "Ainda Transmitindo";
                break;

            case "Ended":
                status = "Já Concluída";
                break;

            case "In Development":
                status = "Em Desenvolvimento";
                break;

            case "To Be Determined":
                status = "Cancelada";
                break;
        }

        serie.setStatus(status);

        serie.setEstreia(
                json.get("premiered").asText());

        if(json.get("ended") != null &&
                !json.get("ended").isNull()) {

            serie.setTermino(
                    json.get("ended").asText());

        } else {
            serie.setTermino("Em andamento");
        }

        if(json.get("rating") != null &&
                json.get("rating").get("average") != null &&
                !json.get("rating").get("average").isNull()) {

            serie.setNota(
                    json.get("rating")
                            .get("average")
                            .asDouble());
        }

        if(json.get("network") != null &&
                json.get("network").get("name") != null) {

            serie.setEmissora(
                    json.get("network")
                            .get("name")
                            .asText());
        }

        java.util.List<String> generos =
                new java.util.ArrayList<>();

        for(JsonNode genero :
                json.get("genres")) {

            generos.add(
                    genero.asText());
        }

        serie.setGeneros(generos);

        return serie;
    }
}