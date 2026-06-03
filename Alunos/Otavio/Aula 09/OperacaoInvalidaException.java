public class OperacaoInvalidaException extends CalculadoraException {
    public OperacaoInvalidaException() {
        super("Erro: nenhuma operação selecionada.");
    }
}