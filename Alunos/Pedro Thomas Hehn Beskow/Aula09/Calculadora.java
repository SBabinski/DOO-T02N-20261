import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Interface gráfica da Calculadora usando Java Swing.
 * Aplica tratamento de exceções com CalculadoraException.
 */
public class Calculadora extends JFrame {

    // ── Paleta de cores ──────────────────────────────────────────────────────
    private static final Color COR_FUNDO        = new Color(18,  18,  18);
    private static final Color COR_DISPLAY       = new Color(30,  30,  30);
    private static final Color COR_BTN_NUMERO    = new Color(50,  50,  50);
    private static final Color COR_BTN_OP        = new Color(255, 149,   0);
    private static final Color COR_BTN_ACAO      = new Color(70,  70,  70);
    private static final Color COR_BTN_IGUAL     = new Color(255, 149,   0);
    private static final Color COR_HOVER_NUMERO  = new Color(80,  80,  80);
    private static final Color COR_HOVER_OP      = new Color(255, 179,  64);
    private static final Color COR_HOVER_ACAO    = new Color(100, 100, 100);
    private static final Color COR_ERRO          = new Color(255,  69,  58);
    private static final Color COR_TEXTO_DISPLAY = Color.WHITE;
    private static final Color COR_TEXTO_BTN     = Color.WHITE;

    // ── Componentes visuais ───────────────────────────────────────────────────
    private JLabel   labelExpressao;  // linha superior: expressão em curso
    private JLabel   labelDisplay;    // linha inferior: número atual / resultado
    private JLabel   labelStatus;     // mensagem de erro / status

    // ── Estado interno ────────────────────────────────────────────────────────
    private final CalculadoraLogica logica = new CalculadoraLogica();

    private String  textoAtual    = "0";
    private double  primeiroNum   = 0;
    private String  operador      = "";
    private boolean novoNumero    = true;   // próximo dígito inicia novo número
    private boolean temResultado  = false;  // ultimo pressionar foi "="

    // ─────────────────────────────────────────────────────────────────────────

    public Calculadora() {
        configurarJanela();
        construirUI();
        setVisible(true);
    }

    // ── Configuração da janela ────────────────────────────────────────────────

    private void configurarJanela() {
        setTitle("Calculadora");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        getContentPane().setBackground(COR_FUNDO);
    }

    // ── Construção da interface ───────────────────────────────────────────────

    private void construirUI() {
        setLayout(new BorderLayout(0, 0));

        add(criarPainelDisplay(), BorderLayout.NORTH);
        add(criarPainelBotoes(),  BorderLayout.CENTER);

        pack();
        setMinimumSize(getSize());
        setLocationRelativeTo(null);
    }

    private JPanel criarPainelDisplay() {
        JPanel painel = new JPanel();
        painel.setLayout(new BoxLayout(painel, BoxLayout.Y_AXIS));
        painel.setBackground(COR_DISPLAY);
        painel.setBorder(new EmptyBorder(20, 20, 12, 20));

        // Expressão (p.ex. "12 + ")
        labelExpressao = new JLabel(" ");
        labelExpressao.setFont(new Font("SF Pro Display", Font.PLAIN, 16));
        labelExpressao.setForeground(new Color(180, 180, 180));
        labelExpressao.setAlignmentX(Component.RIGHT_ALIGNMENT);

        // Número / resultado principal
        labelDisplay = new JLabel("0");
        labelDisplay.setFont(new Font("SF Pro Display", Font.BOLD, 48));
        labelDisplay.setForeground(COR_TEXTO_DISPLAY);
        labelDisplay.setAlignmentX(Component.RIGHT_ALIGNMENT);

        // Mensagem de status / erro
        labelStatus = new JLabel(" ");
        labelStatus.setFont(new Font("SF Pro Display", Font.PLAIN, 13));
        labelStatus.setForeground(COR_ERRO);
        labelStatus.setAlignmentX(Component.RIGHT_ALIGNMENT);

        painel.add(labelExpressao);
        painel.add(Box.createVerticalStrut(4));
        painel.add(labelDisplay);
        painel.add(Box.createVerticalStrut(6));
        painel.add(labelStatus);

        return painel;
    }

