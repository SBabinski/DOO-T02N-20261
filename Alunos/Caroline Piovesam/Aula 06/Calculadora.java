import java.util.Scanner;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.HashMap;

public class Calculadora {

    Scanner sc = new Scanner(System.in);
    ArrayList<String> vendas = new ArrayList<>();

    Map<LocalDate, Integer> vendasPorData = new HashMap<>();

    double totalCompra = 0;
    int quantidade = 0;
    double VAR_DESCONTO = 0.95;

    public double calcularPrecoTotal() {

        System.out.println("Entre com a quantidade de plantas a serem compradas: ");
        quantidade = sc.nextInt();
        sc.nextLine();

        if (quantidade <= 0) {
            System.out.println("Quantidade inválida!");
            System.out.println();
            return 0;
        }

        System.out.println("Entre com o preço por unidade: ");
        double precoUnidade = sc.nextDouble();
        sc.nextLine();

        if (precoUnidade <= 0) {
            System.out.println("Preço inválido!");
            System.out.println();
            return 0;
        }

        totalCompra = quantidade * precoUnidade;

        if (quantidade > 10) {
            totalCompra *= VAR_DESCONTO;
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate data = null;

        while (true) {
            System.out.println("Digite a data da venda (dd/MM/yyyy): ");
            String dataTexto = sc.nextLine();

            try {
                data = LocalDate.parse(dataTexto, formatter);
                break;
            } catch (Exception e) {
                System.out.println("Data inválida! Tente novamente.");
            }
        }

        DateTimeFormatter formatterSaida = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        vendas.add(String.format(
                "Data: %s | Qtd: %d | Total: R$ %.2f ",
                data.format(formatterSaida), quantidade, totalCompra));
        System.out.println();

        if (vendasPorData.containsKey(data)) {
            vendasPorData.put(data, vendasPorData.get(data) + 1);
        } else {
            vendasPorData.put(data, 1);
        }
        System.out.println("Venda registrada!");
        return totalCompra;

    }

    public double calcularTroco() {

        System.out.println("Entre com o total da compra: ");
        double totalCompra = sc.nextDouble();
        sc.nextLine();

        if (totalCompra <= 0) {
            System.out.println("Valor da compra inválido!");
            System.out.println();
            return 0;
        }

        System.out.println("Entre com o total pago pelo cliente: ");
        double pagamentoCliente = sc.nextDouble();
        sc.nextLine();

        if (pagamentoCliente < totalCompra) {
            System.out.println("Valor insuficiente!");
            System.out.println();
            return 0;
        } else {
            double troco = pagamentoCliente - totalCompra;
            System.out.printf("O troco deverá ser de: R$ %.2f ", troco);
            System.out.println();
            return troco;
        }

    }

    public void registrarVendas() {

        if (vendas.isEmpty()) {
            System.out.println("Nenhuma venda realizada!");
            System.out.println();
            return;
        }
        System.out.println("\n===Registro de Vendas===");
        for (String v : vendas) {
            System.out.println(v);
        }
        System.out.println();
    }

    public void consultarVendasPorData() {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        System.out.println("Digite a data para consulta (dd/MM/yyyy): ");
        String dataTexto = sc.nextLine();

        try {
            LocalDate data = LocalDate.parse(dataTexto, formatter);

            if (vendasPorData.containsKey(data)) {
                System.out.println("Total de vendas nesta data: " + vendasPorData.get(data));
            } else {
                System.out.println("Nenhuma venda nesta data.");
            }

        } catch (Exception e) {
            System.out.println("Data inválida!");
        }

        System.out.println();
    }
}