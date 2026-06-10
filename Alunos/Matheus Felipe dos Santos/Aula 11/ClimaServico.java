import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class ClimaServico {

    private static final String API_KEY =
            System.getenv("VISUAL_CROSSING_KEY");

    public Clima buscarClima(String cidade) {

        try {

            cidade = URLEncoder.encode(
                    cidade,
                    StandardCharsets.UTF_8);

            String endereco =
                    "https://weather.visualcrossing.com/VisualCrossingWebServices/rest/services/timeline/"
                            + cidade
                            + "?unitGroup=metric&key="
                            + API_KEY
                            + "&contentType=json";

            URL url = new URL(endereco);

            HttpURLConnection conexao =
                    (HttpURLConnection) url.openConnection();

            conexao.setRequestMethod("GET");

            BufferedReader leitor =
                    new BufferedReader(
                            new InputStreamReader(conexao.getInputStream()));

            StringBuilder resposta = new StringBuilder();

            String linha;

            while ((linha = leitor.readLine()) != null) {

                resposta.append(linha);

            }

            leitor.close();

            JsonObject json =
                    JsonParser.parseString(resposta.toString())
                            .getAsJsonObject();

            JsonArray days = json.getAsJsonArray("days");

            JsonObject hoje = days.get(0).getAsJsonObject();

            Clima clima = new Clima();

            clima.setCidade(
                    json.get("resolvedAddress").getAsString());

            clima.setTemperatura(
                    hoje.get("temp").getAsDouble());

            clima.setTempMax(
                    hoje.get("tempmax").getAsDouble());

            clima.setTempMin(
                    hoje.get("tempmin").getAsDouble());

            clima.setUmidade(
                    hoje.get("humidity").getAsDouble());

                String condicao =
                        hoje.get("conditions").getAsString();

                System.out.println(
                        "Condição recebida: " + condicao);

                if (hoje.has("icon")) {

                System.out.println(
                        "Ícone recebido: "
                                + hoje.get("icon").getAsString());

                }

                clima.setCondicao(condicao);

                clima.setPrecipitacao(
                        hoje.get("precip").getAsDouble());
                        
            clima.setVelocidadeVento(
                    hoje.get("windspeed").getAsDouble());

            clima.setDirecaoVento(
                    hoje.get("winddir").getAsDouble());

            return clima;

        } catch (Exception e) {

            e.printStackTrace();

            return null;

        }

    }

}