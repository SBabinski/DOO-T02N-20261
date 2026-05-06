package objetos;

import java.util.ArrayList;
import java.util.Date;

public class Pedido {

    private int id;
    private Date dataCriacao;
    private Date dataPagamento;
    private Date dataVencimentoReserva;
    private Cliente cliente;
    private Vendedor vendedor;
    private Loja loja;
    private ArrayList<Item> itens = new ArrayList<>();

    public Pedido(int id, Cliente cliente, Vendedor vendedor, Loja loja) {
        this.id = id;
        this.cliente = cliente;
        this.vendedor = vendedor;
        this.loja = loja;
        this.dataCriacao = new Date();
    }

    public void adicionarItem(Item item) {
        itens.add(item);
    }

    public double calcularValorTotal() {
        double total = 0;
        for (Item i : itens) {
            total += i.getValor();
        }
        return total;
    }

    public void gerarDescricaoVenda() {
        System.out.println("Data: " + dataCriacao);
        System.out.println("Total: R$" + calcularValorTotal());
    }

    public Date getDataCriacao() {
        return dataCriacao;
    }

    public Date getDataVencimentoReserva() {
        return dataVencimentoReserva;
    }

    public void setDataVencimentoReserva(Date data) {
        this.dataVencimentoReserva = data;
    }

    public void setDataPagamento(Date data) {
        this.dataPagamento = data;
    }
}