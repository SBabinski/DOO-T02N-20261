import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class ZeroDivisionException extends RuntimeException {

    private final JFrame frame = new JFrame();

    public ZeroDivisionException(String message){

            JOptionPane.showMessageDialog(frame, message, "Erro na operação!", JOptionPane.ERROR_MESSAGE);
    }
}
