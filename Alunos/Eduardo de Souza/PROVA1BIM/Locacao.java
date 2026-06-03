package prova;

import java.time.LocalDate;

public class Locacao {
	private Cliente cliente;
	private Moto moto;
	private Carro carro;
	private LocalDate dataRetirada;
	private LocalDate dataDevolucao;
	private String situacao;
	
	public Locacao(Cliente cliente, Moto moto, Carro carro, LocalDate dataRetirada, LocalDate dataDevolucao,String situacao) {
		setCliente(cliente);
		setMoto(moto);
		setCarro(carro);
		setDataRetirada(dataRetirada);
		setDataDevolucao(dataDevolucao);
		setSituacao(situacao);
	}
	
	public Cliente getCliente() {return cliente;}
	public void setCliente(Cliente cliente) {
		if(cliente != null)this.cliente = cliente;
	}
	
	public Moto getMoto() {return moto;}
	public void setMoto(Moto moto) {
		if(moto != null)this.moto = moto;
	}
	public Carro getCarro() {return carro;}
	public void setCarro(Carro carro) {
		if(carro != null)this.carro = carro;
	}
	public LocalDate getDataRetirada() {return dataRetirada;}
	public void setDataRetirada(LocalDate dataRetirada) {
		this.dataRetirada = dataRetirada;
	}
	public LocalDate getDataDevolucao() {return dataDevolucao;}
	public void setDataDevolucao(LocalDate dataDevolucao) {
		this.dataDevolucao = dataDevolucao;
	}
	public String getSituacao() {return situacao;}
	public void setSituacao(String situacao) {
		if(situacao != null && !situacao.isBlank())this.situacao = situacao;
	}
	
	public void listaLocacao() {
	    String veiculo;
	    String placa;

	    if (getMoto() != null) {
	        veiculo = "Moto";
	        placa = getMoto().getPlaca();
	    } else if (getCarro() != null) {
	        veiculo = "Carro";
	        placa = getCarro().getPlaca();
	    } else {
	        veiculo = "Nenhum veículo";
	        placa = "N/A";
	    }

	    System.out.printf(
	        "Cliente: %s | Veículo: %s | Placa: %s | Retirada: %s | Devolução: %s | Situação: %s \n", getCliente().getNome(), veiculo, placa, getDataRetirada(), getDataDevolucao(), getSituacao()
	    );
	}
}
