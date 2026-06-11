import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

class CalculadoraException extends Exception {

    public CalculadoraException(String mensagem) {
        super(mensagem);
    }
}

public class Main {

    public static void main(String[] args) {

        final double[] numero1 = {0};
        final String[] operacao = {""};

        JFrame frame = new JFrame("Super Calculadora Cristã");
        frame.setSize(360, 520);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout(8, 8));
        mainPanel.setBackground(new Color(32, 32, 32));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JTextField input = new JTextField("0");
        input.setEditable(false);
        input.setPreferredSize(new Dimension(340, 90));
        input.setHorizontalAlignment(JTextField.RIGHT);
        input.setFont(new Font("Segoe UI", Font.BOLD, 34));
        input.setBackground(new Color(32, 32, 32));
        input.setForeground(Color.WHITE);
        input.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel buttonsPanel = new JPanel(new GridLayout(5, 4, 8, 8));
        buttonsPanel.setBackground(new Color(32, 32, 32));

        String[] botoes = {
                "C", "←", "?", "/",
                "7", "8", "9", "*",
                "4", "5", "6", "-",
                "1", "2", "3", "+",
                "+/-", "0", ",", "="
        };

        for (String texto : botoes) {

            JButton button = new JButton(texto);

            button.setFont(new Font("Segoe UI", Font.BOLD, 22));
            button.setFocusPainted(false);
            button.setBorderPainted(false);
            button.setForeground(Color.WHITE);

            if (texto.equals("=")) {
                button.setBackground(new Color(0, 120, 215));
            } else if ("+-*/←C?".contains(texto)) {
                button.setBackground(new Color(50, 50, 50));
            } else {
                button.setBackground(new Color(60, 60, 60));
            }

            if (texto.matches("[0-9]")) {

                button.addActionListener(e -> {

                    String textoAtual = input.getText();

                    if (textoAtual.equals("0")) {
                        input.setText(texto);
                    } else {
                        input.setText(textoAtual + texto);
                    }
                });
            }

            if (texto.equals(",")) {

                button.addActionListener(e -> {

                    String textoAtual = input.getText();

                    if (!textoAtual.contains(".")) {
                        input.setText(textoAtual + ".");
                    }
                });
            }

            if ("+-*/".contains(texto)) {

                button.addActionListener(e -> {

                    try {
                        numero1[0] = converterEntrada(input.getText());
                        operacao[0] = texto;
                        input.setText("0");

                    } catch (CalculadoraException erro) {
                        JOptionPane.showMessageDialog(
                                frame,
                                erro.getMessage(),
                                "Entrada inválida",
                                JOptionPane.ERROR_MESSAGE
                        );
                    }
                });
            }

            if (texto.equals("=")) {

                button.addActionListener(e -> {

                    try {
                        String valorAtual = input.getText();

                        verificarEasterEggs(frame, input, valorAtual);

                        double numero2 = converterEntrada(valorAtual);

                        double resultado = calcular(numero1[0], numero2, operacao[0]);

                        if (resultado == 666) {
                            throw new CalculadoraException(
                                    "Resultado proibido nessa calculadora!\nO sangue de Jesus tem poder 🙏"
                            );
                        }

                        input.setText(formatarResultado(resultado));

                    } catch (CalculadoraException erro) {

                        JOptionPane.showMessageDialog(
                                frame,
                                erro.getMessage(),
                                "Erro na calculadora",
                                JOptionPane.ERROR_MESSAGE
                        );

                        input.setText("0");

                    } catch (Exception erro) {

                        JOptionPane.showMessageDialog(
                                frame,
                                "Ocorreu um erro inesperado.",
                                "Erro",
                                JOptionPane.ERROR_MESSAGE
                        );

                        input.setText("0");
                    }
                });
            }

            if (texto.equals("C")) {

                button.addActionListener(e -> {
                    input.setText("0");
                    numero1[0] = 0;
                    operacao[0] = "";
                });
            }

            if (texto.equals("←")) {

                button.addActionListener(e -> {

                    String textoAtual = input.getText();

                    if (textoAtual.length() > 1) {
                        input.setText(textoAtual.substring(0, textoAtual.length() - 1));
                    } else {
                        input.setText("0");
                    }
                });
            }

            if (texto.equals("+/-")) {

                button.addActionListener(e -> {

                    try {
                        double valor = converterEntrada(input.getText());
                        valor = valor * -1;

                        input.setText(formatarResultado(valor));

                    } catch (CalculadoraException erro) {
                        JOptionPane.showMessageDialog(
                                frame,
                                erro.getMessage(),
                                "Entrada inválida",
                                JOptionPane.ERROR_MESSAGE
                        );

                        input.setText("0");
                    }
                });
            }

            if (texto.equals("?")) {

                button.addActionListener(e -> {

                    JOptionPane.showMessageDialog(
                            frame,
                            "Super Calculadora Cristã\n\n" +
                                    "Recursos:\n" +
                                    "- Soma\n" +
                                    "- Subtração\n" +
                                    "- Multiplicação\n" +
                                    "- Divisão\n" +
                                    "Use com sabedoria.\n" +
                                    "Não digite 666.\n" +
                                    "Não divida por zero.\n\n" +
                                    "Deus abençoe seus cálculos 🙏",
                            "Ajuda",
                            JOptionPane.INFORMATION_MESSAGE
                    );
                });
            }

            buttonsPanel.add(button);
        }

