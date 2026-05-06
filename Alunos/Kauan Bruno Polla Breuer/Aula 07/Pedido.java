import java.text.SimpleDateFormat;
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

    public Pedido() {
    }

    public double calcularValorTotal() {
        double total = 0;

        for (Item item : itens) {
            total += item.valor;
        }

        return total;
    }

    public void gerarDescricaoVenda() {
        SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        String dataFormatada = (dataCriacao != null) ? formatador.format(dataCriacao) : "sem data";

        System.out.println("Pedido #" + id + " criado em: " + dataFormatada);

        if (cliente != null) {
            System.out.println("Cliente: " + cliente.nome);
        }

        if (vendedor != null) {
            System.out.println("Vendedor: " + vendedor.nome);
        }

        if (loja != null) {
            System.out.println("Loja: " + loja.nomeFantasia);
        }

        System.out.printf("Valor total: R$ %.2f%n", calcularValorTotal());
    }
}