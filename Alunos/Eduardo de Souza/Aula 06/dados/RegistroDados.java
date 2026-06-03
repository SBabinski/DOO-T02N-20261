package aulas.dados;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class RegistroDados {
	private int quantidade;
	private double valorVenda;
	private boolean descontoAplicado;
	private LocalDate dataRegistro;
	private static final DateTimeFormatter formatacao = DateTimeFormatter.ofPattern("dd/MM/yyyy");

	public RegistroDados(int quantidade, double valorVenda, boolean descontoAplicado, LocalDate dataRegistro) {
		setQuantidade(quantidade);
		setValorVenda(valorVenda);
		setDescontoAplicado(descontoAplicado);
		setDataRegistro(dataRegistro);
	}
	
	public LocalDate getDataRegistro() {return dataRegistro;}
	public void setDataRegistro(LocalDate dataRegistro) {
		this.dataRegistro = dataRegistro;
	}
	
	public int getQuantidade() {return quantidade;}
	public void setQuantidade(int quantidade) {
		if(quantidade > 0)this.quantidade = quantidade;
	}
	public double getValorVenda() {return valorVenda;}
	public void setValorVenda(double valorVenda) {
		if(valorVenda >= 0)this.valorVenda = valorVenda;
	}

	public boolean isDescontoAplicado() {return descontoAplicado;}
	public void setDescontoAplicado(boolean descontoAplicado) {
		this.descontoAplicado = descontoAplicado;
	}
	
	public void listarRegistro() {
		String desconto = descontoAplicado ? "Sim" : "Não";
		String dataFormatada = dataRegistro.format(formatacao);
		System.out.printf("Venda: %.2f. | Quantidade: %d | Desconto: %s | Data: %s \n", getValorVenda(), getQuantidade(), desconto, dataFormatada);
	}
	
	public void listarRegistroFiltrado() {
		String desconto = descontoAplicado ? "Sim" : "Não";
		String dataFormatada = dataRegistro.format(formatacao);
		System.out.printf("Venda: %.2f. | Quantidade: %d | Desconto: %s | Data: %s \n", getValorVenda(), getQuantidade(), desconto, dataFormatada);
	}
}
