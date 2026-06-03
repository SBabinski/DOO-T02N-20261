import java.util.Scanner;
public class Classbasic {
    public static void main (String [] agrs ) {
    
        Scanner entrada = new Scanner (System.in);
        int option = 0;
        while (option != 3) {

        System.out.println ("Menu");
        System.out.println ("1 - Calcular total");
        System.out.println ("2 - Calcular troco");
        System.out.println ("3- Sair");
        option = entrada.nextInt();

        switch (option) {
            case 1:
                 System.out.println ("Digite a quantidade de plantas?");
                 double quantidade = entrada.nextDouble();
        
                 System.out.println ("Digite o valor da planta?");
                 double price = entrada.nextDouble();
        
                 double resultado = quantidade * price; 
                 System.out.println ("Valor total da venda: " + resultado);
                 break;

            case 2: 
                 System.out.println ("Valor recebido pelo cliente: ");
                 double recebido = entrada.nextDouble();

                 System.out.println ("Valor total da venda foi: ");
                 double total = entrada.nextDouble();

                 double troco = recebido - total;
                 System.out.println ("Troco do cliente: " + troco);
                 break;

            case 3:
                System.out.println ("Programa encerrado");
                break;
                default: 
                System.out.println ("Option inválid");
            }
        } 
        entrada.close();
    }
}