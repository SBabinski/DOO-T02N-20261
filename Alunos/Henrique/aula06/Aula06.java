package fag;

import java.util.Scanner;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Aula06 {
	
	private static Scanner scan = new Scanner(System.in);

	public static void main(String[] args) {
		
		basiquin();
		
		 
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
	
	public static void basiquin() {
		Loja loja = new Loja(
				"My Plant",
				"My Plant Indústrias Ltda",
				"12345678900001",
				"Cascavel",
				"Quebec",
				"Rua do Arroio 500"
				);
		
		double[] salarioSamu = {2800.00, 3000.00, 2950.00};
		double[] salarioPati = {3200.00, 3000.00, 3150.00};
		
		Vendedor samu = new Vendedor("Samuel", 40, loja, "Cascavel", "Quebec", "Rua do Arroio 600", 2800.00, salarioSamu);
		Vendedor pati = new Vendedor("Patrick", 35, loja, "Cascavel", "Guaruja", "Rua do Girassol 100", 3200.00, salarioPati);
		
		loja.vendedores.add(samu);
		loja.vendedores.add(pati);
		
		Cliente c1 = new Cliente("João", 30, "Cascavel", "Country", "Rua da amizade 777");
		Cliente c2 = new Cliente("Maria", 28, "Cascavel", "Country", "Rua da amizade 666");
		Cliente c3 = new Cliente("Eduardo", 32, "Cascavel", "Country", "Rua da amizade 888");
		
		loja.clientes.add(c1);
		loja.clientes.add(c2);
		loja.clientes.add(c3);
		
		loja.apresentarse();
		loja.contarClientes();
		loja.contarVendedores();
		
		samu.apresentarse();
		samu.calcularMedia();
		samu.calcularBonus();
		
		pati.apresentarse();
		pati.calcularMedia();
		pati.calcularBonus();
		
		c1.apresentarse();
		c2.apresentarse();
		c3.apresentarse();
		
	}
	
}