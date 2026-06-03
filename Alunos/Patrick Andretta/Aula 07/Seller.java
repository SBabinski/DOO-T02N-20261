import java.util.List;

public class Seller extends Employee {
  public Seller(
    String name,
    int age,
    String store,
    Address address,
    Double baseSalary,
    List<Double> payedSalary
  ) {
    super(name, age, store, address, baseSalary, payedSalary);
  }
}
