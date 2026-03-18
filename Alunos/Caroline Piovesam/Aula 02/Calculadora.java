
import java.util.Scanner;

public class Calculadora {

    Scanner sc = new Scanner(System.in);
    double totalCompra = 0;

    public double calcularPrecoTotal() {

        System.out.println("Entre com a quantidade de plantas a serem compradas: ");
        int quantidade = sc.nextInt();
        sc.nextLine();

        if (quantidade <= 0) {
            System.out.println("Quantidade inválida!");
            return 0;
        }

        System.out.println("Entre com o preço por unidade: ");
        double precoUnidade = sc.nextDouble();
        sc.nextLine();

        if (precoUnidade <= 0) {
            System.out.println("Preço inválido!");
            return 0;
        }

        totalCompra = quantidade * precoUnidade;

        System.out.printf("Valor total a pagar: R$ %.2f ", totalCompra);

        return totalCompra;
    }

    public double calcularTroco() {

        System.out.println("Entre com o total da compra: ");
        double totalCompra = sc.nextDouble();
        sc.nextLine();

        if (totalCompra <= 0) {
            System.out.println("Valor da compra inválido!");
            return 0;
        }

        System.out.println("Entre com o total pago pelo cliente: ");
        double pagamentoCliente = sc.nextDouble();
        sc.nextLine();

        if (pagamentoCliente < totalCompra) {
            System.out.println("Valor insuficiente!");
            return 0;
        } else {
            double troco = pagamentoCliente - totalCompra;
            System.out.printf("O troco deverá ser de: R$ %.2f ", troco);
            return troco;
        }
    }
}