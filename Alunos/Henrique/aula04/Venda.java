package fag;

import java.time.LocalDate;
//import java.time.format.DateTimeFormatter;

public class Venda {

    double total;
    int qtd;
    double valor;
    double desconto;
    LocalDate data;

    public Venda(double total, double valor, int qtd, double desconto, LocalDate data) {
        this.total = total;
        this.valor = valor;
        this.qtd = qtd;
        this.desconto = desconto;
        this.data = data;
    }

}