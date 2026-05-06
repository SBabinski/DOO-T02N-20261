import java.util.ArrayList;
import java.util.List;

public class Populate {
  public static List<Store> create() {
    Store storeA = new Store(
      "Loja A",
      "Comercial A",
      "11111111111111",
      "Cascavel",
      "Centro",
      "Rua Um"
    );

    Store storeB = new Store(
      "Loja B",
      "Comercial B",
      "22222222222222",
      "Cascavel",
      "Country",
      "Rua Dois"
    );

    Store storeC = new Store(
      "Loja C",
      "Comercial C",
      "33333333333333",
      "Cascavel",
      "Parque Verde",
      "Rua Tres"
    );

    Seller seller1 = new Seller(
      "Vendedor 1",
      25,
      "Loja B",
      "Cascavel",
      "Country",
      "Rua Dois",
      2500.0,
      new ArrayList<>(List.of(2600.0, 2550.0, 2700.0))
    );
    storeB.addSeller(seller1);

    Seller seller2 = new Seller(
      "Vendedor 2",
      28,
      "Loja C",
      "Cascavel",
      "Parque Verde",
      "Rua Tres",
      2600.0,
      new ArrayList<>(List.of(2650.0, 2600.0, 2750.0))
    );
    Seller seller3 = new Seller(
      "Vendedor 3",
      30,
      "Loja C",
      "Cascavel",
      "Parque Verde",
      "Rua Tres",
      2700.0,
      new ArrayList<>(List.of(2700.0, 2800.0, 2750.0))
    );
    Seller seller4 = new Seller(
      "Vendedor 4",
      22,
      "Loja C",
      "Cascavel",
      "Parque Verde",
      "Rua Tres",
      2400.0,
      new ArrayList<>(List.of(2450.0, 2500.0, 2550.0))
    );
    Seller seller5 = new Seller(
      "Vendedor 5",
      26,
      "Loja C",
      "Cascavel",
      "Parque Verde",
      "Rua Tres",
      2550.0,
      new ArrayList<>(List.of(2600.0, 2650.0, 2580.0))
    );
    Seller seller6 = new Seller(
      "Vendedor 6",
      29,
      "Loja C",
      "Cascavel",
      "Parque Verde",
      "Rua Tres",
      2650.0,
      new ArrayList<>(List.of(2700.0, 2750.0, 2680.0))
    );
    storeC.addSeller(seller2);
    storeC.addSeller(seller3);
    storeC.addSeller(seller4);
    storeC.addSeller(seller5);
    storeC.addSeller(seller6);

    Client client1 = new Client("Cliente 1", 21, "Cascavel", "Rua Dois", "Country");
    storeB.addClient(client1);

    Client client2 = new Client("Cliente 2", 20, "Cascavel", "Rua Tres", "Parque Verde");
    Client client3 = new Client("Cliente 3", 23, "Cascavel", "Rua Tres", "Parque Verde");
    Client client4 = new Client("Cliente 4", 24, "Cascavel", "Rua Tres", "Parque Verde");
    Client client5 = new Client("Cliente 5", 22, "Cascavel", "Rua Tres", "Parque Verde");
    Client client6 = new Client("Cliente 6", 26, "Cascavel", "Rua Tres", "Parque Verde");
    storeC.addClient(client2);
    storeC.addClient(client3);
    storeC.addClient(client4);
    storeC.addClient(client5);
    storeC.addClient(client6);

    return new ArrayList<>(List.of(storeA, storeB, storeC));
  }
}
