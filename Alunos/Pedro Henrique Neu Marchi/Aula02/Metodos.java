import java.util.Scanner;

public class Metodos {
   static Scanner leitor = new Scanner(System.in);

    public static void calcularPreco(){
        System.out.println("Quantidade comprada: ");
        int quantidade = leitor.nextInt();
        leitor.nextLine();
        System.out.println("Valor Unitario: ");
        double valor = leitor.nextDouble();
        leitor.nextLine();
        
        double total = quantidade * valor;

        System.out.println("Preco total: " + total);

    }

    public static void calcularTroco(){
        System.out.println("Valor recebido: ");
        double recebido = leitor.nextDouble();
        leitor.nextLine();
        System.out.println("Valor da compra: ");
        double compra = leitor.nextDouble();
        leitor.nextLine();
        
        double troco = recebido - compra;

        System.out.println("Troco: " + troco);
    }
}
