package fag.objetos;

public class Calculadora {

	public static double retornaTotal(int quantidade, double valorUnid) {
		
		return quantidade*valorUnid;
			
	}

	public static double retornaPrecoFinal(int quantidade, double valorUnid, double desconto) {
		double total = quantidade * valorUnid;
		return total - (total * desconto);
	}
	
}
