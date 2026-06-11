import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import org.json.JSONObject;

public class Requisicao {

    public static PrevisaoResultado obterPrevisao(String cidade, String estado) {
        try {
            String chaveAPI = ChaveAPI.getChaveAPI(); // pega a chave do seu arquivo
            String url = "https://weather.visualcrossing.com/VisualCrossingWebServices/rest/services/timeline/"
                    + cidade + "%2C" + estado
                    + "/today?unitGroup=metric&key=" + chaveAPI + "&include=current";

            HttpClient cliente = HttpClient.newHttpClient();
            HttpRequest requisicao = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .GET()
                    .build();

            HttpResponse<String> resposta = cliente.send(requisicao, HttpResponse.BodyHandlers.ofString());

            JSONObject json = new JSONObject(resposta.body());
            JSONObject current = json.getJSONObject("currentConditions");

            double temperaturaAtual = current.getDouble("temp");
            double temperaturaMaxima = json.getJSONArray("days").getJSONObject(0).getDouble("tempmax");
            double temperaturaMinima = json.getJSONArray("days").getJSONObject(0).getDouble("tempmin");
            int umidade = current.getInt("humidity");
            String condicao = current.getString("conditions");
            double precipitacao = current.optDouble("precip", 0.0);
            double ventoVelocidade = current.getDouble("windspeed");
            double ventoDirecao = current.getDouble("winddir");
            String icon = current.getString("icon");

            return new PrevisaoResultado(temperaturaAtual, temperaturaMaxima, temperaturaMinima, 
                umidade, condicao, precipitacao, ventoVelocidade, ventoDirecao, icon);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}