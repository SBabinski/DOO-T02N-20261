package fag;

public class CalculadoraService {
	 
    public double calcular(String entradaA, String entradaB, Operacao operacao) throws CalculadoraException {
 
        if (entradaA == null || entradaA.trim().isEmpty()) {
            throw new CalculadoraException(CalculadoraException.CAMPO_VAZIO, "O primeiro campo está vazio.");
        }
        if (entradaB == null || entradaB.trim().isEmpty()) {
            throw new CalculadoraException(CalculadoraException.CAMPO_VAZIO, "O segundo campo está vazio.");
        }
 
        double a;
        double b;
 
        try {
            a = Double.parseDouble(entradaA.replace(",", "."));
        } catch (NumberFormatException e) {
            throw new CalculadoraException(CalculadoraException.ENTRADA_INVALIDA,
                    "\"" + entradaA + "\" não é um número válido.");
        }
 
        try {
            b = Double.parseDouble(entradaB.replace(",", "."));
        } catch (NumberFormatException e) {
            throw new CalculadoraException(CalculadoraException.ENTRADA_INVALIDA,
                    "\"" + entradaB + "\" não é um número válido.");
        }
 
        return operacao.executar(a, b);
    }
 
    public String formatarResultado(double valor) {
        if (valor == (long) valor) {
            return String.valueOf((long) valor);
        }
        return String.format("%.2f", valor);
    }
}