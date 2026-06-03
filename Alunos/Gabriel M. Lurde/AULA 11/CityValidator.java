public class CityValidator {

    public static void validar(String cidade)
            throws WeatherException {

        if (cidade == null ||
                cidade.isBlank()) {

            throw new WeatherException(
                    "Cidade invalida.");
        }
    }
}