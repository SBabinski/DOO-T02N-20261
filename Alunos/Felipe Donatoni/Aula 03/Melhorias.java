import java.util.Scanner;
public class Melhorias {
    public static void main (String [] agrs ) {

        Scanner scanner = new Scanner (System.in);

        int option = 0;
        int totalquantidade = 0;
        double totalvenda = 0;
        double totaldesconto = 0;

        while (option != 4) {

            System.out.println ("Menu");
            System.out.println ("1 - Calcular total");
            System.out.println ("2 - Calcular troco");
            System.out.println ("3 - Registro de vendas");
            System.out.println ("4 - Sair");
            option = scanner.nextInt();

            switch (option) {
                case 1:
                    System.out.println ("Digite a quantidade de plantas?");
                    double quantidade = scanner.nextDouble();

                    System.out.println ("Digite o valor da planta?");
                    double price = scanner.nextDouble();

                    double total = quantidade * price;
                    double desconto = 0;

                    if (quantidade > 10) {
                        desconto = total * 0.05;
                        total = total - desconto;
                        System.out.println ("Desconto aplicado: " + desconto);
                    }

                    System.out.println ("Valor total da venda: " + total);

                    totalquantidade = totalquantidade + (int) quantidade;
                    totalvenda = totalvenda + total;
                    totaldesconto = totaldesconto + desconto;
                    break;

                case 2:
                    System.out.println ("Valor recebido pelo cliente: ");
                    double recebido = scanner.nextDouble();

                    System.out.println ("Valor total da venda foi: ");
                    double totalcase2 = scanner.nextDouble();

                    double troco = recebido - totalcase2;
                    System.out.println ("Troco do cliente: " + troco);
                    break;

                case 3:
                    System.out.println ("Registro de vendas");
                    System.out.println ("Quantidade de plantas vendidas: " + totalquantidade);
                    System.out.println ("Valor total das vendas: " + totalvenda);
                    System.out.println ("Total de desconto aplicado: " + totaldesconto);
                    break;

                case 4:
                    System.out.println ("Programa encerrado");
                    break;

                default:
                    System.out.println ("Option inválida");
            }
        }
        scanner.close();
    }
}