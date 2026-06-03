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

    public Pedido(int id, Date dataCriacao, Date dataPagamento, Date dataVencimentoReserva, Cliente cliente, Vendedor vendedor, Loja loja) {
        this.id = id;
        this.dataCriacao = dataCriacao;
        this.dataPagamento = dataPagamento;
        this.dataVencimentoReserva = dataVencimentoReserva;
        this.cliente = cliente;
        this.vendedor = vendedor;
        this.loja = loja;
    }

    public double calcularValorTotal() {
        double total = 0;
        for (Item item : itens) {
            total += item.getValor();
        }
        return total;
    }

    public void gerarDescricaoVenda() {
        System.out.println("Pedido #" + id + " | Criado em: " + dataCriacao + " | Valor total: R$" + calcularValorTotal());
    }

    public void adicionarItem(Item item) { itens.add(item); }

    public int getId() { return id; }
    public Date getDataCriacao() { return dataCriacao; }
    public Date getDataPagamento() { return dataPagamento; }
    public Date getDataVencimentoReserva() { return dataVencimentoReserva; }
    public Cliente getCliente() { return cliente; }
    public Vendedor getVendedor() { return vendedor; }
    public Loja getLoja() { return loja; }
    public ArrayList<Item> getItens() { return itens; }
}
