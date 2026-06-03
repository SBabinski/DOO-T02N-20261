package calculadoraSwing;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;

import javax.swing.*;

public class Calculadora {

	JFrame tela;
	JTextField campo;

	JButton b0, b1, b2, b3, b4, b5, b6, b7, b8, b9;
	JButton bMais, bMenos, bDivisao, bMultiplicacao;
	JButton bIgual, bC;

	double num1;
	double num2;
	String operador;

	public Calculadora() {

		criarTela();
		criarComponentes();
		adicionarEventos();

		tela.setVisible(true);
	}

	void criarTela() {

		tela = new JFrame("Calculadora");
		tela.setBounds(1000, 100, 400, 600);
		tela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		tela.setLayout(new BorderLayout());
	}

	void criarComponentes() {

		campo = new JTextField();
		campo.setFocusable(false);

		JPanel painelDisplay = new JPanel(new GridLayout(1, 1));
		painelDisplay.add(campo);

		JPanel painelBotoes = new JPanel(new GridLayout(4, 4));

		b1 = new JButton("1");
		b2 = new JButton("2");
		b3 = new JButton("3");
		bC = new JButton("C");

		b4 = new JButton("4");
		b5 = new JButton("5");
		b6 = new JButton("6");
		bMais = new JButton("+");

		b7 = new JButton("7");
		b8 = new JButton("8");
		b9 = new JButton("9");
		bMenos = new JButton("-");

		b0 = new JButton("0");
		bDivisao = new JButton("/");
		bMultiplicacao = new JButton("*");
		bIgual = new JButton("=");

		painelBotoes.add(b1);
		painelBotoes.add(b2);
		painelBotoes.add(b3);
		painelBotoes.add(bC);

		painelBotoes.add(b4);
		painelBotoes.add(b5);
		painelBotoes.add(b6);
		painelBotoes.add(bMais);

		painelBotoes.add(b7);
		painelBotoes.add(b8);
		painelBotoes.add(b9);
		painelBotoes.add(bMenos);

		painelBotoes.add(b0);
		painelBotoes.add(bDivisao);
		painelBotoes.add(bMultiplicacao);
		painelBotoes.add(bIgual);

		tela.add(painelDisplay, BorderLayout.NORTH);
		tela.add(painelBotoes, BorderLayout.CENTER);
	}

	void adicionarEventos() {

		adicionarBotaoNumero(b0, "0");
		adicionarBotaoNumero(b1, "1");
		adicionarBotaoNumero(b2, "2");
		adicionarBotaoNumero(b3, "3");
		adicionarBotaoNumero(b4, "4");
		adicionarBotaoNumero(b5, "5");
		adicionarBotaoNumero(b6, "6");
		adicionarBotaoNumero(b7, "7");
		adicionarBotaoNumero(b8, "8");
		adicionarBotaoNumero(b9, "9");

		adicionarOperador(bMais, "+");
		adicionarOperador(bMenos, "-");
		adicionarOperador(bDivisao, "/");
		adicionarOperador(bMultiplicacao, "*");

		// BOTÃO IGUAL
		bIgual.addActionListener(e -> calcular());

		// ENTER
		campo.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
				.put(KeyStroke.getKeyStroke("ENTER"), "=");

		campo.getActionMap().put("=", new AbstractAction() {

			@Override
			public void actionPerformed(ActionEvent e) {
				calcular();
			}
		});

		// LIMPAR
		bC.addActionListener(e -> campo.setText(""));

		campo.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
				.put(KeyStroke.getKeyStroke("BACK_SPACE"), "limpar");

		campo.getActionMap().put("limpar", new AbstractAction() {

			@Override
			public void actionPerformed(ActionEvent e) {
				campo.setText("");
			}
		});
	}

	void adicionarBotaoNumero(JButton botao, String valor) {

		botao.addActionListener(e -> addNumero(valor));

		campo.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
				.put(KeyStroke.getKeyStroke(valor.charAt(0)), valor);

		campo.getActionMap().put(valor, new AbstractAction() {

			@Override
			public void actionPerformed(ActionEvent e) {
				addNumero(valor);
			}
		});
	}

	void adicionarOperador(JButton botao, String op) {

		botao.addActionListener(e -> executarOperador(op));

		// TECLADO
		campo.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
				.put(KeyStroke.getKeyStroke(op.charAt(0)), op);

		campo.getActionMap().put(op, new AbstractAction() {

			@Override
			public void actionPerformed(ActionEvent e) {
				executarOperador(op);
			}
		});
	}

	void executarOperador(String op) {

		try {

			if (campo.getText().isEmpty()) {
				throw new UmaException(
						"Digite um número antes do operador.");
			}

			num1 = Double.parseDouble(campo.getText());

			operador = op;

			campo.setText("");

		} catch (UmaException e) {

			return;

		} catch (Exception erro) {

			new UmaException("Número inválido.");
		}
	}

	void calcular() {

		try {

			if (campo.getText().isEmpty()) {
				throw new UmaException(
						"Digite o segundo número.");
			}

			if (operador == null) {
				throw new UmaException(
						"O que você quer fazer filho de Deus? selecione uma operação,"
						+ " só nao vai dividir por zero.");
			}

			num2 = Double.parseDouble(campo.getText());

			double resultado = Operacoes.calcular(
					num1, num2, operador);
			
			campo.setText(String.valueOf(resultado));
			
			if(resultado == 13) {
				throw new UmaException("faz o L! :VVVVVVVV");
			}

		} catch (UmaException e) {

			return;

		} catch (Exception erro) {

			new UmaException(erro.getMessage());
		}
	}

	void addNumero(String n) {

		campo.setText(campo.getText() + n);
	}
}