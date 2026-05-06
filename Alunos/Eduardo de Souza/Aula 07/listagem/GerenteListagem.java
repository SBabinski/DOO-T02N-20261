package aulas.listagem;

import aulas.BancoDados;

public class GerenteListagem {

	public static void apresentarse() {
		if(BancoDados.getGerentes().isEmpty()) {
			BancoDados.mensagemListaVazia("Gerente");
			return;
		}
		for(int i=0; i < BancoDados.getGerentes().size(); i++) {
			System.out.printf("%d - ", i+1);
			BancoDados.getGerentes().get(i).listaGerente();
		}
	}
}
