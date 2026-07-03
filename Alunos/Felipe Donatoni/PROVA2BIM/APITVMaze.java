import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class APITVMaze {

    private static final String URL_BUSCA = "https://api.tvmaze.com/search/shows?q=";

    public List<Serie> buscar(String nome) throws Exception {
        String url = URL_BUSCA + URLEncoder.encode(nome.trim(), "UTF-8");
        String json = fazerRequisicao(url);

        List<Serie> resultados = new ArrayList<>();
        String[] itens = json.split("\"show\":\\{");

        for (int i = 1; i < itens.length; i++) {
            Serie serie = parsearSerie(itens[i]);
            if (serie != null) resultados.add(serie);
        }

        return resultados;
    }

    private Serie parsearSerie(String json) {
        try {
            int    id       = lerInt(json, "\"id\":");
            String nome     = lerString(json, "\"name\":\"");
            String idioma   = lerString(json, "\"language\":\"");
            String estado   = lerString(json, "\"status\":\"");
            double nota     = lerDouble(json, "\"average\":");
            String emissora = lerEmissora(json);
            String resumo   = lerResumo(json);

            List<String> generos  = lerGeneros(json);
            LocalDate dataEstreia = lerData(json, "\"premiered\":\"");
            LocalDate dataTermino = lerData(json, "\"ended\":\"");

            if (id == 0 || nome.isEmpty()) return null;

            return new Serie(id, nome, idioma, generos, nota, estado, dataEstreia, dataTermino, emissora, resumo);
        } catch (Exception e) {
            return null;
        }
    }

    private int lerInt(String json, String chave) {
        Pattern p = Pattern.compile(Pattern.quote(chave) + "(\\d+)");
        Matcher m = p.matcher(json);
        return m.find() ? Integer.parseInt(m.group(1)) : 0;
    }

    private String lerString(String json, String chave) {
        int inicio = json.indexOf(chave);
        if (inicio == -1) return "";
        inicio += chave.length();
        int fim = json.indexOf("\"", inicio);
        if (fim == -1) return "";
        return json.substring(inicio, fim);
    }

    private double lerDouble(String json, String chave) {
        Pattern p = Pattern.compile(Pattern.quote(chave) + "([\\d.]+)");
        Matcher m = p.matcher(json);
        if (m.find()) {
            try { return Double.parseDouble(m.group(1)); } catch (Exception e) {}
        }
        return 0;
    }

    private List<String> lerGeneros(String json) {
        List<String> lista = new ArrayList<>();
        Pattern bloco = Pattern.compile("\"genres\":\\[([^\\]]*)\\]");
        Matcher mb = bloco.matcher(json);
        if (mb.find()) {
            Matcher mg = Pattern.compile("\"([^\"]+)\"").matcher(mb.group(1));
            while (mg.find()) lista.add(mg.group(1));
        }
        if (lista.isEmpty()) lista.add("N/A");
        return lista;
    }

    private String lerEmissora(String json) {
        Pattern p = Pattern.compile("\"network\":\\{[^}]*\"name\":\"([^\"]*)\"");
        Matcher m = p.matcher(json);
        return m.find() ? m.group(1) : "N/A";
    }

    private String lerResumo(String json) {
        int inicio = json.indexOf("\"summary\":\"");
        if (inicio == -1) return "";
        inicio += "\"summary\":\"".length();
        int fim = json.indexOf("\",", inicio);
        if (fim == -1) return "";
        return json.substring(inicio, fim)
                   .replaceAll("<[^>]*>", "")
                   .replace("\\n", " ")
                   .replace("\\\"", "\"");
    }

    private LocalDate lerData(String json, String chave) {
        String valor = lerString(json, chave);
        if (valor.isEmpty() || valor.length() < 10) return null;
        try {
            return LocalDate.parse(valor.substring(0, 10));
        } catch (Exception e) {
            return null;
        }
    }

    private String fazerRequisicao(String endereco) throws Exception {
        try {
            HttpURLConnection conn = (HttpURLConnection) new URL(endereco).openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(10000);
            conn.setReadTimeout(10000);
            conn.setRequestProperty("User-Agent", "Mozilla/5.0");

            if (conn.getResponseCode() != 200) {
                throw new Exception("Código HTTP: " + conn.getResponseCode());
            }

            BufferedReader reader = new BufferedReader(
                new InputStreamReader(conn.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String linha;
            while ((linha = reader.readLine()) != null) sb.append(linha);
            reader.close();
            conn.disconnect();

            return sb.toString();

        } catch (java.net.UnknownHostException e) {
            throw new Exception("Sem conexão com a internet.");
        } catch (java.net.SocketTimeoutException e) {
            throw new Exception("A conexão expirou. Tente novamente.");
        }
    }
}
