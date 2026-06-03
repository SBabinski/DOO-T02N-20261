public class DadosClima {

    private final String cidade;
    private final String enderecoResolvido;
    private final double temperaturaAtual;
    private final double temperaturaMaxima;
    private final double temperaturaMinima;
    private final double umidade;
    private final String condicao;
    private final double precipitacao;
    private final double velocidadeVento;
    private final double direcaoVento;
    private final String direcaoCardinal;

    public DadosClima(String cidade, String enderecoResolvido,
                      double temperaturaAtual, double temperaturaMaxima, double temperaturaMinima,
                      double umidade, String condicao, double precipitacao,
                      double velocidadeVento, double direcaoVento, String direcaoCardinal) {
        this.cidade             = cidade;
        this.enderecoResolvido  = enderecoResolvido;
        this.temperaturaAtual   = temperaturaAtual;
        this.temperaturaMaxima  = temperaturaMaxima;
        this.temperaturaMinima  = temperaturaMinima;
        this.umidade            = umidade;
        this.condicao           = condicao;
        this.precipitacao       = precipitacao;
        this.velocidadeVento    = velocidadeVento;
        this.direcaoVento       = direcaoVento;
        this.direcaoCardinal    = direcaoCardinal;
    }

    public String getCidade()            { return cidade; }
    public String getEnderecoResolvido() { return enderecoResolvido; }
    public double getTemperaturaAtual()  { return temperaturaAtual; }
    public double getTemperaturaMaxima() { return temperaturaMaxima; }
    public double getTemperaturaMinima() { return temperaturaMinima; }
    public double getUmidade()           { return umidade; }
    public String getCondicao()          { return condicao; }
    public double getPrecipitacao()      { return precipitacao; }
    public double getVelocidadeVento()   { return velocidadeVento; }
    public double getDirecaoVento()      { return direcaoVento; }
    public String getDirecaoCardinal()   { return direcaoCardinal; }
}