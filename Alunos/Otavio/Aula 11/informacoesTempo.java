public class informacoesTempo {
    private String nomeCidade;
    private double temperaturaAtual;
    private double temperaturaMaxima;
    private double temperaturaMinima;
    private double umidade;
    private String condicoes;
    private double precipitacao;
    private double velocidadeVento;
    private double direcaoVento;

    public informacoesTempo(String nomeCidade, double temperaturaAtual, double temperaturaMaxima, double temperaturaMinima,
                            double umidade, String condicoes, double precipitacao,
                            double velocidadeVento, double direcaoVento) {
        this.nomeCidade = nomeCidade;
        this.temperaturaAtual = temperaturaAtual;
        this.temperaturaMaxima = temperaturaMaxima;
        this.temperaturaMinima = temperaturaMinima;
        this.umidade = umidade;
        this.condicoes = condicoes;
        this.precipitacao = precipitacao;
        this.velocidadeVento = velocidadeVento;
        this.direcaoVento = direcaoVento;
    }

    public String getNomeCidade() { return nomeCidade; }
    public double getTemperaturaAtual() { return temperaturaAtual; }
    public double getTemperaturaMaxima() { return temperaturaMaxima; }
    public double getTemperaturaMinima() { return temperaturaMinima; }
    public double getUmidade() { return umidade; }
    public String getCondicoes() { return condicoes; }
    public double getPrecipitacao() { return precipitacao; }
    public double getVelocidadeVento() { return velocidadeVento; }
    public double getDirecaoVento() { return direcaoVento; }
}