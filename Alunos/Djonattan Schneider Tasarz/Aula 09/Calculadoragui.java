import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CalculadoraGUI extends JFrame {

    private JTextField display;
    private Calculadora calc;
    private String operacaoAtual;
    private double primeroNumero;
    private boolean novaEntrada;

    public CalculadoraGUI() {
        calc = new Calculadora();
        novaEntrada = false;
        operacaoAtual = "";
        primeroNumero = 0;

        setTitle("Calculadora");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLayout(new BorderLayout(5, 5));

        display = new JTextField("0");
        display.setHorizontalAlignment(JTextField.RIGHT);
        display.setEditable(false);
        display.setFont(new Font("Arial", Font.PLAIN, 26));
        display.setPreferredSize(new Dimension(300, 55));
        display.setBackground(Color.WHITE);
        add(display, BorderLayout.NORTH);

        JPanel painel = new JPanel(new GridLayout(5, 4, 5, 5));
        painel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        String[] botoes = {
            "7", "8", "9", "/",
            "4", "5", "6", "*",
            "1", "2", "3", "-",
            "0", ".", "=", "+",
            "C", "", "", ""
        };

        for (String texto : botoes) {
            if (texto.isEmpty()) {
                painel.add(new JLabel()); // espaco vazio
            } else {
                JButton btn = criarBotao(texto);
                painel.add(btn);
            }
        }

        add(painel, BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private JButton criarBotao(String texto) {
        JButton btn = new JButton(texto);
        btn.setFont(new Font("Arial", Font.BOLD, 18));
        btn.setFocusPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));

        if (texto.matches("[0-9]|\\.")) {
            btn.setBackground(new Color(230, 230, 230));
        } else if (texto.equals("=")) {
            btn.setBackground(new Color(70, 130, 180));
            btn.setForeground(Color.WHITE);
        } else if (texto.equals("C")) {
            btn.setBackground(new Color(220, 80, 80));
            btn.setForeground(Color.WHITE);
        } else {
            btn.setBackground(new Color(180, 180, 180));
        }

        btn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                tratarClique(texto);
            }
        });

        return btn;
    }

    private void tratarClique(String valor) {
        if (valor.matches("[0-9]")) {
            if (display.getText().equals("0") || novaEntrada) {
                display.setText(valor);
                novaEntrada = false;
            } else {
                display.setText(display.getText() + valor);
            }

        } else if (valor.equals(".")) {
            if (novaEntrada) {
                display.setText("0.");
                novaEntrada = false;
            } else if (!display.getText().contains(".")) {
                display.setText(display.getText() + ".");
            }

        } else if (valor.equals("C")) {
            display.setText("0");
            operacaoAtual = "";
            primeroNumero = 0;
            novaEntrada = false;

        } else if (valor.equals("=")) {
            if (!operacaoAtual.isEmpty()) {
                try {
                    double segundoNumero = calc.parsearNumero(display.getText());
                    double resultado = calc.calcular(primeroNumero, segundoNumero, operacaoAtual);

                    if (resultado == (long) resultado) {
                        display.setText(String.valueOf((long) resultado));
                    } else {
                        display.setText(String.valueOf(resultado));
                    }

                    operacaoAtual = "";
                    novaEntrada = true;

                } catch (CalculadoraException ex) {
                    JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                    display.setText("0");
                    operacaoAtual = "";
                    novaEntrada = false;
                }
            }

        } else {

            try {
                primeroNumero = calc.parsearNumero(display.getText());
                operacaoAtual = valor;
                novaEntrada = true;
            } catch (CalculadoraException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new CalculadoraGUI();
            }
        });
    }
}