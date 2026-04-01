import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class CalculadoraGabrielinha {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/mm/yyyy");

        ArrayList<Integer> quantidades = new ArrayList<>();
        ArrayList<Double> totais = new ArrayList<>();
        ArrayList<LocalDate> datas = new ArrayList<>();

        int opcao;

        do {
            System.out.println("\n=== Calculadora da Dona Gabrielinha ===");
            System.out.println("[1] - Realizar Venda");
            System.out.println("[2] - Calcular Troco");
            System.out.println("[3] - Ver Registro de Vendas");
            System.out.println("[4] - Buscar vendas por DIA");
            System.out.println("[5] - Buscar vendas por MÊS");
            System.out.println("[6] - Sair");
            System.out.print("Escolha uma opção: ");

            opcao = scanner.nextInt();
            scanner.nextLine(); 

            switch (opcao) {

                case 1:
                    System.out.print("Quantidade de plantas: ");
                    int quantidade = scanner.nextInt();

                    System.out.print("Preço unitário: ");
                    double preco = scanner.nextDouble();

                    scanner.nextLine();

                    System.out.print("Digite a data da venda (dd/mm/yyyy): ");
                    String dataTexto = scanner.nextLine();
                    LocalDate data = LocalDate.parse(dataTexto, formatter);

                    double totalSemDesconto = quantidade * preco;
                    double desconto = 0;

                    if (quantidade > 10) {
                        desconto = totalSemDesconto * 0.05;
                    }

                    double totalFinal = totalSemDesconto - desconto;

                    if (desconto > 0) {
                        System.out.println("Total sem desconto: R$ " + totalSemDesconto);
                        System.out.println("Desconto: R$ " + desconto);
                    }

                    System.out.println("Total final: R$ " + totalFinal);

                    quantidades.add(quantidade);
                    totais.add(totalFinal);
                    datas.add(data);

                    break;

                case 2:
                    System.out.print("Valor pago: ");
                    double pago = scanner.nextDouble();

                    System.out.print("Valor da compra: ");
                    double compra = scanner.nextDouble();

                    if (pago < compra) {
                        System.out.println("Valor insuficiente!");
                    } else {
                        System.out.println("Troco: R$ " + (pago - compra));
                    }
                    break;

                case 3:
                    if (quantidades.isEmpty()) {
                        System.out.println("Nenhuma venda registrada.");
                    } else {
                        System.out.println("\n=== Registro de Vendas ===");
                        for (int i = 0; i < quantidades.size(); i++) {
                            System.out.println("Data: " + datas.get(i).format(formatter));
                            System.out.println("Quantidade: " + quantidades.get(i));
                            System.out.println("Total: R$ " + totais.get(i));
                            System.out.println("----------------------");
                        }
                    }
                    break;

                case 4:
                    System.out.print("Digite a data (dd/MM/yyyy): ");
                    String buscaDiaTexto = scanner.nextLine();
                    LocalDate buscaDia = LocalDate.parse(buscaDiaTexto, formatter);

                    int contadorDia = 0;

                    for (LocalDate d : datas) {
                        if (d.equals(buscaDia)) {
                            contadorDia++;
                        }
                    }

                    System.out.println("Total de vendas no dia: " + contadorDia);
                    break;

                case 5:
                    System.out.print("Digite o mês e ano (MM/yyyy): ");
                    String mesAno = scanner.nextLine();

                    String[] partes = mesAno.split("/");
                    int mes = Integer.parseInt(partes[0]);
                    int ano = Integer.parseInt(partes[1]);

                    int contadorMes = 0;

                    for (LocalDate d : datas) {
                        if (d.getMonthValue() == mes && d.getYear() == ano) {
                            contadorMes++;
                        }
                    }

                    System.out.println("Total de vendas no mês: " + contadorMes);
                    break;

                case 6:
                    System.out.println("Encerrando...");
                    break;

                default:
                    System.out.println("Opção inválida!");
            }

        } while (opcao != 6);

        scanner.close();
    }
}