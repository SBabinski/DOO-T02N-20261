package calculadora.model;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JButton;

public class ButtonNumber extends JButton {

	public ButtonNumber (String texto) {

		super(texto);

		setBackground(Color.GRAY);
		setForeground(Color.WHITE);
		
		setFont(new Font("Arial", Font.BOLD, 20));

		setFocusPainted(false);

		setPreferredSize(new Dimension(50, 40));
	}
	
}
