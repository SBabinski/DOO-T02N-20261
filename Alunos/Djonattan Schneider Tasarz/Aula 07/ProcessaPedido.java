import java.util.Date;

public class ProcessaPedido {

    public Pedido processar(int id, Date dataCriacao, Date dataVencimentoReserva,
                            Cliente cliente, Vendedor vendedor, Loja loja, Item[] itens) {
        if (!confirmarPagamento(dataVencimentoReserva)) {
            System.out.println("Reserva vencida. Pedido não confirmado.");
            return new Pedido(id, dataCriacao, null, dataVencimentoReserva, cliente, vendedor, loja, itens);
        }

        Date dataPagamento = new Date();
        Pedido pedido = new Pedido(id, dataCriacao, dataPagamento, dataVencimentoReserva, cliente, vendedor, loja, itens);
        pedido.gerarDescricaoVenda();
        System.out.println("Pagamento confirmado com sucesso.");
        return pedido;
    }

    private boolean confirmarPagamento(Date dataVencimentoReserva) {
        Date hoje = new Date();
        return !hoje.after(dataVencimentoReserva);
    }
}