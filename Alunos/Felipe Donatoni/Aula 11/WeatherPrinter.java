public class WeatherPrinter {

    public void print(WeatherResponse response) {
        CurrentConditions cur   = response.currentConditions;
        DayForecast       today = response.getTodayForecast();

        printHeader(response);
        printTemperatura(cur, today);
        printUmidade(cur);
        printCondicao(cur, today);
        printPrecipitacao(cur, today);
        printVento(cur);
    }

    private void printHeader(WeatherResponse r) {
        System.out.println();
        System.out.println("CLIMA ATUAL — " + (r.resolvedAddress != null ? r.resolvedAddress.toUpperCase() : "N/D"));
        if (r.latitude != null && r.longitude != null) {
            System.out.printf("Coordenadas: %.4f, %.4f%n", r.latitude, r.longitude);
        }
        System.out.println();
    }

    private void printTemperatura(CurrentConditions cur, DayForecast today) {
        System.out.println("TEMPERATURA");
        System.out.printf("%-20s %s%n", "Agora:",
                WeatherFormatter.formatTemperature(cur != null ? cur.temperature : null));
        if (cur != null && cur.feelsLike != null) {
            System.out.printf("%-20s %s%n", "Sensação térmica:",
                    WeatherFormatter.formatTemperature(cur.feelsLike));
        }
        if (today != null) {
            System.out.printf("%-20s %s%n", "Máxima do dia:",
                    WeatherFormatter.formatTemperature(today.tempMax));
            System.out.printf("%-20s %s%n", "Mínima do dia:",
                    WeatherFormatter.formatTemperature(today.tempMin));
        }
        System.out.println();
    }

    private void printUmidade(CurrentConditions cur) {
        System.out.println("UMIDADE DO AR");
        if (cur == null) { System.out.println("  Dados indisponíveis."); System.out.println(); return; }
        System.out.printf("%-20s %s — %s%n", "Umidade relativa:",
                WeatherFormatter.formatHumidity(cur.humidity),
                WeatherFormatter.describeHumidity(cur.humidity));
        System.out.println();
    }

    private void printCondicao(CurrentConditions cur, DayForecast today) {
        System.out.println("CONDIÇÃO DO TEMPO");
        String condNow = (cur != null && cur.conditions != null) ? cur.conditions : null;
        String condDay = (today != null && today.conditions != null) ? today.conditions : null;
        if (condNow != null) {
            System.out.printf("%-20s %s%n", "Agora:", WeatherFormatter.traduzir(condNow));
        }
        if (condDay != null && !condDay.equals(condNow)) {
            System.out.printf("%-20s %s%n", "Resumo do dia:", WeatherFormatter.traduzir(condDay));
        }
        if (today != null && today.description != null) {
            System.out.printf("%-20s %s%n", "Previsão:", WeatherFormatter.traduzir(today.description));
        }
        if (cur != null && cur.cloudCover != null) {
            System.out.printf("%-20s %.0f%%%n", "Cobertura de nuvens:", cur.cloudCover);
        }
        System.out.println();
    }

    private void printPrecipitacao(CurrentConditions cur, DayForecast today) {
        System.out.println("PRECIPITAÇÃO");
        System.out.printf("%-20s %s%n", "Acumulado (agora):",
                WeatherFormatter.formatPrecipitation(cur != null ? cur.precipitation : null));
        if (today != null) {
            System.out.printf("%-20s %s%n", "Total do dia:",
                    WeatherFormatter.formatPrecipitation(today.precipitationTotal));
            System.out.printf("%-20s %s%n", "Probabilidade:",
                    WeatherFormatter.formatPrecipitationProbability(today.precipitationProbability));
        }
        System.out.println();
    }

    private void printVento(CurrentConditions cur) {
        System.out.println("VENTO");
        if (cur == null) { System.out.println("  Dados indisponíveis."); System.out.println(); return; }
        System.out.printf("%-20s %s — %s%n", "Velocidade:",
                WeatherFormatter.formatWindSpeed(cur.windSpeed),
                WeatherFormatter.describeWindSpeed(cur.windSpeed));
        if (cur.windGust != null) {
            System.out.printf("%-20s %s%n", "Rajadas:",
                    WeatherFormatter.formatWindSpeed(cur.windGust));
        }
        String cardinal = WeatherFormatter.degreesToCardinal(cur.windDirection);
        String graus = cur.windDirection != null ? String.format("(%.1f°)", cur.windDirection) : "";
        System.out.printf("%-20s %s %s%n", "Direção:", cardinal, graus);
        System.out.println();
    }
}
