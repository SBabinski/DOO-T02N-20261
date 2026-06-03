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

    String status;

    public Pedido(int id, Cliente cliente, Vendedor vendedor, Loja loja) {
        this.id = id;
        this.cliente = cliente;
        this.vendedor = vendedor;
        this.loja = loja;

        this.dataCriacao = new Date();
        this.dataVencimentoReserva = new Date(System.currentTimeMillis() + 172800000);

        this.status = "PENDENTE";
    }

    double calcularValorTotal() {
        double total = 0;
        for (Item item : itens) {
            total += item.getTotal();
        }
        return total;
    }

    void gerarDescricaoVenda() {
        System.out.println("Data de criação: " + dataCriacao);
        System.out.println("Valor total: " + calcularValorTotal());
    }

    void mostrarResumo(int numero) {

        String produtosTexto = "";

        for (Item i : itens) {
            produtosTexto += i.nome + ", ";
        }

        if (produtosTexto.length() > 0) {
            produtosTexto = produtosTexto.substring(0, produtosTexto.length() - 2);
        }

        System.out.println(
            "Pedido " + numero +
            " | Produtos: " + produtosTexto +
            " | Cliente: " + cliente.nome +
            " | Status: " + status
        );
    }

    Venda gerarVenda() {

        int quantidadeTotal = 0;

        for (Item i : itens) {
            quantidadeTotal += i.quantidade;
        }

        return new Venda(
                quantidadeTotal,
                calcularValorTotal(),
                java.time.LocalDate.now()
        );
    }
}