package model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class WebChannel {

    private String name;

    public String getName() {
        return name;
    }
}