import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;


public class WeatherService {

    public WeatherData fetch(String city, String apiKey) throws IOException {
        String encoded = URLEncoder.encode(city, StandardCharsets.UTF_8);
        String url = AppConfig.API_BASE_URL + encoded
            + "/today?unitGroup=metric&include=current,days&key=" + apiKey
            + "&contentType=json";

        HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
        conn.setRequestMethod("GET");
        conn.setConnectTimeout(AppConfig.CONNECT_TIMEOUT_MS);
        conn.setReadTimeout(AppConfig.READ_TIMEOUT_MS);
        conn.setRequestProperty("Accept", "application/json");

        int code = conn.getResponseCode();
        if (code != 200) {
            InputStream es = conn.getErrorStream();
            String body = es != null
                ? new String(es.readAllBytes(), StandardCharsets.UTF_8) : "";
            throw new IOException(friendlyError(body, code));
        }

        String body = new String(conn.getInputStream().readAllBytes(), StandardCharsets.UTF_8);
        return parse(new SimpleJSON.JSONObject(body));
    }


    private WeatherData parse(SimpleJSON.JSONObject root) {
        SimpleJSON.JSONObject cur = root.optJSONObject("currentConditions");
        if (cur == null) cur = new SimpleJSON.JSONObject();

        SimpleJSON.JSONObject day = new SimpleJSON.JSONObject();
        SimpleJSON.JSONArray days = root.optJSONArray("days");
        if (days != null && !days.isEmpty()) day = days.getJSONObject(0);

        String address = root.optString("resolvedAddress", "—");

        CurrentTemp currentTemp = new CurrentTemp(
            cur.optDouble("temp", Double.NaN));

        DailyTempRange tempRange = new DailyTempRange(
            day.optDouble("tempmax", Double.NaN),
            day.optDouble("tempmin", Double.NaN));

        Humidity humidity = new Humidity(
            cur.optDouble("humidity", day.optDouble("humidity", Double.NaN)));

        WeatherCondition condition = new WeatherCondition(
            cur.optString("conditions", day.optString("conditions", "")));

        Precipitation precipitation = new Precipitation(
            cur.optDouble("precip",    day.optDouble("precip",    0.0)),
            day.optDouble("precipprob", 0.0));

        WindInfo wind = new WindInfo(
            cur.optDouble("windspeed", day.optDouble("windspeed", Double.NaN)),
            cur.optDouble("winddir",   day.optDouble("winddir",   Double.NaN)));

        double visibility = cur.optDouble("visibility", day.optDouble("visibility", Double.NaN));
        double pressure   = cur.optDouble("pressure",   day.optDouble("pressure",   Double.NaN));

        return new WeatherData(
            address, currentTemp, tempRange, humidity,
            condition, precipitation, wind, visibility, pressure);
    }


    private String friendlyError(String body, int code) {
        try {
            SimpleJSON.JSONObject j = new SimpleJSON.JSONObject(body);
            String msg = j.optString("message", "");
            if (!msg.isEmpty()) return msg;
        } catch (Exception ignored) {}

        return switch (code) {
            case 401 -> "API Key inválida ou sem permissão.";
            case 404 -> "Cidade não encontrada. Tente outro nome.";
            case 429 -> "Limite de requisições excedido. Aguarde e tente novamente.";
            default  -> "Erro HTTP " + code + " ao buscar dados da API.";
        };
    }
}
