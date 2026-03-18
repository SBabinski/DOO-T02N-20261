import java.util.Scanner;

public class CalculadoraGabrielinha {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int opcao = 0;

        while (opcao != 3) {

            System.out.println("\n=== Calculadora da Dona Gabrielinha ===");
            System.out.println("[1] - Calcular Preço Total");
            System.out.println("[2] - Calcular Troco");
            System.out.println("[3] - Sair");
            System.out.print("Escolha uma opção: ");

            opcao = scanner.nextInt();

            switch (opcao) {

                case 1:
                    System.out.print("Digite a quantidade de plantas: ");
                    int quantidade = scanner.nextInt();

                    System.out.print("Digite o preço unitário da planta: ");
                    double preco = scanner.nextDouble();

                    double total = quantidade * preco;

                    System.out.println("Preço total da venda: R$ " + total);
                    break;

                case 2:
                    System.out.print("Digite o valor pago pelo cliente: ");
                    double valorPago = scanner.nextDouble();

                    System.out.print("Digite o valor total da compra: ");
                    double valorCompra = scanner.nextDouble();

                    double troco = valorPago - valorCompra;

                    System.out.println("Troco a ser devolvido: R$ " + troco);
                    break;

                case 3:
                    System.out.println("Encerrando o sistema... Obrigado!");
                    break;

                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
        }

        scanner.close();
    }
}