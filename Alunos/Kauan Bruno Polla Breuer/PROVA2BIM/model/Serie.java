package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Representa uma série de TV com todas as informações provenientes da API TVMaze.
 */
public class Serie {

    private int id;
    private String nome;
    private String idioma;
    private List<String> generos;
    private Double nota;
    private String estado;
    private String dataEstreia;
    private String dataTermino;
    private String emissora;

    public Serie() {
        this.generos = new ArrayList<>();
    }

    public Serie(int id, String nome, String idioma, List<String> generos,
                 Double nota, String estado, String dataEstreia,
                 String dataTermino, String emissora) {
        this.id = id;
        this.nome = nome;
        this.idioma = idioma;
        this.generos = (generos != null) ? generos : new ArrayList<>();
        this.nota = nota;
        this.estado = estado;
        this.dataEstreia = dataEstreia;
        this.dataTermino = dataTermino;
        this.emissora = emissora;
    }

    // ─── Getters ─────────────────────────────────────────────────────────────

    public int getId()                { return id; }
    public String getNome()           { return nome != null ? nome : ""; }
    public String getIdioma()         { return idioma != null ? idioma : "N/A"; }
    public List<String> getGeneros()  { return generos != null ? generos : new ArrayList<>(); }
    public Double getNota()           { return nota; }
    public String getEstado()         { return estado != null ? estado : "N/A"; }
    public String getDataEstreia()    { return dataEstreia; }
    public String getDataTermino()    { return dataTermino; }
    public String getEmissora()       { return emissora != null ? emissora : "N/A"; }

    // ─── Setters ─────────────────────────────────────────────────────────────

    public void setId(int id)                        { this.id = id; }
    public void setNome(String nome)                 { this.nome = nome; }
    public void setIdioma(String idioma)             { this.idioma = idioma; }
    public void setGeneros(List<String> generos)     { this.generos = generos; }
    public void setNota(Double nota)                 { this.nota = nota; }
    public void setEstado(String estado)             { this.estado = estado; }
    public void setDataEstreia(String dataEstreia)   { this.dataEstreia = dataEstreia; }
    public void setDataTermino(String dataTermino)   { this.dataTermino = dataTermino; }
    public void setEmissora(String emissora)         { this.emissora = emissora; }

    // ─── Helpers formatadores ─────────────────────────────────────────────────

    /** Retorna a nota formatada com uma casa decimal, ou "N/A". */
    public String getNotaFormatada() {
        return (nota != null) ? String.format("%.1f", nota) : "N/A";
    }

    /** Retorna os gêneros separados por vírgula, ou "N/A". */
    public String getGenerosFormatados() {
        if (generos == null || generos.isEmpty()) return "N/A";
        return String.join(", ", generos);
    }

    /** Retorna o estado traduzido para português. */
    public String getEstadoTraduzido() {
        if (estado == null) return "N/A";
        switch (estado) {
            case "Ended":            return "Encerrada";
            case "Running":          return "Em andamento";
            case "To Be Determined": return "A definir";
            case "In Development":   return "Em desenvolvimento";
            default:                 return estado;
        }
    }

    /** Data de estreia formatada, ou "N/A". */
    public String getDataEstreiaFormatada() {
        return (dataEstreia != null && !dataEstreia.isEmpty()) ? dataEstreia : "N/A";
    }

    /** Data de término formatada, ou "Em andamento". */
    public String getDataTerminoFormatada() {
        return (dataTermino != null && !dataTermino.isEmpty()) ? dataTermino : "Em andamento";
    }

    // ─── equals / hashCode / toString ────────────────────────────────────────

    /** Duas séries são iguais se tiverem o mesmo ID da API. */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Serie)) return false;
        Serie other = (Serie) obj;
        return this.id == other.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return nome + " (" + getDataEstreiaFormatada() + ")";
    }
}
