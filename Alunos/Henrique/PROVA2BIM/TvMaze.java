package fag;

import java.util.List;
import java.util.ArrayList;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
 
public class TvMaze {

		
		public List<Serie> buscarSerie(String nomeSerie){
			List<Serie> series = new ArrayList<>();
			
			try {
				
				OkHttpClient client = new OkHttpClient();
				String url = "https://api.tvmaze.com/search/shows?q=" + nomeSerie;
				Request request = new Request.Builder()
						.url(url)
						.build();
				Response response = client.newCall(request).execute();
				String json = response.body().string();
				
				//System.out.println(json);
				
				JsonArray array = JsonParser.parseString(json).getAsJsonArray();
				for (int i = 0; i < array.size(); i++) {
					JsonObject item = array.get(i).getAsJsonObject();
					JsonObject show = item.get("show").getAsJsonObject();
					
					int id = show.get("id").getAsInt();
					String nome = show.get("name").getAsString();
					String idioma = show.get("language").isJsonNull() ? "" : show.get("language").getAsString();
					String status = show.get("status").isJsonNull() ? "" : show.get("status").getAsString();
					
					JsonObject rating = show.get("rating").getAsJsonObject();
					double nota = rating.get("average").isJsonNull() ? 0.0 : rating.get("average").getAsDouble();
					
					List<String> generos = new ArrayList<>();
					JsonArray generosLista = show.get("genres").getAsJsonArray();
					for (int j = 0; j < generosLista.size(); j++) {
						generos.add(generosLista.get(j).getAsString());
					}
					String estreia = show.get("premiered").isJsonNull() ? "" : show.get("premiered").getAsString();
					String termino = show.get("ended").isJsonNull() ? "" : show.get("ended").getAsString();
					
					Emissora emissora = null;
					if (!show.get("network").isJsonNull()) {
						JsonObject network = show.get("network").getAsJsonObject();
						String nomeEmissora = network.get("name").getAsString();
						String pais = network.get("country").getAsJsonObject().get("name").getAsString();
						emissora = new Emissora (nomeEmissora, pais);
					}
					
					Serie serie = new Serie(id, nome, idioma, generos, nota, status, estreia, termino, emissora);
					series.add(serie);
					
					
				}
				
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			return series;
			
		
			
		}
	}
