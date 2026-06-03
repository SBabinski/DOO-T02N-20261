public class Client {
  private String name;
  private int age;
  private String city;
  private String street;
  private String neighborhood;

  public Client(String name, int age, String city, String street, String neighborhood) {
    this.name = name;
    this.age = age;
    this.city = city;
    this.street = street;
    this.neighborhood = neighborhood;
  }

  public String introduce() {
    return "Nome: " + name + "\nIdade: " + age;
  }
}
