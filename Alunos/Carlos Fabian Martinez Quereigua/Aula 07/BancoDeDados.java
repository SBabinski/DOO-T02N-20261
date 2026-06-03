package fag.objetos;

import java.util.ArrayList;

public class BancoDeDados {
	
	public static ArrayList<Vendedor> vendedores = new ArrayList<Vendedor>();
	public static ArrayList<Gerente> gerentes = new ArrayList<Gerente>();
	public static ArrayList<Loja> lojas = new ArrayList<Loja>();
	public static ArrayList<Item> itens = new ArrayList<Item>();
	public static ArrayList<Endereco> enderecos = new ArrayList<Endereco>();
	public static ArrayList<Cliente> clientes = new ArrayList<Cliente>();
	public static ArrayList<Pedido> pedidos = new ArrayList<Pedido>();
	
	public static void inicializar() {
		popularEnderecos();
		popularItens();
		
		popularLojas();
		popularClientes();
		popularVendedores();
		popularGerentes();
		
		popularPedidos();
	}

	private static void popularEnderecos() {
		Endereco e1 = new Endereco("PR", "Cascavel", "Floresta", "R. Da Paz", "123");
		Endereco e2 = new Endereco("PR", "Cascavel", "Universitario", "R. Software", "256");
		Endereco e3 = new Endereco("PR", "Cafelândia", "Centro", "Av. Desembargador M. Melo", "1103");
		Endereco e4 = new Endereco("PR", "Foz do Iguaçu", "Centro", "R. São João", "35");
		Endereco e5 = new Endereco("PR", "Curitiba", "centro", "R. Elizabeth Pereira", "57");
		
		enderecos.add(e1);
		enderecos.add(e2);
		enderecos.add(e3);
		enderecos.add(e4);
		enderecos.add(e5);
	}
	
	private static void popularItens() { 
		Item i1 = new Item("Rosa", "A", 20.0);
		Item i2 = new Item("Girassol", "B", 31.0);
		Item i3 = new Item("Tulipã", "A", 27.0);
		Item i4 = new Item("Orquídea", "A", 25.0);
		
		itens.add(i1);
		itens.add(i2);
		itens.add(i3);
		itens.add(i4);
	}
	
	private static void popularLojas() {
		Loja l1 = new Loja("My Plant", "My Plant", "12.123.123/0001-00", enderecos.get(0));
		Loja l2 = new Loja("My Plant", "My Plant", "12.123.123/0001-00", enderecos.get(2));
		Loja l3 = new Loja("My Plant", "My Plant", "12.123.123/0001-00", enderecos.get(3));
		
		lojas.add(l1);
		lojas.add(l2);
		lojas.add(l3);
	}
	
	private static void popularClientes(){
		Cliente c1 = new Cliente("Antonio", 53, enderecos.get(1));
		Cliente c2 = new Cliente("Rosa", 47, enderecos.get(3));
		Cliente c3 = new Cliente("Maria", 61, enderecos.get(4));
		
		clientes.add(c1);
		clientes.add(c2);
		clientes.add(c3);
		
	}
	
	private static void popularVendedores() {
		Vendedor v1 = new Vendedor("Carlos", 20, enderecos.get(1), lojas.get(0), 5000.0);
		Vendedor v2 = new Vendedor("Felipe", 35, enderecos.get(2), lojas.get(1), 5000.0);
		Vendedor v3 = new Vendedor("Marisa", 23, enderecos.get(3), lojas.get(2), 5000.0);
		
		vendedores.add(v1);
		vendedores.add(v2);
		vendedores.add(v3);
	}
	
	private static void popularGerentes() {
		Gerente g1 = new Gerente("Marcos", 57, enderecos.get(4), lojas.get(0), 10000.0);
		Gerente g2 = new Gerente("Julio", 41, enderecos.get(3), lojas.get(1), 10000.0);
		Gerente g3 = new Gerente("Nilse", 48, enderecos.get(2), lojas.get(2), 10000.0);
		
		gerentes.add(g1);
		gerentes.add(g2);
		gerentes.add(g3);		
	}
	
	private static void popularPedidos() {
		Pedido p1 = new Pedido("22/04/2026", "22/04/2026", "29/04/2026", clientes.get(0), vendedores.get(0), 
																		vendedores.get(0).getLoja(), itens);
		
		Pedido p2 = new Pedido("21/04/2026", "21/04/2026", "28/04/2026", clientes.get(1), vendedores.get(2),  
																		vendedores.get(2).getLoja(), itens);
		
		Pedido p3 = new Pedido("20/04/2026", "20/04/2026", "27/04/2026", clientes.get(2), vendedores.get(1),  
																		vendedores.get(1).getLoja(), itens);
		
		Pedido p4 = new Pedido("19/04/2026", "19/04/2026", "26/04/2026", clientes.get(0), vendedores.get(0),  
																		vendedores.get(0).getLoja(), itens);
		
		Pedido p5 = new Pedido("18/04/2026", "28/04/2026", "25/04/2026", clientes.get(2), vendedores.get(1),  
																		vendedores.get(1).getLoja(), itens);
		
		pedidos.add(p1);
		pedidos.add(p2);
		pedidos.add(p3);
		pedidos.add(p4);
		pedidos.add(p5);
	}
}
