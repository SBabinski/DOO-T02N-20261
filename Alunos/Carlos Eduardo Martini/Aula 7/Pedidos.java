import java.util.Date;
import java.util.ArrayList;

public class Pedidos {
    public int id;
    public Cliente cliente;
    public Vendedor vendedor;
    public Loja loja;
    public ArrayList<Item> itens;
    public Date dataCriacao;
    public Date dataPagamento;
    public Date dataVencimento;

    public Pedidos(int id, Cliente cliente, Vendedor vendedor, Loja loja, ArrayList<Item> itens, Date dataCriacao, Date dataVencimento) {
        this.id = id;
        this.cliente = cliente;
        this.vendedor = vendedor;
        this.loja = loja;
        this.itens = itens;
        this.dataCriacao = dataCriacao;
        this.dataVencimento = dataVencimento;
    }

    public void impriPedido() {
        System.out.println("Pedido ID: " + id);
    }
}