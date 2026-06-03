package fag.objetos;

public class Carro extends Veiculo{
	
	private boolean possuiArCondicionado;
	
	public Carro() {
		super();
	}
	
	public Carro(String placa, Double valorDiaria, boolean possuiArCondicionado) {
		super(placa, valorDiaria);
		setPossuiArCondicionado(possuiArCondicionado);
	}

	public boolean isPossuiArCondicionado() {
		return possuiArCondicionado;
	}

	public void setPossuiArCondicionado(boolean possuiArCondicionado) {
			this.possuiArCondicionado = possuiArCondicionado;
	}
	
	public void mostrarDados(){
		System.out.printf("Placa: %s | Valor Diaria: %.2f | Possui Ar-Condicionado: %s\n	", placa, valorDiaria,
																		possuiArCondicionado? "Sim" : "Não");
	}

}
