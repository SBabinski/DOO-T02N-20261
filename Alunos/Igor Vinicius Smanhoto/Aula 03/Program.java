package fag;

import java.util.ArrayList;
import java.util.Scanner;

import fag.objects.Floricultura;

public class Program {

    public static ArrayList<Floricultura> listaPlantas = new ArrayList<>();
    public static ArrayList<String> registroVendas = new ArrayList<>();

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

    public static void registrarVenda(String nomePlanta, int quantidade, double valorTotal, double desconto, double valorFinal) {
        String venda = String.format(
                "Planta: %s | Quantidade: %d | Valor bruto: R$ %.2f | Desconto: R$ %.2f | Valor final: R$ %.2f",
                nomePlanta, quantidade, valorTotal, desconto, valorFinal
        );

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
            System.out.println("[3] - Sair");
            System.out.print("Digite a opção desejada: ");
            opcao = sc.nextInt();

            switch (opcao) {
                case 1:
                    realizarVenda(sc);
                    break;

                case 2:
                    listarRegistroVendas();
                    break;

                case 3:
                    System.out.println("Encerrando o programa...");
                    break;

                default:
                    System.out.println("Opção inválida.");
            }

        } while (opcao != 3);
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

        if (quantidade <= 0) {
            System.out.println("Quantidade inválida.");
            return;
        }

        double valorTotal = calcularValorTotal(plantaEscolhida.getPreco(), quantidade);
        double desconto = calcularDesconto(valorTotal, quantidade);
        double valorFinal = calcularValorFinal(valorTotal, desconto);

        System.out.println("\n===== RESUMO DA COMPRA =====");
        System.out.printf("Planta escolhida: %s%n", plantaEscolhida.getNome());
        System.out.printf("Quantidade: %d%n", quantidade);
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
                    valorFinal
            );

            System.out.println("Venda registrada com sucesso.");
        }
    }
}