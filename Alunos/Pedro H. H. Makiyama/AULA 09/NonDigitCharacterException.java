import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class NonDigitCharacterException extends RuntimeException {

    private final JFrame frame = new JFrame();
    
    public NonDigitCharacterException(String message){

        JOptionPane.showMessageDialog(frame, message, "Erro no input!", JOptionPane.ERROR_MESSAGE);
    }
}
