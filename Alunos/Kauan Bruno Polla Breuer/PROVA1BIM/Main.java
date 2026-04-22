import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static final Locadora locadora = new Locadora("Locadora Prova");

    public static void main(String[] args) {
        int opcao;

        do {
            exibirMenu();
            opcao = lerInteiro("Escolha uma opcao: ");

            switch (opcao) {
                case 1:
                    cadastrarCliente();
                    break;
                case 2:
                    cadastrarVeiculo();
                    break;
                case 3:
                    cadastrarLocacao();
                    break;
                case 4:
                    realizarDevolucao();
                    break;
                case 5:
                    locadora.listarLocacoesSemDevolucao();
                    break;
                case 6:
                    locadora.demonstracao();
                    break;
                case 7:
                    System.out.println("Encerrando o sistema.");
                    break;
                default:
                    System.out.println("Opcao invalida.");
            }
        } while (opcao != 7);

        scanner.close();
    }

    private static void exibirMenu() {
        System.out.println("\n=== Locadora Prova ===");
        System.out.println("[1] Cadastrar cliente");
        System.out.println("[2] Cadastrar veiculo");
        System.out.println("[3] Cadastrar locacao");
        System.out.println("[4] Realizar devolucao");
        System.out.println("[5] Listar locacoes sem devolucao");
        System.out.println("[6] Demonstracao");
        System.out.println("[7] Sair");
    }

    private static void cadastrarCliente() {
        String nome = lerTexto("Nome do cliente: ");
        String cpf = lerTexto("CPF: ");
        String numeroCnh = lerTexto("Número da CNH: ");

        Cliente cliente = new Cliente(nome, cpf, numeroCnh);
        locadora.cadastrarCliente(cliente);

        System.out.println("Cliente cadastrado com sucesso.");
    }

    private static void cadastrarVeiculo() {
        System.out.println("Tipo de veículo:");
        System.out.println("1 - Carro");
        System.out.println("2 - Moto");

        int tipo = lerInteiro("Escolha: ");
        String placa = lerTexto("Placa: ");
        double valorDiaria = lerDouble("Valor da diária: ");

        if (tipo == 1) {
            boolean arCondicionado = lerTexto("Possui ar-condicionado? (s/n): ").equalsIgnoreCase("s");
            locadora.cadastrarVeiculo(new Carro(placa, valorDiaria, arCondicionado));
            System.out.println("Carro cadastrado com sucesso.");
        } else if (tipo == 2) {
            int cilindrada = lerInteiro("Cilindrada: ");
            locadora.cadastrarVeiculo(new Moto(placa, valorDiaria, cilindrada));
            System.out.println("Moto cadastrada com sucesso.");
        } else {
            System.out.println("Tipo invalido.");
        }
    }

    private static void cadastrarLocacao() {
        if (locadora.getQuantidadeClientes() == 0 || locadora.getQuantidadeVeiculos() == 0) {
            System.out.println("E necessario ter pelo menos um cliente e um veiculo cadastrados.");
            return;
        }

        System.out.println("Clientes cadastrados:");
        locadora.listarClientes();

        int indiceCliente = lerInteiro("Informe o indice do cliente: ");
        Cliente cliente = locadora.getCliente(indiceCliente);

        if (cliente == null) {
            System.out.println("Cliente invalido.");
            return;
        }

        System.out.println("Veiculos cadastrados:");
        locadora.listarVeiculos();

        int indiceVeiculo = lerInteiro("Informe o indice do veiculo: ");
        Veiculo veiculo = locadora.getVeiculo(indiceVeiculo);

        if (veiculo == null) {
            System.out.println("Veiculo invalido.");
            return;
        }

        LocalDate retirada = lerData("Data da retirada (dd/MM/yyyy): ");
        LocalDate devolucao = lerData("Data da devolucao (dd/MM/yyyy): ");

        Locacao locacao = new Locacao(cliente, veiculo, retirada, devolucao);

        if (locadora.adicionarLocacao(locacao)) {
            System.out.println("Locacao cadastrada com sucesso.");
            System.out.printf("Valor total previsto: R$ %.2f%n", locacao.calcularValorTotal());
        }
    }

    private static void realizarDevolucao() {
        if (locadora.getQuantidadeLocacoes() == 0) {
            System.out.println("Nao ha locacoes cadastradas.");
            return;
        }

        locadora.listarTodasLocacoes();

        int indiceLocacao = lerInteiro("Informe o indice da locacao: ");
        LocalDate dataDevolucao = lerData("Data da devolucao (dd/MM/yyyy): ");

        locadora.realizarDevolucao(indiceLocacao, dataDevolucao);
    }

    private static String lerTexto(String mensagem) {
        System.out.print(mensagem);
        return scanner.nextLine().trim();
    }

    private static int lerInteiro(String mensagem) {
        while (true) {
            System.out.print(mensagem);
            String entrada = scanner.nextLine().trim();

            try {
                return Integer.parseInt(entrada);
            } catch (NumberFormatException e) {
                System.out.println("Valor inteiro invalido.");
            }
        }
    }

    private static double lerDouble(String mensagem) {
        while (true) {
            System.out.print(mensagem);
            String entrada = scanner.nextLine().trim().replace(",", ".");

            try {
                return Double.parseDouble(entrada);
            } catch (NumberFormatException e) {
                System.out.println("Valor numerico invalido.");
            }
        }
    }

    private static LocalDate lerData(String mensagem) {
        while (true) {
            System.out.print(mensagem);
            String entrada = scanner.nextLine().trim();

            try {
                return LocalDate.parse(entrada, formatter);
            } catch (DateTimeParseException e) {
                System.out.println("Data invalida. Use o formato dd/MM/yyyy.");
            }
        }
    }
}