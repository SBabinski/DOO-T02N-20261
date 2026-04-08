package aulas.objetos;

public class Registro {
	private int quantidade;
	private double valorVenda;
	private boolean descontoAplicado;

	public Registro(int quantidade, double valorVenda, boolean descontoAplicado) {
		setQuantidade(quantidade);
		setValorVenda(valorVenda);
		setDescontoAplicado(descontoAplicado);
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
	
	public void listarCarrinho() {
		String desconto;
		if(descontoAplicado == false) {
			desconto = "Não";
		} else {
			desconto = "Sim";
		}
		System.out.printf("Venda: %.2f. | Quantidade: %d | Desconto: %s \n", getValorVenda(), getQuantidade(), desconto);
	}
}
