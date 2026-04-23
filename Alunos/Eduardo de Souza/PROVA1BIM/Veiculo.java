package prova;

public class Veiculo {
	private String placa;
	private double valorDiaria = 0.0;

	public String getPlaca() {return placa;}
	public void setPlaca(String placa) {
		if(placa != null && !placa.isBlank())this.placa = placa;
	}
	
	public double getValorDiaria() {return valorDiaria;}
	public void setValorDiaria(double valorDiaria) {
		if(valorDiaria > 0.0)this.valorDiaria = valorDiaria;
	}
}
