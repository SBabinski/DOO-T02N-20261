import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Calculadora {

    static double numero1 = 0;
    static double numero2 = 0;
    static String operacao = "";

    public static void main(String[] args) {

        JFrame janela = new JFrame("Calculadora");

        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        janela.setSize(400, 600);
        janela.setLayout(new BorderLayout());

        JTextField visor = new JTextField();

        visor.setFont(new Font("Arial", Font.PLAIN, 40));
        visor.setPreferredSize(new java.awt.Dimension(400, 100));
        visor.setEditable(false);

        janela.add(visor, BorderLayout.NORTH);

        JPanel painel = new JPanel();

        painel.setLayout(new GridLayout(4, 4, 5, 5));
        painel.setBorder(
            javax.swing.BorderFactory.createEmptyBorder(10,10,10,10)
        );

        String[] botoes = {
                "7", "8", "9", "/",
                "4", "5", "6", "*",
                "1", "2", "3", "-",
                "0", "=", "+", "C"
        };

        for (String texto : botoes) {

            JButton botao = new JButton(texto);

            botao.setFont(new Font("Arial", Font.BOLD, 16));

            botao.addActionListener(e -> {

                try {
                
                    if (texto.equals("C")) {

                        visor.setText("");

                        numero1 = 0;
                        numero2 = 0;
                        operacao = "";

                    }

                    else if (
                            texto.equals("+") ||
                            texto.equals("-") ||
                            texto.equals("*") ||
                            texto.equals("/")
                    ) {

                        numero1 = Double.parseDouble(visor.getText());

                        operacao = texto;

                        visor.setText("");

                    }

                    else if (texto.equals("=")) {

                        numero2 = Double.parseDouble(visor.getText());

                        double resultado = 0;

                        switch (operacao) {

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

                                if (numero2 == 0) {

                                    throw new MinhaException(
                                            "Impossível dividir por zero!"
                                    );
                                }

                                resultado = numero1 / numero2;
                                break;
                        }

                        visor.setText(String.valueOf(resultado));

                    }

                    else {

                        visor.setText(visor.getText() + texto);

                    }

                }

                catch (MinhaException erro) {

                    visor.setText(erro.getMessage());

                }

                catch (NumberFormatException erro) {

                    visor.setText("Entrada inválida!");

                }

                catch (Exception erro) {

                    visor.setText("Erro na operação!");

                }

            });

            painel.add(botao);
        }

        janela.add(painel, BorderLayout.CENTER);

        janela.setVisible(true);
    }
}