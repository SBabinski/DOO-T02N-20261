package com.prova.model;
import java.util.ArrayList;
import java.util.List;

public class Serie {
    private int id;
    private String nome;
    private String idioma;
    private List<String> generos = new ArrayList<>();
    private Double nota;
    private String estado;
    private String dataEstreia;
    private String dataTermino;
    private String emissora;

    public Serie() {

    }

    public Serie(int id, String nome, String idioma, List<String> generos, Double nota, String estado, String dataEstreia, String dataTermino, String emissora) {
        this.id = id;
        this.nome = nome;
        this.idioma = idioma;
        this.generos = generos != null ? generos : new ArrayList<>();
        this.nota = nota;
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

    public List<String> getGeneros() {
        return generos;
    }   

    public void setGeneros(List<String> generos) {
        this.generos = generos != null ? generos : new ArrayList<>();
    }

    public Double getNota() {
        return nota;
    }

    public void setNota(Double nota) {
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

    @Override
    public String toString() {
        return nome;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof Serie)) {
            return false;
        }

        Serie outraSerie = (Serie) obj;
        return this.id == outraSerie.id;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(id);
    }

}
