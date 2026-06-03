package Aula06;

import java.time.LocalDate;

public class Venda {

    private int quantidade;
    private double valor;
    private double desconto;
    private double total;
    private LocalDate data;

    public Venda(int quantidade, double valor, double desconto, double total, LocalDate data) {
        this.quantidade = quantidade;
        this.valor = valor;
        this.desconto = desconto;
        this.total = total;
        this.data = data;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public double getValor() {
        return valor;
    }

    public double getDesconto() {
        return desconto;
    }

    public double getTotal() {
        return total;
    }

    public LocalDate getData() {
        return data;
    }
}