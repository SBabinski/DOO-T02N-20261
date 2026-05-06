import java.util.*;

public class Pedido {

    private int id;
    private Date dataCriacao;
    private Date dataPagamento;
    private Date dataVencimentoReserva;

    private Cliente cliente;
    private Vendedor vendedor;
    private Loja loja;

    private ArrayList<Item> itens = new ArrayList<>();

    public Pedido(int id, Cliente cliente, Vendedor vendedor, Loja loja) {
        this.id = id;
        this.cliente = cliente;
        this.vendedor = vendedor;
        this.loja = loja;

        this.dataCriacao = new Date();

        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, 2);
        this.dataVencimentoReserva = cal.getTime();
    }

    public void adicionarItem(Item item) {
        itens.add(item);
    }

    public double calcularValorTotal() {
        double total = 0;
        for (Item i : itens) {
            total += i.getValor();
        }
        return total;
    }

    public void gerarDescricaoVenda() {
        System.out.println("\n=== RESUMO DA VENDA ===");
        System.out.println("Data: " + dataCriacao);

        System.out.println("Quantidade de itens: " + itens.size());

        if (!itens.isEmpty()) {
            double precoUnitario = itens.get(0).getValor();
            System.out.println("Preço unitário: " + precoUnitario);
        }

        System.out.println("Total: " + calcularValorTotal());
    }

    public Date getDataVencimentoReserva() {
        return dataVencimentoReserva;
    }

    public void setDataPagamento(Date dataPagamento) {
        this.dataPagamento = dataPagamento;
    }

} 