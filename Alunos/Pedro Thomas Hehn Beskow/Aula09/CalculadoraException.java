/**
 * Exceção personalizada para erros da Calculadora.
 * Cobre situações como divisão por zero e entrada inválida.
 */
public class CalculadoraException extends Exception {

    public enum TipoErro {
        DIVISAO_POR_ZERO,
        ENTRADA_INVALIDA,
        OVERFLOW,
        OPERACAO_INVALIDA
    }

    private final TipoErro tipoErro;

    public CalculadoraException(TipoErro tipoErro, String mensagem) {
        super(mensagem);
        this.tipoErro = tipoErro;
    }

    public TipoErro getTipoErro() {
        return tipoErro;
    }

    /**
     * Retorna um ícone emoji amigável baseado no tipo de erro.
     */
    public String getIconeErro() {
        return switch (tipoErro) {
            case DIVISAO_POR_ZERO  -> "⚠️";
            case ENTRADA_INVALIDA  -> "❌";
            case OVERFLOW          -> "📈";
            case OPERACAO_INVALIDA -> "🔧";
        };
    }

    @Override
    public String toString() {
        return "[" + tipoErro + "] " + getMessage();
    }
}
