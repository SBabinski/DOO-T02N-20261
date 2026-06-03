package fag;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

import fag.objetos.BancoDeDados;
import fag.objetos.Caixa;
import fag.objetos.Calculadora;
import fag.objetos.Cliente;
import fag.objetos.Endereco;
import fag.objetos.Gerente;
import fag.objetos.Item;
import fag.objetos.Loja;
import fag.objetos.Pedido;
import fag.objetos.Venda;
import fag.objetos.Vendedor;

public class Principal {
	
	static Scanner scan = new Scanner(System.in);
	static ArrayList<Venda> vendas = new ArrayList<>();

	public static void main(String[] args) {
		BancoDeDados.inicializar();
		populaListaVendas();

		System.out.println("-------MY PLANT-------");
		System.out.println();
		menuSelecao();
	}

	private static void populaListaVendas() {
		Venda v1 = new Venda(10, 95, 0.05, 5d, LocalDate.of(2025, 3, 25));
		Venda v2 = new Venda(5, 50, 0.0, 0d, LocalDate.of(2025, 03, 25));

		vendas.add(v1);
		vendas.add(v2);
	}

	private static void menuSelecao() {
		System.out.println("Selecione uma opção abaixo.");
		System.out.println("[1] - Calcular Preço Total (Simula)");
		System.out.println("[2] - Calcular Troco");
		System.out.println("[3] - Vender");
		System.out.println("[4] - Consultar Vendas");
		System.out.println("[5] - Consultar vendas por data");
		System.out.println("[6] - Clientes");
		System.out.println("[7] - Vendedores");
		System.out.println("[8] - Gerentes");
		System.out.println("[9] - Pedidos");
		System.out.println("[0] - Sair");

		int opcao = scan.nextInt();
		scan.nextLine();

		menuSwicht(opcao);
	}

	private static void menuSwicht(int opcao) {

		switch (opcao) {
		case 1: {
			calculaTotal();
			break;
		}
		case 2: {
			calculaTroco();
			break;
		}
		case 3: {
			vende();
			break;
		}
		case 4: {
			mostraVendas();
			break;
		}
		case 5: {
			mostraVendasPorData();
			break;
		}
		case 6: {
			mostarMenuClientes();
			break;
		}
		case 7: {
			mostrarMenuVendedores();
			break;
		}
		case 8: {
			mostrarMenuGerentes();
			break;
		}
		case 9: {
			mostrarMenuPedidos();
			break;
		}
		case 0: {
			System.out.println("Obrigado por utilizar! volte quando quiser");
			break;
		}
		default: {
			System.out.println("Opção invalida! \ntente outra opção\n");
			menuSelecao();
		}
		}
	}

	private static void mostrarMenuPedidos() {
		System.out.println("[1] Criar Pedido");
		System.out.println("[2] Mostrar Pedidos");
		int opcao = scan.nextInt();
		scan.nextLine();
		
		switchPedidos(opcao);
	}

	private static void switchPedidos(int opcao) {
		switch(opcao) {
		
			case 1: {
				criarPedido();
				break;
			}
			
			case 2: {
				mostrarPedidos();
				menuSelecao();
				break;
			}
			
		}
		
	}

	private static void mostrarPedidos() {
		for(Pedido p : BancoDeDados.pedidos) {
			p.gerarDescricaoVenda();
		}
		
	}

