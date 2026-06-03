import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;


public class CalculadoraSwing extends JFrame {

    private final CalculadoraLogica logica = new CalculadoraLogica();

    private String operacaoAtual = "";   // +  -  *  /
    private boolean novoNumero = false;  // se o próximo dígito inicia novo número
    private boolean erroAtivo = false;   // impede digitação após erro

    private final JTextField display;
    private final JLabel labelOperacao;
    private final JLabel labelStatus;

    private static final Color COR_FUNDO       = new Color(18, 18, 24);
    private static final Color COR_DISPLAY_BG  = new Color(10, 10, 14);
    private static final Color COR_BTN_NUM     = new Color(38, 38, 52);
    private static final Color COR_BTN_OP      = new Color(80, 60, 180);
    private static final Color COR_BTN_OP_HOV  = new Color(100, 80, 210);
    private static final Color COR_BTN_IGUAL   = new Color(50, 180, 120);
    private static final Color COR_BTN_IGUAL_H = new Color(60, 210, 140);
    private static final Color COR_BTN_CLR     = new Color(180, 50, 70);
    private static final Color COR_BTN_CLR_H   = new Color(210, 70, 90);
    private static final Color COR_TEXTO        = new Color(230, 230, 245);
    private static final Color COR_ERRO         = new Color(255, 100, 100);
    private static final Color COR_OK           = new Color(100, 200, 140);


