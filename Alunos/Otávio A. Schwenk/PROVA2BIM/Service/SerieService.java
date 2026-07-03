package Service;

import Objetos.Serie;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.ArrayList;
import java.util.List;

public class SerieService{

    private final HttpClient client = HttpClient.newHttpClient();
    private final ObjectMapper mapper = new ObjectMapper();

    public List<Serie> buscarSerie(String query){

        try{
            URI url = new URI("https://api.tvmaze.com/search/shows?q=" + query);

            HttpRequest request = HttpRequest.newBuilder(url)
                    .GET()
                    .build();

            HttpResponse<String> response =
                    client.send(request, BodyHandlers.ofString());

            if(response.statusCode() != 200){
                return List.of();
            }

            JsonNode root = mapper.readTree(response.body());

            List<Serie> series = new ArrayList<>();

            for (JsonNode item : root){

                JsonNode show = item.get("show");

                int id = show.get("id").asInt();
                String nome = show.get("name").asText();

                double nota = (show.get("rating").get("average").isNull())
                        ? 0.0
                        : show.get("rating").get("average").asDouble();

                String estado = show.get("status").asText();
                String idioma = show.get("language").asText();

                String descricao = (show.get("summary").isNull())
                        ? "Sem descrição"
                        : show.get("summary").asText().replaceAll("<.*?>", "");

                String emissora = (show.get("network").isNull())
                        ? "Desconhecida"
                        : show.get("network").get("name").asText();

                String estreia = parseData(show.get("premiered"));
                String termino = parseData(show.get("ended"));

                List<String> generos = new ArrayList<>();
                for(JsonNode g : show.get("genres")){
                    generos.add(g.asText());
                }

                Serie serie = new Serie(
                        id,
                        nome,
                        nota,
                        estado,
                        idioma,
                        estreia,
                        termino,
                        emissora,
                        descricao,
                        generos
                );

                series.add(serie);
            }

            return series;

        }catch (Exception e){
            throw new RuntimeException("Erro ao buscar séries", e);
        }
    }

    private String parseData(JsonNode node){
        if(node == null || node.isNull() || node.asText().isEmpty()){
            return null;
        }
        return node.asText();
    }
}