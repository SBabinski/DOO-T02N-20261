package com.pedrohhm.model;

import java.util.List;

import com.pedrohhm.exception.DuplicateSeriesException;
import com.pedrohhm.service.PersistenceService;

public class TvSeriesLists {

    private final UserData userData;

    // CONSTRUCTOR \\

    public TvSeriesLists(UserData userData) {

        this.userData = userData;
    }

    // FAVORITES \\

    public void addFavorite(TvSeries series) {

        addSeries(userData.getFavoritesList(), series);

        PersistenceService.saveUserData(userData);
    }

    public void removeFavorite(TvSeries series) {

        removeSeries(userData.getFavoritesList(), series);
        PersistenceService.saveUserData(userData);
    }

    public List<TvSeries> getFavorites() {

        return userData.getFavoritesList();
    }

    // WATCHED \\

    public void addWatched(TvSeries series) {

        addSeries(userData.getWatchedList(), series);
        PersistenceService.saveUserData(userData);
    }

    public void removeWatched(TvSeries series) {

        removeSeries(userData.getWatchedList(), series);
        PersistenceService.saveUserData(userData);
    }

    public List<TvSeries> getWatched() {

        return userData.getWatchedList();
    }

    // TO WATCH \\

    public void addToWatch(TvSeries series) {

        addSeries(userData.getToWatchList(), series);
        PersistenceService.saveUserData(userData);
    }

    public void removeToWatch(TvSeries series) {

        removeSeries(userData.getToWatchList(), series);
        PersistenceService.saveUserData(userData);
    }

    public List<TvSeries> getToWatch() {

        return userData.getToWatchList();
    }
    
    // MÉTODOS PRIVADOS \\

    private void addSeries(List<TvSeries> list, TvSeries series) {

        if (list.contains(series)) {
            throw new DuplicateSeriesException(
                "Essa série já pertence a esta lista."
            );
        }

        list.add(series);
    }

    private void removeSeries(List<TvSeries> list, TvSeries series) {

        list.remove(series);
    }
}