package attDOO.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CurrentConditions {

    public double temp;

    public double humidity;

    public String conditions;

    public double precip;

    public double windspeed;

    public double winddir;
}