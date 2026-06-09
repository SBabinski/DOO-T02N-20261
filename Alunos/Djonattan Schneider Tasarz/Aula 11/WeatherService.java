import java.io.IOException;

public class WeatherService {

    private final WeatherApiClient apiClient;
    private final WeatherParser parser;

    public WeatherService(String apiKey) {
        this.apiClient = new WeatherApiClient(apiKey);
        this.parser    = new WeatherParser();
    }

    public WeatherData consultarClima(String cidade) throws WeatherApiException {
        if (cidade == null || cidade.isBlank()) {
            throw new IllegalArgumentException("O nome da cidade não pode ser vazio.");
        }

        String jsonResposta;
        try {
            jsonResposta = apiClient.buscarDadosClima(cidade);
        } catch (IOException e) {
            throw new WeatherApiException("Erro de conexão ao consultar o clima: " + e.getMessage(), e);
        }

        return parser.parse(cidade, jsonResposta);
    }
}