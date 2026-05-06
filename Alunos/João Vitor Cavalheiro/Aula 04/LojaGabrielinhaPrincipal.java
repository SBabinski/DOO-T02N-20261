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
	static DateTimeFormatter formatoBR = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	static DateTimeFormatter formatoVM = DateTimeFormatter.ofPattern("MM/yyyy");

	public static void main(String[] args) {
		mostrarMenu();
	}

	private static void mostrarMenu() {
		int escolha=1;
		while (escolha != 0) {
			System.out.println("-----Menu-----");
			System.out.println("[1]-Calcular Preço Total da Compra");
			System.out.println("[2]-Calcular Troco");
			System.out.println("[3]-Consultar Historico de Vendas");
			System.out.println("[4]-Consultar Historico de Vendas Dia/Mês");
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
		case 0 -> System.out.println("Obrigado por usar nosso sistema❤");
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
}
