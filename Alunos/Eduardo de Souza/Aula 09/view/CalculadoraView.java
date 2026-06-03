package calculadora.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import calculadora.model.ButtonNumber;
import calculadora.model.ButtonOperator;
import calculadora.model.ButtonResult;

public class CalculadoraView {
	public JFrame frame = new JFrame("Calculadora");
	public JPanel mainPanel = new JPanel(new BorderLayout());
	
	public JPanel keyboardPanel = new JPanel();
	public JPanel keyboardNumberPanel = new JPanel();
	public JPanel keyboardOperatorPanel = new JPanel();
	
	public JPanel resultPanel = new JPanel();
	public JPanel resultCalcPanel = new JPanel();
	public JTextField label_result = new JTextField("");
	
	public ButtonOperator button_sum = new ButtonOperator("+");
	public ButtonOperator button_minus = new ButtonOperator("-");
	public ButtonOperator button_multiply = new ButtonOperator("x");
	public ButtonOperator button_divide = new ButtonOperator("÷");

	public ButtonNumber button_nine = new ButtonNumber("9");
	public ButtonNumber button_eight = new ButtonNumber("8");
	public ButtonNumber button_seven = new ButtonNumber("7");

	public ButtonNumber button_six = new ButtonNumber("6");
	public ButtonNumber button_five = new ButtonNumber("5");
	public ButtonNumber button_four = new ButtonNumber("4");

	public ButtonNumber button_three = new ButtonNumber("3");
	public ButtonNumber button_two = new ButtonNumber("2");
	public ButtonNumber button_one = new ButtonNumber("1");

	public ButtonResult button_c = new ButtonResult("C");
	public ButtonNumber button_zero = new ButtonNumber("0");
	public ButtonResult button_equal = new ButtonResult("=");	
	
	public CalculadoraView() {
		construirCalculadora();
	}
	
	public void construirCalculadora() {
		frame.setSize(350, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		mainPanel.setBackground(Color.DARK_GRAY);
		
		keyboardPanel.setLayout(new BorderLayout());
		keyboardPanel.setBackground(Color.DARK_GRAY);
		keyboardPanel.setPreferredSize(new Dimension(350, 400));

		keyboardNumberPanel.setBackground(Color.DARK_GRAY);
		keyboardNumberPanel.setLayout(new GridLayout(4, 3, 2, 2));	

		keyboardOperatorPanel.setBackground(Color.DARK_GRAY);
		keyboardOperatorPanel.setLayout(new GridLayout(1, 4, 1, 1));

		resultPanel.setLayout(new BorderLayout());
		resultPanel.setBackground(Color.DARK_GRAY);
		resultPanel.setPreferredSize(new Dimension(350, 160));
		
		resultCalcPanel.setLayout(new BorderLayout());
		resultCalcPanel.setBackground(Color.DARK_GRAY);
		
		label_result.setHorizontalAlignment(JTextField.RIGHT);
		label_result.setBackground(Color.DARK_GRAY);
		label_result.setBorder(null);
		label_result.setForeground(Color.WHITE);
		label_result.setFont(new Font("Arial", Font.BOLD, 30));
		label_result.setEditable(false);
		
		keyboardOperatorPanel.add(button_sum);
		keyboardOperatorPanel.add(button_minus);
		keyboardOperatorPanel.add(button_multiply);
		keyboardOperatorPanel.add(button_divide);

		keyboardNumberPanel.add(button_seven);
		keyboardNumberPanel.add(button_eight);
		keyboardNumberPanel.add(button_nine);

		keyboardNumberPanel.add(button_four);
		keyboardNumberPanel.add(button_five);
		keyboardNumberPanel.add(button_six);

		keyboardNumberPanel.add(button_one);
		keyboardNumberPanel.add(button_two);
		keyboardNumberPanel.add(button_three);

		keyboardNumberPanel.add(button_c);
		keyboardNumberPanel.add(button_zero);
		keyboardNumberPanel.add(button_equal);
		
		keyboardPanel.add(keyboardOperatorPanel, BorderLayout.NORTH);
		keyboardPanel.add(keyboardNumberPanel, BorderLayout.CENTER);
			
		resultPanel.add(resultCalcPanel, BorderLayout.SOUTH);
		
		resultCalcPanel.add(label_result, BorderLayout.SOUTH);
	
		mainPanel.add(resultPanel, BorderLayout.NORTH);
		mainPanel.add(keyboardPanel, BorderLayout.SOUTH);

		frame.add(mainPanel);
		
		frame.setVisible(true);
	}
}
