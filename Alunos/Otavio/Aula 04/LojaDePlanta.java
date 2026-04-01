import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LojaDePlanta {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        CalculadoraDaLoja calculadora = new CalculadoraDaLoja();
        List<Venda> registroVendas = new ArrayList<>();

        int opcao = 0;

        while (opcao != 6) {

            System.out.println("\n===== LOJA DA DONA GABRIELINHA =====");
            System.out.println("1 - Realizar Venda");
            System.out.println("2 - Calcular Troco");
            System.out.println("3 - Listar Vendas");
            System.out.println("4 - Buscar Total de Vendas por Mes e Dia");
            System.out.println("5 - Buscar Total de Vendas por Mes");
            System.out.println("6 - Sair");
            System.out.print("Escolha uma opcao: ");

            opcao = scanner.nextInt();

            switch (opcao) {

                case 1:
                    System.out.print("Quantidade de plantas: ");
                    int quantidade = scanner.nextInt();

                    System.out.print("Preco unitario da planta: ");
                    double preco = scanner.nextDouble();

                    Venda venda = calculadora.realizarVenda(quantidade, preco);
                    registroVendas.add(venda);

                    System.out.println("Venda registrada na data: " + venda.getDataFormatada());
                    System.out.println("Quantidade: " + venda.getQuantidade());
                    System.out.println("Valor bruto: R$ " + venda.getValorBruto());
                    System.out.println("Desconto: R$ " + venda.getDesconto());
                    System.out.println("Valor final: R$ " + venda.getValorFinal());

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
                    if (registroVendas.isEmpty()) {
                        System.out.println("Nenhuma venda registrada.");
                    } else {
                        for (Venda v : registroVendas) {
                            System.out.println(v);
                        }
                    }
                    break;

                case 4:
                    System.out.print("Informe o mes (1-12): ");
                    int mes = scanner.nextInt();

                    System.out.print("Informe o dia (1-31): ");
                    int dia = scanner.nextInt();

                    List<Venda> vendasPorMesDia = calculadora.buscarVendasPorMesEDia(registroVendas, mes, dia);

                    System.out.println("\nVendas em " + String.format("%02d/%02d", dia, mes) + ":");

                    if (vendasPorMesDia.isEmpty()) {
                        System.out.println("Nenhuma venda encontrada para essa data.");
                    } else {
                        for (Venda v : vendasPorMesDia) {
                            System.out.println(v);
                        }
                        System.out.println("Total: " + vendasPorMesDia.size() + " venda(s)");
                    }
                    break;

                case 5:
                    System.out.print("Informe o mes (1-12): ");
                    int mesBusca = scanner.nextInt();

                    List<Venda> vendasPorMes = calculadora.buscarVendasPorMes(registroVendas, mesBusca);

                    System.out.println("\nVendas no mes " + String.format("%02d", mesBusca) + ":");

                    if (vendasPorMes.isEmpty()) {
                        System.out.println("Nenhuma venda encontrada para esse mes.");
                    } else {
                        for (Venda v : vendasPorMes) {
                            System.out.println(v);
                        }
                        System.out.println("Total: " + vendasPorMes.size() + " venda(s)");
                    }
                    break;

                case 6:
                    System.out.println("Sistema encerrado.");
                    break;

                default:
                    System.out.println("Opcao invalida.");
            }
        }

        scanner.close();
    }
}

class CalculadoraDaLoja {

    private static final double DESCONTO_PERCENTUAL = 0.05;

    public Venda realizarVenda(int quantidade, double precoUnitario) {
        double valorBruto = quantidade * precoUnitario;
        double desconto = calcularDesconto(quantidade, valorBruto);
        double valorFinal = valorBruto - desconto;
        return new Venda(quantidade, valorBruto, desconto, valorFinal, LocalDate.now());
    }

    private double calcularDesconto(int quantidade, double valorBruto) {
        if (quantidade > 10) {
            return valorBruto * DESCONTO_PERCENTUAL;
        }
        return 0;
    }

    public double calcularTroco(double valorPago, double valorCompra) {
        return valorPago - valorCompra;
    }

    public List<Venda> buscarVendasPorMesEDia(List<Venda> vendas, int mes, int dia) {
        List<Venda> resultado = new ArrayList<>();
        for (Venda v : vendas) {
            if (v.getData().getMonthValue() == mes && v.getData().getDayOfMonth() == dia) {
                resultado.add(v);
            }
        }
        return resultado;
    }

    public List<Venda> buscarVendasPorMes(List<Venda> vendas, int mes) {
        List<Venda> resultado = new ArrayList<>();
        for (Venda v : vendas) {
            if (v.getData().getMonthValue() == mes) {
                resultado.add(v);
            }
        }
        return resultado;
    }
}

class Venda {

    private int quantidade;
    private double valorBruto;
    private double desconto;
    private double valorFinal;
    private LocalDate data;

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public Venda(int quantidade, double valorBruto, double desconto, double valorFinal, LocalDate data) {
        this.quantidade = quantidade;
        this.valorBruto = valorBruto;
        this.desconto = desconto;
        this.valorFinal = valorFinal;
        this.data = data;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public double getValorBruto() {
        return valorBruto;
    }

    public double getDesconto() {
        return desconto;
    }

    public double getValorFinal() {
        return valorFinal;
    }

    public LocalDate getData() {
        return data;
    }

    public String getDataFormatada() {
        return data.format(FORMATTER);
    }

    @Override
    public String toString() {
        return "Data: " + data.format(FORMATTER)
                + " | Quantidade: " + quantidade
                + " | Bruto: R$ " + valorBruto
                + " | Desconto: R$ " + desconto
                + " | Final: R$ " + valorFinal;
    }
}