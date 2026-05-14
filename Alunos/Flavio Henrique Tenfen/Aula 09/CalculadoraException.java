
public class CalculadoraException extends Exception {

    public enum TipoErro {
        DIVISAO_POR_ZERO,
        ENTRADA_INVALIDA,
        OVERFLOW_NUMERICO,
        OPERACAO_INDEFINIDA
    }

    private final TipoErro tipoErro;

    public CalculadoraException(TipoErro tipo, String mensagem) {
        super(mensagem);
        this.tipoErro = tipo;
    }

    public TipoErro getTipoErro() {
        return tipoErro;
    }

    public String getMensagemAmigavel() {
        return switch (tipoErro) {
            case DIVISAO_POR_ZERO     -> "Erro: Divisão por zero não é permitida!";
            case ENTRADA_INVALIDA     -> "Erro: Entrada inválida. Use apenas números.";
            case OVERFLOW_NUMERICO    -> "Erro: Número muito grande ou muito pequeno!";
            case OPERACAO_INDEFINIDA  -> "Erro: Nenhuma operação selecionada.";
        };
    }
}
