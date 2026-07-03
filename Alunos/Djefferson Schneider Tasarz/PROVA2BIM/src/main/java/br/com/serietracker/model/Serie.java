package br.com.serietracker.model;

import java.util.List;

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
    }

    public Serie(int id, String nome, String idioma,
                 List<String> generos, double nota,
                 String estado, String estreia,
                 String termino, String emissora) {

        this.id = id;
        this.nome = nome;
        this.idioma = idioma;
        this.generos = generos;
        this.nota = nota;
        this.estado = estado;
        this.estreia = estreia;
        this.termino = termino;
        this.emissora = emissora;
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

    @Override
    public String toString() {
        return nome;
    }
}