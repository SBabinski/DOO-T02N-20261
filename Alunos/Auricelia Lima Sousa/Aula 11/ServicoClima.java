import java.net.URI;
import java.net.http.*;
import java.util.regex.*;

public class ServicoClima {
    private static final String API_KEY = "MGM9FAZKLYULRWP2QBXRYJ4X6"; 

    public Clima buscarDados(String cidadeAlvo) throws ClimaException {
        try {
            String url = "https://weather.visualcrossing.com/VisualCrossingWebServices/rest/services/timeline/" 
                         + cidadeAlvo.replace(" ", "%20") + "?unitGroup=metric&key=" + API_KEY + "&contentType=json";

            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).GET().build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() != 200) {
                throw new ClimaException("Erro na API: " + response.statusCode());
            }

            return extrairDados(response.body(), cidadeAlvo);
        } catch (Exception e) {
            throw new ClimaException("Falha na conexao: " + e.getMessage());
        }
    }

    private Clima extrairDados(String json, String cidade) {
        Clima c = new Clima();
        c.cidade = cidade;
        c.temperatura = buscar(json, "\"temp\":\\s*(-?\\d+\\.?\\d*)");
        c.tempMaxima = buscar(json, "\"tempmax\":\\s*(-?\\d+\\.?\\d*)");
        c.tempMinima = buscar(json, "\"tempmin\":\\s*(-?\\d+\\.?\\d*)");
        c.umidade = buscar(json, "\"humidity\":\\s*(\\d+\\.?\\d*)");
        c.precipitacao = buscar(json, "\"precip\":\\s*(\\d+\\.?\\d*)");
        c.velocidadeVento = buscar(json, "\"windspeed\":\\s*(\\d+\\.?\\d*)");
        c.direcaoVento = buscar(json, "\"winddir\":\\s*(\\d+\\.?\\d*)");
        
        Matcher m = Pattern.compile("\"conditions\":\\s*\"([^\"]+)\"").matcher(json);
        c.condicao = m.find() ? m.group(1) : "Nao informada";

        return c;
    }

    private String buscar(String json, String regex) {
        Matcher m = Pattern.compile(regex).matcher(json);
        return m.find() ? m.group(1) : "0.0";
    }
}