    public CalculadoraSwing() {
        super("Calculadora");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        getContentPane().setBackground(COR_FUNDO);
        setLayout(new BorderLayout(0, 0));

        JPanel painelDisplay = new JPanel(new BorderLayout(4, 4));
        painelDisplay.setBackground(COR_DISPLAY_BG);
        painelDisplay.setBorder(new CompoundBorder(
                new MatteBorder(0, 0, 2, 0, new Color(80, 60, 180)),
                new EmptyBorder(12, 16, 12, 16)));

        labelOperacao = new JLabel(" ");
        labelOperacao.setFont(new Font("Monospaced", Font.PLAIN, 13));
        labelOperacao.setForeground(new Color(130, 120, 200));
        labelOperacao.setHorizontalAlignment(SwingConstants.RIGHT);

        display = new JTextField("0");
        display.setFont(new Font("Monospaced", Font.BOLD, 38));
        display.setForeground(COR_TEXTO);
        display.setBackground(COR_DISPLAY_BG);
        display.setHorizontalAlignment(JTextField.RIGHT);
        display.setBorder(BorderFactory.createEmptyBorder());
        display.setEditable(true);
        display.setCaretColor(COR_TEXTO);

        display.addActionListener(e -> calcular());

        display.addKeyListener(new KeyAdapter() {
            @Override public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!Character.isDigit(c) && c != '.' && c != '-'
                        && c != KeyEvent.VK_BACK_SPACE && c != KeyEvent.VK_DELETE) {
                    e.consume();
                }
                if (erroAtivo) { limpar(); erroAtivo = false; }
            }
        });

        labelStatus = new JLabel(" ");
        labelStatus.setFont(new Font("SansSerif", Font.PLAIN, 11));
        labelStatus.setForeground(COR_OK);
        labelStatus.setHorizontalAlignment(SwingConstants.RIGHT);

        painelDisplay.add(labelOperacao, BorderLayout.NORTH);
        painelDisplay.add(display,       BorderLayout.CENTER);
        painelDisplay.add(labelStatus,   BorderLayout.SOUTH);

        JPanel painelBotoes = new JPanel(new GridLayout(5, 4, 8, 8));
        painelBotoes.setBackground(COR_FUNDO);
        painelBotoes.setBorder(new EmptyBorder(14, 14, 14, 14));

        painelBotoes.add(criarBotao("C",   COR_BTN_CLR,  COR_BTN_CLR_H,  e -> limpar()));
        painelBotoes.add(criarBotao("⌫",  COR_BTN_NUM,  hover(COR_BTN_NUM), e -> backspace()));
        painelBotoes.add(criarBotao("±",   COR_BTN_NUM,  hover(COR_BTN_NUM), e -> inverterSinal()));
        painelBotoes.add(criarBotao("÷",   COR_BTN_OP,   COR_BTN_OP_HOV, e -> selecionarOp("/")));

        painelBotoes.add(criarBotao("7",   COR_BTN_NUM,  hover(COR_BTN_NUM), e -> digitarNumero("7")));
        painelBotoes.add(criarBotao("8",   COR_BTN_NUM,  hover(COR_BTN_NUM), e -> digitarNumero("8")));
        painelBotoes.add(criarBotao("9",   COR_BTN_NUM,  hover(COR_BTN_NUM), e -> digitarNumero("9")));
        painelBotoes.add(criarBotao("×",   COR_BTN_OP,   COR_BTN_OP_HOV, e -> selecionarOp("*")));

        painelBotoes.add(criarBotao("4",   COR_BTN_NUM,  hover(COR_BTN_NUM), e -> digitarNumero("4")));
        painelBotoes.add(criarBotao("5",   COR_BTN_NUM,  hover(COR_BTN_NUM), e -> digitarNumero("5")));
        painelBotoes.add(criarBotao("6",   COR_BTN_NUM,  hover(COR_BTN_NUM), e -> digitarNumero("6")));
        painelBotoes.add(criarBotao("−",   COR_BTN_OP,   COR_BTN_OP_HOV, e -> selecionarOp("-")));

        painelBotoes.add(criarBotao("1",   COR_BTN_NUM,  hover(COR_BTN_NUM), e -> digitarNumero("1")));
        painelBotoes.add(criarBotao("2",   COR_BTN_NUM,  hover(COR_BTN_NUM), e -> digitarNumero("2")));
        painelBotoes.add(criarBotao("3",   COR_BTN_NUM,  hover(COR_BTN_NUM), e -> digitarNumero("3")));
        painelBotoes.add(criarBotao("+",   COR_BTN_OP,   COR_BTN_OP_HOV, e -> selecionarOp("+")));

        painelBotoes.add(criarBotao("0",   COR_BTN_NUM,  hover(COR_BTN_NUM), e -> digitarNumero("0")));
        painelBotoes.add(criarBotao(".",   COR_BTN_NUM,  hover(COR_BTN_NUM), e -> digitarPonto()));
        painelBotoes.add(criarBotao("00",  COR_BTN_NUM,  hover(COR_BTN_NUM), e -> digitarNumero("00")));
        painelBotoes.add(criarBotao("=",   COR_BTN_IGUAL, COR_BTN_IGUAL_H, e -> calcular()));

        add(painelDisplay, BorderLayout.NORTH);
        add(painelBotoes,  BorderLayout.CENTER);

        KeyboardFocusManager.getCurrentKeyboardFocusManager()
                .addKeyEventDispatcher(ke -> {
                    if (ke.getID() == KeyEvent.KEY_PRESSED) {
                        char c = ke.getKeyChar();
                        if (Character.isDigit(c))            digitarNumero(String.valueOf(c));
                        else if (c == '+')                   selecionarOp("+");
                        else if (c == '-')                   selecionarOp("-");
                        else if (c == '*')                   selecionarOp("*");
                        else if (c == '/')                   selecionarOp("/");
                        else if (c == '.' || c == ',')       digitarPonto();
                        else if (c == '=' || c == '\n')      calcular();
                        else if (ke.getKeyCode() == KeyEvent.VK_ESCAPE) limpar();
                        else if (ke.getKeyCode() == KeyEvent.VK_BACK_SPACE) backspace();
                    }
                    return false;
                });

        pack();
        setMinimumSize(new Dimension(320, 440));
        setLocationRelativeTo(null);
    }


    private void selecionarOp(String op) {
        if (erroAtivo) return;
        operacaoAtual = op;
        novoNumero = true;
        String simbolo = switch (op) {
            case "+" -> "+";  case "-" -> "−";
            case "*" -> "×";  case "/" -> "÷"; default -> op;
        };
        labelOperacao.setText(display.getText() + " " + simbolo);
        setStatus(" ", COR_OK);
    }

    private void digitarNumero(String digito) {
        if (erroAtivo) { limpar(); erroAtivo = false; }
        String atual = display.getText();
        if (novoNumero) {
            display.setText(digito.equals("00") ? "0" : digito);
            novoNumero = false;
        } else {
            display.setText(atual.equals("0") && !digito.equals("00")
                    ? digito : atual + digito);
        }
    }

    private void digitarPonto() {
        if (erroAtivo) { limpar(); erroAtivo = false; }
        if (novoNumero) { display.setText("0."); novoNumero = false; return; }
        if (!display.getText().contains("."))
            display.setText(display.getText() + ".");
    }

    private void backspace() {
        if (erroAtivo) { limpar(); return; }
        String txt = display.getText();
        display.setText(txt.length() <= 1 ? "0" : txt.substring(0, txt.length() - 1));
    }

    private void inverterSinal() {
        if (erroAtivo) return;
        try {
            double v = Double.parseDouble(display.getText());
            display.setText(CalculadoraLogica.formatarResultado(-v));
        } catch (NumberFormatException ignored) {}
    }

    private void limpar() {
        display.setText("0");
        labelOperacao.setText(" ");
        operacaoAtual = "";
        novoNumero = false;
        erroAtivo = false;
        setStatus(" ", COR_OK);
    }

    private void calcular() {
        if (operacaoAtual.isBlank()) return;

        String expr   = labelOperacao.getText().trim();
        String[] partes = expr.split(" ");
        String primeiroOp = partes[0];
        String segundoOp  = display.getText();

        try {
            double resultado = logica.calcular(primeiroOp, segundoOp, operacaoAtual);
            String resultadoFmt = CalculadoraLogica.formatarResultado(resultado);
            display.setText(resultadoFmt);
            labelOperacao.setText(primeiroOp + " " + simboloVisual(operacaoAtual)
                    + " " + segundoOp + " =");
            setStatus("Resultado OK", COR_OK);
            operacaoAtual = "";
            novoNumero = true;

        } catch (CalculadoraException e) {
            display.setText("Erro");
            setStatus(e.getMensagemAmigavel(), COR_ERRO);
            erroAtivo = true;

            System.err.println("[CalculadoraException] Tipo: " + e.getTipoErro()
                    + " | Detalhe: " + e.getMessage());
        }
    }


    private void setStatus(String msg, Color cor) {
        labelStatus.setText(msg);
        labelStatus.setForeground(cor);
    }

    private String simboloVisual(String op) {
        return switch (op) {
            case "+" -> "+";  case "-" -> "−";
            case "*" -> "×";  case "/" -> "÷"; default -> op;
        };
    }

    private Color hover(Color base) {
        return base.brighter();
    }

    private JButton criarBotao(String rotulo, Color cor, Color corHover,
                                ActionListener acao) {
        JButton btn = new JButton(rotulo);
        btn.setFont(new Font("Monospaced", Font.BOLD, 17));
        btn.setForeground(COR_TEXTO);
        btn.setBackground(cor);
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setOpaque(true);
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btn.setBorder(new EmptyBorder(10, 6, 10, 6));

        btn.addMouseListener(new MouseAdapter() {
            @Override public void mouseEntered(MouseEvent e) { btn.setBackground(corHover); }
            @Override public void mouseExited (MouseEvent e) { btn.setBackground(cor); }
            @Override public void mousePressed(MouseEvent e) {
                btn.setBackground(corHover.darker());
            }
            @Override public void mouseReleased(MouseEvent e) {
                btn.setBackground(corHover);
            }
        });

        btn.addActionListener(acao);
        return btn;
    }


    public static void main(String[] args) {
        // Ativa look-and-feel do sistema (opcional; mantém consistência no SO)
        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (Exception ignored) {}

        SwingUtilities.invokeLater(() -> new CalculadoraSwing().setVisible(true));
    }
}
