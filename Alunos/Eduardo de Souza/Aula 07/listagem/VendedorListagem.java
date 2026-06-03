package aulas.listagem;

import aulas.*;

public class VendedorListagem {
	
	public static void apresentarse() {
		if(BancoDados.getVendedores().isEmpty()) {
			BancoDados.mensagemListaVazia("Vendedor");
			return;
		}
		for(int i=0;i < BancoDados.getVendedores().size(); i++) {
			System.out.printf("%d - ", i+1);
			BancoDados.getVendedores().get(i).listaVendedor();
		}
	}
}
