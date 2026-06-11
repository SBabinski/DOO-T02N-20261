import javax.swing.*;
import java.awt.*;

public class interfaceCalculadora extends JFrame {

    private JTextField display;
    private JTextArea areaHistorico;
    private Calculadora calculadora;
    private String operadorAtual = " ";
    private double primeiroNumero = 0;
    private boolean novoNumero = true;

    public interfaceCalculadora() {

        calculadora = new Calculadora();

        setTitle("Calculadora");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLayout(new BorderLayout(10, 10));

        display = new JTextField("0");

        display.setFont(new Font("Arial", Font.BOLD, 26));

        display.setHorizontalAlignment(JTextField.RIGHT);

        display.setEditable(false);

        display.setBackground(Color.WHITE);

        add(display, BorderLayout.NORTH);

        JPanel botoesPainel = new JPanel(new GridLayout(4, 4, 5, 5));

        String[] botoes = {
                "7", "8", "9", "÷",
                "4", "5", "6", "×",
                "1", "2", "3", "-",
                "0", "C", "=", "+"
        };

        for (String texto : botoes) {

            JButton btn = new JButton(texto);

            btn.setFont(new Font("Arial", Font.PLAIN, 18));
            btn.addActionListener(e -> acaoBotao(texto));
            botoesPainel.add(btn);
        }

        add(botoesPainel, BorderLayout.CENTER);

        areaHistorico = new JTextArea(6, 20);
        areaHistorico.setEditable(false);
        areaHistorico.setBorder(BorderFactory.createTitledBorder("Historico"));

        add(new JScrollPane(areaHistorico), BorderLayout.SOUTH);

        pack();

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void acaoBotao(String texto) {

        try {

            if ("0123456789".contains(texto)) {

                if (novoNumero) {
                    display.setText(texto);
                    novoNumero = false;
                } else {
                    display.setText(display.getText() + texto);
                }

            } else if (texto.equals("C")) {

                display.setText("0");
                primeiroNumero = 0;
                operadorAtual = " ";
                novoNumero = true;

            } else if (texto.equals("=")) {

                double segundoNumero = Double.parseDouble(display.getText());

                double res = calculadora.calcularOperacoes(
                        primeiroNumero,
                        segundoNumero,
                        operadorAtual
                );

                display.setText(String.valueOf(res));

                areaHistorico.setText(calculadora.getHistoricoFormatado());

                novoNumero = true;

            } else {

                primeiroNumero = Double.parseDouble(display.getText());
                operadorAtual = texto;
                novoNumero = true;
            }

        } catch (NumberFormatException ex) {

            JOptionPane.showMessageDialog(this, "Entrada invalida");

        } catch (CalculadoraException ex) {

            JOptionPane.showMessageDialog(
                    this,
                    ex.getMessage(),
                    "Erro de calculo",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> new interfaceCalculadora());
    }
}