import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Calculator implements ActionListener{

	JFrame frame;
	JTextField textfield;
	JButton[] numberButtons = new JButton[10];
	JButton[] functionButtons = new JButton[9];
	JButton addButton,subButton,mulButton,divButton;
	JButton decButton, equButton, delButton, clrButton, negButton;
	JPanel panel;
	boolean resultadoExibido = false;
	JLabel expressionLabel;
	String expressao = "";
	
	Font myFont = new Font("Ink Free",Font.PLAIN,30);
	
	double num1=0,num2=0,result=0;
	char operator;
	
	Calculator(){
		
		frame = new JFrame("Calculator");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(420, 550);
		frame.setLayout(null);
		
		textfield = new JTextField();
		textfield.setBounds(50, 30, 300, 50);
		textfield.setFont(myFont);
		textfield.setEditable(false);
		
		addButton = new JButton("+");
		subButton = new JButton("-");
		mulButton = new JButton("*");
		divButton = new JButton("/");
		decButton = new JButton(".");
		equButton = new JButton("=");
		delButton = new JButton("Del");
		clrButton = new JButton("Clr");
		negButton = new JButton("(-)");
		
		functionButtons[0] = addButton;
		functionButtons[1] = subButton;
		functionButtons[2] = mulButton;
		functionButtons[3] = divButton;
		functionButtons[4] = decButton;
		functionButtons[5] = equButton;
		functionButtons[6] = delButton;
		functionButtons[7] = clrButton;
		functionButtons[8] = negButton;
		
		for(int i =0;i<9;i++) {
			functionButtons[i].addActionListener(this);
			functionButtons[i].setFont(myFont);
			functionButtons[i].setFocusable(false);
		}
		
		for(int i =0;i<10;i++) {
			numberButtons[i] = new JButton(String.valueOf(i));
			numberButtons[i].addActionListener(this);
			numberButtons[i].setFont(myFont);
			numberButtons[i].setFocusable(false);
		}
		
		negButton.setBounds(50,430,100,50);
		delButton.setBounds(150,430,100,50);
		clrButton.setBounds(250,430,100,50);
		
		panel = new JPanel();
		panel.setBounds(50, 100, 300, 300);
		panel.setLayout(new GridLayout(4,4,10,10));

		panel.add(numberButtons[1]);
		panel.add(numberButtons[2]);
		panel.add(numberButtons[3]);
		panel.add(addButton);
		panel.add(numberButtons[4]);
		panel.add(numberButtons[5]);
		panel.add(numberButtons[6]);
		panel.add(subButton);
		panel.add(numberButtons[7]);
		panel.add(numberButtons[8]);
		panel.add(numberButtons[9]);
		panel.add(mulButton);
		panel.add(decButton);
		panel.add(numberButtons[0]);
		panel.add(equButton);
		panel.add(divButton);
		
		frame.add(panel);
		frame.add(negButton);
		frame.add(delButton);
		frame.add(clrButton);
		frame.add(textfield);
		frame.setVisible(true);

		expressionLabel = new JLabel("", SwingConstants.RIGHT);
		expressionLabel.setBounds(50, 8, 300, 20);
		expressionLabel.setFont(new Font("Ink Free", Font.PLAIN, 14));
		expressionLabel.setForeground(Color.GRAY);
		frame.add(expressionLabel);
	}
	
	public static void main(String[] args) {
		
		Calculator calc = new Calculator();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		for (int i = 0; i < 10; i++) {
			if (e.getSource() == numberButtons[i]) {
				if (resultadoExibido) {
					textfield.setText(String.valueOf(i)); 
					resultadoExibido = false;
				} else {
					textfield.setText(textfield.getText().concat(String.valueOf(i)));
				}
			}
		}
		if (e.getSource() == decButton) {
			if (resultadoExibido) {
				textfield.setText("0.");
				resultadoExibido = false;
			} else if (!textfield.getText().contains(".")) {
				textfield.setText(textfield.getText().concat("."));
			}
		}
		if(e.getSource()==addButton) {
			try{
				num1 = parseInput();
				operator ='+';
				expressao = textfield.getText() + " +";
				expressionLabel.setText(expressao);
				textfield.setText("");
				resultadoExibido = false;
			} catch (NumberFormatException ex) {
				showError("entrada inválida");
			}
		}
		if(e.getSource()==subButton) {
			try{
				num1 = parseInput();
				operator ='-';
				expressao = textfield.getText() + " -";
				expressionLabel.setText(expressao);
				textfield.setText("");
				resultadoExibido = false;
			} catch (NumberFormatException ex) {
				showError("entrada inválida");
			}
		}
		if(e.getSource()==mulButton) {
			try{
				num1 = parseInput();
				operator ='*';
				expressao = textfield.getText() + " x";
				expressionLabel.setText(expressao);
				textfield.setText("");
				resultadoExibido = false;
			} catch (NumberFormatException ex) {
				showError("entrada inválida");
			}
		}
		if(e.getSource()==divButton) {
			try{
				num1 = parseInput();
				operator ='/';
				expressao = textfield.getText() + " /";
				expressionLabel.setText(expressao);
				textfield.setText("");
				resultadoExibido = false;
			} catch (NumberFormatException ex) {
				showError("entrada inválida");
			}
		}
		if (e.getSource() == equButton) {
			try {
				num2 = parseInput();
				switch (operator) {
					case '+': result = num1 + num2; break;
					case '-': result = num1 - num2; break;
					case '*': result = num1 * num2; break;
					case '/':
						if (num2 == 0) {
							throw new ArithmeticException("divisão por zero");
						}
						result = num1 / num2;
						break;
					default:
						throw new ArithmeticException("operador não definido");
				}
				expressionLabel.setText(expressao + " " + textfield.getText() + " =");
				textfield.setText(String.valueOf(result));
				num1 = result;
				resultadoExibido = true;
			} catch (NumberFormatException ex) {
				showError("entrada inválida");
			} catch (ArithmeticException ex) {
				showError(ex.getMessage());
			}
		}
		if(e.getSource()==clrButton) {
			textfield.setText("");
			expressionLabel.setText("");
    		expressao = "";
    		resultadoExibido = false;
		}
		if(e.getSource()==delButton) {
			String string = textfield.getText();
			textfield.setText("");
			for(int i=0;i<string.length()-1;i++) {
				textfield.setText(textfield.getText()+string.charAt(i));
			}
		}
		if (e.getSource() == negButton) {
			try {
				double temp = parseInput();
				temp *= -1;
				textfield.setText(String.valueOf(temp));
			} catch (NumberFormatException ex) {
				showError("nada para negar");
			}
		}
	}
	private double parseInput() throws NumberFormatException {
    String text = textfield.getText().trim();
    if (text.isEmpty()) {
        throw new NumberFormatException("Campo vazio");
    }
    return Double.parseDouble(text);
	}

	private void showError(String mensagem) {
		textfield.setText("ERRO: " + mensagem);
		num1 = 0;
		num2 = 0;
		operator = ' ';
	}
}