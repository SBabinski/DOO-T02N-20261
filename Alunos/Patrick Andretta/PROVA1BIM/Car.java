public class Car extends Vehicle {
  private Boolean haveAC;

  public Car(
    String plate,
    Double rentValue,
    Boolean haveAC  
  ) {
    super(plate, rentValue);
    this.haveAC = haveAC;
  } 

  @Override
  public String describe() {
    String airConditioning;
    if (haveAC) {
      airConditioning = "\nPossui ar condicionado";
    } else {
      airConditioning = "\nNao possui ar condicionado";
    }

    return super.describe() + airConditioning;
  }
}
