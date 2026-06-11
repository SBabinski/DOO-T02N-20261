import com.weather.client.VisualCrossingClient;
import com.weather.dto.TimelineResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

@Service
@RequiredArgsConstructor
@Slf4j
public class WeatherService {

    private static final int MAX_HISTORICAL_DAYS = 365;
    private static final DateTimeFormatter DATE_FMT = DateTimeFormatter.ISO_LOCAL_DATE;

    private final VisualCrossingClient client;

    public TimelineResponse getForecast(String location) {
        validateLocation(location);
        log.debug("Buscando previsão para '{}'", location);
        return client.getForecast(location);
    }

    public TimelineResponse getHistory(String location, LocalDate start, LocalDate end) {
        validateLocation(location);
        validateDateRange(start, end);

        String date1 = start.format(DATE_FMT);
        String date2 = end.format(DATE_FMT);
        log.debug("Buscando histórico para '{}' de {} a {}", location, date1, date2);
        return client.getHistory(location, date1, date2);
    }

    public TimelineResponse getCurrentConditions(String location) {
        validateLocation(location);
        log.debug("Buscando condições atuais para '{}'", location);
        return client.getCurrentConditions(location);
    }

    public TimelineResponse getDynamicPeriod(String location, String period) {
        validateLocation(location);
        validateDynamicPeriod(period);
        log.debug("Buscando período dinâmico '{}' para '{}'", period, location);
        return client.getDynamicPeriod(location, period);
    }

    private void validateLocation(String location) {
        if (location == null || location.isBlank()) {
            throw new IllegalArgumentException("O parâmetro 'location' não pode ser vazio.");
        }
    }

    private void validateDateRange(LocalDate start, LocalDate end) {
        if (start == null || end == null) {
            throw new IllegalArgumentException("As datas 'start' e 'end' são obrigatórias.");
        }
        if (end.isBefore(start)) {
            throw new IllegalArgumentException("A data 'end' não pode ser anterior a 'start'.");
        }
        if (end.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("A data 'end' não pode ser uma data futura para consultas históricas.");
        }
        long days = ChronoUnit.DAYS.between(start, end);
        if (days > MAX_HISTORICAL_DAYS) {
            throw new IllegalArgumentException(
                    "O intervalo máximo para consultas históricas é de %d dias.".formatted(MAX_HISTORICAL_DAYS));
        }
    }
    private void validateDynamicPeriod(String period) {
        var valid = java.util.Set.of(
                "today", "yesterday", "last7days", "last30days", "lastyear", "last365days"
        );
        if (!valid.contains(period)) {
            throw new IllegalArgumentException(
                    "Período dinâmico inválido: '%s'. Valores aceitos: %s".formatted(period, valid));
        }
    }
}
