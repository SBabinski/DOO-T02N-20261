public class CalculadoraLogica {
    public double executar(double n1, double n2, String op) throws CalculadoraException {
        switch (op) {
            case "+":
                return n1 + n2;
            case "-":
                return n1 - n2;
            case "×":
                return n1 * n2;
            case "÷":
                if (n2 == 0)
                    throw new CalculadoraException("Impossível dividir por zero!!!!");
                return n1 / n2;
            default:
                return n2;
        }
    }
}