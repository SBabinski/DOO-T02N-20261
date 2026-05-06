import java.util.Date;

public class Pedido {

    int id;
    Date dataCriacao;
    Date dataPagamento;
    Date dataVencimentoReserva;

    Cliente cliente;
    Vendedor vendedor;
    String loja;

    Item[] itens;

    public Pedido(int id, Cliente cliente, Vendedor vendedor, String loja, Item[] itens){
        this.id = id;
        this.cliente = cliente;
        this.vendedor = vendedor;
        this.loja = loja;
        this.itens = itens;

        this.dataCriacao = new Date();

        // vencimento +2 dias
        this.dataVencimentoReserva = new Date(System.currentTimeMillis() + 2*24*60*60*1000);
    }

    public double calcularValorTotal(){
        double total = 0;

        for(int i=0;i<itens.length;i++){
            total = total + itens[i].valor;
        }

        return total;
    }

    public void gerarDescricaoVenda(){
        System.out.println("data: " + dataCriacao + " total: " + calcularValorTotal());
    }
}