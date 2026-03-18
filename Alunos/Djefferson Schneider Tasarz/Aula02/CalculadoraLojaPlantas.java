import java.util.Scanner;

public class CalculadoraLojaPlantas {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int opcao = 0;

        System.out.println("Bem-vindo à loja de plantas da Dona Gabrielinha ");

        while (opcao != 3) {
            System.out.println("\nMENU ");
            System.out.println("[1] - Calcular Preço Total");
            System.out.println("[2] - Calcular Troco");
            System.out.println("[3] - Sair");
            System.out.print("Escolha uma opção: ");

            opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    calcularPrecoTotal(scanner);
                    break;
                case 2:
                    calcularTroco(scanner);
                    break;
                case 3:
                    System.out.println("Encerrando a calculadora. Até mais!");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }

        scanner.close();
    }

    private static void calcularPrecoTotal(Scanner scanner) {

        System.out.println("\nCalcular Preço Total ");
        System.out.print("Informe a quantidade da planta: ");
        int quantidade = scanner.nextInt();

        System.out.print("Informe o preço unitário da planta: ");
        double precoUnitario = scanner.nextDouble();

        double total = quantidade * precoUnitario;

        System.out.println("Preço total da venda: R$ " + total);
    }

    private static void calcularTroco(Scanner scanner) {

        System.out.println("\nCalcular Troco ");
        System.out.print("Informe o valor recebido do cliente: ");
        double valorRecebido = scanner.nextDouble();

        System.out.print("Informe o valor total da compra: ");
        double valorCompra = scanner.nextDouble();

        double troco = valorRecebido - valorCompra;

        System.out.println("Troco a ser devolvido: R$ " + troco);
    }
}
