import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpTimeoutException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class TVMazeClient {

    private static final String BASE = "https://api.tvmaze.com";

    private static final ObjectMapper MAPPER = new ObjectMapper();

    private final HttpClient http;

    public TVMazeClient() {
        this.http = HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(15))
                .build();
    }

    public List<Serie> buscarPorNome(String termo) throws TVMazeException {
        if (termo == null || termo.trim().isEmpty()) {
            throw new TVMazeException("Digite o nome de uma serie para buscar.");
        }

        String url = BASE + "/search/shows?q="
                + URLEncoder.encode(termo.trim(), StandardCharsets.UTF_8);
        String corpo = executarGet(url);

        JsonNode raiz;
        try {
            raiz = MAPPER.readTree(corpo);
        } catch (JsonProcessingException e) {
            throw new TVMazeException("A resposta da API veio em um formato inesperado.", e);
        }

        if (raiz == null || !raiz.isArray()) {
            throw new TVMazeException("A resposta da API veio em um formato inesperado.");
        }

        List<Serie> resultado = new ArrayList<>();
        for (JsonNode item : raiz) {
            JsonNode show = item.path("show");
            if (show.isObject()) {
                resultado.add(Serie.fromJson(show));
            }
        }
        return resultado;
    }

    private String executarGet(String url) throws TVMazeException {
        try {
            HttpRequest requisicao = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .timeout(Duration.ofSeconds(20))
                    .header("Accept", "application/json")
                    .GET()
                    .build();

            HttpResponse<String> resposta =
                    http.send(requisicao, HttpResponse.BodyHandlers.ofString(StandardCharsets.UTF_8));

            int status = resposta.statusCode();
            if (status == 429) {
                throw new TVMazeException(
                        "Limite de requisicoes da API atingido. Aguarde alguns segundos e tente novamente.");
            }
            if (status < 200 || status >= 300) {
                throw new TVMazeException("A API retornou o codigo HTTP " + status + ".");
            }
            return resposta.body();

        } catch (HttpTimeoutException e) {
            throw new TVMazeException("Tempo de conexao esgotado. Verifique sua internet.", e);
        } catch (IOException e) {
            throw new TVMazeException("Erro de rede ao acessar a API. Verifique sua conexao.", e);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new TVMazeException("A busca foi interrompida.", e);
        }
    }
}