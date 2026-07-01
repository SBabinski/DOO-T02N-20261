package com.pedrohhm.controller;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import com.pedrohhm.exception.InvalidSearchException;
import com.pedrohhm.model.Rating;
import com.pedrohhm.model.TvSeries;
import com.pedrohhm.model.TvSeriesLists;
import com.pedrohhm.service.TvMazeApiService;

public class TvSeriesController {

    private final TvMazeApiService apiService;

    private final TvSeriesLists tvSeriesLists;

    private final UserController userController;

    private List<TvSeries> currentSearchResults;

    // CONSTRUCTOR \\

    public TvSeriesController(TvMazeApiService apiService,
        TvSeriesLists tvSeriesLists,
        UserController userController) {

        this.apiService = apiService;
        this.tvSeriesLists = tvSeriesLists;
        this.userController = userController;

        currentSearchResults = new ArrayList<>();
    }

    // SEARCH \\
    
    public List<TvSeries> searchTvSeries(String name){

        if (name == null) {
            throw new InvalidSearchException(
                "Digite o nome de uma série."
            );
        }
        
        name = name.trim();

        if (name.isEmpty()) {
            throw new InvalidSearchException(
                "Digite o nome de uma série."
            );
        }

        currentSearchResults = apiService.searchSeries(name);

        return currentSearchResults;
    }

    // SORTINGS \\

    private void sortList(List<TvSeries> list, Comparator<TvSeries> comparator) {

        list.sort(comparator);
    }

    public void sortByName(List<TvSeries> list) {

        sortList(
            list,
            Comparator.comparing(TvSeries::getName)
        );

        userController.save();
    }

    public void sortByRating(List<TvSeries> list) {

        sortList(
            list,
            Comparator.comparing(
                (TvSeries series) -> {
                    Rating rating = series.getRating();
                    return rating != null
                            ? rating.getAverage()
                            : null;
                },
                Comparator.nullsLast(Double::compareTo)
            ).reversed()
        );

        userController.save();
    }

    public void sortByStatus(List<TvSeries> list) {

        sortList(
            list,
            Comparator.comparing(TvSeries::getStatus)
        );

        userController.save();
    }

    public void sortByPremiered(List<TvSeries> list) {

        sortList(
            list,
            Comparator.comparing(
                TvSeries::getPremiered,
                Comparator.nullsLast(
                    java.time.LocalDate::compareTo
                )
            )
        );

        userController.save();
    }

    // ADD AND REMOVE FROM LISTS \\

    public void addFavorite(TvSeries series) {

        tvSeriesLists.addFavorite(series);
    }

    public void removeFavorite(TvSeries series) {

        tvSeriesLists.removeFavorite(series);
    }

    public void addToWatch(TvSeries series) {

        tvSeriesLists.addToWatch(series);
    }

    public void removeToWatch(TvSeries series) {

        tvSeriesLists.removeToWatch(series);
        userController.save();
    }

    public void addWatched(TvSeries series) {

        tvSeriesLists.addWatched(series);
    }

    public void removeWatched(TvSeries series) {

        tvSeriesLists.removeWatched(series);
    }

    // GETTERS \\

    public List<TvSeries> getCurrentSearchResults(){

        return currentSearchResults;
    }

    public List<TvSeries> getFavorites() {

        return tvSeriesLists.getFavorites();
    }

    public List<TvSeries> getToWatch() {

        return tvSeriesLists.getToWatch();
    }

    public List<TvSeries> getWatched() {

        return tvSeriesLists.getWatched();
    }
}
