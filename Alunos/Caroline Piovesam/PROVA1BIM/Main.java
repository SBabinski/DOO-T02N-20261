import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Locadora locadora = new Locadora();
        ArrayList<Cliente> clientes = new ArrayList<>();
        ArrayList<Veiculo> veiculos = new ArrayList<>();

        int opcao;

        do {
            System.out.println("\n=== Menu Locadora ===");
            System.out.println("1 - Cadastrar cliente");
            System.out.println("2 - Cadastrar veículo");
            System.out.println("3 - Cadastrar locação");
            System.out.println("4 - Realizar devolução");
            System.out.println("5 - Listar locações pendentes");
            System.out.println("6 - Demonstração");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");

            opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {
                case 1:
                    System.out.print("Nome Cliente: ");
                    String nome = sc.nextLine();
                    System.out.print("Número da CNH: ");
                    String numCnh = sc.nextLine();
                    System.out.print("CPF: ");
                    String cpf = sc.nextLine();

                    clientes.add(new Cliente(nome, numCnh, cpf));
                    System.out.println("Cliente cadastrado com sucesso!");
                    break;

                case 2:
                    System.out.println("Tipo de veículo: 1 - Moto | 2 - Carro");
                    int tipo = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Placa: ");
                    String placa = sc.nextLine();
                    System.out.print("Valor Diária: ");
                    double valorDiaria = sc.nextDouble();
                    sc.nextLine();

                    if (tipo == 1) {
                        System.out.print("Cilindradas: ");
                        int cilindradas = sc.nextInt();
                        sc.nextLine();
                        veiculos.add(new Moto(placa, valorDiaria, cilindradas));
                    } else {
                        System.out.print("Possui ar condicionado? (true/false): ");
                        boolean ar = sc.nextBoolean();
                        sc.nextLine();
                        veiculos.add(new Carro(placa, valorDiaria, ar));
                    }
                    System.out.println("Veículo cadastrado!");
                    break;

                case 3:
                    if (clientes.isEmpty() || veiculos.isEmpty()) {
                        System.out.println("Erro, cadastre pelo menos um cliente e um veículo primeiro!");
                        break;
                    }

                    System.out.println("\n===Selecione o Clientee===");
                    for (int i = 0; i < clientes.size(); i++) {
                        System.out.println(i + " - " + clientes.get(i).getNome());
                    }
                    int iCliente = sc.nextInt();

                    System.out.println("\n===Selecione o Veículo===");
                    for (int i = 0; i < veiculos.size(); i++) {
                        System.out.print(i + " - ");
                        veiculos.get(i).mostrarDados();
                    }
                    int iVeiculo = sc.nextInt();

                    System.out.print("Quantos dias de locação? ");
                    int dias = sc.nextInt();
                    sc.nextLine();

                    LocalDateTime retirada = LocalDateTime.now();
                    LocalDateTime devolucao = retirada.plusDays(dias);

                    Locacao novaLocacao = new Locacao(clientes.get(iCliente), veiculos.get(iVeiculo),
                            retirada, devolucao);
                    locadora.adicionarLocacao(novaLocacao);
                    break;

                case 4:
                    System.out.println("\n===Realizar Devolução===");
                    ArrayList<Locacao> lista = locadora.getLocacoes();
                    boolean temPendentes = false;

                    for (int i = 0; i < lista.size(); i++) {
                        if (!lista.get(i).isDevolvido()) {
                            System.out.print(i + " - ");
                            lista.get(i).mostrarDados();
                            temPendentes = true;
                        }
                    }

                    if (temPendentes) {
                        System.out.print("Digite o índice da locação para devolver: ");
                        int i = sc.nextInt();
                        if (i >= 0 && i < lista.size()) {
                            lista.get(i).realizarDevolucao();
                        } else {
                            System.out.println("Índice inválido.");
                        }
                    } else {
                        System.out.println("Não há locações pendentes.");
                    }
                    break;

                case 5:
                    locadora.listarPendentes();
                    break;

                case 6:
                    System.out.println("\n===Demonstração===");
                    Cliente demoC1 = new Cliente("Conan", "1234556", "1475289641032");
                    Cliente demoC2 = new Cliente("Carool", "456478", "55554621789");

                    Moto demoMoto = new Moto("1245abc", 80.0, 300);
                    Carro demoCarro = new Carro("456abc", 150.0,
                            true);

                    Locacao loc1 = new Locacao(demoC1, demoMoto, LocalDateTime.now().minusDays(2),
                            LocalDateTime.now());
                    loc1.realizarDevolucao();

                    Locacao loc2 = new Locacao(demoC2, demoCarro, LocalDateTime.now(),
                            LocalDateTime.now().plusDays(5));

                    locadora.adicionarLocacao(loc1);
                    locadora.adicionarLocacao(loc2);

                    System.out.println("\n===Locações Pendentes===");
                    locadora.listarPendentes();
                    break;

                case 0:
                    System.out.println("Saindo...");
                    break;

                default:
                    System.out.println("Opção inválida!");
            }
        } while (opcao != 0);

        sc.close();
    }
}