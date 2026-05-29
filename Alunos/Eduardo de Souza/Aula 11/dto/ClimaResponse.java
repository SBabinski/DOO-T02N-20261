package attDOO.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ClimaResponse {

    public CurrentConditions currentConditions;

    public List<Day> days;
}