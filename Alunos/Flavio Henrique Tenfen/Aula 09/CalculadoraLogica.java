public class CalculadoraLogica {

    public double calcular(String aStr, String bStr, String operacao)
            throws CalculadoraException {

        double a = parseNumero(aStr);
        double b = parseNumero(bStr);

        if (operacao == null || operacao.isBlank()) {
            throw new CalculadoraException(
                    CalculadoraException.TipoErro.OPERACAO_INDEFINIDA,
                    "Nenhuma operação selecionada.");
        }

        double resultado = switch (operacao) {
            case "+" -> a + b;
            case "-" -> a - b;
            case "*" -> a * b;
            case "/" -> dividir(a, b);
            default  -> throw new CalculadoraException(
                    CalculadoraException.TipoErro.OPERACAO_INDEFINIDA,
                    "Operação desconhecida: " + operacao);
        };

        if (Double.isInfinite(resultado) || Double.isNaN(resultado)) {
            throw new CalculadoraException(
                    CalculadoraException.TipoErro.OVERFLOW_NUMERICO,
                    "O resultado está fora do intervalo numérico suportado.");
        }

        return resultado;
    }

    private double parseNumero(String texto) throws CalculadoraException {
        if (texto == null || texto.isBlank()) {
            throw new CalculadoraException(
                    CalculadoraException.TipoErro.ENTRADA_INVALIDA,
                    "Campo de número está vazio.");
        }
        try {
            double valor = Double.parseDouble(texto.replace(",", ".").trim());
            if (Double.isInfinite(valor) || Double.isNaN(valor)) {
                throw new CalculadoraException(
                        CalculadoraException.TipoErro.OVERFLOW_NUMERICO,
                        "Número fora do intervalo suportado: " + texto);
            }
            return valor;
        } catch (NumberFormatException e) {
            throw new CalculadoraException(
                    CalculadoraException.TipoErro.ENTRADA_INVALIDA,
                    "\"" + texto + "\" não é um número válido.");
        }
    }

    private double dividir(double a, double b) throws CalculadoraException {
        if (b == 0) {
            throw new CalculadoraException(
                    CalculadoraException.TipoErro.DIVISAO_POR_ZERO,
                    "Tentativa de dividir " + a + " por zero.");
        }
        return a / b;
    }

    public static String formatarResultado(double valor) {
        if (valor == Math.floor(valor) && !Double.isInfinite(valor)) {
            return String.valueOf((long) valor);
        }
        String s = String.format("%.10f", valor).replaceAll("0+$", "").replaceAll("\\.$", "");
        return s;
    }
}
