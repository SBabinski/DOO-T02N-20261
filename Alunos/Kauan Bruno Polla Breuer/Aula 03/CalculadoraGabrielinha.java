import java.util.ArrayList;
import java.util.Scanner;

public class CalculadoraGabrielinha {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        // listas que registram vendas
        ArrayList<Integer> quantidades = new ArrayList<>();
        ArrayList<Double> precos = new ArrayList<>();
        ArrayList<Double> descontos = new ArrayList<>();
        ArrayList<Double> totais = new ArrayList<>();

        int opcao;

        do {
            System.out.println("\n=== Calculadora da Dona Gabrielinha  ===");
            System.out.println("[1] - Calcular Preço Total (desconto com 10+)");
            System.out.println("[2] - Calcular Troco");
            System.out.println("[3] - Ver Registro de Vendas");
            System.out.println("[4] - Sair");
            System.out.print("Escolha uma opção: ");

            opcao = scanner.nextInt();

            switch (opcao) {

                case 1:
                    System.out.print("Quantidade de plantas: ");
                    int quantidade = scanner.nextInt();

                    System.out.print("Preço unitário: ");
                    double preco = scanner.nextDouble();

                    double totalSemDesconto = quantidade * preco;
                    double desconto = 0;

                    if (quantidade > 10) {
                        desconto = totalSemDesconto * 0.05;
                    }

                    double totalFinal = totalSemDesconto - desconto;

                    if (desconto > 0) {
                    System.out.println("Total sem desconto: R$ " + totalSemDesconto);
                    System.out.println("Desconto: R$ " + desconto);
                    System.out.println("Total final: R$ " + totalFinal);
                    } else {
                    System.out.println("Total final: R$ " + totalFinal);
                    }

                    // registra venda
                    quantidades.add(quantidade);
                    precos.add(preco);
                    descontos.add(desconto);
                    totais.add(totalFinal);

                    break;

                case 2:
                    System.out.print("Valor pago: ");
                    double pago = scanner.nextDouble();

                    System.out.print("Valor da compra: ");
                    double compra = scanner.nextDouble();

                    if (pago < compra) {
                        System.out.println("Valor insuficiente!");
                    } else {
                        double troco = pago - compra;
                        System.out.println("Troco: R$ " + troco);
                    }

                    break;

                case 3:
                    if (quantidades.isEmpty()) {
                        System.out.println("Nenhuma venda registrada.");
                    } else {
                        System.out.println("\n=== Registro de Vendas ===");
                        for (int i = 0; i < quantidades.size(); i++) {
                            System.out.println("Venda " + (i + 1));
                            System.out.println("Quantidade: " + quantidades.get(i));
                            System.out.println("Preço unitário: R$ " + precos.get(i));
                            System.out.println("Desconto: R$ " + descontos.get(i));
                            System.out.println("Total: R$ " + totais.get(i));
                            System.out.println("----------------------");
                        }
                    }
                    break;

                case 4:
                    System.out.println("Encerrando o sistema... Obrigado!");
                    break;

                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }

        } while (opcao != 4);

        scanner.close();
    }
}