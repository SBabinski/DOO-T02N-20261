package fag;
import java.util.Scanner;
import java.util.ArrayList;

import objetos.*;
public class Main {
    static Scanner scan = new Scanner(System.in);
    static ArrayList<Clientes> clientes = new ArrayList<>();
    static ArrayList<Veiculos> veiculos = new ArrayList<>();
    static ArrayList<Locacao> locacoes = new ArrayList<>();
    static Locadora locadora = new Locadora();

	public static void main(String[] args) {
		mostrarMenu();

	}

	private static void mostrarMenu() {
		int opcao;
        do {
            System.out.println("------- MENU ------");
            System.out.println("1-Cadastrar Clientes");
            System.out.println("2-Cadastrar Veiculos");
            System.out.println("3-Cadastrar Locacao");
            System.out.println("4-Realizar Devolucao");
            System.out.println("5-Listar Pendentes");
            System.out.println("6-Demonstracao");
            System.out.println("0-Sair");
            opcao = scan.nextInt();
            validarEscolha(opcao);
          
        } while (opcao != 0);
    }

	private static void validarEscolha(int opcao) {
		switch (opcao) {
		case 1 -> cadastrarClientes();
		case 2 -> cadastrarVeiculos();
		case 3 -> cadastrarLocacao();
		case 4 -> realizarDevolucao();
		case 5 -> listarPendentes();
		case 6 -> realizarDemonstracao();
		case 0 -> System.out.println("saindo...");
		}
	}

	public static void realizarDemonstracao() {
		Clientes l1 = new Clientes("Frodo",90769,96);
		Clientes l2 = new Clientes("Sam",02234,84);
	    clientes.add(l1);
	    clientes.add(l2);
	    
	    Carro carro = new Carro(507,20,true);
	    Moto moto = new Moto(406,10,2000);
	    veiculos.add(carro);
	    veiculos.add(moto);
	    
	    Locacao loc = new Locacao(clientes.get(0),veiculos.get(0),5);
	    Locacao loc2 = new Locacao(clientes.get(1),veiculos.get(1),2);
	    locacoes.add(loc);
	    locacoes.add(loc2);
	    locadora.adicionarLocacao(loc);
	    locadora.adicionarLocacao(loc2);
	    locacoes.get(0).devolver();
	    listarPendentes();
	}

	public static void listarPendentes() {
		System.out.println("LOCACOES ATIVAS");
		locadora.listarPendentes();
		
	}

	public static void realizarDevolucao() {
		if (locacoes.isEmpty()) {
            System.out.println("Nenhuma locacao");
            return;
        }
		System.out.println("Escolha locacao:");
        for (int i = 0; i < locacoes.size(); i++) {
            System.out.println(i + " - " + locacoes.get(i).getDataPrevista());
        }
        int i = scan.nextInt();
        locacoes.get(i).devolver();
        System.out.println("devolucao realizada");
		
	}

	public static void cadastrarLocacao() {
		if (clientes.isEmpty() || veiculos.isEmpty()) {
            System.out.println("Cadastre clientes e veiculos primeiro");
            return;
        }
        System.out.println("Escolha o Cliente (indice):");
        for (int i = 0; i < clientes.size(); i++) {
            System.out.println(i + " - " + clientes.get(i).getNome());
        }
        int l = scan.nextInt();
        System.out.println("Escolha o Veiculo (indice):");
        for (int i = 0; i < veiculos.size(); i++) {
            System.out.println(i + " - " + veiculos.get(i).getPlaca());
        }
        int it = scan.nextInt();
        System.out.print("dias para devolucao: ");
        int dias = scan.nextInt();
        
        Locacao lo = new Locacao(clientes.get(l), veiculos.get(it), dias);
        locacoes.add(lo);
        locadora.adicionarLocacao(lo);
		
	}

	public static void cadastrarVeiculos() {
		System.out.println("1-Carro | 2-Moto");
        int tipo = scan.nextInt();

        System.out.print("Placa: ");
        int placa = scan.nextInt();

        System.out.print("Valor ao dia: ");
        int valdia = scan.nextInt();
        if(tipo == 1) {
        	System.out.println("Possui ar condicionado?");
        	boolean arcond = scan.nextBoolean();
        	veiculos.add(new Carro(placa,valdia,arcond));
        
        }else {
        	System.out.println("Quantas Cilindradas?");
        	int cilindradas = scan.nextInt();
        	veiculos.add(new Moto(placa,valdia,cilindradas));
        }

	}

	public static void cadastrarClientes() {
        System.out.print("Nome: ");
        String nome = scan.next();

        System.out.print("CNH: ");
        int cnh = scan.nextInt();

        System.out.print("CPF: ");
        int cpf = scan.nextInt();

        clientes.add(new Clientes(nome, cnh, cpf));
		
	}
	}


