import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LojaDePlanta {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        CalculadoraDaLoja calculadora = new CalculadoraDaLoja();
        List<Venda> registroVendas = new ArrayList<>();

        int opcao = 0;

        while (opcao != 4) {

            System.out.println("1 - Realizar Venda");
            System.out.println("2 - Calcular Troco");
            System.out.println("3 - Listar Vendas");
            System.out.println("4 - Sair");
            System.out.print("Escolha uma opção: ");

            opcao = scanner.nextInt();

            switch (opcao) {

                case 1:
                    System.out.print("Quantidade de plantas: ");
                    int quantidade = scanner.nextInt();

                    System.out.print("Preço unitário da planta: ");
                    double preco = scanner.nextDouble();

                    Venda venda = calculadora.realizarVenda(quantidade, preco);

                    registroVendas.add(venda);

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
                    System.out.println("Sistema encerrado.");
                    break;

                default:
                    System.out.println("Opção inválida.");
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

        return new Venda(quantidade, valorBruto, desconto, valorFinal);
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
}

class Venda {

    private int quantidade;
    private double valorBruto;
    private double desconto;
    private double valorFinal;

    public Venda(int quantidade, double valorBruto, double desconto, double valorFinal) {
        this.quantidade = quantidade;
        this.valorBruto = valorBruto;
        this.desconto = desconto;
        this.valorFinal = valorFinal;
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

    @Override
    public String toString() {
        return "Quantidade: " + quantidade + " Bruto: R$ " + valorBruto + " Desconto: R$ " + desconto + " Final: R$ " + valorFinal;
    }
}