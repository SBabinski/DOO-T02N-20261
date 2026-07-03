package service;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import model.Serie;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ApiService {

    public Serie buscarSerie(String nome) throws Exception {

        String url =
                "https://api.tvmaze.com/singlesearch/shows?q="
                        + nome.replace(" ", "%20");

        HttpClient client =
                HttpClient.newHttpClient();

        HttpRequest request =
                HttpRequest.newBuilder()
                        .uri(URI.create(url))
                        .build();

        HttpResponse<String> response =
                client.send(
                        request,
                        HttpResponse.BodyHandlers.ofString());

        JsonObject obj =
                JsonParser.parseString(response.body())
                        .getAsJsonObject();

        return new Serie(
                obj.get("id").getAsInt(),
                obj.get("name").getAsString(),
                obj.get("language").getAsString(),
                obj.get("genres").toString(),

                obj.get("rating")
                        .getAsJsonObject()
                        .get("average")
                        .isJsonNull()
                        ? 0
                        : obj.get("rating")
                        .getAsJsonObject()
                        .get("average")
                        .getAsDouble(),

                obj.get("status").getAsString(),

                obj.get("premiered").isJsonNull()
                        ? "-"
                        : obj.get("premiered").getAsString(),

                obj.get("ended").isJsonNull()
                        ? "-"
                        : obj.get("ended").getAsString(),

                obj.get("network").isJsonNull()
                        ? "-"
                        : obj.get("network")
                        .getAsJsonObject()
                        .get("name")
                        .getAsString()
        );
    }
}