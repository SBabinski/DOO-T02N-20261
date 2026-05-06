package aulas.listagem;

import java.util.List;

import aulas.BancoDados;
import aulas.dados.*;

public class LojaListagem {
	static List<LojaDados> lojas = BancoDados.getLojas();
	
	public static void listarLoja() {
		if(lojas.isEmpty()) {
			BancoDados.mensagemListaVazia("Loja");
			return;
		}
		for(int i=0; i < lojas.size(); i++) {
			System.out.printf("%d - ", i+1);
			lojas.get(i).listarLoja();
		}
	}
	
	public static void listarLojaCliente(LojaDados loja) {
		if(loja.getClientes().isEmpty()) {
			BancoDados.mensagemListaVazia("Cliente vinculado a Loja");
			return;
		}
		for(int i=0; i < loja.getClientes().size(); i++) {
			System.out.printf("%d - Nome: %s | Idade: %d \n", i+1, loja.getClientes().get(i).getNome(), loja.getClientes().get(i).getIdade());
		}
	}
	
	public static void listarLojaVendedor(LojaDados loja) {
		if(loja.getVendedores().isEmpty()) {
			BancoDados.mensagemListaVazia("Vendedor vinculado a Loja");
			return;
		}
		for(int i=0; i < loja.getVendedores().size(); i++) {
			System.out.printf("%d - Nome: %s | Idade: %d | Salário: %.2f \n", i+1, loja.getVendedores().get(i).getNome(), loja.getVendedores().get(i).getIdade(),loja.getVendedores().get(i).getSalarioBase());
		}
	}
}
