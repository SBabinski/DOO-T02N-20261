package com.weather.util;

public class WeatherLabels {

    public static String iconToEmoji(String icon) {
        if (icon == null) return "🌡";
        switch (icon) {
            case "clear-day":             return "☀";
            case "clear-night":           return "🌙";
            case "partly-cloudy-day":     return "⛅";
            case "partly-cloudy-night":   return "🌥";
            case "cloudy":                return "☁";
            case "fog":                   return "🌫";
            case "wind":                  return "💨";
            case "rain":                  return "🌧";
            case "showers-day":           return "🌦";
            case "showers-night":         return "🌧";
            case "thunder-rain":          return "⛈";
            case "thunder-showers-day":   return "⛈";
            case "thunder-showers-night": return "⛈";
            case "snow":                  return "❄";
            case "snow-showers-day":      return "🌨";
            case "snow-showers-night":    return "🌨";
            default:                      return "🌡";
        }
    }

    public static String translateCondition(String condition) {
        if (condition == null || condition.isEmpty()) return "N/D";
        String lower = condition.toLowerCase();
        if (lower.contains("clear"))                               return "Céu limpo";
        if (lower.contains("overcast"))                            return "Encoberto";
        if (lower.contains("partially cloudy") || lower.contains("partly")) return "Parcialmente nublado";
        if (lower.contains("cloudy"))                              return "Nublado";
        if (lower.contains("thunder"))                             return "Tempestade";
        if (lower.contains("drizzle"))                             return "Garoa";
        if (lower.contains("rain") || lower.contains("shower"))    return "Chuva";
        if (lower.contains("snow"))                                return "Neve";
        if (lower.contains("fog") || lower.contains("mist"))       return "Neblina";
        if (lower.contains("wind"))                                return "Ventoso";
        return condition;
    }
}
