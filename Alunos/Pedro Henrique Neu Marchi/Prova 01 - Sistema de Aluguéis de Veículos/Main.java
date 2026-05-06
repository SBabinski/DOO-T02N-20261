import java.time.LocalDate;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Locadora locadora = new Locadora();
        int opcao;

        do {
            System.out.println("Menu:");
            System.out.println("1. Cadastrar Cliente");
            System.out.println("2. Cadastrar Veículo");
            System.out.println("3. Realizar Locação");
            System.out.println("4. Devolução Locação");
            System.out.println("5. Exibir Locações pendentes");
            System.out.println("6. Executar Demonstração");
            System.out.println("7. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine(); 


            switch (opcao) {
                case 1:
                    Clientes cliente = new Clientes();
                    System.out.print("Nome: ");
                    cliente.nome = scanner.nextLine();
                    System.out.print("CPF: ");
                    cliente.cpf = scanner.nextLine();
                    System.out.print("CNH: ");
                    cliente.CNH = scanner.nextLine();
                    locadora.adicionarCliente(cliente);
                    break;

                case 2:
                    System.out.println("Tipo de Veículo (1 - Carro, 2 - Moto): ");
                    int tipoVeiculo = scanner.nextInt();
                    scanner.nextLine(); 
                    if (tipoVeiculo == 1) {
                        Carro carro = new Carro();
                        System.out.print("Placa: ");
                        carro.placa = scanner.nextLine();
                        System.out.print("Valor de Locação: ");
                        carro.valorLocacao = scanner.nextDouble();
                        System.out.print("Possui Ar Condicionado (true/false): ");
                        carro.arCondicionado = scanner.nextBoolean();
                        locadora.cadastrarVeiculo(carro);
                    } else if (tipoVeiculo == 2) {
                        Moto moto = new Moto();
                        System.out.print("Placa: ");
                        moto.placa = scanner.nextLine();
                        System.out.print("Valor de Locação: ");
                        moto.valorLocacao = scanner.nextDouble();
                        System.out.print("Cilindradas: ");
                        moto.cilindradas = scanner.nextInt();
                        locadora.cadastrarVeiculo(moto);
                    }
                    break;

                case 3:
                    Locacao locacao = new Locacao();
                    System.out.print("CPF do Cliente: ");
                    String cpfCliente = scanner.nextLine();
                    locacao.clientes = locadora.buscarCliente(cpfCliente);
                    if (locacao.clientes == null) {
                        System.out.println("Cliente não encontrado.");
                        break;
                    }
                    System.out.print("Placa do Veículo: ");
                    String placaVeiculo = scanner.nextLine();
                    locacao.veiculo = locadora.buscarVeiculo(placaVeiculo);
                    if (locacao.veiculo == null) {
                        System.out.println("Veículo não encontrado.");
                        break;
                    }
                    System.out.print("Data de Locação (YYYY-MM-DD): ");
                    locacao.dataLocacao = LocalDate.parse(scanner.nextLine());
                    System.out.print("Dias de Locação: ");
                    locacao.diasLocacao = scanner.nextInt();
                    locacao.valorTotal = locacao.veiculo.valorLocacao * locacao.diasLocacao;
                    locadora.adicionarLocacao(locacao);
                    break;

                case 4:
                    System.out.print("CPF do Cliente para Devolução: ");
                    String cpfDevolucao = scanner.nextLine();
                    locadora.devolverLocacao(cpfDevolucao);
                    break;

                case 5:
                    System.out.print("Exibir Locações não devolvidas (true/false): ");
                    locadora.listarPendentes();
                    break;

                case 6:
                Demo.executarDemo(locadora);
                    break;

                case 7:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }

        } while (opcao != 7);
        scanner.close();
    }
}