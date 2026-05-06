import java.util.Scanner;
import java.util.ArrayList;

public class Calculadora {

    Scanner sc = new Scanner(System.in);
    ArrayList<String> vendas = new ArrayList<>();

    double totalCompra = 0;
    int quantidade = 0;
    double VAR_DESCONTO = 0.95;

    public double calcularPrecoTotal() {

        System.out.println("Entre com a quantidade de plantas a serem compradas: ");
        quantidade = sc.nextInt();
        sc.nextLine();

        if (quantidade <= 0) {
            System.out.println("Quantidade inválida!");
            System.out.println();
            return 0;
        }

        System.out.println("Entre com o preço por unidade: ");
        double precoUnidade = sc.nextDouble();
        sc.nextLine();

        if (precoUnidade <= 0) {
            System.out.println("Preço inválido!");
            System.out.println();
            return 0;
        }

        totalCompra = quantidade * precoUnidade;

        if (quantidade > 10) {
            totalCompra *= VAR_DESCONTO;
        }

        System.out.printf("Valor total a pagar: R$ %.2f ", totalCompra);
        System.out.println();

        vendas.add(String.format(
                "Qtd: %d | Total: R$ %.2f ", quantidade, totalCompra));
        System.out.println();

        return totalCompra;

    }

    public double calcularTroco() {

        System.out.println("Entre com o total da compra: ");
        double totalCompra = sc.nextDouble();
        sc.nextLine();

        if (totalCompra <= 0) {
            System.out.println("Valor da compra inválido!");
            System.out.println();
            return 0;
        }

        System.out.println("Entre com o total pago pelo cliente: ");
        double pagamentoCliente = sc.nextDouble();
        sc.nextLine();

        if (pagamentoCliente < totalCompra) {
            System.out.println("Valor insuficiente!");
            System.out.println();
            return 0;
        } else {
            double troco = pagamentoCliente - totalCompra;
            System.out.printf("O troco deverá ser de: R$ %.2f ", troco);
            System.out.println();
            return troco;
        }

    }

    public void registrarVendas() {

        if (vendas.isEmpty()) {
            System.out.println("Nenhuma venda realizada!");
            System.out.println();
            return;
        }
        System.out.println("\n===Registro de Vendas===");
        for (String v : vendas) {
            System.out.println(v);
        }
    }
}