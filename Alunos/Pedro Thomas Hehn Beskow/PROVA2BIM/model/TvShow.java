package tvtracker.model;

import java.util.ArrayList;
import java.util.List;

public class TvShow {
    private int id;
    private String name;
    private String language;
    private List<String> genres;
    private double rating;
    private String status;
    private String premiered;   // data de estreia
    private String ended;       // data de término
    private String network;     // emissora
    private String summary;
    private String imageUrl;

    public TvShow() {
        this.genres = new ArrayList<>();
        this.rating = 0.0;
    }

    public TvShow(int id, String name, String language, List<String> genres,
                  double rating, String status, String premiered, String ended,
                  String network, String summary, String imageUrl) {
        this.id = id;
        this.name = name;
        this.language = language;
        this.genres = genres != null ? genres : new ArrayList<>();
        this.rating = rating;
        this.status = status;
        this.premiered = premiered;
        this.ended = ended;
        this.network = network;
        this.summary = summary;
        this.imageUrl = imageUrl;
    }

    // Getters
    public int getId()          { return id; }
    public String getName()     { return name; }
    public String getLanguage() { return language; }
    public List<String> getGenres() { return genres; }
    public double getRating()   { return rating; }
    public String getStatus()   { return status; }
    public String getPremiered(){ return premiered; }
    public String getEnded()    { return ended; }
    public String getNetwork()  { return network; }
    public String getSummary()  { return summary; }
    public String getImageUrl() { return imageUrl; }

    // Setters
    public void setId(int id)             { this.id = id; }
    public void setName(String name)      { this.name = name; }
    public void setLanguage(String lang)  { this.language = lang; }
    public void setGenres(List<String> g) { this.genres = g; }
    public void setRating(double rating)  { this.rating = rating; }
    public void setStatus(String status)  { this.status = status; }
    public void setPremiered(String p)    { this.premiered = p; }
    public void setEnded(String e)        { this.ended = e; }
    public void setNetwork(String n)      { this.network = n; }
    public void setSummary(String s)      { this.summary = s; }
    public void setImageUrl(String url)   { this.imageUrl = url; }

    public String getGenresDisplay() {
        if (genres == null || genres.isEmpty()) return "N/A";
        return String.join(", ", genres);
    }

    public String getRatingDisplay() {
        if (rating <= 0) return "N/A";
        return String.format("%.1f / 10", rating);
    }

    public String getStatusDisplay() {
        if (status == null) return "Desconhecido";
        return switch (status) {
            case "Ended"     -> "Encerrada";
            case "Running"   -> "Em exibição";
            case "To Be Determined" -> "A ser determinado";
            case "In Development"   -> "Em desenvolvimento";
            default -> status;
        };
    }

    @Override
    public String toString() {
        return name + " (" + (premiered != null ? premiered.substring(0, 4) : "?") + ")";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof TvShow)) return false;
        return this.id == ((TvShow) obj).id;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(id);
    }
}
