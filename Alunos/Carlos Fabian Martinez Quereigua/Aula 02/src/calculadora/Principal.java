package calculadora;

import java.util.Scanner;
import calculadora.objetos.Caixa;
import calculadora.objetos.Calculadora;

public class Principal {
	
	static Scanner scan = new Scanner(System.in);
	
	public static void main(String[] args) {
	
		System.out.println("Bem vindo(a)!!");
		menuSelecao();
		
	}
	private static void menuSelecao() {
		System.out.println("Selecione uma opção abaixo.");
		System.out.println("[1] - Calcular Preço Total");
		System.out.println("[2] - Calcular Troco");
		System.out.println("[3] - Sair");
		
		int opcao = scan.nextInt(); 
		
		menuSwicht(opcao);
	}

	private static void menuSwicht(int opcao) {
		
		switch(opcao) {
			case 1: {
				calculaTotal();
				break;
			}
			case 2: {
				
				calculaTroco();
				break;
			}
			case 3: {
				System.out.println("Obrigado por utilizar! volte quando quiser");
				break;
			}
		}
	}

	private static void calculaTroco() {
		//calcula troco recebe primeiro o dinheiro e depois o valor total da compra retorna calculo
		System.out.println("Insira o valor pago:");
		double valorPago = scan.nextDouble();
		System.out.println("Insita o valor total a pagar:");
		double preco = scan.nextDouble();
		
		double troco = Caixa.retornaTroco(valorPago, preco);
		
		System.out.printf("Troco: %.2f",troco);
		System.out.println();
		
		menuSelecao();
	}

	private static void calculaTotal() {
		//calculadora total, recebe primeiro a quantidade de plantas depois o valor unitario retorna resultado do calculo
		System.out.println("Insira a quantidade de plantas:");
		int quantidade = scan.nextInt();
		System.out.println("Insita o valor unitário da planta:");
		double valor = scan.nextDouble();
		
		double total = Calculadora.retornaTotal(quantidade, valor);
		
		System.out.printf("total = %.2f", total);
		System.out.println();
		
		menuSelecao();
	}
}
