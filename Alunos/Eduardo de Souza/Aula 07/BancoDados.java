package aulas;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import aulas.dados.*;

public class BancoDados {
	private static List<RegistroDados> registrosVenda = new ArrayList<>();
	private static List<VendedorDados> vendedores = new ArrayList<>();
	private static List<LojaDados> lojas = new ArrayList<>();
	private static List<ClienteDados> clientes = new ArrayList<>();
	private static List<GerenteDados> gerentes = new ArrayList<>();
	private static List<ItemDados> itens = new ArrayList<>();
	private static List<PedidoDados> pedidos = new ArrayList<>();
	private static final DateTimeFormatter formatacao = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	
	public static List<GerenteDados> getGerentes() {return gerentes;}
	public static void adicionarGerente(GerenteDados gerente) {
		if(gerente != null) {
			gerentes.add(gerente);
		}
	}
	
	public static List<RegistroDados> getRegistros(){return registrosVenda;}
	public static void adicionarRegistro(RegistroDados registro) {
		if(registro != null) {
			registrosVenda.add(registro);
		}
	}

	public static List<VendedorDados> getVendedores(){return vendedores;}
	public static void adicionarVendedor(VendedorDados vendedor) {
		if(vendedor != null) {
			vendedores.add(vendedor);
		}
	}
	
	public static List<LojaDados> getLojas(){return lojas;}
	public static void adicionarLoja(LojaDados loja) {
		if(loja != null) {
			lojas.add(loja);
		}
	}
	
	public static List<ClienteDados> getClientes(){return clientes;}
	public static void adicionarCliente(ClienteDados cliente) {
		if(cliente != null) {
			clientes.add(cliente);
		}
	}
	
	public static List<PedidoDados> getPedidos() {return pedidos;}
	public static void adicionarPedido(PedidoDados pedido) {
		if(pedido != null) {
			pedidos.add(pedido);
		}
	}
	
	public static List<ItemDados> getItens() {return itens;}
	public static void adicionarItem(ItemDados item) {
		if(item != null) {
			itens.add(item);
		}
	}
	
	public static DateTimeFormatter getFormatacao() {return formatacao;}
	
	public static void mensagemVoltar() {
		System.out.println("Voltando.. \n");
	}
	
	public static void mensagemListaVazia(String tipo) {
		System.out.printf("A lista do %s está vazia \n", tipo);
	}
	
	public static void mensagemOpcInvalida() {
		System.out.println("Opção Inválida, Selecione uma Opção Correta! \n");
	}
	
	public static void mensagemIDInvalido(String id) {
		System.out.printf("%s inválido! \n", id);
	}

}
