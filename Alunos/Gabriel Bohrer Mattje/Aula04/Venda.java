package Aula04;

import java.time.LocalDate;

public class Venda {

    int quantidade;
    double valor;
    double desconto;
    double total;
    LocalDate data;

    public Venda(int quantidade, double valor, double desconto, double total, LocalDate data) {
        this.quantidade = quantidade;
        this.valor = valor;
        this.desconto = desconto;
        this.total = total;
        this.data = data;
    }
}