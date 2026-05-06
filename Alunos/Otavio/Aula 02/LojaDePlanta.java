import java.util.Scanner;

public class LojaDePlanta {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        CalculadoraDaLoja calculadora = new CalculadoraDaLoja();

        int opcao = 0;

        while (opcao != 3) {

            System.out.println("\n===== Loja de plantas =====");
            System.out.println("[1] - Calcular Preço Total");
            System.out.println("[2] - Calcular Troco");
            System.out.println("[3] - Sair");
            System.out.print("Escolha uma opção: ");

            opcao = scanner.nextInt();

            switch (opcao) {

                case 1:
                    System.out.print("Quantidade de plantas: ");
                    int quantidade = scanner.nextInt();

                    System.out.print("Preço unitário da planta: ");
                    double preco = scanner.nextDouble();

                    double total = calculadora.calcularPrecoTotal(quantidade, preco);

                    System.out.println("Preço total da compra: R$ " + total);
                    break;

                case 2:
                    System.out.print("Valor pago pelo cliente: ");
                    double valorPago = scanner.nextDouble();

                    System.out.print("Valor total da compra: ");
                    double valorCompra = scanner.nextDouble();

                    double troco = calculadora.calcularTroco(valorPago, valorCompra);

                    System.out.println("Troco a devolver: R$ " + troco);
                    break;

                case 3:
                    System.out.println("Sistema encerrado.");
                    break;

                default:
                    System.out.println("Opção inválida.");
            }
        }

        scanner.close();
    }
}

class CalculadoraDaLoja {

    public double calcularPrecoTotal(int quantidade, double precoUnitario) {
        return quantidade * precoUnitario;
    }

    public double calcularTroco(double valorPago, double valorCompra) {
        return valorPago - valorCompra;
    }
}