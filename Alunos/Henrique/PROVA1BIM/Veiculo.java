package fag;

public class Veiculo {
	String placa;
	double valorD;
	
	public Veiculo(String placa, double valorD) {
		if (placa != null && !placa.isBlank()) {
			this.placa = placa;
		}else {
			System.out.println("Placa Inválida.");
		}
		if (valorD > 0){
			this.valorD = valorD;
		}else {
			System.out.println("Valor da diária inválida.");
		}
		
	}
	public void ExibirInfo() {
		System.out.println("Placa: "+placa);
		System.out.println("Valor da Diária: "+valorD);
		
	}
}
