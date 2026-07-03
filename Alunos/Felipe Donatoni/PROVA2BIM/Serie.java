import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Serie {

    private int id;
    private String nome;
    private String idioma;
    private List<String> generos;
    private double nota;
    private String estado;
    private LocalDate dataEstreia;
    private LocalDate dataTermino;
    private String emissora;
    private String resumo;

    public Serie(int id, String nome, String idioma, List<String> generos, double nota, String estado, LocalDate dataEstreia, LocalDate dataTermino, String emissora, String resumo) {
        this.id = id;
        this.nome = nome;
        this.idioma = idioma;
        this.generos = generos;
        this.nota = nota;
        this.estado = estado;
        this.dataEstreia = dataEstreia;
        this.dataTermino = dataTermino;
        this.emissora = emissora;
        this.resumo = resumo;
    }

    public int getId()               { return id; }
    public String getNome()          { return nome; }
    public String getIdioma()        { return idioma; }
    public List<String> getGeneros() { return generos; }
    public double getNota()          { return nota; }
    public String getEstado()        { return estado; }
    public LocalDate getDataEstreia()  { return dataEstreia; }
    public LocalDate getDataTermino()  { return dataTermino; }
    public String getEmissora()      { return emissora; }
    public String getResumo()        { return resumo; }

    public String getGenerosFormatado() {
        if (generos == null || generos.isEmpty()) return "N/A";
        return String.join(", ", generos);
    }

    public String getNotaFormatada() {
        if (nota == 0) return "N/A";
        return String.format("%.1f / 10", nota);
    }

    public String getDataEstreiaFormatada() {
        if (dataEstreia == null) return "N/A";
        return dataEstreia.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    public String getDataTerminoFormatada() {
        if (dataTermino == null) return "N/A";
        return dataTermino.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    @Override
    public String toString() {
        int ano = dataEstreia != null ? dataEstreia.getYear() : 0;
        return ano > 0 ? nome + " (" + ano + ")" : nome;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Serie) return this.id == ((Serie) obj).id;
        return false;
    }

    @Override
    public int hashCode() {
        return id;
    }
}
