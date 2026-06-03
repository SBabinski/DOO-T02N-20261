import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Pedido {

    int       id;
    LocalDate dataCriacao;
    LocalDate dataPagamento;
    LocalDate dataVencimentoReserva;
    Cliente   cliente;
    Vendedor  vendedor;
    Loja      loja;
    Item[]    itens;

    static final DateTimeFormatter FORMATO_DATA =
            DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public Pedido(int id,
                  LocalDate dataCriacao,
                  LocalDate dataPagamento,
                  LocalDate dataVencimentoReserva,
                  Cliente   cliente,
                  Vendedor  vendedor,
                  Loja      loja,
                  Item[]    itens) {
        this.id                    = id;
        this.dataCriacao           = dataCriacao;
        this.dataPagamento         = dataPagamento;
        this.dataVencimentoReserva = dataVencimentoReserva;
        this.cliente               = cliente;
        this.vendedor              = vendedor;
        this.loja                  = loja;
        this.itens                 = itens;
    }

    // soma o valor de todos os itens do pedido
    public double calcularValorTotal() {
        double total = 0;
        for (Item item : itens) total += item.valor;
        return total;
    }

    // printa data de criação, valor total e lista de itens
    public void gerarDescricaoVenda() {
        System.out.println("\n========== PEDIDO #" + id + " ==========");
        System.out.println("Data de criação         : " + dataCriacao.format(FORMATO_DATA));
        System.out.println("Vencimento da reserva   : " + dataVencimentoReserva.format(FORMATO_DATA));
        System.out.println("Cliente                 : " + cliente.nome);
        System.out.println("Vendedor                : " + vendedor.nome);
        System.out.println("Loja                    : " + loja.nomeFantasia);
        System.out.println("\nItens do pedido:");
        for (Item item : itens) item.gerarDescricao();
        System.out.printf("%n  %-24s R$ %.2f%n", "VALOR TOTAL:", calcularValorTotal());
        System.out.println("=====================================");
    }
}
