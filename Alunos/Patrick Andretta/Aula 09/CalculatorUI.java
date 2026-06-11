import javax.swing.*;
import java.awt.*;

public class CalculatorUI extends JFrame {

    private static final Color BG          = new Color(28, 28, 28);
    private static final Color BTN_NUM     = new Color(55, 55, 55);
    private static final Color BTN_OP      = new Color(255, 149, 0);
    private static final Color BTN_CLEAR   = new Color(200, 55, 55);
    private static final Color DISPLAY_BG  = new Color(15, 15, 15);
    private static final Color FG          = new Color(230, 230, 230);

    private JTextField display;
    private JLabel expressionLabel;
    private double storedValue;
    private String currentOperation;
    private boolean newNumber;

    public CalculatorUI() {
        setTitle("Calculadora");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));
        getContentPane().setBackground(BG);

        JLabel titleLabel = new JLabel();
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setForeground(FG);
        titleLabel.setBackground(BG);
        titleLabel.setOpaque(true);
        add(titleLabel, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel(new BorderLayout(5, 5));
        centerPanel.setBackground(BG);

        // Painel do visor com linha da expressão e valor atual
        JPanel displayPanel = new JPanel(new BorderLayout());
        displayPanel.setBackground(DISPLAY_BG);
        displayPanel.setBorder(BorderFactory.createEmptyBorder(4, 0, 0, 0));

        expressionLabel = new JLabel(" ", SwingConstants.RIGHT);
        expressionLabel.setFont(new Font("Arial", Font.PLAIN, 13));
        expressionLabel.setForeground(new Color(160, 160, 160));
        expressionLabel.setBackground(DISPLAY_BG);
        expressionLabel.setOpaque(true);
        expressionLabel.setBorder(BorderFactory.createEmptyBorder(4, 10, 0, 10));

        display = new JTextField("0");
        display.setEditable(false);
        display.setHorizontalAlignment(JTextField.RIGHT);
        display.setFont(new Font("Arial", Font.PLAIN, 26));
        display.setBackground(DISPLAY_BG);
        display.setForeground(FG);
        display.setCaretColor(FG);
        display.setBorder(BorderFactory.createEmptyBorder(2, 10, 6, 10));

        displayPanel.add(expressionLabel, BorderLayout.NORTH);
        displayPanel.add(display, BorderLayout.CENTER);
        centerPanel.add(displayPanel, BorderLayout.NORTH);

        JPanel buttonsContainer = new JPanel(new BorderLayout(5, 5));
        buttonsContainer.setBackground(BG);
        JPanel grid = new JPanel(new GridLayout(4, 4, 5, 5));
        grid.setBackground(BG);

        String[] labels = {
            "7", "8", "9", "÷",
            "4", "5", "6", "×",
            "1", "2", "3", "-",
            "0", ".", "=", "+"
        };

        for (String text : labels) {
            JButton btn = createButton(text);
            btn.addActionListener(e -> handleButton(text));
            grid.add(btn);
        }

        JButton clearBtn = new JButton("C");
        clearBtn.setFont(new Font("Arial", Font.BOLD, 18));
        clearBtn.setBackground(BTN_CLEAR);
        clearBtn.setForeground(Color.WHITE);
        clearBtn.setFocusPainted(false);
        clearBtn.setBorderPainted(false);
        clearBtn.addActionListener(e -> clear());

        // Divisor visual entre o grid e o botão C
        JSeparator separator = new JSeparator();
        separator.setForeground(new Color(80, 80, 80));
        separator.setBackground(BG);

        JPanel clearPanel = new JPanel(new BorderLayout(0, 6));
        clearPanel.setBackground(BG);
        clearPanel.add(separator, BorderLayout.NORTH);
        clearPanel.add(clearBtn, BorderLayout.CENTER);

        buttonsContainer.add(grid, BorderLayout.CENTER);
        buttonsContainer.add(clearPanel, BorderLayout.SOUTH);
        centerPanel.add(buttonsContainer, BorderLayout.CENTER);

        add(centerPanel, BorderLayout.CENTER);

        setSize(320, 420);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private JButton createButton(String text) {
        JButton btn = new JButton(text);
        btn.setFont(new Font("Arial", Font.PLAIN, 18));
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        boolean isOp = text.equals("+") || text.equals("-") || text.equals("×") || text.equals("÷") || text.equals("=");
        btn.setBackground(isOp ? BTN_OP : BTN_NUM);
        btn.setForeground(isOp ? Color.WHITE : FG);
        return btn;
    }

    private void handleButton(String text) {
        switch (text) {
            case "+":
            case "-":
            case "×":
            case "÷":
                handleOperation(text);
                break;
            case "=":
                handleEquals();
                break;
            default:
                handleDigit(text);
        }
    }

    private void handleDigit(String digit) {
        if (newNumber) {
            display.setText(digit.equals(".") ? "0." : digit);
            newNumber = false;
        } else {
            String current = display.getText();
            if (digit.equals(".") && current.contains(".")) return;
            if (current.equals("0") && !digit.equals(".")) {
                display.setText(digit);
            } else {
                display.setText(current + digit);
            }
        }
    }

    private void handleOperation(String op) {
        try {
            storedValue = parseDisplay();
            currentOperation = op;
            newNumber = true;
            expressionLabel.setText(format(storedValue) + " " + op);
        } catch (CalculatorException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void handleEquals() {
        if (currentOperation == null) return;
        try {
            double currentValue = parseDisplay();
            String expr = format(storedValue) + " " + currentOperation + " " + format(currentValue);
            double result = Calculator.calculate(storedValue, currentValue, currentOperation);
            expressionLabel.setText(expr + " =");
            display.setText(format(result));
            currentOperation = null;
            newNumber = true;
        } catch (CalculatorException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            clear();
        }
    }

    private double parseDisplay() throws CalculatorException {
        try {
            return Double.parseDouble(display.getText());
        } catch (NumberFormatException e) {
            throw new CalculatorException("Entrada inválida: \"" + display.getText() + "\" não é um número válido.");
        }
    }

    private String format(double value) {
        if (value == (long) value) return String.valueOf((long) value);
        return String.valueOf(value);
    }

    private void clear() {
        display.setText("0");
        expressionLabel.setText(" ");
        storedValue = 0;
        currentOperation = null;
        newNumber = false;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(CalculatorUI::new);
    }
}
