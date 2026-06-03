package fag;

public class CalculadoraException extends Exception {

    public static final String DIVISAO_POR_ZERO  = "DIVISAO_POR_ZERO";
    public static final String ENTRADA_INVALIDA  = "ENTRADA_INVALIDA";
    public static final String CAMPO_VAZIO       = "CAMPO_VAZIO";

    private String tipo;

    public CalculadoraException(String tipo, String mensagem) {
        super(mensagem);
        this.tipo = tipo;
    }

    public String getTipo() {
        return tipo;
    }

    public void exibirErro() {
        System.out.println("[ERRO - " + tipo + "] " + getMessage());
    }
}