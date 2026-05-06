package fag;

import java.time.LocalDate;
import java.util.ArrayList;

public class Pedido {
	
	int id;
	LocalDate dataCriacao;
	LocalDate dataPagamento;
	LocalDate dataVencimentoReserva;
	Cliente cliente;
	Vendedor vendedor;
	Loja loja;
	ArrayList<Item> itens = new ArrayList<>();
	
	public Pedido(int id, LocalDate dataCriacao, LocalDate dataPagamento, LocalDate dataVencimentoReserva, Cliente cliente, Vendedor vendedor, Loja loja) {
		
		this.id = id;
		this.dataCriacao = dataCriacao;
		this.dataPagamento = dataPagamento;
		this.dataVencimentoReserva = dataVencimentoReserva;
		this.cliente = cliente;
		this.vendedor = vendedor;
		this.loja = loja;
		
	}
	
	public double calcularValorTotal() {
		double total = 0;
		for (Item item : itens) {
			total += item.valor;
		}	
		return total;
	}

	public void gerarDescricaoVenda() {
		System.out.println("Id do Pedido: "+id);
		System.out.println("Data de criação do pedido: "+dataCriacao);
		System.out.println("Valor total do pedido: R$ "+calcularValorTotal());
		
	}
	
	public void adicionarItem(Item item) {
		itens.add(item);
	}
}
