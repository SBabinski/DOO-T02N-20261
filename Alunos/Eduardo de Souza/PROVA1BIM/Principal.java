package prova;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Principal {
	
	static Scanner scanner =  new Scanner(System.in);
	static List<Cliente> clientes = new ArrayList<>();
	static List<Carro> carros = new ArrayList<>();
	static List<Moto> motos = new ArrayList<>();
	static List<Locacao> locacoes = new ArrayList<>(10);

	public static void main(String[] args) {
		criarDemonstracao();
		chamarMenu();
	}

	private static void criarDemonstracao() {
	    Cliente c1 = new Cliente("João Silva", "11111111111", "12345");
	    Cliente c2 = new Cliente("Maria Souza", "22222222222", "67890");
	    clientes.add(c1);
	    clientes.add(c2);

	    Carro carro = new Carro("ABC-1234", 100.0, true);
	    Moto moto = new Moto("XYZ-9999", 50.0, 300);

	    carros.add(carro);
	    motos.add(moto);

	    Locacao loc1 = new Locacao(c1,null,carro,LocalDate.of(2026, 4, 10),LocalDate.of(2026, 4, 15),"Realizada");

	    Locacao loc2 = new Locacao(c2,moto,null,LocalDate.of(2026, 4, 20),null,"Não");

	    locacoes.add(loc1);
	    locacoes.add(loc2);
	}
	public static void chamarMenu() {
		do {
			System.out.println("[Menu]");
			System.out.println("[1] - Cadastrar Cliente");
			System.out.println("[2] - Cadastrar Veiculos");
			System.out.println("[3] - Cadastrar Locação");
			System.out.println("[4] - Realizar Devolução");
			System.out.println("[5] - Listar Locações sem Devolução");
			System.out.println("[6] - Demonstração");
			System.out.println("[7] - Sair");
			int opc = scanner.nextInt();
			scanner.nextLine();
			if(opc ==  7) {
				System.out.println("Saindo...");
				return;
			} else {
				validarOpc(opc);
			}
		}while(true);
		
	}

	private static void validarOpc(int opc) {
		switch(opc) {
		case 1:
			cadastrarCliente();
			break;
		case 2:
			selecionarTipoVeiculo();
			break;
		case 3:
			cadastrarLocacao();
			break;
		case 4:
			realizarDevolucao();
			break;
		case 5:
			listarLocacaoSDevolucao();
			break;
		case 6:
			demonstracao();
			break;
		default:
			System.out.println("Opção Inválida!");
			return;
		}
	}

	private static void demonstracao() {
		System.out.println("Clientes:");
		for(int i = 0; i < clientes.size(); i++) {
			System.out.printf("%d - ", i+1);
			clientes.get(i).listaCliente();
		}
		System.out.println("Motos:");
		for(int i = 0; i < motos.size(); i++) {
			System.out.printf("%d - ", i+1);
			motos.get(i).listaMoto();
		}
		System.out.println("Carros:");
		for(int i = 0; i < carros.size(); i++) {
			System.out.printf("%d - ", i+1);
			carros.get(i).listaCarro();
		}
		System.out.println("Locações:");
		for(int i = 0; i < locacoes.size(); i++) {
				System.out.printf("%d - ", i+1);
				locacoes.get(i).listaLocacao();
		}
	}

	private static void listarLocacaoSDevolucao() {
		for(int i = 0; i < locacoes.size(); i++) {
			if(locacoes.get(i).getSituacao().contentEquals("Não")) {
				System.out.printf("%d - ", i+1);
				locacoes.get(i).listaLocacao();
			}
		}
	}

	private static void realizarDevolucao() {
		Locacao locacao = selecionarLocacao();
		if(locacao == null) {
			System.out.println("Locação Inválida");
			return;
		}
		double valorDiaria = 0.0;
		double valorTotal = 0.0;
		
		long dias = LocalDate.now().toEpochDay() - locacao.getDataRetirada().toEpochDay();

		if(dias == 0) {
		    dias = 1;
		}
	    if(locacao.getCarro() != null) {
	        valorDiaria = locacao.getCarro().getValorDiaria();
	    } else {
	        valorDiaria = locacao.getMoto().getValorDiaria();
	    }
	    
	    valorTotal = valorDiaria * dias;
	    
		System.out.printf("Valor total a ser pago é %.2f \n", valorTotal);
		locacao.setSituacao("Realizada");
		locacao.setDataDevolucao(LocalDate.now());
		System.out.println("Devolução Realizado com Sucesso!");
		
	}

	private static Locacao selecionarLocacao() {
		for(int i = 0; i < locacoes.size(); i++) {
			System.out.printf("%d - ", i+1);
			locacoes.get(i).listaLocacao();
		}
		System.out.println("Digite o código do locação que deseja:");
		int cod = scanner.nextInt();
		scanner.nextLine();
		cod -= 1;
		
		if(cod < 0 || cod >= locacoes.size()) {
			System.out.println("Locação inválido");
			return null;
		}
		
		Locacao locacao = locacoes.get(cod);
		
		return locacao;
	}

	private static void cadastrarLocacao() {
		if(locacoes.size() >= 10) {
			System.out.println("Está no máximo de Locação possível!");
			return;
		}
		
		Cliente cliente = selecionarCliente();
		if(cliente == null) {
			System.out.println("Cliente Inválido");
			return;
		}
		
		System.out.println("Deseja uma moto ou carro: (1 - Moto | 2 - Carro)");
		int opc = scanner.nextInt();
		scanner.nextLine();
		
		DateTimeFormatter formatacao = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		
		System.out.println("Digite a Data de Retirada: (Dia/Mês/Ano)");
		String dataRetiradaNformatada = scanner.nextLine();
		LocalDate dataRetirada = LocalDate.parse(dataRetiradaNformatada, formatacao);
		
		Moto moto = null;
		Carro carro = null;

		if(opc == 1) {
		    moto = selecionarMoto();
		    if(moto == null) return;
		} else if(opc == 2) {
		    carro = selecionarCarro();
		    if(carro == null) return;
		}

		Locacao locacao = new Locacao(cliente, moto, carro, dataRetirada, null, "Não");
		
		locacoes.add(locacao);
		
	}

	private static Carro selecionarCarro() {
		for(int i = 0; i < carros.size(); i++) {
			System.out.printf("%d - ", i+1);
			carros.get(i).listaCarro();
		}
		System.out.println("Digite o código do carro que deseja:");
		int cod = scanner.nextInt();
		scanner.nextLine();
		cod -= 1;
		
		if(cod < 0 || cod >= carros.size()) {
			System.out.println("Carro inválido");
			return null;
		}
		
		Carro carro = carros.get(cod);
		
		return carro;
	}

	private static Moto selecionarMoto() {
		for(int i = 0; i < motos.size(); i++) {
			System.out.printf("%d - ", i+1);
			motos.get(i).listaMoto();
		}
		System.out.println("Digite o código da moto que deseja:");
		int cod = scanner.nextInt();
		scanner.nextLine();
		cod -= 1;
		
		if(cod < 0 || cod >= motos.size()) {
			System.out.println("Moto inválida");
			return null;
		}
		
		Moto moto = motos.get(cod);
		
		return moto;
	}

	private static void selecionarTipoVeiculo() {
		System.out.println("Qual veiculo deseja cadastrar:");
		System.out.println("[1] - Carro");
		System.out.println("[2] - Moto");
		System.out.println("[3] - Voltar");
		int opc = scanner.nextInt();
		scanner.nextLine();
		if(opc == 3) {
			System.out.println("Voltando");
			return;
		} else {
			validarTipoVeiculo(opc);
		}
	}

	private static void validarTipoVeiculo(int opc) {
		switch(opc) {
		case 1:
			cadastrarCarro();
			break;
		case 2:
			cadastrarMoto();
			break;
		default:
			System.out.println("Opção Inválida!");
			return;
		}
	}
	
	private static void cadastrarMoto() {
		System.out.println("Digite a Placa:");
		String placa = scanner.nextLine();
		
		System.out.println("Digite o Valor da Diária:");
		double valorDiaria = scanner.nextDouble();
		scanner.nextLine();
		
		System.out.println("Digite a quantidade de Cilindrada:");
		double cilindrada = scanner.nextDouble();
		scanner.nextLine();
		
		Moto moto = new Moto(placa, valorDiaria, cilindrada);
		motos.add(moto);
	}

	private static void cadastrarCarro() {
		System.out.println("Digite a Placa:");
		String placa = scanner.nextLine();
		
		System.out.println("Digite o Valor da Diária:");
		double valorDiaria = scanner.nextDouble();
		scanner.nextLine();
		
		
		System.out.println("Digite se possui Ar-Condicionado: (1 - Sim | 2 - Não)");
		int opc = scanner.nextInt();
		scanner.nextLine();
		
		boolean ar;
		
		if(opc == 1) {
			ar = true;
		} else {
			ar = false;
		}
		
		Carro carro = new Carro(placa, valorDiaria, ar);
		carros.add(carro);
	}

	private static Cliente selecionarCliente() {
		for(int i = 0; i < clientes.size(); i++) {
			System.out.printf("%d - ", i+1);
			clientes.get(i).listaCliente();
		}
		System.out.println("Digite o código do cliente que deseja:");
		int cod = scanner.nextInt();
		scanner.nextLine();
		cod -= 1;
		
		if(cod < 0 || cod >= clientes.size()) {
			System.out.println("Cliente inválido");
			return null;
		}
		
		Cliente cliente = clientes.get(cod);
		
		return cliente;
	}

	private static void cadastrarCliente() {
		System.out.println("Digite o nome do Cliente:");
		String nome = scanner.nextLine();
		
		System.out.println("Digite o cpf do Cliente:");
		String cpf = scanner.nextLine();
		
		System.out.println("Digite a cnh do Cliente:");
		String cnh = scanner.nextLine();
		
		Cliente cliente = new Cliente(nome, cpf, cnh);
		clientes.add(cliente);
	}
	
}
