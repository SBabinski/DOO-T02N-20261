package app.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Serie {

    private Integer id;
    private String name;
    private String language;
    private List<String> genres;
    private Double rating;
    private String status;
    private String premiered;
    private String ended;
    private String network;
    private String summary;

    public Serie() {}

    public Serie(Integer id,
                 String name,
                 String language,
                 List<String> genres,
                 Double rating,
                 String status,
                 String premiered,
                 String ended,
                 String network,
                 String summary) {

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
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name == null ? "" : name;
    }

    public String getLanguage() {
        return language == null || language.isBlank()
                ? "Não informado"
                : language;
    }

    public List<String> getGenres() {
        return genres;
    }

    public Double getRating() {
        return rating == null ? 0.0 : rating;
    }

    public String getStatus() {
        return status == null ? "" : status;
    }

    public String getPremiered() {
        return premiered == null ? "" : premiered;
    }

    public String getEnded() {
        return ended == null ? "" : ended;
    }

    public String getNetwork() {
        return network == null || network.isBlank()
                ? "Não informada"
                : network;
    }

    public String getSummary() {
        return summary == null || summary.isBlank()
                ? "Descrição não disponível em português."
                : summary;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public void setGenres(List<String> genres) {
        this.genres = genres;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setPremiered(String premiered) {
        this.premiered = premiered;
    }

    public void setEnded(String ended) {
        this.ended = ended;
    }

    public void setNetwork(String network) {
        this.network = network;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    @JsonIgnore
    public String getStatusEmPortugues() {

        return switch (getStatus()) {
            case "Running" -> "Em andamento";
            case "Ended" -> "Concluída";
            case "Canceled", "Cancelled" -> "Cancelada";
            case "In Development" -> "Em desenvolvimento";
            case "To Be Determined" -> "A definir";
            default -> getStatus().isBlank()
                    ? "Não informado"
                    : getStatus();
        };
    }

    @JsonIgnore
    public String getGenerosEmPortugues() {

        if (genres == null || genres.isEmpty()) {
            return "Não informado";
        }

        return genres.stream()
                .map(Serie::traduzirGenero)
                .toList()
                .toString();
    }

    private static String traduzirGenero(String genero) {

        if (genero == null) {
            return "Não informado";
        }

        return switch (genero) {
            case "Action" -> "Ação";
            case "Adventure" -> "Aventura";
            case "Anime" -> "Anime";
            case "Children" -> "Infantil";
            case "Comedy" -> "Comédia";
            case "Crime" -> "Crime";
            case "DIY" -> "Faça você mesmo";
            case "Drama" -> "Drama";
            case "Espionage" -> "Espionagem";
            case "Family" -> "Família";
            case "Fantasy" -> "Fantasia";
            case "Food" -> "Culinária";
            case "History" -> "História";
            case "Horror" -> "Terror";
            case "Legal" -> "Jurídico";
            case "Medical" -> "Médico";
            case "Music" -> "Música";
            case "Mystery" -> "Mistério";
            case "Nature" -> "Natureza";
            case "Romance" -> "Romance";
            case "Science-Fiction" -> "Ficção científica";
            case "Sports" -> "Esportes";
            case "Supernatural" -> "Sobrenatural";
            case "Thriller" -> "Suspense";
            case "Travel" -> "Viagem";
            case "War" -> "Guerra";
            case "Western" -> "Faroeste";
            default -> genero;
        };
    }

    @JsonIgnore
    public String getPremieredFormatado() {
        return formatarData(getPremiered());
    }

    @JsonIgnore
    public String getEndedFormatado() {
        return formatarData(getEnded());
    }

    private String formatarData(String data) {

        if (data == null || data.isBlank()) {
            return "Não informada";
        }

        try {
            LocalDate localDate = LocalDate.parse(data);
            return localDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        } catch (Exception e) {
            return data;
        }
    }

    @Override
    public String toString() {

        return "Nome: " + getName() +
                "\nIdioma original: " + getLanguage() +
                "\nGêneros: " + getGenerosEmPortugues() +
                "\nNota geral: " + getRating() +
                "\nEstado da série: " + getStatusEmPortugues() +
                "\nData de estreia: " + getPremieredFormatado() +
                "\nData de encerramento: " + getEndedFormatado() +
                "\nEmissora: " + getNetwork() +
                "\n\nDescrição:\n" + getSummary();
    }
}
