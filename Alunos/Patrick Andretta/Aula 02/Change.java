import java.util.Scanner;

public class Change {
    public double change(double purchaseTotalValue) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Valor total pago pelo cliente");
        double total = scan.nextDouble();
        scan.nextLine();

        double change = total - purchaseTotalValue;

        if (change > 0) {
        System.out.println("O valor a ser devolvido é: R$" + change);
        } else if (change == 0) {
            System.out.println("O valor está correto e não precisa de troco!");
        } else {
            System.out.println("O valor pago é menor do que o valor total da compra");
        }
        return change;
    }
}
