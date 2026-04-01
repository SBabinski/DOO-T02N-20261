import java.time.LocalDate;

public class Venda {

    int quantidade;
    double valorTotal;
    double valorDesconto;
    double valorFinal;
    LocalDate data;

    public Venda(int quantidade, double valorTotal, double valorDesconto, double valorFinal, LocalDate data){
        this.quantidade = quantidade;
        this.valorTotal = valorTotal;
        this.valorDesconto = valorDesconto;
        this.valorFinal = valorFinal;
        this.data = data;
    }
}
