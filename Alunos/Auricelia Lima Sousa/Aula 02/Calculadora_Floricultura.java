import java.util.Scanner;

public class Calculadora_Floricultura{
 Scanner scan = new Scanner (System.in);

 public double calculartotal(){

    System.out.println("Quantidade de plantas: ");
    int quantidade = scan.nextInt();
    scan.nextLine();

    if(quantidade <=0){
        System.out.println("Erro!");
        return 0;
    }
System.out.println("Preco unitario: ");

double preco  = scan.nextDouble();
    scan.nextLine();
  if(preco <=0){
        System.out.println("Erro!");
        return 0;
    }
   
    double total = quantidade * preco;
    System.out.printf("Valor tota: %.2f ", total);
return total;
 }
 public double calcularpreco(){

   System.out.println("Entre com o total da compra: ");
   double total = scan.nextDouble();
   scan.nextLine();

    if(total <=0){
        System.out.println("Erro!");
        return 0;
    } 

   
    System.out.println("Entre com o pagamento do cliente: ");
    double valor = scan.nextDouble();
    scan.nextLine();
  if(valor <total){
    System.out.println("Valor errado");
    return 0;
  } 
  else{
    double troco = valor - total;
    System.out.printf("Troco: %.2f" , troco);
    return troco;


  }
 

 }



}