	private static void criarPedido() {
		
		System.out.println("Data Criação (dd/MM/yyyy)");
		String dataCriacao = scan.nextLine();
		System.out.println("Data Pagamento (dd/MM/yyyy)");
		String dataPagamento = scan.nextLine();
		System.out.println("Data Vencimento da Reserva (dd/MM/yyyy)");
		String dataVencimentoReserva = scan.nextLine();
		
		System.out.println("Qual o cliente?");
		mostrarClientes();
		int opcaoCliente = scan.nextInt();
		scan.nextLine();
		
		System.out.println("Qual o Vendedor?");
		mostrarVendedores();
		int opcaoVendedor = scan.nextInt();
		scan.nextLine();
		
		System.out.println("Qual a Loja?");
		mostraLojas();
		int opcaoLoja = scan.nextInt();
		scan.nextLine();
		
		ArrayList<Item> itens = new ArrayList<Item>();
		int opcaoAdicionar;
		
		do {
			mostrarItens();
			itens.add(BancoDeDados.itens.get(scan.nextInt() - 1 ));
			
			System.out.println("Deseja adicionar mais um item?");
			System.out.println("[1] Sim");
			System.out.println("[2] Não");
			opcaoAdicionar = scan.nextInt();
			
		}while(opcaoAdicionar == 1);
		
		Pedido p = new Pedido(dataCriacao, dataPagamento, dataVencimentoReserva, BancoDeDados.clientes.get(opcaoCliente - 1), 
											BancoDeDados.vendedores.get(opcaoVendedor - 1), BancoDeDados.lojas.get(opcaoLoja - 1), itens);
		
		BancoDeDados.pedidos.add(p);
		menuSelecao();
	}

	private static void mostrarItens() {
		int i = 1;
		
		for(Item it : BancoDeDados.itens) {
			System.out.println(i + ". " + it.getNome());
		}
		
	}

	private static void mostrarMenuGerentes() {
		System.out.println("[1] Criar gererente");
		System.out.println("[2] Mostrar gerentes");
		System.out.println("[3] Mostrar dados de gerente");
		
		int opcao = scan.nextInt();
		scan.nextLine();
		
		switchGerentes(opcao);
		
	}

	private static void switchGerentes(int opcao) {
		switch(opcao) {
				
			case 1: {
				criarGerente();
				break;
			}
			
			case 2: {
				mostrarGerentes();
				menuSelecao();
				break;
			}
			
			case 3: {
				mostrarDadosGerente();
				break;
			}
		}
	}

	private static void mostrarDadosGerente() {
		System.out.println("Qual Gerente deseja consultar?");
		mostrarGerentes();
		int opcao = scan.nextInt();
		scan.nextLine();
		
		BancoDeDados.gerentes.get(opcao - 1).apresentarse();
		menuSelecao();
	}

	private static void mostrarGerentes() {
		System.out.println("Gerentes: ");
		int i = 1;
		
		for(Gerente v : BancoDeDados.gerentes) {
			System.out.println(i + ". " + v.getNome());
			
			i++;
		}
		
	}

	private static void criarGerente() {
		System.out.println("Nome");
		String nome = scan.nextLine();
		System.out.println("Idade");
		int idade = scan.nextInt();
		scan.nextLine();
		System.out.println("Estado");
		String estado = scan.nextLine();
		System.out.println("Cidade");
		String cidade = scan.nextLine();
		System.out.println("Bairro");
		String bairro = scan.nextLine();
		System.out.println("Rua");
		String rua = scan.nextLine();	
		System.out.println("Numero");
		String numero = scan.nextLine();
		
		Endereco endereco = new Endereco(estado, cidade, bairro, rua, numero);
		
		System.out.println("Qual a Loja?");
		mostraLojas();
		int opcao = scan.nextInt();
		scan.nextLine();
		
		System.out.println("Salário");
		double salario = scan.nextDouble();
		scan.nextLine();
		
		
		Gerente gerente = new Gerente(nome, idade, endereco, BancoDeDados.lojas.get(opcao - 1), salario);
		
		BancoDeDados.gerentes.add(gerente);
		menuSelecao();
	}

	private static void mostrarMenuVendedores() {
		System.out.println("[1] Criar vendedor");
		System.out.println("[2] Mostrar vendedores");
		System.out.println("[3] Mostrar dados de vendedor");
		
		int opcao = scan.nextInt();
		scan.nextLine();
		
		switchVendedores(opcao);
	}

