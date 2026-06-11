package app.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import app.model.Serie;

public class ApiService {

    public List<Serie> buscarSeries(String nomeSerie) {

        List<Serie> lista = new ArrayList<>();

        //verificação para caso inválido
        if (nomeSerie == null || nomeSerie.isBlank()) {
            return lista;
        }

        try {
            //encoder transforma texto para url (breaking bad -> breaking+bad)
            String termoBusca = URLEncoder.encode(
                    nomeSerie.trim(),
                    StandardCharsets.UTF_8
            );

            String endereco =
                    "https://api.tvmaze.com/search/shows?q=" + termoBusca;

            //transforma a string em url válido e abre a conexão - get para pegar informações e verifica se tem o conteúdo em pt
            HttpURLConnection conexao =
                    (HttpURLConnection) URI.create(endereco)
                            .toURL()
                            .openConnection();

            conexao.setRequestMethod("GET");
            conexao.setRequestProperty(
                    "Accept-Language",
                    "pt-BR,pt;q=0.9,en;q=0.8"
            );

            //getInputStream pega os bytes, stream reader transforma em caracteres, buffered reader le linha a linha
            BufferedReader leitor =
                    new BufferedReader(
                            new InputStreamReader(
                                    conexao.getInputStream(),
                                    StandardCharsets.UTF_8
                            )
                    );

            StringBuilder resposta = new StringBuilder();

            String linha;

            //while le linha a linha e acumula no string builder, .toString contem o JSON completo como uma string
            while ((linha = leitor.readLine()) != null) {
                resposta.append(linha);
            }

            leitor.close();

            ObjectMapper mapper = new ObjectMapper();

            //o mapper cria uma árvore de jsonNode
            JsonNode array = mapper.readTree(resposta.toString());

            for (JsonNode item : array) {

                JsonNode json = item.get("show");

                Integer id = lerInteiro(json, "id");
                String name = lerTexto(json, "name", "Sem nome");
                String language = lerTexto(json, "language", "Não informado");

                List<String> genres = new ArrayList<>();

                if (json.has("genres") && json.get("genres").isArray()) {
                    for (JsonNode genero : json.get("genres")) {
                        genres.add(genero.asText());
                    }
                }

                Double rating = 0.0;

                if (json.has("rating") &&
                        json.get("rating").has("average") &&
                        !json.get("rating").get("average").isNull()) {

                    rating = json.get("rating")
                            .get("average")
                            .asDouble();
                }

                String status = lerTexto(json, "status", "Não informado");
                String premiered = lerTexto(json, "premiered", "");
                String ended = lerTexto(json, "ended", "");

                String network = "Não informada";

                if (json.has("network") &&
                        !json.get("network").isNull() &&
                        json.get("network").has("name")) {

                    network = json.get("network")
                            .get("name")
                            .asText();
                }

                String summary = "Descrição não disponível em português.";

                if (json.has("summary") &&
                        !json.get("summary").isNull()) {

                    String textoApi = json.get("summary")
                            .asText()
                            .replaceAll("<[^>]*>", "")
                            .trim();

                    if (!textoApi.isBlank()) {
                        summary = textoApi;
                    }
                }

                Serie serie = new Serie(
                        id,
                        name,
                        language,
                        genres,
                        rating,
                        status,
                        premiered,
                        ended,
                        network,
                        summary
                );

                lista.add(serie);
            }

        } catch (Exception e) {

            System.out.println(
                    "Erro ao buscar séries: " + e.getMessage()
            );
        }

        return lista;
    }

    private String lerTexto(JsonNode json,
                            String campo,
                            String valorPadrao) {

        if (json.has(campo) && !json.get(campo).isNull()) {
            return json.get(campo).asText();
        }

        return valorPadrao;
    }

    private Integer lerInteiro(JsonNode json,
                               String campo) {

        if (json.has(campo) && !json.get(campo).isNull()) {
            return json.get(campo).asInt();
        }

        return null;
    }
}
