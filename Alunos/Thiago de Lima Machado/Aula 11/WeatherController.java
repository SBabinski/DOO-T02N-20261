import com.weather.dto.TimelineResponse;
import com.weather.service.WeatherService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Controller
@RequiredArgsConstructor
public class WeatherController {

    private final WeatherService weatherService;

    @GetMapping("/weather")
    public String dashboard(
            @RequestParam(defaultValue = "Cascavel,BR") String location,
            Model model) {

        TimelineResponse forecast = weatherService.getForecast(location);
        model.addAttribute("weather",  forecast);
        model.addAttribute("location", location);
        return "weather"; // resolve para templates/weather.html
    }

    @GetMapping("/api/weather/forecast")
    @ResponseBody
    public ResponseEntity<TimelineResponse> forecast(@RequestParam String location) {
        return ResponseEntity.ok(weatherService.getForecast(location));
    }

    @GetMapping("/api/weather/history")
    @ResponseBody
    public ResponseEntity<TimelineResponse> history(
            @RequestParam String location,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate start,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate end) {

        return ResponseEntity.ok(weatherService.getHistory(location, start, end));
    }

    @GetMapping("/api/weather/current")
    @ResponseBody
    public ResponseEntity<TimelineResponse> current(@RequestParam String location) {
        return ResponseEntity.ok(weatherService.getCurrentConditions(location));
    }

    @GetMapping("/api/weather/period")
    @ResponseBody
    public ResponseEntity<TimelineResponse> period(
            @RequestParam String location,
            @RequestParam String period) {

        return ResponseEntity.ok(weatherService.getDynamicPeriod(location, period));
    }
}
