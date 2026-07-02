package service;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import model.Serie;

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

public class TVMazeService {
    private static final String API_URL = "https://api.tvmaze.com/search/shows?q=";

    private final HttpClient client;

    public TVMazeService() {
        this.client = HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(10))
                .build();
    }

    public List<Serie> buscarSeries(String nome) throws IOException, InterruptedException {
        String pesquisa = URLEncoder.encode(nome, StandardCharsets.UTF_8);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(API_URL + pesquisa))
                .timeout(Duration.ofSeconds(15))
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() != 200) {
            throw new IOException("A API retornou o código " + response.statusCode());
        }

        JsonArray resultados = JsonParser.parseString(response.body()).getAsJsonArray();
        List<Serie> series = new ArrayList<>();

        for (JsonElement item : resultados) {
            JsonObject show = item.getAsJsonObject().getAsJsonObject("show");
            series.add(criarSerie(show));
        }

        return series;
    }

    private Serie criarSerie(JsonObject show) {
        int id = getInt(show, "id");
        String nome = getString(show, "name");
        String idioma = getString(show, "language");
        List<String> generos = getListaDeGeneros(show);
        double nota = getNota(show);
        String estado = getString(show, "status");
        String estreia = getString(show, "premiered");
        String termino = getString(show, "ended");
        String emissora = getEmissora(show);

        return new Serie(id, nome, idioma, generos, nota, estado, estreia, termino, emissora);
    }

    private List<String> getListaDeGeneros(JsonObject show) {
        List<String> generos = new ArrayList<>();
        JsonArray array = show.getAsJsonArray("genres");

        if (array != null) {
            for (JsonElement genero : array) {
                generos.add(genero.getAsString());
            }
        }

        return generos;
    }

    private double getNota(JsonObject show) {
        if (!show.has("rating") || show.get("rating").isJsonNull()) {
            return 0;
        }

        JsonObject rating = show.getAsJsonObject("rating");
        if (!rating.has("average") || rating.get("average").isJsonNull()) {
            return 0;
        }

        return rating.get("average").getAsDouble();
    }

    private String getEmissora(JsonObject show) {
        if (show.has("network") && !show.get("network").isJsonNull()) {
            JsonObject network = show.getAsJsonObject("network");
            return getString(network, "name");
        }

        if (show.has("webChannel") && !show.get("webChannel").isJsonNull()) {
            JsonObject webChannel = show.getAsJsonObject("webChannel");
            return getString(webChannel, "name");
        }

        return "Não informado";
    }

    private String getString(JsonObject object, String campo) {
        if (!object.has(campo) || object.get(campo).isJsonNull()) {
            return "Não informado";
        }
        return object.get(campo).getAsString();
    }

    private int getInt(JsonObject object, String campo) {
        if (!object.has(campo) || object.get(campo).isJsonNull()) {
            return 0;
        }
        return object.get(campo).getAsInt();
    }
}
