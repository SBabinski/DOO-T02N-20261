package objetos;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Pedido {
    private static int contador =0;
    private int idPedido;
    private LocalDate dataCriacao;
    private LocalDate dataPagamento;
    private LocalDate dataVencimentoReserva;
    private Cliente cliente;
    private Vendedor vendedor;
    private Loja loja;
    private List<Item> itens;

    public Pedido(LocalDate dataCriacao, LocalDate dataPagamento, LocalDate dataVencimentoReserva, Cliente cliente, Vendedor vendedor, Loja loja, List<Item> itens) {
        this.idPedido = ++contador;
        this.dataCriacao = dataCriacao;
        this.dataPagamento = dataPagamento;
        this.dataVencimentoReserva = dataVencimentoReserva;
        this.cliente = cliente;
        this.vendedor = vendedor;
        this.loja = loja;
        this.itens = itens;
    }
    
    public double calcularValorTotal() {
        double total = 0;
        if (itens != null) {
            for (Item item : itens) {
                total += item.getValor();
            }
        }
        return total;
    }

    public void gerarDescricaoVenda() {
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        System.out.printf("Pedido criado em: %s | Valor Total: R$ %.2f\n", 
            dataCriacao.format(formato), calcularValorTotal());
    }


    public int getIdPedido() {
        return idPedido;
    }

    public LocalDate getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDate dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public LocalDate getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(LocalDate dataPagamento) {
        this.dataPagamento = dataPagamento;
    }

    public LocalDate getDataVencimentoReserva() {
        return dataVencimentoReserva;
    }

    public void setDataVencimentoReserva(LocalDate dataVencimentoReserva) {
        this.dataVencimentoReserva = dataVencimentoReserva;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Vendedor getVendedor() {
        return vendedor;
    }

    public void setVendedor(Vendedor vendedor) {
        this.vendedor = vendedor;
    }

    public Loja getLoja() {
        return loja;
    }

    public void setLoja(Loja loja) {
        this.loja = loja;
    }

    public List<Item> getItens() {
        return itens;
    }

    public void setItens(List<Item> itens) {
        this.itens = itens;
    }
}
