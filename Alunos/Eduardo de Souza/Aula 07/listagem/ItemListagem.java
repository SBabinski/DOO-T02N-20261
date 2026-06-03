package aulas.listagem;

import aulas.BancoDados;

public class ItemListagem {
	public static void gerarDescricao() {
		if(BancoDados.getItens().isEmpty()) {
			BancoDados.mensagemListaVazia("Item");
			return;
		}
		
		for(int i=0; i < BancoDados.getItens().size(); i++) {
			System.out.printf("%d - ", i+1);
			BancoDados.getItens().get(i).listaItens();
		}
	}
}
