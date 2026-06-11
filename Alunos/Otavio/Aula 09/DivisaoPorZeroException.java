public class DivisaoPorZeroException extends CalculadoraException {
    public DivisaoPorZeroException() {
        super("Erro: divisão por zero não é permitida.");
    }
}