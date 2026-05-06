import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Rental {
  private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");
  private Client client;
  private Vehicle vehicle;
  private LocalDate pickupDate;
  private LocalDate returnDate;
  private boolean returned;

  public Rental(
    Client client,
    Vehicle vehicle,
    LocalDate pickupDate,
    LocalDate returnDate,
    boolean returned
  ) {
    this.client = client;
    this.vehicle = vehicle;
    this.pickupDate = pickupDate;
    this.returnDate = returnDate;
    this.returned = returned;
  }

  public boolean isReturned() {
    return returned;
  }

  public void markAsReturned() {
    returned = true;
  }

  public long getDailyCount() {
    return returnDate.toEpochDay() - pickupDate.toEpochDay();
  }

  public double getTotalValue() {
    return getDailyCount() * vehicle.getRentValue();
  }

  public String describe() {
    String returnStatus = returned ? "Realizada" : "Nao realizada";

    return "--- Dados da locacao ---\n" +
      client.describe() + "\n" +
      vehicle.describe() + "\n" +
      "Data da retirada: " + pickupDate.format(DATE_FORMATTER) + "\n" +
      "Data da devolucao: " + returnDate.format(DATE_FORMATTER) + "\n" +
      "Situacao da devolucao: " + returnStatus + "\n" +
      "Quantidade de diarias: " + getDailyCount() + "\n" +
      "Valor total: R$ " + getTotalValue();
  }
}
