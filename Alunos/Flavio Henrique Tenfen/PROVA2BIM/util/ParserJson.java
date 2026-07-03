package util;

import modelo.PerfilUsuario;
import modelo.Serie;

import java.util.ArrayList;
import java.util.List;

/**
 * Parser e serializador JSON manual — sem dependências externas.
 * Suporta as respostas da API TVMaze e o arquivo de perfil local.
 */
public class ParserJson {

    private ParserJson() {}

    // ══════════════════════════════════════════════════════════════════════
    //  TVMaze — busca (/search/shows)
    // ══════════════════════════════════════════════════════════════════════

    /**
     * Parseia a resposta de /search/shows?q=...
     * Formato: [ { "score": 1.0, "show": { ... } }, ... ]
     */
    public static List<Serie> parseResultadoBusca(String json) {
        List<Serie> series = new ArrayList<>();
        for (String entrada : dividirObjetos(json)) {
            String blocoShow = extrairObjeto(entrada, "show");
            if (blocoShow != null) series.add(parseSerie(blocoShow));
        }
        return series;
    }

    /** Parseia a resposta de /shows/:id */
    public static Serie parseSerie(String json) {
        Serie serie = new Serie();
        serie.setId(extrairInt(json, "id"));
        serie.setNome(extrairString(json, "name"));
        serie.setIdioma(extrairString(json, "language"));
        serie.setEstado(extrairString(json, "status"));
        serie.setDataEstreia(extrairString(json, "premiered"));
        serie.setDataTermino(extrairString(json, "ended"));
        serie.setSinopse(removerHtml(extrairString(json, "summary")));
        serie.setGeneros(extrairArrayStrings(json, "genres"));

        String blocoNota = extrairObjeto(json, "rating");
        if (blocoNota != null) serie.setNota(extrairDouble(blocoNota, "average"));

        String blocoRede = extrairObjeto(json, "network");
        if (blocoRede != null) serie.setEmissora(extrairString(blocoRede, "name"));

        return serie;
    }

    // ══════════════════════════════════════════════════════════════════════
    //  Perfil de usuário
    // ══════════════════════════════════════════════════════════════════════

    public static PerfilUsuario parsePerfil(String json) {
        PerfilUsuario perfil = new PerfilUsuario();
        perfil.setNome(extrairString(json, "nome"));
        perfil.setFavoritas(parseListaSeries(extrairBlocoArray(json, "favoritas")));
        perfil.setAssistidas(parseListaSeries(extrairBlocoArray(json, "assistidas")));
        perfil.setQueroAssistir(parseListaSeries(extrairBlocoArray(json, "queroAssistir")));
        return perfil;
    }

    public static String serializarPerfil(PerfilUsuario perfil) {
        return "{\n"
             + "  \"nome\": " + jsonStr(perfil.getNome()) + ",\n"
             + "  \"favoritas\": " + serializarLista(perfil.getFavoritas()) + ",\n"
             + "  \"assistidas\": " + serializarLista(perfil.getAssistidas()) + ",\n"
             + "  \"queroAssistir\": " + serializarLista(perfil.getQueroAssistir()) + "\n"
             + "}";
    }

    // ══════════════════════════════════════════════════════════════════════
    //  Helpers de parsing
    // ══════════════════════════════════════════════════════════════════════

    private static List<Serie> parseListaSeries(String arrayJson) {
        List<Serie> lista = new ArrayList<>();
        if (arrayJson == null || arrayJson.isBlank()) return lista;
        for (String obj : dividirObjetos(arrayJson)) lista.add(parseSerie(obj));
        return lista;
    }

    /** Divide um array JSON em objetos individuais: [ {...}, {...} ] → lista de strings */
    private static List<String> dividirObjetos(String json) {
        List<String> resultado = new ArrayList<>();
        json = json.trim();
        if (json.startsWith("[")) json = json.substring(1);
        if (json.endsWith("]"))   json = json.substring(0, json.length() - 1);

        int profundidade = 0, inicio = -1;
        boolean emString = false;
        for (int i = 0; i < json.length(); i++) {
            char c = json.charAt(i);
            if (c == '"' && (i == 0 || json.charAt(i - 1) != '\\')) emString = !emString;
            if (emString) continue;
            if (c == '{') { if (profundidade == 0) inicio = i; profundidade++; }
            else if (c == '}') {
                profundidade--;
                if (profundidade == 0 && inicio != -1) {
                    resultado.add(json.substring(inicio, i + 1));
                    inicio = -1;
                }
            }
        }
        return resultado;
    }

    private static String extrairString(String json, String chave) {
        String padrao = "\"" + chave + "\"";
        int idx = json.indexOf(padrao);
        if (idx == -1) return null;
        int doisPontos = json.indexOf(':', idx + padrao.length());
        if (doisPontos == -1) return null;
        int ini = json.indexOf('"', doisPontos + 1);
        if (ini == -1) return null;
        int fim = ini + 1;
        while (fim < json.length()) {
            if (json.charAt(fim) == '"' && json.charAt(fim - 1) != '\\') break;
            fim++;
        }
        return json.substring(ini + 1, fim)
                   .replace("\\\"", "\"").replace("\\n", "\n").replace("\\/", "/");
    }

    private static int extrairInt(String json, String chave) {
        String padrao = "\"" + chave + "\"";
        int idx = json.indexOf(padrao);
        if (idx == -1) return 0;
        int dp = json.indexOf(':', idx + padrao.length());
        if (dp == -1) return 0;
        int ini = dp + 1;
        while (ini < json.length() && json.charAt(ini) == ' ') ini++;
        int fim = ini;
        while (fim < json.length() && (Character.isDigit(json.charAt(fim)) || json.charAt(fim) == '-')) fim++;
        try { return Integer.parseInt(json.substring(ini, fim).trim()); } catch (NumberFormatException e) { return 0; }
    }

