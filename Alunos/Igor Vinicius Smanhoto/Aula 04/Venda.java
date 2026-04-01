package fag.objects;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Venda {

    private String nomePlanta;
    private int quantidade;
    private double valorTotal;
    private double desconto;
    private double valorFinal;
    private LocalDate dataVenda;

    public Venda(String nomePlanta, int quantidade, double valorTotal, double desconto, double valorFinal, LocalDate dataVenda) {
        this.nomePlanta = nomePlanta;
        this.quantidade = quantidade;
        this.valorTotal = valorTotal;
        this.desconto = desconto;
        this.valorFinal = valorFinal;
        this.dataVenda = dataVenda;
    }

    public String getNomePlanta() {
        return nomePlanta;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public double getDesconto() {
        return desconto;
    }

    public double getValorFinal() {
        return valorFinal;
    }

    public LocalDate getDataVenda() {
        return dataVenda;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        return String.format(
                "Planta: %s | Quantidade: %d | Valor bruto: R$ %.2f | Desconto: R$ %.2f | Valor final: R$ %.2f | Data: %s",
                nomePlanta,
                quantidade,
                valorTotal,
                desconto,
                valorFinal,
                dataVenda.format(formatter)
        );
    }
}