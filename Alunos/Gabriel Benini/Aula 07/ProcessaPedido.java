package CalculadoraLoja;

import java.util.ArrayList;
import java.util.Date;

public class ProcessaPedido {

    public Pedido processar(int id, Cliente cliente, Vendedor vendedor,
                            Loja loja, ArrayList<Item> itens, Date dataVencimentoReserva) {

        Pedido pedido = new Pedido(
                id,
                new Date(),
                dataVencimentoReserva,
                cliente,
                vendedor,
                loja,
                itens
        );

        if (confirmarPagamento(pedido)) {
            pedido.setDataPagamento(new Date());
            return pedido;
        }

        System.out.println("Reserva vencida. Pedido não pode ser pago.");
        return null;
    }

    private boolean confirmarPagamento(Pedido pedido) {
        Date hoje = new Date();

        return !hoje.after(pedido.getDataVencimentoReserva());
    }
}