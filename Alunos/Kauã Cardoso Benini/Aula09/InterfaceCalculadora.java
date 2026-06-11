
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class InterfaceCalculadora
        extends JFrame
        implements ActionListener {

    JTextField visor;

    String primeiroNumero = "";
    String operador = "";

    public InterfaceCalculadora() {

        setTitle("Calculadora");
        setSize(350, 500);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLocationRelativeTo(null);

        setLayout(new BorderLayout(10, 10));



        visor = new JTextField();

        visor.setFont(new Font("Arial", Font.BOLD, 30));

        visor.setHorizontalAlignment(JTextField.RIGHT);

        visor.setEditable(false);

        add(visor, BorderLayout.NORTH);



        JPanel painel = new JPanel();

        painel.setLayout(new GridLayout(4, 4, 10, 10));

        String[] botoes = {

                "7", "8", "9", "/",

                "4", "5", "6", "*",

                "1", "2", "3", "-",

                "C", "0", "=", "+"
        };

        for (String texto : botoes) {

            JButton botao = new JButton(texto);

            botao.setFont(new Font("Arial", Font.BOLD, 22));

            botao.addActionListener(this);

            painel.add(botao);
        }

        add(painel, BorderLayout.CENTER);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        String comando = e.getActionCommand();

        try {


            if (comando.matches("[0-9]")) {

                visor.setText(
                        visor.getText() + comando
                );
            }



            else if (
                    comando.equals("+")
                            || comando.equals("-")
                            || comando.equals("*")
                            || comando.equals("/")
            ) {

                primeiroNumero = visor.getText();

                operador = comando;

                visor.setText("");
            }



            else if (comando.equals("=")) {

                String segundoNumero = visor.getText();

                double n1 =
                        Double.parseDouble(primeiroNumero);

                double n2 =
                        Double.parseDouble(segundoNumero);

                double resultado =
                        Operacoes.calcular(
                                n1,
                                n2,
                                operador
                        );

                visor.setText(
                        String.valueOf(resultado)
                );
            }



            else if (comando.equals("C")) {

                visor.setText("");

                primeiroNumero = "";

                operador = "";
            }

        }

        catch (NumberFormatException ex) {

            JOptionPane.showMessageDialog(
                    null,
                    "Digite apenas números!"
            );
        }

        catch (CalculadoraException ex) {

            JOptionPane.showMessageDialog(
                    null,
                    ex.getMessage()
            );
        }
    }
}