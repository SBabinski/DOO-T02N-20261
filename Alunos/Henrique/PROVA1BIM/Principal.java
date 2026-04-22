package fag;

import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDate;

public class Principal {
	
	  static Scanner scan = new Scanner(System.in);
	  static ArrayList<Cliente> clientes = new ArrayList<>();
	  static ArrayList<Veiculo> veiculos = new ArrayList<>();
	  static Locadora locadora = new Locadora();
	
	public static void main(String[] args) {
		
		demonstracao();
		
		int opc = -1;
		
		while(opc != 0) {
			System.out.println("Sistema de Gerenciamento de Locações");
			System.out.println("1 - Cadastrar Cliente");
			System.out.println("2 - Cadastrar Veículo");
			System.out.println("3 - Cadastrar Locação");
			System.out.println("4 - Realizar Devolução");
			System.out.println("5 - Listar Locações Ativas");
			System.out.println("6 - Demonstração");
			System.out.println("0 - Sair");
			opc = scan.nextInt();
			scan.nextLine();
			
			switch(opc) {
			case 1:
				System.out.println("Digite o Nome do Cliente:");
				String nome = scan.nextLine();
				System.out.println("Digite o CPF do Cliente: ");
				String cpf = scan.nextLine();
				System.out.println("Digite a CNH do Cliente: ");
				String cnh = scan.nextLine();
				clientes.add(new Cliente(nome, cpf, cnh));
				System.out.println("Cliente Cadastrado!");
				break;
			case 2:
				System.out.println("1 - Carro | 2 - Moto");
				int tipo = scan.nextInt();
				scan.nextLine();
				System.out.println("Digite a Placa: ");
				String placa = scan.nextLine();
				System.out.println("Digite o Valor da Diaria: ");
				double valorD = scan.nextDouble();
				scan.nextLine();
				if(tipo == 1) {
					System.out.println("O Carro tem ar-condicionado?");
					System.out.println("1 - Sim | 2 - Não");
					int ar = scan.nextInt();
					scan.nextLine();
					veiculos.add(new Carro(placa, valorD, ar == 1));
				}else {
					System.out.println("Digite a Cilindrada: ");
					int cilindrada = scan.nextInt();
					scan.nextLine();
					veiculos.add(new Moto(placa, valorD, cilindrada));
					
				}
				System.out.println("Veículo Cadastrado!");
				break;
			case 3:
				if(clientes.isEmpty()) {
					System.out.println("Nenhum Cliente Cadastrado.");
					break;
				}
				if(veiculos.isEmpty()) {
					System.out.println("Nenhum Veículo Cadastrado.");
					break;
				}
				System.out.println("Escolha o Cliente:");
				for(int i = 0; i < clientes.size(); i++) {
					System.out.println(i + " - "+clientes.get(i).nome);
				}
				int idxC = scan.nextInt();
				scan.nextLine();
				System.out.println("Escolha o Veículo: ");
				for(int i = 0; i < veiculos.size(); i++) {
					System.out.println(i + " - "+veiculos.get(i).placa);
				}
				int idxV = scan.nextInt();
				scan.nextLine();
				System.out.println("Data de Retirada: (YYYY-MM-DD): ");
				LocalDate retirada = LocalDate.parse(scan.nextLine());
				System.out.println("Quantidade de dias de Locação:");
				int dias = scan.nextInt();
				scan.nextLine();
				locadora.adicionarLocacao(new Locacao(clientes.get(idxC), veiculos.get(idxV), retirada, dias, false));
				break;
			case 4:
				if (locadora.total == 0) {
					System.out.println("Nenhuma Locação Registrada. ");
					break;
				}
				System.out.println("Escolha uma Locação para Devolver:");
				for (int i = 0; i < locadora.total; i++) {
					System.out.println(i + " - "+ locadora.locacoes[i].cliente.nome + " | "+locadora.locacoes[i].veiculo.placa+" | Devolvido: "+ (locadora.locacoes[i].devolvido ? "Sim" : "Não"));
					
				}
				int idxL = scan.nextInt();
				scan.nextLine();
				locadora.locacoes[idxL].devolvido = true;
				System.out.println("Devolução realizada! ");
				
				break;
			case 5:
				locadora.listarAtivas();
				break;
			case 6:
				demonstracao();
				break;
				
				default:
					System.out.println("Opção Inválida!");
					break;
			}
		}
		
	}
	
	public static void demonstracao() {
		Cliente c1 = new Cliente("Eduardo Souza", "12345678900", "CNH001");
		Cliente c2 = new Cliente("Jonas Maciel", "12345678901", "CNH002");
		
		Carro carro = new Carro("BAK-3052", 200.00, true);
		Moto moto = new Moto("XYZ5678", 100.00, 600);
		
		clientes.add(c1);
	    clientes.add(c2);
	    veiculos.add(carro);
	    veiculos.add(moto);
		
		Locacao l1 = new Locacao(c1, carro, LocalDate.of(2026, 4, 1), 4, true);
		Locacao l2 = new Locacao(c2, moto, LocalDate.of(2026,  4, 10), 5, false);
		
		locadora.adicionarLocacao(l1);
	    locadora.adicionarLocacao(l2);
		
		System.out.println("----DEMONSTRAÇÃO----");
		l1.ExibirDados();
		l2.ExibirDados();
		
		System.out.println();
		locadora.listarAtivas();
	}
}
