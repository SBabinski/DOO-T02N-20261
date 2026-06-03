import java.util.Scanner;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Aula6 {
	
	private static Scanner scan = new Scanner(System.in);

	public static void main(String[] args) {
		
		projetoSamuca();
		
		 
		 ArrayList<Venda> vendas = new ArrayList<>();
		
		 double total = 0;
		 int qtd=0;
		 double valor=0;
		 double desconto=0;
		 int opc =0;
		
		 LocalDate hoje = LocalDate.now();
		 System.out.println("Data de hoje: " + hoje);
         DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
         String dataformat = hoje.format(formatter);
         System.out.println(dataformat);
        
		 
		 while(opc != 5) {
			menu(opc);
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
			 System.out.println("Digite a data: (DD/MM/YYYY)");
			 String datavenda = scan.nextLine();
			 LocalDate data = LocalDate.parse(datavenda, formatter);
			 Venda v = new Venda (total, valor, qtd, desconto, data);
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
			 System.out.println("Digite a data: (DD/MM/YYYY)");
			 String databuscar = scan.nextLine();
			 LocalDate data1 = LocalDate.parse(databuscar, formatter);
			 for (Venda venda : vendas) {
				 if (venda.data.equals(data1)) {
					 System.out.println("----------------------------------");
					 System.out.println("Venda Encontrada:");
			         System.out.println("Total da Venda: "+venda.total);
			         System.out.println("Quantidade de Planta: "+venda.qtd);
			         System.out.println("Valor de cada Planta: "+venda.valor);
			         System.out.println("Desconto aplicado: "+venda.desconto);
					 System.out.println("Total: "+ venda.total);
					 System.out.println("Data: "+ venda.data.format(formatter));
				 }
			 }
			 break;
		 case 5:
			 System.out.println("Saindo.");
			 break;
			 default:
				 System.out.println("Número errado, tente novamente.");
				 break;
		 }
		 }
		 scan.close();
	}
	
	public static void menu(int opc) {
		 System.out.println("----------------------------------");
		 System.out.println("Digite o número do que deseja:");
		 System.out.println("1 - Calcular Preço Total.");
		 System.out.println("2 - Calcular Troco.");
		 System.out.println("3 - Registro de Vendas.");
		 System.out.println("4 - Buscar Data.");
		 System.out.println("5 - Sair.");
		 opc = scan.nextInt();
		 scan.nextLine();
	}
	
	public static void projetoSamuca() {
		Loja loja = new Loja(
				"Verde Vivo",
				"Verde Vivo Comércio de Plantas Ltda",
				"98765432000188",
				"Curitiba",
				"Batel",
				"Avenida do Iguaçu 300"
				);
		
		double[] salarioAna = {3100.00, 3250.00, 3050.00};
		double[] salarioCarlos = {2900.00, 3100.00, 2800.00};
		
		Vendedor ana = new Vendedor("Ana", 27, loja, "Curitiba", "Batel", "Avenida do Iguaçu 310", 3100.00, salarioAna);
		Vendedor carlos = new Vendedor("Carlos", 33, loja, "Curitiba", "Agua Verde", "Rua Itupava 220", 2900.00, salarioCarlos);
		
		loja.vendedores.add(ana);
		loja.vendedores.add(carlos);
		
		Cliente c1 = new Cliente("Lucas", 25, "Curitiba", "Batel", "Rua Comendador Araújo 150");
		Cliente c2 = new Cliente("Fernanda", 31, "Curitiba", "Batel", "Rua Comendador Araújo 160");
		Cliente c3 = new Cliente("Ricardo", 40, "Curitiba", "Batel", "Rua Comendador Araújo 170");
		
		loja.clientes.add(c1);
		loja.clientes.add(c2);
		loja.clientes.add(c3);
		
		loja.apresentarse();
		loja.contarClientes();
		loja.contarVendedores();
		
		ana.apresentarse();
		ana.calcularMedia();
		ana.calcularBonus();
		
		carlos.apresentarse();
		carlos.calcularMedia();
		carlos.calcularBonus();
		
		c1.apresentarse();
		c2.apresentarse();
		c3.apresentarse();
		
	}
	
}