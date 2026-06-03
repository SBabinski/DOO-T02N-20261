public class CalculadoraException extends Exception {

    public static final int DIVISAO_POR_ZERO = 1;
    public static final int ENTRADA_INVALIDA = 2;
    public static final int CAMPO_VAZIO      = 3;

    private int tipoErro;

  
    public CalculadoraException(String mensagem, int tipoErro) {
        super(mensagem);
        this.tipoErro = tipoErro;
    }

    public int getTipoErro() {
        return tipoErro;
    }

    public String getMensagemAmigavel() {
        if (tipoErro == DIVISAO_POR_ZERO) {
            return "Erro: Divisao por zero nao e permitida!";
        } else if (tipoErro == ENTRADA_INVALIDA) {
            return "Erro: Entrada invalida! Digite apenas numeros.";
        } else if (tipoErro == CAMPO_VAZIO) {
            return "Erro: Preencha os dois campos antes de calcular.";
        } else {
            return "Erro desconhecido.";
        }
    }
}
