package Objetos;
import java.util.ArrayList;

public class Resultados {

    public static void main(String args[]) {

    }

    public static void clearTerminal() {
        System.out.print("\033[H\033[2J");
    }

    public static void mostraTotalCompra(double valorProduto, int quantidade, double valorTotal) {
        clearTerminal();
        System.out.println("=======================================");
        System.out.printf("Valor do produto: %.2f", valorProduto);
        System.out.println();
        System.out.println("Quantidade de Produtos: " + quantidade);
        System.out.printf("Valor total da compra: %.2f", valorTotal);
        System.out.println();
    }

    public static void mostraTroco(double valorTotal, double valorPago, double troco) {
        clearTerminal();
        System.out.println("=======================================");
        System.out.printf("Valor total da Compra: %.2f", valorTotal);
        System.out.println();
        System.out.printf("Valor recebido: %.2f", valorPago);
        System.out.println();
        System.out.printf("Troco: %.2f", troco);
        System.out.println();
    }

    public static void mostraDesconto(double desconto) {
        System.out.println();
        System.out.println("Desconto aplicado: 5%");
        System.out.printf("Valor total a pagar: %.2f", desconto);
        System.out.println();
    }

    public static void mostraVendas(ArrayList<Double> vendas) {
        clearTerminal();
        System.out.println("================================");
        for (int i = 0; i < vendas.size(); i++) {
            System.out.printf("Nº Venda %d - total %.2f%n", i + 1, vendas.get(i));
        }
    }

}