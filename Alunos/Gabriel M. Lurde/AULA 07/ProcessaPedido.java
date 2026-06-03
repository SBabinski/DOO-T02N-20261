import java.util.Date;

public class ProcessaPedido {

    Pedido processar(Cliente cliente, Vendedor vendedor, Loja loja, Item[] itens) {

        Pedido pedido = new Pedido();
        pedido.id = 1;
        pedido.dataCriacao = new Date();
        pedido.dataVencimentoReserva = new Date(System.currentTimeMillis() + 86400000);
        pedido.cliente = cliente;
        pedido.vendedor = vendedor;
        pedido.loja = loja;
        pedido.itens = itens;

        if (confirmarPagamento(pedido)) {
            pedido.dataPagamento = new Date();
        }

        return pedido;
    }

    private boolean confirmarPagamento(Pedido pedido) {
        Date hoje = new Date();
        return hoje.before(pedido.dataVencimentoReserva);
    }
}