import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class WeatherApiException extends RuntimeException {

    private final int statusCode;
    private final String apiMessage;

    public WeatherApiException(int statusCode, String apiMessage) {
        super("Erro na Visual Crossing API [%d]: %s".formatted(statusCode, apiMessage));
        this.statusCode = statusCode;
        this.apiMessage = apiMessage;
    }

    public HttpStatus toHttpStatus() {
        return switch (statusCode) {
            case 400 -> HttpStatus.BAD_REQUEST;
            case 401 -> HttpStatus.UNAUTHORIZED;
            case 404 -> HttpStatus.NOT_FOUND;
            case 429 -> HttpStatus.TOO_MANY_REQUESTS;
            default  -> HttpStatus.INTERNAL_SERVER_ERROR;
        };
    }
}
