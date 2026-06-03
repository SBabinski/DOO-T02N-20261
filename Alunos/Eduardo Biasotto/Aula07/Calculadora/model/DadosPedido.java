package Calculadora.model;

import java.util.ArrayList;
import java.util.Date;

public class DadosPedido {
    private int id;
    private Date dataCriacao;
    private Date dataPagamento;
    private Date dataVencimentoReserva;
    private DadosCliente cliente;
    private DadosVendedor vendedor;
    private DadosLoja loja;
    private ArrayList<DadosItem> itens;

    public DadosPedido(int id, Date dataCriacao, Date dataPagamento, Date dataVencimentoReserva, DadosCliente cliente, DadosVendedor vendedor, DadosLoja loja) {
        this.id = id;
        this.dataCriacao = dataCriacao;
        this.dataPagamento = dataPagamento;
        this.dataVencimentoReserva = dataVencimentoReserva;
        this.cliente = cliente;
        this.vendedor = vendedor;
        this.loja = loja;
        this.itens = new ArrayList<>();
    }

    public double calcularValorTotal() {
        double total = 0;
        for (DadosItem item : itens) {
            total += item.getValor();
        }
        return total;
    }

    public void gerarDescricaoVenda() {
        System.out.println("\n======= DESCRIÇÃO DO PEDIDO =======");
        System.out.println("ID: " + id);
        System.out.println("Data de criação: " + dataCriacao);
        System.out.println("Valor total: R$ " + calcularValorTotal());
    }

    public int getId() {
        return id;
    }

    public Date getDataCriacao() {
        return dataCriacao;
    }

    public Date getDataVencimentoReserva() {
        return dataVencimentoReserva;
    }

    public DadosCliente getCliente() {
        return cliente;
    }

    public DadosVendedor getVendedor() {
        return vendedor;
    }

    public DadosLoja getLoja() {
        return loja;
    }

    public ArrayList<DadosItem> getItens() {
        return itens;
    }
}