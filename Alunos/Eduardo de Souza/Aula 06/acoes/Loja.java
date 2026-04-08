package aulas.acoes;

import java.util.List;
import java.util.Scanner;

import aulas.*;
import aulas.dados.*;
import aulas.listagem.*;

public class Loja {
	static Scanner scanner =  new Scanner(System.in);
	static List<VendedorDados> vendedores = BancoDados.getVendedores();
	static List<ClienteDados> clientes = BancoDados.getClientes();
	static List<LojaDados> lojas = BancoDados.getLojas();
	
	public static void vendaMenu() {
		System.out.println("[Dados das Lojas]");
		System.out.println("[1] - Cadastrar Loja");
		System.out.println("[2] - Vincular um Cliente");
		System.out.println("[3] - Vincular um Vendedor");
		System.out.println("[4] - Listar Lojas");
		System.out.println("[5] - Voltar");
		int opc = scanner.nextInt();
		scanner.nextLine();
		if(opc != 5) {
			validarOpc(opc);
		} else {
			BancoDados.mensagemVoltar();
			Principal.escolherTipoCadastro();
		}
	}

	private static void validarOpc(int opc) {
		switch(opc) {
		case 1:
			cadastrarLoja();
			break;
		case 2:
			vinculoCliente();
			break;
		case 3:
			vinculoVendedor();
			break;
		case 4:
			LojaListagem.listarLoja();
			break;
		default:
			BancoDados.mensagemOpcInvalida();
		}
	}

	private static void vinculoVendedor() {
		do {
			System.out.println("[Vinculo do Vendedor]");
			System.out.println("[1] - Vincular");
			System.out.println("[2] - Listar");
			System.out.println("[3] - Voltar");
			int opc = scanner.nextInt();
			scanner.nextLine();
			if(opc == 3) {
				BancoDados.mensagemVoltar();
				return;
			} else {
				validarOpcVinculoVendedor(opc);
			}
			
		} while(true);
	}

	private static void validarOpcVinculoVendedor(int opc) {
		switch(opc) {
		case 1:
			vincularVendedor();
			break;
		case 2:
			LojaDados loja = Functions.selecionarLoja();
			if(loja == null) return;
			System.out.printf("Quantidade de Vendedores: %d \n", loja.getVendedores().size());
			LojaListagem.listarLojaVendedor(loja);
			break;
		default:
			BancoDados.mensagemOpcInvalida();
			return;
		}
	}

	private static void vinculoCliente() {
		do {
			System.out.println("[Vinculo do Cliente]");
			System.out.println("[1] - Vincular");
			System.out.println("[2] - Listar");
			System.out.println("[3] - Voltar");
			int opc = scanner.nextInt();
			scanner.nextLine();
			if(opc == 3) {
				BancoDados.mensagemVoltar();
				return;
			} else {
				validarOpcVinculoCliente(opc);
			}
			
		} while(true);
	}

	private static void validarOpcVinculoCliente(int opc) {
		switch(opc) {
		case 1:
			vincularCliente();
			break;
		case 2:
			LojaDados loja = Functions.selecionarLoja();
			if(loja == null) return;
			System.out.printf("Quantidade de Clientes: %d \n", loja.getClientes().size());
			LojaListagem.listarLojaCliente(loja);
			break;
		default:
			BancoDados.mensagemOpcInvalida();
			return;
		}
	}

	private static void vincularVendedor() {
		LojaDados loja = Functions.selecionarLoja();
		if(loja == null) return;
		
		VendedorDados vendedor = Functions.selecionarVendedor();
		if(vendedor == null) return;
		
		if(vendedor.getLoja() == null) {
			loja.getVendedores().add(vendedor);
		} else {
			System.out.printf("Vendedor já possui um vinculo com a loja %s \n", vendedor.getLoja().getNomeFantasia());
		}
		
	}

	private static void vincularCliente() {
		LojaDados loja = Functions.selecionarLoja();
		if(loja == null) return;
		
		ClienteDados cliente = Functions.selecionarCliente();
		if(cliente == null) return;
		
		loja.getClientes().add(cliente);
	}


	private static void cadastrarLoja() {
		System.out.println("Digite o Nome Fantasia:");
		String nomeFantasia = scanner.nextLine();	
		
		System.out.println("Digite a Razão Social:");
		String razaoSocial = scanner.nextLine();
		
		System.out.println("Digite o CNPJ:");
		String cnpj = scanner.nextLine();
		
		System.out.println("Digite a Cidade:");
		String cidade = scanner.nextLine();
		
		System.out.println("Digite o Bairro:");
		String bairro = scanner.nextLine();
		
		System.out.println("Digite a Rua:");
		String rua = scanner.nextLine();
		
		LojaDados loja = new LojaDados(nomeFantasia, razaoSocial, cnpj, cidade, bairro, rua, null, null);
		BancoDados.adicionarLoja(loja);
		
	}

}
