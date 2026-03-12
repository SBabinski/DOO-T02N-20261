import java.util.Scanner;

public class Purchase {
    private int quantity;
    private double unitValue;
    private double totalValue;
    private double discountValue;

    Scanner scan = new Scanner(System.in);
    
    public double calculateTotalPrice() {
        System.out.println("Insira a quantidade de plantas compradas");
        quantity = scan.nextInt();
        scan.nextLine();

        System.out.println("Insira o valor unitário da planta");
        unitValue = scan.nextDouble();
        scan.nextLine();

        totalValue = quantity * unitValue;

        if (quantity > 10) {
            discountValue = totalValue - (totalValue * 0.95);
            totalValue -= discountValue;

            System.out.println("Por comprar mais de 10 plantas você ganhou 5% de desconto!! :D");
        }

        System.out.printf("O preço total foi: R$%.2f%n", totalValue);
        System.out.println("Enter para voltar ao menu");
        scan.nextLine();
        return totalValue;
    }

    public int getQuantity() {
      return quantity;
    }

    public double getUnitValue() {
      return unitValue;
    }

    public double getTotalValue() {
      return totalValue;
    }

    public double getDiscountValue() {
      return discountValue;
    }

}
