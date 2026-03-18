import java.util.Scanner;

public class calculadora {

	public static void main(String[] args) {
		
		 Scanner scan = new Scanner(System.in);
		
		 float total = 0;
		 
		int opc =0;
		 
		 while(opc != 3) {
			 System.out.println("Digite o número do que deseja:");
			 System.out.println("1 - Calcular Preço Total.");
			 System.out.println("2 - Calcular Troco");
			 System.out.println("3 - Sair");
			 opc = scan.nextInt();
			 scan.nextLine();
			 switch(opc) {
		 case 1:
			 System.out.println("Digite a quantidade de plantas");
			 int qtd = scan.nextInt();
			 scan.nextLine();
			 
			 System.out.println("Digite o valor da planta");
			 float valor = scan.nextFloat();
			 scan.nextLine();
			 
			total = (qtd*valor);
			 
			 System.out.println("O valor total é: " + total);
			 break;
		 case 2:
			 System.out.println("Digite o valor pago pelo cliente.");
			 float pago = scan.nextFloat();
			 scan.nextLine();
			 
			 float troco = pago - total;
			 System.out.println("O troco é: "+troco);
			 break;
		 case 3:
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