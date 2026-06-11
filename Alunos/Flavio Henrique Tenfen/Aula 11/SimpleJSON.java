import java.util.*;

public class SimpleJSON {

    public static class JSONObject {
        private final Map<String, Object> map;

        public JSONObject() { map = new LinkedHashMap<>(); }

        public JSONObject(String json) {
            map = new LinkedHashMap<>();
            parse(json.trim());
        }

        private void parse(String s) {
            if (!s.startsWith("{")) return;
            s = s.substring(1, s.length() - 1).trim();
            int i = 0;
            while (i < s.length()) {
                i = skipWS(s, i);
                if (i >= s.length() || s.charAt(i) != '"') break;
                int keyEnd = findClosingQuote(s, i + 1);
                String key = s.substring(i + 1, keyEnd);
                i = keyEnd + 1;
                i = skipWS(s, i);
                if (i >= s.length() || s.charAt(i) != ':') break;
                i = skipWS(s, i + 1);
                Object[] vr = readValue(s, i);
                map.put(key, vr[0]);
                i = (int) vr[1];
                i = skipWS(s, i);
                if (i < s.length() && s.charAt(i) == ',') i++;
            }
        }

        public String optString(String k, String def) {
            Object v = map.get(k);
            return v == null ? def : v.toString();
        }

        public double optDouble(String k, double def) {
            Object v = map.get(k);
            if (v == null) return def;
            try { return Double.parseDouble(v.toString()); } catch (Exception e) { return def; }
        }

        public JSONObject optJSONObject(String k) {
            Object v = map.get(k);
            return v instanceof JSONObject ? (JSONObject) v : null;
        }

        public JSONArray optJSONArray(String k) {
            Object v = map.get(k);
            return v instanceof JSONArray ? (JSONArray) v : null;
        }
    }

    public static class JSONArray {
        private final List<Object> list;

        JSONArray() { list = new ArrayList<>(); }

        JSONArray(String s) {
            list = new ArrayList<>();
            parse(s.trim());
        }

        private void parse(String s) {
            if (!s.startsWith("[")) return;
            s = s.substring(1, s.length() - 1).trim();
            int i = 0;
            while (i < s.length()) {
                i = skipWS(s, i);
                if (i >= s.length()) break;
                Object[] vr = readValue(s, i);
                list.add(vr[0]);
                i = (int) vr[1];
                i = skipWS(s, i);
                if (i < s.length() && s.charAt(i) == ',') i++;
            }
        }

        public int length()      { return list.size(); }
        public boolean isEmpty() { return list.isEmpty(); }

        public JSONObject getJSONObject(int idx) {
            Object v = list.get(idx);
            return v instanceof JSONObject ? (JSONObject) v : new JSONObject();
        }
    }

    static int skipWS(String s, int i) {
        while (i < s.length() && Character.isWhitespace(s.charAt(i))) i++;
        return i;
    }

    static int findClosingQuote(String s, int i) {
        while (i < s.length()) {
            char c = s.charAt(i);
            if (c == '\\') { i += 2; continue; }
            if (c == '"')  return i;
            i++;
        }
        return i;
    }

    static Object[] readValue(String s, int i) {
        char c = s.charAt(i);

        if (c == '"') {
            int end = findClosingQuote(s, i + 1);
            String val = s.substring(i + 1, end)
                          .replace("\\\"", "\"")
                          .replace("\\n", "\n")
                          .replace("\\\\", "\\");
            return new Object[]{val, end + 1};
        }
        if (c == '{') {
            int end = findMatchingBrace(s, i, '{', '}');
            return new Object[]{new JSONObject(s.substring(i, end)), end};
        }
        if (c == '[') {
            int end = findMatchingBrace(s, i, '[', ']');
            return new Object[]{new JSONArray(s.substring(i, end)), end};
        }

        int j = i;
        while (j < s.length() && s.charAt(j) != ',' && s.charAt(j) != '}' && s.charAt(j) != ']') j++;
        String raw = s.substring(i, j).trim();
        if (raw.equals("null"))  return new Object[]{null,  j};
        if (raw.equals("true"))  return new Object[]{true,  j};
        if (raw.equals("false")) return new Object[]{false, j};
        try { return new Object[]{Double.parseDouble(raw), j}; } catch (Exception e) {}
        return new Object[]{raw, j};
    }

    static int findMatchingBrace(String s, int start, char open, char close) {
        int depth = 0; boolean inStr = false;
        for (int i = start; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '\\' && inStr) { i++; continue; }
            if (c == '"') { inStr = !inStr; continue; }
            if (inStr) continue;
            if (c == open)  depth++;
            if (c == close && --depth == 0) return i + 1;
        }
        return s.length();
    }
}
