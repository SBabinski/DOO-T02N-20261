import java.util.Scanner;

public class Main {

    private static final String API_KEY = "KA8ZHP3HTPTNHCVHLBBJEN9CY";

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("=== CONSULTA CLIMATICA ===");
        System.out.print("Digite a cidade: ");

        String cidade = scanner.nextLine();

        try {

            WeatherService service =
                    new WeatherService(API_KEY);

            WeatherData clima =
                    service.buscarClima(cidade);

            System.out.println();
            System.out.println("Cidade: " + clima.getCidade());
            System.out.println("Temperatura Atual: "
                    + clima.getTemperaturaAtual() + "°C");

            System.out.println("Maxima: "
                    + clima.getTemperaturaMaxima() + "°C");

            System.out.println("Minima: "
                    + clima.getTemperaturaMinima() + "°C");

            System.out.println("Umidade: "
                    + clima.getUmidade() + "%");

            System.out.println("Condicao: "
                    + clima.getCondicao());

            System.out.println("Precipitacao: "
                    + clima.getPrecipitacao() + " mm");

            System.out.println("Velocidade do vento: "
                    + clima.getVelocidadeVento()
                    + " km/h");

            System.out.println("Direcao do vento: "
                    + WindDirectionConverter.converter(
                    clima.getDirecaoVento()));

        } catch (WeatherException e) {

            System.out.println("Erro: "
                    + e.getMessage());

        }

        scanner.close();
    }
}