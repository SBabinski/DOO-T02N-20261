package br.com.serietracker.model;

import java.util.ArrayList;
import java.util.List;

public class Usuario {

    private String apelido;

    private List<Serie> favoritos =
            new ArrayList<>();

    private List<Serie> assistidas =
            new ArrayList<>();

    private List<Serie> desejoAssistir =
            new ArrayList<>();

    public Usuario() {
    }

    public Usuario(String apelido) {
        this.apelido = apelido;
    }

    public String getApelido() {
        return apelido;
    }

    public void setApelido(String apelido) {
        this.apelido = apelido;
    }

    public List<Serie> getFavoritos() {
        return favoritos;
    }

    public List<Serie> getAssistidas() {
        return assistidas;
    }

    public List<Serie> getDesejoAssistir() {
        return desejoAssistir;
    }
}