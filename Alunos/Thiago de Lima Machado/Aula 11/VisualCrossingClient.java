import com.weather.config.WeatherClientConfig;
import com.weather.dto.TimelineResponse;
import com.weather.exception.WeatherApiException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.Duration;


@Component
@RequiredArgsConstructor
@Slf4j
public class VisualCrossingClient {

    private final WebClient visualCrossingWebClient;
    private final WeatherClientConfig config;

    public TimelineResponse getForecast(String location) {
        return request(location, null, null, "days,hours,current,alerts");
    }

    public TimelineResponse getHistory(String location, String date1, String date2) {
        return request(location, date1, date2, "days,hours");
    }

    public TimelineResponse getCurrentConditions(String location) {
        return request(location, "today", null, "current");
    }

    public TimelineResponse getDynamicPeriod(String location, String period) {
        return request(location, period, null, "days,hours");
    }

    private TimelineResponse request(String location, String date1, String date2, String include) {
        String path = buildPath(location, date1, date2);
        log.debug("[VC] Requisitando: path={} include={}", path, include);

        return visualCrossingWebClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path(path)
                        .queryParam("key",         config.getKey())
                        .queryParam("unitGroup",   config.getUnitGroup())
                        .queryParam("lang",        config.getLang())
                        .queryParam("include",     include)
                        .queryParam("contentType", "json")
                        .build())
                .retrieve()
                // Erros 4xx — parâmetros inválidos, key expirada, rate limit
                .onStatus(
                        status -> status.is4xxClientError(),
                        response -> response.bodyToMono(String.class)
                                .map(body -> new WeatherApiException(response.statusCode().value(), body))
                )
                // Erros 5xx — falha interna da VC API
                .onStatus(
                        status -> status.is5xxServerError(),
                        response -> Mono.error(new WeatherApiException(500, "Erro interno na Visual Crossing API"))
                )
                .bodyToMono(TimelineResponse.class)
                // Loga o custo da query para controle de quota
                .doOnNext(r -> log.info("[VC] OK — queryCost={} location='{}' resolvedAddress='{}'",
                        r.getQueryCost(), location, r.getResolvedAddress()))
                .block(Duration.ofSeconds(config.getTimeoutSeconds()));
    }

    private String buildPath(String location, String date1, String date2) {
        String encoded = URLEncoder.encode(location, StandardCharsets.UTF_8);
        if (date1 == null) return "/" + encoded;
        if (date2 == null) return "/" + encoded + "/" + date1;
        return "/" + encoded + "/" + date1 + "/" + date2;
    }
}
