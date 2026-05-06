package fag.objetos;

public class Moto extends Veiculo {
	private int cilindrada;
	
	public Moto() {
		super();
	}
	
	public Moto(String placa, Double valorDiaria, int cilindrada) {
		super(placa, valorDiaria);
		
		setCilindrada(cilindrada);
	}

	public int getCilindrada() {
		return cilindrada;
	}

	public void setCilindrada(int cilindrada) {
		if(cilindrada >= 0) {
			this.cilindrada = cilindrada;
		}
	}
	public void mostrarDados(){
		System.out.printf("Placa: %s | Valor Diaria: %.2f | Cilindrada: %dCC\n", placa, valorDiaria, 
																						cilindrada);
	}
}
