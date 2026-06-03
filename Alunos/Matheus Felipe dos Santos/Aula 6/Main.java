import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        ArrayList<Venda> vendas = new ArrayList<>();

        Loja loja = new Loja("Loja Gabrielinha", "123456", "Cascavel", "Centro", "Rua A");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        int opcao = 0;

        while (opcao != 8) {

            System.out.println("\n===== MENU =====");
            System.out.println("[1] - Realizar Venda");
            System.out.println("[2] - Ver Todas Vendas");
            System.out.println("[3] - Buscar Vendas por DIA");
            System.out.println("[4] - Buscar Vendas por MÊS");
            System.out.println("[5] - Calcular Troco");
            System.out.println("[6] - Cadastrar Cliente");
            System.out.println("[7] - Cadastrar Vendedor");
            System.out.println("[8] - Sair");

            System.out.print("Escolha: ");
            opcao = scanner.nextInt();

            switch (opcao) {

                case 1:

                    if (loja.clientes.isEmpty() || loja.vendedores.isEmpty()) {
                        System.out.println("Cadastre pelo menos 1 cliente e 1 vendedor!");
                        break;
                    }

                    System.out.print("Quantidade: ");
                    int quantidade = scanner.nextInt();

                    System.out.print("Preço unitário: ");
                    double preco = scanner.nextDouble();

                    scanner.nextLine();

                    System.out.print("Data (dd/MM/yyyy): ");
                    String dataStr = scanner.nextLine();

                    LocalDate data = LocalDate.parse(dataStr, formatter);

                    Venda venda = new Venda(quantidade, data);
                    venda.calcularTotal(preco);

                    vendas.add(venda);

                    System.out.println("Total: R$ " + venda.total);

                    break;

                case 2:
                    System.out.println("\n=== TODAS AS VENDAS ===");

                    for (Venda v : vendas) {
                        v.apresentar(formatter); 
                    }
                    break;

                case 3:
                    scanner.nextLine();

                    System.out.print("Digite a data (dd/MM/yyyy): ");
                    String buscaDia = scanner.nextLine();

                    LocalDate dataBusca = LocalDate.parse(buscaDia, formatter);

                    int totalDia = 0;

                    for (Venda v : vendas) {
                        if (v.data.equals(dataBusca)) {
                            totalDia += v.quantidade;
                        }
                    }

                    System.out.println("Total no dia: " + totalDia);
                    break;

                case 4:
                    System.out.print("Mês (1-12): ");
                    int mes = scanner.nextInt();

                    System.out.print("Ano: ");
                    int ano = scanner.nextInt();

                    int totalMes = 0;

                    for (Venda v : vendas) {
                        if (v.data.getMonthValue() == mes && v.data.getYear() == ano) {
                            totalMes += v.quantidade;
                        }
                    }

                    System.out.println("Total no mês: " + totalMes);
                    break;

                case 5:
                    System.out.print("Valor pago: ");
                    double pago = scanner.nextDouble();

                    System.out.print("Valor da compra: ");
                    double compra = scanner.nextDouble();

                    double troco = pago - compra;

                    if (troco < 0) {
                        System.out.println("Falta: R$ " + (-troco));
                    } else {
                        System.out.println("Troco: R$ " + troco);
                    }
                    break;

                case 6:
                    scanner.nextLine();

                    System.out.print("Nome: ");
                    String nomeCliente = scanner.nextLine();

                    System.out.print("Idade: ");
                    int idadeCliente = scanner.nextInt();
                    scanner.nextLine();

                    System.out.print("Cidade: ");
                    String cidade = scanner.nextLine();

                    System.out.print("Bairro: ");
                    String bairro = scanner.nextLine();

                    System.out.print("Rua: ");
                    String rua = scanner.nextLine();

                    Cliente cliente = new Cliente(nomeCliente, idadeCliente, cidade, bairro, rua);
                    loja.clientes.add(cliente);

                    System.out.println("Cliente cadastrado!");
                    break;

                case 7:
                    scanner.nextLine();

                    System.out.print("Nome: ");
                    String nomeVend = scanner.nextLine();

                    System.out.print("Idade: ");
                    int idadeVend = scanner.nextInt();
                    scanner.nextLine();

                    System.out.print("Cidade: ");
                    String cidadeVend = scanner.nextLine();

                    System.out.print("Bairro: ");
                    String bairroVend = scanner.nextLine();

                    System.out.print("Rua: ");
                    String ruaVend = scanner.nextLine();

                    System.out.print("Salário base: ");
                    double salario = scanner.nextDouble();

                    Vendedor vendedor = new Vendedor(
                            nomeVend, idadeVend, cidadeVend, bairroVend, ruaVend, salario, loja.nomeFantasia
                    );

                    loja.vendedores.add(vendedor);

                    System.out.println("Vendedor cadastrado!");
                    break;

                case 8:
                    System.out.println("Saindo...");
                    break;

                default:
                    System.out.println("Opção inválida!");
            }
        }

        scanner.close();
    }
}