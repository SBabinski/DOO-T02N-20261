import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class CalculadoraPlantas {

    static int[]      quantidadesVendidas = new int[100];
    static double[]   valoresVenda        = new double[100];
    static double[]   descontosAplicados  = new double[100];
    static LocalDate[] datasVenda         = new LocalDate[100];
    static int totalVendas = 0;

    static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int opcao = 0;

        while (opcao != 6) {

            System.out.println("\n=== Calculadora da Loja da Dona Gabrielinha ===");
            System.out.println("[1] - Calcular Preço Total");
            System.out.println("[2] - Calcular Valor a Pagar (com desconto)");
            System.out.println("[3] - Calcular Troco");
            System.out.println("[4] - Ver Registro de Vendas");
            System.out.println("[5] - Buscar Vendas por Mês e Dia");
            System.out.println("[6] - Sair");
            System.out.print("Escolha uma opção: ");

            opcao = scanner.nextInt();

            switch (opcao) {

                case 1:
                    System.out.print("Digite a quantidade de plantas: ");
                    int quantidade = scanner.nextInt();

                    System.out.print("Digite o preço unitário: ");
                    double preco = scanner.nextDouble();

                    double total = quantidade * preco;

                    System.out.println("Preço total da compra: R$ " + String.format("%.2f", total));
                    break;

                case 2:
                    System.out.print("Digite a quantidade de plantas: ");
                    int qtdDesconto = scanner.nextInt();

                    System.out.print("Digite o preço unitário: ");
                    double precoDesconto = scanner.nextDouble();

                    double totalDesconto = qtdDesconto * precoDesconto;
                    double desconto  = 0;
                    double valorFinal = totalDesconto;

                    if (qtdDesconto > 10) {
                        desconto  = totalDesconto * 0.05;
                        valorFinal = totalDesconto - desconto;
                        System.out.println("Desconto de 5% aplicado: R$ " + String.format("%.2f", desconto));
                    } else {
                        System.out.println("Sem desconto (compra de 10 plantas ou menos).");
                    }

                    System.out.println("Valor a pagar: R$ " + String.format("%.2f", valorFinal));

                    // ── Lê e valida a data da venda ──────────────────────────────────────
                    LocalDate dataVenda = null;
                    scanner.nextLine(); 

                    while (dataVenda == null) {
                        System.out.print("Digite a data da venda (dd/MM/yyyy): ");
                        String dataStr = scanner.nextLine().trim();
                        try {
                            dataVenda = LocalDate.parse(dataStr, formatter);
                        } catch (DateTimeParseException e) {
                            System.out.println("Data inválida! Use o formato dd/MM/yyyy.");
                        }
                    }

                    quantidadesVendidas[totalVendas] = qtdDesconto;
                    valoresVenda[totalVendas]        = valorFinal;
                    descontosAplicados[totalVendas]  = desconto;
                    datasVenda[totalVendas]          = dataVenda;
                    totalVendas++;

                    System.out.println("Venda registrada com sucesso em " + dataVenda.format(formatter) + "!");
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
                    if (totalVendas == 0) {
                        System.out.println("Nenhuma venda registrada ainda.");
                        break;
                    }
                    System.out.println("\n--- Registro de Vendas ---");
                    for (int i = 0; i < totalVendas; i++) {
                        System.out.println("Venda " + (i + 1) + ":"
                                + " Data: "     + datasVenda[i].format(formatter)
                                + " | Qtd: "    + quantidadesVendidas[i]
                                + " | Valor: R$ "   + String.format("%.2f", valoresVenda[i])
                                + " | Desconto: R$ " + String.format("%.2f", descontosAplicados[i]));
                    }
                    break;

                case 5:
                    System.out.print("Digite o mês para busca (1-12): ");
                    int mesBusca = scanner.nextInt();

                    System.out.print("Digite o dia para busca (1-31): ");
                    int diaBusca = scanner.nextInt();

                    if (mesBusca < 1 || mesBusca > 12 || diaBusca < 1 || diaBusca > 31) {
                        System.out.println("Mês ou dia inválido!");
                        break;
                    }

                    int    totalVendasEncontradas = 0;
                    double totalValorDia          = 0;

                    System.out.println("\n--- Vendas no dia " + String.format("%02d/%02d", diaBusca, mesBusca) + " ---");

                    for (int i = 0; i < totalVendas; i++) {
                        if (datasVenda[i].getMonthValue() == mesBusca
                                && datasVenda[i].getDayOfMonth() == diaBusca) {

                            totalVendasEncontradas++;
                            totalValorDia += valoresVenda[i];

                            System.out.println("Venda " + totalVendasEncontradas + ":"
                                    + " Ano: "      + datasVenda[i].getYear()
                                    + " | Qtd: "    + quantidadesVendidas[i]
                                    + " | Valor: R$ "    + String.format("%.2f", valoresVenda[i])
                                    + " | Desconto: R$ "  + String.format("%.2f", descontosAplicados[i]));
                        }
                    }

                    if (totalVendasEncontradas == 0) {
                        System.out.println("Nenhuma venda encontrada para esta data.");
                    } else {
                        System.out.println("─────────────────────────────────────────");
                        System.out.println("Total de vendas encontradas : " + totalVendasEncontradas);
                        System.out.println("Valor total arrecadado      : R$ " + String.format("%.2f", totalValorDia));
                    }
                    break;

                case 6:
                    System.out.println("Encerrando a calculadora...");
                    break;

                default:
                    System.out.println("Opção inválida!");
            }
        }

        scanner.close();
    }
}