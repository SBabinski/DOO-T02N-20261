package aulas;

import java.util.List;
import java.util.Scanner;

import aulas.acoes.Cliente;
import aulas.acoes.Loja;
import aulas.acoes.Venda;
import aulas.acoes.Vendedor;
import aulas.dados.ClienteDados;
import aulas.dados.LojaDados;
import aulas.dados.VendedorDados;
import aulas.listagem.*;

public class Principal {
	static Scanner scanner =  new Scanner(System.in);
	static List<VendedorDados> vendedores = BancoDados.getVendedores();
	static List<ClienteDados> clientes = BancoDados.getClientes();
	static List<LojaDados> lojas = BancoDados.getLojas();

	public static void main(String[] args) {
		preencherBancoDados();
		chamarMenu();
	}
	
	private static void preencherBancoDados() {
		LojaDados loja = new LojaDados("Americanas", "Americana Ltda", "01200106000109", "Cascavel", "Esmeralda", "Castro Alves", null, null);
		BancoDados.adicionarLoja(loja);
		VendedorDados vendedor = new VendedorDados("Carlos", 24, null, "Cascavel", "Cascavel Velho", "Duque de Caxias", 2000.0, null);
		BancoDados.adicionarVendedor(vendedor);
		ClienteDados cliente = new ClienteDados("Pedro", 42, "Cascavel", "Wilson Jofre", "Petrolina");
		BancoDados.adicionarCliente(cliente);
	}

	public static void chamarMenu() {
		int opc=0;
		do {
			System.out.println("\n");
			System.out.println("[Menu]");
			System.out.println("[1] - Realizar Venda");
			System.out.println("[2] - Calcular Troco");
			System.out.println("[3] - Registros das Vendas");
			System.out.println("[4] - Dados Cadastrais");
			System.out.println("[5] - Sair");
			System.out.println("Digite a Opção:");
			opc = scanner.nextInt();
			scanner.nextLine();
			if(opc != 5) {
				validarOpcMenu(opc);
			} else {
				System.out.println("Saindo...");
			}
		} while (opc!=5);
	}
	
	public static void validarOpcMenu(int opc){
		switch(opc) {
		case 1:
			Venda.calcularPrecoTotal();
			break;
		case 2:
			Venda.calcularTroco(0,0,false);
			break;
		case 3:
			VendaListagem.validarTipoListar();
			break;
		case 4:
			escolherTipoCadastro();
			break;
		default:
			BancoDados.mensagemOpcInvalida();
		}
	}
	
	public static void escolherTipoCadastro() {
		int opc=0;
		do {
			System.out.println("[Dados Cadastrais]");
			System.out.println("[1] - Loja");
			System.out.println("[2] - Cliente");
			System.out.println("[3] - Vendedor");
			System.out.println("[4] - Voltar");
			opc = scanner.nextInt();
			scanner.nextLine();
			if(opc != 4) {
				validarOpcTipoCadastro(opc);
			} else {
				BancoDados.mensagemVoltar();
			}
		}while(opc !=4);
	}

	public static void validarOpcTipoCadastro(int opc) {
		switch(opc) {
		case 1:
			Loja.vendaMenu();
			break;
		case 2:
			Cliente.clienteMenu();
			break;
		case 3:
			Vendedor.menuVendedor();
			break;
		default:
			BancoDados.mensagemOpcInvalida();
		}
	}

}
