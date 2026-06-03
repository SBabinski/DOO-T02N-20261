package aulas.acoes;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import aulas.*;
import aulas.dados.*;
import aulas.listagem.*;

public class Vendedor {
	static Scanner scanner =  new Scanner(System.in);
	static List<VendedorDados> vendedores = BancoDados.getVendedores();
	public static void menuVendedor() {
		int opc = 0;
		do {
			System.out.println("[Dados dos Vendedores]");
			System.out.println("[1] - Cadastrar");
			System.out.println("[2] - Listar ");
			System.out.println("[3] - Média dos Salários");
			System.out.println("[4] - Calcular Bônus");
			System.out.println("[5] - Voltar ");
			opc = scanner.nextInt();
			scanner.nextLine();
			if(opc == 5) {
				return;
			} else {
				validarOpc(opc);
			}
		} while (opc != 5);
	}
	private static void validarOpc(int opc) {
		switch(opc) {
		case 1:
			cadastrarVendedor();
			break;
		case 2:
			VendedorListagem.listarVendedores();
			break;
		case 3:
			mostrarMedia();
			break;
		case 4:
			mostrarBonus();
			break;
		default:
			BancoDados.mensagemOpcInvalida();
			return;
		}
	}
	private static void mostrarBonus() {
		VendedorDados vendedor = Functions.selecionarVendedor();
		
		if(vendedor == null) return;
		
		double bonus = calcularBonus(vendedor);
		
		System.out.printf("O bônus deste funcionário vai ser dê: %.2f \n", bonus);
	}
	
	private static double calcularBonus(VendedorDados vendedor) {
		return vendedor.getSalarioBase() * 0.2;
	}
	
	private static void mostrarMedia() {
		VendedorDados vendedor = Functions.selecionarVendedor();
		
		if(vendedor == null) return;
		
		if(vendedor.getSalarioRecebido().isEmpty()) {
			BancoDados.mensagemListaVazia("Salário");
			return;
		}
		
		double media = calcularMedia(vendedor);
		
		System.out.printf("Média de Salário deste %s é: %.2f \n", vendedor.getNome(), media);
	}
	
	private static double calcularMedia(VendedorDados vendedor) {
		double soma = 0.0;
		
		for (Double item : vendedor.getSalarioRecebido()) {
			soma += item;
		}
		
		return soma / vendedor.getSalarioRecebido().size();
	}
	
	private static void cadastrarVendedor() {
		System.out.println("Digite o Nome:");
		String nome = scanner.nextLine();
		
		System.out.println("Digite a Idade:");
		int idade = scanner.nextInt();
		scanner.nextLine();
		
		System.out.println("Digite a Cidade:");
		String cidade = scanner.nextLine();
		
		System.out.println("Digite o Bairro:");
		String bairro = scanner.nextLine();
		
		System.out.println("Digite a Rua:");
		String rua = scanner.nextLine();
		
		System.out.println("Digite o Salário:");
		double salarioBase = scanner.nextDouble();
		scanner.nextLine();
		
		List<Double> salarios = new ArrayList<>();
		salarios.add(1500.0);
		salarios.add(1700.0);
		salarios.add(3500.0);
		
		VendedorDados vendedor = new VendedorDados(nome, idade, null, cidade, bairro, rua, salarioBase, salarios);
		BancoDados.adicionarVendedor(vendedor);
	}

}
