import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ClimaDados {
    private String cidade;
    private String estado;
    private String data;
    private double temperaturaAtual;
    private double temperaturaMaxima;
    private double temperaturaMinima;
    private double umidadeAr;
    private String condicaoTempo;
    private double precipitacao;
    private double velocidadeVento;
    private double direcaoVento;
    private String icone; 

    public ClimaDados(String cidade, String estado, String data, double temperaturaAtual, double temperaturaMaxima, double temperaturaMinima, double umidadeAr, String condicaoTempo, double precipitacao, double velocidadeVento, double direcaoVento, String icone) {
        this.cidade = cidade;
        this.estado = estado;
        this.data = data;
        this.temperaturaAtual = temperaturaAtual;
        this.temperaturaMaxima = temperaturaMaxima;
        this.temperaturaMinima = temperaturaMinima;
        this.umidadeAr = umidadeAr;
        this.condicaoTempo = condicaoTempo;
        this.precipitacao = precipitacao;
        this.velocidadeVento = velocidadeVento;
        this.direcaoVento = direcaoVento;
        this.icone = icone; // <-- NOVO CAMPO
    }

    public String getIcone() { return icone; }
    public void setIcone(String icone) { this.icone = icone; }

    public String getCidade() { return cidade; }
    public String getEstado() { return estado; }
    public String getData() { return data; }
    public double getTemperaturaAtual() { return temperaturaAtual; }
    public double getTemperaturaMaxima() { return temperaturaMaxima; }
    public double getTemperaturaMinima() { return temperaturaMinima; }
    public double getUmidadeAr() { return umidadeAr; }
    public String getCondicaoTempo() { return condicaoTempo; }
    public double getPrecipitacao() { return precipitacao; }
    public double getVelocidadeVento() { return velocidadeVento; }
    public double getDirecaoVento() { return direcaoVento; }
}
