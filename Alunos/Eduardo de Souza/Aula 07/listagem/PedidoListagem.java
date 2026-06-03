package aulas.listagem;

import aulas.BancoDados;

public class PedidoListagem {
	public static void gerarDescricaoVenda() {
		if(BancoDados.getPedidos().isEmpty()) {
			BancoDados.mensagemListaVazia("Pedido");
			return;
		}
		
		for(int i=0; i < BancoDados.getPedidos().size(); i++) {
			System.out.printf("%d - ", i+1);
			BancoDados.getPedidos().get(i).listaPedidos();
		}
	}
}
