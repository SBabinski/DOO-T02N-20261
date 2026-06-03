import java.util.Scanner;
import java.util.HashMap;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Datavendas {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int option = 0;
        int totalquantidade = 0;
        double totalvenda = 0;
        double totaldesconto = 0;

        HashMap<LocalDate, Integer> vendaspordia = new HashMap<>();

        while (option != 5) {

            System.out.println("Menu");
            System.out.println("1 - Calcular total");
            System.out.println("2 - Calcular troco");
            System.out.println("3 - Registro de vendas");
            System.out.println("4 - Consultar vendas por data");
            System.out.println("5 - Sair");

            option = scanner.nextInt();

            switch (option) {

                case 1:
                    System.out.println("Digite a quantidade de plantas?");
                    int quantidade = scanner.nextInt();

                    System.out.println("Digite o valor da planta?");
                    double price = scanner.nextDouble();

                    double total = quantidade * price;
                    double desconto = 0;

                    if (quantidade > 10) {
                        desconto = total * 0.05;
                        total = total - desconto;
                        System.out.println("Desconto aplicado: " + desconto);
                    }

                    System.out.println("Valor total da venda: " + total);

                    totalquantidade = totalquantidade + quantidade;
                    totalvenda = totalvenda + total;
                    totaldesconto = totaldesconto + desconto;

                    LocalDate hoje = LocalDate.now();
                    vendaspordia.put(hoje,
                            vendaspordia.getOrDefault(hoje, 0) + quantidade);

                    break;

                case 2:
                    System.out.println("Valor recebido pelo cliente: ");
                    double recebido = scanner.nextDouble();

                    System.out.println("Valor total da venda foi: ");
                    double totalcase2 = scanner.nextDouble();

                    double troco = recebido - totalcase2;
                    System.out.println("Troco do cliente: " + troco);
                    break;

                case 3:
                    System.out.println("Registro de vendas");
                    System.out.println("Quantidade de plantas vendidas: " + totalquantidade);
                    System.out.println("Valor total das vendas: " + totalvenda);
                    System.out.println("Total de desconto aplicado: " + totaldesconto);
                    break;

                case 4:
                    System.out.println("Digite a data (dd/mm/yyyy): ");
                    String datatexto = scanner.next();

                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                    LocalDate data = LocalDate.parse(datatexto, formatter);

                    int totaldia = vendaspordia.getOrDefault(data, 0);

                    System.out.println("Total vendido nesse dia: " + totaldia);
                    break;

                case 5:
                    System.out.println("Programa encerrado");
                    break;

                default:
                    System.out.println("Opção inválida");
            }
        }

        scanner.close();
    }
}