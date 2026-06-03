package aulas.objetos;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Registro {
	private int quantidade;
	private double valorVenda;
	private boolean descontoAplicado;
	private LocalDate dataRegistro;
	public DateTimeFormatter formatacao;

	public Registro(int quantidade, double valorVenda, boolean descontoAplicado, LocalDate dataRegistro) {
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
		String desconto;
		if(descontoAplicado == false) {
			desconto = "Não";
		} else {
			desconto = "Sim";
		}
		System.out.printf("Venda: %.2f. | Quantidade: %d | Desconto: %s | Data: %s \n", getValorVenda(), getQuantidade(), desconto, getDataRegistro());
	}
	
	public void listarRegistroFiltrado() {
		String desconto;
		if(descontoAplicado == false) {
			desconto = "Não";
		} else {
			desconto = "Sim";
		}
		System.out.printf("Venda: %.2f. | Quantidade: %d | Desconto: %s | Data: %s \n", getValorVenda(), getQuantidade(), desconto, getDataRegistro());
	}
}
