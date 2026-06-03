package aulas.acoes;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import aulas.*;
import aulas.dados.*;
import aulas.listagem.*;

public class Gerente {
	static Scanner scanner =  new Scanner(System.in);

	public static void menuGerente() {
		do { 
			System.out.println("[Dados Gerente]");
			System.out.println("[1] - Cadastrar");
			System.out.println("[2] - Listar");
			System.out.println("[3] - Média do Salário");
			System.out.println("[4] - Calcula Bônus");
			System.out.println("[5] - Voltar");
			int opc = scanner.nextInt();
			scanner.nextLine();
			if(opc == 5) {
				BancoDados.mensagemVoltar();
				return;
			} else {
				validarOpc(opc);
			}
		} while(true);
	}
	private static void validarOpc(int opc) {
		switch(opc) {
		case 1:
			cadastrarGerente();
			break;
		case 2:
			GerenteListagem.apresentarse();
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
		GerenteDados gerente = Functions.selecionarGerente();
		
		if(gerente == null) return;
		
		double bonus = calcularBonus(gerente);
		
		System.out.printf("O bônus do(a) %s vai ser dê: %.2f \n", gerente.getNome(),bonus);
		
	}
	private static double calcularBonus(GerenteDados gerente) {

		double bonus = gerente.getSalarioBase() * 0.35;
		
		return bonus;
	}
	private static void mostrarMedia() {
		GerenteDados gerente = Functions.selecionarGerente();
		
		if(gerente == null) return;
		
		if(gerente.getSalarioRecebido().isEmpty()) {
			BancoDados.mensagemListaVazia("Salário");
		}
		
		double media = calcularMedia(gerente);
		
		System.out.printf("Média de Salário deste %s é: %.2f \n", gerente.getNome(), media);
	}
	private static double calcularMedia(GerenteDados gerente) {
		
		double soma = 0.00;
		
		for(double item : gerente.getSalarioRecebido()) {
			soma += item;
		}
		
		return soma / gerente.getSalarioRecebido().size();
	}
	private static void cadastrarGerente() {
		System.out.println("Digite o Nome:");
		String nome = scanner.nextLine();
		
		System.out.println("Digite a Idade:");
		int idade = scanner.nextInt();
		scanner.nextLine();
		
		System.out.println("Digite o Estado:");
		String estado = scanner.nextLine();
		
		System.out.println("Digite a Cidade:");
		String cidade = scanner.nextLine();
		
		System.out.println("Digite o Bairro:");
		String bairro = scanner.nextLine();
		
		System.out.println("Digite a Rua:");
		String rua = scanner.nextLine();
		
		System.out.println("Digite o Complemento:");
		String complemento = scanner.nextLine();
		
		System.out.println("Digite o Salário:");
		double salario = scanner.nextDouble();
		scanner.nextLine();
		
		List<Double> salarios = new ArrayList<>();
		salarios.add(1500.0);
		salarios.add(1700.0);
		salarios.add(3500.0);
		
		EnderecoDados endereco = new EnderecoDados(estado, cidade, bairro, rua, complemento);
		GerenteDados gerente = new GerenteDados(nome, idade, null, endereco, salario, salarios);
		
		BancoDados.adicionarGerente(gerente);
	}
}
