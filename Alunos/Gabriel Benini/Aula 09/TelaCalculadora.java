import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaCalculadora extends JFrame {

    private JTextField display;

    private String numero1 = "";
    private String numero2 = "";
    private String operador = "";

    public TelaCalculadora() {

        configurarJanela();
        criarComponentes();

        setVisible(true);
    }

    private void configurarJanela() {

        try {
            UIManager.setLookAndFeel(
                    UIManager.getSystemLookAndFeelClassName()
            );
        } catch (Exception e) {
            e.printStackTrace();
        }

        setTitle("Calculadora");
        setSize(400, 550);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        getContentPane().setBackground(Color.DARK_GRAY);
    }

    private void criarComponentes() {

        JLabel titulo = new JLabel("Calculadora");

        titulo.setBounds(85, 10, 250, 50);
        titulo.setFont(new Font("Impact", Font.BOLD, 35));
        titulo.setForeground(Color.ORANGE);

        add(titulo);

        display = new JTextField();

        display.setBounds(30, 70, 320, 50);
        display.setFont(new Font("Arial", Font.BOLD, 25));
        display.setEditable(false);

        add(display);

        criarBotoes();
    }

    private void criarBotoes() {

        String[] textos = {
                "1", "2", "3", "/",
                "4", "5", "6", "*",
                "7", "8", "9", "-",
                "C", "0", "=", "+"
        };

        int x = 30;
        int y = 150;

        for (String texto : textos) {

            JButton botao = new JButton(texto);

            botao.setFont(new Font("Impact", Font.BOLD, 22));
            botao.setBounds(x, y, 70, 70);

            botao.setFocusPainted(false);

            botao.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {

                    clicarBotao(botao.getText());
                }
            });

            add(botao);

            x += 80;

            if (x > 270) {

                x = 30;
                y += 80;
            }
        }
    }

    private void clicarBotao(String valor) {

        try {

            if (valor.matches("[0-9]")) {

                adicionarNumero(valor);

            } else if (
                    valor.equals("+")
                            || valor.equals("-")
                            || valor.equals("*")
                            || valor.equals("/")
            ) {

                adicionarOperador(valor);

            } else if (valor.equals("=")) {

                calcularResultado();

            } else if (valor.equals("C")) {

                limpar();
            }

        } catch (EntradaInvalidaException erro) {

            JOptionPane.showMessageDialog(
                    null,
                    erro.getMessage(),
                    "Erro",
                    JOptionPane.ERROR_MESSAGE
            );

        } catch (DivisaoPorZeroException erro) {

            JOptionPane.showMessageDialog(
                    null,
                    erro.getMessage(),
                    "Erro",
                    JOptionPane.ERROR_MESSAGE
            );

        } catch (Exception erro) {

            JOptionPane.showMessageDialog(
                    null,
                    "Erro inesperado.",
                    "Erro",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private void adicionarNumero(String valor) {

        if (operador.isEmpty()) {

            numero1 += valor;
            display.setText(numero1);

        } else {

            numero2 += valor;
            display.setText(numero1 + " " + operador + " " + numero2);
        }
    }

    private void adicionarOperador(String valor)
            throws EntradaInvalidaException,
            DivisaoPorZeroException {

        if (numero1.isEmpty()) {

            throw new EntradaInvalidaException(
                    "Digite um número primeiro."
            );
        }

        if (!numero2.isEmpty()) {

            double n1 = Double.parseDouble(numero1);
            double n2 = Double.parseDouble(numero2);

            double resultado = Operacoes.calcular(
                    n1,
                    n2,
                    operador
            );

            formatarResultado(resultado);

            numero2 = "";
        }

        operador = valor;

        display.setText(numero1 + " " + operador);
    }

    private void calcularResultado()
            throws EntradaInvalidaException,
            DivisaoPorZeroException {

        if (numero1.isEmpty() || numero2.isEmpty()) {

            throw new EntradaInvalidaException(
                    "Operação incompleta."
            );
        }

        double n1 = Double.parseDouble(numero1);
        double n2 = Double.parseDouble(numero2);

        double resultado = Operacoes.calcular(
                n1,
                n2,
                operador
        );

        formatarResultado(resultado);

        numero2 = "";
        operador = "";
    }

    private void formatarResultado(double resultado) {

        if (resultado == (int) resultado) {

            display.setText(String.valueOf((int) resultado));
            numero1 = String.valueOf((int) resultado);

        } else {

            display.setText(String.valueOf(resultado));
            numero1 = String.valueOf(resultado);
        }
    }

    private void limpar() {

        numero1 = "";
        numero2 = "";
        operador = "";

        display.setText("");
    }
}