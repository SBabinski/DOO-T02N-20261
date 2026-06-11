import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Calculadora extends JFrame implements ActionListener {
    JTextField txt = new JTextField();
    double v1, v2;
    char op;

    public Calculadora() {
        add(txt, "North");
        JPanel p = new JPanel(new GridLayout(4, 4));
        
        String[] b = {
            "7", "8", "9", "/",
            "4", "5", "6", "*",
            "1", "2", "3", "-",
            "C", "0", "=", "+"
        };
        
        for (String s : b) {
            JButton bt = new JButton(s);
            bt.addActionListener(this);
            p.add(bt);
        }
        add(p);
        pack();
        setVisible(true);
        setDefaultCloseOperation(3);
    }

    public void actionPerformed(ActionEvent e) {
        String s = e.getActionCommand();
        try {
            if ("0123456789".contains(s)) {
                txt.setText(txt.getText() + s);
            } else if (s.equals("C")) {
                txt.setText("");
            } else if (s.equals("=")) {
                v2 = Double.parseDouble(txt.getText());
                if (op == '/' && v2 == 0) throw new CalculadoraException("Zero!");
                
                if (op == '+') txt.setText("" + (v1 + v2));
                if (op == '-') txt.setText("" + (v1 - v2));
                if (op == '*') txt.setText("" + (v1 * v2));
                if (op == '/') txt.setText("" + (v1 / v2));
            } else {
                v1 = Double.parseDouble(txt.getText());
                op = s.charAt(0);
                txt.setText("");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro: " + ex.getMessage());
            txt.setText("");
        }
    }
    public static void main(String[] args) {
        new Calculadora();
    }
}