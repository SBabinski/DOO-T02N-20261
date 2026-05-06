
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Venda {

    private int quantidade;
    private double preco;
    private double desconto;
    private double total;
    private LocalDate data;

    public Venda(int quantidade, double preco, double desconto, double total, LocalDate data) {

        this.quantidade = quantidade;
        this.preco = preco;
        this.desconto = desconto;
        this.total = total;
        this.data = data;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public double getTotal() {
        return total;
    }

    public double getDesconto() {
        return desconto;
    }

    public double getPreco() {
        return preco;
    }

    public LocalDate getData() {
        return data;
    }

    public void mostrarVendas() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String dataFormatada = data.format(formatter);

        System.out.println("Quantidade: " + quantidade + " | Preço: R$ " + preco
                + " | Desconto: R$ " + desconto + " | Total: R$ " + total + " | Data: " + dataFormatada);
    }

}
