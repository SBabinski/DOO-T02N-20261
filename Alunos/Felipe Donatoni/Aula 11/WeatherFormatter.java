import java.util.LinkedHashMap;
import java.util.Map;

public final class WeatherFormatter {

    private WeatherFormatter() {}

    private static final Map<String, String> TRADUCOES = new LinkedHashMap<>();

    static {
        TRADUCOES.put("Partially cloudy", "Parcialmente nublado");
        TRADUCOES.put("Overcast", "Nublado");
        TRADUCOES.put("Cloudy", "Nublado");
        TRADUCOES.put("Clear", "Céu limpo");
        TRADUCOES.put("Rain, Partially cloudy", "Chuva com nuvens");
        TRADUCOES.put("Rain, Overcast", "Chuva e nublado");
        TRADUCOES.put("Rain", "Chuva");
        TRADUCOES.put("Heavy Rain", "Chuva forte");
        TRADUCOES.put("Light Rain", "Chuva fraca");
        TRADUCOES.put("Drizzle", "Garoa");
        TRADUCOES.put("Snow", "Neve");
        TRADUCOES.put("Light Snow", "Neve fraca");
        TRADUCOES.put("Heavy Snow", "Neve forte");
        TRADUCOES.put("Thunderstorm", "Tempestade");
        TRADUCOES.put("Thunder", "Trovão");
        TRADUCOES.put("Fog", "Neblina");
        TRADUCOES.put("Mist", "Névoa");
        TRADUCOES.put("Hail", "Granizo");
        TRADUCOES.put("Windy", "Ventoso");
        TRADUCOES.put("Sunny", "Ensolarado");
        TRADUCOES.put("Clearing in the afternoon", "Tempo abrindo à tarde");
        TRADUCOES.put("Clearing in the morning", "Tempo abrindo de manhã");
        TRADUCOES.put("Becoming cloudy in the afternoon", "Nublando à tarde");
        TRADUCOES.put("Becoming cloudy in the morning", "Nublando de manhã");
        TRADUCOES.put("Rain in the morning and afternoon", "Chuva de manhã e à tarde");
        TRADUCOES.put("Rain in the morning", "Chuva de manhã");
        TRADUCOES.put("Rain in the afternoon", "Chuva à tarde");
        TRADUCOES.put("Rain in the evening", "Chuva à noite");
        TRADUCOES.put("Similar conditions continuing", "Condições semelhantes continuam");
        TRADUCOES.put("No significant weather", "Sem eventos significativos");
    }

    public static String traduzir(String texto) {
        if (texto == null) return "N/D";
        for (Map.Entry<String, String> entry : TRADUCOES.entrySet()) {
            if (texto.toLowerCase().contains(entry.getKey().toLowerCase())) {
                return texto.replaceAll("(?i)" + entry.getKey(), entry.getValue());
            }
        }
        return texto;
    }

    public static String degreesToCardinal(Double degrees) {
        if (degrees == null) return "N/D";
        String[] directions = {
            "N", "NNE", "NE", "ENE",
            "L", "ESE", "SE", "SSE",
            "S", "SSO", "SO", "OSO",
            "O", "ONO", "NO", "NNO"
        };
        int index = (int) Math.round(degrees / 22.5) % 16;
        return directions[index];
    }

    public static String describeWindSpeed(Double kmh) {
        if (kmh == null) return "N/D";
        if (kmh < 2)  return "Calmaria";
        if (kmh < 12) return "Brisa leve";
        if (kmh < 29) return "Brisa fraca";
        if (kmh < 50) return "Vento moderado";
        if (kmh < 75) return "Vento forte";
        return "Tempestade";
    }

    public static String describeHumidity(Double humidity) {
        if (humidity == null) return "N/D";
        if (humidity < 25) return "Muito seco";
        if (humidity < 50) return "Confortável";
        if (humidity < 75) return "Úmido";
        return "Muito úmido";
    }

    public static String formatTemperature(Double temp) {
        if (temp == null) return "N/D";
        return String.format("%.1f°C", temp);
    }

    public static String formatPrecipitation(Double mm) {
        if (mm == null || mm == 0.0) return "0,00 mm (sem chuva)";
        return String.format("%.2f mm", mm);
    }

    public static String formatPrecipitationProbability(Double probability) {
        if (probability == null) return "N/D";
        return String.format("%.0f%%", probability);
    }

    public static String formatWindSpeed(Double kmh) {
        if (kmh == null) return "N/D";
        return String.format("%.1f km/h", kmh);
    }

    public static String formatHumidity(Double humidity) {
        if (humidity == null) return "N/D";
        return String.format("%.0f%%", humidity);
    }
}
