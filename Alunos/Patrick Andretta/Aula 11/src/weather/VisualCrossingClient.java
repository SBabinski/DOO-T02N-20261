package weather;

import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.time.Duration;

public class VisualCrossingClient {

    private static final String BASE_URL =
        "https://weather.visualcrossing.com/VisualCrossingWebServices/rest/services/timeline";

    private final String apiKey;
    private final HttpClient http;

    public VisualCrossingClient(String apiKey) {
        this.apiKey = apiKey;
        this.http = HttpClient.newBuilder().connectTimeout(Duration.ofSeconds(10)).build();
    }

    public WeatherData fetchWeather(String city) throws WeatherException {
        String encoded = URLEncoder.encode(city, StandardCharsets.UTF_8).replace("+", "%20");
        String url = BASE_URL + "/" + encoded + "/today?key=" + apiKey
                   + "&unitGroup=metric&contentType=json&include=current,days&lang=pt";

        HttpResponse<String> response;
        try {
            HttpRequest req = HttpRequest.newBuilder()
                .uri(URI.create(url)).timeout(Duration.ofSeconds(15)).GET().build();
            response = http.send(req, HttpResponse.BodyHandlers.ofString(StandardCharsets.UTF_8));
        } catch (java.net.http.HttpTimeoutException e) {
            throw new WeatherException("Tempo limite de conexão excedido. Tente novamente.", 504);
        } catch (Exception e) {
            throw new WeatherException("Erro de conexão com a API: " + e.getMessage(), 503);
        }

        switch (response.statusCode()) {
            case 200: break;
            case 400: throw new WeatherException("Cidade inválida ou parâmetro incorreto.", 400);
            case 401: throw new WeatherException("Chave de API inválida ou conta não autorizada.", 401);
            case 404: throw new WeatherException("Cidade não encontrada: \"" + city + "\".", 404);
            case 429: throw new WeatherException("Limite de requisições excedido. Tente mais tarde.", 429);
            default:
                if (response.statusCode() >= 500)
                    throw new WeatherException("Erro no servidor da API (HTTP " + response.statusCode() + ").", 502);
                throw new WeatherException("Resposta inesperada da API (HTTP " + response.statusCode() + ").", 500);
        }

        return WeatherParser.parse(response.body(), city);
    }
}
