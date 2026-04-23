import java.util.Scanner;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Locadora locadora = new Locadora();

        Clientes cliente = null;
        Veiculo veiculo = null;

        int opcao;

        do {
            System.out.println("\n1 - Cadastrar cliente");
            System.out.println("2 - Cadastrar carro");
            System.out.println("3 - Cadastrar moto");
            System.out.println("4 - Cadastrar locação");
            System.out.println("5 - Realizar devolução");
            System.out.println("6 - Listar locações pendentes");
            System.out.println("7 - Demonstração");
            System.out.println("0 - Sair");

            opcao = sc.nextInt();

            switch (opcao) {

                case 1:
                    sc.nextLine();
                    System.out.print("Nome: ");
                    String nome = sc.nextLine();

                    System.out.print("CPF: ");
                    String cpf = sc.nextLine();

                    System.out.print("CNH: ");
                    String cnh = sc.nextLine();

                    cliente = new Clientes(nome, cpf, cnh);
                    System.out.println("Cliente cadastrado!");
                    break;

                case 2:
                    sc.nextLine();
                    System.out.print("Placa: ");
                    String placaC = sc.nextLine();

                    System.out.print("Valor diária: ");
                    double diariaC = sc.nextDouble();

                    System.out.print("Tem ar? (true ou false): ");
                    boolean ar = sc.nextBoolean();

                    veiculo = new Carro(placaC, diariaC, ar);
                    System.out.println("Carro cadastrado!");
                    break;

                case 3:
                    sc.nextLine();
                    System.out.print("Placa: ");
                    String placaM = sc.nextLine();

                    System.out.print("Valor diária: ");
                    double diariaM = sc.nextDouble();

                    System.out.print("Cilindrada: ");
                    int cil = sc.nextInt();

                    veiculo = new Moto(placaM, diariaM, cil);
                    System.out.println("Moto cadastrada!");
                    break;

                case 4:
                    if (cliente == null) {
                        System.out.println("Cadastre um cliente primeiro!");
                        break;
                    }
                    if (veiculo == null) {
                        System.out.println("Cadastre um veículo primeiro!");
                        break;
                    }

                    System.out.print("Dias: ");
                    int dias = sc.nextInt();

                    Locacao loc = new Locacao(cliente, veiculo, LocalDate.now(), LocalDate.now().plusDays(dias), dias);
                    locadora.addLocacao(loc);
                    System.out.println("Locação cadastrada!");
                    break;

                case 5:
                    System.out.print("Índice da locação: ");
                    int i = sc.nextInt();
                    locadora.realizarDevolucao(i);
                    System.out.println("Devolução realizada!");
                    break;

                case 6:
                    locadora.listarLocacoesPendentes();
                    break;

                case 7:
                    Clientes c1 = new Clientes("João", "111", "123");
                    Clientes c2 = new Clientes("Maria", "222", "456");

                    Carro carro = new Carro("ABC-1234", 150, true);
                    Moto moto = new Moto("XYZ-9999", 80, 300);

                    Locacao l1 = new Locacao(c1, carro, LocalDate.of(2024, 5, 1), LocalDate.of(2024, 5, 4), 3);
                    Locacao l2 = new Locacao(c2, moto, LocalDate.of(2024, 5, 10), LocalDate.of(2024, 5, 12), 2);

                    l1.realizarDevolucao();

                    locadora.addLocacao(l1);
                    locadora.addLocacao(l2);


                    locadora.listarLocacoesPendentes();
                    break;

                case 0:
                    System.out.println("Encerrando!");
                    break;
            }

        } while (opcao != 0);

        sc.close();
    }
}