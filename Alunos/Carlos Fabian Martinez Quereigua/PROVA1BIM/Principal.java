package fag;

import java.util.Scanner;

import fag.objetos.BancoDeDados;
import fag.objetos.Carro;
import fag.objetos.Cliente;
import fag.objetos.Locacao;
import fag.objetos.Moto;
import fag.objetos.ProcessaLocacao;
import fag.objetos.Veiculo;

public class Principal {
	static Scanner scan = new Scanner(System.in);
	public static void main(String[] args) {
		
		BancoDeDados.inicializar();
		
		menu();
		
	}

	private static void menu() {
		System.out.println("SISTEMA___________");
		System.out.println("[1] Cadastrar cliente");
		System.out.println("[2] Cadastrar Veiculo");
		System.out.println("[3] Cadastrar Locacao");
		System.out.println("[4] Realizar Devolucao");
		System.out.println("[5] Listar Locacoes Sem Devolucao");
		System.out.println("[6] Fazer Demonstração");
		int opcao = scan.nextInt();
		scan.nextLine();	
		
		switchMenu(opcao);
	}

	private static void switchMenu(int opcao) {
		
		switch(opcao) {
			
		case 1 :{
			cadastrarCliente();
			break;
		}
		case 2 :{
			cadastrarVeiculo();
			break;
		}
		case 3 :{
			cadastrarLocacao();
			break;
		}
		case 4 :{
			realizarDevolucao();
			break;
		}
		case 5 :{
			listarLocacoesSemDevolucao();
			break;
		}
		case 6 :{
			fazerDemonstracao();
			break;
		}
		
		
		
		}
		
	}

	private static void cadastrarLocacao() {
		
		System.out.println("QUal o cliente?");
		mostrarClientes();
		int opcaoCliente = scan.nextInt();
		
		System.out.println("Qual o Veiculo?");
		mostrarVeiculos();
		int opcaoVeiculo = scan.nextInt();
		scan.nextLine();
		
		System.out.println("Data retirada");
		String dataRetirada = scan.nextLine();
		
		System.out.println("Data Devolução");
		String dataDevolucao = scan.nextLine();
		
		ProcessaLocacao.processa((BancoDeDados.clientes.get(opcaoCliente - 1), BancoDeDados.veiculos.get(opcaoVeiculo - 1), dataRetirada, dataDevolucao));
		
	}

	private static void mostrarVeiculos() {
		int i = 1;
		for(Veiculo v : BancoDeDados.veiculos) {
			System.out.println(i + ". " + v.getPlaca());
		}
	}

	private static void mostrarClientes() {
		int i = 1;
		for(Cliente c : BancoDeDados.clientes) {
			System.out.println(i + ". " + c.getNome());
		}
	}

	private static void cadastrarVeiculo() {
		System.out.println("Placa:");
		String placa = scan.nextLine();
;		System.out.println("Valor Diaria");
		double valorDiaria = scan.nextDouble();
		scan.nextLine();
		
		System.out.println("[1] Moto");
		System.out.println("[2] Carro");
		int opcaoMotoCarro = scan.nextInt();
		scan.nextLine();
		
		if (opcaoMotoCarro == 1) {
			
			System.out.println("Tem ar-condicionado? /n[1]Sim\n[2]Não");
			int opcaoAr = scan.nextInt();
			if(opcaoAr == 1 || opcaoAr == 2) {
				
				Carro carro = new Carro(placa, valorDiaria, opcaoAr == 1);
				
				BancoDeDados.carros.add(carro);
				
				menu();
			}
			else{
				System.out.println("Opcao invalida");
				menu();
			}
			
			
		}
		else if(opcaoMotoCarro == 2) {
			
			System.out.println("Qual a Cilindrada (ex: 150)");
			int cilindrada = scan.nextInt();
			
			Moto moto = new Moto(placa, valorDiaria, cilindrada);
			
			BancoDeDados.motos.add(moto);
			menu();
		}
		else {
			System.out.println("Opção invalida!");
			menu();
		}
	}

	private static void cadastrarCliente() {
		System.out.println("Nome: ");
		String nome = scan.nextLine();
		System.out.println("CPF: ");
		String cPF = scan.nextLine();
		System.out.println("CNH: ");
		String cNH = scan.nextLine();
		
		Cliente c = new Cliente(nome, cPF, cNH);
		
		BancoDeDados.clientes.add(c);
		menu();
	}
}
