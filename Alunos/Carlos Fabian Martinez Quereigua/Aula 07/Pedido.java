package fag.objetos;

import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;
public class Pedido {
	private static int idCount = 1;
	private static SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
	private int id;
	private Date dataCriacao;
	private Date dataPagamento;
	private Date dataVencimentoReserva;
	private Pessoa cliente;
	private Funcionario vendedor;
	private Loja loja;
	private ArrayList<Item> itens;
	
	public Pedido() {
		
	}

	public Pedido(String dataCriacao, String dataPagamento,String dataVencimentoReserva, Pessoa cliente,
			Funcionario vendedor, Loja loja, ArrayList<Item> itens) {
		this.id = idCount++;
		
		try {
			setDataCriacao(formato.parse(dataCriacao));
		}
		catch(Exception e){
			System.out.println("Data invalida");
		}
		
		try {
			setDataPagamento(formato.parse(dataPagamento));
		}
		catch(Exception e){
			System.out.println("Data invalida");
		}
		try {
			setDataVencimentoReserva(formato.parse(dataVencimentoReserva));
		}
		catch(Exception e){
			System.out.println("Data invalida");
		}
		
		setCliente(cliente);
		setVendedor(vendedor);
		setLoja(loja);
		setItens(itens);
	}

	public static int getIdCount() {
		return idCount;
	}

	public int getId() {
		return id;
	}

	public Date getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(Date dataCriacao) {
		if(dataCriacao != null) {
			this.dataCriacao = dataCriacao;
		}
	}

	public Date getDataPagamento() {
		return dataPagamento;
	}

	public void setDataPagamento(Date dataPagamento) {
		if(dataPagamento != null) {
			this.dataPagamento = dataPagamento;
		}
	}

	public Date getDataVencimentoReserva() {
		return dataVencimentoReserva;
	}

	public void setDataVencimentoReserva(Date dataVencimentoReserva) {
		if(dataVencimentoReserva != null) {
			this.dataVencimentoReserva = dataVencimentoReserva;
		}
	}

	public Pessoa getCliente() {
		return cliente;
	}

	public void setCliente(Pessoa cliente) {
		if(cliente != null) {
			this.cliente = cliente;
		}
	}

	public Funcionario getVendedor() {
		return vendedor;
	}

	public void setVendedor(Funcionario vendedor) {
		if(vendedor != null) {
			this.vendedor = vendedor;
		}
	}

	public Loja getLoja() {
		return loja;
	}

	public void setLoja(Loja loja) {
		if(loja != null) {
			this.loja = loja;
		}
	}

	public ArrayList<Item> getItens() {
		return itens;
	}

	public void setItens(ArrayList<Item> itens) {
		if(itens != null && !itens.isEmpty()) {
			this.itens = itens;
		}
	}
	
	//Métodos
	public double calculaValorTotal() {
		double soma = 0;
		
		for(Item item : itens) {
			soma += item.getValor();
		}
		
		return soma;
	}
	
	public void gerarDescricaoVenda() {
		System.out.println("Data de Criação: " + formato.format(dataCriacao));
		System.out.println("Valor total do pedido: " + calculaValorTotal());
	}
}
