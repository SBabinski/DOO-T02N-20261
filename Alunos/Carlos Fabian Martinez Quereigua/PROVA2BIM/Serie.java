package model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.LocalDate;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Serie {
    private Integer id;
    private String name;
    private String language;
    private List<String> genres;
    private String status;
    private LocalDate premiered;
    private LocalDate ended;

    private Rating rating;
    private WebChannel webChannel;
    private Network network;
    private SerieImage image;

    public Serie(){

    }

    public Serie(String name, String language, List<String> genres,
                 String status, LocalDate premiered, LocalDate ended,
                 Rating rating, WebChannel webChannel, Network network) {
        this.name = name;
        this.language = language;
        this.genres = genres;
        this.status = status;
        this.premiered = premiered;
        this.ended = ended;
        this.rating = rating;
        this.webChannel = webChannel;
        this.network = network;
    }

    //GETTERS
    public Integer getId() { return id; }

    public String getName() { return name;}

    public String getLanguage() { return language; }

    public List<String> getGenres() {
        return genres;
    }

    public String getStatus() {
        return status;
    }

    public LocalDate getPremiered() {
        return premiered;
    }

    public LocalDate getEnded() {
        return ended;
    }

    public Rating getRating() {
        return rating;
    }

    public WebChannel getWebChannel() {
        return webChannel;
    }

    public Network getNetwork() {
        return network;
    }

    public SerieImage getImage() {
        return image;
    }

    //SETTERS
    public void setId(Integer id) {this.id = id;}

    public void setName(String name) {this.name = name;}

    public void setLanguage(String language) {this.language = language;}

    public void setGenres(List<String> genres) {this.genres = genres;}

    public void setStatus(String status) {this.status = status;}

    public void setPremiered(LocalDate premiered) {this.premiered = premiered;}

    public void setEnded(LocalDate ended) {this.ended = ended;}

    public void setRating(Rating rating) {this.rating = rating;}

    public void setWebChannel(WebChannel webChannel) {this.webChannel = webChannel;}

    public void setNetwork(Network network) {this.network = network;}

    public void setImage(SerieImage image) {this.image = image;}

    //Retorna a nota geral da série.

    public Double getNotaGeral() {
        return rating != null ? rating.getAverage() : null;
    }

    //Retorna o nome da emissora ou plataforma.

    public String getNomeEmissora() {
        if (network != null) {
            return network.getName();
        }

        if (webChannel != null) {
            return webChannel.getName();
        }

        return "Não informado";
    }

    @Override
    public String toString() {
        return  "Id: " + id +
                "\nNome: " + name +
                "\nIdioma: " + language +
                "\nGêneros: " + genres +
                "\nNota Geral: " + getNotaGeral() +
                "\nStatus: " + status +
                "\nEstreia: " + premiered +
                "\nTérmino: " + ended +
                "\nEmissora: " + getNomeEmissora();
    }

    public String getUrlImage() {
        if (image != null && image.getMedium() != null) {
            return image.getMedium();
        }

        return null;
    }
}