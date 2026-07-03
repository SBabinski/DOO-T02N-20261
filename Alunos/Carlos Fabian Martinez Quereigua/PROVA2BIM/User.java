package model;

import java.util.ArrayList;
import java.util.List;

public class User {

    private String nome;
    private List<Serie> favoritos;
    private List<Serie> desejoAssistir;
    private List<Serie> assistidas;

    public User() {
        this.favoritos = new ArrayList<>();
        this.desejoAssistir = new ArrayList<>();
        this.assistidas = new ArrayList<>();
    }

    public User(String nome) {
        this.nome = nome;
        this.favoritos = new ArrayList<>();
        this.desejoAssistir = new ArrayList<>();
        this.assistidas = new ArrayList<>();
    }

    public void setAssistidas(List<Serie> assistidas) {
        this.assistidas = assistidas;
    }

    public void setDesejoAssistir(List<Serie> desejoAssistir) {
        this.desejoAssistir = desejoAssistir;
    }

    public void setFavoritos(List<Serie> favoritos) {
        this.favoritos = favoritos;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public List<Serie> getFavoritos() {
        return favoritos;
    }

    public List<Serie> getDesejoAssistir() {
        return desejoAssistir;
    }

    public List<Serie> getAssistidas() {
        return assistidas;
    }

    //adiciona/remove favoritos
    public void adicionaFavoritoALista(Serie serie){
        if (!favoritos.contains(serie)) {
            favoritos.add(serie);
        }
    }
    public void removeFavoritoDaLista(Serie serie){
        favoritos.remove(serie);
    }

    //adiciona/remove series assistidas
    public void adicionaSerieAssistidaALista(Serie serie){
        if (!assistidas.contains(serie)) {
            assistidas.add(serie);
        }
    }
    public void removeSerieAssistidaALista(Serie serie){
        assistidas.remove(serie);
    }

    //adiciona/remove series que deseja assistir
    public void adicionaSerieDesejadaALista(Serie serie){
        if (!desejoAssistir.contains(serie)) {
            desejoAssistir.add(serie);
        }
    }
    public void removeSerieDesejadaDaLista(Serie serie){
        desejoAssistir.remove(serie);
    }

    @Override
    public String toString() {
        return nome;
    }
}
