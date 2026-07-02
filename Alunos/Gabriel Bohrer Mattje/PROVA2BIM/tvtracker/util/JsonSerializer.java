package com.tvtracker.util;

import com.tvtracker.model.SeriesStatus;
import com.tvtracker.model.TvSeries;
import com.tvtracker.model.UserProfile;

import java.util.ArrayList;
import java.util.List;

/**
 * Serializa e desserializa os dados do usuário em formato JSON.
 * Implementado sem dependências externas.
 */
public class JsonSerializer {

    /**
     * Serializa o perfil do usuário em JSON.
     */
    public static String serializeUserProfile(UserProfile profile) {
        if (profile == null) return "{}";
        StringBuilder sb = new StringBuilder();
        sb.append("{\n");
        sb.append("  \"name\": ").append(escapeString(profile.getName())).append(",\n");
        sb.append("  \"nickname\": ").append(escapeString(profile.getNickname())).append(",\n");
        sb.append("  \"favorites\": ").append(serializeSeriesList(profile.getFavorites())).append(",\n");
        sb.append("  \"watched\": ").append(serializeSeriesList(profile.getWatched())).append(",\n");
        sb.append("  \"watchLater\": ").append(serializeSeriesList(profile.getWatchLater())).append("\n");
        sb.append("}");
        return sb.toString();
    }

    /**
     * Desserializa o perfil do usuário a partir de JSON.
     */
    public static UserProfile deserializeUserProfile(String json) {
        if (json == null || json.isBlank()) return new UserProfile();
        UserProfile profile = new UserProfile();
        try {
            profile.setName(SimpleJsonParser.extractString(json, "name"));
            profile.setNickname(SimpleJsonParser.extractString(json, "nickname"));

            String favJson = extractArrayContent(json, "favorites");
            profile.setFavorites(deserializeSeriesList(favJson));

            String watchedJson = extractArrayContent(json, "watched");
            profile.setWatched(deserializeSeriesList(watchedJson));

            String watchLaterJson = extractArrayContent(json, "watchLater");
            profile.setWatchLater(deserializeSeriesList(watchLaterJson));
        } catch (Exception e) {
            System.err.println("Erro ao desserializar perfil: " + e.getMessage());
        }
        return profile;
    }

    private static String serializeSeriesList(List<TvSeries> list) {
        if (list == null || list.isEmpty()) return "[]";
        StringBuilder sb = new StringBuilder("[\n");
        for (int i = 0; i < list.size(); i++) {
            sb.append(serializeSeries(list.get(i)));
            if (i < list.size() - 1) sb.append(",");
            sb.append("\n");
        }
        sb.append("  ]");
        return sb.toString();
    }

    private static String serializeSeries(TvSeries s) {
        if (s == null) return "{}";
        StringBuilder sb = new StringBuilder("    {\n");
        sb.append("      \"id\": ").append(s.getId()).append(",\n");
        sb.append("      \"name\": ").append(escapeString(s.getName())).append(",\n");
        sb.append("      \"language\": ").append(escapeString(s.getLanguage())).append(",\n");
        sb.append("      \"genres\": ").append(serializeStringList(s.getGenres())).append(",\n");
        sb.append("      \"rating\": ").append(s.getRating()).append(",\n");
        sb.append("      \"status\": ").append(escapeString(s.getStatus() != null ? s.getStatus().name() : null)).append(",\n");
        sb.append("      \"premiereDate\": ").append(escapeString(s.getPremiereDate())).append(",\n");
        sb.append("      \"endDate\": ").append(escapeString(s.getEndDate())).append(",\n");
        sb.append("      \"networkName\": ").append(escapeString(s.getNetworkName())).append(",\n");
        sb.append("      \"summary\": ").append(escapeString(s.getSummary())).append(",\n");
        sb.append("      \"imageUrl\": ").append(escapeString(s.getImageUrl())).append("\n");
        sb.append("    }");
        return sb.toString();
    }

    private static List<TvSeries> deserializeSeriesList(String arrayJson) {
        List<TvSeries> result = new ArrayList<>();
        if (arrayJson == null || arrayJson.isBlank()) return result;
        
        List<String> items = SimpleJsonParser.splitJsonArray("[" + arrayJson + "]");
        for (String item : items) {
            try {
                TvSeries s = deserializeSeries(item);
                if (s != null && s.getId() > 0) result.add(s);
            } catch (Exception e) {
                System.err.println("Erro ao desserializar série: " + e.getMessage());
            }
        }
        return result;
    }

    private static TvSeries deserializeSeries(String json) {
        if (json == null || json.isBlank()) return null;
        TvSeries s = new TvSeries();

        Integer id = SimpleJsonParser.extractInt(json, "id");
        s.setId(id != null ? id : 0);
        s.setName(SimpleJsonParser.extractString(json, "name"));
        s.setLanguage(SimpleJsonParser.extractString(json, "language"));
        s.setGenres(SimpleJsonParser.extractStringArray(json, "genres"));

        Double rating = SimpleJsonParser.extractDouble(json, "rating");
        s.setRating(rating != null ? rating : 0.0);

        String statusStr = SimpleJsonParser.extractString(json, "status");
        if (statusStr != null) {
            try {
                s.setStatus(SeriesStatus.valueOf(statusStr));
            } catch (IllegalArgumentException e) {
                s.setStatus(SeriesStatus.UNKNOWN);
            }
        }

        s.setPremiereDate(SimpleJsonParser.extractString(json, "premiereDate"));
        s.setEndDate(SimpleJsonParser.extractString(json, "endDate"));
        s.setNetworkName(SimpleJsonParser.extractString(json, "networkName"));
        s.setSummary(SimpleJsonParser.extractString(json, "summary"));
        s.setImageUrl(SimpleJsonParser.extractString(json, "imageUrl"));

        return s;
    }

    private static String serializeStringList(List<String> list) {
        if (list == null || list.isEmpty()) return "[]";
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < list.size(); i++) {
            sb.append(escapeString(list.get(i)));
            if (i < list.size() - 1) sb.append(", ");
        }
        sb.append("]");
        return sb.toString();
    }

    private static String escapeString(String value) {
        if (value == null) return "null";
        String escaped = value
            .replace("\\", "\\\\")
            .replace("\"", "\\\"")
            .replace("\n", "\\n")
            .replace("\r", "\\r")
            .replace("\t", "\\t");
        return "\"" + escaped + "\"";
    }

    /**
     * Extrai o conteúdo de um array JSON (sem os colchetes externos) por chave.
     */
    private static String extractArrayContent(String json, String key) {
        if (json == null || key == null) return null;
        try {
            String searchKey = "\"" + key + "\"";
            int keyIndex = json.indexOf(searchKey);
            if (keyIndex == -1) return null;

            int colonIndex = json.indexOf(":", keyIndex + searchKey.length());
            if (colonIndex == -1) return null;

            int arrayStart = json.indexOf("[", colonIndex);
            if (arrayStart == -1) return null;

            int depth = 0;
            int i = arrayStart;
            while (i < json.length()) {
                char c = json.charAt(i);
                if (c == '[') depth++;
                else if (c == ']') {
                    depth--;
                    if (depth == 0) return json.substring(arrayStart + 1, i);
                }
                i++;
            }
        } catch (Exception e) {
            System.err.println("Erro ao extrair array '" + key + "': " + e.getMessage());
        }
        return null;
    }
}
