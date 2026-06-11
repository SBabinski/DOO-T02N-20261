package weather;

public class WindDirectionConverter {

    private static final String[] CARDINALS = {"N", "NE", "L", "SE", "S", "SO", "O", "NO"};

    public static String toCardinal(double degrees) {
        double normalized = ((degrees % 360) + 360) % 360;
        return CARDINALS[(int) Math.round(normalized / 45.0) % 8];
    }
}
