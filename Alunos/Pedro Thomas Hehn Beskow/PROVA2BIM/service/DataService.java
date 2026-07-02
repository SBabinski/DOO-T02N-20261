package tvtracker.service;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.*;
import tvtracker.model.*;

public class DataService {

    private static final String APP_DIR  = System.getProperty("user.home") + File.separator + ".tvtracker";
    private static final String DATA_FILE = APP_DIR + File.separator + "data.json";

    public void save(UserData data) throws IOException {
        Files.createDirectories(Paths.get(APP_DIR));

        Map<String, Object> root = new LinkedHashMap<>();

        // Perfil
        Map<String, Object> profileMap = new LinkedHashMap<>();
        profileMap.put("name",     data.getProfile().getName());
        profileMap.put("nickname", data.getProfile().getNickname());
        root.put("profile", profileMap);

        // Listas
        root.put("favorites", showListToJson(data.getFavorites()));
        root.put("watched",   showListToJson(data.getWatched()));
        root.put("watchlist", showListToJson(data.getWatchlist()));

        String json = JsonParser.toJson(root);

        try (Writer writer = new OutputStreamWriter(
                new FileOutputStream(DATA_FILE), StandardCharsets.UTF_8)) {
            writer.write(json);
        }
    }

    public UserData load() {
        UserData data = new UserData();
        Path path = Paths.get(DATA_FILE);

        if (!Files.exists(path)) return data;

        try {
            String json = Files.readString(path, StandardCharsets.UTF_8);
            Map<String, Object> root = JsonParser.parseObject(json);

            // Perfil
            Map<String, Object> profileMap = JsonParser.getMap(root, "profile");
            if (profileMap != null) {
                String name     = JsonParser.getString(profileMap, "name");
                String nickname = JsonParser.getString(profileMap, "nickname");
                data.setProfile(new UserProfile(name, nickname));
            }

            // Listas
            data.setFavorites(jsonToShowList(JsonParser.getList(root, "favorites")));
            data.setWatched(  jsonToShowList(JsonParser.getList(root, "watched")));
            data.setWatchlist(jsonToShowList(JsonParser.getList(root, "watchlist")));

        } catch (Exception e) {
            System.err.println("Erro ao carregar dados: " + e.getMessage());
            // Retorna dados vazios em caso de erro
        }

        return data;
    }

    private List<Object> showListToJson(List<TvShow> shows) {
        List<Object> list = new ArrayList<>();
        for (TvShow s : shows) {
            list.add(showToMap(s));
        }
        return list;
    }

    private Map<String, Object> showToMap(TvShow s) {
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("id",        s.getId());
        map.put("name",      nvl(s.getName()));
        map.put("language",  nvl(s.getLanguage()));
        map.put("genres",    s.getGenres());
        map.put("rating",    s.getRating());
        map.put("status",    nvl(s.getStatus()));
        map.put("premiered", nvl(s.getPremiered()));
        map.put("ended",     nvl(s.getEnded()));
        map.put("network",   nvl(s.getNetwork()));
        map.put("summary",   nvl(s.getSummary()));
        map.put("imageUrl",  nvl(s.getImageUrl()));
        return map;
    }

    @SuppressWarnings("unchecked")
    private List<TvShow> jsonToShowList(List<Object> jsonList) {
        List<TvShow> shows = new ArrayList<>();
        if (jsonList == null) return shows;
        for (Object item : jsonList) {
            if (item instanceof Map<?, ?> rawMap) {
                Map<String, Object> map = (Map<String, Object>) rawMap;
                TvShow show = mapToShow(map);
                if (show != null) shows.add(show);
            }
        }
        return shows;
    }

    @SuppressWarnings("unchecked")
    private TvShow mapToShow(Map<String, Object> map) {
        if (map == null) return null;
        try {
            TvShow s = new TvShow();
            s.setId((int) JsonParser.getInt(map, "id"));
            s.setName(JsonParser.getString(map, "name"));
            s.setLanguage(JsonParser.getString(map, "language"));
            s.setRating(JsonParser.getDouble(map, "rating"));
            s.setStatus(JsonParser.getString(map, "status"));
            s.setPremiered(JsonParser.getString(map, "premiered"));
            s.setEnded(JsonParser.getString(map, "ended"));
            s.setNetwork(JsonParser.getString(map, "network"));
            s.setSummary(JsonParser.getString(map, "summary"));
            s.setImageUrl(JsonParser.getString(map, "imageUrl"));

            List<Object> genresList = JsonParser.getList(map, "genres");
            List<String> genres = new ArrayList<>();
            for (Object g : genresList) {
                if (g != null) genres.add(g.toString());
            }
            s.setGenres(genres);

            return s;
        } catch (Exception e) {
            System.err.println("Erro ao deserializar série: " + e.getMessage());
            return null;
        }
    }

    private String nvl(String s) { return s != null ? s : ""; }
}
