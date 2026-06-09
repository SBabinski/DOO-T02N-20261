import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class WeatherApiClient {

    private static final String BASE_URL =
        "https://weather.visualcrossing.com/VisualCrossingWebServices/rest/services/timeline/";

    private final String apiKey;

    public WeatherApiClient(String apiKey) {
        if (apiKey == null || apiKey.isBlank()) {
            throw new IllegalArgumentException("A chave da API não pode ser nula ou vazia.");
        }
        this.apiKey = apiKey;
    }

    public String buscarDadosClima(String cidade) throws IOException, WeatherApiException {
        String cidadeCodificada = URLEncoder.encode(cidade, StandardCharsets.UTF_8);

        String urlCompleta = BASE_URL
            + cidadeCodificada
            + "/today"
            + "?unitGroup=metric"
            + "&include=current,days"
            + "&lang=pt"
            + "&contentType=json"
            + "&key=" + apiKey;

        HttpURLConnection conexao = null;
        try {
            URL url = new URL(urlCompleta);
            conexao = (HttpURLConnection) url.openConnection();
            conexao.setRequestMethod("GET");
            conexao.setConnectTimeout(10_000);
            conexao.setReadTimeout(10_000);

            int statusHttp = conexao.getResponseCode();
            if (statusHttp != HttpURLConnection.HTTP_OK) {
                String mensagemErro = lerResposta(conexao, true);
                throw new WeatherApiException("Erro na API (HTTP " + statusHttp + "): " + mensagemErro);
            }

            return lerResposta(conexao, false);

        } finally {
            if (conexao != null) {
                conexao.disconnect();
            }
        }
    }

    private String lerResposta(HttpURLConnection conexao, boolean isErro) throws IOException {
        var stream = isErro ? conexao.getErrorStream() : conexao.getInputStream();
        if (stream == null) {
            return "";
        }
        try (var reader = new BufferedReader(new InputStreamReader(stream, StandardCharsets.UTF_8))) {
            var sb = new StringBuilder();
            String linha;
            while ((linha = reader.readLine()) != null) {
                sb.append(linha);
            }
            return sb.toString();
        }
    }
}