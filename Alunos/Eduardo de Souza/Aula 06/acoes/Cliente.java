package aulas.acoes;

import java.util.List;
import java.util.Scanner;

import aulas.*;
import aulas.dados.*;
import aulas.listagem.*;

public class Cliente {
	static Scanner scanner =  new Scanner(System.in);
	static List<ClienteDados> clientes = BancoDados.getClientes();
	public static void clienteMenu() {
		int opc=0;
		do {
			System.out.println("Dados dos Clientes");
			System.out.println("[1] - Cadastrar");
			System.out.println("[2] - Listar clientes ");
			System.out.println("[3] - Voltar");
			opc = scanner.nextInt();
			scanner.nextLine();
			if(opc == 3) {
				return;
			}else {
				validarOpc(opc);
			}
			
		}while(opc != 3);
	}
	private static void validarOpc(int opc) {
		switch(opc) {
		case 1:
			cadastrarCliente();
			break;
		case 2:
			ClienteListagem.listarClientes();
			break;
		default:
			BancoDados.mensagemOpcInvalida();
		}
	}
	private static void cadastrarCliente() {
		System.out.println("Digite o Nome:");
		String nome = scanner.nextLine();
		
		System.out.println("Digite a Idade:");
		int idade = scanner.nextInt();
		scanner.nextLine();
		
		System.out.println("Digite a Cidade:");
		String cidade = scanner.nextLine();
		
		System.out.println("Digite o Bairro:");
		String bairro = scanner.nextLine();
		
		System.out.println("Digite a Rua:");
		String rua = scanner.nextLine();
		
		ClienteDados cliente = new ClienteDados(nome, idade, cidade, bairro, rua);
		BancoDados.adicionarCliente(cliente);
		
	}
}
