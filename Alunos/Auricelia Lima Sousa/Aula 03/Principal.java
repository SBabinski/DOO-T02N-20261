import java.util.Scanner;

public class Principal {
    public static void main (String[] args){
     Scanner scan = new Scanner (System.in);
Calculadora_Floricultura calculadora = new Calculadora_Floricultura();
      int opcao;

   do {
   System.out.println("\nMenu\n");
   System.out.println(" [1] - Calcular Preço Total");
   System.out.println(" [2] - Calcular Troco    ");
   System.out.println("[3] -Listar vendas  ");
 System.out.println("[0] - Sair  ");

  System.out.println("Entre com a opcao");

    
   opcao = scan.nextInt();
   scan.nextLine();
   
   switch (opcao){
  
    case 1:
        calculadora.calculartotal();
    break;

    case 2:
       calculadora.calcularpreco();
       break;
      case 3:
        calculadora.registrarVendas();
        break;
    case 0:
    System.out.println("Saindo...");
    break;

    default: 

    System.out.println("Opcao invalida");
    break;

   } 



   } while (opcao !=0);

scan.close();
    }
}