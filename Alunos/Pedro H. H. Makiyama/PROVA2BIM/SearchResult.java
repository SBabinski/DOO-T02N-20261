package com.pedrohhm.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SearchResult {

    private TvSeries show;

    public TvSeries getShow() {
        return show;
    }

    public void setShow(TvSeries show) {
        this.show = show;
    }
}
