import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class TimelineResponse {

    private int queryCost;
    private double latitude;
    private double longitude;
    private String resolvedAddress;
    private String address;
    private String timezone;
    private double tzoffset;
    private String description;
    private List<DailyData> days;
    private List<WeatherAlert> alerts;
    private CurrentConditions currentConditions;
    private Map<String, StationInfo> stations;
}

@JsonIgnoreProperties(ignoreUnknown = true)
class DailyData {

    private String datetime;
    private long datetimeEpoch;

    private double tempmax;
    private double tempmin;
    private double temp;
    private double feelslikemax;
    private double feelslikemin;
    private double feelslike;
    private double humidity;
    private double dew;
    private double precip;
    private Double precipprob;
    private double precipcover;
    private List<String> preciptype;
    private double windspeed;
    private Double windgust;
    private double winddir;
    private double pressure;
    private double cloudcover;
    private double visibility;
    private int uvindex;
    private String sunrise;
    private long sunriseEpoch;
    private String sunset;
    private long sunsetEpoch;
    private double moonphase;
    private String conditions;
    private String description;
    private String icon;
    private String source;
    private List<String> stations;
    private List<HourlyData> hours;
}

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
class HourlyData {

    /** Formato: HH:mm:ss */
    private String datetime;
    private long datetimeEpoch;

    private double temp;
    private double feelslike;
    private double humidity;
    private double dew;

    private double precip;
    private Double precipprob;
    private List<String> preciptype;

    private double windspeed;
    private Double windgust;
    private double winddir;

    private double pressure;
    private double cloudcover;
    private double visibility;

    private double solarradiation;
    private double solarenergy;
    private int uvindex;

    private String conditions;
    private String icon;
    private String source;
}

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
class CurrentConditions {

    private String datetime;
    private long datetimeEpoch;

    private double temp;
    private double feelslike;
    private double humidity;
    private double dew;

    private double precip;
    private Double precipprob;

    private double windspeed;
    private Double windgust;
    private double winddir;

    private double pressure;
    private double cloudcover;
    private double visibility;

    private int uvindex;
    private String sunrise;
    private String sunset;
    private double moonphase;

    private String conditions;
    private String icon;
}

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
class WeatherAlert {
    private String event;
    private String onset;
    private String ends;
    private String description;
    private String link;
}

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
class StationInfo {
    private String name;
    private double latitude;
    private double longitude;
    private double distance;
    private double elevation;
}
