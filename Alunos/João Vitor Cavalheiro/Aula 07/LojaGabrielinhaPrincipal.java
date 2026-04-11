//package fag;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LojaGabrielinhaPrincipal {

	static Scanner ler = new Scanner(System.in);
	static List <Venda> vendas = new ArrayList<>();
	static List <LocalDate> datasDeVenda = new ArrayList<>();
	static List <Loja> lojas = new ArrayList<>();
	static DateTimeFormatter formatoBR = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	static DateTimeFormatter formatoVM = DateTimeFormatter.ofPattern("MM/yyyy");

	public static void main(String[] args) {
		mostrarMenu();
	}

	private static void mostrarMenu() {
		int escolha=1;
		popular();
		while (escolha != 0) {
			System.out.println("-----Menu-----");
			System.out.println("[1]-Calcular Preço Total da Compra");
			System.out.println("[2]-Calcular Troco");
			System.out.println("[3]-Consultar Historico de Vendas");
			System.out.println("[4]-Consultar Historico de Vendas Dia/Mês");
			System.out.println("[5]-Sistema RH");
			System.out.println("[0]-Sair");
			escolha = ler.nextInt();
			ler.nextLine();
			validarEscolha(escolha);
		}
	}

	private static void validarEscolha(int escolha) {
		switch (escolha) {
		case 1 -> calcularValorTotal();
		case 2 -> calcularTroco();
		case 3 -> historicoVendas();
		case 4 -> historicoVendasDM();
		case 5 -> escolhaLoja();
		case 0 -> System.out.println("Obrigado por usar nosso sistema❤");
		default -> System.out.println("Escolha uma opção válida do Menu");	
		}
	}

	private static void escolhaLoja() {

		int escolhaL;

		System.out.println("Digite 1 para verificar a Matriz e 2 a Filial01");
		escolhaL=ler.nextInt();
		validarEscolhaL(escolhaL);
	}

	private static void validarEscolhaL(int escolhaL) {
		switch (escolhaL) {
			case 1 ->{ 
				verificarMatriz(escolhaL);
			}
			case 2 ->{ 
				verificarFilial01(escolhaL);
			}
			default ->System.out.println("Escolha uma opção válida");
		}
	}

	private static void verificarFilial01(int escolhaL) {
		int escolhaF01=1;
		while (escolhaF01 != 0) { 
		System.out.println("-----Menu Filial-----");
		System.out.println("[1]-Verificar Informações da Filial");
		System.out.println("[2]-Verificar Funcionários");
		System.out.println("[3]-Verificar Cliente");
		System.out.println("[4]-Verificar Gerente");
		System.out.println("[0]-Sair");
		escolhaF01=ler.nextInt();
		validarEscolhaF01(escolhaF01, escolhaL);	
		}
	}

	private static void validarEscolhaF01(int escolhaF01, int escolhaL) {
		switch (escolhaF01) {
			
		case 1 -> {
			lojas.get(escolhaL-1).apresentarLoja();
		}
		case 2 -> {
			for (int i=0;i<lojas.get(escolhaL-1).vendedores.size() ;i++) {
				lojas.get(escolhaL-1).vendedores.get(i).apresentarFuncionario();
			}
		}
		case 3 -> {
			for (int i=0;i<lojas.get(escolhaL-1).clientes.size() ;i++) {
				lojas.get(escolhaL-1).clientes.get(i).apresentarCliente();
			}
		}
		case 4 -> {
			for (int i=0;i<lojas.get(escolhaL-1).gerentes.size() ;i++) {
				lojas.get(escolhaL-1).gerentes.get(i).apresentarFuncionario();
			}
		}
		case 0 -> System.out.println("");
		default -> System.out.println("Escolha uma opção válida do Menu");	
		}
	}

	private static void verificarMatriz(int escolhaL) {
		int escolhaM=1;
		while (escolhaM != 0) { 
			System.out.println("-----Menu Matriz-----");
			System.out.println("[1]-Verificar Informações da Matriz");
			System.out.println("[2]-Verificar Funcionários");
			System.out.println("[3]-Verificar Cliente");
			System.out.println("[4]-Verificar Gerente");
			System.out.println("[0]-Sair");
			escolhaM=ler.nextInt();
			validarEscolhaM(escolhaM, escolhaL);
		}
	}

	private static void validarEscolhaM(int escolhaM, int escolhaL) {
			switch (escolhaM) {
			
		case 1 ->{
			lojas.get(escolhaL-1).apresentarLoja();
			System.out.println("");
		}
		case 2 -> {
			for (int i=0;i<lojas.get(escolhaL-1).vendedores.size() ;i++) {
				lojas.get(escolhaL-1).vendedores.get(i).apresentarFuncionario();
				System.out.println("");
			}
		}
		case 3 -> {
			for (int i=0;i<lojas.get(escolhaL-1).clientes.size() ;i++) {
				lojas.get(escolhaL-1).clientes.get(i).apresentarCliente();
				System.out.println("");
			}
		}
		case 4 -> {
			for (int i=0;i<lojas.get(escolhaL-1).gerentes.size() ;i++) {
				lojas.get(escolhaL-1).gerentes.get(i).apresentarFuncionario();
			}
		}
		case 0 -> System.out.println("");
		default -> System.out.println("Escolha uma opção válida do Menu");	
		}
	}

	private static void historicoVendasDM() {
		int escolha3;
		System.out.println("Caso a busca seja pelo Digite 1, caso seja Mês digite 2");
		escolha3=ler.nextInt();
		ler.nextLine();
		validarEscolha3(escolha3);
	}

	private static void validarEscolha3(int escolha3) {
		switch (escolha3) {
			case 1 ->{
				pesquisaDia();
			}
			case 2 ->{
				pesquisaMes();
			}
			default ->System.out.println("Escolha uma opção válida");
		}
	}

	private static void pesquisaMes() {
		double totalVM=0;
		int qtdVM=0;

		System.out.println("Por qual mes deseja fazer a pesquisa, Digite a data seguindo o exemplo:");
		System.out.println("EXEMPLO: MM/YYYY");
		String mesPesquisa=ler.nextLine();

		System.out.println("As vendas feitas neste mês foram:");
		for(int i =0;i<vendas.size();i++){
			String mesVenda=vendas.get(i).data.format(formatoVM);
			if (mesVenda.equals(mesPesquisa)) {
				vendas.get(i).mostrarVenda();
				totalVM += vendas.get(i).getTotal();
				qtdVM += vendas.get(i).qtd;
			}
		}

		if ((totalVM==0)&&(qtdVM==0)) {
			System.out.println("Ainda não foram registradas vendas neste mês");
		}else{
			System.out.println("A quantidade total de flores vendidas neste mês foram de "+qtdVM+" flor(es)");
			System.out.println("O valor total arrecadado neste mês foi de R$"+totalVM);	
		}
	}

	private static void pesquisaDia() {
		double totalVD=0;
		int qtdVD=0;

		System.out.println("Por qual dia deseja fazer a pesquisa, Digite a data seguindo o exemplo:");
		System.out.println("EXEMPLO: DD/MM/YYYY");
		String diaPesquisa=ler.nextLine();
		LocalDate dataPesquisa = LocalDate.parse(diaPesquisa, formatoBR);

		System.out.println("As vendas feitas neste dia foram:");
        for (int i=0;i<vendas.size() ;i++) {
            if (vendas.get(i).data.equals(dataPesquisa)) {
                vendas.get(i).mostrarVenda();
				totalVD += vendas.get(i).getTotal();
				qtdVD += vendas.get(i).qtd;
            }
    	}

		if ((totalVD==0)&&(qtdVD==0)) {
			System.out.println("Ainda não foram registradas vendas neste dia");
		}else{
			System.out.println("A quantidade total de flores vendidas neste dia foram de "+qtdVD+" flor(es)");
			System.out.println("O valor total arrecadado neste dia foi de R$"+totalVD);	
		}

	}

	private static void historicoVendas() {
		for (int i=0;i<vendas.size();i++) {
			int x=i+1;
			System.out.println("Venda "+x+":");
			vendas.get(i).mostrarVenda();
		}
	}

	private static void calcularTroco() {
		float valc;
		float troc;
		float total;
		System.out.println("Qual foi o valor pago pelo cliente?");
		valc=ler.nextFloat();
		System.out.println("Qual foi o valor total da compra?");
		total=ler.nextFloat();
		troc=valc-total;
		System.out.println("O troco do cliente vai ser R$"+troc);
	}

	private static void calcularValorTotal() {
		int escolha2=0;
		int qtd;
		int qtd2=0;
		float val;
		float total=0;
		
		System.out.println("Digite a data que a venda está sendo realizada neste formato DD/MM/YYYY");
		String dataV=ler.nextLine();
		LocalDate dataVenda = LocalDate.parse(dataV, formatoBR);
		datasDeVenda.add(dataVenda);

		while(escolha2<2){
			System.out.println("Qual a quantidade de flores que foram compradas?");
			qtd=ler.nextInt();
			ler.nextLine();
			qtd2=qtd2+qtd;
			System.out.println("Qual o valor unitário dessa flor?");
			val=ler.nextFloat();
			
			total+=(qtd*val);
			
			System.out.println("Ela comprou algum outro tipo de flor?");
			System.out.println("          [1]Sim [2]Não");
			escolha2=ler.nextInt();
			ler.nextLine();
			validarEscolha2(escolha2, total, qtd2, dataVenda);
		}
	}
	
	private static void validarEscolha2(int escolha2, double total, int qtd2, LocalDate dataVenda) {
		switch (escolha2) {
		case 1 -> {
    	}
		case 2 -> {
			if (qtd2>10) {
				total=total*0.95;
				System.out.println("Está venda foi realizada no dia "+dataVenda.format(formatoBR));
				System.out.println("O valor total da compra foi de R$"+total);
				Venda venda = new Venda(qtd2, total, dataVenda);
				vendas.add(venda);

			}else{
				System.out.println("Está venda foi realizada no dia "+dataVenda.format(formatoBR));
				System.out.println("O valor total da compra foi de R$"+total);
				Venda venda2 = new Venda(qtd2, total, dataVenda);
				vendas.add(venda2);
			}
        }
		default -> System.out.println("Escolha uma opção válida do Menu");
		}
	}

	private static void popular(){

		Loja matriz = new Loja("My Plant", 
		"Gabrielina", 
		"12.345.678/0001-95", 
		"Cascavel", 
		"Santa Rita", 
		"Av:Iguaçu");

		Loja filial01 = new Loja("My Plant01", 
		"Gabrielina", 
		"98.765.432/0001-10", 
		"Capitão", 
		"Centro", 
		"Av:Tancredo Neves");

		lojas.add(matriz);
		lojas.add(filial01);

		Cliente cliente1 = new Cliente("Leonardo Silva", 
		18, 
		"Cascavel", 
		"Centro", 
		"Av:Tancredo Neves");

		Cliente cliente2 = new Cliente("João Vitor", 
		40, 
		"Cascavel", 
		"Centro", 
		"Av:Iguaçu");

		Cliente cliente3 = new Cliente("Pedro Henrique", 
		28, 
		"Cascavel", 
		"Vila Nova", 
		"Rua Ibapora");

		Cliente cliente4 = new Cliente("Otávio Capanema", 
		20, 
		"Capitão", 
		"Santa Mônica", 
		"Rua Gonsalves Dias");

		Cliente cliente5 = new Cliente("Vanessa Silva", 
		60, 
		"Capitão", 
		"Santa Rita", 
		"Av:Iguaçu");

		Cliente cliente6 = new Cliente("Guilerme Vieira", 
		31, 
		"Capitão", 
		"Centro", 
		"Av:Tancredo Neves");

		matriz.AddCliente(cliente1);
		matriz.AddCliente(cliente2);
		matriz.AddCliente(cliente3);
		filial01.AddCliente(cliente4);
		filial01.AddCliente(cliente5);
		filial01.AddCliente(cliente6);
		

		Vendedor vendedor1 = new Vendedor("Claudio", 
			31, 
			"", 
			"Cascavel", 
			"Centro", 
			"Av:Tancredo Neves", 
			1800, 
			new double[]{1800.50, 1900.74, 2000.94});

			Vendedor vendedor2 = new Vendedor("Vitor", 
			22, 
			"", 
			"Cascavel", 
			"Vila Nova", 
			"Rua Ibapora", 
			1800, 
			new double[]{1890, 1901.90, 2030.78});

			Vendedor vendedor3 = new Vendedor("Ines", 
			30, 
			"", 
			"Cascavel", 
			"Santa Mônica", 
			"Rua Paraiba", 
			2000, 
			new double[]{2050.10, 2190.74, 2000.99});

			Vendedor vendedor4 = new Vendedor("Carlos", 
			25, 
			"", 
			"Capitão", 
			"Santa Rita", 
			"Av:Iguaçu", 
			1800, 
			new double[]{1840.50, 1930.64, 2000.35});

			Vendedor vendedor5 = new Vendedor("Genesio", 
			20, 
			"", 
			"Capitão", 
			"Santa Mônica", 
			"Av:Tancredo Neves", 
			2000, 
			new double[]{2100.50, 2010.74, 2000.50});

			Gerente gerente1 = new Gerente("Ana Celia", 
				10, 
				"Ibaipura", 
				"Centro", 
				"Rua Caixa Prego", 
				"", 
				1, 
				new double[]{1.2, 1.5, 2.1});

			Gerente gerente2 = new Gerente("Valdemar", 
			99, 
			"Ibaipura", 
			"Centro", 
			"Rua Caixa Prego", 
			"", 
			10000, 
			new double[]{12100.50, 12010.74, 12000.50});

			matriz.AddVendedor(vendedor1);
			matriz.AddVendedor(vendedor2);
			matriz.AddVendedor(vendedor3);
			filial01.AddVendedor(vendedor4);
			filial01.AddVendedor(vendedor5);

			filial01.AddGerente(gerente1);
			matriz.AddGerente(gerente2);
	}
	
}
