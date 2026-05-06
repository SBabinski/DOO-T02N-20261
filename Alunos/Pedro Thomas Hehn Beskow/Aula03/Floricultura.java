import java.util.Scanner;
import java.util.ArrayList;

public class Floricultura {

    static ArrayList<int[]> registroQuantidades = new ArrayList<>();
    static ArrayList<double[]> registroValores = new ArrayList<>();

    public static void main(String[] args) {
        Scanner objScanner = new Scanner(System.in);
        int intOpcao;

        // Variáveis para manter o último cálculo (usadas na opção 2)
        double[] ultimaVenda = {0, 0, 0}; // [total, desconto, totalComDesconto]

        System.out.println("    Loja de Plantas da Dona Gabi        ");

        do {
            System.out.println("\n--- MENU ---");
            System.out.println("1- Calcular Preço Total");
            System.out.println("2- Calcular Troco");
            System.out.println("3- Ver Registro de Vendas");
            System.out.println("4- Sair");
            System.out.print("Escolha uma opção: ");
            intOpcao = objScanner.nextInt();

            switch (intOpcao) {

                case 1:
                    System.out.print("\nQuantidade de plantas: ");
                    int intQuantidade = objScanner.nextInt();
                    System.out.print("Preço unitário (R$): ");
                    double doubPreco = objScanner.nextDouble();

                    double doubTotal        = calcularPrecoTotal(intQuantidade, doubPreco);
                    double doubDesconto     = calcularDesconto(intQuantidade, doubTotal);
                    double doubTotalFinal   = doubTotal - doubDesconto;

                    System.out.println("\n--- RESUMO DA COMPRA ---");
                    System.out.printf("Subtotal:  R$ %.2f%n", doubTotal);

                    if (doubDesconto > 0) {
                        System.out.printf("Desconto (5%% para +10 plantas): -R$ %.2f%n", doubDesconto);
                    } else {
                        System.out.println("Desconto:  Nenhum");
                    }

                    System.out.printf("Total a pagar: R$ %.2f%n", doubTotalFinal);

                    // Salva os dados para uso na opção 2 e para o registro
                    ultimaVenda[0] = doubTotal;
                    ultimaVenda[1] = doubDesconto;
                    ultimaVenda[2] = doubTotalFinal;

                    // Registra a venda
                    registrarVenda(intQuantidade, doubTotal, doubDesconto, doubTotalFinal);
                    break;

                case 2:
                    if (ultimaVenda[2] == 0) {
                        System.out.println("\nNenhuma compra calculada ainda. Use a opção 1 primeiro.");
                        break;
                    }

                    System.out.printf("%nTotal da última compra: R$ %.2f%n", ultimaVenda[2]);
                    System.out.print("Valor recebido (R$): ");
                    double doubRecebido = objScanner.nextDouble();

                    double doubTroco = calcularTroco(doubRecebido, ultimaVenda[2]);

                    System.out.println("\n--- PAGAMENTO ---");
                    System.out.printf("Valor recebido: R$ %.2f%n", doubRecebido);
                    System.out.printf("Total a pagar:  R$ %.2f%n", ultimaVenda[2]);

                    if (doubTroco < 0) {
                        System.out.printf("Valor insuficiente! Faltam R$ %.2f%n", doubTroco * -1);
                    } else {
                        System.out.printf("Troco:        R$ %.2f%n", doubTroco);
                    }
                    break;

                case 3:
                    exibirRegistro();
                    break;

                case 4:
                    System.out.println("\nSistema Finalizando... Até logo!");
                    break;

                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }

        } while (intOpcao != 4);

        objScanner.close();
    }


    // Calcula o preço total sem desconto
    public static double calcularPrecoTotal(int intQuantidade, double doubPrecoUnitario) {
        return intQuantidade * doubPrecoUnitario;
    }

    // Calcula o desconto de 5% quando quantidade > 10
    // Retorna 0 caso não haja desconto
    public static double calcularDesconto(int intQuantidade, double doubTotal) {
        if (intQuantidade > 10) {
            return doubTotal * 0.05;
        }
        return 0;
    }

    // Calcula o troco
    public static double calcularTroco(double doubValorRecebido, double doubValorTotal) {
        return doubValorRecebido - doubValorTotal;
    }

    // Registra a venda nas listas de histórico */
    public static void registrarVenda(int intQtd, double doubSubtotal,
                                      double doubDesconto, double doubTotalFinal) {
        registroQuantidades.add(new int[]{intQtd});
        registroValores.add(new double[]{doubSubtotal, doubDesconto, doubTotalFinal});
        System.out.println("\n✔ Venda registrada com sucesso!");
    }

    // Exibe o histórico completo de vendas */
    public static void exibirRegistro() {
        if (registroQuantidades.isEmpty()) {
            System.out.println("\nNenhuma venda registrada ainda.");
            return;
        }

        System.out.println("\n");
        System.out.println("         REGISTRO DE VENDAS             ");

        double doubTotalGeral    = 0;
        double doubDescontoGeral = 0;
        int    intQtdGeral       = 0;

        for (int i = 0; i < registroQuantidades.size(); i++) {
            int    qtd       = registroQuantidades.get(i)[0];
            double subtotal  = registroValores.get(i)[0];
            double desconto  = registroValores.get(i)[1];
            double total     = registroValores.get(i)[2];

            System.out.printf("%nVenda #%d:%n", i + 1);
            System.out.printf("  Quantidade vendida : %d plantas%n",   qtd);
            System.out.printf("  Subtotal           : R$ %.2f%n",      subtotal);
            System.out.printf("  Desconto aplicado  : R$ %.2f%n",      desconto);
            System.out.printf("  Total cobrado      : R$ %.2f%n",      total);

            doubTotalGeral    += total;
            doubDescontoGeral += desconto;
            intQtdGeral       += qtd;
        }

        System.out.println("\n");
        System.out.printf("Total de plantas vendidas : %d%n",      intQtdGeral);
        System.out.printf("Total em descontos dados  : R$ %.2f%n", doubDescontoGeral);
        System.out.printf("Total arrecadado          : R$ %.2f%n", doubTotalGeral);
    }
}