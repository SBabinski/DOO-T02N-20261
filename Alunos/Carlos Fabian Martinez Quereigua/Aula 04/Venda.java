package fag.objetos;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Venda {
	
	private int quantidadePlantas;
	private double valorVenda;
	private double descontosAplicados;
	private double descontoReais;
	private LocalDate data;
	
	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public Venda() {
		
	}
	
	public Venda(int quantidadePlantas, double valorVenda, double descontosAplcados,
														  double descontoReais,
														  LocalDate data) {
		setQuantidadePlantas(quantidadePlantas);
		setValorVenda(valorVenda);
		setDescontosAplicados(descontosAplcados);
		setDescontoReais(descontoReais);
		setData(data);
	}

	public double getDescontoReais() {
		return descontoReais;
	}

	public void setDescontoReais(double descontoReais) {
		if(descontoReais >= 0) {
			this.descontoReais = descontoReais;
		}
	}

	public int getQuantidadePlantas() {
		return quantidadePlantas;
	}

	public void setQuantidadePlantas(int quantidadePlantas) {
		if(quantidadePlantas >= 1) {
			this.quantidadePlantas = quantidadePlantas;
		}
	}

	public double getValorVenda() {
		return valorVenda;
	}

	public void setValorVenda(double valorVenda) {
		if(valorVenda >= 1) {
			this.valorVenda = valorVenda;
		}
	}

	public double getDescontosAplicados() {
		return descontosAplicados;
	}

	public void setDescontosAplicados(double descontosAplicados) {
		if(descontosAplicados >= 0) {
			this.descontosAplicados = descontosAplicados;
		}
	}
	public void mostraDados() {
		System.out.printf("Quantidade de Plantas: %d\n", quantidadePlantas);
		System.out.printf("Valor da Venda: R$%.2f\n", valorVenda);
		System.out.println("Descontos aplicados " + descontosAplicados*100 + "%");
		System.out.printf("Desconto em R$ %.2f\n", descontoReais);
		System.out.println("Data da venda: " + 
							data.format(DateTimeFormatter.ofPattern("dd/MM/YYYY")));
		System.out.println("----------------------------------");
	}
}
