package com.tvtracker.model;

import java.util.List;

/**
 * Representa uma série de TV com todas as informações relevantes
 * vindas da API TVMaze.
 */
public class Series {

    // Identificador único da série na API TVMaze
    private int id;
    private String name;
    private String language;
    private List<String> genres;
    private double rating;

    // Status: "Running", "Ended", "To Be Determined", "In Development"
    private String status;

    private String premiered;
    private String ended;
    private String network;
    private String summary;
    private String imageUrl;

    // Construtor padrão necessário para desserialização JSON pelo Gson
    public Series() {}

    public Series(int id, String name, String language, List<String> genres,
                  double rating, String status, String premiered, String ended,
                  String network, String summary, String imageUrl) {
        this.id = id;
        this.name = name;
        this.language = language;
        this.genres = genres;
        this.rating = rating;
        this.status = status;
        this.premiered = premiered;
        this.ended = ended;
        this.network = network;
        this.summary = summary;
        this.imageUrl = imageUrl;
    }

    // Retorna o nome dos gêneros separados por vírgula para exibição
    public String getGenresAsString() {
        if (genres == null || genres.isEmpty()) return "N/A";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < genres.size(); i++) {
            if (i > 0) sb.append(", ");
            sb.append(genres.get(i));
        }
        return sb.toString();
    }

    // Retorna a nota formatada ou "N/A" se não disponível
    public String getRatingFormatted() {
        if (rating <= 0) return "N/A";
        return String.format("%.1f", rating);
    }

    // Retorna o período de exibição formatado para a UI
    public String getAirPeriod() {
        String start = (premiered != null && !premiered.isEmpty()) ? premiered : "?";
        String end = (ended != null && !ended.isEmpty()) ? ended : "em exibição";
        return start + " → " + end;
    }

    // Traduz o status para português para melhor experiência do usuário.
    // A API TVMaze usa "Ended" tanto para séries encerradas normalmente quanto canceladas.
    public String getStatusInPortuguese() {
        if (status == null) return "Desconhecido";
        switch (status) {
            case "Running":          return "Em exibição";
            case "Ended":            return "Encerrada / Cancelada";
            case "To Be Determined": return "A determinar";
            case "In Development":   return "Em desenvolvimento";
            default:                 return status;
        }
    }

    // Getters e setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name != null ? name : "Sem nome"; }
    public void setName(String name) { this.name = name; }

    public String getLanguage() { return language != null ? language : "N/A"; }
    public void setLanguage(String language) { this.language = language; }

    public List<String> getGenres() { return genres; }
    public void setGenres(List<String> genres) { this.genres = genres; }

    public double getRating() { return rating; }
    public void setRating(double rating) { this.rating = rating; }

    public String getStatus() { return status != null ? status : "Unknown"; }
    public void setStatus(String status) { this.status = status; }

    public String getPremiered() { return premiered; }
    public void setPremiered(String premiered) { this.premiered = premiered; }

    public String getEnded() { return ended; }
    public void setEnded(String ended) { this.ended = ended; }

    public String getNetwork() { return network != null ? network : "N/A"; }
    public void setNetwork(String network) { this.network = network; }

    public String getSummary() { return summary != null ? summary : "Sem descrição disponível."; }
    public void setSummary(String summary) { this.summary = summary; }

    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }

    // Igualdade baseada no ID único da série
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Series)) return false;
        Series s = (Series) o;
        return this.id == s.id;
    }

    @Override
    public int hashCode() {
        return Integer.valueOf(id).hashCode();
    }

    @Override
    public String toString() {
        return name + " (" + getRatingFormatted() + ")";
    }
}
