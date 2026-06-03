package aulas;

import java.util.ArrayList;
import java.util.List;

import aulas.dados.*;

public class BancoDados {
	private static List<RegistroDados> registrosVenda = new ArrayList<>();
	private static List<VendedorDados> vendedores = new ArrayList<>();
	private static List<LojaDados> lojas = new ArrayList<>();
	private static List<ClienteDados> clientes = new ArrayList<>();
	
	public static List<RegistroDados> getRegistros(){return registrosVenda;}
	public static void adicionarRegistros(RegistroDados registro) {
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
