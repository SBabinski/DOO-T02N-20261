import java.time.LocalDate;
import java.util.List;

public class Demo {
  public static void load(List<Client> clients, List<Vehicle> vehicles, Rental[] rentals) {
    clients.clear();
    vehicles.clear();
    clearRentals(rentals);

    clients.add(new Client("Patrick", "123.456.765-00", "RG23231123"));
    clients.add(new Client("Bruno Lima", "329.232.999-90", "RG213456"));

    vehicles.add(new Car("ABC-1234", 150.0, true));
    vehicles.add(new Motorcycle("XYZ-9876", 90.0, 300));

    rentals[0] = new Rental(
      clients.get(0),
      vehicles.get(0),
      LocalDate.of(2026, 4, 10),
      LocalDate.of(2026, 4, 15),
      true
    );

    rentals[1] = new Rental(
      clients.get(1),
      vehicles.get(1),
      LocalDate.of(2026, 4, 20),
      LocalDate.of(2026, 4, 25),
      false
    );
  }

  private static void clearRentals(Rental[] rentals) {
    for (int i = 0; i < rentals.length; i++) {
      rentals[i] = null;
    }
  }
}
