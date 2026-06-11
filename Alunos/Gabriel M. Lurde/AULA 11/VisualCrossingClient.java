import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

public class VisualCrossingClient {

    private final String apiKey;

    public VisualCrossingClient(String apiKey) {
        this.apiKey = apiKey;
    }

    public WeatherData buscarClima(String cidade)
            throws WeatherException {

        try {

            String cidadeCodificada =
                    URLEncoder.encode(
                            cidade,
                            StandardCharsets.UTF_8);

            String url =
                    "https://weather.visualcrossing.com/VisualCrossingWebServices/rest/services/timeline/"
                    + cidadeCodificada
                    + "/today?unitGroup=metric"
                    + "&include=current"
                    + "&lang=pt"
                    + "&key=" + apiKey
                    + "&contentType=json";

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

            if (response.statusCode() != 200) {

                throw new WeatherException(
                        "Erro HTTP: "
                                + response.statusCode());
            }

            return WeatherParser.parse(
                    cidade,
                    response.body());

        } catch (WeatherException e) {

            throw e;

        } catch (Exception e) {

            throw new WeatherException(
                    "Falha ao consultar API.");
        }
    }
}