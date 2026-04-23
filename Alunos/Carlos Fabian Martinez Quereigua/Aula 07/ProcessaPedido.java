package fag.objetos;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ProcessaPedido {
	
	static SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
	
	public static void processar(String dataCriacao, String dataPagamento,String dataVencimentoReserva, Pessoa cliente,
			Funcionario vendedor, Loja loja, ArrayList<Item> itens) {
		
		if(confirmarPagamento(dataPagamento, dataVencimentoReserva)) {
			System.out.println("Prazo para pagamento vencido!");
		}
		else {
			Pedido p = new Pedido(dataCriacao, dataPagamento, dataVencimentoReserva, cliente, vendedor, loja, itens);
			BancoDeDados.pedidos.add(p);
		}

	}
	
	private static boolean confirmarPagamento(String dataPagamento, String dataVencimentoReserva) {
		Date diaPagamento = null;
		Date diaVencimentoReserva = null;
		
		try {
			diaPagamento = formato.parse(dataPagamento);
		}
		catch(Exception e) {
			System.out.println("Data de pagamento inválida!");
		}
		
		try {
			diaVencimentoReserva = formato.parse(dataVencimentoReserva);
		}
		catch(Exception e) {
			System.out.println("Data de vencimento da reserva inválida!");
		}
		
		return diaPagamento.after(diaVencimentoReserva);
	}
}
