import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        History history = new History();
        Change change = new Change(scan);
        Menu menu = new Menu(scan, history, change);

        menu.callMenu();
        scan.close();
    }
}