	private static void switchVendedores(int opcao) {
		switch(opcao) {
		
			case 1: {
				criarVendedor();
				break;
			}
			
			case 2: {
				mostrarVendedores();
				menuSelecao();
				break;
			}
			
			case 3: {
				mostrarDadosVendedor();
				break;
			}
		}
	}

	private static void mostrarDadosVendedor() {
		System.out.println("Qual vendedor deseja consultar?");
		mostrarVendedores();
		int opcao = scan.nextInt();
		scan.nextLine();
		
		BancoDeDados.vendedores.get(opcao - 1).apresentarse();
		menuSelecao();
	}

	private static void mostrarVendedores() {
		System.out.println("Vendedores: ");
		int i = 1;
		
		for(Vendedor v : BancoDeDados.vendedores) {
			System.out.println(i + ". " + v.getNome());
			
			i++;
		}
		
	}

	private static void criarVendedor() {
		System.out.println("Nome");
		String nome = scan.nextLine();
		System.out.println("Idade");
		int idade = scan.nextInt();
		scan.nextLine();
		
		System.out.println("Estado");
		String estado = scan.nextLine();
		System.out.println("Cidade");
		String cidade = scan.nextLine();
		System.out.println("Bairro");
		String bairro = scan.nextLine();
		System.out.println("Rua");
		String rua = scan.nextLine();	
		System.out.println("Numero");
		String numero = scan.nextLine();
		Endereco endereco = new Endereco(estado, cidade, bairro, rua, numero);
		
		System.out.println("Qual a Loja?");
		mostraLojas();
		int opcao = scan.nextInt();
		scan.nextLine();
		
		System.out.println("Salário");
		double salario = scan.nextDouble();
		scan.nextLine();
		
		
		Vendedor vendedor = new Vendedor(nome, idade, endereco, BancoDeDados.lojas.get(opcao - 1), salario);
		
		BancoDeDados.vendedores.add(vendedor);
		menuSelecao();
	}

	private static void mostraLojas() {
		int i = 1;
		System.out.println("Lojas: ");
		for(Loja l : BancoDeDados.lojas) {
			System.out.printf("%d. %s | %s", i, l.getNomeFantasia(), l.getEndereco().getCidade());
			i++;
		}

	}

	private static void mostarMenuClientes() {
		System.out.println("[1] Criar Cliente");
		System.out.println("[2] Mostrar Clientes");
		System.out.println("[3] Mostrar dados de Cliente");
		
		int opcao = scan.nextInt();
		scan.nextLine();
		
		switchClientes(opcao);
	}

	private static void switchClientes(int opcao) {
		
		switch(opcao) {
		
			case 1: {
				criarCliente();
				break;
			}
			
			case 2: {
				mostrarClientes();
				menuSelecao();
				break;
			}
			
			case 3: {
				mostrarDadosCliente();
				break;
			}
		
		}
		
	}

	private static void mostrarDadosCliente() {
		System.out.println("Qual cliente deseja consultar?");
		mostrarClientes();
		int opcao = scan.nextInt();
		scan.nextLine();
		
		BancoDeDados.clientes.get(opcao - 1).apresentarse();
		menuSelecao();
	}

	private static void mostrarClientes() {
		System.out.println("Clientes: ");
		int i = 1;
		for(Cliente c : BancoDeDados.clientes) {
			System.out.println(i + ". " + c.getNome());
			
			i++;
		}
	}

	private static void criarCliente() {
		
		System.out.println("Nome");
		String nome = scan.nextLine();
		System.out.println("Idade");
		int idade = scan.nextInt();
		scan.nextLine();
		System.out.println("Estado");
		String estado = scan.nextLine();
		System.out.println("Cidade");
		String cidade = scan.nextLine();
		System.out.println("Bairro");
		String bairro = scan.nextLine();
		System.out.println("Rua");
		String rua = scan.nextLine();	
		System.out.println("Numero");
		String numero = scan.nextLine();
		
		Endereco endereco = new Endereco(estado, cidade, bairro, rua, numero);
		
		Cliente cliente = new Cliente(nome, idade, endereco);
		
		BancoDeDados.clientes.add(cliente);
		menuSelecao();
	}

