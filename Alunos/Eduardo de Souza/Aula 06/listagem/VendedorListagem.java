package aulas.listagem;

import java.util.List;

import aulas.*;
import aulas.dados.*;

public class VendedorListagem {
	
	static List<VendedorDados> vendedores = BancoDados.getVendedores();
	
	public static void listarVendedores() {
		if(vendedores.isEmpty()) {
			BancoDados.mensagemListaVazia("Vendedor");
			return;
		}
		for(int i=0;i < vendedores.size(); i++) {
			System.out.printf("%d - ", i+1);
			vendedores.get(i).listaVendedor();
		}
	}
}
