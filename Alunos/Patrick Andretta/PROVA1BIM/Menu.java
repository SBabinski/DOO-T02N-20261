import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class Menu {
  private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

  private Scanner scan;
  private List<Client> clients;
  private List<Vehicle> vehicles;
  private Rental[] rentals;

  public Menu(
    Scanner scan,
    List<Client> clients,
    List<Vehicle> vehicles,
    Rental[] rentals
  ) {
    this.scan = scan;
    this.clients = clients;
    this.vehicles = vehicles;
    this.rentals = rentals;
  }

  public void start() {
    int choice;

    do {
      System.out.println("\nBem-vindo a locadora de veiculos My Car");
      System.out.println("Escolha uma opcao:");
      System.out.println("1- Cadastrar cliente");
      System.out.println("2- Cadastrar veiculo");
      System.out.println("3- Cadastrar locacao");
      System.out.println("4- Realizar devolucao");
      System.out.println("5- Listar locacoes sem devolucao");
      System.out.println("6- Demonstracao");
      System.out.println("0- Sair");
      choice = scan.nextInt();
      scan.nextLine();

      if (choice == 1) {
        registerClient();
      } else if (choice == 2) {
        registerVehicle();
      } else if (choice == 3) {
        registerRental();
      } else if (choice == 4) {
        performReturn();
      } else if (choice == 5) {
        listOpenRentals();
      } else if (choice == 6) {
        Demo.load(clients, vehicles, rentals);
        System.out.println("Demonstracao carregada com sucesso.");
        listOpenRentals();
      }
    } while (choice != 0);

    System.out.println("Obrigado por utilizar nosso sistema, volte sempre.");
  }

  public void registerClient() {
    clients.add(Client.createClient(scan));
    System.out.println("Cliente cadastrado com sucesso.");
  }

  public void registerVehicle() {
    System.out.println("Escolha o tipo de veiculo:");
    System.out.println("1- Carro");
    System.out.println("2- Moto");
    int vehicleType = scan.nextInt();
    scan.nextLine();

    System.out.println("Informe a placa:");
    String plate = scan.nextLine();

    System.out.println("Informe o valor da diaria:");
    double rentValue = scan.nextDouble();
    scan.nextLine();

    if (vehicleType == 1) {
      System.out.println("Possui ar condicionado? 1 para sim, 0 para nao");
      int haveACOption = scan.nextInt();
      scan.nextLine();
      boolean haveAC = haveACOption == 1;
      vehicles.add(new Car(plate, rentValue, haveAC));
    } else if (vehicleType == 2) {
      System.out.println("Informe a cilindrada:");
      int cylinderCapacity = scan.nextInt();
      scan.nextLine();
      vehicles.add(new Motorcycle(plate, rentValue, cylinderCapacity));
    } else {
      System.out.println("Tipo de veiculo invalido.");
      return;
    }

    System.out.println("Veiculo cadastrado com sucesso.");
  }

  public void registerRental() {
    int freePosition = findFreePosition(rentals);
    LocalDate today = LocalDate.now();

    if (freePosition == -1) {
      System.out.println("Nao ha espaco disponivel para novas locacoes.");
      return;
    }

    if (clients.isEmpty() || vehicles.isEmpty()) {
      System.out.println("Cadastre pelo menos um cliente e um veiculo antes de registrar a locacao.");
      return;
    }

    System.out.println("Selecione o cliente:");
    listClients();
    int clientIndex = scan.nextInt() - 1;
    scan.nextLine();

    System.out.println("Selecione o veiculo:");
    listVehicles();
    int vehicleIndex = scan.nextInt() - 1;
    scan.nextLine();

    if (!isValidListSelection(clients, clientIndex) || !isValidListSelection(vehicles, vehicleIndex)) {
      System.out.println("Selecao invalida.");
      return;
    }

    System.out.println("Informe a data da retirada no formato dd/MM/yyyy:");
    LocalDate pickupDate = LocalDate.parse(scan.nextLine(), DATE_FORMATTER);

    if (pickupDate.isBefore(today)) {
      System.out.println("A data de retirada nao pode ser anterior a data atual.");
      return;
    }

    System.out.println("Informe a data da devolucao no formato dd/MM/yyyy:");
    LocalDate returnDate = LocalDate.parse(scan.nextLine(), DATE_FORMATTER);

    if (!returnDate.isAfter(pickupDate)) {
      System.out.println("A data de devolucao deve ser posterior a data de retirada.");
      return;
    }

    rentals[freePosition] = new Rental(
      clients.get(clientIndex),
      vehicles.get(vehicleIndex),
      pickupDate,
      returnDate,
      false
    );

    System.out.println("Locacao cadastrada com sucesso.");
    System.out.println(rentals[freePosition].describe());
  }

  public void performReturn() {
    if (!hasOpenRental()) {
      System.out.println("Nao existem locacoes pendentes de devolucao.");
      return;
    }

    System.out.println("Selecione a locacao para realizar a devolucao:");
    listOpenRentalsWithIndexes();
    int rentalIndex = scan.nextInt() - 1;
    scan.nextLine();

    if (!isValidSelection(rentals, rentalIndex) || rentals[rentalIndex].isReturned()) {
      System.out.println("Selecao invalida.");
      return;
    }

    rentals[rentalIndex].markAsReturned();
    System.out.println("Devolucao realizada com sucesso.");
  }

  public void listOpenRentals() {
    if (!hasOpenRental()) {
      System.out.println("Nao existem locacoes sem devolucao.");
      return;
    }

    for (int i = 0; i < rentals.length; i++) {
      if (rentals[i] != null && !rentals[i].isReturned()) {
        System.out.println("\nLocacao " + (i + 1));
        System.out.println(rentals[i].describe());
      }
    }
  }

  public void listClients() {
    for (int i = 0; i < clients.size(); i++) {
      System.out.println((i + 1) + "- " + clients.get(i).describe() + "\n");
    }
  }

  public void listVehicles() {
    for (int i = 0; i < vehicles.size(); i++) {
      System.out.println((i + 1) + "- " + vehicles.get(i).describe() + "\n");
    }
  }

  public void listOpenRentalsWithIndexes() {
    for (int i = 0; i < rentals.length; i++) {
      if (rentals[i] != null && !rentals[i].isReturned()) {
        System.out.println((i + 1) + "- " + rentals[i].describe() + "\n");
      }
    }
  }

  public int findFreePosition(Object[] items) {
    for (int i = 0; i < items.length; i++) {
      if (items[i] == null) {
        return i;
      }
    }

    return -1;
  }

  public boolean hasOpenRental() {
    for (Rental rental : rentals) {
      if (rental != null && !rental.isReturned()) {
        return true;
      }
    }

    return false;
  }

  public boolean isValidSelection(Object[] items, int index) {
    return index >= 0 && index < items.length && items[index] != null;
  }

  public boolean isValidListSelection(List<?> items, int index) {
    return index >= 0 && index < items.size();
  }
}
