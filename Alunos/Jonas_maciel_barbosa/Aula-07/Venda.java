import java.util.Date;

public class Venda {

    double total;
    int qtd;
    double valor;
    double desconto;
    Date data;

    public Venda(double total, double valor, int qtd, double desconto, Date data) {
        if (total > 0) {
            this.total = total;
        } else {
            System.out.println("Valor total inválido. Verifique e tente novamente.");
        }
        if (valor > 0) {
            this.valor = valor;
        } else {
            System.out.println("Valor unitário inválido. Verifique e tente novamente.");
        }
        this.qtd = qtd;
        this.desconto = desconto;
        this.data = data;
    }
}
