import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        ArrayList<String> vendas = new ArrayList<>();

        int opcao = 0;

        while (opcao != 4) {

            System.out.println("\n===== LOJA DONA GABRIELINHA =====");
            System.out.println("[1] - Calcular Preço Total (com desconto)");
            System.out.println("[2] - Ver Registro de Vendas");
            System.out.println("[3] - Calcular Troco");
            System.out.println("[4] - Sair");
            System.out.print("Escolha: ");

            opcao = scanner.nextInt();

            switch (opcao) {

                case 1:

                    System.out.print("Quantidade: ");
                    int quantidade = scanner.nextInt();

                    System.out.print("Preço unitário: ");
                    double preco = scanner.nextDouble();

                    double total = quantidade * preco;
                    double desconto = 0;

                    if (quantidade > 10) {
                        desconto = total * 0.05;
                        total -= desconto;
                        System.out.println("Desconto de 5% aplicado!");
                    }

                    System.out.println("Total: R$ " + total);

                    vendas.add("Qtd: " + quantidade +
                               " | Total: R$ " + total +
                               " | Desconto: R$ " + desconto);

                    break;

                case 2:

                    System.out.println("\n=== REGISTRO DE VENDAS ===");

                    if (vendas.isEmpty()) {
                        System.out.println("Nenhuma venda registrada.");
                    } else {
                        for (String v : vendas) {
                            System.out.println(v);
                        }
                    }

                    break;

                case 3:

                    System.out.print("Valor pago: ");
                    double pago = scanner.nextDouble();

                    System.out.print("Valor da compra: ");
                    double compra = scanner.nextDouble();

                    double troco = pago - compra;

                    if (troco < 0) {
                        System.out.println("Falta: R$ " + (-troco));
                    } else {
                        System.out.println("Troco: R$ " + troco);
                    }

                    break;

                case 4:
                    System.out.println("Saindo...");
                    break;

                default:
                    System.out.println("Opção inválida!");
            }
        }

        scanner.close();
    }
}