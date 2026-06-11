import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class OperatorAlreadyDefinedException extends RuntimeException {

    JFrame frame = new JFrame();

    public OperatorAlreadyDefinedException(String message){

        JOptionPane.showMessageDialog(frame, message, "Operador já definido!", JOptionPane.ERROR_MESSAGE);
    }
}
