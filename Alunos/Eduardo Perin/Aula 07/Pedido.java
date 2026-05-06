import java.time.LocalDate;
import java.util.ArrayList;

public class Pedido {
    private String id;
    private LocalDate dataCriacao;
    private LocalDate dataPagamento;
    private LocalDate dataVencimentoReserva;
    private Cliente cliente;
    private Vendedor vendedor;
    private Loja loja;
    private ArrayList<Item> itens = new ArrayList<>();

    


    public Pedido(String id, LocalDate dataCriacao, LocalDate dataPagamento, LocalDate dataVencimentoReserva,
            Cliente cliente, Vendedor vendedor, Loja loja, ArrayList<Item> itens) {
        this.id = id;
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
        for (Item item : itens) {
            total += item.getValor();
        }
        return total;
    }

    public void gerarDescricaoVenda() {
        System.out.println("PEDIDO ID: " + id);
        System.out.println("DATA DE CRIAÇÃO: " + dataCriacao);
        System.out.println("VALOR TOTAL: R$ " + calcularValorTotal());
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

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public LocalDate getDataCriacao() { return dataCriacao; }
    public void setDataCriacao(LocalDate dataCriacao) { this.dataCriacao = dataCriacao; }

    public LocalDate getDataPagamento() { return dataPagamento; }
    public void setDataPagamento(LocalDate dataPagamento) { this.dataPagamento = dataPagamento; }

    public LocalDate getDataVencimentoReserva() { return dataVencimentoReserva; }
    public void setDataVencimentoReserva(LocalDate dataVencimentoReserva) { this.dataVencimentoReserva = dataVencimentoReserva; }

    public ArrayList<Item> getItens() { return itens; }
    public void setItens(ArrayList<Item> itens) { this.itens = itens; }
}