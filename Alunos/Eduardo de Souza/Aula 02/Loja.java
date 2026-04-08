package aulas;

import java.util.Scanner;

public class Loja {
	static Scanner scanner =  new Scanner(System.in);
	
	public static void main(String[] args) {
		chamarMenu();
	}
	
	public static void chamarMenu() {
		int opc=0;
		do {
			System.out.println("\n");
			System.out.println("[Menu]");
			System.out.println("[1] - Calcular Preço Total");
			System.out.println("[2] - Calcular Troco");
			System.out.println("[3] - Sair");
			System.out.println("Digite a Opção:");
			opc = scanner.nextInt();
			if(opc != 3) {
				validarOpc(opc);
			} else {
				System.out.println("Saindo...");
			}
		} while (opc!=3);
	}
	
	public static void validarOpc(int opc){
		switch(opc) {
		case 1:
			calcularPrecoTotal();
			break;
		case 2:
			calcularTroco(0);
			break;
		default:
			System.out.println("Opção Inválida, Selecione uma Opção Correta!");
		}
	}
	
	public static void calcularPrecoTotal() {
			System.out.println("Digite a Quantidade da Planta Referida:");
			int qntd = scanner.nextInt();
			
			System.out.println("Digite o Valor da Planta Referida:");
			double valor = scanner.nextDouble();
			
			double totalVenda = qntd * valor;
			
			calcularTroco(totalVenda);
	}
	
	public static void calcularTroco(double totalVenda) {
		if(totalVenda == 0) {
			calcularPrecoTotal();
		}
		System.out.printf("Valor da Venda: %.2f\n", totalVenda);
		System.out.println("Digite o Valor Recebido do Cliente:");
		double valorRecebido = scanner.nextDouble();
		
		double troco = valorRecebido - totalVenda;
		
		System.out.printf("Troco da Venda: %.2f", troco);
	}


}
