import java.util.Scanner;

public class Main {

    private static final String API_KEY = "2QNL6YQ6A53JXWU98SLSKFMDV";

    public static void main(String[] args) {
        WeatherService service = new WeatherService(API_KEY);
        WeatherView view = new WeatherView();

        System.out.println("=== Aplicativo de Consulta de Clima ===");
        System.out.println("Powered by Visual Crossing Weather API");

        try (Scanner scanner = new Scanner(System.in)) {
            boolean continuar = true;

            while (continuar) {
                System.out.print("\nDigite o nome da cidade: ");
                String cidade = scanner.nextLine().trim();

                if (cidade.isEmpty()) {
                    view.exibirErro("O nome da cidade não pode estar vazio. Tente novamente.");
                } else {
                    try {
                        System.out.println("Buscando dados para \"" + cidade + "\"...");
                        WeatherData dados = service.consultarClima(cidade);
                        view.exibir(dados);
                    } catch (WeatherApiException e) {
                        view.exibirErro(e.getMessage());
                    }
                }

                System.out.print("Deseja consultar outra cidade? (s/n): ");
                String resposta = scanner.nextLine().trim().toLowerCase();
                continuar = resposta.equals("s") || resposta.equals("sim");
            }
        }

        System.out.println("\nEncerrando o aplicativo. Até logo!");
    }
}