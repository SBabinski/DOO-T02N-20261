import java.util.Scanner;

public class Index {

    static Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {
        
        clearTerminal();
        menu();
    }

    public static void clearTerminal() {
        System.out.print("\033[H\033[2J");
    }

    public static void menu() {
    
        int opcao = -1;

        do {
            System.out.println("================================");
            System.out.println("( 1 ) Calcular valor Total ");
            System.out.println("( 2 ) Calcular troco ");
            System.out.println("( 0 ) Sair ");
            System.out.println("================================");
            
            System.out.printf("Informe uma opcao: ");
            opcao = scan.nextInt();
            validaOpcao(opcao);

        } while (opcao != 0);
    }

    public static void validaOpcao(int opcao) {

        if (opcao == 1) {
            calculaTotal();
        } else if (opcao == 2) {
            calculaTroco();
        } else if (opcao == 0) {
            System.out.println("Saindo...");
        } else {
            clearTerminal();
            System.out.println("================================");
            System.out.println("Opcao invalida, tente novamente.");
            menu();
        }
    }

    public static void calculaTotal() {
        
        System.out.println("================================");
        System.out.printf("Informe o valor do produto: ");
        double valorProduto = scan.nextDouble();

        System.out.printf("Informe a quantidade: ");
        int quantidade = scan.nextInt();

        double valorTotal = valorProduto * quantidade;

        clearTerminal();
        System.out.println("================================");
        System.out.println("Valor do produto: " + valorProduto);
        System.out.println("Quantidade: " + quantidade);
        System.out.println("Valor total: " + valorTotal);
    }

    public static void calculaTroco() {

        System.out.println("================================");
        System.out.printf("Informe o valor total da compra: ");        
        double valorTotal = scan.nextDouble();

        System.out.print("Informe o valor pago: ");
        double valorPago = scan.nextDouble();

        double troco = valorPago - valorTotal;

        clearTerminal();
        System.out.println("================================");
        System.out.println("Valor total da Compra: " + valorTotal);
        System.out.println("Valor recebido: " + valorPago);

        if (troco < 0) {
            System.out.println("Valor insuficiente, faltam: " + Math.abs(troco));
        } else {
            System.out.println("Troco: " + troco);
        }

    }

}