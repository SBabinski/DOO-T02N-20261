import java.util.Scanner;

public class CalculadoraPlantas {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println(" | Calculadora da Dona Gabrielinha |");
        System.out.println("[1] - Calcular Preço Total");
        System.out.println("[2] - Calcular Troco");
        System.out.println("[3] - Sair");

        int opcao = sc.nextInt();

        if (opcao == 1) {

            System.out.println("Quantidade da planta:");
            int quantidade = sc.nextInt();

            System.out.println("Preço da planta:");
            double preco = sc.nextDouble();
        
            double total = quantidade * preco;

            System.out.println("Preço total: " + total);
        }

        if (opcao == 2) {

            System.out.println("Valor recebido:");
            double recebido = sc.nextDouble();

            System.out.println("Valor da compra:");
            double compra = sc.nextDouble();

            double troco = recebido - compra;

            System.out.println("Troco: " + troco);
        }

        if (opcao == 3) {
            System.out.println("Obrigado pela visita, volte sempre!");
        }

        sc.close();
    }
}