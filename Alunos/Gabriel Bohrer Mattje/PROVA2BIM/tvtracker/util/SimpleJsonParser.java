package com.tvtracker.util;

import java.util.ArrayList;
import java.util.List;

/**
 * Parser JSON simples para processar respostas da API TvMaze.
 * Implementado sem dependências externas.
 */
public class SimpleJsonParser {

    private final String json;
    private int pos;

    public SimpleJsonParser(String json) {
        this.json = json.trim();
        this.pos = 0;
    }

    /**
     * Extrai o valor de uma chave simples (string) de um objeto JSON.
     */
    public static String extractString(String json, String key) {
        if (json == null || key == null) return null;
        try {
            String searchKey = "\"" + key + "\"";
            int keyIndex = json.indexOf(searchKey);
            while (keyIndex != -1) {
                int colonIndex = json.indexOf(":", keyIndex + searchKey.length());
                if (colonIndex == -1) break;

                int valueStart = colonIndex + 1;
                while (valueStart < json.length() && Character.isWhitespace(json.charAt(valueStart))) {
                    valueStart++;
                }

                if (valueStart >= json.length()) break;

                char firstChar = json.charAt(valueStart);
                if (firstChar == '"') {
                    // Valor string
                    StringBuilder sb = new StringBuilder();
                    int i = valueStart + 1;
                    while (i < json.length()) {
                        char c = json.charAt(i);
                        if (c == '\\' && i + 1 < json.length()) {
                            char next = json.charAt(i + 1);
                            switch (next) {
                                case '"' -> { sb.append('"'); i += 2; }
                                case '\\' -> { sb.append('\\'); i += 2; }
                                case 'n' -> { sb.append('\n'); i += 2; }
                                case 'r' -> { sb.append('\r'); i += 2; }
                                case 't' -> { sb.append('\t'); i += 2; }
                                default -> { sb.append(c); i++; }
                            }
                        } else if (c == '"') {
                            break;
                        } else {
                            sb.append(c);
                            i++;
                        }
                    }
                    return sb.toString();
                } else if (firstChar == 'n') {
                    // null
                    return null;
                }
                keyIndex = json.indexOf(searchKey, keyIndex + 1);
            }
        } catch (Exception e) {
            // Ignora erros de parsing
        }
        return null;
    }

    /**
     * Extrai o valor numérico de uma chave de um objeto JSON.
     */
    public static Double extractDouble(String json, String key) {
        if (json == null || key == null) return null;
        try {
            String searchKey = "\"" + key + "\"";
            int keyIndex = json.indexOf(searchKey);
            if (keyIndex == -1) return null;

            int colonIndex = json.indexOf(":", keyIndex + searchKey.length());
            if (colonIndex == -1) return null;

            int valueStart = colonIndex + 1;
            while (valueStart < json.length() && Character.isWhitespace(json.charAt(valueStart))) {
                valueStart++;
            }

            if (valueStart >= json.length()) return null;
            char firstChar = json.charAt(valueStart);
            if (firstChar == 'n') return null; // null

            int valueEnd = valueStart;
            while (valueEnd < json.length() && "0123456789.-".indexOf(json.charAt(valueEnd)) >= 0) {
                valueEnd++;
            }

            if (valueEnd > valueStart) {
                return Double.parseDouble(json.substring(valueStart, valueEnd));
            }
        } catch (Exception e) {
            // Ignora
        }
        return null;
    }

    /**
     * Extrai o valor inteiro de uma chave de um objeto JSON.
     */
    public static Integer extractInt(String json, String key) {
        Double d = extractDouble(json, key);
        return d != null ? d.intValue() : null;
    }

    /**
     * Extrai um array de strings de uma chave JSON.
     */
    public static List<String> extractStringArray(String json, String key) {
        List<String> result = new ArrayList<>();
        if (json == null || key == null) return result;
        try {
            String searchKey = "\"" + key + "\"";
            int keyIndex = json.indexOf(searchKey);
            if (keyIndex == -1) return result;

            int colonIndex = json.indexOf(":", keyIndex + searchKey.length());
            if (colonIndex == -1) return result;

            int arrayStart = json.indexOf("[", colonIndex);
            if (arrayStart == -1) return result;

            int arrayEnd = json.indexOf("]", arrayStart);
            if (arrayEnd == -1) return result;

            String arrayContent = json.substring(arrayStart + 1, arrayEnd);
            String[] parts = arrayContent.split(",");
            for (String part : parts) {
                String trimmed = part.trim();
                if (trimmed.startsWith("\"") && trimmed.endsWith("\"")) {
                    result.add(trimmed.substring(1, trimmed.length() - 1));
                }
            }
        } catch (Exception e) {
            // Ignora
        }
        return result;
    }

    /**
     * Extrai um sub-objeto JSON como string.
     */
    public static String extractObject(String json, String key) {
        if (json == null || key == null) return null;
        try {
            String searchKey = "\"" + key + "\"";
            int keyIndex = json.indexOf(searchKey);
            if (keyIndex == -1) return null;

            int colonIndex = json.indexOf(":", keyIndex + searchKey.length());
            if (colonIndex == -1) return null;

            int valueStart = colonIndex + 1;
            while (valueStart < json.length() && Character.isWhitespace(json.charAt(valueStart))) {
                valueStart++;
            }

            if (valueStart >= json.length() || json.charAt(valueStart) != '{') return null;

            int depth = 0;
            int i = valueStart;
            while (i < json.length()) {
                char c = json.charAt(i);
                if (c == '{') depth++;
                else if (c == '}') {
                    depth--;
                    if (depth == 0) return json.substring(valueStart, i + 1);
                }
                i++;
            }
        } catch (Exception e) {
            // Ignora
        }
        return null;
    }

    /**
     * Divide um array JSON em elementos (objetos ou strings).
     */
    public static List<String> splitJsonArray(String jsonArray) {
        List<String> items = new ArrayList<>();
        if (jsonArray == null) return items;
        
        String trimmed = jsonArray.trim();
        if (!trimmed.startsWith("[")) return items;
        
        int start = 1;
        int depth = 0;
        boolean inString = false;
        StringBuilder current = new StringBuilder();

        for (int i = start; i < trimmed.length() - 1; i++) {
            char c = trimmed.charAt(i);

            if (c == '\\' && inString) {
                current.append(c);
                if (i + 1 < trimmed.length()) {
                    current.append(trimmed.charAt(i + 1));
                    i++;
                }
                continue;
            }

            if (c == '"') {
                inString = !inString;
                current.append(c);
            } else if (!inString && (c == '{' || c == '[')) {
                depth++;
                current.append(c);
            } else if (!inString && (c == '}' || c == ']')) {
                depth--;
                current.append(c);
            } else if (!inString && c == ',' && depth == 0) {
                String item = current.toString().trim();
                if (!item.isEmpty()) items.add(item);
                current = new StringBuilder();
            } else {
                current.append(c);
            }
        }

        String lastItem = current.toString().trim();
        if (!lastItem.isEmpty()) items.add(lastItem);

        return items;
    }

    /**
     * Remove tags HTML de uma string.
     */
    public static String stripHtml(String html) {
        if (html == null) return "";
        return html.replaceAll("<[^>]+>", "").trim();
    }
}
