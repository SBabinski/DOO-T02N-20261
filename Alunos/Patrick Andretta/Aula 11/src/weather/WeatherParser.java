package weather;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WeatherParser {

    public static WeatherData parse(String json, String city) throws WeatherException {
        try {
            String resolvedAddress = str(json, "resolvedAddress");

            String cur    = block(json, json.indexOf("\"currentConditions\""));
            String day    = block(json, json.indexOf('[', json.indexOf("\"days\"")));
            double tempMax = num(day, "tempmax");
            double tempMin = num(day, "tempmin");

            return new WeatherData(city, resolvedAddress,
                num(cur, "temp"), tempMax, tempMin,
                num(cur, "humidity"), str(cur, "conditions"),
                num(cur, "precip"), num(cur, "windspeed"), num(cur, "winddir"));
        } catch (WeatherException e) {
            throw e;
        } catch (Exception e) {
            throw new WeatherException("Erro ao interpretar a resposta da API.", 500);
        }
    }

    // Encontra o próximo bloco { ... } a partir de `from`, contando chaves aninhadas
    private static String block(String json, int from) throws WeatherException {
        int start = json.indexOf('{', from);
        if (start == -1) throw new WeatherException("Resposta da API malformada.", 500);
        int depth = 0;
        for (int i = start; i < json.length(); i++) {
            if      (json.charAt(i) == '{') depth++;
            else if (json.charAt(i) == '}' && --depth == 0) return json.substring(start, i + 1);
        }
        throw new WeatherException("Resposta da API malformada.", 500);
    }

    private static double num(String json, String key) {
        Matcher m = Pattern.compile("\"" + key + "\"\\s*:\\s*(-?[0-9]+\\.?[0-9]*)").matcher(json);
        try { return m.find() ? Double.parseDouble(m.group(1)) : 0.0; }
        catch (NumberFormatException e) { return 0.0; }
    }

    private static String str(String json, String key) {
        Matcher m = Pattern.compile("\"" + key + "\"\\s*:\\s*\"([^\"]*)\"").matcher(json);
        return m.find() ? m.group(1) : "";
    }
}
