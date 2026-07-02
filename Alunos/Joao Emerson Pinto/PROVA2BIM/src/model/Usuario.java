package model;

import java.util.ArrayList;
import java.util.List;

public class Usuario {
    private String nome;
    private List<Serie> favoritos;
    private List<Serie> assistidas;
    private List<Serie> desejoAssistir;

    public Usuario() {
        this("", new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
    }

    public Usuario(String nome) {
        this(nome, new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
    }

    public Usuario(String nome, List<Serie> favoritos, List<Serie> assistidas, List<Serie> desejoAssistir) {
        this.nome = nome;
        this.favoritos = favoritos == null ? new ArrayList<>() : favoritos;
        this.assistidas = assistidas == null ? new ArrayList<>() : assistidas;
        this.desejoAssistir = desejoAssistir == null ? new ArrayList<>() : desejoAssistir;
    }

    public String getNome() {
        return nome;
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
