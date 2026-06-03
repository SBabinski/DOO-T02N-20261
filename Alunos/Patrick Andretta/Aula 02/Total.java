import java.util.Scanner;

public class Total {
    Scanner scan = new Scanner(System.in);
    
    public Double CalculateTotalPrice() {
        System.out.println("Insira a quantidade de plantas compradas");
        int quantity = scan.nextInt();
        scan.nextLine();

        System.out.println("Insira o valor unitário da planta");
        double value = scan.nextDouble();
        scan.nextLine();

        double result = quantity * value;
        System.out.println("O preço total foi: R$" + result);
        System.out.println("Enter para voltar ao menu");
        scan.nextLine();
        return  result;
    }
}
