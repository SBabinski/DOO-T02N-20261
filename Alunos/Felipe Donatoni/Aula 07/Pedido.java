import java.util.Date;
import java.util.ArrayList;
public class Pedido {
    
    int id;
    Date dataCriacao;
    Date dataPagamento;
    Date dataVencimentoReserva;

    Pessoa cliente;
    Pessoa vendedor;
    String Loja;

    ArrayList <Item> itens = new ArrayList<>();

    public Pedido (int id, Date dataCriacao, Date dataPagamento, Date dataVencimentoReserva, Pessoa cliente, Pessoa vendedor, String Loja) {
        this.id = id;
        this.dataCriacao = dataCriacao;
        this.dataPagamento = dataPagamento;
        this.dataVencimentoReserva = dataVencimentoReserva;
        this.cliente = cliente;
        this.vendedor = vendedor;
        this.Loja = Loja;
    }
    public double calcularValorTotal() {
        double total = 0;
        for (Item i : itens) {
            total = total + i.valor;
        }
        return total;
    }
    public void gerarDescricaoVenda() {
        System.out.println("Data do pedido: " + dataCriacao);
        System.out.println("Valor total: R$ " + calcularValorTotal());
    }
}
