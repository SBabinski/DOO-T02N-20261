package clima.util;

public class ConversorDirecaoVento {

    private static final String[] CARDEAIS = { "N", "NE", "L", "SE", "S", "SO", "O", "NO" };

    private ConversorDirecaoVento() {}

    public static String paraCardinal(double graus) {
        double normalizado = ((graus % 360) + 360) % 360;
        return CARDEAIS[(int) Math.round(normalizado / 45.0) % 8];
    }
}