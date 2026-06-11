/**
 * Classe responsável pela lógica das operações matemáticas.
 * Lança CalculadoraException para situações de erro específicas.
 */
public class CalculadoraLogica {

    private static final double VALOR_MAXIMO = 1e15;

    /**
     * Realiza a operação matemática entre dois operandos.
     */
    public double calcular(double a, double b, String operador) throws CalculadoraException {
        validarValor(a);
        validarValor(b);

        double resultado = switch (operador) {
            case "+"  -> a + b;
            case "-"  -> a - b;
            case "×"  -> a * b;
            case "÷"  -> dividir(a, b);
            default   -> throw new CalculadoraException(
                    CalculadoraException.TipoErro.OPERACAO_INVALIDA,
                    "Operação desconhecida: " + operador
            );
        };

        if (Double.isInfinite(resultado) || Math.abs(resultado) > VALOR_MAXIMO) {
            throw new CalculadoraException(
                    CalculadoraException.TipoErro.OVERFLOW,
                    "Resultado fora do intervalo permitido (máx. ±" + VALOR_MAXIMO + ")"
            );
        }
        if (Double.isNaN(resultado)) {
            throw new CalculadoraException(
                    CalculadoraException.TipoErro.ENTRADA_INVALIDA,
                    "O cálculo produziu um valor inválido (NaN)"
            );
        }
        return resultado;
    }

    /**
     * Converte uma String em double com validação.
     */
    public double parseNumero(String texto) throws CalculadoraException {
        if (texto == null || texto.isBlank()) {
            throw new CalculadoraException(
                    CalculadoraException.TipoErro.ENTRADA_INVALIDA,
                    "O campo de número está vazio"
            );
        }
        try {
            return Double.parseDouble(texto.trim().replace(",", "."));
        } catch (NumberFormatException e) {
            throw new CalculadoraException(
                    CalculadoraException.TipoErro.ENTRADA_INVALIDA,
                    "Entrada inválida: \"" + texto + "\" não é um número válido"
            );
        }
    }

    private double dividir(double a, double b) throws CalculadoraException {
        if (b == 0) {
            throw new CalculadoraException(
                    CalculadoraException.TipoErro.DIVISAO_POR_ZERO,
                    "Divisão por zero não é permitida"
            );
        }
        return a / b;
    }

    private void validarValor(double valor) throws CalculadoraException {
        if (Double.isNaN(valor) || Double.isInfinite(valor)) {
            throw new CalculadoraException(
                    CalculadoraException.TipoErro.ENTRADA_INVALIDA,
                    "Valor numérico inválido detectado"
            );
        }
    }

    /**
     * Formata o resultado: remove ".0" de inteiros.
     */
    public String formatarResultado(double valor) {
        if (valor == Math.floor(valor) && Math.abs(valor) < 1e12) {
            return String.valueOf((long) valor);
        }
        // Até 10 casas decimais, sem zeros desnecessários
        String s = String.format("%.10f", valor).replaceAll("0+$", "").replaceAll("\\.$", "");
        return s;
    }
}
