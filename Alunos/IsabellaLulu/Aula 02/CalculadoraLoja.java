
import java.util.Scanner;

public class CalculadoraLoja{

     static Scanner scanner = new Scanner (System.in);

    public static void main(String[] args) {
        mostrarMenu();

    }

    public static void mostrarMenu(){
        int opcao = 1000;

    do { 
        System.out.println("\n--Calculadora para loja de plantas da Dona Gabrielinha--");
        System.out.println("[1] Calcular Preço Total");
        System.out.println("[2] Calcular Troco");
        System.out.println("[0] Sair");
        System.out.println("Escolha uma opção: ");
        opcao = scanner.nextInt();
        scanner.nextLine();

        validarMenu(opcao);

    } while (opcao!=0);
    }

    public static void validarMenu(int opcao){
        switch (opcao) {
            case 1: 
            calcularTotal();            
            break;

            case 2: 
            calcularTroco();
            break;

            case 0: 
            break;

            default:
			System.out.println("Opção invalida!");
			break;
        }
    }

    public static void calcularTotal(){
        System.out.println("Digite a quantidade de plantas: ");
        int quantidade = scanner.nextInt();

        System.out.println("Digite o preço da planta: ");
        double preco = scanner.nextDouble();

        scanner.nextLine();

        double total = quantidade * preco;

        System.out.println("Preço total da compra: R$" + total);
    }

    public static void calcularTroco(){
        System.out.println("Digite o valor recebido: ");
        int recebido = scanner.nextInt();

        System.out.println("Digite o valor total da compra: ");
        double valorTotal = scanner.nextDouble();

        double troco = recebido - valorTotal;

         System.out.println("Troco: R$ " + troco);
    }
}