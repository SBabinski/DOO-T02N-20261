package calculadora.model;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JButton;

public class ButtonOperator extends JButton {
	
	public ButtonOperator (String texto) {

		super(texto);

		setBackground(Color.yellow);
		setForeground(Color.BLACK);
		
		setFont(new Font("Arial", Font.BOLD, 20));

		setFocusPainted(false);

		setPreferredSize(new Dimension(50, 40));
	}
}
