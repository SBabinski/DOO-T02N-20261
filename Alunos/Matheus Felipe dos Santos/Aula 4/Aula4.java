import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

class Venda {
    int quantidade;
    double total;
    double desconto;
    LocalDate data;

    public Venda(int quantidade, double total, double desconto, LocalDate data) {
        this.quantidade = quantidade;
        this.total = total;
        this.desconto = desconto;
        this.data = data;
    }
}

public class Aula04 {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        ArrayList<Venda> vendas = new ArrayList<>();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        int opcao = 0;

        while (opcao != 6) {

            System.out.println("\n===== LOJA DONA GABRIELINHA =====");
            System.out.println("[1] - Realizar Venda");
            System.out.println("[2] - Ver Todas Vendas");
            System.out.println("[3] - Buscar Vendas por DIA");
            System.out.println("[4] - Buscar Vendas por MÊS");
            System.out.println("[5] - Calcular Troco");
            System.out.println("[6] - Sair");
            System.out.print("Escolha: ");

            opcao = scanner.nextInt();

            switch (opcao) {

                case 1:
                    System.out.print("Quantidade: ");
                    int quantidade = scanner.nextInt();

                    System.out.print("Preço unitário: ");
                    double preco = scanner.nextDouble();

                    scanner.nextLine(); // limpar buffer

                    System.out.print("Data (dd/MM/yyyy): ");
                    String dataStr = scanner.nextLine();

                    LocalDate data = LocalDate.parse(dataStr, formatter);

                    double total = quantidade * preco;
                    double desconto = 0;

                    if (quantidade > 10) {
                        desconto = total * 0.05;
                        total -= desconto;
                        System.out.println("Desconto de 5% aplicado!");
                    }

                    vendas.add(new Venda(quantidade, total, desconto, data));

                    System.out.println("Total: R$ " + total);

                    break;

                case 2:
                    System.out.println("\n=== TODAS AS VENDAS ===");

                    for (Venda v : vendas) {
                        System.out.println("Data: " + v.data.format(formatter) +
                                " | Qtd: " + v.quantidade +
                                " | Total: R$ " + v.total +
                                " | Desconto: R$ " + v.desconto);
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

                    System.out.println("Total de vendas no dia: " + totalDia);
                    break;

                case 4:
                    System.out.print("Digite o mês (1-12): ");
                    int mes = scanner.nextInt();

                    System.out.print("Digite o ano: ");
                    int ano = scanner.nextInt();

                    int totalMes = 0;

                    for (Venda v : vendas) {
                        if (v.data.getMonthValue() == mes && v.data.getYear() == ano) {
                            totalMes += v.quantidade;
                        }
                    }

                    System.out.println("Total de vendas no mês: " + totalMes);
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
                    System.out.println("Saindo...");
                    break;

                default:
                    System.out.println("Opção inválida!");
            }
        }

        scanner.close();
    }
}