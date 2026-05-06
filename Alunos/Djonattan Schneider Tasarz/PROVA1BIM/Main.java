import java.time.LocalDate;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        SistemaLocadora locadora = new SistemaLocadora();
        Scanner sc = new Scanner(System.in);
        int opcao;

        demonstracao(locadora);

        do {
            System.out.println("\n=== SISTEMA DE ALUGUÉIS DE VEÍCULOS ===");
            System.out.println("1. Cadastrar cliente");
            System.out.println("2. Cadastrar carro");
            System.out.println("3. Cadastrar moto");
            System.out.println("4. Cadastrar locação");
            System.out.println("5. Realizar devolução");
            System.out.println("6. Listar locações sem devolução");
            System.out.println("7. Fazer nova demonstração");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");
            
            opcao = sc.nextInt();
            sc.nextLine();
            
            switch(opcao) {
                case 1:
                    cadastrarCliente(sc, locadora);
                    break;
                case 2:
                    cadastrarCarro(sc, locadora);
                    break;
                case 3:
                    cadastrarMoto(sc, locadora);
                    break;
                case 4:
                    cadastrarLocacao(sc, locadora);
                    break;
                case 5:
                    realizarDevolucao(sc, locadora);
                    break;
                case 6:
                    locadora.listarLocacoesSemDevolucao();
                    break;
                case 7:
                    demonstracao(locadora);
                    break;
                case 0:
                    System.out.println("Encerrando o sistema.");
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        } while(opcao != 0);
        
        sc.close();
    }

    private static void cadastrarCliente(Scanner sc, SistemaLocadora locadora) {
        System.out.print("Nome: ");
        String nome = sc.nextLine();
        System.out.print("CPF: ");
        String cpf = sc.nextLine();
        System.out.print("CNH: ");
        String cnh = sc.nextLine();
        locadora.cadastrarCliente(new Cliente(nome, cpf, cnh));
        System.out.println("Cliente cadastrado com sucesso.");
    }

    private static void cadastrarCarro(Scanner sc, SistemaLocadora locadora) {
        System.out.print("Placa: ");
        String placa = sc.nextLine();
        System.out.print("Valor da diária: ");
        double valorDiaria = sc.nextDouble();
        System.out.print("Tem ar-condicionado? (1-Sim / 0-Não): ");
        boolean arCondicionado = sc.nextInt() == 1;
        locadora.cadastrarVeiculo(new Carro(placa, valorDiaria, arCondicionado));
        System.out.println("Carro cadastrado com sucesso.");
    }

    private static void cadastrarMoto(Scanner sc, SistemaLocadora locadora) {
        System.out.print("Placa: ");
        String placa = sc.nextLine();
        System.out.print("Valor da diária: ");
        double valorDiaria = sc.nextDouble();
        System.out.print("Cilindrada: ");
        int cilindrada = sc.nextInt();
        locadora.cadastrarVeiculo(new Moto(placa, valorDiaria, cilindrada));
        System.out.println("Moto cadastrada com sucesso.");
    }

    private static void cadastrarLocacao(Scanner sc, SistemaLocadora locadora) {
        System.out.print("Índice do cliente (0 a " + (locadora.getClientes().length-1) + "): ");
        int idxCliente = sc.nextInt();
        System.out.print("Índice do veículo (0 a " + (locadora.getVeiculos().length-1) + "): ");
        int idxVeiculo = sc.nextInt();

        System.out.print("Dia da retirada: ");
        int diaRetirada = sc.nextInt();
        System.out.print("Mês da retirada: ");
        int mesRetirada = sc.nextInt();
        System.out.print("Ano da retirada: ");
        int anoRetirada = sc.nextInt();

        System.out.print("Dia da devolução: ");
        int diaDevolucao = sc.nextInt();
        System.out.print("Mês da devolução: ");
        int mesDevolucao = sc.nextInt();
        System.out.print("Ano da devolução: ");
        int anoDevolucao = sc.nextInt();

        LocalDate retirada = LocalDate.of(anoRetirada, mesRetirada, diaRetirada);
        LocalDate devolucao = LocalDate.of(anoDevolucao, mesDevolucao, diaDevolucao);

        boolean sucesso = locadora.cadastrarLocacao(
            locadora.getClientes()[idxCliente],
            locadora.getVeiculos()[idxVeiculo],
            retirada,
            devolucao
        );

        if (sucesso) {
            System.out.println("Locação cadastrada com sucesso.");
        } else {
            System.out.println("Erro ao cadastrar locação (sem espaço disponível).");
        }
    }

    private static void realizarDevolucao(Scanner sc, SistemaLocadora locadora) {
        System.out.print("Índice da locação (0 a 9): ");
        int idx = sc.nextInt();
        locadora.realizarDevolucao(idx);
    }

    private static void demonstracao(SistemaLocadora locadora) {
        Cliente c1 = new Cliente("João Silva", "111.222.333-44", "CNH123456");
        Cliente c2 = new Cliente("Ana Souza", "555.666.777-88", "CNH987654");

        Carro carro = new Carro("ABC-1234", 150.0, true);
        Moto moto  = new Moto("XYZ-5678", 80.0, 160);

        locadora.cadastrarCliente(c1);
        locadora.cadastrarCliente(c2);
        locadora.cadastrarVeiculo(carro);
        locadora.cadastrarVeiculo(moto);

        locadora.cadastrarLocacao(
            c1, carro,
            LocalDate.of(2026, 4, 1),
            LocalDate.of(2026, 4, 5)
        );

        locadora.cadastrarLocacao(
            c2, moto,
            LocalDate.of(2026, 4, 10),
            LocalDate.of(2026, 4, 15)
        );

        System.out.println("\n=== Demonstração inicial concluída ===");
        System.out.println("Duas locações foram registradas (1 carro e 1 moto).");
        System.out.println("A locação do carro já teve devolução realizada.");
        System.out.println("A locação da moto está ativa (sem devolução).");
        System.out.println("Segue abaixo a lista de locações ativas (sem devolução):");
        locadora.listarLocacoesSemDevolucao();
    }
}