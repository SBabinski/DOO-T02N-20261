import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Calculadora calc = new Calculadora();
        Scanner sc = new Scanner(System.in);

        int opc;
        do {
            System.out.println("\n===MENU===");
            System.out.println("[1] Calcular preço total");
            System.out.println("[2] Calcular troco");
            System.out.println("[3] Sair");

            System.out.println("Entre com a opção desejada: ");
            opc = sc.nextInt();
            sc.nextLine();

            switch (opc) {

                case 1:
                    calc.calcularPrecoTotal();
                    break;
                case 2:
                    calc.calcularTroco();
                    break;
                case 3:
                    System.out.println("Saindo...");
                    break;

                default:
                    System.out.println("Opção inválida!");
                    break;
            }

        } while (opc != 3);

        sc.close();
    }
}
