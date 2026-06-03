import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Calculadora extends JFrame implements ActionListener {

    JTextField visor;

    double numero1, numero2, resultado;
    String operacao;

    public class NumeroProibidoException extends Exception {
    }

    public Calculadora() {

        setTitle("Calculadora");
        setSize(350, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        visor = new JTextField();
        visor.setEditable(false);
        visor.setFont(new Font("Arial", Font.BOLD, 60));
        visor.setHorizontalAlignment(JTextField.RIGHT);

        add(visor, BorderLayout.NORTH);

        JPanel painel = new JPanel();
        painel.setLayout(new GridLayout(5, 4, 10, 10));

        String[] botoes = {
                "7", "8", "9", "/",
                "4", "5", "6", "*",
                "1", "2", "3", "-",
                " ", "0", " ", "+",
                "←", ".", "C", "="
        };

        for (String texto : botoes) {

            JButton botao = new JButton(texto);

            botao.setFont(new Font("Arial", Font.BOLD, 25));

            botao.addActionListener(this);
            

            if (texto.equals("+")
                    || texto.equals("-")
                    || texto.equals("*")
                    || texto.equals("/")
                    || texto.equals("C")
                    || texto.equals("=")
                    || texto.equals("←")
                    || texto.equals(".")) {

                botao.setBackground(Color.LIGHT_GRAY);

            } else {

                botao.setBackground(Color.GRAY);

            }

            botao.setForeground(Color.BLACK);
            painel.add(botao);
        }

        add(painel, BorderLayout.CENTER);

        setVisible(true);
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        String comando = e.getActionCommand();

        if (comando.charAt(0) >= '0' && comando.charAt(0) <= '9' ||
                (comando.equals(".") && !visor.getText().contains("."))) {

            visor.setText(visor.getText() + comando);

        } else if (comando.equals("C")) {

            visor.setText("");

            numero1 = 0;
            numero2 = 0;
            resultado = 0;
        } else if (comando.equals("←")) {

            String texto = visor.getText();

            if (!texto.isEmpty()) {

                visor.setText(texto.substring(0, texto.length() - 1));
            } else {
                JOptionPane.showMessageDialog(null,
                        "O Visor está vazio",
                        "Visor vazio",
                        JOptionPane.INFORMATION_MESSAGE);
            }

        } else if (comando.equals("=")) {

            numero2 = Double.parseDouble(visor.getText());

            try {

                switch (operacao) {

                    case "+" -> resultado = numero1 + numero2;

                    case "-" -> resultado = numero1 - numero2;

                    case "*" -> resultado = numero1 * numero2;

                    case "/" -> {
                        if(numero2==0) throw new DivException("É impossível dividir por zero");
                        break;
                    }

                }

            visor.setText(String.valueOf(resultado));

            if(resultado == 666){
                throw new SixSixSixException("Você acabou de invocar o Senho do Sub Mundo");
            }

            } catch(SixSixSixException erro) {
                JOptionPane.showMessageDialog(null,
                    erro.getMessage(),
                    "☠️☠️☠️☠️☠️☠️☠️☠️☠️☠️",
                    JOptionPane.WARNING_MESSAGE
                );
            } catch(DivException erro){
                JOptionPane.showMessageDialog(null,
                    erro.getMessage(),
                    "Aí Não Paizão",
                    JOptionPane.INFORMATION_MESSAGE
                );
            }

        } else {
            numero1 = Double.parseDouble(visor.getText());

            operacao = comando;

            visor.setText("");
        }
    }

    public static void main(String[] args) {

        Calculadora calculadora = new Calculadora();

    }
}