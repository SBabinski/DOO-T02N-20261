package objetos;

public class Carro extends Veiculos {
	private boolean arcond;

	public Carro(int placa, int valdia,boolean arcond) {
		setPlaca(placa);
		setValdia(valdia);
		setArcond(arcond);
	}

	public boolean isArcond() {
		return arcond;
	}

	public void setArcond(boolean arcond) {
		this.arcond = arcond;
	}
	
}
