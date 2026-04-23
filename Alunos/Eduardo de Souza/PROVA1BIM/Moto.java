package prova;

public class Moto extends Veiculo{
	private double cilindrada = 0.0;
	
	 public Moto(String placa, double valorDiaria, double cilindrada) {
		setPlaca(placa);
		setValorDiaria(valorDiaria);
		setCilindrada(cilindrada);
	}
	 
	public double getCilindrada() {return cilindrada;}
	public void setCilindrada(double cilindrada) {
		if(cilindrada > 0.0)this.cilindrada = cilindrada;
	}

	public void listaMoto() {
		System.out.printf("Moto | Placa: %s / Valor Diária: %.2f / Cilindrada: %.2f \n", getPlaca(), getValorDiaria(), getCilindrada());
	}
}
