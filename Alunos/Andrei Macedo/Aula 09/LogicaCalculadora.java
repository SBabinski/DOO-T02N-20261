public class LogicaCalculadora { 

    public static double calcular (double num1, double num2, String operacao) {
        switch (operacao) {
            case "+": return num1 + num2;
            case "-": return num1 - num2;
            case "*": return num1 * num2;
            case "/":
                if (num2 == 0) {
                    throw new ErroDivisaoPorZeroException();
                }
                return num1 / num2;
            default:
                return 0;
        }
    }
}
