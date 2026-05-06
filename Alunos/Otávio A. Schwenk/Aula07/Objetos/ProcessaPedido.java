package Objetos;

import java.time.LocalDate;
import java.util.ArrayList;

public class ProcessaPedido {

    public Pedido processar(LocalDate dataCriacao, Cliente cliente, Vendedor vendedor,
                            Loja loja, ArrayList<Item> itens) {

        LocalDate dataVencimento = dataCriacao.plusDays(30);
        Pedido pedido = new Pedido(
            dataCriacao,
            null,
            dataVencimento,
            cliente,
            vendedor,
            loja,
            itens
        );
        boolean pago = confirmarPagamento(pedido);
        if (pago) {
            System.out.println("Pagamento realizado com sucesso!");
        } else {
            System.out.println("Não foi possível confirmar pagamento (reserva vencida).");
        }
        return pedido;
    }

    private boolean confirmarPagamento(Pedido pedido) {

        LocalDate hoje = LocalDate.now();

        if (!hoje.isAfter(pedido.getDataVencimentoReserva())) {
            pedido.setDataPagamento(hoje);
            return true;
        }

        return false;
    }
}
