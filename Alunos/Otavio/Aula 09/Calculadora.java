import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Calculadora extends JFrame {

    private JTextField display;
    private JLabel expressaoDisplay;
    private String entrada = "";
    private String operador = "";
    private double valorAnterior = 0;
    private boolean novaEntrada = true;
    private boolean errou = false;

    public Calculadora() {
        setTitle("Calculadora");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        JPanel painel = new JPanel(new BorderLayout(5, 5));
        painel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel painelDisplay = new JPanel(new BorderLayout());

        expressaoDisplay = new JLabel(" ");
        expressaoDisplay.setHorizontalAlignment(SwingConstants.RIGHT);
        expressaoDisplay.setForeground(Color.GRAY);
        expressaoDisplay.setFont(new Font("Monospaced", Font.PLAIN, 12));

        display = new JTextField("0");
        display.setHorizontalAlignment(JTextField.RIGHT);
        display.setEditable(false);
        display.setFont(new Font("Monospaced", Font.BOLD, 32));
        display.setBorder(BorderFactory.createEtchedBorder());

        painelDisplay.add(expressaoDisplay, BorderLayout.NORTH);
        painelDisplay.add(display, BorderLayout.CENTER);

        JPanel painelBotoes = new JPanel(new GridLayout(5, 4, 5, 5));

        String[][] botoes = {
                {"AC", "+/-", "%", "÷"},
                {"7",  "8",   "9", "×"},
                {"4",  "5",   "6", "−"},
                {"1",  "2",   "3", "+"},
                {"0",  ".",   "⌫", "="}
        };

        for (String[] linha : botoes) {
            for (String texto : linha) {
                painelBotoes.add(criarBotao(texto));
            }
        }

        painel.add(painelDisplay, BorderLayout.NORTH);
        painel.add(painelBotoes, BorderLayout.CENTER);

        setContentPane(painel);
        setSize(300, 420);
        setLocationRelativeTo(null);

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                char c = e.getKeyChar();
                if (Character.isDigit(c)) processar(String.valueOf(c));
                else if (c == '+') processar("+");
                else if (c == '-') processar("−");
                else if (c == '*') processar("×");
                else if (c == '/') processar("÷");
                else if (c == '.' || c == ',') processar(".");
                else if (c == '\n' || c == '=') processar("=");
                else if (c == '\b') processar("⌫");
                else if (c == 27)  processar("AC");
                else if (c == '%') processar("%");
            }
        });
        setFocusable(true);
    }

    private JButton criarBotao(String texto) {
        final Color corBase, corHover, corTexto;

        switch (texto) {
            case "÷": case "×": case "−": case "+": case "=":
                corBase  = new Color(70, 130, 180);
                corHover = new Color(100, 155, 205);
                corTexto = Color.WHITE;
                break;
            case "AC": case "+/-": case "%": case "⌫":
                corBase  = new Color(200, 200, 200);
                corHover = new Color(175, 175, 175);
                corTexto = Color.BLACK;
                break;
            default:
                corBase  = new Color(240, 240, 240);
                corHover = new Color(210, 210, 210);
                corTexto = Color.BLACK;
        }

        JButton btn = new JButton(texto);
        btn.setFont(new Font("Monospaced", Font.BOLD, 16));
        btn.setFocusPainted(false);
        btn.setForeground(corTexto);
        btn.setOpaque(true);
        btn.setBorderPainted(false);
        btn.setBackground(corBase);

        btn.addActionListener(e -> processar(texto));
        return btn;
    }

    private void processar(String cmd) {
        if (errou && !cmd.equals("AC")) return;

        switch (cmd) {
            case "AC":
                entrada = "";
                operador = "";
                valorAnterior = 0;
                novaEntrada = true;
                errou = false;
                display.setText("0");
                expressaoDisplay.setText(" ");
                break;

            case "+/-":
                if (!entrada.isEmpty() && !entrada.equals("0")) {
                    if (entrada.startsWith("-")) entrada = entrada.substring(1);
                    else entrada = "-" + entrada;
                    display.setText(entrada);
                }
                break;

            case "%":
                try {
                    double v = parsearEntrada(entrada);
                    entrada = formatar(v / 100);
                    display.setText(entrada);
                    novaEntrada = false;
                } catch (CalculadoraException ex) {
                    exibirErro(ex.getMessage());
                }
                break;

            case "⌫":
                if (!entrada.isEmpty() && !novaEntrada) {
                    entrada = entrada.length() > 1 ? entrada.substring(0, entrada.length() - 1) : "";
                    display.setText(entrada.isEmpty() ? "0" : entrada);
                }
                break;

            case ".":
                if (novaEntrada) { entrada = "0."; novaEntrada = false; }
                else if (!entrada.contains(".")) entrada += ".";
                display.setText(entrada);
                break;

            case "÷": case "×": case "−": case "+":
                try {
                    if (!entrada.isEmpty()) valorAnterior = parsearEntrada(entrada);
                    operador = cmd;
                    novaEntrada = true;
                    expressaoDisplay.setText(formatar(valorAnterior) + " " + cmd);
                } catch (CalculadoraException ex) {
                    exibirErro(ex.getMessage());
                }
                break;

            case "=":
                try {
                    double b = parsearEntrada(entrada);
                    double resultado = calcular(valorAnterior, b, operador);

                    expressaoDisplay.setText(formatar(valorAnterior) + " " + operador + " " + formatar(b) + " =");
                    entrada = formatar(resultado);
                    display.setText(entrada);
                    valorAnterior = resultado;

                    if (resultado == 67) {
                        JOptionPane.showMessageDialog(
                                this,
                                "20 + 20 + 20 + 7...",
                                "67",
                                JOptionPane.INFORMATION_MESSAGE
                        );
                    }

                } catch (CalculadoraException ex) {
                    exibirErro(ex.getMessage());
                }

                operador = "";
                novaEntrada = true;
                break;

            default:
                if (novaEntrada) { entrada = cmd; novaEntrada = false; }
                else if (entrada.length() < 12) entrada += cmd;
                display.setText(entrada);
        }
    }

    private double parsearEntrada(String valor) throws EntradaInvalidaException {
        if (valor == null || valor.isEmpty()) {
            throw new EntradaInvalidaException("vazio");
        }
        try {
            return Double.parseDouble(valor);
        } catch (NumberFormatException e) {
            throw new EntradaInvalidaException(valor);
        }
    }

    private double calcular(double a, double b, String op) throws CalculadoraException {
        if (op == null || op.isEmpty()) {
            throw new OperacaoInvalidaException();
        }
        switch (op) {
            case "+": return a + b;
            case "−": return a - b;
            case "×": return a * b;
            case "÷":
                if (b == 0) throw new DivisaoPorZeroException();
                return a / b;
            default:
                throw new OperacaoInvalidaException();
        }
    }

    private void exibirErro(String mensagem) {
        display.setText("Erro");
        expressaoDisplay.setText(mensagem);
        errou = true;
    }

    private String formatar(double v) {
        if (v == Math.floor(v) && !Double.isInfinite(v) && Math.abs(v) < 1e12)
            return String.valueOf((long) v);
        return String.format("%.8f", v).replaceAll("0+$", "").replaceAll("\\.$", "");
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignored) {}

        SwingUtilities.invokeLater(() -> {
            Calculadora calc = new Calculadora();
            calc.setVisible(true);
        });
    }
}