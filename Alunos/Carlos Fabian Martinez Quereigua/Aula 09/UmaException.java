package calculadoraSwing;

import javax.swing.JOptionPane;

public class UmaException extends NumberFormatException {

	public UmaException(String message) {
		super(message);
		
		JOptionPane.showMessageDialog(null, message, "Burrice detectada", JOptionPane.ERROR_MESSAGE);
		
	}

}
