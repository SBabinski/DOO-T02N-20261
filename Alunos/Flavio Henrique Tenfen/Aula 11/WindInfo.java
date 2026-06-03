/**
 * Informações do vento: velocidade (km/h) e direção (graus + cardinal).
 */
public class WindInfo {

    /** Velocidade do vento em km/h. Double.NaN se indisponível. */
    public final double speedKmh;

    /** Direção do vento em graus (0–360). Double.NaN se indisponível. */
    public final double directionDeg;

    public WindInfo(double speedKmh, double directionDeg) {
        this.speedKmh     = speedKmh;
        this.directionDeg = directionDeg;
    }

    /** Ex: "24 km/h" ou "—". */
    public String formattedSpeed() {
        return Double.isNaN(speedKmh) ? "—" : Math.round(speedKmh) + " km/h";
    }

    /**
     * Ex: "NE (45°)" ou "—".
     * Ponto cardeal em português: N, NE, L (Leste), SE, S, SO, O (Oeste), NO.
     */
    public String formattedDirection() {
        if (Double.isNaN(directionDeg)) return "—";
        return toCardinal(directionDeg) + " (" + Math.round(directionDeg) + "°)";
    }

    // ─────────────────────────────────────────────────────────────────────
    //  Conversão graus → cardinal (português)
    // ─────────────────────────────────────────────────────────────────────

    private static final String[] CARDINALS = {
        "N","NNE","NE","ENE",
        "L","ESE","SE","SSE",
        "S","SSO","SO","OSO",
        "O","ONO","NO","NNO"
    };

    public static String toCardinal(double degrees) {
        int index = (int) Math.round(degrees / 22.5) % 16;
        return CARDINALS[index];
    }
}
