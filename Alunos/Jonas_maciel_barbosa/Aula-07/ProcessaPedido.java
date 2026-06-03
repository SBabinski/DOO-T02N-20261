import java.util.Date;

public class ProcessaPedido {

    public void processar(int id, Date dataCriacao, Date dataPagamento, Date dataVencimentoReserva,
                          Cliente cliente, Vendedor vendedor, Loja loja, Item[] itens) {

        Pedido pedido = new Pedido(id, dataCriacao, dataPagamento, dataVencimentoReserva,
                                   cliente, vendedor, loja, itens);

        if (confirmarPagamento(pedido)) {
            System.out.println("Pagamento confirmado!");
            pedido.gerarDescricaoVenda();
        } else {
            System.out.println("Pagamento não confirmado. Reserva vencida.");
        }
    }

    private boolean confirmarPagamento(Pedido pedido) {
        Date hoje = new Date();
        return !hoje.after(pedido.dataVencimentoReserva);
    }
}
