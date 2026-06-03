import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ComunicaApiTempo {
    private static final String CHAVE_API = System.getenv("CHAVE_API");
    private static final String URL_BASE = "https://weather.visualcrossing.com/VisualCrossingWebServices/rest/services/timeline/";
    private final ObjectMapper objectMapper;

    public ComunicaApiTempo() {
        if (CHAVE_API == null || CHAVE_API.isBlank()) {
            throw new IllegalStateException("ERRO: A variável de ambiente CHAVE_API não foi configurada!");
        }
        this.objectMapper = new ObjectMapper();
    }

    public informacoesTempo fetchWeather(String cidade) throws Exception {
        String urlString = URL_BASE + cidade.replace(" ", "%20") + "?unitGroup=metric&key=" + CHAVE_API + "&contentType=json";

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(urlString))
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() != 200) {
            throw new RuntimeException("Erro ao buscar dados. Código HTTP: " + response.statusCode());
        }

        return parseJsonWithJackson(cidade, response.body());
    }

    private informacoesTempo parseJsonWithJackson(String city, String jsonBody) throws Exception {
        JsonNode rootNode = objectMapper.readTree(jsonBody);

        JsonNode current = rootNode.path("currentConditions");
        double temperaturaAtual = current.path("temp").asDouble();
        double umidade = current.path("humidity").asDouble();
        double precipitacao = current.path("precip").asDouble();
        double velocidadeVento = current.path("windspeed").asDouble();
        double direcaoVento = current.path("winddir").asDouble();
        String condicoes = current.path("conditions").asText("Desconhecido");

        JsonNode primeiroDia = rootNode.path("days").path(0);
        double temperaturaMaxima = primeiroDia.path("tempmax").asDouble();
        double temperaturaMinima = primeiroDia.path("tempmin").asDouble();

        String endereco = rootNode.path("resolvedAddress").asText(city);

        return new informacoesTempo(endereco, temperaturaAtual, temperaturaMaxima, temperaturaMinima, umidade, condicoes, precipitacao, velocidadeVento, direcaoVento);
    }
}