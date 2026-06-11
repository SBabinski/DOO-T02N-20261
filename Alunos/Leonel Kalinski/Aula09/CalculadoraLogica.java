package calculadora;

public class CalculadoraLogica {
 
    public double calcular(double a, double b, String op) throws CalculadoraException {
        switch (op) {
            case "+": return a + b;
            case "-": return a - b;
            case "*": return a * b;
            case "/":
                if (b == 0) throw new CalculadoraException("Divisão por zero.");
                return a / b;
            default:
                throw new CalculadoraException("Operação inválida: " + op);
        }
    }
   
    public String formatarNumero(double numero) {
        if (numero == Math.floor(numero) && !Double.isInfinite(numero)) {
            return String.format("%.0f", numero);
        }
        String formatado = String.format("%.10f", numero);
        formatado = formatado.replaceAll("0+$", "");
        formatado = formatado.replaceAll("\\.$", "");
        return formatado;
    }
}
