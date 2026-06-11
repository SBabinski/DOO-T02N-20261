package aula09;
 
public class Calculadora {
 
    public double calcular(double a, double b, String operacao) throws CalculadoraException {
        switch (operacao) {
            case "+":
                return a + b;
            case "-":
                return a - b;
            case "*":
                return a * b;
            case "/":
                if (b == 0) {
                    throw new CalculadoraException("Erro: divisão por zero não é permitida.");
                }
                return a / b;
            default:
                throw new CalculadoraException("Erro: operação desconhecida.");
        }
    }
 
    public double parseNumero(String texto) throws CalculadoraException {
        try {
            return Double.parseDouble(texto.trim());
        } catch (NumberFormatException e) {
            throw new CalculadoraException("Erro: entrada inválida. Digite apenas números.");
        }
    }
}