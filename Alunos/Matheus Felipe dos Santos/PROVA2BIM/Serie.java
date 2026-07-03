import java.util.List;
import java.util.Objects;

public class Serie {

    private String nome;
    private String idioma;
    private double nota;
    private String status;
    private List<String> generos;
    private String estreia;
    private String termino;
    private String emissora;
    private String imagemUrl;

    public Serie() {
    }

    public Serie(String nome, String idioma, double nota, String status,
                 List<String> generos, String estreia, String termino,
                 String emissora, String imagemUrl) {

        this.nome = nome;
        this.idioma = idioma;
        this.nota = nota;
        this.status = status;
        this.generos = generos;
        this.estreia = estreia;
        this.termino = termino;
        this.emissora = emissora;
        this.imagemUrl = imagemUrl;
    }

    public String getNome() {
        return nome;
    }

    public String getIdioma() {
        return idioma;
    }

    public double getNota() {
        return nota;
    }

    public String getStatus() {
        return status;
    }

    public List<String> getGeneros() {
        return generos == null ? List.of() : generos;
    }

    public String getEstreia() {
        return estreia;
    }

    public String getTermino() {
        return termino;
    }

    public String getEmissora() {
        return emissora;
    }

    public String getImagemUrl() {
        return imagemUrl == null ? "" : imagemUrl;
    }

    @Override
    public String toString() {
        return String.format("%s | Nota: %.1f", nome, nota);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Serie)) return false;

        Serie serie = (Serie) o;
        return nome != null && nome.equalsIgnoreCase(serie.nome); 
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome);
    }

        public java.time.LocalDate getDataEstreia() {
        try {
            return java.time.LocalDate.parse(estreia);
        } catch (Exception e) {
            return java.time.LocalDate.MIN;
        }
    }
}