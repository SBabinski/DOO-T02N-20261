import java.util.ArrayList;
import java.util.List;

public class Populate {
  public static List<Store> create() {
    Address addressStoreA = new Address("PR", "Cascavel", "Centro", "Rua Um", "S/N", "Próximo à praça");
    Address addressStoreB = new Address("PR", "Cascavel", "Country", "Rua Dois", "120", "Sala 2");
    Address addressStoreC = new Address("PR", "Cascavel", "Parque Verde", "Rua Tres", "250", "Esquina");

    Store storeA = new Store(
      "Loja A",
      "Comercial A",
      "11111111111111",
      addressStoreA
    );

    Store storeB = new Store(
      "Loja B",
      "Comercial B",
      "22222222222222",
      addressStoreB
    );

    Store storeC = new Store(
      "Loja C",
      "Comercial C",
      "33333333333333",
      addressStoreC
    );

    Address addressSellerB = new Address("PR", "Cascavel", "Country", "Rua Dois", "45", "Casa");
    Address addressSellerC = new Address("PR", "Cascavel", "Parque Verde", "Rua Tres", "78", "Apartamento 3");

    Seller seller1 = new Seller(
      "Vendedor 1",
      25,
      "Loja B",
      addressSellerB,
      2500.0,
      new ArrayList<>(List.of(2600.0, 2550.0, 2700.0))
    );
    storeB.addSeller(seller1);

    Seller seller2 = new Seller(
      "Vendedor 2",
      28,
      "Loja C",
      addressSellerC,
      2600.0,
      new ArrayList<>(List.of(2650.0, 2600.0, 2750.0))
    );
    Seller seller3 = new Seller(
      "Vendedor 3",
      30,
      "Loja C",
      addressSellerC,
      2700.0,
      new ArrayList<>(List.of(2700.0, 2800.0, 2750.0))
    );
    Seller seller4 = new Seller(
      "Vendedor 4",
      22,
      "Loja C",
      addressSellerC,
      2400.0,
      new ArrayList<>(List.of(2450.0, 2500.0, 2550.0))
    );
    Seller seller5 = new Seller(
      "Vendedor 5",
      26,
      "Loja C",
      addressSellerC,
      2550.0,
      new ArrayList<>(List.of(2600.0, 2650.0, 2580.0))
    );
    Seller seller6 = new Seller(
      "Vendedor 6",
      29,
      "Loja C",
      addressSellerC,
      2650.0,
      new ArrayList<>(List.of(2700.0, 2750.0, 2680.0))
    );
    storeC.addSeller(seller2);
    storeC.addSeller(seller3);
    storeC.addSeller(seller4);
    storeC.addSeller(seller5);
    storeC.addSeller(seller6);

    Address addressClientB = new Address("PR", "Cascavel", "Country", "Rua Dois", "300", "Fundos");
    Address addressClientC = new Address("PR", "Cascavel", "Parque Verde", "Rua Tres", "410", "Casa 2");

    Client client1 = new Client("Cliente 1", 21, addressClientB);
    storeB.addClient(client1);

    Client client2 = new Client("Cliente 2", 20, addressClientC);
    Client client3 = new Client("Cliente 3", 23, addressClientC);
    Client client4 = new Client("Cliente 4", 24, addressClientC);
    Client client5 = new Client("Cliente 5", 22, addressClientC);
    Client client6 = new Client("Cliente 6", 26, addressClientC);
    storeC.addClient(client2);
    storeC.addClient(client3);
    storeC.addClient(client4);
    storeC.addClient(client5);
    storeC.addClient(client6);

    return new ArrayList<>(List.of(storeA, storeB, storeC));
  }
}
