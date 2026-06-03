import java.util.List;

public class Manager extends Employee {
  public Manager(
    String name,
    int age,
    String store,
    Address address,
    Double baseSalary,
    List<Double> payedSalary
  ) {
    super(name, age, store, address, baseSalary, payedSalary);
  }

  @Override
  public Double calculateBonus() {
    return this.baseSalary * 0.35;
  }
  
}
