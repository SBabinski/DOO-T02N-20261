package fag;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

import fag.objects.Floricultura;
import fag.objects.Venda;

public class Program {

    public static ArrayList<Floricultura> listaPlantas = new ArrayList<>();
    public static ArrayList<Venda> registroVendas = new ArrayList<>();
    public static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        popularFloricultura();
        exibirMenu(sc);

        sc.close();
    }

    public static void popularFloricultura() {
        listaPlantas.add(new Floricultura("Orquidea", 16.0));
        listaPlantas.add(new Floricultura("Rosa do Deserto", 33.0));
        listaPlantas.add(new Floricultura("Rosa do Oceano", 12.0));
        listaPlantas.add(new Floricultura("Rosas Amarelas", 6.0));
        listaPlantas.add(new Floricultura("Rosas", 8.0));
    }

    public static void listarPlantas() {
        System.out.println("\n===== PLANTAS DISPONÍVEIS =====");
        for (int i = 0; i < listaPlantas.size(); i++) {
            System.out.printf("[%d] - %s | Preço: R$ %.2f%n",
                    i + 1,
                    listaPlantas.get(i).getNome(),
                    listaPlantas.get(i).getPreco());
        }
    }

    public static double calcularValorTotal(double precoUnitario, int quantidade) {
        return precoUnitario * quantidade;
    }

    public static double calcularDesconto(double valorTotal, int quantidade) {
        if (quantidade > 10) {
            return valorTotal * 0.05;
        }
        return 0.0;
    }

    public static double calcularValorFinal(double valorTotal, double desconto) {
        return valorTotal - desconto;
    }

    public static void registrarVenda(String nomePlanta, int quantidade, double valorTotal, double desconto, double valorFinal, LocalDate dataVenda) {
        Venda venda = new Venda(nomePlanta, quantidade, valorTotal, desconto, valorFinal, dataVenda);
        registroVendas.add(venda);
    }

    public static void listarRegistroVendas() {
        System.out.println("\n===== REGISTRO DE VENDAS =====");

        if (registroVendas.isEmpty()) {
            System.out.println("Nenhuma venda registrada.");
            return;
        }

        for (int i = 0; i < registroVendas.size(); i++) {
            System.out.println((i + 1) + " - " + registroVendas.get(i));
        }
    }

    public static void exibirMenu(Scanner sc) {
        int opcao;

        do {
            System.out.println("\n===== MENU =====");
            System.out.println("[1] - Realizar venda");
            System.out.println("[2] - Exibir registro de vendas");
            System.out.println("[3] - Buscar total de vendas por dia");
            System.out.println("[4] - Buscar total de vendas por mês");
            System.out.println("[5] - Sair");
            System.out.print("Digite a opção desejada: ");
            opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {
                case 1:
                    realizarVenda(sc);
                    break;

                case 2:
                    listarRegistroVendas();
                    break;

                case 3:
                    buscarTotalVendasPorDia(sc);
                    break;

                case 4:
                    buscarTotalVendasPorMes(sc);
                    break;

                case 5:
                    System.out.println("Encerrando o programa...");
                    break;

                default:
                    System.out.println("Opção inválida.");
            }

        } while (opcao != 5);
    }

    public static void realizarVenda(Scanner sc) {
        listarPlantas();

        System.out.print("\nEscolha o número da planta que deseja comprar: ");
        int escolha = sc.nextInt();

        if (escolha < 1 || escolha > listaPlantas.size()) {
            System.out.println("Opção de planta inválida.");
            return;
        }

        Floricultura plantaEscolhida = listaPlantas.get(escolha - 1);

        System.out.print("Digite a quantidade: ");
        int quantidade = sc.nextInt();
        sc.nextLine();

        if (quantidade <= 0) {
            System.out.println("Quantidade inválida.");
            return;
        }

        System.out.print("Digite a data da venda (dd/MM/yyyy): ");
        String dataTexto = sc.nextLine();

        LocalDate dataVenda;
        try {
            dataVenda = LocalDate.parse(dataTexto, formatter);
        } catch (DateTimeParseException e) {
            System.out.println("Data inválida. Use o formato dd/MM/yyyy.");
            return;
        }

        double valorTotal = calcularValorTotal(plantaEscolhida.getPreco(), quantidade);
        double desconto = calcularDesconto(valorTotal, quantidade);
        double valorFinal = calcularValorFinal(valorTotal, desconto);

        System.out.println("\n===== RESUMO DA COMPRA =====");
        System.out.printf("Planta escolhida: %s%n", plantaEscolhida.getNome());
        System.out.printf("Quantidade: %d%n", quantidade);
        System.out.printf("Data da venda: %s%n", dataVenda.format(formatter));
        System.out.printf("Valor bruto: R$ %.2f%n", valorTotal);
        System.out.printf("Desconto aplicado: R$ %.2f%n", desconto);
        System.out.printf("Valor final a pagar: R$ %.2f%n", valorFinal);

        System.out.print("\nDigite o valor do pagamento: ");
        double pagamento = sc.nextDouble();

        if (pagamento < valorFinal) {
            System.out.printf("Pagamento insuficiente. Faltam R$ %.2f%n", (valorFinal - pagamento));
        } else {
            double troco = pagamento - valorFinal;
            System.out.printf("Troco: R$ %.2f%n", troco);

            registrarVenda(
                    plantaEscolhida.getNome(),
                    quantidade,
                    valorTotal,
                    desconto,
                    valorFinal,
                    dataVenda
            );

            System.out.println("Venda registrada com sucesso.");
        }
    }

    public static void buscarTotalVendasPorDia(Scanner sc) {
        System.out.print("Digite a data para consulta (dd/MM/yyyy): ");
        String dataTexto = sc.nextLine();

        LocalDate dataConsulta;
        try {
            dataConsulta = LocalDate.parse(dataTexto, formatter);
        } catch (DateTimeParseException e) {
            System.out.println("Data inválida. Use o formato dd/MM/yyyy.");
            return;
        }

        int totalQuantidade = 0;

        for (Venda venda : registroVendas) {
            if (venda.getDataVenda().equals(dataConsulta)) {
                totalQuantidade += venda.getQuantidade();
            }
        }

        System.out.printf("Total de vendas no dia %s: %d unidade(s)%n",
                dataConsulta.format(formatter), totalQuantidade);
    }

    public static void buscarTotalVendasPorMes(Scanner sc) {
        System.out.print("Digite o mês (1 a 12): ");
        int mes = sc.nextInt();

        System.out.print("Digite o ano: ");
        int ano = sc.nextInt();
        sc.nextLine();

        if (mes < 1 || mes > 12) {
            System.out.println("Mês inválido.");
            return;
        }

        int totalQuantidade = 0;

        for (Venda venda : registroVendas) {
            if (venda.getDataVenda().getMonthValue() == mes && venda.getDataVenda().getYear() == ano) {
                totalQuantidade += venda.getQuantidade();
            }
        }

        System.out.printf("Total de vendas no mês %02d/%d: %d unidade(s)%n",
                mes, ano, totalQuantidade);
    }
}