package modelo;

public class Serie {

    private int id;
    private String nome;
    private String idioma;
    private String[] generos;
    private double nota;
    private String estado;
    private String dataEstreia;
    private String dataTermino;
    private String emissora;
    private String sinopse;

    public Serie() {}

    public Serie(int id, String nome, String idioma, String[] generos,
                 double nota, String estado, String dataEstreia,
                 String dataTermino, String emissora, String sinopse) {
        this.id          = id;
        this.nome        = nome;
        this.idioma      = idioma;
        this.generos     = generos;
        this.nota        = nota;
        this.estado      = estado;
        this.dataEstreia = dataEstreia;
        this.dataTermino = dataTermino;
        this.emissora    = emissora;
        this.sinopse     = sinopse;
    }

    public int      getId()          { return id; }
    public String   getNome()        { return nome; }
    public String   getIdioma()      { return idioma; }
    public String[] getGeneros()     { return generos; }
    public double   getNota()        { return nota; }
    public String   getEstado()      { return estado; }
    public String   getDataEstreia() { return dataEstreia; }
    public String   getDataTermino() { return dataTermino; }
    public String   getEmissora()    { return emissora; }
    public String   getSinopse()     { return sinopse; }

    public void setId(int id)                   { this.id = id; }
    public void setNome(String nome)            { this.nome = nome; }
    public void setIdioma(String idioma)        { this.idioma = idioma; }
    public void setGeneros(String[] generos)    { this.generos = generos; }
    public void setNota(double nota)            { this.nota = nota; }
    public void setEstado(String estado)        { this.estado = estado; }
    public void setDataEstreia(String d)        { this.dataEstreia = d; }
    public void setDataTermino(String d)        { this.dataTermino = d; }
    public void setEmissora(String emissora)    { this.emissora = emissora; }
    public void setSinopse(String sinopse)      { this.sinopse = sinopse; }

    public String getGenerosFormatados() {
        if (generos == null || generos.length == 0) return "N/A";
        return String.join(", ", generos);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Serie outro)) return false;
        return this.id == outro.id;
    }

    @Override
    public int hashCode() { return Integer.hashCode(id); }

    @Override
    public String toString() { return nome != null ? nome : "Série sem nome"; }
}
