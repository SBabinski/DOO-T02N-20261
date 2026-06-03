import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TelaCalculadora extends JFrame implements ActionListener {

    JTextField campo1;
    JTextField campo2;
    JTextField resultado;

    JButton soma;
    JButton subtracao;
    JButton multiplicacao;
    JButton divisao;

    Calculadora calc = new Calculadora();

    public TelaCalculadora() {

        setTitle("Calculadora");
        setSize(300, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        campo1 = new JTextField(20);
        campo2 = new JTextField(20);
        resultado = new JTextField(20);

        resultado.setEditable(false);

        soma = new JButton("+");
        subtracao = new JButton("-");
        multiplicacao = new JButton("*");
        divisao = new JButton("/");

        add(new JLabel("Numero 1"));
        add(campo1);

        add(new JLabel("Numero 2"));
        add(campo2);

        add(soma);
        add(subtracao);
        add(multiplicacao);
        add(divisao);

        add(new JLabel("Resultado"));
        add(resultado);

        soma.addActionListener(this);
        subtracao.addActionListener(this);
        multiplicacao.addActionListener(this);
        divisao.addActionListener(this);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {

        try {

            double n1 = Double.parseDouble(campo1.getText());
            double n2 = Double.parseDouble(campo2.getText());

            String operacao = e.getActionCommand();

            double res = calc.calcular(n1, n2, operacao);

            resultado.setText(String.valueOf(res));

        } catch (NumberFormatException ex) {

            JOptionPane.showMessageDialog(null,
                    "Digite apenas numeros.");

        } catch (CalculadoraException ex) {

            JOptionPane.showMessageDialog(null,
                    ex.getMessage());
        }
    }
}