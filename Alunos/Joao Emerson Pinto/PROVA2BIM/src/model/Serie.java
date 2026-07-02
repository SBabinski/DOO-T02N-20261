package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Serie {
    private int id;
    private String nome;
    private String idioma;
    private List<String> generos;
    private double nota;
    private String estado;
    private String estreia;
    private String termino;
    private String emissora;

    public Serie() {
        this.generos = new ArrayList<>();
    }

    public Serie(int id, String nome, String idioma, List<String> generos, double nota,
                 String estado, String estreia, String termino, String emissora) {
        this.id = id;
        this.nome = textoOuPadrao(nome);
        this.idioma = textoOuPadrao(idioma);
        this.generos = generos == null ? new ArrayList<>() : new ArrayList<>(generos);
        this.nota = nota;
        this.estado = textoOuPadrao(estado);
        this.estreia = textoOuPadrao(estreia);
        this.termino = textoOuPadrao(termino);
        this.emissora = textoOuPadrao(emissora);
    }

    private String textoOuPadrao(String texto) {
        return texto == null || texto.isBlank() ? "Não informado" : texto;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getIdioma() {
        return idioma;
    }

    public List<String> getGeneros() {
        return generos;
    }

    public double getNota() {
        return nota;
    }

    public String getEstado() {
        return estado;
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

    public String getGenerosFormatados() {
        if (generos == null || generos.isEmpty()) {
            return "Não informado";
        }
        return String.join(", ", generos);
    }

    public String getNotaFormatada() {
        return nota <= 0 ? "Sem nota" : String.format("%.1f", nota);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Serie serie)) {
            return false;
        }
        return id == serie.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
