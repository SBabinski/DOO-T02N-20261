import java.util.Scanner;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Aula04 {

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        ArrayList<Venda> vendas = new ArrayList<>();

        double total = 0;
        int qtd = 0;
        double valor = 0;
        double desconto = 0;

        int opc = 0;

        while (opc != 6) {
            System.out.println("----------------------------------");
            System.out.println("1 - Calcular Preço Total");
            System.out.println("2 - Calcular Troco");
            System.out.println("3 - Listar Vendas");
            System.out.println("4 - Buscar Vendas por Dia");
            System.out.println("5 - Buscar Vendas por Mês");
            System.out.println("6 - Sair");
            opc = scan.nextInt();
            scan.nextLine();

            switch (opc) {
                case 1:
                    System.out.println("Quantidade:");
                    qtd = scan.nextInt();
                    scan.nextLine();

                    System.out.println("Valor:");
                    valor = scan.nextDouble();
                    scan.nextLine();

                    total = qtd * valor;
                    desconto = 0;

                    if (qtd > 10) {
                        desconto = total * 0.05;
                        total = total - desconto;
                    }

                    System.out.println("Total: " + total);
                    break;

                case 2:
                    System.out.println("Valor pago:");
                    double pago = scan.nextDouble();
                    scan.nextLine();

                    double troco = pago - total;

                    if (troco < 0) {
                        System.out.println("Saldo insuficiente");
                        break;
                    }

                    System.out.println("Troco: " + troco);

                    LocalDate data = LocalDate.now();
                    Venda v = new Venda(total, valor, qtd, desconto, data);
                    vendas.add(v);
                    break;

                case 3:
                    int count = 0;
                    DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");

                    for (Venda venda : vendas) {
                        count++;
                        System.out.println("----------------------");
                        System.out.println(count + " Venda");
                        System.out.println("Data: " + venda.data.format(fmt));
                        System.out.println("Total: " + venda.total);
                    }
                    break;

                case 4:
                    System.out.println("Digite o dia (dd/MM/yyyy):");
                    String diaStr = scan.nextLine();
                    LocalDate dia = LocalDate.parse(diaStr, DateTimeFormatter.ofPattern("dd/MM/yyyy"));

                    double totalDia = 0;

                    for (Venda venda : vendas) {
                        if (venda.data.equals(dia)) {
                            totalDia += venda.total;
                        }
                    }

                    System.out.println("Total no dia: " + totalDia);
                    break;

                case 5:
                    System.out.println("Digite o mês e ano (MM/yyyy):");
                    String mesStr = scan.nextLine();
                    DateTimeFormatter fmtMes = DateTimeFormatter.ofPattern("MM/yyyy");

                    double totalMes = 0;

                    for (Venda venda : vendas) {
                        String vendaMes = venda.data.format(fmtMes);
                        if (vendaMes.equals(mesStr)) {
                            totalMes += venda.total;
                        }
                    }

                    System.out.println("Total no mês: " + totalMes);
                    break;

                case 6:
                    System.out.println("Saindo...");
                    break;

                default:
                    System.out.println("Opção inválida");
                    break;
            }
        }

        scan.close();
    }
}