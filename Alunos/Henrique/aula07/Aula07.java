package fag;

import java.util.Scanner;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Aula07 {
	
	private static Scanner scan = new Scanner(System.in);
	
	 	static Loja loja;
	    static Vendedor samu, pati;
	    static Cliente c1, c2, c3;
	    static Item samambaia, cacto, orquidea;

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
        
		 
		 while(opc != 6) {
			opc = menu();
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
			 ProcessaPedido pp = new ProcessaPedido();
			    pp.processar(1, LocalDate.now().plusDays(7), c1, samu, loja);
			    pp.pedido.adicionarItem(samambaia);
			    pp.pedido.adicionarItem(cacto);
			    pp.pedido.adicionarItem(orquidea);
			    pp.pedido.gerarDescricaoVenda();
			 break;
		 case 6:
			 System.out.println("Saindo.");
			 break;
			 default:
				 System.out.println("Número errado, tente novamente.");
				 break;
		 }
		 }
		 scan.close();
	}
	
	public static int menu() {
		 System.out.println("----------------------------------");
		 System.out.println("Digite o número do que deseja:");
		 System.out.println("1 - Calcular Preço Total.");
		 System.out.println("2 - Calcular Troco.");
		 System.out.println("3 - Registro de Vendas.");
		 System.out.println("4 - Buscar Data.");
		 System.out.println("5 - Criar Pedido");
		 System.out.println("6 - Sair.");
		 int opc = scan.nextInt();
		 scan.nextLine();
		 return opc;
	}
	
	public static void basiquin() {
		
		Endereco enderecoLoja = new Endereco ("PR", "Cascavel", "Quebec", 500, "Rua do arroio");
		
		loja = new Loja(
				"My Plant",
				"My Plant Indústrias Ltda",
				"12345678900001",
				enderecoLoja
				);
		
		double[] salarioSamu = {2800.00, 3000.00, 2950.00};
		double[] salarioPati = {3200.00, 3000.00, 3150.00};
		
		Endereco enderecoSamu = new Endereco ("PR", "Cascavel", "Quebec", 600, "Rua do Arroio");
		Endereco enderecoPati = new Endereco ("PR", "Cascavel", "Guaruja", 100, "Rua do Girassol");
		
		samu = new Vendedor("Samuel", 40, loja, enderecoSamu, 2800.00, salarioSamu);
		pati = new Vendedor("Patrick", 35, loja, enderecoPati, 3200.00, salarioPati);
		
		loja.vendedores.add(samu);
		loja.vendedores.add(pati);
		
		Endereco enderecoC1 = new Endereco ("PR", "Cascavel", "Country", 777, "Rua da Amizade");
		Endereco enderecoC2 = new Endereco ("PR", "Cascavel", "Country", 666, "Rua da Amizade");
		Endereco enderecoC3 = new Endereco ("PR", "Cascavel", "Country", 888, "Rua da Amizade");
		
		c1 = new Cliente("João", 30, enderecoC1);
		c2 = new Cliente("Maria", 28, enderecoC2);
		c3 = new Cliente("Eduardo", 32, enderecoC3);
		
		samambaia = new Item(1, "Samambaia", "Planta", 25.00);
		cacto = new Item(2, "Cacto", "Suculenta", 15.00);
		orquidea = new Item(3, "Orquídea", "Planta", 80.00);

		samambaia.gerarDescricao();
		cacto.gerarDescricao();
		orquidea.gerarDescricao();
		
		ProcessaPedido pp = new ProcessaPedido();
		pp.processar(1, LocalDate.now().plusDays(7), c3, pati, loja);
		
		pp.pedido.adicionarItem(samambaia);
		pp.pedido.adicionarItem(cacto);
		pp.pedido.adicionarItem(orquidea);
		
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