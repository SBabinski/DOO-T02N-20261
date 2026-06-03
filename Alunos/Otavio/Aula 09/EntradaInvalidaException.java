public class EntradaInvalidaException extends CalculadoraException {
    public EntradaInvalidaException(String valor) {
        super("Erro: \"" + valor + "\" não é um número válido.");
    }
}