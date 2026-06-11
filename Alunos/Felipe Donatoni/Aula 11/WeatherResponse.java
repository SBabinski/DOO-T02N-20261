import java.util.List;

public class WeatherResponse {
    public String resolvedAddress;
    public String address;
    public String timezone;
    public Double latitude;
    public Double longitude;
    public String description;
    public CurrentConditions currentConditions;
    public List<DayForecast> days;

    public DayForecast getTodayForecast() {
        if (days == null || days.isEmpty()) return null;
        return days.get(0);
    }
}
