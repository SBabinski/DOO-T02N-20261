package objetos;

public class Moto extends Veiculos {
	private int cilindrada;
	
	public Moto(int placa, int valdia, int cilindrada) {
		setPlaca(placa);
		setValdia(valdia);
		setCilindrada(cilindrada);
	}

	public int getCilindrada() {
		return cilindrada;
	}

	public void setCilindrada(int cilindrada) {
		this.cilindrada = cilindrada;
	}
	
}
