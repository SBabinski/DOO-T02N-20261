public class PrevisaoResultado {

    private double temperaturaAtual;
    private double temperaturaMaxima;
    private double temperaturaMinima;
    private int umidade;
    private String condicao;
    private double precipitacao;
    private double velocidadeVento;
    private double direcaoVento;
    private String icon;

    public PrevisaoResultado(double temperaturaAtual, double temperaturaMaxima, double temperaturaMinima,
                             int umidade, String condicao, double precipitacao,
                             double velocidadeVento, double direcaoVento, String icon) {

        setTemperaturaAtual(temperaturaAtual);
        setTemperaturaMaxima(temperaturaMaxima);
        setTemperaturaMinima(temperaturaMinima);
        setUmidade(umidade);
        setCondicao(condicao);
        setPrecipitacao(precipitacao);
        setVelocidadeVento(velocidadeVento);
        setDirecaoVento(direcaoVento);
        setIcon(icon);
    }

    // Getters
    public double getTemperaturaAtual() {
        return temperaturaAtual;
    }

    public double getTemperaturaMaxima() {
        return temperaturaMaxima;
    }

    public double getTemperaturaMinima() {
        return temperaturaMinima;
    }

    public int getUmidade() {
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

    public String getIcon() {
        return icon;
    }

    // Setters
    public void setTemperaturaAtual(double temperaturaAtual) {
        this.temperaturaAtual = temperaturaAtual;
    }

    public void setTemperaturaMaxima(double temperaturaMaxima) {
        this.temperaturaMaxima = temperaturaMaxima;
    }

    public void setTemperaturaMinima(double temperaturaMinima) {
        this.temperaturaMinima = temperaturaMinima;
    }

    public void setUmidade(int umidade) {
        this.umidade = umidade;
    }

    public void setCondicao(String condicao) {
        this.condicao = condicao;
    }

    public void setPrecipitacao(double precipitacao) {
        this.precipitacao = precipitacao;
    }

    public void setVelocidadeVento(double velocidadeVento) {
        this.velocidadeVento = velocidadeVento;
    }

    public void setDirecaoVento(double direcaoVento) {
        this.direcaoVento = direcaoVento;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}