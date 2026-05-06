import java.util.Date;
import java.util.Random;

public class ProcessaPedido {

    private final Random random = new Random();

    public Pedido processar(Cliente cliente, Vendedor vendedor, Loja loja) {
        Pedido pedido = new Pedido();
        pedido.id = gerarId();
        pedido.dataCriacao = new Date();
        pedido.dataVencimentoReserva = new Date(System.currentTimeMillis() + 24L * 60L * 60L * 1000L);
        pedido.cliente = cliente;
        pedido.vendedor = vendedor;
        pedido.loja = loja;

        pedido.itens.add(new Item(1, "Samambaia Azul", "Planta", 50.0));
        pedido.itens.add(new Item(2, "Orquídea Negra", "Planta", 80.0));

        confirmarPagamento(pedido);

        return pedido;
    }

    private boolean confirmarPagamento(Pedido pedido) {
        Date agora = new Date();

        if (!agora.after(pedido.dataVencimentoReserva)) {
            pedido.dataPagamento = agora;
            System.out.println("Pagamento confirmado.");
            return true;
        }

        System.out.println("Reserva vencida. Pagamento não confirmado.");
        return false;
    }

    public void testarConfirmarPagamento(Cliente cliente, Vendedor vendedor, Loja loja) {
        Pedido pedidoValido = new Pedido();
        pedidoValido.id = gerarId();
        pedidoValido.dataCriacao = new Date();
        pedidoValido.dataVencimentoReserva = new Date(System.currentTimeMillis() + 24L * 60L * 60L * 1000L);
        pedidoValido.cliente = cliente;
        pedidoValido.vendedor = vendedor;
        pedidoValido.loja = loja;

        boolean confirmadoValido = confirmarPagamento(pedidoValido);

        Pedido pedidoVencido = new Pedido();
        pedidoVencido.id = gerarId();
        pedidoVencido.dataCriacao = new Date();
        pedidoVencido.dataVencimentoReserva = new Date(System.currentTimeMillis() - 1000L);
        pedidoVencido.cliente = cliente;
        pedidoVencido.vendedor = vendedor;
        pedidoVencido.loja = loja;

        boolean confirmadoVencido = confirmarPagamento(pedidoVencido);

        if (confirmadoValido) {
            System.out.println("Teste 1 aprovado: pagamento confirmado antes do vencimento.");
        } else {
            System.out.println("Teste 1 reprovado.");
        }

        if (!confirmadoVencido) {
            System.out.println("Teste 2 aprovado: reserva vencida bloqueada.");
        } else {
            System.out.println("Teste 2 reprovado.");
        }
    }

    private int gerarId() {
        return random.nextInt(9000) + 1000;
    }
}