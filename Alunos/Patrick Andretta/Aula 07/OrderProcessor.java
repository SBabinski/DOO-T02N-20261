import java.time.LocalDate;
import java.util.List;

public class OrderProcessor {
  private final LocalDate currentDate;

  public OrderProcessor() {
    this(LocalDate.now());
  }

  public OrderProcessor(LocalDate currentDate) {
    this.currentDate = currentDate;
  }

  public Order process(
    int id,
    LocalDate creationDate,
    LocalDate reservationDueDate,
    Client client,
    Seller seller,
    Store store,
    List<Item> items
  ) {
    if (!confirmPayment(reservationDueDate)) {
      throw new IllegalStateException("Reservation has expired.");
    }

    return new Order(
      id,
      creationDate,
      currentDate,
      reservationDueDate,
      client,
      seller,
      store,
      items
    );
  }

  private boolean confirmPayment(LocalDate reservationDueDate) {
    return !currentDate.isAfter(reservationDueDate);
  }
}
