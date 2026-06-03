package fag;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class Aula09 {

	public static void main(String[] args) {
		
		JFrame frame = new JFrame("Exemplo bala do JFrame e JPanel");
		frame.setSize(450, 550);
		
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.gray);
		panel.setBorder(javax.swing.BorderFactory.createEmptyBorder(20, 20, 20, 20));
		
		frame.add(panel);
		
		JLabel view = new JLabel();
		view.setBackground(Color.darkGray);
		view.setOpaque(true);
		panel.add(view);
		view.setPreferredSize(new Dimension (400, 150));
		view.setForeground(Color.white);
		view.setHorizontalAlignment(JLabel.RIGHT);
		view.setFont(new Font("Arial", Font.PLAIN, 48));
		view.setFocusable(false);
		
		int[] primeiroNumero = {0};
		String[] operador = {""};
		
		ActionListener escutante = new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        String clicas = e.getActionCommand();
		        if(clicas.equals("+") || clicas.equals("X") || clicas.equals("-") || clicas.equals("%")) {
		        	operador[0] = clicas;
		        	primeiroNumero[0] = Integer.parseInt(view.getText());
		        	view.setText("");
		        	
		        } else if(clicas.equals("C")) {
		        	view.setText("");
		        } else if(clicas.equals("=")){
		        	
		        	try {
		        		if(view.getText().isEmpty()) {
		        			throw new CalculadoraException("Entrada inválida.");	
		        		}
		        	
		        	
		        	int segundoNumero = Integer.parseInt(view.getText());
		            int resultado = 0;
		            
		            if(operador[0].equals("%") && segundoNumero == 0) {
		            	throw new CalculadoraException("Dividiu por 0.");
		            }
		        	
		            if(operador[0].equals("+")) {
		            	resultado = primeiroNumero[0] + segundoNumero;
		            }
		            if(operador[0].equals("X")) {
		            	resultado = primeiroNumero[0] * segundoNumero;
		            }
		            if(operador[0].equals("-")) {
		            	resultado = primeiroNumero[0] - segundoNumero;
		            }
		            if(operador[0].equals("%")) {
		            	resultado = primeiroNumero[0] / segundoNumero;
		            }
		            
		            view.setText(String.valueOf(resultado));
		            
		        	}catch(CalculadoraException ex) {
		        		view.setText(ex.getMessage());
		        	}
		            
		        } else {
		        	view.setText(view.getText() + e.getActionCommand());
		        }
		    }
		};
		
		JPanel numeros = new JPanel();
		numeros.setBackground(Color.black);
		numeros.setLayout(new GridLayout(4, 3, 5, 5));
		numeros.setPreferredSize(new Dimension(400, 300));
		panel.add(numeros);
		
		
		JButton um = new JButton("1");
		um.addActionListener(escutante);
		
		
		JButton dois = new JButton("2");
		dois.addActionListener(escutante);
		
		JButton tres = new JButton("3");
		tres.addActionListener(escutante);
		
		JButton quatro = new JButton("4");
		quatro.addActionListener(escutante);
		
		JButton cinco = new JButton("5");
		cinco.addActionListener(escutante);
		
		JButton seis = new JButton("6");
		seis.addActionListener(escutante);

		JButton sete = new JButton("7");
		sete.addActionListener(escutante);
		
		JButton oito = new JButton("8");
		oito.addActionListener(escutante);
		
		JButton nove = new JButton("9");
		nove.addActionListener(escutante);
		
		JButton c = new JButton("C");
		c.addActionListener(escutante);
		
		JButton zero = new JButton("0");
		zero.addActionListener(escutante);
		
		JButton igual = new JButton("=");
		igual.addActionListener(escutante);
		igual.setFont(new Font("Arial", Font.PLAIN, 16));
		
		JButton mais = new JButton("+");
		mais.addActionListener(escutante);
		mais.setFont(new Font("Arial", Font.PLAIN, 16));
		
		JButton vezes = new JButton("X");
		vezes.addActionListener(escutante);
		vezes.setFont(new Font("Arial", Font.PLAIN, 16));
		
		JButton menos = new JButton("-");
		menos.addActionListener(escutante);
		menos.setFont(new Font("Arial", Font.PLAIN, 16));
		
		JButton divisao = new JButton("%");
		divisao.addActionListener(escutante);
		divisao.setFont(new Font("Arial", Font.PLAIN, 16));
		
		numeros.add(um);
		numeros.add(dois);
		numeros.add(tres);
		numeros.add(mais);
		numeros.add(quatro);
		numeros.add(cinco);
		numeros.add(seis);
		numeros.add(vezes);
		numeros.add(sete);
		numeros.add(oito);
		numeros.add(nove);
		numeros.add(menos);
		numeros.add(c);
		numeros.add(zero);
		numeros.add(igual);
		numeros.add(divisao);
		
		for(java.awt.Component comp : numeros.getComponents()) {
		    comp.setFocusable(false);
		    comp.setBackground(Color.DARK_GRAY);
		    comp.setForeground(Color.WHITE);
		}
		
		c.setForeground(Color.RED);
		
		for(java.awt.Component comp : numeros.getComponents()) {
		    comp.setFocusable(false);
		}
		
		frame.setVisible(true);
		
	}

}
