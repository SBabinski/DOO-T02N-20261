package fag;

import java.util.Scanner;

public class LojaGabrielinhaPrincipal {

	static Scanner ler = new Scanner(System.in);

	public static void main(String[] args) {
		mostrarMenu();
	}

	private static void mostrarMenu() {
		int escolha = 0;
		while (escolha != 3) {
			System.out.println("-----Menu-----");
			System.out.println("[1]-Calcular Preço Total da Compra");
			System.out.println("[2]-Calcular Troco");
			System.out.println("[3]-Sair");
			escolha = ler.nextInt();
			ler.nextLine();
			validarEscolha(escolha);
		}
	}

	private static void validarEscolha(int escolha) {
		switch (escolha) {
		case 1:
			calcularValorTotal();
			break;
		case 2:
			calcularTroco();
			break;
		case 3:
			System.out.println("Obrigado por usar nosso sistema❤");
			break;
		default:
			System.out.println("Escolha uma opção válida do Menu");
			break;	
		}
	}

	private static void calcularTroco() {
		float valc=0;
		float troc=0;
		float total=0;
		System.out.println("Qual foi o valor pago pelo cliente?");
		valc=ler.nextFloat();
		System.out.println("Qual foi o valor total da compra?");
		total=ler.nextFloat();
		troc=valc-total;
		System.out.println("O troco do cliente vai ser R$"+troc);
	}

	private static void calcularValorTotal() {
		int escolha2=0;
		int qtd=0;
		float val=0;
		float total=0;
		
		while(escolha2<2){
			System.out.println("Qual a quantidade de flores que foram compradas?");
			qtd=ler.nextInt();
			ler.nextLine();
			System.out.println("Qual o valor unitário dessa flor?");
			val=ler.nextFloat();
			
			total+=(qtd*val);
			
			System.out.println("Ela comprou algum outro tipo de flor?");
			System.out.println("          [1]Sim [2]Não");
			escolha2=ler.nextInt();
			ler.nextLine();
			validarEscolha2(escolha2);
		}
		
		System.out.println("O valor total da compra foi de R$"+total);
	}
	
	private static void validarEscolha2(int escolha2) {
		switch (escolha2) {
		case 1:
			break;
		case 2:
			break;
		default:
			System.out.println("Escolha uma opção válida do Menu");
			break;
		}
	}
}
