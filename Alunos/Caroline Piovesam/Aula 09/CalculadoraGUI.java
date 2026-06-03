import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalculadoraGUI extends JFrame {

    private JTextField visor;
    private JTextArea log;
    private double val1 = 0, val2Memoria = 0;
    private String op = "", ultimaOp = "";
    private boolean novo = true, repete = false;
    private CalculadoraLogica calc;

    public CalculadoraGUI() {
        calc = new CalculadoraLogica();
        init();
    }

    private void init() {
        setTitle("Calculadorinha");
        setSize(320, 550);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));
        getContentPane().setBackground(Color.BLACK);

        log = new JTextArea(5, 20);
        log.setEditable(false);
        log.setBackground(new Color(20, 20, 20));
        log.setForeground(new Color(150, 150, 150));
        log.setFont(new Font("Arial", Font.PLAIN, 14));
        JScrollPane scroll = new JScrollPane(log);
        scroll.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));

        visor = new JTextField("0");
        visor.setEditable(false);
        visor.setHorizontalAlignment(SwingConstants.RIGHT);
        visor.setFont(new Font("Arial", Font.BOLD, 35));
        visor.setBackground(Color.BLACK);
        visor.setForeground(Color.WHITE);
        visor.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel topo = new JPanel(new BorderLayout());
        topo.add(scroll, BorderLayout.NORTH);
        topo.add(visor, BorderLayout.SOUTH);
        add(topo, BorderLayout.NORTH);

        JPanel grade = new JPanel(new GridLayout(4, 4, 8, 8));
        grade.setBackground(Color.BLACK);

        String[] teclas = { "0", "C", "=", "+", "1", "2", "3",
                "-", "4", "5", "6", "×", "7", "8", "9", "÷" };

        for (String t : teclas) {
            JButton b = new JButton(t);
            b.setFont(new Font("Arial", Font.PLAIN, 20));
            b.setFocusPainted(false);

            if ("0123456789".contains(t)) {
                b.setBackground(new Color(50, 50, 50));
                b.setForeground(Color.WHITE);
            } else if (t.equals("=")) {
                b.setBackground(new Color(255, 150, 0));
                b.setForeground(Color.WHITE);
            } else {
                b.setBackground(new Color(80, 80, 80));
                b.setForeground(Color.WHITE);
            }

            b.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    processar(t);
                }
            });
            grade.add(b);
        }

        add(grade, BorderLayout.CENTER);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void processar(String t) {
        try {
            if ("0123456789".contains(t)) {
                if (novo) {
                    visor.setText(t);
                    novo = false;
                } else {
                    visor.setText(visor.getText() + t);
                }
                repete = false;
            } else if (t.equals("C")) {
                limpar();
            } else if (t.equals("=")) {
                resolver();
            } else {
                setOp(t);
            }
        } catch (CalculadoraException err) {
            JOptionPane.showMessageDialog(null, err.getMessage(),
                    "Erro!!", 0);
            limpar();
        }
    }

    private void limpar() {
        visor.setText("0");
        val1 = 0;
        val2Memoria = 0;
        op = "";
        ultimaOp = "";
        novo = true;
        repete = false;
        log.setText("");
    }

    private void resolver() throws CalculadoraException {
        double v2;
        if (!repete) {
            if (op.isEmpty())
                return;
            v2 = Double.parseDouble(visor.getText());
            val2Memoria = v2;
            ultimaOp = op;
            repete = true;
        } else {
            if (ultimaOp.isEmpty())
                return;
            val1 = Double.parseDouble(visor.getText());
            v2 = val2Memoria;
        }

        double res = calc.executar(val1, v2, ultimaOp);
        log.append(val1 + " " + ultimaOp + " " + v2 + " = " + res + "\n");
        visor.setText(String.valueOf(res));
        val1 = res;
        novo = true;
        op = "";
    }

    private void setOp(String s) {
        val1 = Double.parseDouble(visor.getText());
        op = s;
        novo = true;
        repete = false;
    }
}