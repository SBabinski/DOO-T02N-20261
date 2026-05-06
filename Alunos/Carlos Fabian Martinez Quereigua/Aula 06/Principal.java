package fag;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import java.util.Scanner;
import fag.objetos.Caixa;
import fag.objetos.Calculadora;
import fag.objetos.Venda;

public class Principal {
	
	static Scanner scan = new Scanner(System.in);
	static ArrayList<Venda> vendas = new ArrayList<>();

	public static void main(String[] args) {
		
		populaLista();

		System.out.println("-------MY PLANT-------");
		System.out.println();
		menuSelecao();
	}

	private static void populaLista() {
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
