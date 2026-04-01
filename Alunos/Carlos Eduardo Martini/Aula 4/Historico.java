import java.time.LocalDate;

public class Historico {
    public int quantPlant;
    public double resultTotal;
    public double valorDesc;
    public LocalDate hoje;


    public Historico(int quantPlant, double resultTotal, double valorDesc, LocalDate hoje) {
        this.quantPlant = quantPlant;
        this.resultTotal = resultTotal;
        this.valorDesc = valorDesc;
        this.hoje = hoje;
    }


}
