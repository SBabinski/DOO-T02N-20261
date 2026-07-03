import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class ApiSeries {

    public Serie buscarSerie(String nome) {

        try {

            String nomeCodificado =
                    URLEncoder.encode(nome, StandardCharsets.UTF_8);

            String url =
                    "https://api.tvmaze.com/search/shows?q=" + nomeCodificado;

            HttpClient client = HttpClient.newHttpClient();

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .build();

            HttpResponse<String> response =
                    client.send(request, HttpResponse.BodyHandlers.ofString());

            String json = response.body();

            if (json == null || json.length() < 10 || !json.contains("\"show\"")) {
                return new Serie("Não encontrada", "-", 0, "Sem dados",
                        new ArrayList<>(), "-", "-", "-", null);
            }

            String blocoShow;

            try {
                blocoShow = json.split("\"show\":\\{")[1];
            } catch (Exception e) {
                return new Serie("Não encontrada", "-", 0, "Sem dados",
                        new ArrayList<>(), "-", "-", "-", null);
            }

            String nomeSerie = extrair(blocoShow, "\"name\":\"");
            String idioma = extrair(blocoShow, "\"language\":\"");
            String status = extrair(blocoShow, "\"status\":\"");
            String estreia = extrair(blocoShow, "\"premiered\":\"");
            String termino = extrairOpcional(blocoShow, "\"ended\":\"");

            double nota = extrairNota(blocoShow);
            List<String> generos = extrairGeneros(blocoShow);
            String emissora = extrairEmissora(blocoShow);
            String imagemUrl = extrairImagem(blocoShow);

            return new Serie(
                    nomeSerie,
                    idioma,
                    nota,
                    status,
                    generos,
                    estreia,
                    termino,
                    emissora,
                    imagemUrl
            );

        } catch (IOException | InterruptedException e) {

            return new Serie(
                    "Erro API",
                    "-",
                    0,
                    "Erro",
                    new ArrayList<>(),
                    "-",
                    "-",
                    "-",
                    null
            );
        }
    }

    private String extrairImagem(String texto) {
        try {
            if (texto.contains("\"image\"")) {
                return texto.split("\"image\":\\{")[1]
                        .split("\"original\":\"")[1]
                        .split("\"")[0];
            }
        } catch (Exception e) {
            return null;
        }
        return null;
    }

    private double extrairNota(String texto) {
        try {
            String notaStr = texto.split("\"average\":")[1]
                    .split("[,}]")[0]
                    .trim();

            if (notaStr.equals("null")) return 0;

            return Double.parseDouble(notaStr);

        } catch (Exception e) {
            return 0;
        }
    }

    private List<String> extrairGeneros(String texto) {

        List<String> generos = new ArrayList<>();

        try {
            String genresPart = texto.split("\"genres\":\\[")[1]
                    .split("]")[0];

            String[] gs = genresPart.replace("\"", "").split(",");

            for (String g : gs) {
                if (!g.trim().isEmpty()) {
                    generos.add(g.trim());
                }
            }

        } catch (Exception e) {
            return new ArrayList<>();
        }

        return generos;
    }

    private String extrairEmissora(String texto) {

        try {
            if (texto.contains("\"network\"")) {
                return texto.split("\"network\":\\{")[1]
                        .split("\"name\":\"")[1]
                        .split("\"")[0];
            }

            if (texto.contains("\"webChannel\"")) {
                return texto.split("\"webChannel\":\\{")[1]
                        .split("\"name\":\"")[1]
                        .split("\"")[0];
            }

        } catch (Exception e) {
            return "N/A";
        }

        return "N/A";
    }

    private String extrair(String texto, String chave) {
        try {
            return texto.split(chave)[1].split("\"")[0];
        } catch (Exception e) {
            return "-";
        }
    }

    private String extrairOpcional(String texto, String chave) {
        try {
            return texto.split(chave)[1].split("\"")[0];
        } catch (Exception e) {
            return "-";
        }
    }
}