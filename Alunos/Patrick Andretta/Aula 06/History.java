import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class History {
    ArrayList<Purchase> purchases = new ArrayList<>();
    private final DateTimeFormatter dateFmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private final DateTimeFormatter monthFmt = DateTimeFormatter.ofPattern("MM/yyyy");
    
    public void add(Purchase purchase) {
        purchases.add(purchase);
    }

    public void printHistory() {
        int index = 1;
        for (Purchase purchase : this.purchases) {
            printPurchase(purchase, index);
            index++;
        }
    }

    public void printByDate(LocalDate date) {
        int index = 1;
        boolean found = false;
        for (Purchase purchase : this.purchases) {
            if (date != null && date.equals(purchase.getPurchaseDate())) {
                printPurchase(purchase, index);
                found = true;
            }
            index++;
        }
        if (!found) {
            System.out.println("Nenhuma compra encontrada na data " + date.format(dateFmt));
        }
    }

    public void printByMonth(YearMonth month) {
        int index = 1;
        boolean found = false;
        for (Purchase purchase : this.purchases) {
            LocalDate date = purchase.getPurchaseDate();
            if (date != null && YearMonth.from(date).equals(month)) {
                printPurchase(purchase, index);
                found = true;
            }
            index++;
        }
        if (!found) {
            System.out.println("Nenhuma compra encontrada no mês " + month.format(monthFmt));
        }
    }

    private void printPurchase(Purchase purchase, int index) {
        System.out.println("\nCompra - " + index);
        if (purchase.getPurchaseDate() != null) {
            System.out.println("Data: " + purchase.getPurchaseDate().format(dateFmt));
        }

        System.out.println("Quantidade total: " + purchase.getQuantity());
        System.out.printf("Valor unitário: R$ %.2f%n", purchase.getUnitValue());
        System.out.printf("Valor total: R$ %.2f%n", purchase.getTotalValue());
        System.out.printf("Valor desconto: R$ %.2f%n%n", purchase.getDiscountValue());
    }
}
