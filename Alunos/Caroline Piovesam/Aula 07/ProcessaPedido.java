import java.util.Date;

public class ProcessaPedido {

    public Pedido processar(int id, Date dataCriacao, Date dataVencimento,
            Cliente cliente, Vendedor vendedor, Loja loja) {

        Pedido pedido = new Pedido(id, dataCriacao, dataVencimento, cliente, vendedor, loja);

        if (confirmarPagamento(pedido)) {
            pedido.pagarPedido(new Date());
            System.out.println("Pagamento realizado com sucesso!");
        } else {
            System.out.println("Não foi possível pagar: prazo vencido!");
        }

        return pedido;
    }

    private boolean confirmarPagamento(Pedido pedido) {

        Date agora = new Date();

        return agora.before(pedido.getDataVencimentoReserva());
    }
}