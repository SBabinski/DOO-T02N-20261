public class Calculadora {
        public double calcular(double num1, double num2, String operacao) throws CalculadoraException {
        switch (operacao) {
            case "+":
                return num1 + num2;
            case "-":
                return num1 - num2;
            case "*":
                return num1 * num2;
            case "/":
                if (num2 == 0) {
                    throw new CalculadoraException("Divisão por zero não é permitida.");
                }
                return num1 / num2;
            default:
                throw new CalculadoraException("Operação inválida.");
        }
    }
}
