package attDOO.api;

import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import attDOO.dto.ClimaResponse;

public class AppApi {

    private static final String API_KEY = "NMGPCWXFLYBC5TUVPBJ3MRZ9B";

    public ClimaResponse buscarClima(String cidade) {

        try {

            String cidadeFormatada =
                    URLEncoder.encode(cidade, "UTF-8");

            String endpoint =
                    "https://weather.visualcrossing.com/VisualCrossingWebServices/rest/services/timeline/"
                    + cidadeFormatada
                    + "?unitGroup=metric&lang=pt&key="
                    + API_KEY
                    + "&contentType=json";

            HttpClient client =
                    HttpClient.newHttpClient();

            HttpRequest request =
                    HttpRequest.newBuilder()
                    .uri(URI.create(endpoint))
                    .GET()
                    .build();

            HttpResponse<String> response =
                    client.send(
                            request,
                            HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                ObjectMapper mapper =
                        new ObjectMapper();
                return mapper.readValue(
                        response.body(),
                        ClimaResponse.class);
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}