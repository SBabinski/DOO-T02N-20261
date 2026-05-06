import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Venda {

    private int quantidade;
    private double valorBruto;
    private double desconto;
    private double valorFinal;
    private LocalDate data;

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public Venda(int quantidade, double valorBruto, double desconto, double valorFinal, LocalDate data) {
        this.quantidade = quantidade;
        this.valorBruto = valorBruto;
        this.desconto = desconto;
        this.valorFinal = valorFinal;
        this.data = data;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public double getValorBruto() {
        return valorBruto;
    }

    public double getDesconto() {
        return desconto;
    }

    public double getValorFinal() {
        return valorFinal;
    }

    public LocalDate getData() {
        return data;
    }

    public String getDataFormatada() {
        return data.format(FORMATTER);
    }

    @Override
    public String toString() {
        return "Data: " + data.format(FORMATTER)
                + " | Quantidade: " + quantidade
                + " | Bruto: R$ " + valorBruto
                + " | Desconto: R$ " + desconto
                + " | Final: R$ " + valorFinal;
    }
}
