package fag;

import java.time.LocalDate;

public class ProcessaPedido {
	
	Pedido pedido;
	
	public void processar(int id, LocalDate dataVencimentoReserva, Cliente cliente, Vendedor vendedor, Loja loja) {
		this.pedido = new Pedido(id, LocalDate.now(), null, dataVencimentoReserva, cliente, vendedor, loja);
		System.out.println("O Pedido de número "+id+" foi criado com sucesso.");
		confirmarPagamento();
		
	}
	
	public void confirmarPagamento() {
		if (pedido.dataVencimentoReserva.isAfter(LocalDate.now())) {
			pedido.dataPagamento = LocalDate.now();
			System.out.println("Pagamento confirmado. Data: "+pedido.dataPagamento);
		}else {
			System.out.println("Reserva vencida, não é possível confirmar o pagamento.");
		}
	}
}
