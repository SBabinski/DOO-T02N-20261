package com.pedrohhm.model;

import java.util.ArrayList;
import java.util.List;

public class UserData {

    private String username;
    private List<TvSeries> favoritesList = new ArrayList<>();
    private List<TvSeries> toWatchList = new ArrayList<>(); 
    private List<TvSeries> watchedList = new ArrayList<>();

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<TvSeries> getFavoritesList() {
        return favoritesList;
    }

    public void setFavoritesList(List<TvSeries> favoritesList) {
        this.favoritesList = favoritesList;
    }

    public List<TvSeries> getToWatchList() {
        return toWatchList;
    }           

    public void setToWatchList(List<TvSeries> toWatchList) {
        this.toWatchList = toWatchList;
    }

    public List<TvSeries> getWatchedList() {
        return watchedList;
    }

    public void setWatchedList(List<TvSeries> watchedList) {
        this.watchedList = watchedList;
    }

    public void addToFavorites(TvSeries tvSeries){
        favoritesList.add(tvSeries);
    }

    public void addToToWatchList(TvSeries tvSeries){
        toWatchList.add(tvSeries);
    }

    public void addToWatchedList(TvSeries tvSeries){
        watchedList.add(tvSeries);
    }
}
