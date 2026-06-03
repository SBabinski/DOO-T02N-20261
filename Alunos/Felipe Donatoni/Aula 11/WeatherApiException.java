public class WeatherApiException extends Exception {
    private final int httpStatus;

    public WeatherApiException(String message) {
        super(message);
        this.httpStatus = -1;
    }

    public WeatherApiException(String message, int httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }

    public WeatherApiException(String message, Throwable cause) {
        super(message, cause);
        this.httpStatus = -1;
    }

    public int getHttpStatus() {
        return httpStatus;
    }
}
