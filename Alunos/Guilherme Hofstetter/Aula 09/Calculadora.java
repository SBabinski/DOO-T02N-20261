import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

public class Calculadora {

    private JFrame frame;
    private JPanel painelPrincipal;

    private static final Color FUNDO      = new Color(0x1C, 0x1C, 0x24);
    private static final Color DISPLAY_BG = new Color(0x12, 0x12, 0x18);
    private static final Color BTN_NUM    = new Color(0x32, 0x34, 0x46);
    private static final Color BTN_OP     = new Color(0x50, 0x32, 0xB4);
    private static final Color BTN_IGUAL  = new Color(0x78, 0x46, 0xFF);
    private static final Color BTN_CLEAR  = new Color(0xBE, 0x37, 0x4B);
    private static final Color TEXTO      = new Color(0xEB, 0xEB, 0xFF);
    private static final Color SUBTEXTO   = new Color(0x8C, 0x82, 0xAF);
    private static final Color ERRO       = new Color(0xFF, 0x55, 0x69);
    private static final Color SUCESSO    = new Color(0x5A, 0xD2, 0x96);

    private StringBuilder buffer   = new StringBuilder();
    private double  primeiroNum    = 0;
    private String  operacao       = null;
    private boolean esperaSegundo  = false;
    private boolean emErro         = false;

    private JLabel labelExpressao;
    private JLabel labelDisplay;

    private static final String FONTE = "Dialog";

    public Calculadora() {
        criarJanela();
        criarInterface();
        frame.setVisible(true);
    }

    private void criarJanela() {
        frame = new JFrame("Calculadora");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(360, 560);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.getContentPane().setBackground(FUNDO);
        frame.setLayout(new BorderLayout());
    }

    private void criarInterface() {
        painelPrincipal = new JPanel(new BorderLayout(0, 10));
        painelPrincipal.setBackground(FUNDO);
        painelPrincipal.setBorder(BorderFactory.createEmptyBorder(12, 12, 12, 12));
        painelPrincipal.add(criarDisplay(), BorderLayout.NORTH);
        painelPrincipal.add(criarBotoes(), BorderLayout.CENTER);
        frame.add(painelPrincipal);
    }

