package fag;

public class Multiplicacao extends Operacao {
	 
    public Multiplicacao() {
        super("×");
    }
 
    @Override
    public double executar(double a, double b) throws CalculadoraException {
        return a * b;
    }
}