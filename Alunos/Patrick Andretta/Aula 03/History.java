import java.util.ArrayList;

public class History {
    ArrayList<Purchase> purchases = new ArrayList<>();
    
    public void add(Purchase purchase) {
      purchases.add(purchase);
    }

    public void printHistory() {
      for (Purchase purchase : this.purchases) {
        System.out.println("\nCompra - " + (purchases.indexOf(purchase) + 1) );
        
        System.out.println("Quantidade total: " + purchase.getQuantity());
        System.out.printf("Valor unitário: R$ %.2f%n", purchase.getUnitValue());
        System.out.printf("Valor total: R$ %.2f%n", purchase.getTotalValue());
        System.out.printf("Valor desconto: R$ %.2f%n%n", purchase.getDiscountValue());
      }
    }
}
