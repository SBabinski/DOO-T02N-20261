package com.example.model;

import java.util.ArrayList;
import java.util.List;

public class Usuario {

    private String nome;

    private List<Serie> favoritos = new ArrayList<>();

    private List<Serie> assistidas = new ArrayList<>();

    private List<Serie> desejoAssistir = new ArrayList<>();

    public Usuario() {
    }

    public Usuario(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Serie> getFavoritos() {
        return favoritos;
    }

    public void setFavoritos(List<Serie> favoritos) {
        this.favoritos = favoritos;
    }

    public List<Serie> getAssistidas() {
        return assistidas;
    }

    public void setAssistidas(List<Serie> assistidas) {
        this.assistidas = assistidas;
    }

    public List<Serie> getDesejoAssistir() {
        return desejoAssistir;
    }

    public void setDesejoAssistir(List<Serie> desejoAssistir) {
        this.desejoAssistir = desejoAssistir;
    }

        public void adicionarFavorito(Serie serie) {
            if (!favoritos.contains(serie)) {
                favoritos.add(serie);
            }
        }

        public void removerFavorito(Serie serie) {
            favoritos.remove(serie);
        }

        public void adicionarAssistida(Serie serie) {
            if (!assistidas.contains(serie)) {
                assistidas.add(serie);
            }
        }

        public void removerAssistida(Serie serie) {
            assistidas.remove(serie);
        }

        public void adicionarDesejoAssistir(Serie serie) {
            if (!desejoAssistir.contains(serie)) {
                desejoAssistir.add(serie);
            }
        }

        public void removerDesejoAssistir(Serie serie) {
            desejoAssistir.remove(serie);
        }
}