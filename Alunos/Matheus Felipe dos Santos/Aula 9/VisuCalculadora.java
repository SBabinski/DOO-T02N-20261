import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class VisuCalculadora extends JFrame implements ActionListener {

    JTextField campo;

    double numero1;
    String operador;

    public VisuCalculadora() {

        setTitle("🇧🇷 Calculadora Brasileira");
        setSize(320, 450);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        getContentPane().setBackground(new Color(0, 156, 59));

        campo = new JTextField();

        campo.setFont(new Font("SansSerif", Font.BOLD, 32));
        campo.setBackground(new Color(0, 39, 118));
        campo.setForeground(Color.WHITE);

        campo.setHorizontalAlignment(JTextField.RIGHT);

        campo.setEditable(false);

        campo.setBorder(
                BorderFactory.createEmptyBorder(10,10,10,10)
        );

        add(campo, BorderLayout.NORTH);

        JPanel painel = new JPanel();

        painel.setBackground(new Color(0, 156, 59));

        painel.setLayout(new GridLayout(4,4,5,5));

        String[] botoes = {
                "7","8","9","/",
                "4","5","6","*",
                "1","2","3","-",
                "0","C","+","="
        };

        for(String texto : botoes) {

            JButton botao = new JButton(texto);

            botao.setFont(new Font("SansSerif", Font.BOLD, 24));

            if(texto.matches("[0-9]")) {

                botao.setBackground(new Color(255, 223, 0));
                botao.setForeground(Color.BLACK);
            }

            else if(texto.matches("[+\\-*/]")) {

                botao.setBackground(new Color(0, 39, 118));
                botao.setForeground(Color.WHITE);
            }

            else if(texto.equals("=")) {

                botao.setBackground(new Color(0, 156, 59));
                botao.setForeground(Color.WHITE);
            }

            else if(texto.equals("C")) {

                botao.setBackground(Color.RED);
                botao.setForeground(Color.WHITE);
            }

            botao.addActionListener(this);

            painel.add(botao);
        }

        add(painel, BorderLayout.CENTER);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        String comando = e.getActionCommand();

        if(comando.matches("[0-9]")) {

            campo.setText(campo.getText() + comando);
        }

        else if(comando.matches("[+\\-*/]")) {

            try {

                numero1 = Double.parseDouble(campo.getText());

                operador = comando;

                campo.setText("");

            } catch (NumberFormatException ex) {

                JOptionPane.showMessageDialog(
                        null,
                        "Digite apenas números",
                        "Erro",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        }

        else if(comando.equals("=")) {

            try {

                double numero2 =
                        Double.parseDouble(campo.getText());

                if(numero1 == 7 && numero2 == 1) {

                    throw new MinhaException(
                            "Operação não permitida por motivos de trauma =("
                    );
                }

                double resultado = 0;

                switch (operador) {

                    case "+":
                        resultado = numero1 + numero2;
                        break;

                    case "-":
                        resultado = numero1 - numero2;
                        break;

                    case "*":
                        resultado = numero1 * numero2;
                        break;

                    case "/":

                        if(numero2 == 0) {

                            throw new MinhaException(
                                    "Divisão por zero não permitida"
                            );
                        }

                        resultado = numero1 / numero2;
                        break;
                }

                campo.setText(String.valueOf(resultado));

            } catch (NumberFormatException ex) {

                JOptionPane.showMessageDialog(
                        null,
                        "Entrada inválida",
                        "Erro",
                        JOptionPane.ERROR_MESSAGE
                );

            } catch (MinhaException ex) {

                JOptionPane.showMessageDialog(
                        null,
                        ex.getMessage(),
                        "Erro",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        }

        else if(comando.equals("C")) {

            campo.setText("");
        }
    }
}