package app.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Usuario {

    private String nome;

    private List<Serie> favoritos = new ArrayList<>();
    private List<Serie> assistidas = new ArrayList<>();
    private List<Serie> desejaAssistir = new ArrayList<>();
    private boolean dadosIniciaisCriados;

    public Usuario() {}

    public Usuario(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome == null || nome.isBlank()
                ? "Usuário"
                : nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Serie> getFavoritos() {
        if (favoritos == null) {
            favoritos = new ArrayList<>();
        }
        return favoritos;
    }

    public void setFavoritos(List<Serie> favoritos) {
        this.favoritos = favoritos;
    }

    public List<Serie> getAssistidas() {
        if (assistidas == null) {
            assistidas = new ArrayList<>();
        }
        return assistidas;
    }

    public void setAssistidas(List<Serie> assistidas) {
        this.assistidas = assistidas;
    }

    public List<Serie> getDesejaAssistir() {
        if (desejaAssistir == null) {
            desejaAssistir = new ArrayList<>();
        }
        return desejaAssistir;
    }

    public void setDesejaAssistir(List<Serie> desejaAssistir) {
        this.desejaAssistir = desejaAssistir;
    }

    public boolean isDadosIniciaisCriados() {
        return dadosIniciaisCriados;
    }

    public void setDadosIniciaisCriados(boolean dadosIniciaisCriados) {
        this.dadosIniciaisCriados = dadosIniciaisCriados;
    }
}
