package tvtracker.model;

import java.util.ArrayList;
import java.util.List;

public class UserData {
    private UserProfile profile;
    private List<TvShow> favorites;       // favoritos
    private List<TvShow> watched;         // já assistidas
    private List<TvShow> watchlist;       // quer assistir

    public UserData() {
        this.profile   = new UserProfile();
        this.favorites = new ArrayList<>();
        this.watched   = new ArrayList<>();
        this.watchlist = new ArrayList<>();
    }

    // Getters e Setters
    public UserProfile getProfile()     { return profile; }
    public List<TvShow> getFavorites()  { return favorites; }
    public List<TvShow> getWatched()    { return watched; }
    public List<TvShow> getWatchlist()  { return watchlist; }

    public void setProfile(UserProfile p)    { this.profile = p; }
    public void setFavorites(List<TvShow> l) { this.favorites = l; }
    public void setWatched(List<TvShow> l)   { this.watched = l; }
    public void setWatchlist(List<TvShow> l) { this.watchlist = l; }

    public boolean addFavorite(TvShow show) {
        if (!favorites.contains(show)) {
            favorites.add(show);
            return true;
        }
        return false;
    }
    public boolean removeFavorite(TvShow show) {
        return favorites.remove(show);
    }
    public boolean isFavorite(TvShow show) {
        return favorites.contains(show);
    }

    public boolean addWatched(TvShow show) {
        if (!watched.contains(show)) {
            watched.add(show);
            return true;
        }
        return false;
    }
    public boolean removeWatched(TvShow show) {
        return watched.remove(show);
    }
    public boolean isWatched(TvShow show) {
        return watched.contains(show);
    }

    public boolean addToWatchlist(TvShow show) {
        if (!watchlist.contains(show)) {
            watchlist.add(show);
            return true;
        }
        return false;
    }
    public boolean removeFromWatchlist(TvShow show) {
        return watchlist.remove(show);
    }
    public boolean isInWatchlist(TvShow show) {
        return watchlist.contains(show);
    }
}
