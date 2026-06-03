package com.weather.util;

public class UnitConverter {

    private static final String[] DIRECTIONS = {
        "N", "NNE", "NE", "ENE", "L", "ESE", "SE", "SSE",
        "S", "SSO", "SO", "OSO", "O", "ONO", "NO", "NNO"
    };

    public static double fahrenheitToCelsius(double f) {
        return (f - 32.0) * 5.0 / 9.0;
    }

    public static double mphToKmh(double mph) {
        return mph * 1.60934;
    }

    public static String degreesToCardinal(double degrees) {
        int index = (int) Math.round(degrees / 22.5) % 16;
        return DIRECTIONS[index];
    }
}
