import java.util.Scanner;

public class CalculadoraPlantas {

    static int[] quantidadesVendidas = new int[100];
    static double[] valoresVenda = new double[100];
    static double[] descontosAplicados = new double[100];
    static int totalVendas = 0;

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int opcao = 0;

        while (opcao != 4) {

            System.out.println("\n=== Calculadora da Loja da Dona Gabrielinha ===");
            System.out.println("[1] - Calcular Preço Total");
            System.out.println("[2] - Calcular Valor a Pagar (com desconto)");
            System.out.println("[3] - Calcular Troco");
            System.out.println("[4] - Sair");
            System.out.print("Escolha uma opção: ");

            opcao = scanner.nextInt();

            switch (opcao) {

                case 1:
                    System.out.print("Digite a quantidade de plantas: ");
                    int quantidade = scanner.nextInt();

                    System.out.print("Digite o preço unitário: ");
                    double preco = scanner.nextDouble();

                    double total = quantidade * preco;

                    System.out.println("Preço total da compra: R$ " + total);
                    break;

                case 2:
                    System.out.print("Digite a quantidade de plantas: ");
                    int qtdDesconto = scanner.nextInt();

                    System.out.print("Digite o preço unitário: ");
                    double precoDesconto = scanner.nextDouble();

                    double totalDesconto = qtdDesconto * precoDesconto;
                    double desconto = 0;
                    double valorFinal = totalDesconto;

                    if (qtdDesconto > 10) {
                        desconto = totalDesconto * 0.05;
                        valorFinal = totalDesconto - desconto;
                        System.out.println("Desconto de 5% aplicado: R$ " + String.format("%.2f", desconto));
                    } else {
                        System.out.println("Sem desconto (compra de 10 plantas ou menos).");
                    }

                    System.out.println("Valor a pagar: R$ " + String.format("%.2f", valorFinal));

                    quantidadesVendidas[totalVendas] = qtdDesconto;
                    valoresVenda[totalVendas] = valorFinal;
                    descontosAplicados[totalVendas] = desconto;
                    totalVendas++;

                    System.out.println("Venda registrada com sucesso!");
                    
                    System.out.println("\n--- Registro de Vendas ---");
                    for (int i = 0; i < totalVendas; i++) {
                        System.out.println("Venda " + (i + 1) + ":"
                                + " Qtd: " + quantidadesVendidas[i]
                                + " | Valor: R$ " + String.format("%.2f", valoresVenda[i])
                                + " | Desconto: R$ " + String.format("%.2f", descontosAplicados[i]));
                    }
                    break;

                case 3:
                    System.out.print("Digite o valor recebido do cliente: ");
                    double valorRecebido = scanner.nextDouble();

                    System.out.print("Digite o valor total da compra: ");
                    double valorCompra = scanner.nextDouble();

                    double troco = valorRecebido - valorCompra;

                    if (troco < 0) {
                        System.out.println("Valor insuficiente! Faltam R$ " + String.format("%.2f", Math.abs(troco)));
                    } else {
                        System.out.println("Troco a devolver: R$ " + String.format("%.2f", troco));
                    }
                    break;

                case 4:
                    System.out.println("Encerrando a calculadora...");
                    break;

                default:
                    System.out.println("Opção inválida!");
            }
        }

        scanner.close();
    }
}