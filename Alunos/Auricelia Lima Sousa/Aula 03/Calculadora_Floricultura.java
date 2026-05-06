import java.util.Scanner;
import java.util.ArrayList;

public class Calculadora_Floricultura{
 Scanner scan = new Scanner (System.in);
 ArrayList <String> lista = new ArrayList<>();

int quantidade = 0;
double total = 0;
double DESCONTO = 0.95;

 public double calculartotal(){

    System.out.println("Quantidade de plantas: ");
    quantidade = scan.nextInt();
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
   
     total = quantidade * preco;
    if( quantidade >10){
      total *=DESCONTO;
    }

    System.out.printf("Valor tota: %.2f ", total);

lista.add("Quantidade vendida: " + quantidade + " | " +  String.format ("preco da venda:  %.2f ", total));

System.out.println();
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
 public void registrarVendas(){
  if(lista.isEmpty()){
    System.out.println("Nenhuma venda realizada ainda");
    return;}
    System.out.println("Quantidade de vendas");
    for(String l : lista){
      System.out.println(l);
    }
  
 System.out.println();
 }
 

}