        mainPanel.add(input, BorderLayout.NORTH);
        mainPanel.add(buttonsPanel, BorderLayout.CENTER);

        frame.add(mainPanel);
        frame.setVisible(true);
    }

    public static double converterEntrada(String valor) throws CalculadoraException {

        try {
            return Double.parseDouble(valor);

        } catch (NumberFormatException erro) {
            throw new CalculadoraException(
                    "Entrada inválida.\nDigite apenas números válidos."
            );
        }
    }

    public static double calcular(double numero1, double numero2, String operacao)
            throws CalculadoraException {

        if (operacao == null || operacao.isEmpty()) {
            throw new CalculadoraException("Escolha uma operação antes de calcular.");
        }

        switch (operacao) {

            case "+":
                return numero1 + numero2;

            case "-":
                return numero1 - numero2;

            case "*":
                return numero1 * numero2;

            case "/":
                if (numero2 == 0) {
                    throw new CalculadoraException(
                            "Não é possível dividir por zero.\nNem com muita fé 😅"
                    );
                }

                return numero1 / numero2;

            default:
                throw new CalculadoraException("Operação inválida.");
        }
    }

    public static void verificarEasterEggs(JFrame frame, JTextField input, String valor)
            throws CalculadoraException {

        if (valor.equals("666")) {

            JOptionPane.showMessageDialog(
                    frame,
                    "Essa calculadora é cristã!\nO sangue de Jesus tem poder 🙏",
                    "Alerta espiritual",
                    JOptionPane.WARNING_MESSAGE
            );

            throw new CalculadoraException("Valor bloqueado pela calculadora cristã.");
        }

        if (valor.equals("777")) {

            JOptionPane.showMessageDialog(
                    frame,
                    "Número abençoado detectado ✨",
                    "Aleluia",
                    JOptionPane.INFORMATION_MESSAGE
            );

            input.setText("777");
        }

        if (valor.equals("171")) {

            JOptionPane.showMessageDialog(
                    frame,
                    "Golpe detectado!\nA Receita Federal está observando 👀",
                    "Sistema Anti-Golpe",
                    JOptionPane.WARNING_MESSAGE
            );
        }
    }

    public static String formatarResultado(double resultado) {

        if (resultado == (int) resultado) {
            return String.valueOf((int) resultado);
        }

        return String.valueOf(resultado);
    }
}
