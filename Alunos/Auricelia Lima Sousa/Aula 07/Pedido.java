import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Pedido{

    int id;
    Date dataCriacao;
    Date dataPagamento;
    Date dataVencimentoReserva;
    Cliente cliente;   
    Vendedor vendedor; 
    Loja loja;

   ArrayList<Item> itens = new ArrayList<>();
    public Pedido(int id, Date dataCriacao, Date dataVencimentoReserva, Cliente cliente, Vendedor vendedor, Loja loja) {
        this.id = id;
        this.dataCriacao = dataCriacao;
        this.dataVencimentoReserva = dataVencimentoReserva;
        this.cliente = cliente;
        this.vendedor = vendedor;
        this.loja = loja;
    }
    public double calcularValorTotal() {
        double total = 0;
        for (Item item : itens) {
            total += item.valor;
        }
        return total;
    }

    public void gerarDescricaoVenda() {
    SimpleDateFormat filtro = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

    System.out.println("Resumo do pedido");
    System.out.println("Data de Criacao: " + filtro.format(this.dataCriacao));
    System.out.println("Valor Total    : R$ " + calcularValorTotal());
    System.out.println();
}





}