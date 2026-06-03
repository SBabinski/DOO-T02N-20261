package weather;

/**
 * Ponto de entrada do aplicativo de clima.
 * Lê a chave da API da variável de ambiente VISUALCROSSING_API_KEY e inicia o servidor HTTP.
 */
public class Main {

    public static void main(String[] args) {
        String apiKey = System.getenv("VISUALCROSSING_API_KEY");

        if (apiKey == null || apiKey.isBlank()) {
            System.err.println("Erro: variável de ambiente VISUALCROSSING_API_KEY não definida.");
            System.err.println("Defina-a antes de executar:");
            System.err.println("  export VISUALCROSSING_API_KEY=sua_chave_aqui");
            System.exit(1);
        }

        int port = 8888;
        String portEnv = System.getenv("PORT");
        if (portEnv != null && !portEnv.isBlank()) {
            try { port = Integer.parseInt(portEnv); } catch (NumberFormatException ignored) {}
        }

        try {
            WeatherServer server = new WeatherServer(apiKey, port);
            server.start();
        } catch (Exception e) {
            System.err.println("Erro ao iniciar o servidor: " + e.getMessage());
            System.exit(1);
        }
    }
}
