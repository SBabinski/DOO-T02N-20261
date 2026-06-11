public class WeatherService {

    private VisualCrossingClient client;

    public WeatherService(String apiKey) {
        this.client = new VisualCrossingClient(apiKey);
    }

    public WeatherData buscarClima(String cidade)
            throws WeatherException {

        CityValidator.validar(cidade);

        return client.buscarClima(cidade);
    }
}