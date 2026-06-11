import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CalculadoraUI extends JFrame {

    private JTextField display;
    private JLabel labelResultado;
    private Calculadora calculadora;
    private String operacaoAtual;
    private double primeiroNumero;

    public CalculadoraUI() {
        calculadora = new Calculadora();
        configurarJanela();
        criarComponentes();
    }

    public static void main(String[] args) {
        new CalculadoraUI();
    }

    private void configurarJanela(){
        setTitle("Calculadora");
        setSize(300, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void criarComponentes(){
        display = new JTextField("0");
        display.setEditable(false);
        display.setFont(new Font("Arial", Font.BOLD, 24));
        display.setBackground(new Color(50, 50, 50));
        display.setForeground(new Color(255, 255, 255));
        add(display, BorderLayout.NORTH);

        JPanel panelBotoes = new JPanel();
        panelBotoes.setLayout(new GridLayout(4, 4, 5, 5));
        panelBotoes.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        getContentPane().setBackground(new Color(30, 30, 30));
        panelBotoes.setBackground(new Color(30, 30, 30));

        String[] botoes = {
            "7", "8", "9", "C",
            "4", "5", "6", "/",
            "1", "2", "3", "*",
            "0", ".", "=", "+"
        };

        for(String texto : botoes){
            JButton btn = new JButton(texto);

            if (texto.matches("[+\\-*/]")) {
                btn.setBackground(new Color(255, 149, 0));
                btn.setForeground(Color.WHITE);
            } else if (texto.equals("C")) {
                btn.setBackground(new Color(255, 59, 48));
                btn.setForeground(Color.WHITE);
            } else if (texto.equals("=")) {
                btn.setBackground(new Color(52, 199, 89));
                btn.setForeground(Color.WHITE);
            } else {
                btn.setBackground(new Color(70, 70, 70));
                btn.setForeground(Color.WHITE);
            }

            btn.setOpaque(true);
            btn.setBorderPainted(false);
            btn.addActionListener(e -> aoClicarBotao(texto));
            panelBotoes.add(btn);
        }
        add(panelBotoes, BorderLayout.CENTER);

        labelResultado = new JLabel("Resultado: ");
        labelResultado.setForeground(new Color(200, 200, 200));
        labelResultado.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(labelResultado, BorderLayout.SOUTH);
    }

    private void aoClicarBotao(String texto){
        switch (texto) {

            case "C":
                display.setText("0");
                labelResultado.setText("Resultado: ");
                operacaoAtual = null;
                primeiroNumero = 0;
                break;

            case "+": case "-": case "*": case "/":
                primeiroNumero = Double.parseDouble(display.getText());
                operacaoAtual = texto;
                display.setText("0");
                break;

            case "=":
                try {
                    if (operacaoAtual == null) {
                        JOptionPane.showMessageDialog(this, "Selecione uma operação primeiro!", "Erro", JOptionPane.ERROR_MESSAGE);
                        break;
                    }
                    double segundoNumero = Double.parseDouble(display.getText());
                    double resultado = calculadora.calcular(primeiroNumero, segundoNumero, operacaoAtual);
                    display.setText(String.valueOf(resultado));
                    labelResultado.setText("Resultado: " + resultado);
                    if (primeiroNumero == 7 && segundoNumero == 6 && resultado == 13) {
                        JOptionPane.showMessageDialog(this,
                            "ERRO: 7 + 6 = 12 + 1 foi retirado do nosso vocabulario #Bolsonaro26",
                            "Erro Crítico",
                            JOptionPane.ERROR_MESSAGE);
                    }
                } catch (CalculadoraException e) {
                    JOptionPane.showMessageDialog(this, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(this, "Número inválido", "Erro", JOptionPane.ERROR_MESSAGE);
                }
                break;

            default:
                if (display.getText().equals("0")) {
                    display.setText(texto);
                } else {
                    display.setText(display.getText() + texto);
                }
                break;
        }
    }
}