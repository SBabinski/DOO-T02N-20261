public class WeatherApiException extends Exception {

    public WeatherApiException(String mensagem) {
        super(mensagem);
    }

    public WeatherApiException(String mensagem, Throwable causa) {
        super(mensagem, causa);
    }
} 