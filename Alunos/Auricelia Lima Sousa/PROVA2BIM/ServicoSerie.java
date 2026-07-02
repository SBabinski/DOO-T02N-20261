import java.net.URI;
import java.net.http.*;
import java.util.ArrayList;
import java.util.regex.*;

public class ServicoSerie {
    public String buscarDados(String nome) throws SerieException {
        try {
            String url = "https://api.tvmaze.com/search/shows?q=" + nome.replace(" ", "%20");
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest req = HttpRequest.newBuilder().uri(URI.create(url)).build();
            return client.send(req, HttpResponse.BodyHandlers.ofString()).body();
        } catch (Exception e) { 
            throw new SerieException("Erro de conexão."); 
        }
    }

    public ArrayList<Serie> extrairSeries(String json) {
    ArrayList<Serie> lista = new ArrayList<>();

    Matcher mShow = Pattern.compile("\"show\"\\s*:\\s*\\{(.*?)(?=\\}\\s*,\\s*\\{\\s*\"score\"|\\}\\s*\\])").matcher(json);
    while (mShow.find()) {
        String d = mShow.group(1);
        lista.add(new Serie(
            limpar(extrair(d, "name")), limpar(extrair(d, "language")),
            limpar(extrairLista(d, "genres")), limpar(extrairNota(d)),
            limpar(extrair(d, "status")), limpar(extrair(d, "premiered")),
            limpar(extrair(d, "ended")), limpar(extrairNet(d))
        ));
    }
    return lista;
}

    private String extrair(String j, String c) {
        Matcher m = Pattern.compile("\"" + c + "\":\\s*\"(.*?)\"").matcher(j);
        return m.find() ? m.group(1) : "N/A";
    }

    private String extrairLista(String j, String c) {
        Matcher m = Pattern.compile("\"" + c + "\":\\s*\\[(.*?)\\]").matcher(j);
        return m.find() ? m.group(1).replace("\"", "") : "N/A";
    }

    private String extrairNota(String j) {
        Matcher m = Pattern.compile("\"average\":\\s*([\\d.]+)").matcher(j);
        return m.find() ? m.group(1) : "N/A";
    }

    private String extrairNet(String j) {
        Matcher m = Pattern.compile("\"network\":\\s*\\{.*?\"name\":\\s*\"(.*?)\"").matcher(j);
        if (m.find()) return m.group(1);
        m = Pattern.compile("\"webChannel\":\\s*\\{.*?\"name\":\\s*\"(.*?)\"").matcher(j);
        return m.find() ? m.group(1) : "N/A";
    }

    private String limpar(String s) {
        return (s == null || s.equals("null") || s.isEmpty()) ? "N/A" : s.replace("\"", "").trim();
    }
}