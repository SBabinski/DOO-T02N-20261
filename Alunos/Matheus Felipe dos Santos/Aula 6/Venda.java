import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Venda {

    int quantidade;
    double total;
    double desconto;
    LocalDate data;

    public Venda(int quantidade, LocalDate data) {
        this.quantidade = quantidade;
        this.data = data;
    }

    public void calcularTotal(double precoUnitario) {
        total = quantidade * precoUnitario;

        if (quantidade > 10) {
            desconto = total * 0.05;
            total -= desconto;
        }
    }

    public void apresentar(DateTimeFormatter formatter) {
        System.out.println("Data: " + data.format(formatter) +
                " | Qtd: " + quantidade +
                " | Total: R$ " + total +
                " | Desconto: R$ " + desconto);
    }
}