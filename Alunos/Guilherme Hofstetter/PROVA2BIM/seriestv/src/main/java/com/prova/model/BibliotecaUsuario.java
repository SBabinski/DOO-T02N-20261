package com.prova.model;

import java.util.ArrayList;
import java.util.List;

public class BibliotecaUsuario {

    private Usuario usuario;
    private List<Serie> favoritos = new ArrayList<>();
    private List<Serie> assistidas = new ArrayList<>();
    private List<Serie> desejoAssistir = new ArrayList<>();

    public BibliotecaUsuario() {
    }

    public BibliotecaUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<Serie> getFavoritos() {
        return favoritos;
    }

    public void setFavoritos(List<Serie> favoritos) {
        this.favoritos = favoritos != null ? favoritos : new ArrayList<>();
    }

    public List<Serie> getAssistidas() {
        return assistidas;
    }

    public void setAssistidas(List<Serie> assistidas) {
        this.assistidas = assistidas != null ? assistidas : new ArrayList<>();
    }

    public List<Serie> getDesejoAssistir() {
        return desejoAssistir;
    }

    public void setDesejoAssistir(List<Serie> desejoAssistir) {
        this.desejoAssistir = desejoAssistir != null ? desejoAssistir : new ArrayList<>();
    }

    public boolean adicionarFavorito(Serie serie) {
        if (!favoritos.contains(serie)) {
            favoritos.add(serie);
            return true;
        }

        return false;
    }

    public boolean removerFavorito(Serie serie) {
        return favoritos.remove(serie);
    }

    public boolean adicionarAssistida(Serie serie) {
        if (!assistidas.contains(serie)) {
            assistidas.add(serie);
            return true;
        }

        return false;
    }

    public boolean removerAssistida(Serie serie) {
        return assistidas.remove(serie);
    }

    public boolean adicionarDesejoAssistir(Serie serie) {
        if (!desejoAssistir.contains(serie)) {
            desejoAssistir.add(serie);
            return true;
        }

        return false;
    }

    public boolean removerDesejoAssistir(Serie serie) {
        return desejoAssistir.remove(serie);
    }
}