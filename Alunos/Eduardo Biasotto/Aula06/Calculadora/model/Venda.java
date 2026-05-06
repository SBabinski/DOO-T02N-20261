package Calculadora.model;

import java.time.LocalDate;

public class Venda {

    private Loja loja;
    private Vendedor vendedor;
    private Cliente cliente;
    private double valor;
    private LocalDate data;

    public Venda(Loja loja, Vendedor vendedor, Cliente cliente, double valor) {
        this.loja = loja;
        this.vendedor = vendedor;
        this.cliente = cliente;
        this.valor = valor;
        this.data = LocalDate.now();
    }

    public void apresentarse() {
        System.out.println("\n======= VENDA DA LOJA " + loja.getNomeFantasia() + " =======");
        System.out.println("\nLoja: " + loja.getNomeFantasia());
        System.out.println("Vendedor: " + vendedor);
        System.out.println("Cliente: " + cliente);
        System.out.println("Valor: R$ " + valor);
        System.out.println("Data: " + data);
    }

    public Loja getLoja() {
        return loja;
    }

    public Vendedor getVendedor() {
        return vendedor;
    }

    public LocalDate getData() {
        return data;
    }

    public double getValor() {
        return valor;
    }
}