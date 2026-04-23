package prova;

public class Carro extends Veiculo {
	private boolean arCondicionado;
	
	public Carro(String placa, double valorDiaria, boolean arCondicionado) {
		setPlaca(placa);
		setValorDiaria(valorDiaria);
		setArCondicionado(arCondicionado);
	}
	 
	public boolean isArCondicionado() {return arCondicionado;}
	public void setArCondicionado(boolean arCondicionado) {
		this.arCondicionado = arCondicionado;
	}
	
	public void listaCarro() {
		String auxAr = (isArCondicionado() == true)? "Sim" : "Não";
		System.out.printf("Carro | Placa: %s / Valor Diária: %.2f / Ar-Condicionado: %s \n", getPlaca(), getValorDiaria(), auxAr );
	}

}
