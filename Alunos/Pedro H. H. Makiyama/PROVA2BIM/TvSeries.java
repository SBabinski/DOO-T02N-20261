package com.pedrohhm.model;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TvSeries {

    private String name;
    private String language;
    private List<String> genres;
    private Rating rating;
    private String status;
    private LocalDate premiered;
    private LocalDate ended;
    private Network network;
    private Network webChannel;
    private int id;

    @Override
    public boolean equals(Object obj) {

        if (this == obj)
            return true;

        if (!(obj instanceof TvSeries other))
            return false;

        return id == other.id;
    }

    @Override
    public int hashCode() {

        return Integer.hashCode(id);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public List<String> getGenres() {
        return genres;
    }

    public void setGenres(List<String> genres) {
        this.genres = genres;
    }

    public Rating getRating() {
        return rating;
    }

    public void setRating(Rating rating) {
        this.rating = rating;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getPremiered() {
        return premiered;
    }

    public void setPremiered(LocalDate premiered) {
        this.premiered = premiered;
    }

    public LocalDate getEnded() {
        return ended;
    }

    public void setEnded(LocalDate ended) {
        this.ended = ended;
    }

    public Network getNetwork() {
        return network;
    }

    public void setNetwork(Network network) {
        this.network = network;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Network getWebChannel() {
        return webChannel;
    }

    public void setWebChannel(Network webChannel) {
        this.webChannel = webChannel;
    }

    public String getBroadcasterName() {

        if (network != null) {
            return network.getName();
        }

        if (webChannel != null) {
            return webChannel.getName();
        }

        return "-";
    }
}
