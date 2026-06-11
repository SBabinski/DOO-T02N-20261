package fag;

public class Soma extends Operacao {
	 
    public Soma() {
        super("+");
    }
 
    @Override
    public double executar(double a, double b) throws CalculadoraException {
        return a + b;
    }
}