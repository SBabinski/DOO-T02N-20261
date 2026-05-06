package aulas.listagem;

import aulas.BancoDados;

public class ClienteListagem {

	public static void apresentarse() {
		if(BancoDados.getClientes().isEmpty()) {
			BancoDados.mensagemListaVazia("Cliente");
			return;
		}
		for(int i=0; i < BancoDados.getClientes().size(); i++) {
			System.out.printf("%d - ", i+1);
			BancoDados.getClientes().get(i).listarCliente();
		}
	}
}
