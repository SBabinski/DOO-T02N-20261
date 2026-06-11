public class WindDirectionConverter {

    private static final String[] DIRECOES = {
            "N",
            "NE",
            "L",
            "SE",
            "S",
            "SO",
            "O",
            "NO"
    };

    public static String converter(double graus) {

        graus = ((graus % 360) + 360) % 360;

        int indice =
                (int)Math.round(graus / 45.0) % 8;

        return DIRECOES[indice];
    }
}