import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WeatherParser {

    public static WeatherData parse(
            String cidade,
            String json)
            throws WeatherException {

        try {

            double temperaturaAtual =
                    numero(json, "temp");

            double temperaturaMaxima =
                    numero(json, "tempmax");

            double temperaturaMinima =
                    numero(json, "tempmin");

            double umidade =
                    numero(json, "humidity");

            String condicao =
                    texto(json, "conditions");

            double precipitacao =
                    numero(json, "precip");

            double velocidadeVento =
                    numero(json, "windspeed");

            double direcaoVento =
                    numero(json, "winddir");

            return new WeatherData(
                    cidade,
                    temperaturaAtual,
                    temperaturaMaxima,
                    temperaturaMinima,
                    umidade,
                    condicao,
                    precipitacao,
                    velocidadeVento,
                    direcaoVento);

        } catch (Exception e) {

            throw new WeatherException(
                    "Erro ao interpretar JSON.");
        }
    }

    private static double numero(
            String json,
            String chave) {

        Pattern pattern =
                Pattern.compile(
                        "\"" + chave +
                        "\"\\s*:\\s*(-?[0-9.]+)");

        Matcher matcher =
                pattern.matcher(json);

        if (matcher.find()) {

            return Double.parseDouble(
                    matcher.group(1));
        }

        return 0;
    }

    private static String texto(
            String json,
            String chave) {

        Pattern pattern =
                Pattern.compile(
                        "\"" + chave +
                        "\"\\s*:\\s*\"([^\"]+)\"");

        Matcher matcher =
                pattern.matcher(json);

        if (matcher.find()) {

            return matcher.group(1);
        }

        return "";
    }
}