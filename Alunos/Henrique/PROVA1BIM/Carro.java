package fag;

public class Carro extends Veiculo{
	
	boolean ar;
	
	public Carro(String placa, double valorD, boolean ar) {
		super(placa, valorD);
		this.ar = ar;
		
	}
	
	
	public void ExibirInfo() {
		System.out.println("----CARRO----");
		super.ExibirInfo();
		System.out.println("Tem ar-condicionado: " + (ar ? "Sim" : "Não"));
	}
}
