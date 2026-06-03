/**
 * Modelo agregado com todos os dados meteorológicos retornados pela API.
 * Cada campo é um objeto especializado com suas próprias regras de formatação.
 */
public class WeatherData {

    /** Endereço resolvido pela API (ex: "São Paulo, SP, Brasil"). */
    public final String resolvedAddress;

    /** Temperatura no momento da consulta. */
    public final CurrentTemp currentTemp;

    /** Máxima e mínima do dia. */
    public final DailyTempRange tempRange;

    /** Humidade relativa do ar. */
    public final Humidity humidity;

    /** Condição do tempo (traduzida). */
    public final WeatherCondition condition;

    /** Precipitação: quantidade e probabilidade. */
    public final Precipitation precipitation;

    /** Velocidade e direção do vento. */
    public final WindInfo wind;

    /** Visibilidade em km. Double.NaN se indisponível. */
    public final double visibilityKm;

    /** Pressão atmosférica em hPa. Double.NaN se indisponível. */
    public final double pressureHpa;

    public WeatherData(
            String resolvedAddress,
            CurrentTemp currentTemp,
            DailyTempRange tempRange,
            Humidity humidity,
            WeatherCondition condition,
            Precipitation precipitation,
            WindInfo wind,
            double visibilityKm,
            double pressureHpa) {

        this.resolvedAddress = resolvedAddress;
        this.currentTemp     = currentTemp;
        this.tempRange       = tempRange;
        this.humidity        = humidity;
        this.condition       = condition;
        this.precipitation   = precipitation;
        this.wind            = wind;
        this.visibilityKm    = visibilityKm;
        this.pressureHpa     = pressureHpa;
    }

    public String formattedVisibility() {
        return Double.isNaN(visibilityKm) ? "—" : String.format("%.1f km", visibilityKm);
    }

    public String formattedPressure() {
        return Double.isNaN(pressureHpa) ? "—" : Math.round(pressureHpa) + " hPa";
    }
}
