public class Client {
  private String name;
  private int age;
  private Address address;

  public Client(String name, int age, Address address) {
    this.name = name;
    this.age = age;
    this.address = address;
  }

  public String introduce() {
    return "Nome: " + name + "\nIdade: " + age + "\n" + address.ShowAddress() + "\n";
  }
}