    private JPanel criarDisplay() {
        JPanel painel = new JPanel();
        painel.setLayout(new BoxLayout(painel, BoxLayout.Y_AXIS));
        painel.setBackground(DISPLAY_BG);
        painel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(0x37, 0x32, 0x5F), 1),
            BorderFactory.createEmptyBorder(10, 14, 10, 14)
        ));
        painel.setPreferredSize(new Dimension(336, 100));

        labelExpressao = new JLabel(" ");
        labelExpressao.setFont(new Font(FONTE, Font.PLAIN, 14));
        labelExpressao.setForeground(SUBTEXTO);
        labelExpressao.setHorizontalAlignment(SwingConstants.RIGHT);

        labelDisplay = new JLabel("0");
        labelDisplay.setFont(new Font(FONTE, Font.BOLD, 42));
        labelDisplay.setForeground(TEXTO);
        labelDisplay.setHorizontalAlignment(SwingConstants.RIGHT);

        JPanel linhaExp = new JPanel(new BorderLayout());
        linhaExp.setBackground(DISPLAY_BG);
        linhaExp.add(labelExpressao, BorderLayout.EAST);

        JPanel linhaNum = new JPanel(new BorderLayout());
        linhaNum.setBackground(DISPLAY_BG);
        linhaNum.add(labelDisplay, BorderLayout.EAST);

        painel.add(Box.createVerticalGlue());
        painel.add(linhaExp);
        painel.add(Box.createRigidArea(new Dimension(0, 2)));
        painel.add(linhaNum);
        painel.add(Box.createVerticalGlue());

        return painel;
    }

    private JPanel criarBotoes() {
        JPanel grade = new JPanel(new GridLayout(5, 4, 8, 8));
        grade.setBackground(FUNDO);

        grade.add(btn("AC",  BTN_CLEAR, e -> clicarAC()));
        grade.add(btn("+/-", BTN_NUM,   e -> clicarPlusMinus()));
        grade.add(btn("%",   BTN_NUM,   e -> clicarPorcento()));
        grade.add(btn("/",   BTN_OP,    e -> clicarOp("/")));
        grade.add(btn("7",   BTN_NUM,   e -> clicarDigito("7")));
        grade.add(btn("8",   BTN_NUM,   e -> clicarDigito("8")));
        grade.add(btn("9",   BTN_NUM,   e -> clicarDigito("9")));
        grade.add(btn("x",   BTN_OP,    e -> clicarOp("x")));
        grade.add(btn("4",   BTN_NUM,   e -> clicarDigito("4")));
        grade.add(btn("5",   BTN_NUM,   e -> clicarDigito("5")));
        grade.add(btn("6",   BTN_NUM,   e -> clicarDigito("6")));
        grade.add(btn("-",   BTN_OP,    e -> clicarOp("-")));
        grade.add(btn("1",   BTN_NUM,   e -> clicarDigito("1")));
        grade.add(btn("2",   BTN_NUM,   e -> clicarDigito("2")));
        grade.add(btn("3",   BTN_NUM,   e -> clicarDigito("3")));
        grade.add(btn("+",   BTN_OP,    e -> clicarOp("+")));
        grade.add(btn("0",   BTN_NUM,   e -> clicarDigito("0")));
        grade.add(btn(".",   BTN_NUM,   e -> clicarPonto()));
        grade.add(btn("<",   BTN_NUM,   e -> clicarBackspace()));
        grade.add(btn("=",   BTN_IGUAL, e -> clicarIgual()));

        return grade;
    }

    private JButton btn(String texto, Color cor, ActionListener acao) {
        JButton b = new JButton(texto);
        b.setFont(new Font(FONTE, Font.BOLD, 20));
        b.setForeground(TEXTO);
        b.setBackground(cor);
        b.setFocusPainted(false);
        b.setBorderPainted(false);
        b.setOpaque(true);
        b.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        Color hover = cor.brighter();
        b.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) { b.setBackground(hover); }
            public void mouseExited(MouseEvent e)  { b.setBackground(cor);   }
        });
        b.addActionListener(acao);
        return b;
    }

    private void clicarDigito(String d) {
        if (emErro) resetar();
        if (esperaSegundo) { buffer.setLength(0); esperaSegundo = false; }
        if (buffer.toString().equals("0")) buffer.setLength(0);
        if (buffer.length() < 12) buffer.append(d);
        mostrar(buffer.toString(), TEXTO);
    }

    private void clicarPonto() {
        if (emErro) resetar();
        if (esperaSegundo) { buffer.setLength(0); buffer.append("0"); esperaSegundo = false; }
        if (!buffer.toString().contains(".")) {
            if (buffer.length() == 0) buffer.append("0");
            buffer.append(".");
        }
        mostrar(buffer.toString(), TEXTO);
    }

    private void clicarBackspace() {
        if (emErro) { resetar(); return; }
        if (esperaSegundo) return;
        if (buffer.length() > 0) buffer.deleteCharAt(buffer.length() - 1);
        mostrar(buffer.length() == 0 ? "0" : buffer.toString(), TEXTO);
    }

    private void clicarAC() {
        resetar();
    }

    private void clicarPlusMinus() {
        if (emErro) return;
        try {
            double v = CalculadoraLogica.parseNumero(bufOu0());
            v = -v;
            buffer.setLength(0);
            buffer.append(CalculadoraLogica.formatar(v));
            mostrar(buffer.toString(), TEXTO);
        } catch (CalculadoraException ex) {
            mostrarErro(ex.getMessage());
        }
    }

    private void clicarPorcento() {
        if (emErro) return;
        try {
            double v = CalculadoraLogica.parseNumero(bufOu0());
            v = v / 100.0;
            buffer.setLength(0);
            buffer.append(CalculadoraLogica.formatar(v));
            mostrar(buffer.toString(), TEXTO);
        } catch (CalculadoraException ex) {
            mostrarErro(ex.getMessage());
        }
    }

    private void clicarOp(String op) {
        if (emErro) return;
        try {
            double valorAtual = CalculadoraLogica.parseNumero(bufOu0());
            if (operacao != null && !esperaSegundo) {
                double resultado = CalculadoraLogica.calcular(primeiroNum, valorAtual, operacao);
                primeiroNum = resultado;
                mostrar(CalculadoraLogica.formatar(resultado), TEXTO);
            } else {
                primeiroNum = valorAtual;
            }
            operacao      = op;
            esperaSegundo = true;
            buffer.setLength(0);
            labelExpressao.setText(CalculadoraLogica.formatar(primeiroNum) + " " + op);
        } catch (CalculadoraException ex) {
            mostrarErro(ex.getMessage());
        }
    }

    private void clicarIgual() {
        if (emErro || operacao == null) return;
        try {
            double segundo   = CalculadoraLogica.parseNumero(bufOu0());
            double resultado = CalculadoraLogica.calcular(primeiroNum, segundo, operacao);
            String res       = CalculadoraLogica.formatar(resultado);
            labelExpressao.setText(
                CalculadoraLogica.formatar(primeiroNum) + " " + operacao + " " +
                CalculadoraLogica.formatar(segundo) + " ="
            );
            buffer.setLength(0);
            buffer.append(res);
            mostrar(res, SUCESSO);
            primeiroNum  = resultado;
            operacao      = null;
            esperaSegundo = true;
        } catch (CalculadoraException ex) {
            mostrarErro(ex.getMessage());
        }
    }

    private void mostrar(String texto, Color cor) {
        emErro = false;
        labelDisplay.setForeground(cor);
        int tam = texto.length() > 12 ? 24 : texto.length() > 8 ? 32 : 42;
        labelDisplay.setFont(new Font(FONTE, Font.BOLD, tam));
        labelDisplay.setText(texto);
    }

    private void mostrarErro(String msg) {
        emErro = true;
        labelDisplay.setFont(new Font(FONTE, Font.BOLD, 16));
        labelDisplay.setForeground(ERRO);
        labelDisplay.setText("<html><center>" + msg + "</center></html>");
        labelExpressao.setText("Erro");
        labelExpressao.setForeground(ERRO);
    }

    private void resetar() {
        buffer.setLength(0);
        primeiroNum  = 0;
        operacao      = null;
        esperaSegundo = false;
        emErro        = false;
        labelExpressao.setText(" ");
        labelExpressao.setForeground(SUBTEXTO);
        mostrar("0", TEXTO);
    }

    private String bufOu0() {
        return buffer.length() == 0 ? "0" : buffer.toString();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Calculadora();
            }
        });
    }
}