    private JPanel criarPainelBotoes() {
        JPanel painel = new JPanel(new GridLayout(5, 4, 2, 2));
        painel.setBackground(COR_FUNDO);
        painel.setBorder(new EmptyBorder(2, 4, 8, 4));

        // Linha 1 – ações especiais
        painel.add(criarBotao("AC",  COR_BTN_ACAO, COR_HOVER_ACAO));
        painel.add(criarBotao("+/-", COR_BTN_ACAO, COR_HOVER_ACAO));
        painel.add(criarBotao("%",   COR_BTN_ACAO, COR_HOVER_ACAO));
        painel.add(criarBotao("÷",   COR_BTN_OP,   COR_HOVER_OP));

        // Linha 2
        painel.add(criarBotao("7", COR_BTN_NUMERO, COR_HOVER_NUMERO));
        painel.add(criarBotao("8", COR_BTN_NUMERO, COR_HOVER_NUMERO));
        painel.add(criarBotao("9", COR_BTN_NUMERO, COR_HOVER_NUMERO));
        painel.add(criarBotao("×", COR_BTN_OP,     COR_HOVER_OP));

        // Linha 3
        painel.add(criarBotao("4", COR_BTN_NUMERO, COR_HOVER_NUMERO));
        painel.add(criarBotao("5", COR_BTN_NUMERO, COR_HOVER_NUMERO));
        painel.add(criarBotao("6", COR_BTN_NUMERO, COR_HOVER_NUMERO));
        painel.add(criarBotao("-", COR_BTN_OP,     COR_HOVER_OP));

        // Linha 4
        painel.add(criarBotao("1", COR_BTN_NUMERO, COR_HOVER_NUMERO));
        painel.add(criarBotao("2", COR_BTN_NUMERO, COR_HOVER_NUMERO));
        painel.add(criarBotao("3", COR_BTN_NUMERO, COR_HOVER_NUMERO));
        painel.add(criarBotao("+", COR_BTN_OP,     COR_HOVER_OP));

        // Linha 5 – zero duplo, vírgula, igual
        painel.add(criarBotao("0", COR_BTN_NUMERO, COR_HOVER_NUMERO));
        painel.add(criarBotao("00", COR_BTN_NUMERO, COR_HOVER_NUMERO));
        painel.add(criarBotao(",",  COR_BTN_NUMERO, COR_HOVER_NUMERO));
        painel.add(criarBotao("=",  COR_BTN_IGUAL,  COR_HOVER_OP));

        return painel;
    }

    // ── Fábrica de botões ─────────────────────────────────────────────────────

    private JButton criarBotao(String texto, Color corFundo, Color corHover) {
        JButton btn = new JButton(texto);
        btn.setFont(new Font("SF Pro Display", Font.BOLD, 22));
        btn.setForeground(COR_TEXTO_BTN);
        btn.setBackground(corFundo);
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setOpaque(true);
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btn.setPreferredSize(new Dimension(80, 72));

        // Efeitos de hover
        btn.addMouseListener(new MouseAdapter() {
            @Override public void mouseEntered(MouseEvent e) { btn.setBackground(corHover); }
            @Override public void mouseExited(MouseEvent e)  { btn.setBackground(corFundo); }
            @Override public void mousePressed(MouseEvent e) {
                btn.setBackground(corFundo.darker());
            }
            @Override public void mouseReleased(MouseEvent e){ btn.setBackground(corHover); }
        });

        btn.addActionListener(e -> processarClique(texto));
        return btn;
    }

    // ── Processamento de cliques ──────────────────────────────────────────────

    private void processarClique(String valor) {
        limparStatus();

        switch (valor) {
            case "AC"  -> resetar();
            case "+/-" -> negarNumero();
            case "%"   -> calcularPorcentagem();
            case "="   -> calcularResultado();
            case ","   -> adicionarDecimal();
            case "+", "-", "×", "÷" -> selecionarOperador(valor);
            default    -> entrarDigito(valor);
        }
    }

    // ── Ações dos botões ──────────────────────────────────────────────────────

