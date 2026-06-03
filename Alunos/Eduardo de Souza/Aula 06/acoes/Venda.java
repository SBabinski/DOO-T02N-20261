package aulas.acoes;

import java.time.LocalDate;

import java.util.List;
import java.util.Scanner;

import aulas.dados.RegistroDados;
import aulas.BancoDados;
import aulas.Principal;

public class Venda {
	static Scanner scanner =  new Scanner(System.in);
	static List<RegistroDados> registrosVenda = BancoDados.getRegistros();
	
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
		
		System.out.printf("Troco da Venda: %.2f \n", troco);
			
		LocalDate dataRegistro = LocalDate.now();
	
		RegistroDados venda = new RegistroDados(qntd, totalVenda, descontoAplicado, dataRegistro);
		registrosVenda.add(venda);
	}

	public static int validarValorRecebidoInferior() {
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
			Principal.chamarMenu();
			break;
		default:
			BancoDados.mensagemOpcInvalida();
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
