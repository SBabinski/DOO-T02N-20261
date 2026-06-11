import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.time.Duration;

public class WeatherService {

    private static final String BASE_URL =
            "https://weather.visualcrossing.com/VisualCrossingWebServices/rest/services/timeline";
    private static final Duration REQUEST_TIMEOUT = Duration.ofSeconds(10);

    private final String apiKey;
    private final HttpClient httpClient;

    public WeatherService(String apiKey) {
        if (apiKey == null || apiKey.isBlank()) {
            throw new IllegalArgumentException("Chave de API não pode ser nula ou vazia.");
        }
        this.apiKey = apiKey;
        this.httpClient = HttpClient.newBuilder()
                .connectTimeout(REQUEST_TIMEOUT)
                .build();
    }

    public WeatherResponse fetchCurrentWeather(String location) throws WeatherApiException {
        if (location == null || location.isBlank()) {
            throw new IllegalArgumentException("Localização não pode ser nula ou vazia.");
        }

        String encodedLocation = URLEncoder.encode(location.trim(), StandardCharsets.UTF_8);
        String url = String.format(
                "%s/%s/today?include=current,days&unitGroup=metric&key=%s&contentType=json",
                BASE_URL, encodedLocation, apiKey
        );

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .timeout(REQUEST_TIMEOUT)
                .GET()
                .build();

        HttpResponse<String> response;
        try {
            response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException e) {
            throw new WeatherApiException("Falha de conexão: " + e.getMessage(), e);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new WeatherApiException("Requisição interrompida.", e);
        }

        handleHttpErrors(response);

        try {
            return JsonParser.parseWeatherResponse(response.body());
        } catch (Exception e) {
            throw new WeatherApiException("Erro ao processar resposta: " + e.getMessage(), e);
        }
    }

    private void handleHttpErrors(HttpResponse<String> response) throws WeatherApiException {
        int status = response.statusCode();
        switch (status) {
            case 200:
                break;
            case 400:
                throw new WeatherApiException("Cidade não encontrada. Tente um nome mais específico.", status);
            case 401:
                throw new WeatherApiException("Chave de API inválida.", status);
            case 429:
                throw new WeatherApiException("Limite de requisições atingido (1000 por dia).", status);
            case 500:
                throw new WeatherApiException("Erro no servidor da API. Tente novamente.", status);
            default:
                throw new WeatherApiException("Resposta inesperada (HTTP " + status + ")", status);
        }
    }
}
