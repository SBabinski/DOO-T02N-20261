import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.time.Duration;

public class ClienteApiVisualCrossing {

    private static final String URL_BASE =
            "https://weather.visualcrossing.com/VisualCrossingWebServices/rest/services/timeline";

    private final String chaveApi;
    private final HttpClient clienteHttp;

    public ClienteApiVisualCrossing(String chaveApi) {
        this.chaveApi    = chaveApi;
        this.clienteHttp = HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(10))
                .build();
    }

    public DadosClima buscarClima(String cidade) throws ErroClima {
        String cidadeCodificada = URLEncoder.encode(cidade, StandardCharsets.UTF_8)
                .replace("+", "%20");

        String url = URL_BASE + "/" + cidadeCodificada
                + "/today?key=" + chaveApi
                + "&unitGroup=metric&contentType=json&include=current,days&lang=pt";

        HttpResponse<String> resposta;
        try {
            HttpRequest requisicao = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .timeout(Duration.ofSeconds(15))
                    .GET()
                    .build();
            resposta = clienteHttp.send(requisicao, HttpResponse.BodyHandlers.ofString(StandardCharsets.UTF_8));
        } catch (java.net.http.HttpTimeoutException e) {
            throw new ErroClima("Tempo de conexão esgotado. Verifique sua internet.", 504);
        } catch (Exception e) {
            throw new ErroClima("Falha ao conectar com a API: " + e.getMessage(), 503);
        }

        validarResposta(resposta.statusCode(), cidade);
        return analisarResposta(resposta.body(), cidade);
    }

    private void validarResposta(int codigo, String cidade) throws ErroClima {
        switch (codigo) {
            case 200: return;
            case 400: throw new ErroClima("Parâmetro inválido na requisição.", 400);
            case 401: throw new ErroClima("Chave de API inválida. Verifique suas credenciais.", 401);
            case 404: throw new ErroClima("Cidade não encontrada: \"" + cidade + "\".", 404);
            case 429: throw new ErroClima("Limite de requisições atingido. Aguarde alguns minutos.", 429);
            default:
                if (codigo >= 500)
                    throw new ErroClima("Erro no servidor da API (HTTP " + codigo + ").", 502);
                throw new ErroClima("Resposta inesperada (HTTP " + codigo + ").", 500);
        }
    }

    private DadosClima analisarResposta(String json, String cidade) throws ErroClima {
        try {
            String enderecoResolvido = UtilitariosJson.extrairTexto(json, "resolvedAddress");

            String blocoAtual = UtilitariosJson.extrairBloco(json,
                    json.indexOf("\"currentConditions\""));

            String blocoDia = UtilitariosJson.extrairBloco(json,
                    json.indexOf('[', json.indexOf("\"days\"")));

            double tempAtual   = UtilitariosJson.extrairNumero(blocoAtual, "temp");
            double tempMax     = UtilitariosJson.extrairNumero(blocoDia, "tempmax");
            double tempMin     = UtilitariosJson.extrairNumero(blocoDia, "tempmin");
            double umidade     = UtilitariosJson.extrairNumero(blocoAtual, "humidity");
            String condicao    = UtilitariosJson.extrairTexto(blocoAtual, "conditions");
            double precipitacao = UtilitariosJson.extrairNumero(blocoAtual, "precip");
            double velVento    = UtilitariosJson.extrairNumero(blocoAtual, "windspeed");
            double dirVento    = UtilitariosJson.extrairNumero(blocoAtual, "winddir");
            String cardinal    = ConversorDirecaoVento.paraCardinal(dirVento);

            return new DadosClima(cidade, enderecoResolvido, tempAtual, tempMax, tempMin,
                    umidade, condicao, precipitacao, velVento, dirVento, cardinal);

        } catch (ErroClima e) {
            throw e;
        } catch (Exception e) {
            throw new ErroClima("Erro ao interpretar dados da API.", 500);
        }
    }
}