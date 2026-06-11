package com.example;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class ClimaService {

    private static final String API_KEY = "9KLS6CESBPQ8XABKZ4MAZQVJ8";

    public String buscarClima(String cidade) {

        try {

          String cidadeFormatada =
                URLEncoder.encode(cidade, StandardCharsets.UTF_8);

        String url =
            "https://weather.visualcrossing.com/VisualCrossingWebServices/rest/services/timeline/"
            + cidadeFormatada
            + "?unitGroup=metric&key="
            + API_KEY
            + "&contentType=json";

            HttpClient client = HttpClient.newHttpClient();

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .GET()
                    .build();

            HttpResponse<String> response =
                    client.send(
                            request,
                            HttpResponse.BodyHandlers.ofString());

            return response.body();

        } catch (Exception e) {

            return "Erro: " + e.getMessage();

        }
    }
    public Clima buscarDadosClima(String cidade) {

    try {

        String json = buscarClima(cidade);

        ObjectMapper mapper = new ObjectMapper();

        JsonNode root = mapper.readTree(json);

        Clima clima = new Clima();

        clima.temperaturaAtual =
                root.path("currentConditions")
                    .path("temp")
                    .asDouble();

        clima.umidade =
                root.path("currentConditions")
                    .path("humidity")
                    .asDouble();

            clima.condicao =
                traduzirCondicao(
                    root.path("currentConditions")
                        .path("conditions")
                        .asText()
                );

        clima.precipitacao =
                root.path("currentConditions")
                    .path("precip")
                    .asDouble();

        clima.velocidadeVento =
                root.path("currentConditions")
                    .path("windspeed")
                    .asDouble();

        clima.direcaoVento =
                root.path("currentConditions")
                    .path("winddir")
                    .asDouble();

        clima.temperaturaMaxima =
                root.path("days")
                    .get(0)
                    .path("tempmax")
                    .asDouble();

        clima.temperaturaMinima =
                root.path("days")
                    .get(0)
                    .path("tempmin")
                    .asDouble();

        return clima;

    } catch (Exception e) {

        e.printStackTrace();
        return null;

    }
}
private String traduzirCondicao(String condicao) {

    String resultado = "";

    if (condicao.toLowerCase().contains("rain"))
        resultado += "Chuva";

    if (condicao.toLowerCase().contains("overcast")) {

        if (!resultado.isEmpty())
            resultado += " e ";

        resultado += "Nublado";
    }

    if (condicao.toLowerCase().contains("partially cloudy")) {

        if (!resultado.isEmpty())
            resultado += " e ";

        resultado += "Parcialmente Nublado";
    }

    if (condicao.toLowerCase().contains("clear")) {

        if (!resultado.isEmpty())
            resultado += " e ";

        resultado += "Céu Limpo";
    }

    return resultado.isEmpty() ? condicao : resultado;
    }
}