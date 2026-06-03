package com.weather.service;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.weather.config.ApiConfig;
import com.weather.model.WeatherData;
import com.weather.util.UnitConverter;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class WeatherService {

    private static final String BASE_URL =
        "https://weather.visualcrossing.com/VisualCrossingWebServices/rest/services/timeline/";

    public WeatherData fetchWeather(String city) throws Exception {
        String apiKey  = ApiConfig.getApiKey();
        String encoded = URLEncoder.encode(city, "UTF-8");
        String urlStr  = BASE_URL + encoded
                + "?unitGroup=us"
                + "&include=current"
                + "&key=" + apiKey
                + "&contentType=json";

        String json = get(urlStr);
        return parse(json, city);
    }

    private String get(String urlStr) throws Exception {
        HttpURLConnection conn = (HttpURLConnection) new URL(urlStr).openConnection();
        conn.setRequestMethod("GET");
        conn.setConnectTimeout(10000);
        conn.setReadTimeout(15000);

        int status = conn.getResponseCode();
        if (status != 200) {
            throw new Exception("Erro na API: HTTP " + status);
        }

        BufferedReader reader = new BufferedReader(
                new InputStreamReader(conn.getInputStream(), "UTF-8"));
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            sb.append(line);
        }
        reader.close();
        return sb.toString();
    }

    private WeatherData parse(String json, String cityInput) {
        JsonObject root = JsonParser.parseString(json).getAsJsonObject();
        String city     = root.has("resolvedAddress")
                        ? root.get("resolvedAddress").getAsString() : cityInput;

        // Primeiro elemento de "days" contem os dados do dia atual
        JsonObject today = root.getAsJsonArray("days").get(0).getAsJsonObject();

        double maxTemp = UnitConverter.fahrenheitToCelsius(today.get("tempmax").getAsDouble());
        double minTemp = UnitConverter.fahrenheitToCelsius(today.get("tempmin").getAsDouble());
        double precip  = today.has("precip") && !today.get("precip").isJsonNull()
                       ? today.get("precip").getAsDouble() : 0.0;
        String icon    = today.has("icon") ? today.get("icon").getAsString() : "";

        // "currentConditions" reflete o estado no momento exato da consulta
        JsonObject current = root.has("currentConditions")
                           ? root.getAsJsonObject("currentConditions") : today;

        double currentTemp = UnitConverter.fahrenheitToCelsius(current.get("temp").getAsDouble());
        double humidity    = current.get("humidity").getAsDouble();
        double windSpeed   = UnitConverter.mphToKmh(current.get("windspeed").getAsDouble());
        double windDeg     = current.get("winddir").getAsDouble();
        String condition   = current.has("conditions")
                           ? current.get("conditions").getAsString() : "N/D";

        return new WeatherData(city, currentTemp, maxTemp, minTemp,
                humidity, condition, precip, windSpeed,
                UnitConverter.degreesToCardinal(windDeg), icon);
    }
}
