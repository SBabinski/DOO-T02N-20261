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
                throw new CalculadoraException("Erro: operação inválida.");
        }
    }

    public double parsearNumero(String texto) throws CalculadoraException {
        try {
            return Double.parseDouble(texto);
        } catch (NumberFormatException e) {
            throw new CalculadoraException("Erro: \"" + texto + "\" não é um número válido.");
        }
    }
}