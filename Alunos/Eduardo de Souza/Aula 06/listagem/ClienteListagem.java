package aulas.listagem;

import java.util.List;

import aulas.BancoDados;
import aulas.dados.ClienteDados;

public class ClienteListagem {
	static List<ClienteDados> clientes = BancoDados.getClientes();
	public static void listarClientes() {
		if(clientes.isEmpty()) {
			BancoDados.mensagemListaVazia("Cliente");
			return;
		}
		for(int i=0; i < clientes.size(); i++) {
			System.out.printf("%d - ", i+1);
			clientes.get(i).listarCliente();
		}
	}
}
