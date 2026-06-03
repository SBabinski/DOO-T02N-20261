import java.util.Date;

public class ProcessaPedido {

    public Pedido processar(int id, Cliente cliente, Vendedor vendedor,
                            Loja loja, Item[] itens, Date dataVencimentoReserva) {
        Pedido pedido = new Pedido();
        pedido.id = id;
        pedido.dataCriacao = new Date();
        pedido.dataVencimentoReserva = dataVencimentoReserva;
        pedido.cliente = cliente;
        pedido.vendedor = vendedor;
        pedido.loja = loja;
        pedido.itens = itens;

        if (confirmarPagamento(pedido)) {
            pedido.dataPagamento = new Date();
            System.out.println("✔ Pedido #" + pedido.id + " processado com sucesso!");
            pedido.gerarDescricaoVenda();
        } else {
            System.out.println("✘ Pedido #" + pedido.id + " RECUSADO: reserva vencida.");
        }

        return pedido;
    }

    private boolean confirmarPagamento(Pedido pedido) {
        Date agora = new Date();
        return !agora.after(pedido.dataVencimentoReserva);
    }

    // ── Testes ──────────────────────────────────────────────────────────────
    public static void main(String[] args) {
        ProcessaPedido pp = new ProcessaPedido();

        System.out.println("=== TESTE 1: reserva válida (vence em 1 hora) ===");
        Date vencimentoValido = new Date(System.currentTimeMillis() + 3_600_000L);
        Pedido p1 = pp.processar(1, new Cliente(), new Vendedor(), new Loja(),
                new Item[]{}, vencimentoValido);
        System.out.println("Pagamento confirmado? " + (p1.dataPagamento != null ? "Sim ✔" : "Não ✘"));

        System.out.println("\n=== TESTE 2: reserva vencida (venceu há 1 hora) ===");
        Date vencimentoVencido = new Date(System.currentTimeMillis() - 3_600_000L);
        Pedido p2 = pp.processar(2, new Cliente(), new Vendedor(), new Loja(),
                new Item[]{}, vencimentoVencido);
        System.out.println("Pagamento confirmado? " + (p2.dataPagamento != null ? "Sim ✔" : "Não ✘"));
    }
}
