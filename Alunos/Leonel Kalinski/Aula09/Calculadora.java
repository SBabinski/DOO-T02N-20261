package calculadora;

import javax.swing.*;
import java.awt.*;
 
public class Calculadora extends JFrame {
 
    private final JTextField display = new JTextField("0");
    private final CalculadoraLogica logica = new CalculadoraLogica();
 
    private double numero1 = 0;
    private String operacao = "";
    private boolean novaEntrada = true;
 
    public Calculadora() {
        setTitle("Calculadora");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout(4, 4));
        getRootPane().setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
        display.setEditable(false);
        display.setHorizontalAlignment(JTextField.RIGHT);
        display.setFont(new Font("SansSerif", Font.PLAIN, 26));
        display.setPreferredSize(new Dimension(0, 55));
        add(display, BorderLayout.NORTH);
        JPanel painel = new JPanel(new GridLayout(4, 4, 4, 4));
        for (String t : new String[]{"7","8","9","/","4","5","6","*","1","2","3","-","0","C","=","+"}) {
            painel.add(criarBotao(t));
        }
        add(painel, BorderLayout.CENTER);
 
        setSize(260, 320);
        setLocationRelativeTo(null);
        setVisible(true);
    }
 
    private JButton criarBotao(String texto) {
        JButton btn = new JButton(texto);
        btn.setFont(new Font("SansSerif", Font.BOLD, 18));
        btn.setFocusPainted(false);
        btn.addActionListener(e -> clicar(texto));
        return btn;
    }
 
    private void clicar(String cmd) {
        try {
            switch (cmd) {
                case "C":
                    display.setText("0");
                    numero1 = 0;
                    operacao = "";
                    novaEntrada = true;
                    break;
                case "+": case "-": case "*": case "/":
                    numero1 = Double.parseDouble(display.getText());
                    operacao = cmd;
                    novaEntrada = true;
                    break;
                case "=":
                    double resultado = logica.calcular(numero1, Double.parseDouble(display.getText()), operacao);
                    display.setText(formatar(resultado));
                    novaEntrada = true;
                    break;
                default:
                    if (novaEntrada || display.getText().equals("0")) {
                        display.setText(cmd);
                        novaEntrada = false;
                    } else {
                        display.setText(display.getText() + cmd);
                    }
            }
        } catch (CalculadoraException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            novaEntrada = true;
        }
    }
 
    private String formatar(double n) {
        return n == (long) n ? String.valueOf((long) n) : String.valueOf(n);
    }
 
    public static void main(String[] args) {
        SwingUtilities.invokeLater(Calculadora::new);
    }
}
