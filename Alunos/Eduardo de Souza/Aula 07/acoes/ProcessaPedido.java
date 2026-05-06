package aulas.acoes;

import java.time.LocalDate;

import aulas.dados.*;

public class ProcessaPedido {
	public PedidoDados processar(PedidoDados pedido) {
		if(confirmarPagamento(pedido)) {
			pedido.setDataPagamento(LocalDate.now());
			System.out.println("Pagamento realizado com sucesso!");
			return pedido;
		} else {
			System.out.println("Reserva está vencida. Pagamento não será permitido!");
			return null;
		}
	}
	
    private boolean confirmarPagamento(PedidoDados pedido) {
        return LocalDate.now().isBefore(pedido.getDataVencimentoReserva());
    }
}
