package aulas;

import java.util.Scanner;

import aulas.dados.*;
import aulas.listagem.*;

public class Functions {
	static Scanner scanner =  new Scanner(System.in);
	
	public static ClienteDados selecionarCliente() {
		ClienteListagem.apresentarse();
		System.out.println("Digite o número do código do Cliente que deseja selecionar:");
		int cod = scanner.nextInt();
		scanner.nextLine();
		cod -= 1;
		
		if(cod < 0 || cod > BancoDados.getClientes().size()) {
			BancoDados.mensagemIDInvalido("Cliente");
			return null;
		}
		
		ClienteDados cliente = BancoDados.getClientes().get(cod);
		return cliente;
	}
	
	public static VendedorDados selecionarVendedor() {
		VendedorListagem.apresentarse();
		System.out.println("Digite o número do código do Vendedor que deseja selecionar:");
		int cod = scanner.nextInt();
		scanner.nextLine();
		cod -= 1;
		
		if(cod < 0 || cod > BancoDados.getVendedores().size()) {
			BancoDados.mensagemIDInvalido("Vendedor");
			return null;
		}
		
		VendedorDados vendedor = BancoDados.getVendedores().get(cod);
		return vendedor;
	}
	
	public static LojaDados selecionarLoja() {
		LojaListagem.apresentarse();
		System.out.println("Digite o número do código da Loja que deseja selecionar:");
		int cod = scanner.nextInt();
		scanner.nextLine();
		cod -= 1;
		
		if(cod < 0 || cod > BancoDados.getLojas().size()) {
			BancoDados.mensagemIDInvalido("Loja");
			return null;
		}
		
		LojaDados loja = BancoDados.getLojas().get(cod);
		return loja;
	}
	
	public static GerenteDados selecionarGerente() {
		GerenteListagem.apresentarse();
		System.out.println("Digite o número do código do Gerente que deseja selecionar:");
		int cod = scanner.nextInt();
		scanner.nextLine();
		cod -= 1;
		
		if(cod < 0 || cod > BancoDados.getGerentes().size()) {
			BancoDados.mensagemIDInvalido("Gerente");
			return null;
		}
		
		GerenteDados gerente = BancoDados.getGerentes().get(cod);
		return gerente;
	}
	
	public static ItemDados selecionarItem() {
		ItemListagem.gerarDescricao();
		System.out.println("Digite o número do código do Item que deseja selecionar:");
		int cod = scanner.nextInt();
		scanner.nextLine();
		cod -= 1;
		
		if(cod < 0 || cod > BancoDados.getItens().size()) {
			BancoDados.mensagemIDInvalido("Item");
			return null;
		}
		
		ItemDados item = BancoDados.getItens().get(cod);
		return item;
	}
	
	public static PedidoDados selecionarPedido() {
		PedidoListagem.gerarDescricaoVenda();
		System.out.println("Digite o número do código do Pedido que deseja selecionar:");
		int cod = scanner.nextInt();
		scanner.nextLine();
		cod -= 1;
		
		if(cod < 0 || cod > BancoDados.getPedidos().size()) {
			BancoDados.mensagemIDInvalido("Pedido");
			return null;
		}
		
		PedidoDados pedido = BancoDados.getPedidos().get(cod);
		return pedido;
	}
}
