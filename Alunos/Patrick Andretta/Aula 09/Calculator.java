public class Calculator {
    public static double calculate(double a, double b, String op) throws CalculatorException {
        switch (op) {
            case "+": return a + b;
            case "-": return a - b;
            case "×": return a * b;
            case "÷":
                if (b == 0) throw new CalculatorException("Divisão por zero não é permitida.");
                return a / b;
            default: throw new CalculatorException("Operação inválida.");
        }
    }
}
