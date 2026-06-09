public class WeatherView {

    private static final String LINHA =
        "============================================================";

    public void exibir(WeatherData dados) {
        System.out.println("\n" + LINHA);
        System.out.println("  CONSULTA DE CLIMA - Visual Crossing Weather API");
        System.out.println(LINHA);
        System.out.printf("  Cidade consultada : %s%n", dados.getCidade());
        System.out.printf("  Endereço resolvido: %s%n", dados.getEnderecoResolvido());
        System.out.println(LINHA);

        System.out.println("  TEMPERATURA");
        System.out.printf("    Atual   : %.1f °C%n", dados.getTemperaturaAtual());
        System.out.printf("    Máxima  : %.1f °C%n", dados.getTemperaturaMaxima());
        System.out.printf("    Mínima  : %.1f °C%n", dados.getTemperaturaMinima());

        System.out.println("\n  ATMOSFERA");
        System.out.printf("    Umidade     : %.1f %%%n", dados.getUmidade());
        System.out.printf("    Condição    : %s%n",      dados.getCondicao());
        System.out.printf("    Precipitação: %.1f mm%n", dados.getPrecipitacao());

        System.out.println("\n  VENTO");
        System.out.printf("    Velocidade: %.1f km/h%n", dados.getVelocidadeVento());
        System.out.printf("    Direção   : %.0f° (%s)%n",
            dados.getDirecaoVento(), dados.getDirecaoVentoCardinal());

        System.out.println(LINHA + "\n");
    }

    public void exibirErro(String mensagem) {
        System.err.println("\n[ERRO] " + mensagem + "\n");
    }
}