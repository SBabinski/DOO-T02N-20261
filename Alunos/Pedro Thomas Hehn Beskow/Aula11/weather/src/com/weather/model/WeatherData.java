package com.weather.model;

public class WeatherData {

    private final String city;
    private final double currentTemp;
    private final double maxTemp;
    private final double minTemp;
    private final double humidity;
    private final String condition;
    private final double precipitation;
    private final double windSpeed;
    private final String windDirection;
    private final String icon;

    public WeatherData(String city, double currentTemp, double maxTemp, double minTemp,
                       double humidity, String condition, double precipitation,
                       double windSpeed, String windDirection, String icon) {
        this.city = city;
        this.currentTemp = currentTemp;
        this.maxTemp = maxTemp;
        this.minTemp = minTemp;
        this.humidity = humidity;
        this.condition = condition;
        this.precipitation = precipitation;
        this.windSpeed = windSpeed;
        this.windDirection = windDirection;
        this.icon = icon;
    }

    public String getCity()          { return city; }
    public double getCurrentTemp()   { return currentTemp; }
    public double getMaxTemp()       { return maxTemp; }
    public double getMinTemp()       { return minTemp; }
    public double getHumidity()      { return humidity; }
    public String getCondition()     { return condition; }
    public double getPrecipitation() { return precipitation; }
    public double getWindSpeed()     { return windSpeed; }
    public String getWindDirection() { return windDirection; }
    public String getIcon()          { return icon; }
}
