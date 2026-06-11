import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class NotEnoughValuesException extends RuntimeException {

    private final JFrame frame = new JFrame();

    public NotEnoughValuesException(String message){

        JOptionPane.showMessageDialog(frame, message, "Quantidade insuficiente de valores!", JOptionPane.ERROR_MESSAGE);
    }
}
