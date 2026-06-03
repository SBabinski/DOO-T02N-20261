public class Address {
  private String state;
  private String city;
  private String neighborhood;
  private String street;
  private String number;
  private String complement;

  public Address(String state, String city, String neighborhood, String street, String number, String complement) {
    this.state = state;
    this.city = city;
    this.neighborhood = neighborhood;
    this.street = street;
    this.number = number;
    this.complement = complement;
  }

  public String ShowAddress() {
    return "Endereço: " + this.street + ", " + this.number +
      " - Bairro: " + this.neighborhood +
      " - " + this.city + "/" + this.state +
      " - Complemento: " + this.complement;
  }

  public String getState() {
    return this.state;
  }

  public String getCity() {
    return this.city;
  }

  public String getNeighborhood() {
    return this.neighborhood;
  }

  public String getStreet() {
    return this.street;
  }

  public String getNumber() {
    return this.number;
  }

  public String getComplement() {
    return this.complement;
  }
}
