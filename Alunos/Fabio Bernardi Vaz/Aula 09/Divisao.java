package fag;

public class Divisao extends Operacao {
	 
    public Divisao() {
        super("÷");
    }
 
    @Override
    public double executar(double a, double b) throws CalculadoraException {
        if (b == 0) {
            throw new CalculadoraException(
                CalculadoraException.DIVISAO_POR_ZERO,
                "Não é possível dividir por zero."
            );
        }
        return a / b;
    }
}
