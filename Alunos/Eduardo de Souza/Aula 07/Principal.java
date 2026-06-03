package aulas;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import aulas.acoes.*;
import aulas.dados.*;
import aulas.listagem.*;

public class Principal {
	static Scanner scanner =  new Scanner(System.in);

	public static void main(String[] args) {
		preencherBancoDados();
		chamarMenu();
	}
	
	private static void preencherBancoDados() {
		EnderecoDados endereco1 = new EnderecoDados("Paraná", "Cascavel", "Neva", "Rua Castro Alves", "Ao lado do Hospital");
		LojaDados loja = new LojaDados("Americanas", "Americana Ltda", "01200106000109", endereco1, null, null);
		BancoDados.adicionarLoja(loja);
		EnderecoDados endereco2 = new EnderecoDados("Paraná","Santa Tereza", "Centro", "Rui Barbosa", "Ao lado da escola");
		VendedorDados vendedor = new VendedorDados("Carlos", 24, null, endereco2, 2000.0, null);
		BancoDados.adicionarVendedor(vendedor);
		EnderecoDados endereco3 = new EnderecoDados("Paraná", "Toledo", "Floripa", "Rastro Raso", "Ao lado do rio");
		ClienteDados cliente = new ClienteDados("Pedro", 42, endereco3);
		BancoDados.adicionarCliente(cliente);
		ItemDados item1 = new ItemDados(1, "Abacate", "Fruta", 10);
		BancoDados.adicionarItem(item1);
		ItemDados item2 = new ItemDados(2, "Maçã", "Fruta", 20);
		BancoDados.adicionarItem(item2);
		LocalDate dataCriacao1 = LocalDate.parse("01/04/2026", BancoDados.getFormatacao());
		LocalDate dataVencimento1 = LocalDate.parse("01/06/2026", BancoDados.getFormatacao());
		List<ItemDados> itens1 = new ArrayList<>();
		itens1.add(BancoDados.getItens().get(0));
		itens1.add(BancoDados.getItens().get(1));
		PedidoDados pedido1 = new PedidoDados(1, dataCriacao1, null, dataVencimento1, BancoDados.getClientes().get(0), BancoDados.getVendedores().get(0), BancoDados.getLojas().get(0), itens1);
		BancoDados.adicionarPedido(pedido1);
		LocalDate dataCriacao2 = LocalDate.parse("01/04/2026", BancoDados.getFormatacao());
		LocalDate dataVencimento2 = LocalDate.parse("02/04/2026", BancoDados.getFormatacao());
		List<ItemDados> itens2 = new ArrayList<>();
		itens2.add(BancoDados.getItens().get(0));
		itens2.add(BancoDados.getItens().get(1));
		PedidoDados pedido2 = new PedidoDados(2, dataCriacao2, null, dataVencimento2, BancoDados.getClientes().get(0), BancoDados.getVendedores().get(0), BancoDados.getLojas().get(0), itens2);
		BancoDados.adicionarPedido(pedido2);
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
			System.out.println("[4] - Item");
			System.out.println("[5] - Pedido");
			System.out.println("[6] - Voltar");
			opc = scanner.nextInt();
			scanner.nextLine();
			if(opc == 6) {
				BancoDados.mensagemVoltar();
				return;
			} else {
				validarOpcTipoCadastro(opc);
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
		case 4:
			Item.menuItem();
			break;
		case 5:
			Pedido.menuPedido();
			break;
		default:
			BancoDados.mensagemOpcInvalida();
		}
	}

}
