package calculadora.model;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JButton;

public class ButtonResult extends JButton{
	public ButtonResult (String texto) {
		super(texto);

		setBackground(Color.BLACK);
		setForeground(Color.WHITE);
		
		setFont(new Font("Arial", Font.BOLD, 20));

		setFocusPainted(false);

		setPreferredSize(new Dimension(60, 60));
	}
}
