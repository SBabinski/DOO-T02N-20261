import java.util.ArrayList;
import java.util.List;

public class Store {
  private String fantasyName;
  private String comercialName;
  private String cnpj;
  private Address address;
  private List<Seller> sellers;
  private List<Client> clients;

  public Store 
  (
    String fantasyName,
    String comercialName,
    String cnpj,
    Address address
  ) 
  {
    this.fantasyName = fantasyName;
    this.comercialName = comercialName;
    this.cnpj = cnpj;
    this.address = address;
    this.sellers = new ArrayList<>();
    this.clients = new ArrayList<>();
  }

  public String introduce() {
    return (
      "Nome: " + this.fantasyName +
      "\nCNPJ: " + this.cnpj + 
      "\nEndereço: " + this.address.ShowAddress()
    );
  }

  public void addSeller(Seller seller) {
    if (seller != null) {
      this.sellers.add(seller);
    }
  }

  public void addClient(Client client) {
    if (client != null) {
      this.clients.add(client);
    }
  }

  public List<Seller> getSellers() {
    return this.sellers;
  }

  public List<Client> getClients() {
    return this.clients;
  }

  public int getSellersQuantity() {
    return this.sellers.size();
  }

  public int getClientsQuantity() {
    return this.clients.size();
  }
}
