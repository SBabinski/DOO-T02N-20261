package aulas.dados;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import aulas.BancoDados;

public class PedidoDados {
	private int id;
	private LocalDate dataCriacao;
	private LocalDate dataPagamento;
	private LocalDate dataVencimentoReserva;
	private ClienteDados cliente;
	private VendedorDados vendedor;
	private LojaDados loja;
	private List<ItemDados> itens = new ArrayList<>();
	
	public PedidoDados(int id, LocalDate dataCriacao, LocalDate dataPagamento, LocalDate dataVencimentoReserva,ClienteDados cliente, VendedorDados vendedor, LojaDados loja, List<ItemDados> itens) {
		setId(id);
		setDataCriacao(dataCriacao);
		setDataPagamento(dataPagamento);
		setDataVencimentoReserva(dataVencimentoReserva);
		setCliente(cliente);
		setVendedor(vendedor);
		setLoja(loja);
		setItens(itens);
	}
	
	public int getId() {return id;}
	public void setId(int id) {
		if(id > 0)this.id = id;
	}
	
	public LocalDate getDataCriacao() {return dataCriacao;}
	public void setDataCriacao(LocalDate dataCriacao) {
		this.dataCriacao = dataCriacao;
	}
	
	public LocalDate getDataPagamento() {return dataPagamento;}
	public void setDataPagamento(LocalDate dataPagamento) {
		this.dataPagamento = dataPagamento;
	}
	
	public LocalDate getDataVencimentoReserva() {return dataVencimentoReserva;}
	public void setDataVencimentoReserva(LocalDate dataVencimentoReserva) {
		this.dataVencimentoReserva = dataVencimentoReserva;
	}
	
	public ClienteDados getCliente() {return cliente;}
	public void setCliente(ClienteDados cliente) {
		if(cliente != null)this.cliente = cliente;
	}
	public VendedorDados getVendedor() {return vendedor;}
	public void setVendedor(VendedorDados vendedor) {
		if(vendedor != null)this.vendedor = vendedor;
	}
	public LojaDados getLoja() {return loja;}
	public void setLoja(LojaDados loja) {
		if(loja != null)this.loja = loja;
	}
	
	public List<ItemDados> getItens() {return itens;}
	public void setItens(List<ItemDados> itens) {
		if(itens != null)this.itens = new ArrayList<>(itens);
	}
	public void adicionarItens(ItemDados item) {
		if(item != null) itens.add(item);
	}
	
	public double calcularValorTotal() {
		double valorTotal= 0.0;
		
		for(ItemDados item : itens) {
			valorTotal += item.getValor();
		}
		
		return valorTotal;
	}
	
	public void listaPedidos() {
		String dataFormatada = dataCriacao.format(BancoDados.getFormatacao());
		
		System.out.printf("Data de Criação: %s | Valor Total: %.2f \n", dataFormatada, calcularValorTotal());
	}
}
	
	
	
	
