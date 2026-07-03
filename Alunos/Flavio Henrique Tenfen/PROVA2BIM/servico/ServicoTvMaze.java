package servico;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import modelo.Serie;
import util.ParserJson;


public class ServicoTvMaze {

    private static final String URL_BASE   = "https://api.tvmaze.com";
    private static final int    TIMEOUT_MS = 8000;

 
    public List<Serie> buscarSeries(String consulta) throws Exception {
        String codificado = URLEncoder.encode(consulta, StandardCharsets.UTF_8);
        String json = fazerGet(URL_BASE + "/search/shows?q=" + codificado);
        return ParserJson.parseResultadoBusca(json);
    }

 
    public Serie buscarPorId(int idSerie) throws Exception {
        String json = fazerGet(URL_BASE + "/shows/" + idSerie);
        return ParserJson.parseSerie(json);
    }

    private String fazerGet(String endereco) throws Exception {
        URL url = new URL(endereco);
        HttpURLConnection conexao = (HttpURLConnection) url.openConnection();
        conexao.setRequestMethod("GET");
        conexao.setConnectTimeout(TIMEOUT_MS);
        conexao.setReadTimeout(TIMEOUT_MS);
        conexao.setRequestProperty("Accept", "application/json");

        int status = conexao.getResponseCode();
        if (status != 200) {
            throw new Exception("Erro HTTP " + status + " ao acessar: " + endereco);
        }

        try (BufferedReader leitor = new BufferedReader(
                new InputStreamReader(conexao.getInputStream(), StandardCharsets.UTF_8))) {
            StringBuilder sb = new StringBuilder();
            String linha;
            while ((linha = leitor.readLine()) != null) sb.append(linha);
            return sb.toString();
        } finally {
            conexao.disconnect();
        }
    }
}
