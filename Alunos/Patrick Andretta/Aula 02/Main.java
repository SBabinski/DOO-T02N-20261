import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Menu menu = new Menu();
        Total total = new Total();
        Change change = new Change();
        double totalPrice = 0;
        
        int escolha = menu.callMenu();
        
        while (escolha != 0) {
            if (escolha == 1) {
                totalPrice = total.CalculateTotalPrice();
                escolha = menu.callMenu();
            } else if (escolha == 2) {
                if (totalPrice == 0) {
                    System.out.println("Seu carrinho está vazio, faça uma compra primeiro!\n");
                    System.out.println("Enter para voltar ao menu");
                    scan.nextLine();
                    escolha = menu.callMenu();
                } else {
                    change.change(totalPrice);
                    System.out.println("Enter para voltar ao menu");
                    scan.nextLine();
                    escolha = menu.callMenu();
                }
            }
        }
        scan.close();
        System.out.println("Obrigado por utilizar nosso sistema, volte sempre!!");      
    }
}
