import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Aula09 extends JFrame implements ActionListener {

    private JTextField campoNumero1;
    private JTextField campoNumero2;
    private JTextField campoResultado;
    private JLabel labelStatus;

    private JButton btnSomar;
    private JButton btnSubtrair;
    private JButton btnMultiplicar;
    private JButton btnDividir;
    private JButton btnLimpar;

    private JButton btn0, btn1, btn2, btn3, btn4;
    private JButton btn5, btn6, btn7, btn8, btn9;
    private JButton btnPonto;

    private CalculadoraLogica logica;
    private int campoAtivo;

    public Aula09() {
        logica = new CalculadoraLogica();
        campoAtivo = 1;

        setTitle("Calculadora");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        criarTela();

        setVisible(true);
    }

    private void criarTela() {
        setLayout(new BorderLayout(5, 5));

        JPanel painelTopo = new JPanel(new GridLayout(5, 1, 5, 5));
        painelTopo.setBackground(Color.WHITE);

        JLabel lblNum1 = new JLabel("  Numero 1:");
        lblNum1.setFont(new Font("Arial", Font.PLAIN, 14));

        campoNumero1 = new JTextField();
        campoNumero1.setFont(new Font("Arial", Font.PLAIN, 16));
        campoNumero1.setEditable(false);
        campoNumero1.setBackground(Color.WHITE);
        campoNumero1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent e) {
                campoAtivo = 1;
                labelStatus.setText("Editando: Numero 1");
            }
        });

        JLabel lblNum2 = new JLabel("  Numero 2:");
        lblNum2.setFont(new Font("Arial", Font.PLAIN, 14));

        campoNumero2 = new JTextField();
        campoNumero2.setFont(new Font("Arial", Font.PLAIN, 16));
        campoNumero2.setEditable(false);
        campoNumero2.setBackground(new Color(240, 240, 240));
        campoNumero2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent e) {
                campoAtivo = 2;
                labelStatus.setText("Editando: Numero 2");
            }
        });

        JLabel lblResultado = new JLabel("  Resultado:");
        lblResultado.setFont(new Font("Arial", Font.PLAIN, 14));

        campoResultado = new JTextField();
        campoResultado.setFont(new Font("Arial", Font.BOLD, 18));
        campoResultado.setEditable(false);
        campoResultado.setBackground(Color.WHITE);

        painelTopo.add(lblNum1);
        painelTopo.add(campoNumero1);
        painelTopo.add(lblNum2);
        painelTopo.add(campoNumero2);
        painelTopo.add(lblResultado);

        add(painelTopo, BorderLayout.NORTH);

        JPanel painelDigitos = new JPanel(new GridLayout(4, 3, 5, 5));
        painelDigitos.setBackground(Color.LIGHT_GRAY);

        btn7 = new JButton("7");
        btn8 = new JButton("8");
        btn9 = new JButton("9");
        btn4 = new JButton("4");
        btn5 = new JButton("5");
        btn6 = new JButton("6");
        btn1 = new JButton("1");
        btn2 = new JButton("2");
        btn3 = new JButton("3");
        btn0 = new JButton("0");
        btnPonto = new JButton(".");
        btnLimpar = new JButton("C");

        JButton[] botoesDigito = {btn7, btn8, btn9, btn4, btn5, btn6,
                                   btn1, btn2, btn3, btn0, btnPonto, btnLimpar};

        for (int i = 0; i < botoesDigito.length; i++) {
            botoesDigito[i].setFont(new Font("Arial", Font.BOLD, 16));
            botoesDigito[i].addActionListener(this);
            painelDigitos.add(botoesDigito[i]);
        }

        btnLimpar.setBackground(new Color(255, 100, 100));
        btnLimpar.setForeground(Color.WHITE);

        add(painelDigitos, BorderLayout.CENTER);

        JPanel painelOperacoes = new JPanel(new GridLayout(4, 1, 5, 5));
        painelOperacoes.setBackground(Color.LIGHT_GRAY);

        btnSomar       = new JButton("+");
        btnSubtrair    = new JButton("-");
        btnMultiplicar = new JButton("x");
        btnDividir     = new JButton("/");

        JButton[] botoesOp = {btnSomar, btnSubtrair, btnMultiplicar, btnDividir};
        for (int i = 0; i < botoesOp.length; i++) {
            botoesOp[i].setFont(new Font("Arial", Font.BOLD, 18));
            botoesOp[i].setBackground(new Color(100, 160, 255));
            botoesOp[i].setForeground(Color.WHITE);
            botoesOp[i].addActionListener(this);
            painelOperacoes.add(botoesOp[i]);
        }

        add(painelOperacoes, BorderLayout.EAST);

        JPanel painelBase = new JPanel(new GridLayout(2, 1, 5, 5));
        painelBase.setBackground(Color.WHITE);

        campoResultado.setHorizontalAlignment(JTextField.RIGHT);
        painelBase.add(campoResultado);

        labelStatus = new JLabel("  Clique em 'Numero 1' para comecar.");
        labelStatus.setFont(new Font("Arial", Font.ITALIC, 12));
        labelStatus.setForeground(Color.DARK_GRAY);
        painelBase.add(labelStatus);

        add(painelBase, BorderLayout.SOUTH);
    }

    public void actionPerformed(ActionEvent e) {
        Object fonte = e.getSource();

        if (fonte == btnLimpar) {
            campoNumero1.setText("");
            campoNumero2.setText("");
            campoResultado.setText("");
            campoResultado.setBackground(Color.WHITE);
            campoAtivo = 1;
            labelStatus.setText("  Calculadora limpa. Digite o Numero 1.");
            return;
        }

        if (fonte == btn0) { digitarNoCampo("0"); return; }
        if (fonte == btn1) { digitarNoCampo("1"); return; }
        if (fonte == btn2) { digitarNoCampo("2"); return; }
        if (fonte == btn3) { digitarNoCampo("3"); return; }
        if (fonte == btn4) { digitarNoCampo("4"); return; }
        if (fonte == btn5) { digitarNoCampo("5"); return; }
        if (fonte == btn6) { digitarNoCampo("6"); return; }
        if (fonte == btn7) { digitarNoCampo("7"); return; }
        if (fonte == btn8) { digitarNoCampo("8"); return; }
        if (fonte == btn9) { digitarNoCampo("9"); return; }
        if (fonte == btnPonto) { digitarNoCampo("."); return; }

        if (fonte == btnSomar)       { calcular("+"); return; }
        if (fonte == btnSubtrair)    { calcular("-"); return; }
        if (fonte == btnMultiplicar) { calcular("x"); return; }
        if (fonte == btnDividir)     { calcular("/"); return; }
    }

    private void digitarNoCampo(String caractere) {
        if (campoAtivo == 1) {
            campoNumero1.setText(campoNumero1.getText() + caractere);
            labelStatus.setText("  Editando: Numero 1");
        } else {
            campoNumero2.setText(campoNumero2.getText() + caractere);
            labelStatus.setText("  Editando: Numero 2");
        }
    }

    private void calcular(String operacao) {
        try {
            double n1 = logica.validarEntrada(campoNumero1.getText(), "Numero 1");
            double n2 = logica.validarEntrada(campoNumero2.getText(), "Numero 2");

            double resultado = 0;

            if (operacao.equals("+")) {
                resultado = logica.somar(n1, n2);
            } else if (operacao.equals("-")) {
                resultado = logica.subtrair(n1, n2);
            } else if (operacao.equals("x")) {
                resultado = logica.multiplicar(n1, n2);
            } else if (operacao.equals("/")) {
                resultado = logica.dividir(n1, n2);
            }

            campoResultado.setText(String.valueOf(resultado));
            campoResultado.setBackground(Color.WHITE);
            labelStatus.setText("  Resultado calculado com sucesso!");

        } catch (CalculadoraException ex) {
            campoResultado.setText("Erro");
            campoResultado.setBackground(new Color(255, 200, 200));
            labelStatus.setText("  " + ex.getMensagemAmigavel());

        } catch (Exception ex) {
            campoResultado.setText("Erro");
            campoResultado.setBackground(new Color(255, 200, 200));
            labelStatus.setText("  Erro inesperado: " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Aula09();
            }
        });
    }
}
