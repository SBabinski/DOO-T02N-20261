package Calculadora.model;

import java.time.LocalDate;
import java.util.Date;

public class DadosVenda {

    private DadosLoja loja;
    private DadosVendedor vendedor;
    private DadosCliente cliente;
    private DadosItem item;
    private double valor;
    private LocalDate data;
    private int quantidade;
    private double desconto;
    private Date dataPagamento;

    public DadosVenda(DadosLoja loja, DadosVendedor vendedor, DadosCliente cliente, DadosItem item, double valor, int quantidade, double desconto) {
        this.loja = loja;
        this.vendedor = vendedor;
        this.cliente = cliente;
        this.item = item;
        this.valor = valor;
        this.data = LocalDate.now();
        this.quantidade = quantidade;
        this.desconto = desconto;
        this.dataPagamento = new Date();
    }

    public void apresentarse() {
        System.out.println("\n======= VENDA DA LOJA " + loja.getNomeFantasia() + " =======");
        System.out.println("Loja: " + loja.getNomeFantasia());
        System.out.println("Vendedor: " + vendedor);
        System.out.println("Cliente: " + cliente);
        System.out.println("Produto: " + item.getNome() + " | Qtd: " + quantidade);
        System.out.println("Desconto: R$ " + desconto);
        System.out.println("Valor final: R$ " + valor);
        System.out.println("Data da venda: " + data);
        System.out.println("Data do pagamento: " + dataPagamento);
    }

    public DadosLoja getLoja() { return loja; }
    public DadosVendedor getVendedor() { return vendedor; }
    public LocalDate getData() { return data; }
    public double getValor() { return valor; }
}