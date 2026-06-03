public class Motorcycle extends Vehicle {
  private int cylinderCapacity;

  public Motorcycle(
    String plate,
    Double rentValue,
    int cylinderCapacity
  ) {
    super(plate, rentValue);
    this.cylinderCapacity = cylinderCapacity;
  }

  @Override
  public String describe() {
    return super.describe() + "\nCilindrada: " + cylinderCapacity + "cc";
  }
}
