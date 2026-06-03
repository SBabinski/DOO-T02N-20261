import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

// Exception personalizada
class CalculadoraException extends Exception {
    public CalculadoraException(String mensagem) {
        super(mensagem);
    }
}

public class Calculadora extends JFrame implements ActionListener {

    private JTextField display;

    private double numero1 = 0;
    private String operador = "";

    public Calculadora() {

        setTitle("Calculadora Java");
        setSize(350, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Campo de exibição
        display = new JTextField();
        display.setFont(new Font("Arial", Font.BOLD, 35));
        display.setHorizontalAlignment(JTextField.RIGHT);
        display.setEditable(false);
        display.setPreferredSize(new Dimension(300, 80));
        display.setBackground(Color.BLACK);
        display.setForeground(Color.WHITE);
        display.setCaretColor(Color.WHITE);

        // Painel principal
        JPanel painel = new JPanel();
        painel.setLayout(new BorderLayout());
        painel.setBackground(new Color(20,20,20));
        painel.setForeground(Color.WHITE);
        painel.add(display, BorderLayout.NORTH);

        // Painel dos botões
        JPanel botoes = new JPanel();
        botoes.setLayout(new GridLayout(4, 4, 10, 10));
        botoes.setBackground(Color.DARK_GRAY);

        String[] textos = {
                "7", "8", "9", "/",
                "4", "5", "6", "*",
                "1", "2", "3", "-",
                "0", "C", "=", "+"
        };

        for (String texto : textos) {
            JButton botao = new JButton(texto);
            botao.setFont(new Font("Arial", Font.BOLD, 22));
            botao.setBackground(new Color(255, 160, 36));
            botao.setForeground(Color.BLACK);
            botao.addActionListener(this);
            botoes.add(botao);
        }

        painel.add(botoes, BorderLayout.CENTER);

        add(painel);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        String comando = e.getActionCommand();

        try {

            // Números
            if (comando.matches("[0-9]")) {
                display.setText(display.getText() + comando);
            }

            // Limpar
            else if (comando.equals("C")) {
                display.setText("");
                numero1 = 0;
                operador = "";
            }

            // Operadores
            else if (comando.matches("[+\\-*/]")) {

                if (display.getText().isEmpty()) {
                    throw new CalculadoraException("Digite um número primeiro.");
                }

                numero1 = Double.parseDouble(display.getText());
                operador = comando;
                display.setText("");
            }

            // Resultado
            else if (comando.equals("=")) {

                if (display.getText().isEmpty()) {
                    throw new CalculadoraException("Digite o segundo número.");
                }

                double numero2 = Double.parseDouble(display.getText());
                double resultado = calcular(numero1, numero2, operador);

                display.setText(String.valueOf(resultado));
            }

        } catch (NumberFormatException ex) {

            JOptionPane.showMessageDialog(
                    this,
                    "Entrada inválida. Digite apenas números.",
                    "Erro",
                    JOptionPane.ERROR_MESSAGE
            );

        } catch (CalculadoraException ex) {

            JOptionPane.showMessageDialog(
                    this,
                    ex.getMessage(),
                    "Erro da Calculadora",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    // Método das operações
    private double calcular(double n1, double n2, String op)
            throws CalculadoraException {

        switch (op) {

            case "+":
                return n1 + n2;

            case "-":
                return n1 - n2;

            case "*":
                return n1 * n2;

            case "/":

                if (n2 == 0) {
                    throw new CalculadoraException(
                            "Não é possível dividir por zero."
                    );
                }

                return n1 / n2;

            default:
                throw new CalculadoraException("Operação inválida.");
        }
    }

    // Main
    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            new Calculadora();
        });
    }
}