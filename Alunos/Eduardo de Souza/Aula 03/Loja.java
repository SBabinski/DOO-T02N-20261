package aulas;

import aulas.objetos.Registro;
import java.util.ArrayList;
import java.util.Scanner;

public class Loja {
	static Scanner scanner =  new Scanner(System.in);
	static ArrayList<Registro> registro = new ArrayList<>();
	
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
			System.out.println("[3] - Registro");
			System.out.println("[4] - Sair");
			System.out.println("Digite a Opção:");
			opc = scanner.nextInt();
			if(opc != 4) {
				validarOpc(opc);
			} else {
				System.out.println("Saindo...");
			}
		} while (opc!=4);
	}
	
	public static void validarOpc(int opc){
		switch(opc) {
		case 1:
			calcularPrecoTotal();
			break;
		case 2:
			calcularTroco(0,0,false);
			break;
		case 3:
			listarRegistro();
			break;
		default:
			System.out.println("Opção Inválida, Selecione uma Opção Correta!");
		}
	}
	
	private static void listarRegistro() {
		if(registro.size() != 0) {
			for(int i=0;i < registro.size();i++) {
				System.out.printf("%d - ", i+1);
				registro.get(i).listarCarrinho();
			}
		} else {
			System.out.println("Sem registro");
		}
	}

	public static void calcularPrecoTotal() {
			System.out.println("Digite a Quantidade da Planta Referida:");
			int qntd = scanner.nextInt();
			
			System.out.println("Digite o Valor da Planta Referida:");
			double valor = scanner.nextDouble();
			
			double totalVenda = qntd * valor;
			
		    double totalVendaD = validarDescontoEspecial(qntd, totalVenda);
		    
		    if(totalVendaD != totalVenda) {
		    	boolean descontoAplicado = true;
				calcularTroco(qntd, totalVendaD, descontoAplicado);
		    } else { 
		    	boolean descontoAplicado = false;
				calcularTroco(qntd, totalVendaD, descontoAplicado);
			}
	}
	
	public static void calcularTroco(int qntd, double totalVenda, boolean descontoAplicado) {
		if(totalVenda == 0) {
			calcularPrecoTotal();
		}
		System.out.printf("Valor da Venda: %.2f\n", totalVenda);
		System.out.println("Digite o Valor Recebido do Cliente:");
		double valorRecebido = scanner.nextDouble();
		
		if(valorRecebido < totalVenda) {
			int opc = validarValorRecebidoInferior();
			if(opc == 1) {
				calcularTroco(qntd, totalVenda, descontoAplicado);
			}
		}
		
		double troco = valorRecebido - totalVenda;
		
		System.out.printf("Troco da Venda: %.2f", troco);
		
		Registro venda = new Registro(qntd, totalVenda, descontoAplicado);
		registro.add(venda);
	}
	
	private static int validarValorRecebidoInferior() {
		System.out.println("Valor recebido do cliente é menor que o Total da Venda");
		System.out.println("O que deseja fazer: ");
		System.out.println("[1] - Inserir o valor recebido do cliente novamente");
		System.out.println("[2] - Cancelar a venda");
		int opc = scanner.nextInt();
		
		switch(opc) {
		case 1:
			System.out.println("Voltando ao valor recebido..");
			break;
		case 2:
			System.out.println("Retornando ao menu..");
			chamarMenu();
			break;
		default:
			chamarMenu();
		}
		
		return opc;
	}

	public static double validarDescontoEspecial(int qntd, double totalVenda) {
		if(qntd > 10) {
			double desconto = 0.95;
			totalVenda = totalVenda * desconto;
		}
		return totalVenda;
	}

}
