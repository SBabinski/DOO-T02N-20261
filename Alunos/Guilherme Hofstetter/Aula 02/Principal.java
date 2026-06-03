import java.util.Scanner;

public class Principal {
    private static Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {
        int opcao;

        do {
            System.out.println("[1] - Calcular Preço Total");
            System.out.println("[2] - Calcular Troco");
            System.out.println("[3] - Sair");

        opcao = scan.nextInt();
        scan.nextLine();

        switch (opcao) {
            case 1: calcularTotal();
                break;
            case 2: calcularTroco();
                break;
            case 3: System.out.println("Saindo!");
                break;
            default: System.out.println("Opcão inválida!");
                break;
        }
            } while (opcao != 3);

            scan.close();
    } 

    private static void calcularTotal() {
        Plantas plantas = new Plantas();

        System.out.println("Digite a quantidade de plantas: ");
        plantas.setQuantidade(scan.nextInt());

        System.out.println("Digite o preço da planta: ");
        plantas.setPreco(scan.nextDouble());

        double total = plantas.getQuantidade() * plantas.getPreco();
        System.out.println("O preço total é: " + total);
    }

    private static void calcularTroco() {
        Plantas plantas = new Plantas();

        System.out.println("Digite o valor pago: ");
        double valorPago = scan.nextDouble();

        System.out.println("Digite o preço total: ");
        double total = scan.nextDouble();
        double troco = valorPago - total;

        if (troco < 0) {
            System.out.println("Valor pago é insuficiente. Faltam: " + (-troco));
        } else {
            System.out.println("O troco é: " + troco);
        }
    }

}

