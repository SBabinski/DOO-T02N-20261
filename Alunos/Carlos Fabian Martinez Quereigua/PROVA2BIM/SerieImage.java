package model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SerieImage {

    private String medium;
    private String original;

    public String getMedium() {
        return medium;
    }

    public String getOriginal() {
        return original;
    }

    public void setMedium(String medium) {
        this.medium = medium;
    }

    public void setOriginal(String original) {
        this.original = original;
    }
}