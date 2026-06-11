import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
 
// exception divisão zero
class DivisaoPorZeroException extends Exception {
    public DivisaoPorZeroException() {
        super("divisão por zero? burro");
    }
}
class EntradaInvalidaException extends Exception {
    public EntradaInvalidaException(String entrada) {
        super("entrada inválida. (" + entrada + ")");
    }
}
 
// exception brainrot
class BrainrotException extends Exception {
    public BrainrotException() {
        super("sem brainrot nessa calculadora");
    }
}
 
// exception 404
class PaginaNaoEncontradaException extends Exception {
    public PaginaNaoEncontradaException() {
        super("page not found");
    }
}
 
public class Calculadora extends JFrame implements ActionListener {
 
    private JTextField display;
    private double num1 = 0, num2 = 0, resultado = 0;
    private char operacao;
 
    public Calculadora() {
        setTitle("Calculadora");
        setSize(280, 380);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
 
        display = new JTextField("0");
        display.setEditable(false);
        display.setHorizontalAlignment(JTextField.RIGHT);
        display.setFont(new Font("Monospaced", Font.BOLD, 28));
        display.setBackground(Color.BLACK);
        display.setForeground(Color.WHITE);
        display.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(display, BorderLayout.NORTH);
 
        JPanel painel = new JPanel(new GridLayout(5, 4, 4, 4));
        painel.setBackground(Color.DARK_GRAY);
        painel.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
 
        String[] botoes = {
            "C",  "+/-", "%",  "/",
            "7",  "8",   "9",  "*",
            "4",  "5",   "6",  "-",
            "1",  "2",   "3",  "+",
            "0",  ".",   "<-", "="
        };
 
        for (String label : botoes) {
            painel.add(criarBotao(label));
        }
 
        add(painel, BorderLayout.CENTER);
        setVisible(true);
    }
 
    private JButton criarBotao(String label) {
        JButton btn = new JButton(label);
        btn.setFont(new Font("SansSerif", Font.BOLD, 18));
        btn.setFocusPainted(false);
        btn.setBorderPainted(true);
        btn.addActionListener(this);
 
        switch (label) {
            case "=":
                btn.setBackground(new Color(255, 149, 0));
                btn.setForeground(Color.WHITE);
                break;
            case "/": case "*": case "-": case "+":
                btn.setBackground(new Color(255, 149, 0).darker());
                btn.setForeground(Color.WHITE);
                break;
            case "C": case "+/-": case "%": case "<-":
                btn.setBackground(new Color(100, 100, 100));
                btn.setForeground(Color.WHITE);
                break;
            default:
                btn.setBackground(new Color(50, 50, 50));
                btn.setForeground(Color.WHITE);
        }
 
        return btn;
    }
 
    private double lerNumero(String texto) throws EntradaInvalidaException {
        try {
            return Double.parseDouble(texto);
        } catch (NumberFormatException e) {
            throw new EntradaInvalidaException(texto);
        }
    }
 
    private double calcular(double n1, double n2, char op)
            throws DivisaoPorZeroException, EntradaInvalidaException {
        switch (op) {
            case '+': return n1 + n2;
            case '-': return n1 - n2;
            case '*': return n1 * n2;
            case '/':
                if (n2 == 0) throw new DivisaoPorZeroException();
                return n1 / n2;
            default:
                throw new EntradaInvalidaException("operação desconhecida");
        }
    }
 
    private void mostrarErro(String mensagem) {
        display.setFont(display.getFont().deriveFont(14f));
        display.setForeground(Color.RED);
        display.setText(mensagem);
    }
 
    private void resetarDisplay() {
        display.setText("0");
        display.setFont(new Font("Monospaced", Font.BOLD, 28));
        display.setForeground(Color.WHITE);
        num1 = num2 = 0;
        operacao = 0;
    }
 
    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();
        String atual = display.getText();
 
        if (display.getForeground().equals(Color.RED)) {
            resetarDisplay();
            return;
        }
 
        try {
            switch (cmd) {
                case "C":
                    resetarDisplay();
                    break;
 
                case "<-":
                    if (atual.length() > 1) {
                        display.setText(atual.substring(0, atual.length() - 1));
                    } else {
                        display.setText("0");
                    }
                    break;
 
                case "+/-":
                    if (!atual.equals("0")) {
                        display.setText(atual.startsWith("-")
                            ? atual.substring(1)
                            : "-" + atual);
                    }
                    break;
 
                case "%":
                    double val = lerNumero(atual);
                    display.setText(formatar(val / 100));
                    break;
 
                case ".":
                    if (!atual.contains(".")) {
                        display.setText(atual + ".");
                    }
                    break;
 
                case "+": case "-": case "*": case "/":
                    num1 = lerNumero(atual);
                    operacao = cmd.charAt(0);
                    display.setText("0");
                    break;
 
                case "=":
                    num2 = lerNumero(atual);
                    resultado = calcular(num1, num2, operacao);
                    display.setText(formatar(resultado));
                    num1 = resultado;
                    operacao = 0;
                    break;
 
                default:
                    String novoValor = atual.equals("0") ? cmd : atual + cmd;
                    if (novoValor.equals("67")) throw new BrainrotException();
                    if (novoValor.equals("404")) throw new PaginaNaoEncontradaException();
                    display.setText(novoValor);
            }
 
        } catch (DivisaoPorZeroException ex) {
            mostrarErro(ex.getMessage());
 
        } catch (EntradaInvalidaException ex) {
            mostrarErro(ex.getMessage());
 
        } catch (BrainrotException ex) {
            mostrarErro(ex.getMessage());
 
        } catch (PaginaNaoEncontradaException ex) {
            mostrarErro(ex.getMessage());
        }
    }
 
    private String formatar(double valor) {
        if (valor == (long) valor) {
            return String.valueOf((long) valor);
        }
        return String.valueOf(valor);
    }
 
    public static void main(String[] args) {
        SwingUtilities.invokeLater(Calculadora::new);
    }
}