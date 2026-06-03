import java.util.ArrayList;
import java.util.List;

public class Seller {
  private String name;
  private int age;
  private String store;
  private String city;
  private String neighborhood;
  private String street;
  private Double baseSalary;
  private List<Double> payedSalary = new ArrayList<>();
  
  public Seller
  (
    String name, 
    int age,
    String store, 
    String city, 
    String neighborhood, 
    String street, 
    Double baseSalary,
    List<Double> payedSalary
  ) 
  {
    this.name = name;
    this.age = age;
    this.store = store;
    this.city = city;
    this.neighborhood = neighborhood;
    this.street = street;
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
