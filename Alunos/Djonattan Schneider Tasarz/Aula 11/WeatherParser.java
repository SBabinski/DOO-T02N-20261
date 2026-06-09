import org.json.JSONObject;

public class WeatherParser {

    public WeatherData parse(String cidade, String jsonTexto) throws WeatherApiException {
        try {
            JSONObject raiz = new JSONObject(jsonTexto);

            String enderecoResolvido = raiz.optString("resolvedAddress", cidade);

            if (!raiz.has("currentConditions")) {
                throw new WeatherApiException("Resposta da API não contém o campo 'currentConditions'.");
            }
            JSONObject atual = raiz.getJSONObject("currentConditions");

            double temperaturaAtual = atual.optDouble("temp", Double.NaN);
            double umidade          = atual.optDouble("humidity", 0.0);
            String condicao         = atual.optString("conditions", "Não informado");
            double precipitacao     = atual.optDouble("precip", 0.0);
            double velocidadeVento  = atual.optDouble("windspeed", 0.0);
            double direcaoVento     = atual.optDouble("winddir", 0.0);

            if (Double.isNaN(temperaturaAtual)) {
                throw new WeatherApiException("Temperatura atual não encontrada na resposta da API.");
            }

            if (!raiz.has("days") || raiz.getJSONArray("days").isEmpty()) {
                throw new WeatherApiException("Resposta da API não contém dados diários ('days').");
            }
            JSONObject hoje = raiz.getJSONArray("days").getJSONObject(0);

            double temperaturaMaxima = hoje.optDouble("tempmax", Double.NaN);
            double temperaturaMinima = hoje.optDouble("tempmin", Double.NaN);

            if (Double.isNaN(temperaturaMaxima) || Double.isNaN(temperaturaMinima)) {
                throw new WeatherApiException("Temperaturas máxima/mínima não encontradas na resposta da API.");
            }

            return new WeatherData(
                cidade,
                enderecoResolvido,
                temperaturaAtual,
                temperaturaMaxima,
                temperaturaMinima,
                umidade,
                condicao,
                precipitacao,
                velocidadeVento,
                direcaoVento
            );

        } catch (WeatherApiException e) {
            throw e;
        } catch (Exception e) {
            throw new WeatherApiException("Falha ao interpretar o JSON da API: " + e.getMessage(), e);
        }
    }
}