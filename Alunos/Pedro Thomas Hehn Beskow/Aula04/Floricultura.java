import java.util.Scanner;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Floricultura {

    static ArrayList<int[]>    registroQuantidades = new ArrayList<>();
    static ArrayList<double[]> registroValores     = new ArrayList<>();
    static ArrayList<LocalDate> registroDatas      = new ArrayList<>();  // ← DATA DE CADA VENDA

    static final DateTimeFormatter FORMATO_DATA = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static void main(String[] args) {
        Scanner objScanner = new Scanner(System.in);
        int intOpcao;

        double[] ultimaVenda = {0, 0, 0}; // [total, desconto, totalComDesconto]

        System.out.println("    Loja de Plantas da Dona Gabi        ");

        do {
            System.out.println("\n--- MENU ---");
            System.out.println("1- Calcular Preço Total");
            System.out.println("2- Calcular Troco");
            System.out.println("3- Ver Registro de Vendas");
            System.out.println("4- Buscar Vendas por Mês e Dia");   // ← NOVA OPÇÃO
            System.out.println("5- Sair");
            System.out.print("Escolha uma opção: ");
            intOpcao = objScanner.nextInt();

            switch (intOpcao) {

                case 1:
                    System.out.print("\nQuantidade de plantas: ");
                    int intQuantidade = objScanner.nextInt();
                    System.out.print("Preço unitário (R$): ");
                    double doubPreco = objScanner.nextDouble();

                    double doubTotal      = calcularPrecoTotal(intQuantidade, doubPreco);
                    double doubDesconto   = calcularDesconto(intQuantidade, doubTotal);
                    double doubTotalFinal = doubTotal - doubDesconto;

                    System.out.println("\n--- RESUMO DA COMPRA ---");
                    System.out.printf("Subtotal:  R$ %.2f%n", doubTotal);

                    if (doubDesconto > 0) {
                        System.out.printf("Desconto (5%% para +10 plantas): -R$ %.2f%n", doubDesconto);
                    } else {
                        System.out.println("Desconto:  Nenhum");
                    }

                    System.out.printf("Total a pagar: R$ %.2f%n", doubTotalFinal);

                    ultimaVenda[0] = doubTotal;
                    ultimaVenda[1] = doubDesconto;
                    ultimaVenda[2] = doubTotalFinal;

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
                        System.out.printf("Troco:          R$ %.2f%n", doubTroco);
                    }
                    break;

                case 3:
                    exibirRegistro();
                    break;

                case 4:
                    System.out.print("\nInforme o mês (1-12): ");
                    int intMes = objScanner.nextInt();
                    System.out.print("Informe o dia (1-31): ");
                    int intDia = objScanner.nextInt();

                    if (intMes < 1 || intMes > 12 || intDia < 1 || intDia > 31) {
                        System.out.println("Data inválida! Mês deve ser 1-12 e dia 1-31.");
                        break;
                    }

                    buscarVendasPorMesEDia(intMes, intDia);
                    break;

                case 5:
                    System.out.println("\nSistema Finalizando... Até logo!");
                    break;

                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }

        } while (intOpcao != 5);

        objScanner.close();
    }

    public static double calcularPrecoTotal(int intQuantidade, double doubPrecoUnitario) {
        return intQuantidade * doubPrecoUnitario;
    }

    public static double calcularDesconto(int intQuantidade, double doubTotal) {
        if (intQuantidade > 10) {
            return doubTotal * 0.05;
        }
        return 0;
    }

    public static double calcularTroco(double doubValorRecebido, double doubValorTotal) {
        return doubValorRecebido - doubValorTotal;
    }

     //Registra a venda salvando também a data atual via LocalDate.now().
    public static void registrarVenda(int intQtd, double doubSubtotal,
                                      double doubDesconto, double doubTotalFinal) {
        registroQuantidades.add(new int[]{intQtd});
        registroValores.add(new double[]{doubSubtotal, doubDesconto, doubTotalFinal});
        registroDatas.add(LocalDate.now());

        System.out.printf("%nVenda registrada em %s com sucesso!%n",
                          LocalDate.now().format(FORMATO_DATA));
    }

    public static void exibirRegistro() {
        if (registroQuantidades.isEmpty()) {
            System.out.println("\nNenhuma venda registrada ainda.");
            return;
        }

        System.out.println("\n         REGISTRO DE VENDAS             ");

        double doubTotalGeral    = 0;
        double doubDescontoGeral = 0;
        int    intQtdGeral       = 0;

        for (int i = 0; i < registroQuantidades.size(); i++) {
            int    qtd      = registroQuantidades.get(i)[0];
            double subtotal = registroValores.get(i)[0];
            double desconto = registroValores.get(i)[1];
            double total    = registroValores.get(i)[2];
            String data     = registroDatas.get(i).format(FORMATO_DATA);   // ← EXIBE DATA

            System.out.printf("%nVenda #%d  [%s]:%n", i + 1, data);
            System.out.printf("  Quantidade vendida : %d plantas%n",  qtd);
            System.out.printf("  Subtotal           : R$ %.2f%n",     subtotal);
            System.out.printf("  Desconto aplicado  : R$ %.2f%n",     desconto);
            System.out.printf("  Total cobrado      : R$ %.2f%n",     total);

            doubTotalGeral    += total;
            doubDescontoGeral += desconto;
            intQtdGeral       += qtd;
        }

        System.out.println();
        System.out.printf("Total de plantas vendidas : %d%n",      intQtdGeral);
        System.out.printf("Total em descontos dados  : R$ %.2f%n", doubDescontoGeral);
        System.out.printf("Total arrecadado          : R$ %.2f%n", doubTotalGeral);
    }


      //Filtra e exibe todas as vendas registradas no mês e dia informados,
      //independentemente do ano. Ao final resume o total do dia.
    public static void buscarVendasPorMesEDia(int intMes, int intDia) {

        System.out.printf("%n=== VENDAS DO DIA %02d/%02d ===%n", intDia, intMes);

        int    intVendasEncontradas  = 0;
        int    intQtdTotal           = 0;
        double doubTotalArrecadado   = 0;
        double doubTotalDescontos    = 0;

        for (int i = 0; i < registroDatas.size(); i++) {
            LocalDate data = registroDatas.get(i);

            // Compara apenas mês e dia (ignora o ano)
            if (data.getMonthValue() == intMes && data.getDayOfMonth() == intDia) {

                int    qtd      = registroQuantidades.get(i)[0];
                double subtotal = registroValores.get(i)[0];
                double desconto = registroValores.get(i)[1];
                double total    = registroValores.get(i)[2];

                intVendasEncontradas++;
                intQtdTotal         += qtd;
                doubTotalArrecadado += total;
                doubTotalDescontos  += desconto;

                System.out.printf("%nVenda #%d  [%s]:%n",
                                  i + 1, data.format(FORMATO_DATA));
                System.out.printf("  Quantidade vendida : %d plantas%n",  qtd);
                System.out.printf("  Subtotal           : R$ %.2f%n",     subtotal);
                System.out.printf("  Desconto aplicado  : R$ %.2f%n",     desconto);
                System.out.printf("  Total cobrado      : R$ %.2f%n",     total);
            }
        }

        if (intVendasEncontradas == 0) {
            System.out.printf("Nenhuma venda encontrada para %02d/%02d.%n", intDia, intMes);
        } else {
            System.out.println("\n--- RESUMO DO DIA ---");
            System.out.printf("Total de vendas realizadas : %d%n",        			    intVendasEncontradas);
            System.out.printf("Total de plantas vendidas  : %d%n",        intQtdTotal);
            System.out.printf("Total em descontos dados   : R$ %.2f%n",   doubTotalDescontos);
            System.out.printf("Total arrecadado no dia    : R$ %.2f%n",   doubTotalArrecadado);
        }
    }
}