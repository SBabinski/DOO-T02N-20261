import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

  public static void main(String[] args) {
    Scanner scan = new Scanner(System.in);
    List<Client> clients = new ArrayList<>();
    List<Vehicle> vehicles = new ArrayList<>();
    Rental[] rentals = new Rental[10];
    Menu menu = new Menu(scan, clients, vehicles, rentals);

    menu.start();
    scan.close();
  }
}
