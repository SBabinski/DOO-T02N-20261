package fag.objetos;

public class Veiculo {
	protected String placa;
	protected Double valorDiaria;
	protected boolean ocupado = false;
	
	public Veiculo() {
		
	}
	
	public Veiculo(String placa, Double valorDiaria) {
		
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		if(placa != null && !placa.isBlank()) {
			this.placa = placa;
		}
		
	}

	public Double getValorDiaria() {
		return valorDiaria;
	}

	public void setValorDiaria(Double valorDiaria) {
		if(valorDiaria >= 0) {
			this.valorDiaria = valorDiaria;
		}
	}
	
	public void setOcupado(boolean ocupado) {
		this.ocupado = ocupado;
	}
	
}
