package Locadora.menus;

import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import Locadora.model.Carro;
import Locadora.model.Locacao;
import Locadora.model.Moto;
import Locadora.repository.BancoDados;

public class MenuPrincipal {

    public static void menuCliente(Scanner teclado, BancoDados bancoDados) {
        int opcao;

        do {
            System.out.println("\n==== Menu Cliente ====");
            System.out.println("1. Cadastrar Cliente");
            System.out.println("2. Listar Clientes");
            System.out.println("0. Voltar");
            System.out.print("\nEscolha uma opção: ");
            opcao = teclado.nextInt();
            teclado.nextLine();

            switch (opcao) {
                case 1:
                    System.out.println("==== Cadastrar Cliente ====");
                    System.out.print("\nDigite o nome do Cliente: ");
                    String nome = teclado.nextLine();
                    System.out.print("Digite o CPF do Cliente: ");
                    String cpf = teclado.nextLine();
                    System.out.print("Digite o numero da CNH do Cliente: ");
                    String cnh = teclado.nextLine();

                    bancoDados.adicionarCliente(nome, cpf, cnh);

                    break;
                case 2:
                    System.out.println("\n===== Listar Clientes =====");
                    bancoDados.listarClientes();
                    break;
                case 0:
                    System.out.println("Voltando...");
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        } while (opcao != 0);
    }

    
    public static void menuVeiculo(Scanner teclado, BancoDados bancoDados) {
        int opcao;
        do {
            System.out.println("\n==== Menu Veículo ====");
            System.out.println("1. Cadastrar Veículo");
            System.out.println("2. Listar Veículos");
            System.out.println("0. Voltar");
            System.out.print("\nEscolha uma opção: ");
            opcao = teclado.nextInt();
            teclado.nextLine();

            switch (opcao) {
                case 1:
                    System.out.println("\n ==== Cadastrar Veículo ====");

                    System.out.println("\nSelecione o tipo do veículo:");
                    System.out.println("1. Carro");
                    System.out.println("2. Moto");
                    System.out.print("Escolha uma opção: ");
                    int tipoVeiculo = teclado.nextInt();
                    teclado.nextLine();

                    System.out.print("Digite a placa do veículo: ");
                    String placa = teclado.nextLine();
                    System.out.print("Digite o valor da diária: ");
                    double valorDiaria = teclado.nextDouble();
                    teclado.nextLine();

                    if (tipoVeiculo == 1) {
                    System.out.print("Tem ar-condicionado? (1) Sim  (2) Nao: ");
                    int ac = teclado.nextInt();
                    teclado.nextLine();
                    bancoDados.adicionarVeiculo(new Carro(placa, valorDiaria, ac == 1));
                    }
                    else if (tipoVeiculo == 2) {
                        System.out.print("Digite a cilindrada da moto: ");
                        int cilindrada = teclado.nextInt();
                        teclado.nextLine();
                        bancoDados.adicionarVeiculo(new Moto(placa, valorDiaria, cilindrada));
                    }
                    else{
                        System.out.println("Tipo de veículo inválido.");
                    }

                    break;

                case 2:
                    System.out.println("\n===== Listar Veículos =====");
                    bancoDados.listarVeiculos();
                    break;

                case 0:
                    System.out.println("Voltando...");
                    break;

                default:
                    System.out.println("Opção inválida.");
            }
        } while (opcao != 0);
    }

    public static void menuLocacao(Scanner teclado, BancoDados bancoDados) {
        int opcao;
        do {
            System.out.println("\n==== Menu Locação ====");
            System.out.println("1. Cadastrar Locação");
            System.out.println("2. Listar Locações sem Devolução");
            System.out.println("0. Voltar");
            System.out.print("\nEscolha uma opção: ");
            opcao = teclado.nextInt();
            teclado.nextLine();

            switch (opcao) {
                case 1:
                    System.out.println("\n==== Cadastrar Locacao ====");

                    bancoDados.listarClientes();
                    System.out.print("\nEscolha o numero do cliente: ");
                    int indiceCliente = teclado.nextInt() - 1;
                    teclado.nextLine();

                    bancoDados.listarVeiculos();
                    System.out.print("\nEscolha o numero do veiculo: ");
                    int indiceVeiculo = teclado.nextInt() - 1;
                    teclado.nextLine();

                    System.out.print("Data de retirada (dd/MM/yyyy): ");
                    String dataRetirada = teclado.nextLine();
                    System.out.print("Data de devolucao (dd/MM/yyyy): ");
                    String dataDevolucao = teclado.nextLine();

                    bancoDados.adicionarLocacao(indiceCliente, indiceVeiculo, dataRetirada, dataDevolucao);
                    break;

                case 2:
                    System.out.println("\n===== Locações sem Devolução =====");
                    bancoDados.listarSemDevolucao();
                    break;

                case 0:
                    System.out.println("Voltando...");
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        } while (opcao != 0);
    }

    public static void menuDevolucao(Scanner teclado, BancoDados bancoDados) {
    int opcao;
    do {
        System.out.println("\n==== Menu Devolucao ====");
        System.out.println("1. Realizar Devolucao");
        System.out.println("0. Voltar");
        System.out.print("\nEscolha uma opcao: ");
        opcao = teclado.nextInt();
        teclado.nextLine();

        switch (opcao) {
            case 1:
                System.out.println("\n==== Realizar Devolucao ====");

                // lista as locacoes sem devolucao
                bancoDados.listarSemDevolucao();

                if (bancoDados.getTotalLocacoes() == 0) {
                    System.out.println("Nenhuma locacao ativa.");
                    break;
                }

                System.out.print("\nDigite o numero da locacao para devolver: ");
                int indice = teclado.nextInt() - 1;
                teclado.nextLine();

                bancoDados.realizarDevolucao(indice);
                break;
            case 0:
                System.out.println("Voltando...");
                break;
            default:
                System.out.println("Opcao invalida.");
        }
    } while (opcao != 0);

    }

    public static void demonstracao(BancoDados bancoDados) {
        System.out.println("\n==== Demonstracao ====");

        bancoDados.adicionarCliente("João Silva", "111.111.111-11", "CNH-001");
        bancoDados.adicionarCliente("Maria Souza", "222.222.222-22", "CNH-002");

        bancoDados.adicionarVeiculo(new Carro("ABC-1234", 150.0, true));
        bancoDados.adicionarVeiculo(new Moto("XYZ-5678", 80.0, 300));

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        Locacao loc1 = new Locacao(
            bancoDados.getClientes()[0],
            bancoDados.getVeiculos()[0],
            LocalDate.parse("01/04/2025", formatter),
            LocalDate.parse("05/04/2025", formatter)
        );
        loc1.realizarDevolucao();

        Locacao loc2 = new Locacao(
            bancoDados.getClientes()[1],
            bancoDados.getVeiculos()[1],
            LocalDate.parse("10/04/2025", formatter),
            LocalDate.parse("15/04/2025", formatter)
        );

        bancoDados.adicionarLocacao(loc1);
        bancoDados.adicionarLocacao(loc2);

        System.out.println("\n==== Locacoes Ativas ====");
        bancoDados.listarSemDevolucao();
    }
}