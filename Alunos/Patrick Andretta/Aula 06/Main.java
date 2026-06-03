import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        List<Store> stores = Populate.create();
        Scanner scan = new Scanner(System.in);
        History history = new History();
        Change change = new Change(scan);
        Menu menu = new Menu(scan, history, change, stores);

        menu.callMenu();
        scan.close();
    }
}
