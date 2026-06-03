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

    double calcularValorTotal() {
        double total = 0;
        for (int i = 0; i < itens.length; i++) {
            total += itens[i].valor;
        }
        return total;
    }

    void gerarDescricaoVenda() {
        System.out.println("Data: " + dataCriacao);
        System.out.println("Total: " + calcularValorTotal());
    }
}