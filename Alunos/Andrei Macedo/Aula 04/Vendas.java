import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Vendas {
    private double desconto;
    private int qntd;
    private double valor;
    private LocalDate horas;
    private double valor_total;

    public Vendas(){

    }

    public double getValor_total() {
        return valor_total;
    }

    public void setValor_total(double valor_total) {
        this.valor_total = valor_total;
    }

    public int getQntd() {
        return qntd;
    }

    public void setQntd(int qntd) {
        if (qntd > 0) {
            this.qntd = qntd;
        }
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        if (valor > 0) {
            this.valor = valor;
        }
    }

    public LocalDate getHoras() {
        return horas;
    }

    public void setHoras(LocalDate horas) {
        this.horas = horas;
    }

    public Double getDesconto() {
        return desconto;
    }

    public void setDesconto(Double desconto) {
        this.desconto = desconto;
    }

    @Override
    public String toString() {
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        String horarioFormatado = horas.format(formato);

        return "Vendas [desconto = " + desconto + 
                ", Quantidade de itens = " + qntd + 
                ", Valor do produto = " + valor + 
                ", Data =" + horarioFormatado + 
                ", valor_total = " + valor_total + "]";
    }
}