	private static void mostraVendasPorData() {
		System.out.println("Qual o mês da venda?");
		int mes = scan.nextInt();
		System.out.println("Qual o dia do mês? (insira o número correspondente ao mês)");
		int dia = scan.nextInt();
		scan.nextLine();
		
		boolean temVendaNaData = false;
		
		for(Venda venda : vendas) {
			if((venda.getData().getDayOfMonth() == dia)&&
					(venda.getData().getMonthValue() == mes)) {
				venda.mostraDados();
				temVendaNaData = true;
			}
		}
		
		if(!temVendaNaData) {
			System.out.println("Não foram vendidas plantas nesta data");
		}
		
		menuSelecao();
	}

	private static void mostraVendas() {

		for (Venda venda : vendas) {
			venda.mostraDados();
		}

		menuSelecao();
	}

	private static void vende() {
		// calculadora total, recebe primeiro a quantidade de plantas depois o valor
		// unitario retorna resultado do calculo
		System.out.println("Insira a quantidade de plantas:");
		int quantidade = scan.nextInt();
		System.out.println("Insita o valor unitário da planta:");
		double valorUnid = scan.nextDouble();
		scan.nextLine();
		LocalDate data = retornaData();
		
		double descontoPorcent = 0;

		if (quantidade >= 10) {
			descontoPorcent = 5d / 100d;
		}

		double total = Calculadora.retornaTotal(quantidade, valorUnid);
		double precoFinal = Calculadora.retornaPrecoFinal(quantidade, valorUnid, descontoPorcent);

		double descontoReais = total * descontoPorcent;

		System.out.printf("Total = %.2f\n", total);
		System.out.printf("Descontos = %.2f\n", descontoReais);
		System.out.printf("Preço Final = %.2f\n", precoFinal);
		System.out.println();

		registraVenda(quantidade, total, descontoPorcent, descontoReais,data);

		System.out.println("Venda Realizada!");

		menuSelecao();
	}

	private static LocalDate retornaData() {
		System.out.println("Insira a data da venda: (no formato DD/MM/AAAA)");
		String dataText = scan.nextLine();
		
		DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		
		LocalDate data = LocalDate.parse(dataText, formato);
	
		return data;
	}

	private static void calculaTroco() {
		// calcula troco recebe primeiro o dinheiro e depois o valor total da compra
		// retorna calculo
		System.out.println("Insira o valor pago:");
		double valorPago = scan.nextDouble();
		System.out.println("Insita o valor total a pagar:");
		double preco = scan.nextDouble();

		double troco = Caixa.retornaTroco(valorPago, preco);

		System.out.printf("Troco: %.2f", troco);
		System.out.println();

		menuSelecao();
	}

	private static void calculaTotal() {
		// calculadora total, recebe primeiro a quantidade de plantas depois o valor
		// unitario retorna resultado do calculo
		System.out.println("Insira a quantidade de plantas:");
		int quantidade = scan.nextInt();
		System.out.println("Insita o valor unitário da planta:");
		double valorUnid = scan.nextDouble();
		scan.nextLine();
		double desconto = 0;

		if (quantidade >= 10) {
			desconto = 5d / 100d;
		}

		double total = Calculadora.retornaTotal(quantidade, valorUnid);
		double precoFinal = Calculadora.retornaPrecoFinal(quantidade, valorUnid, desconto);
		System.out.printf("Total = %.2f\n", total);
		System.out.printf("Descontos = %.2f\n", total * desconto);
		System.out.printf("Preço Final = %.2f\n", precoFinal);
		System.out.println();

		menuSelecao();
	}

	private static void registraVenda(int quantidade, 
									double valor, 
									double descontoPorcent, 
									double descontoReais,
									LocalDate data) {
		
		Venda venda = new Venda(quantidade, valor, descontoPorcent, descontoReais, data);

		vendas.add(venda);
	}
}
