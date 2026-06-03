import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Purchase {
    private int quantity;
    private double unitValue;
    private double totalValue;
    private double discountValue;
    private LocalDate purchaseDate;

    private final Scanner scan;

    public Purchase(Scanner scan) {
        this(scan, LocalDate.now());
    }

    public Purchase(Scanner scan, LocalDate purchaseDate) {
        this.scan = scan;
        this.purchaseDate = purchaseDate;
    }
    
    public double calculateTotalPrice() {
        purchaseDate = readDate();

        System.out.println("Insira a quantidade de plantas compradas");
        quantity = scan.nextInt();
        scan.nextLine();
        
        System.out.println("Insira o valor unitário da planta");
        unitValue = scan.nextDouble();
        scan.nextLine();

        if (unitValue <= 0 || quantity <= 0) {
            System.out.println("O valor e a quantidade devem ser valores positivos!");
            return 0;
        }

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

    private LocalDate readDate() {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        while (true) {
            System.out.print("Digite a data da compra (dd/MM/aaaa): ");
            String input = scan.nextLine().trim();
            try {
                return LocalDate.parse(input, fmt);
            } catch (DateTimeParseException e) {
                System.out.println("Data inválida. Exemplo válido: 18/03/2026");
            }
        }
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

    public LocalDate getPurchaseDate() {
        return purchaseDate;
    }

}
