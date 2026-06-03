package fag;

public class Venda {

    double total;
    int qtd;
    double valor;
    double desconto;

    public Venda(double total, double valor, int qtd, double desconto) {
        this.total = total;
        this.valor = valor;
        this.qtd = qtd;
        this.desconto = desconto;
    }

}
