package com.tvtracker.model;

import java.util.List;
import java.util.ArrayList;

/**
 * Representa uma série de TV com todos os dados relevantes.
 */
public class TvSeries {

    private int id;
    private String name;
    private String language;
    private List<String> genres;
    private double rating;
    private SeriesStatus status;
    private String premiereDate;
    private String endDate;
    private String networkName;
    private String summary;
    private String imageUrl;

    public TvSeries() {
        this.genres = new ArrayList<>();
        this.rating = 0.0;
    }

    public TvSeries(int id, String name, String language, List<String> genres,
                    double rating, SeriesStatus status, String premiereDate,
                    String endDate, String networkName, String summary, String imageUrl) {
        this.id = id;
        this.name = name;
        this.language = language;
        this.genres = genres != null ? genres : new ArrayList<>();
        this.rating = rating;
        this.status = status;
        this.premiereDate = premiereDate;
        this.endDate = endDate;
        this.networkName = networkName;
        this.summary = summary;
        this.imageUrl = imageUrl;
    }

    // Getters e Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getLanguage() { return language; }
    public void setLanguage(String language) { this.language = language; }

    public List<String> getGenres() { return genres; }
    public void setGenres(List<String> genres) { this.genres = genres; }

    public double getRating() { return rating; }
    public void setRating(double rating) { this.rating = rating; }

    public SeriesStatus getStatus() { return status; }
    public void setStatus(SeriesStatus status) { this.status = status; }

    public String getPremiereDate() { return premiereDate; }
    public void setPremiereDate(String premiereDate) { this.premiereDate = premiereDate; }

    public String getEndDate() { return endDate; }
    public void setEndDate(String endDate) { this.endDate = endDate; }

    public String getNetworkName() { return networkName; }
    public void setNetworkName(String networkName) { this.networkName = networkName; }

    public String getSummary() { return summary; }
    public void setSummary(String summary) { this.summary = summary; }

    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }

    public String getGenresAsString() {
        if (genres == null || genres.isEmpty()) return "N/A";
        return String.join(", ", genres);
    }

    public String getRatingFormatted() {
        if (rating <= 0) return "N/A";
        return String.format("%.1f", rating);
    }

    public String getStatusLabel() {
        if (status == null) return "Desconhecido";
        return status.getLabel();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof TvSeries other)) return false;
        return this.id == other.id;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(id);
    }

    @Override
    public String toString() {
        return name;
    }
}
