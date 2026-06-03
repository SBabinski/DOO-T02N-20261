package Objetos;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Resultados {

    static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static void main(String args[]) {

    }

    public static void clearTerminal() {
        System.out.print("\033[H\033[2J");
    }

    public static void mostraTotalCompra(double valorProduto, int quantidade, double valorTotal) {
        clearTerminal();
        System.out.println("=======================================");
        System.out.printf("Valor do produto: %.2f", valorProduto);
        System.out.println();
        System.out.println("Quantidade de Produtos: " + quantidade);
        System.out.printf("Valor total da compra: %.2f", valorTotal);
        System.out.println();
    }

    public static void mostraTroco(double valorTotal, double valorPago, double troco) {
        clearTerminal();
        System.out.println("=======================================");
        System.out.printf("Valor total da Compra: %.2f", valorTotal);
        System.out.println();
        System.out.printf("Valor recebido: %.2f", valorPago);
        System.out.println();
        System.out.printf("Troco: %.2f", troco);
        System.out.println();
    }

    public static void mostraDesconto(double desconto) {
        System.out.println();
        System.out.println("Desconto aplicado: 5%");
        System.out.printf("Valor total a pagar: %.2f", desconto);
        System.out.println();
    }

    public static void mostraVendas(ArrayList<RegistroVenda> vendas) {
        clearTerminal();
        System.out.println("================================");
        if (vendas.isEmpty()) {
            System.out.println("Nenhuma venda registrada.");
            return;
        }

        for (int i = 0; i < vendas.size(); i++) {
            RegistroVenda venda = vendas.get(i);
            System.out.printf(
                "Nº Venda %d - Data: %s - Total: %.2f%n",
                i + 1,
                venda.getData().format(formatter),
                venda.getValor()
            );
        }
    }

    public static void mostraTotalDia(LocalDate data, double totalDia) {
        clearTerminal();
        System.out.println("================================");
        System.out.println("Consulta de vendas por dia");
        System.out.println("Data: " + data.format(formatter));
        System.out.printf("Total vendido no dia: %.2f%n", totalDia);
    }

    public static void mostraTotalMes(int mes, int ano, double totalMes) {
        clearTerminal();
        System.out.println("================================");
        System.out.println("Consulta de vendas por mês");
        System.out.printf("Mês/Ano: %02d/%d%n", mes, ano);
        System.out.printf("Total vendido no mês: %.2f%n", totalMes);
    }
}