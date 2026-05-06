package PROVA1BIM;

import java.util.ArrayList;
import java.util.Scanner;

import PROVA1BIM.Objetos.Veiculo;
import PROVA1BIM.Objetos.Carro;
import PROVA1BIM.Objetos.Moto;
import PROVA1BIM.Objetos.Cliente;
import PROVA1BIM.Objetos.Locacao;
import PROVA1BIM.Objetos.Locadora;

public class Main {

    static Scanner scan = new Scanner(System.in);
    static Main sistema = new Main();

    ArrayList<Cliente> clientes = new ArrayList<>();
    ArrayList<Veiculo> veiculos = new ArrayList<>();
    Locadora locadora = new Locadora();

    public static void main(String[] args) {
        exibirMenu();
    }

    public static void exibirMenu() {

        int op = 100;

        while (op != 0) {

            System.out.println("====SISTEMA DE ALOCAÇÃO DE VEÍCULOS====");
            System.out.println("1- Cadastrar Cliente");
            System.out.println("2- Cadastrar Veículo");
            System.out.println("3- Realizar Locação");
            System.out.println("4- Realizar Devolução");
            System.out.println("5- Exibir Relatório de Locações Ativas");
            System.out.println("6- Demonstração");
            System.out.println("0- Sair");

            op = scan.nextInt();
            scan.nextLine();

            sistema.validarEscolha(op);
        }
    }

    public void validarEscolha(int op) {

        switch (op) {

            case 1:
                cadastrarCliente();
                break;

            case 2:
                cadastrarVeiculo();
                break;

            case 3:
                fazerLocacao();
                break;

            case 4:
                devolverVeiculo();
                break;

            case 5:
                locadora.listarAtivas();
                break;

            case 6:
                demonstrar();
                break;

            case 0:
                System.out.println("Encerrando o programa...");
                break;

            default:
                System.out.println("Opção inválida. Tente novamente.");
        }
    }

    public void cadastrarCliente() {

        System.out.println("\n===== CADASTRO DE CLIENTE =====");

        System.out.print("Nome: ");
        String nome = scan.nextLine();

        System.out.print("CPF: ");
        String cpf = scan.nextLine();

        System.out.print("CNH: ");
        String cnh = scan.nextLine();

        Cliente cliente = new Cliente(nome, cpf, cnh);
        clientes.add(cliente);

        System.out.println("Cliente cadastrado com sucesso.");
    }

    public void cadastrarVeiculo() {

        System.out.println("\n===== CADASTRO DE VEÍCULO =====");
        System.out.println("1- Carro");
        System.out.println("2- Moto");

        int op = scan.nextInt();
        scan.nextLine();

        if (op == 1) {

            System.out.println("\n===== CADASTRO DE CARRO =====");

            System.out.print("Placa: ");
            String placa = scan.nextLine();

            System.out.print("Valor da diária: ");
            double diaria = scan.nextDouble();
            scan.nextLine();

            System.out.print("Tem ar-condicionado?: \n1- Sim\n2- Não\n");
            int opAr = scan.nextInt();
            scan.nextLine();

            if (opAr != 1 && opAr != 2) {
                System.out.println("Opção inválida.");
                return;
            }

            boolean temAr = (opAr == 1);

            Veiculo carro = new Carro(placa, diaria, temAr);
            veiculos.add(carro);

            System.out.println("Carro cadastrado com sucesso.");

        } else if (op == 2) {

            System.out.println("\n===== CADASTRO DE MOTO =====");

            System.out.print("Placa: ");
            String placa = scan.nextLine();

            System.out.print("Valor da diária: ");
            double valorDiaria = scan.nextDouble();
            scan.nextLine();

            System.out.print("Cilindradas: ");
            String cilindradas = scan.nextLine();

            Veiculo moto = new Moto(placa, valorDiaria, cilindradas);
            veiculos.add(moto);

            System.out.println("Moto cadastrada com sucesso.");

        } else {
            System.out.println("Opção inválida.");
        }
    }

