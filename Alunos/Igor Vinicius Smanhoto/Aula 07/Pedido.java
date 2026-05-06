package objects;

import java.time.LocalDate;
import java.util.ArrayList;

public class Pedido {

    private int id;
    private LocalDate dataCriacao;
    private LocalDate dataPagamento;
    private LocalDate dataVencimentoReserva;

    private Cliente cliente;
    private Vendedor vendedor;
    private Loja loja;

    private ArrayList<Item> itens = new ArrayList<>();

    public Pedido(int id, LocalDate dataCriacao, LocalDate dataVencimentoReserva,
                  Cliente cliente, Vendedor vendedor, Loja loja) {

        this.id = id;
        this.dataCriacao = dataCriacao;
        this.dataVencimentoReserva = dataVencimentoReserva;
        this.cliente = cliente;
        this.vendedor = vendedor;
        this.loja = loja;
    }

    public void adicionarItem(Item item) {
        itens.add(item);
    }

    public double calcularValorTotal() {
        return itens.stream().mapToDouble(Item::getValor).sum();
    }

    public void gerarDescricaoVenda() {
        System.out.println("Pedido criado em: " + dataCriacao);
        System.out.println("Valor total: R$ " + calcularValorTotal());
    }

    public LocalDate getDataVencimentoReserva() {
        return dataVencimentoReserva;
    }

    public void setDataPagamento(LocalDate dataPagamento) {
        this.dataPagamento = dataPagamento;
    }
}