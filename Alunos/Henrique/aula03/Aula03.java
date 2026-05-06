package fag;

import java.util.Scanner;
import java.util.ArrayList;

public class Aula03 {

	public static void main(String[] args) {
		
		 Scanner scan = new Scanner(System.in);
		ArrayList<Venda> vendas = new ArrayList<>();
		
		 double total = 0;
		 int qtd=0;
		 double valor=0;
		 double desconto=0;
		 
		int opc =0;
		 
		 while(opc != 4) {
		     System.out.println("----------------------------------");
			 System.out.println("Digite o número do que deseja:");
			 System.out.println("1 - Calcular Preço Total.");
			 System.out.println("2 - Calcular Troco.");
			 System.out.println("3 - Registro de Vendas.");
			 System.out.println("4 - Sair.");
			 opc = scan.nextInt();
			 scan.nextLine();
			 switch(opc) {
		 case 1:
			 System.out.println("Digite a quantidade de plantas");
			 qtd = scan.nextInt();
			 scan.nextLine();
			 
			 System.out.println("Digite o valor da planta");
			 valor = scan.nextDouble();
			 scan.nextLine();
			 
			total = qtd*valor;
			desconto=0;
			
			if(qtd > 10){
			     desconto = total * 0.05;
			     total = total - desconto;
			     System.out.println("Desconto de 5% por ter mais que 10 plantas aplicado.");
			 }
			 
			 System.out.println("O valor total é: " + total);
			 break;
		 case 2:
			 System.out.println("Digite o valor pago pelo cliente.");
			 double pago = scan.nextDouble();
			 scan.nextLine();
			 
			 double troco = pago - total;
			 
			 if(troco < 0){
			     System.out.println("Saldo Insuficiente.");
			     break;
			 }
			 System.out.println("O troco é: "+troco);
			 Venda v = new Venda (total, valor, qtd, desconto);
			 vendas.add(v);
			 break;
		 case 3: 
			     System.out.println("Registro de vendas:");
			     int count=0;
			     for (Venda venda : vendas){
			         count++;
			         System.out.println("----------------------------------");
			         System.out.println(count+" Venda");
			         System.out.println("Total da Venda: "+venda.total);
			         System.out.println("Quantidade de Planta: "+venda.qtd);
			         System.out.println("Valor de cada Planta: "+venda.valor);
			         System.out.println("Desconto aplicado: "+venda.desconto);
			     }
			     break;
		 case 4:
			 System.out.println("Saindo.");
			 break;
			 default:
				 System.out.println("Número errado, tente novamente.");
				 break;
		 }
		 }
		 scan.close();
	}
}
