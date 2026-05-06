import java.util.Date;
import java.util.ArrayList;
import java.util.List;

public class Pedido {
    private int id;
    private Date dataCriacao;
    private Date dataPagamento;
    private Date dataVencimentoReserva;
    private Cliente cliente;
    private Pessoa funcionario;
    private Loja loja;
    private List<Item> itens;

    public Pedido(int id, Cliente cliente, Pessoa funcionario, Loja loja, List<Item> itens) {
        this.id = id;
        this.dataCriacao = new Date();
        this.dataVencimentoReserva = new Date(dataCriacao.getTime() + (7 * 24 * 60 * 60 * 1000L));
        this.cliente = cliente;
        this.funcionario = funcionario;
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
        System.out.println("Pedido #" + id + " criado em " + dataCriacao + 
                          " | Total: R$ " + String.format("%.2f", calcularValorTotal()));
    }

    public boolean isVencido() {
        return new Date().after(dataVencimentoReserva);
    }

    public Date getDataVencimentoReserva() { 
        return dataVencimentoReserva; 
    }
}