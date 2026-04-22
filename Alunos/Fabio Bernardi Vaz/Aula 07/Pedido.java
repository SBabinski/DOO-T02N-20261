package fag;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Pedido {
    private int id;
    private Date dataCriacao;
    private Date dataPagamento;
    private Date dataVencimentoReserva;
    private Cliente cliente;
    private Vendedor vendedor;
    private Loja loja;
    private List<Item> itens;

    public Pedido(int id, Date dataCriacao, Date dataVencimentoReserva, Cliente cliente, Vendedor vendedor, Loja loja, List<Item> itens) {
        this.id = id;
        this.dataCriacao = dataCriacao;
        this.dataVencimentoReserva = dataVencimentoReserva;
        this.cliente = cliente;
        this.vendedor = vendedor;
        this.loja = loja;
        this.itens = itens;
    }

    public double calcularValorTotal() {
        double total = 0;
        if (itens != null) {
            for (Item item : itens) { total += item.getValor(); }
        }
        return total;
    }

    public void gerarDescricaoVenda() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        System.out.println("\n=== RESUMO DO PEDIDO #" + id + " ===");
        System.out.println("Data de Criação: " + sdf.format(dataCriacao));
        System.out.println("Vendedor: " + vendedor.getNome() + " | Cliente: " + cliente.getNome());
        System.out.printf("Valor Total: R$ %.2f%n", calcularValorTotal());
        System.out.println("===============================");
    }

    public void setDataPagamento(Date dataPagamento) { this.dataPagamento = dataPagamento; }
}