    private void entrarDigito(String digito) {
        if (temResultado) { resetar(); }

        if (novoNumero) {
            textoAtual = digito.equals("00") ? "0" : digito;
            novoNumero = false;
        } else {
            // Evita zeros à esquerda desnecessários
            if (textoAtual.equals("0") && !digito.equals("00")) {
                textoAtual = digito;
            } else if (textoAtual.length() < 14) {
                textoAtual += digito;
            }
        }
        atualizarDisplay();
    }

    private void adicionarDecimal() {
        if (novoNumero) {
            textoAtual = "0,";
            novoNumero = false;
        } else if (!textoAtual.contains(",")) {
            textoAtual += ",";
        }
        atualizarDisplay();
    }

    private void selecionarOperador(String op) {
        // Encadear: se já há operador e número pendente, calcular antes
        if (!operador.isEmpty() && !novoNumero) {
            calcularResultado();
            if (temResultado) {
                temResultado = false;
            }
        }

        try {
            primeiroNum = logica.parseNumero(textoAtual.replace(",", "."));
            operador    = op;
            novoNumero  = true;
            temResultado = false;
            labelExpressao.setText(logica.formatarResultado(primeiroNum) + " " + op);
        } catch (CalculadoraException ex) {
            exibirErro(ex);
        }
    }

    private void calcularResultado() {
        if (operador.isEmpty()) return;

        try {
            double segundoNum = logica.parseNumero(textoAtual.replace(",", "."));
            double resultado  = logica.calcular(primeiroNum, segundoNum, operador);

            labelExpressao.setText(
                logica.formatarResultado(primeiroNum)
                + " " + operador + " "
                + logica.formatarResultado(segundoNum)
                + " ="
            );

            textoAtual   = logica.formatarResultado(resultado).replace(".", ",");
            operador     = "";
            novoNumero   = true;
            temResultado = true;
            atualizarDisplay();

        } catch (CalculadoraException ex) {
            exibirErro(ex);
        }
    }

    private void negarNumero() {
        try {
            double num = logica.parseNumero(textoAtual.replace(",", "."));
            num = -num;
            textoAtual = logica.formatarResultado(num).replace(".", ",");
            atualizarDisplay();
        } catch (CalculadoraException ex) {
            exibirErro(ex);
        }
    }

    private void calcularPorcentagem() {
        try {
            double num = logica.parseNumero(textoAtual.replace(",", "."));
            num = num / 100.0;
            textoAtual = logica.formatarResultado(num).replace(".", ",");
            atualizarDisplay();
        } catch (CalculadoraException ex) {
            exibirErro(ex);
        }
    }

    private void resetar() {
        textoAtual   = "0";
        primeiroNum  = 0;
        operador     = "";
        novoNumero   = true;
        temResultado = false;
        labelExpressao.setText(" ");
        atualizarDisplay();
        limparStatus();
    }

    // ── Atualização visual ────────────────────────────────────────────────────

    private void atualizarDisplay() {
        // Ajusta tamanho da fonte conforme comprimento
        int tamanho = textoAtual.length() > 10 ? 32
                    : textoAtual.length() > 7  ? 40
                    : 48;
        labelDisplay.setFont(new Font("SF Pro Display", Font.BOLD, tamanho));
        labelDisplay.setForeground(COR_TEXTO_DISPLAY);
        labelDisplay.setText(textoAtual);
    }

    private void exibirErro(CalculadoraException ex) {
        labelDisplay.setForeground(COR_ERRO);
        labelDisplay.setFont(new Font("SF Pro Display", Font.BOLD, 28));
        labelDisplay.setText("Erro");
        labelStatus.setText(ex.getIconeErro() + "  " + ex.getMessage());

        // Log técnico no console
        System.err.println("[CalculadoraException] " + ex);

        // Prepara reset parcial para próxima interação
        textoAtual   = "0";
        novoNumero   = true;
        temResultado = false;
        operador     = "";
    }

    private void limparStatus() {
        labelStatus.setText(" ");
        labelDisplay.setForeground(COR_TEXTO_DISPLAY);
    }

    // ── Ponto de entrada ──────────────────────────────────────────────────────

    public static void main(String[] args) {
        // Aplica look-and-feel do sistema operacional
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            System.err.println("Look-and-feel não disponível: " + e.getMessage());
        }

        SwingUtilities.invokeLater(Calculadora::new);
    }
}
