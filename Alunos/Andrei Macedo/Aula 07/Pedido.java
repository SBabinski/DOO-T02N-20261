import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Pedido {

    private int id;
    private LocalDate dataCriacao;
    private LocalDate dataPagamento;
    private LocalDate dataVencimentoReserva;
    private Cliente cliente;
    private Vendedor vendedor;
    private Loja loja;
    private static List<Item> itens = new ArrayList<>();
    private double valor;

    public Pedido(int id, LocalDate dataCriacao, LocalDate dataPagamento, LocalDate dataVencimentoReserva,
    Cliente cliente, Vendedor vendedor, double valor) {
        this.id = id;
        this.dataCriacao = dataCriacao;
        this.dataPagamento = dataPagamento;
        this.dataVencimentoReserva = dataVencimentoReserva;
        this.cliente = cliente;
        this.vendedor = vendedor;
        this.valor = valor;
    }

    public Pedido() {
        //TODO Auto-generated constructor stub
    }

    static void exibir_itens() {
        Item item1 = new Item(102030, "Hortênsia", "Arbusto", 100.00);
        Item item2 = new Item(405060, "Echeveria", "Suculenta", 60.00);
        Item item3 = new Item(708090, "Rosa", "Flores", 120.00);
        itens.add(item1);
        itens.add(item2);
        itens.add(item3);
        for (int x = 0; x < itens.size(); x++){
            System.out.println("Posição: " + (x + 1));
            itens.get(x).gerarDescricao();
        }
    }

    public double calcularValorTotal(int pedidoEscolhido, int qntd) {
        double valor = itens.get(pedidoEscolhido - 1).getValor();
        double total = valor * qntd;
        return total;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public void gerarDescricaoVenda() {
        System.out.println("\n");
        System.out.println("Data de criação do pedido: " + getDataCriacao());
        System.out.println("Valor do pedido: " + getValor());
        System.out.println("\n");
    }

    public static List<Item> getItens() {
        return itens;
    }

    public static void setItens(List<Item> itens) {
        Pedido.itens = itens;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }
}
