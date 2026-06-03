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

class CalculadoraException extends Exception {

    public CalculadoraException(String mensagem){
        super(mensagem);
    }
}

public class CalculadoraSwing implements ActionListener {

    static JTextField display;
    static double numero1;
    static String operador;

    public static void main(String[] args) {

        JFrame frame = new JFrame("Calculadora");

        frame.setSize(350,600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        display = new JTextField();
        display.setFont(new Font("Arial", Font.PLAIN, 50));
        display.setBackground(new Color(28,28,28));
        display.setForeground(Color.WHITE);
        display.setHorizontalAlignment(JTextField.RIGHT);
        display.setEditable(false);

        JPanel panel = new JPanel();
        panel.setBackground(new Color(28,28,28));
        panel.setLayout(new GridLayout(4,4,15,15));

        String[] botoes = {
                "7","8","9","÷",
                "4","5","6","×",
                "1","2","3","-",
                "0","C","=","+"
        };

        for(String texto : botoes){

            JButton botao = new JButton(texto);

            botao.addActionListener(new CalculadoraSwing());

            botao.setBorderPainted(false);
            botao.setFocusPainted(false);
            botao.setContentAreaFilled(true);
            botao.setOpaque(true);

            botao.setFont(new Font("Arial", Font.PLAIN, 28));
            botao.setForeground(Color.WHITE);
            botao.setBackground(new Color(80,80,80));

            if(texto.equals("+") || texto.equals("-") ||
               texto.equals("×") || texto.equals("÷") ||
               texto.equals("=")){

                botao.setBackground(new Color(255,149,0));
            }

            panel.add(botao);
        }

        frame.setLayout(new BorderLayout());
        frame.add(display, BorderLayout.NORTH);

        panel.setBorder(
            javax.swing.BorderFactory.createEmptyBorder(15,15,15,15)
        );

        frame.add(panel, BorderLayout.CENTER);
        frame.setVisible(true);
    }

@Override
    public void actionPerformed(ActionEvent e) {

        String texto = e.getActionCommand();

        try {

            if(texto.matches("[0-9]")){
                display.setText(display.getText() + texto);
            }

            else if(texto.equals("+") ||
                    texto.equals("-") ||
                    texto.equals("×") ||
                    texto.equals("÷")){

                numero1 = Double.parseDouble(display.getText());
                operador = texto;
                display.setText("");
            }

            else if(texto.equals("=")){

                double numero2 = Double.parseDouble(display.getText());

                double resultado = 0;

                switch (operador) {

                    case "+" -> resultado = numero1 + numero2;
                    case "-" -> resultado = numero1 - numero2;
                    case "×" -> resultado = numero1 * numero2;
                    case "÷" -> {

                        if(numero2 == 0){
                            throw new CalculadoraException(
                                "Não é possível dividir por zero!"
                            );
                        }

                        resultado = numero1 / numero2;
                    }
                }

                display.setText(String.valueOf(resultado));
            }

            else if(texto.equals("C")){

                display.setText("");
                numero1 = 0;
                operador = "";
            }

        } catch(NumberFormatException ex){

            JOptionPane.showMessageDialog(
                null,
                "Digite apenas números!",
                "Erro",
                JOptionPane.ERROR_MESSAGE
            );

        } catch(CalculadoraException ex){

            JOptionPane.showMessageDialog(
                null,
                ex.getMessage(),
                "Erro da Calculadora",
                JOptionPane.WARNING_MESSAGE
            );
        }
    }
 
}