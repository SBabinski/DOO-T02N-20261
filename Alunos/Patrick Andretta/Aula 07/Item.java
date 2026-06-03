public class Item {
  protected int id;
  protected String name;
  protected String type;
  protected Double value;
  
  public Item(int id, String name, String type, Double value) {
    this.id = id;
    this.name = name;
    this.type = type;
    this.value = value;
  }

  public String describe() {
    return "Item " + this.id + 
    "\nNome: " + this.name + 
    "\nTipo: " + this.type +
    "\nValor: R$ " + this.value;
    
  }
}
