public class Serie {
    private String nome, idioma, generos, nota, estado, dataEstreia, dataTermino, emissora;

    public Serie(String nome, String idioma, String generos, String nota, String estado, String dataEstreia, String dataTermino, String emissora) {
        this.nome = nome;
        this.idioma = idioma;
        this.generos = generos;
        this.nota = nota;
        this.estado = estado;
        this.dataEstreia = dataEstreia;
        this.dataTermino = dataTermino;
        this.emissora = emissora;
    }

    public String getNome() { return nome; }
    public String getIdioma() { return idioma; }
    public String getGeneros() { return generos; }
    public String getNota() { return nota; }
    public String getEstado() { return estado; }
    public String getDataEstreia() { return dataEstreia; }
    public String getDataTermino() { return dataTermino; }
    public String getEmissora() { return emissora; }
}