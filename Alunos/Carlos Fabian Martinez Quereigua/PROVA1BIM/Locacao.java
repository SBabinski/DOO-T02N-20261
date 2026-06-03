package fag.objetos;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Locacao {
	private static SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy"); 
	private Cliente cliente;
	private Veiculo veiculo;
	private Date dataRetirada;
	private Date dataDevolucao;
	private boolean devolucaoRealizada = false;
	
	public Locacao(){
		veiculo.setOcupado(true);
	}
	
	public Locacao(Cliente cliente, Veiculo veiculo, String dataRetirada, String dataDevolucao) {
		setCliente(cliente);
		setVeiculo(veiculo);
		setDataRetirada(dataRetirada);
		setDataDevolucao(dataDevolucao);
		setDevolucaoRealizada(false);
		veiculo.setOcupado(true);
	}
	
	public Cliente getCliente() {
		return cliente;
	}
	
	public void setCliente(Cliente cliente) {
		if(cliente != null) {
			this.cliente = cliente;
		}
	}
	
	public Veiculo getVeiculo() {
		return veiculo;
	}
	
	public void setVeiculo(Veiculo veiculo) {
		if(veiculo != null) {
			this.veiculo = veiculo;
		}
	}
	
	public Date getDataRetirada() {
		return dataRetirada;
	}
	
	public void setDataRetirada(String dataRetirada) {
		try{
			this.dataRetirada = formato.parse(dataRetirada);
		}
		catch(Exception e) {
			System.out.println("A data inserida é invalida!");
		}
	}
	
	public Date getDataDevolucao() {
		return dataDevolucao;
	}
	
	public void setDataDevolucao(String dataDevolucao) {
		try{
			this.dataDevolucao = formato.parse(dataDevolucao);
		}
		catch(Exception e) {
			System.out.println("A data inserida é invalida!");
		}
	}
	
	public boolean isDevolucaoRealizada() {
		return devolucaoRealizada;
	}
	
	public void setDevolucaoRealizada(boolean devolucaoRealizada) {
		this.devolucaoRealizada = devolucaoRealizada;
	}
	
	//Métodos
	
	public void mostrarDados() {
		System.out.println("Cliente: " + cliente.getNome());
		System.out.println("Veiculo: " + veiculo.getPlaca());
		System.out.println("Data de Retidada: " + formato.format(dataRetirada));
		System.out.println("Data de Devolucao: " + formato.format(dataDevolucao));
		System.out.printf("Devolução Realizada: %s\n",devolucaoRealizada? "Sim" : "Não");
		System.out.printf("Valor total a ser pago: %.2f" + QuantidadeDias()*veiculo.getValorDiaria());
	}
	
	public int QuantidadeDias() {
		long diff = dataDevolucao.getTime() - dataRetirada.getTime();
	    return (int) diff / (1000 * 60 * 60 * 24);
	}
}
