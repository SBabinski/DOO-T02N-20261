package fag;

import java.time.LocalDate;

public class Locacao {
	
	Cliente cliente;
	Veiculo veiculo;
	LocalDate dataRetirada;
	int diasLocacao;
	boolean devolvido;
	
	public Locacao(Cliente cliente, Veiculo veiculo, LocalDate dataRetirada, int diasLocacao, boolean devolvido) {
		this.cliente = cliente;
		this.veiculo = veiculo;
		this.dataRetirada = dataRetirada;
		this.diasLocacao = diasLocacao;
		this.devolvido = devolvido;
	}
	
	public LocalDate dataDevolucao() {
		return dataRetirada.plusDays(diasLocacao);
	}
	
	public double calcularTotal() {
		return diasLocacao * veiculo.valorD;
	}
	
	public void ExibirDados() {
		System.out.println("----LOCACAO----");
		System.out.println("Cliente: "+cliente.nome);
		veiculo.ExibirInfo();
		System.out.println("Data de retirada: "+dataRetirada);
		System.out.println("Data de devolução: "+dataDevolucao());
		System.out.println("Valor total: R$ "+calcularTotal());
		System.out.println("Devolvido: " + (devolvido ? "Sim" : "Não"));
	}
	
	
}
