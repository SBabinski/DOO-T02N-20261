public class CalculadoraLogica {

    public static double calcular(double a, double b, String operacao) throws CalculadoraException {
        double resultado;

        switch (operacao) {
            case "+": resultado = a + b; break;
            case "-": resultado = a - b; break;
            case "x": resultado = a * b; break;
            case "/":
                if (b == 0) throw new CalculadoraException(CalculadoraException.TipoErro.DIVISAO_POR_ZERO);
                resultado = a / b;
                break;
            default:
                throw new CalculadoraException(CalculadoraException.TipoErro.OPERACAO_INVALIDA);
        }

        if (Double.isInfinite(resultado) || Double.isNaN(resultado)) {
            throw new CalculadoraException(CalculadoraException.TipoErro.DIVISAO_POR_ZERO);
        }

        return resultado;
    }

    public static double parseNumero(String texto) throws CalculadoraException {
        if (texto == null || texto.trim().isEmpty() || texto.equals("-")) {
            throw new CalculadoraException(CalculadoraException.TipoErro.ENTRADA_INVALIDA);
        }
        try {
            return Double.parseDouble(texto);
        } catch (NumberFormatException e) {
            throw new CalculadoraException(CalculadoraException.TipoErro.ENTRADA_INVALIDA);
        }
    }

    public static String formatar(double valor) {
        if (valor == Math.floor(valor) && !Double.isInfinite(valor) && Math.abs(valor) < 1e15) {
            return String.valueOf((long) valor);
        }
        return String.format("%.6f", valor).replaceAll("0+$", "").replaceAll("\\.$", "");
    }
}
