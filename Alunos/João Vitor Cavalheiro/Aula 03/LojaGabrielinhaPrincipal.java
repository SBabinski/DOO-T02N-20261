//package fag;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LojaGabrielinhaPrincipal {

	static Scanner ler = new Scanner(System.in);
	static List <Venda> vendas = new ArrayList<>();

	public static void main(String[] args) {
		mostrarMenu();
	}

	private static void mostrarMenu() {
		int escolha=1;
		while (escolha != 0) {
			System.out.println("-----Menu-----");
			System.out.println("[1]-Calcular Preço Total da Compra");
			System.out.println("[2]-Calcular Troco");
			System.out.println("[3]-Historico de Vendas");
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
		case 0 -> System.out.println("Obrigado por usar nosso sistema❤");
		default -> System.out.println("Escolha uma opção válida do Menu");	
		}
	}

	private static void historicoVendas() {
		for (int i=0;i<vendas.size();i++) {
			System.out.printf("Venda "+i+":");
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
			validarEscolha2(escolha2, total, qtd2);
		}
	}
	
	private static void validarEscolha2(int escolha2, double total, int qtd2) {
		switch (escolha2) {
		case 1 -> {
    	}
		case 2 -> {
			if (qtd2>10) {
				total=total*0.95;
				System.out.println("O valor total da compra foi de R$"+total);
				Venda venda = new Venda(qtd2, total);
				vendas.add(venda);
			}else{
				System.out.println("O valor total da compra foi de R$"+total);
				Venda vendadois = new Venda(qtd2, total);
				vendas.add(vendadois);
			}
        }
		default -> System.out.println("Escolha uma opção válida do Menu");
		}
	}
}
