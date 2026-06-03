import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpExchange;
import java.io.*;
import java.net.*;
import java.nio.file.*;

public class WeatherServer {

    private static final String API_KEY = "WVTUXYWD34CWP6DFLC2XCNRQU";
    private static final int PORTA = 8080;

    public static void main(String[] args) throws Exception {
        HttpServer servidor = HttpServer.create(new InetSocketAddress(PORTA), 0);

        servidor.createContext("/api/clima", WeatherServer::buscarClima);
        
        servidor.createContext("/", WeatherServer::servirFrontend);

        servidor.start();
        System.out.println("Servidor rodando em http://localhost:" + PORTA);
    }

    private static void buscarClima(HttpExchange troca) throws IOException {
        troca.getResponseHeaders().add("Access-Control-Allow-Origin", "*");
        troca.getResponseHeaders().add("Content-Type", "application/json; charset=UTF-8");

        String query = troca.getRequestURI().getQuery();
        String cidade = "";

        if (query != null && query.startsWith("cidade=")) {
            cidade = URLDecoder.decode(query.split("=")[1], "UTF-8");
        }

        if (cidade.isEmpty()) {
            String erro = "{\"erro\": \"Cidade não informada\"}";
            troca.sendResponseHeaders(400, erro.getBytes().length);
            troca.getResponseBody().write(erro.getBytes());
            troca.getResponseBody().close();
            return;
        }

        try {
            String url = "https://weather.visualcrossing.com/VisualCrossingWebServices/rest/services/timeline/"
                    + URLEncoder.encode(cidade, "UTF-8")
                    + "/today?unitGroup=metric&key=" + API_KEY
                    + "&contentType=json&include=current";

            HttpURLConnection conexao = (HttpURLConnection) new URL(url).openConnection();
            conexao.setRequestMethod("GET");

            BufferedReader leitor = new BufferedReader(new InputStreamReader(conexao.getInputStream(), "UTF-8"));
            StringBuilder resposta = new StringBuilder();
            String linha;
            while ((linha = leitor.readLine()) != null) {
                resposta.append(linha);
            }
            leitor.close();

            String json = resposta.toString();
            String resultado = extrairDados(json, cidade);

            troca.sendResponseHeaders(200, resultado.getBytes("UTF-8").length);
            OutputStream os = troca.getResponseBody();
            os.write(resultado.getBytes("UTF-8"));
            os.close();

        } catch (Exception e) {
            String erro = "{\"erro\": \"Cidade não encontrada\"}";
            troca.sendResponseHeaders(404, erro.getBytes().length);
            troca.getResponseBody().write(erro.getBytes());
            troca.getResponseBody().close();
        }
    }

    private static String extrairDados(String json, String cidade) {
        return "{"
                + "\"cidade\": \"" + cidade + "\","
                + "\"temperaturaAtual\": " + extrairValor(json, "temp") + ","
                + "\"temperaturaMaxima\": " + extrairValor(json, "tempmax") + ","
                + "\"temperaturaMinima\": " + extrairValor(json, "tempmin") + ","
                + "\"umidade\": " + extrairValor(json, "humidity") + ","
                + "\"condicao\": \"" + extrairTexto(json, "conditions") + "\","
                + "\"precipitacao\": " + extrairValor(json, "precip") + ","
                + "\"velocidadeVento\": " + extrairValor(json, "windspeed") + ","
                + "\"direcaoVento\": \"" + converterDirecao(extrairValor(json, "winddir")) + "\""
                + "}";
    }

    private static String extrairValor(String json, String campo) {
        String chave = "\"" + campo + "\":";
        int inicio = json.indexOf(chave);
        if (inicio == -1) return "0";
        inicio += chave.length();
        int fim = json.indexOf(",", inicio);
        if (fim == -1) fim = json.indexOf("}", inicio);
        return json.substring(inicio, fim).trim();
    }

    private static String extrairTexto(String json, String campo) {
        String chave = "\"" + campo + "\":\"";
        int inicio = json.indexOf(chave);
        if (inicio == -1) return "Desconhecido";
        inicio += chave.length();
        int fim = json.indexOf("\"", inicio);
        return json.substring(inicio, fim).trim();
    }

    private static String converterDirecao(String grausStr) {
        try {
            double graus = Double.parseDouble(grausStr);
            if (graus >= 337.5 || graus < 22.5) return "Norte";
            if (graus < 67.5) return "Nordeste";
            if (graus < 112.5) return "Leste";
            if (graus < 157.5) return "Sudeste";
            if (graus < 202.5) return "Sul";
            if (graus < 247.5) return "Sudoeste";
            if (graus < 292.5) return "Oeste";
            return "Noroeste";
        } catch (Exception e) {
            return "Desconhecido";
        }
    }

    private static void servirFrontend(HttpExchange troca) throws IOException {
        String caminho = troca.getRequestURI().getPath();
        if (caminho.equals("/")) caminho = "/index.html";

        File arquivo = new File("frontend" + caminho);
        if (!arquivo.exists()) {
            troca.sendResponseHeaders(404, 0);
            troca.getResponseBody().close();
            return;
        }

        String tipo = caminho.endsWith(".css") ? "text/css"
                : caminho.endsWith(".js") ? "application/javascript"
                : "text/html; charset=UTF-8";

        troca.getResponseHeaders().add("Content-Type", tipo);
        byte[] conteudo = Files.readAllBytes(arquivo.toPath());
        troca.sendResponseHeaders(200, conteudo.length);
        troca.getResponseBody().write(conteudo);
        troca.getResponseBody().close();
    }
}