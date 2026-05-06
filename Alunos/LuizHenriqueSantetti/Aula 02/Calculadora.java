import java.util.Scanner;

public class Calculadora {
    public static void main (String[] args) {
        Scanner entrada = new Scanner(System.in);
        int escolha;
        while (true) {
            
            System.out.print("ESCOLHA ALGUMA OPCAO");
           
            System.out.println("1 - TOTAL");
            System.out.println("2 - TROCO");
            System.out.println("3 - FECHAR");
            
            escolha = entrada.nextInt();
        if (escolha == 1) {
    
            System.out.print("QUANTIDADE =");
            int qtd = entrada.nextInt();
            System.out.print("PRECO =");
            double preco = entrada.nextDouble();
        
        double total = qtd * preco;

            System.out.println("TOTAL = R$" + total);


        }  else if (escolha == 2) {
            
            System.out.print("TOTAL PAGO =");
            double pago = entrada.nextDouble();
        
        double compra = entrada.nextDouble();
        double troco = pago - compra;
            
            System.out.println("TROCO = R$ " + troco);
        
        } else if (escolha == 3) {

            System.out.println("FECHAR");
            break;

        } else {

            System.out.println("INVALIDO");
            System.out.println();

        }
        }
        entrada.close();
    }
}