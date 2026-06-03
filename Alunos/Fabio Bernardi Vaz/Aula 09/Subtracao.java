package fag;

public class Subtracao extends Operacao {
	 
    public Subtracao() {
        super("-");
    }
 
    @Override
    public double executar(double a, double b) throws CalculadoraException {
        return a - b;
    }
}