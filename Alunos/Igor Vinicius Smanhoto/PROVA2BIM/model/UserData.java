package com.tvtracker.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Representa os dados persistentes do usuário local:
 * nome, e suas três listas de séries.
 */
public class UserData {

    // Nome ou apelido do usuário local
    private String userName;

    // Lista de séries marcadas como favoritas
    private List<Series> favorites;

    // Lista de séries já assistidas pelo usuário
    private List<Series> watched;

    // Lista de séries que o usuário deseja assistir futuramente
    private List<Series> wantToWatch;

    // Construtor inicializa as listas vazias para evitar NullPointerException
    public UserData() {
        this.userName = "";
        this.favorites = new ArrayList<>();
        this.watched = new ArrayList<>();
        this.wantToWatch = new ArrayList<>();
    }

    public UserData(String userName) {
        this();
        this.userName = userName;
    }

    // Verifica se a série já está em uma lista pelo ID
    public boolean isInFavorites(Series series) {
        return favorites.stream().anyMatch(s -> s.getId() == series.getId());
    }

    public boolean isInWatched(Series series) {
        return watched.stream().anyMatch(s -> s.getId() == series.getId());
    }

    public boolean isInWantToWatch(Series series) {
        return wantToWatch.stream().anyMatch(s -> s.getId() == series.getId());
    }

    // Adiciona à lista somente se ainda não estiver presente
    public boolean addToFavorites(Series series) {
        if (!isInFavorites(series)) {
            favorites.add(series);
            return true;
        }
        return false;
    }

    public boolean addToWatched(Series series) {
        if (!isInWatched(series)) {
            watched.add(series);
            return true;
        }
        return false;
    }

    public boolean addToWantToWatch(Series series) {
        if (!isInWantToWatch(series)) {
            wantToWatch.add(series);
            return true;
        }
        return false;
    }

    // Remove da lista pelo ID da série
    public void removeFromFavorites(Series series) {
        favorites.removeIf(s -> s.getId() == series.getId());
    }

    public void removeFromWatched(Series series) {
        watched.removeIf(s -> s.getId() == series.getId());
    }

    public void removeFromWantToWatch(Series series) {
        wantToWatch.removeIf(s -> s.getId() == series.getId());
    }

    // Getters e setters
    public String getUserName() { return userName; }
    public void setUserName(String userName) { this.userName = userName; }

    public List<Series> getFavorites() { return favorites; }
    public void setFavorites(List<Series> favorites) { this.favorites = favorites; }

    public List<Series> getWatched() { return watched; }
    public void setWatched(List<Series> watched) { this.watched = watched; }

    public List<Series> getWantToWatch() { return wantToWatch; }
    public void setWantToWatch(List<Series> wantToWatch) { this.wantToWatch = wantToWatch; }
}
