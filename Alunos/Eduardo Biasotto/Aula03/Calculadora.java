import java.util.ArrayList;
import java.util.Scanner;

public class Calculadora {
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);

        ArrayList<Venda> listaDeVendas = new ArrayList<>();

        int opcao = 0;

        System.out.println("Bem vindo a Loja da Dona Gabrielinha\n");

        while (opcao != 3) {
            System.out.println("================================");
            System.out.println("     CALCULADORA DA LOJA");
            System.out.println("================================");
            System.out.println("1. Calcular Preço Total");
            System.out.println("2. Listar vendas realizadas");
            System.out.println("0. Sair");
            System.out.println("================================");
            System.out.print("Escolha uma opção:");

            opcao = teclado.nextInt();

            switch (opcao) {
                case 1:
                    Venda venda = calcularValorTotal(teclado);
                    listaDeVendas.add(venda);
                    break;

                case 2:
                    mostrarVendas(listaDeVendas);
                    break;

                case 0:
                    System.out.println("Saindo...\n");
                    break;

                default:
                    System.out.println("Opção Invalida, Tente novamente!\n");
                    break;
            }
        }

        teclado.close();
    }

    public static Venda calcularValorTotal(Scanner teclado) {
        System.out.println("\nDigite a quantidade de Plantas desejadas:");
        int quantidade = teclado.nextInt();

        System.out.println("Digite o valor unitario da planta escolhida: ");
        double valorUnitario = teclado.nextDouble();

        double valorTotal = quantidade * valorUnitario;
        double valorFinal = valorTotal;
        double valorDesconto = 0;

        if (quantidade > 10) {
            valorDesconto = valorTotal * 0.05;
            valorFinal = valorTotal - valorDesconto;
            System.out.println("Desconto de 5% aplicado!\n");
        }

        System.out.println("Valor total da compra: R$ " +valorFinal + "\n");

        System.out.println("Digite o valor recebido: ");
        double valorRecebido = teclado.nextDouble();

        if (valorRecebido > valorFinal) {
            double valorTroco = valorRecebido - valorFinal;
            System.out.println("Valor do Troco: R$ " + valorTroco + "\n");
        } 
        else if (valorRecebido < valorFinal) {
            double valorFaltante = valorFinal - valorRecebido;
            System.out.println("Valor Faltante: R$ " + valorFaltante + "\n");
        } 
        else {
            System.out.println("\nPagamento Exato!\n");
        }

        return new Venda(quantidade,valorTotal,valorDesconto,valorFinal);
    }

        public static void mostrarVendas(ArrayList<Venda> listaDeVendas) {
            if (listaDeVendas.isEmpty()) {
                System.out.println("\nNenhuma venda realizada!");
                return;
            }

            int contador = 1;
            double totalGeral = 0;

            for(Venda v : listaDeVendas){
                System.out.println("\nVenda: " + contador + ":");
                System.out.println("Quantidade " + v.quantidade);
                System.out.println("Valor total: R$" + v.valorTotal);
                System.out.println("Desconto: R$" + v.valorDesconto);
                System.out.println("valor final: R$" + v.valorFinal);
                System.out.println("\n-----------------------------");
                contador++;
                totalGeral += v.valorFinal;
            }

            System.out.println("\nTotal Geral vendido : R$" + totalGeral + "\n");
            System.out.println("-----------------------------");

        }
    }