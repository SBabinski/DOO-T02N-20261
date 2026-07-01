package com.tvtracker.service;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.tvtracker.model.Series;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * Serviço responsável por toda comunicação com a API pública TVMaze.
 * Realiza requisições HTTP e converte as respostas JSON em objetos Series.
 */
public class TVMazeService {

    // URL base da API TVMaze
    private static final String BASE_URL = "https://api.tvmaze.com";
    // Timeout de 10 segundos para evitar travamentos na UI
    private static final int TIMEOUT_MS = 10_000;

    /**
     * Busca séries pelo nome usando o endpoint de busca da API.
     * Retorna lista vazia em caso de erro para não propagar exceção para a UI.
     */
    public List<Series> searchByName(String query) throws Exception {
        // URLEncoder.encode(String, Charset; usa String "UTF-8" para compatibilidade Java 8
        String encodedQuery = URLEncoder.encode(query, "UTF-8");
        String urlStr = BASE_URL + "/search/shows?q=" + encodedQuery;

        String json = makeGetRequest(urlStr);
        return parseSearchResults(json);
    }

    /**
     * Faz a requisição HTTP GET e retorna o corpo da resposta como String.
     * Lança exceção com mensagem clara em caso de falha de conexão.
     */
    private String makeGetRequest(String urlStr) throws Exception {
        URL url = new URL(urlStr);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

        // Configura a requisição como GET com timeouts adequados
        conn.setRequestMethod("GET");
        conn.setConnectTimeout(TIMEOUT_MS);
        conn.setReadTimeout(TIMEOUT_MS);
        conn.setRequestProperty("Accept", "application/json");

        int responseCode = conn.getResponseCode();
        if (responseCode != HttpURLConnection.HTTP_OK) {
            throw new Exception("Erro na API TVMaze. Código HTTP: " + responseCode);
        }

        // Lê o corpo da resposta linha por linha
        StringBuilder sb = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(conn.getInputStream(), "UTF-8"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
        } finally {
            conn.disconnect();
        }

        return sb.toString();
    }

    /**
     * Converte o JSON de resultado de busca em uma lista de objetos Series.
     * O endpoint /search/shows retorna um array com objetos {"score": ..., "show": {...}}.
     */
    private List<Series> parseSearchResults(String json) {
        List<Series> results = new ArrayList<>();
        JsonArray array = JsonParser.parseString(json).getAsJsonArray();

        for (JsonElement element : array) {
            JsonObject showObj = element.getAsJsonObject().getAsJsonObject("show");
            Series series = parseShowObject(showObj);
            results.add(series);
        }

        return results;
    }

    /**
     * Converte um objeto JSON de série em um objeto Series Java.
     * Trata campos ausentes (null) para garantir robustez.
     */
    private Series parseShowObject(JsonObject show) {
        int id = show.get("id").getAsInt();

        // Extrai campos com verificação de nulo para campos opcionais da API
        String name = getStringOrDefault(show, "name", "Sem nome");
        String language = getStringOrDefault(show, "language", "N/A");
        String status = getStringOrDefault(show, "status", "Unknown");
        String premiered = getStringOrDefault(show, "premiered", null);
        String ended = getStringOrDefault(show, "ended", null);

        // Remove tags HTML da sinopse vinda da API
        String summary = getStringOrDefault(show, "summary", "");
        summary = summary.replaceAll("<[^>]+>", "").trim();

        // Extrai a nota média (rating.average pode ser null)
        double rating = 0.0;
        if (show.has("rating") && !show.get("rating").isJsonNull()) {
            JsonObject ratingObj = show.getAsJsonObject("rating");
            if (ratingObj.has("average") && !ratingObj.get("average").isJsonNull()) {
                rating = ratingObj.get("average").getAsDouble();
            }
        }

        // Extrai os gêneros como lista de strings
        List<String> genres = new ArrayList<>();
        if (show.has("genres") && !show.get("genres").isJsonNull()) {
            for (JsonElement g : show.getAsJsonArray("genres")) {
                genres.add(g.getAsString());
            }
        }

        // Extrai o nome da emissora (network ou webChannel)
        String network = "N/A";
        if (show.has("network") && !show.get("network").isJsonNull()) {
            network = getStringOrDefault(show.getAsJsonObject("network"), "name", "N/A");
        } else if (show.has("webChannel") && !show.get("webChannel").isJsonNull()) {
            network = getStringOrDefault(show.getAsJsonObject("webChannel"), "name", "N/A");
        }

        // Extrai a URL da imagem do poster (medium ou original)
        String imageUrl = null;
        if (show.has("image") && !show.get("image").isJsonNull()) {
            JsonObject img = show.getAsJsonObject("image");
            if (img.has("medium") && !img.get("medium").isJsonNull()) {
                imageUrl = img.get("medium").getAsString();
            }
        }

        return new Series(id, name, language, genres, rating, status,
                premiered, ended, network, summary, imageUrl);
    }

    // Utilitário para ler campo String de um JsonObject com valor padrão
    private String getStringOrDefault(JsonObject obj, String key, String defaultVal) {
        if (obj.has(key) && !obj.get(key).isJsonNull()) {
            return obj.get(key).getAsString();
        }
        return defaultVal;
    }
}
