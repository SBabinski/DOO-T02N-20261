import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Venda {

    private double valor_unitario;
    private int quantidade_venda;
    private double valor_total;
    private LocalDate data;
    private double desconto;
    private double troco;

    

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public Venda(){

    }

    public double getValor_unitario() {
        return valor_unitario;
    }

    public void setValor_unitario(double valor_unitario) {
        this.valor_unitario = valor_unitario;
    }

    public int getQuantidade_venda() {
        return quantidade_venda;
    }

    public void setQuantidade_venda(int quantidade_venda) {
        this.quantidade_venda = quantidade_venda;
    }

    public double getValor_total() {
        return valor_total;
    }

    public void setValor_total(double valor_total) {
        this.valor_total = valor_total;
    }

    public double getDesconto() {
        return desconto;
    }

    public void setDesconto(double desconto) {
        this.desconto = desconto;
    }

    @Override
    public String toString() {
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String horarioFormatado = data.format(formato);

        return "Venda [Valor unitário = " + valor_unitario +
                ", Quantidade de itens vendidos = " + quantidade_venda +
                ", Desconto de: = " + String.format ("%.2f", desconto) +
                ", Valor total = " + String.format("R$ %.2f", valor_total) +
                ", Data = " + horarioFormatado + 
                ", Troco = " + String.format("R$ %.2f", troco) + "]";
    }

    public double getTroco() {
        return troco;
    }

    public void setTroco(double troco) {
        this.troco = troco;
    }
}
