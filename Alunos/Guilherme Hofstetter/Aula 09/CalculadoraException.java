public class CalculadoraException extends Exception {

    public enum TipoErro {
        DIVISAO_POR_ZERO,
        ENTRADA_INVALIDA,
        OPERACAO_INVALIDA
    }

    private final TipoErro tipoErro;

    public CalculadoraException(TipoErro tipo) {
        super(obterMensagem(tipo));
        this.tipoErro = tipo;
    }

    public TipoErro getTipoErro() {
        return tipoErro;
    }

    private static String obterMensagem(TipoErro tipo) {
        switch (tipo) {
            case DIVISAO_POR_ZERO:   return "Nao. Apenas Nao.";
            case ENTRADA_INVALIDA:   return "Numero invalido!";
            case OPERACAO_INVALIDA:  return "Operacao invalida!";
            default:                 return "Erro desconhecido.";
        }
    }
}
