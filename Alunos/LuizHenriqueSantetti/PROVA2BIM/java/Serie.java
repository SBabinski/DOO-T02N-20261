import java.util.ArrayList;

public class Serie {
    private int id;
    private String nome;
    private String idioma;
    private ArrayList<String> generos;
    private double nota;
    private String estado;
    private String dataEstreia;
    private String dataTermino;
    private String emissora;

    public Serie(int id, String nome, String idioma, ArrayList<String> generos, double nota,
            String estado, String dataEstreia, String dataTermino, String emissora) {
        this.id = id;
        this.nome = nome;
        this.idioma = idioma;
        this.generos = generos == null ? new ArrayList<String>() : generos;
        this.nota = nota; // -1 significa que a API nao informou uma nota.
        this.estado = estado;
        this.dataEstreia = dataEstreia;
        this.dataTermino = dataTermino;
        this.emissora = emissora;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public ArrayList<String> getGeneros() {
        return generos;
    }

    public void setGeneros(ArrayList<String> generos) {
        this.generos = generos;
    }

    public double getNota() {
        return nota;
    }

    public void setNota(double nota) {
        this.nota = nota;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getDataEstreia() {
        return dataEstreia;
    }

    public void setDataEstreia(String dataEstreia) {
        this.dataEstreia = dataEstreia;
    }

    public String getDataTermino() {
        return dataTermino;
    }

    public void setDataTermino(String dataTermino) {
        this.dataTermino = dataTermino;
    }

    public String getEmissora() {
        return emissora;
    }

    public void setEmissora(String emissora) {
        this.emissora = emissora;
    }

    public boolean mesmaSerie(Serie outra) {
        if (outra == null) {
            return false;
        }
        if (id > 0 && outra.id > 0) {
            return id == outra.id;
        }
        return texto(nome).equalsIgnoreCase(texto(outra.nome));
    }

    public String getNomeExibicao() {
        return valorOuPadrao(nome, "Sem nome");
    }

    public String getIdiomaExibicao() {
        return valorOuPadrao(idioma, "Nao informado");
    }

    public String getGenerosExibicao() {
        if (generos == null || generos.isEmpty())
            return "Nao informado";
        return String.join(", ", generos);
    }

    public String getNotaExibicao() {
        return nota < 0 ? "Sem nota" : String.valueOf(nota);
    }

    public String getEstadoExibicao() {
        String valor = valorOuPadrao(estado, "Nao informado");
        if (valor.equalsIgnoreCase("Running"))
            return "Em exibicao";
        if (valor.equalsIgnoreCase("Ended"))
            return "Concluida";
        if (valor.equalsIgnoreCase("To Be Determined"))
            return "A definir";
        if (valor.equalsIgnoreCase("In Development"))
            return "Em desenvolvimento";
        return valor;
    }

    public String getDataEstreiaExibicao() {
        return valorOuPadrao(dataEstreia, "Nao informada");
    }

    public String getDataTerminoExibicao() {
        return valorOuPadrao(dataTermino, "Nao informada");
    }

    public String getEmissoraExibicao() {
        return valorOuPadrao(emissora, "Nao informada");
    }

    private String valorOuPadrao(String valor, String padrao) {
        return texto(valor).isEmpty() ? padrao : valor.trim();
    }

    private String texto(String valor) {
        return valor == null ? "" : valor.trim();
    }

    public String toString() {
        return getNomeExibicao();
    }
}
