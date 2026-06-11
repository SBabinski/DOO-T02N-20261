import java.util.Scanner;

public class WeatherApp {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Informe sua chave de API: ");
        String apiKey = scanner.nextLine().trim();

        System.out.print("Informe a cidade: ");
        String location = scanner.nextLine().trim();

        scanner.close();
        run(location, apiKey);
    }

    public static void run(String location, String apiKey) {
        WeatherService service = new WeatherService(apiKey);
        WeatherPrinter printer = new WeatherPrinter();

        System.out.printf("%nBuscando dados para: %s...%n", location);

        try {
            WeatherResponse response = service.fetchCurrentWeather(location);
            printer.print(response);
        } catch (WeatherApiException e) {
            System.err.println("\nErro ao consultar API: " + e.getMessage());
            if (e.getHttpStatus() > 0) {
                System.err.printf("Código HTTP: %d%n", e.getHttpStatus());
            }
        } catch (IllegalArgumentException e) {
            System.err.println("\nParâmetro inválido: " + e.getMessage());
        }
    }
}
