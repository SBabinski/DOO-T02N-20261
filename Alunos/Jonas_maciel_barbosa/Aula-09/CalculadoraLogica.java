public class CalculadoraLogica {

  
    public double validarEntrada(String texto, String nomeCampo) throws CalculadoraException {

  
        if (texto == null || texto.trim().equals("")) {
            throw new CalculadoraException(
                "O campo " + nomeCampo + " esta vazio.",
                CalculadoraException.CAMPO_VAZIO
            );
        }

        try {
            double valor = Double.parseDouble(texto.trim());
            return valor;
        } catch (NumberFormatException e) {
            throw new CalculadoraException(
                "O valor '" + texto + "' nao e um numero valido.",
                CalculadoraException.ENTRADA_INVALIDA
            );
        }
    }

  
    public double somar(double a, double b) {
        return a + b;
    }

  
    public double subtrair(double a, double b) {
        return a - b;
    }

    
    public double multiplicar(double a, double b) {
        return a * b;
    }

    public double dividir(double a, double b) throws CalculadoraException {
        if (b == 0) {
            throw new CalculadoraException(
                "Tentativa de dividir " + a + " por zero.",
                CalculadoraException.DIVISAO_POR_ZERO
            );
        }
        return a / b;
    }
}
