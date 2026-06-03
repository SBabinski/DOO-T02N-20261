import java.util.Scanner;

public class Menu {
    public int callMenu() {
        Scanner scan = new Scanner(System.in);

        System.out.println("Bem-vindo a floricultura da Dona Gabrielinha");
        System.out.println("Escolha uma opção\n");
        System.out.println("1- Calcular Preço total");
        System.out.println("2- Calcular troco");
        System.out.println("3- Histórico de vendas");
        System.out.println("0- Sair");
        int escolha = scan.nextInt();
        scan.nextLine();

        return escolha;
    }
}