    private static double extrairDouble(String json, String chave) {
        String padrao = "\"" + chave + "\"";
        int idx = json.indexOf(padrao);
        if (idx == -1) return 0.0;
        int dp = json.indexOf(':', idx + padrao.length());
        if (dp == -1) return 0.0;
        int ini = dp + 1;
        while (ini < json.length() && json.charAt(ini) == ' ') ini++;
        if (json.startsWith("null", ini)) return 0.0;
        int fim = ini;
        while (fim < json.length() && (Character.isDigit(json.charAt(fim)) || json.charAt(fim) == '.' || json.charAt(fim) == '-')) fim++;
        try { return Double.parseDouble(json.substring(ini, fim).trim()); } catch (NumberFormatException e) { return 0.0; }
    }

    private static String extrairObjeto(String json, String chave) {
        String padrao = "\"" + chave + "\"";
        int idx = json.indexOf(padrao);
        if (idx == -1) return null;
        int dp = json.indexOf(':', idx + padrao.length());
        if (dp == -1) return null;
        int ini = dp + 1;
        while (ini < json.length() && json.charAt(ini) == ' ') ini++;
        if (ini >= json.length() || json.charAt(ini) != '{') return null;
        int prof = 0, fim = ini;
        boolean emStr = false;
        for (; fim < json.length(); fim++) {
            char c = json.charAt(fim);
            if (c == '"' && (fim == 0 || json.charAt(fim - 1) != '\\')) emStr = !emStr;
            if (emStr) continue;
            if (c == '{') prof++;
            else if (c == '}') { prof--; if (prof == 0) return json.substring(ini, fim + 1); }
        }
        return null;
    }

    private static String extrairBlocoArray(String json, String chave) {
        String padrao = "\"" + chave + "\"";
        int idx = json.indexOf(padrao);
        if (idx == -1) return null;
        int dp = json.indexOf(':', idx + padrao.length());
        if (dp == -1) return null;
        int ini = dp + 1;
        while (ini < json.length() && json.charAt(ini) == ' ') ini++;
        if (ini >= json.length() || json.charAt(ini) != '[') return null;
        int prof = 0, fim = ini;
        boolean emStr = false;
        for (; fim < json.length(); fim++) {
            char c = json.charAt(fim);
            if (c == '"' && (fim == 0 || json.charAt(fim - 1) != '\\')) emStr = !emStr;
            if (emStr) continue;
            if (c == '[') prof++;
            else if (c == ']') { prof--; if (prof == 0) return json.substring(ini + 1, fim); }
        }
        return null;
    }

    private static String[] extrairArrayStrings(String json, String chave) {
        String bloco = extrairBlocoArray(json, chave);
        if (bloco == null || bloco.isBlank()) return new String[0];
        List<String> itens = new ArrayList<>();
        int i = 0;
        while (i < bloco.length()) {
            int s = bloco.indexOf('"', i);
            if (s == -1) break;
            int e = s + 1;
            while (e < bloco.length()) { if (bloco.charAt(e) == '"' && bloco.charAt(e-1) != '\\') break; e++; }
            itens.add(bloco.substring(s + 1, e));
            i = e + 1;
        }
        return itens.toArray(new String[0]);
    }

    // ══════════════════════════════════════════════════════════════════════
    //  Helpers de serialização
    // ══════════════════════════════════════════════════════════════════════

    private static String serializarLista(List<Serie> series) {
        if (series == null || series.isEmpty()) return "[]";
        StringBuilder sb = new StringBuilder("[\n");
        for (int i = 0; i < series.size(); i++) {
            sb.append(serializarSerie(series.get(i)));
            if (i < series.size() - 1) sb.append(",");
            sb.append("\n");
        }
        return sb.append("  ]").toString();
    }

    private static String serializarSerie(Serie s) {
        return "    {\n"
             + "      \"id\": " + s.getId() + ",\n"
             + "      \"name\": " + jsonStr(s.getNome()) + ",\n"
             + "      \"language\": " + jsonStr(s.getIdioma()) + ",\n"
             + "      \"genres\": " + serializarArrayStrings(s.getGeneros()) + ",\n"
             + "      \"rating\": { \"average\": " + s.getNota() + " },\n"
             + "      \"status\": " + jsonStr(s.getEstado()) + ",\n"
             + "      \"premiered\": " + jsonStr(s.getDataEstreia()) + ",\n"
             + "      \"ended\": " + jsonStr(s.getDataTermino()) + ",\n"
             + "      \"network\": { \"name\": " + jsonStr(s.getEmissora()) + " },\n"
             + "      \"summary\": " + jsonStr(s.getSinopse()) + "\n"
             + "    }";
    }

    private static String serializarArrayStrings(String[] arr) {
        if (arr == null || arr.length == 0) return "[]";
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < arr.length; i++) {
            sb.append(jsonStr(arr[i]));
            if (i < arr.length - 1) sb.append(", ");
        }
        return sb.append("]").toString();
    }

    private static String jsonStr(String valor) {
        if (valor == null) return "null";
        return "\"" + valor.replace("\\", "\\\\").replace("\"", "\\\"")
                           .replace("\n", "\\n").replace("\r", "") + "\"";
    }

    public static String removerHtml(String html) {
        if (html == null) return "";
        return html.replaceAll("<[^>]*>", "").trim();
    }
}
