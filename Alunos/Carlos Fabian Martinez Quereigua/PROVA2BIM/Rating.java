package model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)

public class Rating {

    private Double average;

    public Double getAverage() {
        return average;
    }
}