import java.util.ArrayList;
import java.util.List;

public class Employee {
  protected String name;
  protected int age;
  protected String store;
  protected Address address;
  protected Double baseSalary;
  protected List<Double> payedSalary = new ArrayList<>();
  
  public Employee
  (
    String name, 
    int age,
    String store, 
    Address address,
    Double baseSalary,
    List<Double> payedSalary
  ) 
  {
    this.name = name;
    this.age = age;
    this.store = store;
    this.address = address;
    this.baseSalary = baseSalary;
    this.payedSalary = payedSalary;
  }
  
  public Double calculateBonus() {
    return this.baseSalary * 0.2;
  }

  public Double calculateAverage() {
    Double sum = 0.0;

    for (Double salary : payedSalary) {
      sum += salary;
    }
    return sum / payedSalary.size();
  }

  public String introduce() {
    return "Nome: " + name + "\nIdade: " + age + "\nLoja: " + store; 
  }
}
