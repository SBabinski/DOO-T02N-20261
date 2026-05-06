package fag;

public class Moto extends Veiculo{
	
	int cilindrada;
	
	public Moto(String placa, double valorD, int cilindrada) {
		super(placa, valorD);
		if(cilindrada > 0) {
			this.cilindrada = cilindrada;
		}
		
	}
	
	public void ExibirInfo() {
		System.out.println("----MOTO----");
		super.ExibirInfo();
		System.out.println("Cilindrada: "+cilindrada);
		
	}
}
