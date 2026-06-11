import javax.swing.JOptionPane;

public class ErroDivisaoPorZeroException extends RuntimeException {

    public ErroDivisaoPorZeroException() {
        JOptionPane.showMessageDialog(null, 
            "Erro Matemático: Não é possível dividir por 0!", 
            "Erro de Cálculo", 
            JOptionPane.ERROR_MESSAGE);
    } 
}
