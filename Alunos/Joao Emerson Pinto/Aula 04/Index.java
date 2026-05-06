import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

import Objetos.RegistroVenda;
import Objetos.Resultados;

public class Index {

    static Scanner scan = new Scanner(System.in);
    static ArrayList<RegistroVenda> vendas = new ArrayList<>();
    static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static void main(String[] args) {
        Resultados.clearTerminal();
        menu();
    }

    public static void menu() {

        int opcao = -1;

        do {
            System.out.println("================================");
            System.out.println("( 1 ) Calcular valor Total ");
            System.out.println("( 2 ) Listar Registro de Vendas ");
            System.out.println("( 3 ) Consultar total vendido por dia ");
            System.out.println("( 4 ) Consultar total vendido por mês ");
            System.out.println("( 0 ) Sair ");
            System.out.println("================================");

            System.out.printf("Informe uma opcao: ");
            opcao = scan.nextInt();
            scan.nextLine();

            validaOpcao(opcao);

        } while (opcao != 0);
    }

    public static void validaOpcao(int opcao) {

        if (opcao == 1) {
            calculaTotal();
        } else if (opcao == 2) {
            Resultados.mostraVendas(vendas);
        } else if (opcao == 3) {
            consultarVendasPorDia();
        } else if (opcao == 4) {
            consultarVendasPorMes();
        } else if (opcao == 0) {
            System.out.println("Saindo...");
        } else {
            Resultados.clearTerminal();
            System.out.println("================================");
            System.out.println("Opcao invalida, tente novamente.");
        }
    }

    public static void calculaTotal() {

        System.out.println("================================");

        System.out.printf("Informe o valor do produto: ");
        float valorProduto = scan.nextFloat();

        System.out.printf("Informe a quantidade: ");
        int quantidade = scan.nextInt();
        scan.nextLine();

        double valorTotal = valorProduto * quantidade;
        Resultados.mostraTotalCompra(valorProduto, quantidade, valorTotal);

        if (quantidade >= 10) {
            valorTotal = calculaDesconto(valorTotal);
        }

        calculaTroco(valorTotal);
    }

    public static void calculaTroco(double valorTotal) {

        System.out.println("================================");
        System.out.println("...Calcular Troco... ");

        System.out.print("Informe o valor pago: ");
        double valorPago = scan.nextDouble();
        scan.nextLine();

        double troco = valorPago - valorTotal;

        if (troco < 0) {
            System.out.printf("Valor insuficiente, faltam: %.2f", Math.abs(troco));
            System.out.println();
        } else {
            Resultados.mostraTroco(valorTotal, valorPago, troco);

            LocalDate dataVenda = lerDataVenda();
            vendas.add(new RegistroVenda(dataVenda, valorTotal));

            System.out.println("Venda registrada com sucesso na data " + dataVenda.format(formatter));
        }
    }

    public static double calculaDesconto(double valorTotal) {

        double valorDesconto = 95;
        double desconto = (valorTotal * valorDesconto) / 100;
        Resultados.mostraDesconto(desconto);

        return desconto;
    }

    public static LocalDate lerDataVenda() {
        while (true) {
            try {
                System.out.print("Informe a data da venda (dd/MM/yyyy): ");
                String dataTexto = scan.nextLine();
                return LocalDate.parse(dataTexto, formatter);
            } catch (DateTimeParseException e) {
                System.out.println("Data inválida. Tente novamente no formato dd/MM/yyyy.");
            }
        }
    }

    public static void consultarVendasPorDia() {
        try {
            System.out.print("Informe a data para consulta (dd/MM/yyyy): ");
            String dataTexto = scan.nextLine();

            LocalDate dataConsulta = LocalDate.parse(dataTexto, formatter);

            double totalDia = 0;

            for (RegistroVenda venda : vendas) {
                if (venda.getData().equals(dataConsulta)) {
                    totalDia += venda.getValor();
                }
            }

            Resultados.mostraTotalDia(dataConsulta, totalDia);

        } catch (DateTimeParseException e) {
            System.out.println("Data inválida. Use o formato dd/MM/yyyy.");
        }
    }

    public static void consultarVendasPorMes() {
        try {
            System.out.print("Informe o mês e ano para consulta (MM/yyyy): ");
            String mesAno = scan.nextLine();

            String[] partes = mesAno.split("/");

            int mes = Integer.parseInt(partes[0]);
            int ano = Integer.parseInt(partes[1]);

            double totalMes = 0;

            for (RegistroVenda venda : vendas) {
                if (venda.getData().getMonthValue() == mes && venda.getData().getYear() == ano) {
                    totalMes += venda.getValor();
                }
            }

            Resultados.mostraTotalMes(mes, ano, totalMes);

        } catch (Exception e) {
            System.out.println("Formato inválido. Use MM/yyyy.");
        }
    }
}