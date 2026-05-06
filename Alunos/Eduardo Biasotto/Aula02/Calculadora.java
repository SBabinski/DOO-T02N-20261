import java.util.Scanner;

public class Calculadora {
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        int opc = 0;

        System.out.println("Bem vindo a Loja da Dona Gabrielinha\n");

        while (opc != 3) {
            System.out.println("================================");
            System.out.println("     CALCULADORA DA LOJA");
            System.out.println("================================");
            System.out.println("1. Calcular Preço Total");
            System.out.println("2. Calcular Troco");
            System.out.println("3. Sair");
            System.out.println("================================");
            System.out.print("Escolha uma opção:");

            opc = teclado.nextInt();

            switch (opc) {
                case 1:
                    calcularValorTotal(teclado);
                    break;

                case 2:
                    calcularTroco(teclado);
                    break;

                case 3:
                    System.out.println("Saindo...\n");
                    break;

                default:
                    System.out.println("Opção Invalida!\n");
                    break;
            }
        }

        teclado.close();

    }

    public static void calcularValorTotal(Scanner teclado) {
        System.out.println("\nDigite a quantidade de Plantas desejadas:");
        int quantidade = teclado.nextInt();

        System.out.println("Digite o valor da planta escolhida: ");
        double valor = teclado.nextDouble();

        double valorTotal = quantidade * valor;

        System.out.println("\nPreço total: R$ " + valorTotal + "\n");
    }

    public static void calcularTroco(Scanner teclado) {

        System.out.println("\nDigite o valor da planta:");
        double valorPlanta = teclado.nextDouble();

        System.out.println("Digite o valor recebido: ");
        double valorRecebido = teclado.nextDouble();

        if (valorRecebido > valorPlanta) {
            double valorTroco = valorRecebido - valorPlanta;
            System.out.println("Valor do Troco: R$ " + valorTroco + "\n");
        } else if (valorRecebido < valorPlanta) {
            double valorFaltante = valorPlanta - valorRecebido;
            System.out.println("Valor Faltante: R$ " + valorFaltante + "\n");
        } else {
            System.out.println("Pagamento Exato!\n");
        }

    }

}