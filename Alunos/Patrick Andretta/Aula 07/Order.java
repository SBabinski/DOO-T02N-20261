import java.time.LocalDate;
import java.util.List;
import java.time.format.DateTimeFormatter;

public class Order {
  protected int id;
  protected LocalDate creationDate;
  protected LocalDate paymentDate;
  protected LocalDate reservationDueDate;
  protected Client client;
  protected Seller seller;
  protected Store store;
  protected List<Item> items;

  public Order(
    int id,
    LocalDate creationDate,
    LocalDate paymentDate,
    LocalDate reservationDueDate,
    Client client,
    Seller seller,
    Store store,
    List<Item> items
  ) {
    this.id = id;
    this.creationDate = creationDate;
    this.paymentDate = paymentDate;
    this.reservationDueDate = reservationDueDate;
    this.client = client;
    this.seller = seller;
    this.store = store;
    this.items = items;
  }

  public double calculateTotalValue() {
    double total = 0.0;

    if (items == null) {
      return total;
    }

    for (Item item : items) {
      if (item != null && item.value != null) {
        total += item.value;
      }
    }

    return total;
  }

  public String createOrderDescription() {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    return "Data de criação do pedido: " + creationDate.format(formatter) +
      "\nValor total do pedido: R$ " + calculateTotalValue();
  }
}
