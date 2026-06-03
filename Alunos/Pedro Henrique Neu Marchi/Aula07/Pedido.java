import java.util.Date;

public class Pedido {

    int id;
    Date dataCriacao;
    Date dataPagamento;
    Date dataVencimentoReserva;
    Cliente cliente;
    Vendedor vendedor;
    Loja loja;
    Item[] itens;

    public double calcularValorTotal() {
        double total = 0;
        if (itens != null) {
            for (Item item : itens) {
                total += item.valor;
            }
        }
        return total;
    }

    public void gerarDescricaoVenda() {
        System.out.println("Pedido #" + id + " criado em: " + dataCriacao);
        System.out.printf("Valor total: R$ %.2f%n", calcularValorTotal());
    }
}
