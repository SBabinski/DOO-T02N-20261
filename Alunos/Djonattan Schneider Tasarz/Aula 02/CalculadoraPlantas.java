import java.util.Scanner;

public class CalculadoraPlantas {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcao;

        System.out.println("CALCULADORA DA DONA GABRIELINHA ");
        System.out.println("Loja de Plantas Exóticas");
        System.out.println();

        do {
            // Menu console conforme enunciado
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
                    System.out.println("Obrigado por usar a calculadora");
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
            System.out.println();
        } while (opcao != 3);

        scanner.close();
    }

  
    public static void calcularPrecoTotal(Scanner scanner) {
        System.out.print("Digite a quantidade de plantas: ");
        int quantidade = scanner.nextInt();
        System.out.print("Digite o preço unitário (R$): ");
        double precoUnitario = scanner.nextDouble();
        double total = quantidade * precoUnitario;
        System.out.printf("Preço total: R$ %.2f%n", total);
    }

    
    public static void calcularTroco(Scanner scanner) {
        System.out.print("Valor total da compra (R$): ");
        double total = scanner.nextDouble();
        System.out.print("Valor pago pelo cliente (R$): ");
        double pago = scanner.nextDouble();
        if (pago < total) {
            System.out.println("Dinheiro insuficiente!");
        } else {
            double troco = pago - total;
            System.out.printf("Troco: R$ %.2f%n", troco);
        }
    }
}
