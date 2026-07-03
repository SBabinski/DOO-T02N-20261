package service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import model.ResultadoBusca;
import model.Serie;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.List;

public  class SeriesAPIServico {

    public static List<Serie> buscaSerie(String nomeSerie) {

        try {
            String nomeSerieUrl =
                    URLEncoder.encode(nomeSerie, StandardCharsets.UTF_8);

            String url = "https://api.tvmaze.com/search/shows?q=" + nomeSerieUrl;

            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .GET()
                    .build();

            HttpResponse<String> response =
                    client.send(request, HttpResponse.BodyHandlers.ofString());

            ObjectMapper mapper = new ObjectMapper();
            mapper.registerModule(new JavaTimeModule());



            List<ResultadoBusca> resultados =
                    mapper.readValue(
                            response.body(),
                            new TypeReference<List<ResultadoBusca>>() {
                            }
                    );

            if (resultados.isEmpty()) {
                return null;
            }

            return resultados.stream()
                    .map(ResultadoBusca::getShow)
                    .toList();

        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("Erro ao buscar série", e);

        }
    }
}