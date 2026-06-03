package fag;

import java.awt.*;
import javax.swing.*;
 
public class CalculadoraFrame extends JFrame {
 
    private static final Color COR_FUNDO   = new Color(28, 28, 28);
    private static final Color COR_BTN_NUM = new Color(55, 55, 55);
    private static final Color COR_BTN_OP  = new Color(100, 70, 200);
    private static final Color COR_BTN_CLR = new Color(180, 50, 60);
    private static final Color COR_TEXTO   = new Color(230, 230, 230);
 
    private JTextField campoNumero1;
    private JTextField campoNumero2;
    private JLabel labelResultado;
    private JLabel labelExpressao;
 
    private CalculadoraService service = new CalculadoraService();
    private Operacao operacaoSelecionada = new Soma();
 
    public CalculadoraFrame() {
        setTitle("Calculadora");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(8, 8));
        getContentPane().setBackground(COR_FUNDO);
 
        // Display
        JPanel painelDisplay = new JPanel(new GridLayout(4, 1, 0, 4));
        painelDisplay.setBackground(new Color(15, 15, 15));
        painelDisplay.setBorder(BorderFactory.createEmptyBorder(10, 12, 10, 12));
 
        labelExpressao = new JLabel(" ", SwingConstants.RIGHT);
        labelExpressao.setFont(new Font("Monospaced", Font.PLAIN, 12));
        labelExpressao.setForeground(new Color(140, 130, 170));
 
        labelResultado = new JLabel("Resultado: --", SwingConstants.RIGHT);
        labelResultado.setFont(new Font("Monospaced", Font.BOLD, 20));
        labelResultado.setForeground(new Color(140, 255, 180));
 
        campoNumero1 = new JTextField();
        campoNumero1.setFont(new Font("Monospaced", Font.PLAIN, 16));
        campoNumero1.setBackground(new Color(38, 36, 55));
        campoNumero1.setForeground(COR_TEXTO);
        campoNumero1.setHorizontalAlignment(JTextField.RIGHT);
        campoNumero1.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(70, 55, 110), 1),
            BorderFactory.createEmptyBorder(4, 8, 4, 8)
        ));
 
        campoNumero2 = new JTextField();
        campoNumero2.setFont(new Font("Monospaced", Font.PLAIN, 16));
        campoNumero2.setBackground(new Color(38, 36, 55));
        campoNumero2.setForeground(COR_TEXTO);
        campoNumero2.setHorizontalAlignment(JTextField.RIGHT);
        campoNumero2.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(70, 55, 110), 1),
            BorderFactory.createEmptyBorder(4, 8, 4, 8)
        ));
 
        painelDisplay.add(labelExpressao);
        painelDisplay.add(labelResultado);
        painelDisplay.add(campoNumero1);
        painelDisplay.add(campoNumero2);
 
        // Botões
        JPanel painelBotoes = new JPanel(new GridLayout(4, 4, 6, 6));
        painelBotoes.setBackground(COR_FUNDO);
        painelBotoes.setBorder(BorderFactory.createEmptyBorder(8, 10, 10, 10));
 
        String[] rotulos = {
            "7", "8", "9", "÷",
            "4", "5", "6", "×",
            "1", "2", "3", "-",
            "C", "0", "=", "+"
        };
 
        for (String r : rotulos) {
            painelBotoes.add(criarBotao(r));
        }
 
        add(painelDisplay, BorderLayout.NORTH);
        add(painelBotoes, BorderLayout.CENTER);
 
        setSize(320, 420);
        setLocationRelativeTo(null);
    }
 
    private JButton criarBotao(String texto) {
        JButton btn = new JButton(texto);
        btn.setFont(new Font("Monospaced", Font.BOLD, 16));
        btn.setForeground(COR_TEXTO);
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
 
        if (texto.equals("C")) {
            btn.setBackground(COR_BTN_CLR);
        } else if (texto.equals("=")) {
            btn.setBackground(new Color(130, 80, 255));
        } else if (texto.equals("+") || texto.equals("-") || texto.equals("×") || texto.equals("÷")) {
            btn.setBackground(COR_BTN_OP);
        } else {
            btn.setBackground(COR_BTN_NUM);
        }
 
        btn.addActionListener(e -> processarBotao(texto));
        return btn;
    }
 
    private void processarBotao(String texto) {
        switch (texto) {
            case "+": 
            	operacaoSelecionada = new Soma();           
            	atualizarExpressao(); 
            break;
            
            case "-": 
            	operacaoSelecionada = new Subtracao();      
            	atualizarExpressao(); 
            break;
            
            case "×": 
            	operacaoSelecionada = new Multiplicacao();  
            	atualizarExpressao(); 
            break;
            
            case "÷": 
            	operacaoSelecionada = new Divisao();        
            	atualizarExpressao(); 
            break;
            
            case "=": 
            	calcular(); 
            break;
            
            case "C": 
            	limpar(); 
            break;
            default:  
            	adicionarDigito(texto); 
            break;
        }
    }
 
    private void adicionarDigito(String digito) {
        JTextField campoAtivo = campoNumero1.isFocusOwner() ? campoNumero1 : campoNumero2;
        if (digito.equals(".") && campoAtivo.getText().contains(".")) return;
        campoAtivo.setText(campoAtivo.getText() + digito);
        atualizarExpressao();
    }
 
    private void calcular() {
        try {
            double resultado = service.calcular(
                campoNumero1.getText(),
                campoNumero2.getText(),
                operacaoSelecionada
            );
            labelResultado.setForeground(new Color(140, 255, 180));
            labelResultado.setText("Resultado: " + service.formatarResultado(resultado));
            atualizarExpressaoCompleta();
 
        } catch (CalculadoraException e) {
            e.exibirErro();
            labelResultado.setForeground(new Color(255, 100, 100));
            labelResultado.setText("Erro: " + e.getMessage());
        }
    }
 
    private void limpar() {
        campoNumero1.setText("");
        campoNumero2.setText("");
        labelResultado.setForeground(new Color(140, 255, 180));
        labelResultado.setText("Resultado: --");
        labelExpressao.setText(" ");
    }
 
    private void atualizarExpressao() {
        String n1 = campoNumero1.getText().isEmpty() ? "?" : campoNumero1.getText();
        String n2 = campoNumero2.getText().isEmpty() ? "?" : campoNumero2.getText();
        labelExpressao.setText(n1 + "  " + operacaoSelecionada.getSimbolo() + "  " + n2);
    }
 
    private void atualizarExpressaoCompleta() {
        String n1 = campoNumero1.getText();
        String n2 = campoNumero2.getText();
        labelExpressao.setText(n1 + " " + operacaoSelecionada.getSimbolo() + " " + n2 + " =");
    }
 
    public void apresentarse() {
        setVisible(true);
        System.out.println("=== Calculadora iniciada ===");
    }
}