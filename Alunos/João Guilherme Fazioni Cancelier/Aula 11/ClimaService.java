import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class ClimaService {
    
    @JsonIgnoreProperties(ignoreUnknown = true)
    public record RespostaClima(CurrentConditions currentConditions, Day[] days) {}
    
    @JsonIgnoreProperties(ignoreUnknown = true)
    public record CurrentConditions(double temp, double humidity, String conditions, double windspeed, double winddir, String icon) {} 
    
    @JsonIgnoreProperties(ignoreUnknown = true)
    public record Day(double tempmax, double tempmin, double precip, String datetime) {}

    public ClimaDados buscar(String cidade, String uf) throws Exception {
        String apiKey = null;
        List<String> linhas = Files.readAllLines(Paths.get(".env"));
        for (String linha : linhas) {
            if (linha.startsWith("CHAVE_API=")) {
                apiKey = linha.split("=")[1].trim();
                break;
            }
        }
        
        if (apiKey == null || apiKey.isBlank()) {
            throw new IOException("A chave 'CHAVE_API' não foi encontrada no seu arquivo .env!");
        }

        String url = "https://weather.visualcrossing.com/VisualCrossingWebServices/rest/services/timeline/%s/today?unitGroup=metric&key=%s&include=day,current"
                .formatted((cidade + "," + uf).replace(" ", "%20"), apiKey);

        try (HttpClient client = HttpClient.newBuilder().connectTimeout(Duration.ofSeconds(10)).build()) {
            HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).timeout(Duration.ofSeconds(10)).GET().build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                String jsonBruto = response.body();
                ObjectMapper mapper = new ObjectMapper();
                RespostaClima dadosApi = mapper.readValue(jsonBruto, RespostaClima.class);
                Day hoje = dadosApi.days()[0];

                return new ClimaDados(
                    cidade, uf, 
                    java.time.LocalDate.parse(hoje.datetime()).format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                    dadosApi.currentConditions().temp(), hoje.tempmax(), hoje.tempmin(),
                    dadosApi.currentConditions().humidity(), dadosApi.currentConditions().conditions(),
                    hoje.precip(), dadosApi.currentConditions().windspeed(), dadosApi.currentConditions().winddir(),
                    dadosApi.currentConditions().icon()
                );
            } else {
                throw new RuntimeException("Erro na API de Clima. Código HTTP: " + response.statusCode());
            }
        }
    }
}
