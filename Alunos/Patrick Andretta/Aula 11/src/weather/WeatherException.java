package weather;

public class WeatherException extends Exception {
    private final int statusCode;

    public WeatherException(String message, int statusCode) {
        super(message);
        this.statusCode = statusCode;
    }

    public int getStatusCode() {
        return statusCode;
    }
}
