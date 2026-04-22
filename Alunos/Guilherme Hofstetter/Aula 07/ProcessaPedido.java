import java.util.Date;

public class ProcessaPedido {

    public void processar(Pedido pedido) {
        System.out.println("\nProcessando pedido #" + pedido.getId() + "...");

        if (confirmarPagamento(pedido)) {
            System.out.println("✅ Pagamento confirmado!");
            pedido.gerarDescricaoVenda();
        } else {
            System.out.println("❌ Pagamento não confirmado. Reserva vencida!");
        }
    }

    private boolean confirmarPagamento(Pedido pedido) {
        Date hoje = new Date();
        return !hoje.after(pedido.getDataVencimentoReserva());
    }

    public static void main(String[] args) {
        Date hoje = new Date();
        Date amanha = new Date(hoje.getTime() + 86400000L);
        Date ontem = new Date(hoje.getTime() - 86400000L);

        Endereco endCliente = new Endereco("SP", "São Paulo", "Pinheiros", "Rua dos Pinheiros", 50, "Apto 1");
        Endereco endLoja = new Endereco("SP", "São Paulo", "Centro", "Rua das Flores", 100, "Loja 1");

        Loja loja = new Loja("My Plant", "My Plant LTDA", "12.345.678/0001-99", endLoja);
        Cliente cliente = new Cliente("Maria Oliveira", 45, endCliente);

        Endereco endVendedor = new Endereco("SP", "São Paulo", "Centro", "Rua das Flores", 100, "");
        Vendedor vendedor = new Vendedor("Carlos Silva", 35, endVendedor, loja, 2500.00);

        Item item1 = new Item(1, "Samambaia", "Planta", 45.00);
        Item item2 = new Item(2, "Vaso Cerâmica", "Acessório", 30.00);

        System.out.println("--- Teste 1: Reserva válida (vence amanhã) ---");
        Pedido pedidoValido = new Pedido(1, hoje, null, amanha, cliente, vendedor, loja);
        pedidoValido.adicionarItem(item1);
        pedidoValido.adicionarItem(item2);
        new ProcessaPedido().processar(pedidoValido);

        System.out.println("\n--- Teste 2: Reserva vencida (venceu ontem) ---");
        Pedido pedidoVencido = new Pedido(2, ontem, null, ontem, cliente, vendedor, loja);
        pedidoVencido.adicionarItem(item1);
        new ProcessaPedido().processar(pedidoVencido);
    }
}
