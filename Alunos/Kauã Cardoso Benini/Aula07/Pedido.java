import java.util.ArrayList;
import java.util.Date;

public class Pedido {

    int id;
    Date dataCriacao;
    Date dataPagamento;
    Date dataVencimentoReserva;

    Cliente cliente;
    Vendedor vendedor;
    Loja loja;

    ArrayList<Item> itens = new ArrayList<>();

    public Pedido(int id, Date dataCriacao, Date dataVencimentoReserva,
                  Cliente cliente, Vendedor vendedor, Loja loja){
        this.id = id;
        this.dataCriacao = dataCriacao;
        this.dataVencimentoReserva = dataVencimentoReserva;
        this.cliente = cliente;
        this.vendedor = vendedor;
        this.loja = loja;
    }

    public double calcularValorTotal(){
        double total = 0;
        for(Item i : itens){
            total += i.valor;
        }
        return total;
    }

    public void gerarDescricaoVenda(){
        System.out.println("Data: " + dataCriacao);
        System.out.println("Total: R$ " + calcularValorTotal());
    }
}