package aula09;
 
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
 
public class CalculadoraGUI extends JFrame implements ActionListener {
 
    private JTextField display;
    private Calculadora calculadora;
    private String operacaoAtual;
    private double primeroNumero;
    private boolean novaEntrada;
 
    public CalculadoraGUI() {
        calculadora = new Calculadora();
        novaEntrada = true;
 
        setTitle("Calculadora");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
 
        display = new JTextField("0");
        display.setHorizontalAlignment(JTextField.RIGHT);
        display.setEditable(false);
        display.setFont(new Font("Arial", Font.PLAIN, 24));
        display.setPreferredSize(new Dimension(280, 50));
 
        JPanel painelBotoes = new JPanel();
        painelBotoes.setLayout(new GridLayout(5, 4, 5, 5));
 
        String[] botoes = {
            "7", "8", "9", "/",
            "4", "5", "6", "*",
            "1", "2", "3", "-",
            "0", "C", "=", "+"
        };
 
        for (String texto : botoes) {
            JButton btn = new JButton(texto);
            btn.setFont(new Font("Arial", Font.PLAIN, 18));
            btn.addActionListener(this);
            painelBotoes.add(btn);
        }
 
        setLayout(new BorderLayout(10, 10));
        add(display, BorderLayout.NORTH);
        add(painelBotoes, BorderLayout.CENTER);
 
        ((JPanel) getContentPane()).setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
 
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
 
    @Override
    public void actionPerformed(ActionEvent e) {
        String comando = e.getActionCommand();
 
        if (comando.equals("C")) {
            display.setText("0");
            operacaoAtual = null;
            primeroNumero = 0;
            novaEntrada = true;
            return;
        }
 
        if (comando.equals("+") || comando.equals("-") || comando.equals("*") || comando.equals("/")) {
            try {
                primeroNumero = calculadora.parseNumero(display.getText());
                operacaoAtual = comando;
                novaEntrada = true;
            } catch (CalculadoraException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
            return;
        }
 
        if (comando.equals("=")) {
            if (operacaoAtual == null) return;
 
            try {
                double segundoNumero = calculadora.parseNumero(display.getText());
                double resultado = calculadora.calcular(primeroNumero, segundoNumero, operacaoAtual);
 
                if (resultado == (long) resultado) {
                    display.setText(String.valueOf((long) resultado));
                } else {
                    display.setText(String.valueOf(resultado));
                }
 
                operacaoAtual = null;
                novaEntrada = true;
            } catch (CalculadoraException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                display.setText("0");
                novaEntrada = true;
            }
            return;
        }
 
        if (novaEntrada) {
            display.setText(comando);
            novaEntrada = false;
        } else {
            String atual = display.getText();
            if (atual.equals("0")) {
                display.setText(comando);
            } else {
                display.setText(atual + comando);
            }
        }
    }
 
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new CalculadoraGUI());
    }
}