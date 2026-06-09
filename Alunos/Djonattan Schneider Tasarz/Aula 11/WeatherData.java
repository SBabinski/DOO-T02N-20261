public class WeatherData {

    private String cidade;
    private String enderecoResolvido;
    private double temperaturaAtual;
    private double temperaturaMaxima;
    private double temperaturaMinima;
    private double umidade;
    private String condicao;
    private double precipitacao;
    private double velocidadeVento;
    private double direcaoVento;

    public WeatherData(String cidade,
                       String enderecoResolvido,
                       double temperaturaAtual,
                       double temperaturaMaxima,
                       double temperaturaMinima,
                       double umidade,
                       String condicao,
                       double precipitacao,
                       double velocidadeVento,
                       double direcaoVento) {
        this.cidade = cidade;
        this.enderecoResolvido = enderecoResolvido;
        this.temperaturaAtual = temperaturaAtual;
        this.temperaturaMaxima = temperaturaMaxima;
        this.temperaturaMinima = temperaturaMinima;
        this.umidade = umidade;
        this.condicao = condicao;
        this.precipitacao = precipitacao;
        this.velocidadeVento = velocidadeVento;
        this.direcaoVento = direcaoVento;
    }

    public String getCidade() {
        return cidade;
    }

    public String getEnderecoResolvido() {
        return enderecoResolvido;
    }

    public double getTemperaturaAtual() {
        return temperaturaAtual;
    }

    public double getTemperaturaMaxima() {
        return temperaturaMaxima;
    }

    public double getTemperaturaMinima() {
        return temperaturaMinima;
    }

    public double getUmidade() {
        return umidade;
    }

    public String getCondicao() {
        return condicao;
    }

    public double getPrecipitacao() {
        return precipitacao;
    }

    public double getVelocidadeVento() {
        return velocidadeVento;
    }

    public double getDirecaoVento() {
        return direcaoVento;
    }

    public String getDirecaoVentoCardinal() {
        String[] direcoes = {"N", "NE", "L", "SE", "S", "SO", "O", "NO"};
        int indice = (int) Math.round(direcaoVento / 45.0) % 8;
        return direcoes[indice];
    }

    @Override
    public String toString() {
        return String.format(
            "WeatherData{cidade='%s', tempAtual=%.1f°C, tempMax=%.1f°C, tempMin=%.1f°C, " +
            "umidade=%.1f%%, condicao='%s', precipitacao=%.1fmm, vento=%.1fkm/h %s}",
            cidade, temperaturaAtual, temperaturaMaxima, temperaturaMinima,
            umidade, condicao, precipitacao, velocidadeVento, getDirecaoVentoCardinal()
        );
    }
}