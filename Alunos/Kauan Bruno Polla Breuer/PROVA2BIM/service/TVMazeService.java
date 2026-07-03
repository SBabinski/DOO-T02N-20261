package service;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import model.Serie;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class TVMazeService {

    private static final String BASE_URL   = "https://api.tvmaze.com";
    private static final int    TIMEOUT_MS = 10_000;

    public List<Serie> buscarPorNome(String query) throws IOException {
        if (query == null || query.trim().isEmpty()) {
            return new ArrayList<>();
        }
        String encodedQuery = URLEncoder.encode(query.trim(), StandardCharsets.UTF_8.name());
        String json = fazerRequisicao(BASE_URL + "/search/shows?q=" + encodedQuery);
        return parseResultados(json);
    }

    private String fazerRequisicao(String urlStr) throws IOException {
        URL url = new URL(urlStr);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setConnectTimeout(TIMEOUT_MS);
        conn.setReadTimeout(TIMEOUT_MS);
        conn.setRequestProperty("Accept", "application/json");
        conn.setRequestProperty("User-Agent", "SeriesTracker/1.0");

        int codigo = conn.getResponseCode();
        if (codigo != HttpURLConnection.HTTP_OK) {
            throw new IOException("Resposta inesperada da API: HTTP " + codigo);
        }

        StringBuilder sb = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                sb.append(linha);
            }
        } finally {
            conn.disconnect();
        }
        return sb.toString();
    }

    private List<Serie> parseResultados(String json) {
        List<Serie> lista = new ArrayList<>();
        JsonArray array = JsonParser.parseString(json).getAsJsonArray();

        for (JsonElement elemento : array) {
            try {
                JsonObject showObj = elemento.getAsJsonObject().getAsJsonObject("show");
                if (showObj != null) {
                    lista.add(parseSerie(showObj));
                }
            } catch (Exception ignorado) {
                // ignora entradas malformadas
            }
        }
        return lista;
    }

    private Serie parseSerie(JsonObject show) {
        int    id         = show.get("id").getAsInt();
        String nome       = getString(show, "name",      "Sem titulo");
        String idioma     = getString(show, "language",  "N/A");
        String estado     = getString(show, "status",    "N/A");
        String dataInicio = getString(show, "premiered", null);
        String dataFim    = getString(show, "ended",     null);

        // Generos
        List<String> generos = new ArrayList<>();
        if (show.has("genres") && !show.get("genres").isJsonNull()) {
            for (JsonElement g : show.getAsJsonArray("genres")) {
                generos.add(g.getAsString());
            }
        }

        // Nota
        Double nota = null;
        if (show.has("rating") && !show.get("rating").isJsonNull()) {
            JsonObject rating = show.getAsJsonObject("rating");
            if (rating.has("average") && !rating.get("average").isJsonNull()) {
                nota = rating.get("average").getAsDouble();
            }
        }

        // Emissora
        String emissora = extrairEmissora(show, "network");
        if (emissora == null) emissora = extrairEmissora(show, "webChannel");
        if (emissora == null) emissora = "N/A";

        // Poster — campo "image" -> "medium"
        String posterUrl = null;
        if (show.has("image") && !show.get("image").isJsonNull()) {
            JsonObject image = show.getAsJsonObject("image");
            if (image.has("medium") && !image.get("medium").isJsonNull()) {
                posterUrl = image.get("medium").getAsString();
            }
        }

        Serie serie = new Serie(id, nome, idioma, generos, nota, estado, dataInicio, dataFim, emissora);
        serie.setPosterUrl(posterUrl);
        return serie;
    }

    private String extrairEmissora(JsonObject show, String campo) {
        if (show.has(campo) && !show.get(campo).isJsonNull()) {
            JsonObject obj = show.getAsJsonObject(campo);
            return getString(obj, "name", null);
        }
        return null;
    }

    private String getString(JsonObject obj, String chave, String padrao) {
        if (obj.has(chave) && !obj.get(chave).isJsonNull()) {
            return obj.get(chave).getAsString();
        }
        return padrao;
    }
}
