package com.tvtracker.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Representa o perfil do usuário com suas listas de séries.
 */
public class UserProfile {

    private String name;
    private String nickname;
    private List<TvSeries> favorites;
    private List<TvSeries> watched;
    private List<TvSeries> watchLater;

    public UserProfile() {
        this.favorites = new ArrayList<>();
        this.watched = new ArrayList<>();
        this.watchLater = new ArrayList<>();
    }

    public UserProfile(String name, String nickname) {
        this();
        this.name = name;
        this.nickname = nickname;
    }

    // Getters e Setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getNickname() { return nickname; }
    public void setNickname(String nickname) { this.nickname = nickname; }

    public List<TvSeries> getFavorites() { return favorites; }
    public void setFavorites(List<TvSeries> favorites) { this.favorites = favorites; }

    public List<TvSeries> getWatched() { return watched; }
    public void setWatched(List<TvSeries> watched) { this.watched = watched; }

    public List<TvSeries> getWatchLater() { return watchLater; }
    public void setWatchLater(List<TvSeries> watchLater) { this.watchLater = watchLater; }

    public String getDisplayName() {
        if (nickname != null && !nickname.isBlank()) return nickname;
        if (name != null && !name.isBlank()) return name;
        return "Usuário";
    }

    // Métodos para gerenciar favoritos
    public boolean addToFavorites(TvSeries series) {
        if (!favorites.contains(series)) {
            favorites.add(series);
            return true;
        }
        return false;
    }

    public boolean removeFromFavorites(TvSeries series) {
        return favorites.remove(series);
    }

    public boolean isFavorite(TvSeries series) {
        return favorites.contains(series);
    }

    // Métodos para gerenciar assistidas
    public boolean addToWatched(TvSeries series) {
        if (!watched.contains(series)) {
            watched.add(series);
            return true;
        }
        return false;
    }

    public boolean removeFromWatched(TvSeries series) {
        return watched.remove(series);
    }

    public boolean isWatched(TvSeries series) {
        return watched.contains(series);
    }

    // Métodos para gerenciar lista "assistir depois"
    public boolean addToWatchLater(TvSeries series) {
        if (!watchLater.contains(series)) {
            watchLater.add(series);
            return true;
        }
        return false;
    }

    public boolean removeFromWatchLater(TvSeries series) {
        return watchLater.remove(series);
    }

    public boolean isInWatchLater(TvSeries series) {
        return watchLater.contains(series);
    }
}
