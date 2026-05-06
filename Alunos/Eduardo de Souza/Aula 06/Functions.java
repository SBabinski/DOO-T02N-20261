package aulas;

import java.util.List;
import java.util.Scanner;

import aulas.dados.*;
import aulas.listagem.*;

public class Functions {
	static Scanner scanner =  new Scanner(System.in);
	static List<VendedorDados> vendedores = BancoDados.getVendedores();
	static List<ClienteDados> clientes = BancoDados.getClientes();
	static List<LojaDados> lojas = BancoDados.getLojas();
	
	public static ClienteDados selecionarCliente() {
		ClienteListagem.listarClientes();
		System.out.println("Digite o número do código do Cliente que deseja selecionar:");
		int cod = scanner.nextInt();
		scanner.nextLine();
		cod -= 1;
		
		if(cod < 0 || cod > clientes.size()) {
			BancoDados.mensagemIDInvalido("Cliente");
			return null;
		}
		
		ClienteDados cliente = BancoDados.getClientes().get(cod);
		return cliente;
	}
	
	public static VendedorDados selecionarVendedor() {
		VendedorListagem.listarVendedores();
		System.out.println("Digite o número do código do Vendedor que deseja selecionar:");
		int cod = scanner.nextInt();
		scanner.nextLine();
		cod -= 1;
		
		if(cod < 0 || cod > vendedores.size()) {
			BancoDados.mensagemIDInvalido("Vendedor");
			return null;
		}
		
		VendedorDados vendedor = BancoDados.getVendedores().get(cod);
		return vendedor;
	}
	
	public static LojaDados selecionarLoja() {
		LojaListagem.listarLoja();
		System.out.println("Digite o número do código da Loja que deseja selecionar:");
		int cod = scanner.nextInt();
		scanner.nextLine();
		cod -= 1;
		
		if(cod < 0 || cod > lojas.size()) {
			BancoDados.mensagemIDInvalido("Loja");
			return null;
		}
		
		LojaDados loja = BancoDados.getLojas().get(cod);
		return loja;
	}
}
