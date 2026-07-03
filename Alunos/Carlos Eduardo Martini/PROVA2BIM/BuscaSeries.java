import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class BuscaSeries {
    public String buscaApi(String nomeSerie) {
        String buscaForm = nomeSerie.replace(" ", "+");
        String endereURL = "https://api.tvmaze.com/singlesearch/shows?q=" + buscaForm;

        try {
            HttpClient client = HttpClient.newHttpClient();

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(endereURL))                 
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return response.body();

        } catch (Exception e) {
            System.out.println("Erro ao buscar" + e.getMessage());
            return null;
        }
    }
}
