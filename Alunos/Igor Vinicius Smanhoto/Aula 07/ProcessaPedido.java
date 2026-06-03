package objects;

import java.time.LocalDate;

public class ProcessaPedido {

    public Pedido processar(Pedido pedido) {

        if (confirmarPagamento(pedido)) {
            pedido.setDataPagamento(LocalDate.now());
            System.out.println("Pagamento aprovado!");
        } else {
            System.out.println("Reserva vencida. Pedido cancelado.");
        }

        return pedido;
    }

    private boolean confirmarPagamento(Pedido pedido) {
        return LocalDate.now().isBefore(pedido.getDataVencimentoReserva());
    }
}