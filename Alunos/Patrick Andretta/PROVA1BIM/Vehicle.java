public class Vehicle {
  private String plate;
  private Double rentValue;

  public Vehicle(String plate, Double rentValue) {
    this.plate = plate;
    this.rentValue = rentValue;
  }

  public String getPlate() {
    return plate;
  }

  public Double getRentValue() {
    return rentValue;
  }

  public String describe() {
    return "--- Descrição do veículo ---\n " +
    "Placa: " + this.plate + "\nValor da diária: R$ " + this.rentValue;
  }
}
