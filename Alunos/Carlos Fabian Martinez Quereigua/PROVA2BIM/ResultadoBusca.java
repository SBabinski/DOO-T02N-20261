package model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)

public class ResultadoBusca {

    private Serie show;

    public Serie getShow() {
        return show;
    }
}