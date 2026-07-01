package tvtracker.model;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Show {

    private final int id;
    private String name;
    private String language;
    private List<String> genres;
    private Double rating;       
    private String status;       
    private String premiered;    
    private String ended;        
    private String network;     
    private String summary;      
    private String imageUrl;     
    public Show(int id, String name, String language, List<String> genres,
                Double rating, String status, String premiered, String ended,
                String network, String summary, String imageUrl) {
        this.id = id;
        this.name = name;
        this.language = language;
        this.genres = genres != null ? new ArrayList<>(genres) : new ArrayList<>();
        this.rating = rating;
        this.status = status;
        this.premiered = premiered;
        this.ended = ended;
        this.network = network;
        this.summary = summary;
        this.imageUrl = imageUrl;
    }



    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLanguage() {
        return language;
    }

    public List<String> getGenres() {
        return genres;
    }

    public String getGenresAsText() {
        if (genres == null || genres.isEmpty()) {
            return "—";
        }
        return String.join(", ", genres);
    }

    public Double getRating() {
        return rating;
    }

    public String getRatingAsText() {
        return rating != null ? String.format("%.1f", rating) : "—";
    }

    public String getStatus() {
        return status;
    }

    public String getPremiered() {
        return premiered;
    }

    public String getEnded() {
        return ended;
    }

    public String getNetwork() {
        return network;
    }

    public String getSummary() {
        return summary;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    
    public Map<String, Object> toMap() {
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("id", (double) id);
        map.put("name", name);
        map.put("language", language);
        map.put("genres", new ArrayList<Object>(genres));
        map.put("rating", rating);
        map.put("status", status);
        map.put("premiered", premiered);
        map.put("ended", ended);
        map.put("network", network);
        map.put("summary", summary);
        map.put("imageUrl", imageUrl);
        return map;
    }

    
    @SuppressWarnings("unchecked")
    public static Show fromMap(Map<String, Object> map) {
        int id = (int) Math.round(asDouble(map.get("id"), -1));
        String name = asString(map.get("name"));
        String language = asString(map.get("language"));

        List<String> genres = new ArrayList<>();
        Object genresObj = map.get("genres");
        if (genresObj instanceof List) {
            for (Object g : (List<Object>) genresObj) {
                if (g != null) {
                    genres.add(String.valueOf(g));
                }
            }
        }

        Double rating = map.get("rating") != null ? asDouble(map.get("rating"), 0) : null;
        String status = asString(map.get("status"));
        String premiered = asString(map.get("premiered"));
        String ended = asString(map.get("ended"));
        String network = asString(map.get("network"));
        String summary = asString(map.get("summary"));
        String imageUrl = asString(map.get("imageUrl"));

        return new Show(id, name, language, genres, rating, status, premiered,
                ended, network, summary, imageUrl);
    }

    private static String asString(Object o) {
        return o == null ? null : String.valueOf(o);
    }

    private static double asDouble(Object o, double fallback) {
        if (o instanceof Number) {
            return ((Number) o).doubleValue();
        }
        if (o instanceof String) {
            try {
                return Double.parseDouble((String) o);
            } catch (NumberFormatException e) {
                return fallback;
            }
        }
        return fallback;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Show)) return false;
        Show show = (Show) o;
        return id == show.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return name + " (" + (premiered != null ? premiered.substring(0, 4) : "?") + ")";
    }
}
