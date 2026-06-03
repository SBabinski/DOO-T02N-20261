import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JsonParser {

    public static WeatherResponse parseWeatherResponse(String json) {
        WeatherResponse response = new WeatherResponse();
        
        response.resolvedAddress = extractString(json, "resolvedAddress");
        response.address = extractString(json, "address");
        response.timezone = extractString(json, "timezone");
        response.latitude = extractDouble(json, "latitude");
        response.longitude = extractDouble(json, "longitude");
        response.description = extractString(json, "description");
        
        String currentJson = extractObject(json, "currentConditions");
        if (currentJson != null) {
            response.currentConditions = parseCurrentConditions(currentJson);
        }
        
        response.days = parseDays(json);
        
        return response;
    }

    public static CurrentConditions parseCurrentConditions(String json) {
        CurrentConditions cond = new CurrentConditions();
        cond.temperature = extractDouble(json, "temp");
        cond.feelsLike = extractDouble(json, "feelslike");
        cond.humidity = extractDouble(json, "humidity");
        cond.conditions = extractString(json, "conditions");
        cond.precipitation = extractDouble(json, "precip");
        cond.windSpeed = extractDouble(json, "windspeed");
        cond.windDirection = extractDouble(json, "winddir");
        cond.windGust = extractDouble(json, "windgust");
        cond.cloudCover = extractDouble(json, "cloudcover");
        cond.visibility = extractDouble(json, "visibility");
        cond.datetime = extractString(json, "datetime");
        return cond;
    }

    public static List<DayForecast> parseDays(String json) {
        List<DayForecast> days = new ArrayList<>();
        String daysArray = extractArray(json, "days");
        if (daysArray == null || daysArray.isEmpty()) return days;
        
        String[] dayObjects = splitArrayObjects(daysArray);
        for (String dayJson : dayObjects) {
            if (!dayJson.trim().isEmpty()) {
                DayForecast day = new DayForecast();
                day.date = extractString(dayJson, "datetime");
                day.tempMax = extractDouble(dayJson, "tempmax");
                day.tempMin = extractDouble(dayJson, "tempmin");
                day.tempAvg = extractDouble(dayJson, "temp");
                day.precipitationProbability = extractDouble(dayJson, "precipprob");
                day.precipitationTotal = extractDouble(dayJson, "precip");
                day.conditions = extractString(dayJson, "conditions");
                day.description = extractString(dayJson, "description");
                days.add(day);
            }
        }
        return days;
    }

    private static String extractString(String json, String key) {
        Pattern pattern = Pattern.compile("\"" + key + "\"\\s*:\\s*\"([^\"]*)\"");
        Matcher matcher = pattern.matcher(json);
        return matcher.find() ? matcher.group(1) : null;
    }

    private static Double extractDouble(String json, String key) {
        Pattern pattern = Pattern.compile("\"" + key + "\"\\s*:\\s*([\\d.]+)");
        Matcher matcher = pattern.matcher(json);
        if (matcher.find()) {
            try {
                return Double.parseDouble(matcher.group(1));
            } catch (NumberFormatException e) {
                return null;
            }
        }
        return null;
    }

    private static String extractObject(String json, String key) {
        Pattern pattern = Pattern.compile("\"" + key + "\"\\s*:\\s*\\{");
        Matcher matcher = pattern.matcher(json);
        if (!matcher.find()) return null;
        
        int start = matcher.end() - 1;
        int braceCount = 1;
        int i = start + 1;
        
        while (i < json.length() && braceCount > 0) {
            if (json.charAt(i) == '{') braceCount++;
            else if (json.charAt(i) == '}') braceCount--;
            i++;
        }
        
        return json.substring(start, i);
    }

    private static String extractArray(String json, String key) {
        Pattern pattern = Pattern.compile("\"" + key + "\"\\s*:\\s*\\[");
        Matcher matcher = pattern.matcher(json);
        if (!matcher.find()) return null;
        
        int start = matcher.end() - 1;
        int bracketCount = 1;
        int i = start + 1;
        
        while (i < json.length() && bracketCount > 0) {
            if (json.charAt(i) == '[') bracketCount++;
            else if (json.charAt(i) == ']') bracketCount--;
            i++;
        }
        
        return json.substring(start + 1, i - 1);
    }

    private static String[] splitArrayObjects(String arrayContent) {
        List<String> objects = new ArrayList<>();
        int braceCount = 0;
        int start = 0;
        
        for (int i = 0; i < arrayContent.length(); i++) {
            char c = arrayContent.charAt(i);
            if (c == '{') braceCount++;
            else if (c == '}') {
                braceCount--;
                if (braceCount == 0) {
                    objects.add(arrayContent.substring(start, i + 1));
                    start = i + 1;
                }
            }
        }
        
        return objects.toArray(new String[0]);
    }
}
