package aulas.acoes;

import java.util.Scanner;

import aulas.BancoDados;
import aulas.dados.ItemDados;
import aulas.listagem.ItemListagem;

public class Item {
	static Scanner scanner =  new Scanner(System.in);
	public static void menuItem() {
		do {
			System.out.println("[Dados do Item]");
			System.out.println("[1] - Cadastrar");
			System.out.println("[2] - Listar");
			System.out.println("[3] - Voltar");
			int opc = scanner.nextInt();
			scanner.nextLine();
			if(opc == 3) {
				BancoDados.mensagemVoltar();
				return;
			} else {
				validarOpc(opc);
			}
		} while(true);
	}
	private static void validarOpc(int opc) {
		switch(opc) {
		case 1:
			cadastrarItem();
			break;
		case 2:
			ItemListagem.gerarDescricao();
			break;
		default:
			BancoDados.mensagemOpcInvalida();
		}
	}
	private static void cadastrarItem() {
		System.out.println("Digite o ID do item:");
		int id = scanner.nextInt();
		scanner.nextLine();
		
		System.out.println("Digite o Nome do item:");
		String nome = scanner.nextLine();
		
		System.out.println("Digite o Tipo do item:");
		String tipo = scanner.nextLine();
		
		System.out.println("Digite o Valor do item:");
		double valor = scanner.nextDouble();
		scanner.nextLine();
		
		ItemDados item = new ItemDados(id,nome,tipo,valor);
		BancoDados.adicionarItem(item);
	}
}
