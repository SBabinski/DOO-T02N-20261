package weather;

import java.util.Locale;

public class WeatherData {

    private final String city, resolvedAddress, conditions, windCardinal;
    private final double currentTemp, tempMax, tempMin, humidity, precip, windSpeed, windDir;

    public WeatherData(String city, String resolvedAddress, double currentTemp,
                       double tempMax, double tempMin, double humidity, String conditions,
                       double precip, double windSpeed, double windDir) {
        this.city            = city;
        this.resolvedAddress = resolvedAddress;
        this.currentTemp     = currentTemp;
        this.tempMax         = tempMax;
        this.tempMin         = tempMin;
        this.humidity        = humidity;
        this.conditions      = conditions;
        this.precip          = precip;
        this.windSpeed       = windSpeed;
        this.windDir         = windDir;
        this.windCardinal    = WindDirectionConverter.toCardinal(windDir);
    }

    public String toJson() {
        return String.format(Locale.US,
            "{\"city\":\"%s\",\"resolvedAddress\":\"%s\"," +
            "\"currentTemp\":%.1f,\"tempMax\":%.1f,\"tempMin\":%.1f," +
            "\"humidity\":%.1f,\"conditions\":\"%s\"," +
            "\"precip\":%.1f,\"windSpeed\":%.1f,\"windDir\":%.1f,\"windCardinal\":\"%s\"}",
            esc(city), esc(resolvedAddress), currentTemp, tempMax, tempMin,
            humidity, esc(conditions), precip, windSpeed, windDir, windCardinal);
    }

    private String esc(String s) {
        return s == null ? "" : s.replace("\\", "\\\\").replace("\"", "\\\"");
    }
}
