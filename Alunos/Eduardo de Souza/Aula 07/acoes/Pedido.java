package aulas.acoes;

import java.time.LocalDate;
import java.util.Scanner;

import aulas.*;
import aulas.dados.*;
import aulas.listagem.PedidoListagem;

public class Pedido {
	static Scanner scanner =  new Scanner(System.in); 
	public static void menuPedido() {
		do {
			System.out.println("[Dados do Pedido]");
			System.out.println("[1] - Cadastrar");
			System.out.println("[2] - Listar");
			System.out.println("[3] - Calcular Valor Total");
			System.out.println("[4] - Processar Pedido");
			System.out.println("[5] - Voltar");
			int opc = scanner.nextInt();
			scanner.nextLine();
			if(opc == 5) {
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
			cadastrarPedido();
			break;
		case 2:
			PedidoListagem.gerarDescricaoVenda();
			break;
		case 3:
			mostrarValorTotal();
			break;
		case 4:
			chamarProcessarPedido();
			break;
		default:
			BancoDados.mensagemOpcInvalida();
			return;
		}
	}
	private static void chamarProcessarPedido() {
		PedidoDados pedido = Functions.selecionarPedido();
		ProcessaPedido processa = new ProcessaPedido();
		processa.processar(pedido);
	}
	private static void mostrarValorTotal() {
		PedidoDados pedido = Functions.selecionarPedido();
		if(pedido == null)return;
		double valorTotal = pedido.calcularValorTotal();
		
		System.out.printf("O valor total do pedido é de %.2f \n", valorTotal);
	}
	private static void cadastrarPedido() {
		System.out.println("Digite o ID do pedido:");
		int id = scanner.nextInt();
		scanner.nextLine();
		
		System.out.println("Digite a Data de Criação do Pedido: (Dia/Mês/Ano)");
		String dataCriacaoNformatada = scanner.nextLine();
		LocalDate dataCriacao = LocalDate.parse(dataCriacaoNformatada, BancoDados.getFormatacao());
		
		System.out.println("Digite a Data de Vencimento do Pedido: (Dia/Mês/Ano)");
		String dataVencimentoNformatada = scanner.nextLine();
		LocalDate dataVencimento = LocalDate.parse(dataVencimentoNformatada, BancoDados.getFormatacao());
		
		if(dataVencimento.isBefore(dataCriacao)) {
			System.out.println("Data de Criação deve ser antes que a Data de Vencimento");
			return;
		}
		
		ClienteDados cliente = Functions.selecionarCliente();
		if(cliente == null)return;
		
		VendedorDados vendedor = Functions.selecionarVendedor();
		if(vendedor == null)return;
		
		LojaDados loja = Functions.selecionarLoja();
		if(loja == null) return;
		
		PedidoDados pedido = new PedidoDados(id,dataCriacao,null,dataVencimento,cliente,vendedor,loja,null);
		
		BancoDados.adicionarPedido(pedido);
		
		adicionarItens(pedido);
	}
	
	private static void adicionarItens(PedidoDados pedido) {
		boolean continuar = true;
		while(continuar == true) {
			ItemDados item = Functions.selecionarItem();
			pedido.adicionarItens(item);
			System.out.println("Deseja continuar adicionando itens ? (1 - Sim | 2 - Não)");
			int opc = scanner.nextInt();
			scanner.nextLine();
			if(opc == 2)continuar = false;
		}
	}
}
