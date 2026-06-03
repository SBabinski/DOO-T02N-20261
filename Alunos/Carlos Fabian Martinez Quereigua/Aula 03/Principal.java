package calculadora;

import java.util.ArrayList;
import java.util.Scanner;
import calculadora.objetos.Caixa;
import calculadora.objetos.Calculadora;
import calculadora.objetos.Venda;

public class Principal {

	static Scanner scan = new Scanner(System.in);
	static ArrayList<Venda> vendas = new ArrayList<>();

	public static void main(String[] args) {
		
		populaLista();

		System.out.println("Bem vindo(a)!!");
		menuSelecao();
	}

	private static void populaLista() {
		Venda v1 = new Venda(10, 95, 0.05, 5d);
		Venda v2 = new Venda(5, 50, 0, 0d);

		vendas.add(v1);
		vendas.add(v2);
	}

	private static void menuSelecao() {
		System.out.println("Selecione uma opção abaixo.");
		System.out.println("[1] - Calcular Preço Total (Simula)");
		System.out.println("[2] - Calcular Troco");
		System.out.println("[3] - Vender");
		System.out.println("[4] - Consultar Vendas");
		System.out.println("[0] - Sair");

		int opcao = scan.nextInt();

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

		registraVenda(quantidade, total, descontoPorcent, descontoReais);

		System.out.println("Venda Realizada!");

		menuSelecao();
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
									double descontoReais) {
		
		Venda venda = new Venda(quantidade, valor, descontoPorcent, descontoReais);

		vendas.add(venda);
	}
}
