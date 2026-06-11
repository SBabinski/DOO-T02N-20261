/**
 * Condição do tempo no momento da consulta.
 * Traduz automaticamente o texto da API (inglês) para português com emoji.
 */
public class WeatherCondition {

    /** Texto original retornado pela API (ex: "Partially cloudy"). */
    public final String raw;

    /** Texto traduzido para português com emoji (ex: "⛅  Parcialmente nublado"). */
    public final String translated;

    public WeatherCondition(String raw) {
        this.raw        = raw != null ? raw : "";
        this.translated = translate(this.raw);
    }

    // ─────────────────────────────────────────────────────────────────────
    //  Tradução
    // ─────────────────────────────────────────────────────────────────────

    private static String translate(String condition) {
        if (condition == null || condition.isBlank()) return "—";
        String lo = condition.toLowerCase();

        if (lo.contains("thunderstorm"))                              return "⛈  Tempestade";
        if (lo.contains("heavy rain"))                                return "🌧  Chuva forte";
        if (lo.contains("freezing rain"))                             return "🌨  Chuva congelante";
        if (lo.contains("rain"))                                      return "🌦  Chuva";
        if (lo.contains("drizzle"))                                   return "🌧  Garoa";
        if (lo.contains("snow"))                                      return "❄  Neve";
        if (lo.contains("sleet")||lo.contains("hail")||lo.contains("ice")) return "🌨  Granizo";
        if (lo.contains("fog") || lo.contains("mist"))                return "🌫  Névoa";
        if (lo.contains("overcast") || lo.contains("cloudy"))         return "☁  Nublado";
        if (lo.contains("partly cloudy"))                             return "⛅  Parcialmente nublado";
        if (lo.contains("clear") || lo.contains("sunny"))             return "☀  Céu limpo";
        if (lo.contains("wind"))                                      return "💨  Ventania";
        if (lo.contains("haz"))                                       return "😶‍🌫  Névoa seca";

        return condition; // retorna original se não houver mapeamento
    }
}