    public void fazerLocacao() {

        System.out.println("\n===== NOVA LOCAÇÃO =====");

        if (clientes.isEmpty() || veiculos.isEmpty()) {
            System.out.println("Cadastre clientes e veículos antes.");
            return;
        }

        System.out.println("\nClientes:");
        for (int i = 0; i < clientes.size(); i++) {
            System.out.println(i + " - " + clientes.get(i).getNome());
        }

        int opCliente = scan.nextInt();
        scan.nextLine();

        if (opCliente < 0 || opCliente >= clientes.size()) {
            System.out.println("Cliente inválido.");
            return;
        }

        Cliente cliente = clientes.get(opCliente);

        System.out.println("\nVeículos:");
        for (int i = 0; i < veiculos.size(); i++) {
            System.out.println(i + " - " + veiculos.get(i).getPlaca());
        }

        int opVeiculo = scan.nextInt();
        scan.nextLine();

        if (opVeiculo < 0 || opVeiculo >= veiculos.size()) {
            System.out.println("Veículo inválido.");
            return;
        }

        Veiculo veiculo = veiculos.get(opVeiculo);

        System.out.print("Data de retirada (AAAA-MM-DD): ");
        String dataRetiradaStr = scan.nextLine();

        System.out.print("Data de devolução (AAAA-MM-DD): ");
        String dataDevolucaoStr = scan.nextLine();

        java.time.LocalDate dataRetirada = java.time.LocalDate.parse(dataRetiradaStr);
        java.time.LocalDate dataDevolucao = java.time.LocalDate.parse(dataDevolucaoStr);

        Locacao l = new Locacao(cliente, veiculo, dataRetirada, dataDevolucao);
        locadora.adicionarLocacao(l);

        System.out.println("Locação realizada com sucesso.");
    }

    public void devolverVeiculo() {

        System.out.println("\n===== DEVOLUÇÃO =====");

        ArrayList<Locacao> lista = locadora.getLocacoes();
        ArrayList<Integer> indicesPendentes = new ArrayList<>();

        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).estaAtiva()) {
                System.out.println(indicesPendentes.size() + " - " +
                        lista.get(i).getCliente().getNome() + " | " +
                        lista.get(i).getVeiculo().getPlaca());
                indicesPendentes.add(i);
            }
        }

        if (indicesPendentes.isEmpty()) {
            System.out.println("Nenhuma locação pendente.");
            return;
        }

        int op = scan.nextInt();
        scan.nextLine();

        if (op < 0 || op >= indicesPendentes.size()) {
            System.out.println("Opção inválida.");
            return;
        }

        lista.get(indicesPendentes.get(op)).realizarDevolucao();
        System.out.println("Devolução realizada com sucesso.");
    }

    public void demonstrar() {

        System.out.println("\n===== DEMONSTRAÇÃO =====");

        Cliente c1 = new Cliente("Gabriel", "123", "CNH001");
        Cliente c2 = new Cliente("Maria", "456", "CNH002");

        Veiculo carro = new Carro("ABC-1234", 150.0, true);
        Veiculo moto = new Moto("XYZ-5678", 80.0, "600");

        java.time.LocalDate d1 = java.time.LocalDate.parse("2025-07-01");
        java.time.LocalDate d2 = java.time.LocalDate.parse("2025-07-07");

        java.time.LocalDate d3 = java.time.LocalDate.parse("2025-07-05");
        java.time.LocalDate d4 = java.time.LocalDate.parse("2025-07-10");

        Locacao l1 = new Locacao(c1, carro, d1, d2);
        Locacao l2 = new Locacao(c2, moto, d3, d4);

        locadora.adicionarLocacao(l1);
        locadora.adicionarLocacao(l2);

        l1.realizarDevolucao();

        System.out.println("Demonstração criada.");

        locadora.listarAtivas();
    }
}