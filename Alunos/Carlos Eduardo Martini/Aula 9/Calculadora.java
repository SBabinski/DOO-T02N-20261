import javax.swing.*;
import java.awt.*;


public class Calculadora extends JFrame {
    private JTextField visor;
    
    private double valor1 = 0;
    private String operador = "";

    public Calculadora(){
    setTitle("Calculadora");
    setSize(300, 400);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    setLayout(new BorderLayout());
    visor = new JTextField();
    //visor.setEditable(false); ignora o teclado
    visor.setHorizontalAlignment(JTextField.RIGHT);

    JPanel painelBotoes = new JPanel();
    painelBotoes.setLayout(new GridLayout(4, 4, 5, 5));

    JButton botao1 = new JButton("1");
    JButton botao2 = new JButton("2");
    JButton botao3 = new JButton("3");
    JButton botaoDiv = new JButton("/");

    JButton botao4 = new JButton("4");
    JButton botao5 = new JButton("5");
    JButton botao6 = new JButton("6");
    JButton botaoMul = new JButton("*");

    JButton botao7 = new JButton("7");
    JButton botao8 = new JButton("8");
    JButton botao9 = new JButton("9");
    JButton botaoSub = new JButton("-");

    JButton botao0 = new JButton("0");
    JButton botaoSom = new JButton("+");
    JButton botaoRes = new JButton("=");
    JButton botaolmp = new JButton("c");

        

    painelBotoes.add(botao1);
    painelBotoes.add(botao2);
    painelBotoes.add(botao3);
    painelBotoes.add(botaoDiv);
    painelBotoes.add(botao4);
    painelBotoes.add(botao5);
    painelBotoes.add(botao6);
    painelBotoes.add(botaoMul);
    painelBotoes.add(botao7);
    painelBotoes.add(botao8);
    painelBotoes.add(botao9);
    painelBotoes.add(botaoSub);
    painelBotoes.add(botao0);
    painelBotoes.add(botaoRes);
    painelBotoes.add(botaoSom);
    painelBotoes.add(botaolmp);

    botao0.addActionListener(e -> {
        visor.setText(visor.getText() + "0");
    });
    botao1.addActionListener(e -> {
        visor.setText(visor.getText() + "1");
    });
    botao2.addActionListener(e -> {
        visor.setText(visor.getText() + "2");
    });
    botao3.addActionListener(e -> {
        visor.setText(visor.getText() + "3");
    });
    botao4.addActionListener(e -> {
        visor.setText(visor.getText() + "4");
    });
    botao5.addActionListener(e -> {
        visor.setText(visor.getText() + "5");
    });
    botao6.addActionListener(e -> {
        visor.setText(visor.getText() + "6");
    });
    botao7.addActionListener(e -> {
        visor.setText(visor.getText() + "7");
        if (visor.getText().equals("67")) {
            JOptionPane.showMessageDialog(null, "você não merece usar essa calculadora");
            visor.setText(""); // Limpa a tela como punição
        }
    });
    botao8.addActionListener(e -> {
        visor.setText(visor.getText() + "8");
    });
    botao9.addActionListener(e -> {
        visor.setText(visor.getText() + "9");
        if (visor.getText().equals("69")) {
            JOptionPane.showMessageDialog(null, "( ͡° ͜ʖ ͡°)");
        }
    });

    botaoSom.addActionListener(e -> {
        valor1 = Double.parseDouble(visor.getText());
        operador = "+";
        visor.setText("");
    });
    botaoSub.addActionListener(e -> {
        valor1 = Double.parseDouble(visor.getText());
        operador = "-";
        visor.setText("");
    });
    botaoDiv.addActionListener(e -> {
        valor1 = Double.parseDouble(visor.getText());
        operador = "/";
        visor.setText("");
    });
    botaoMul.addActionListener(e -> {
        valor1 = Double.parseDouble(visor.getText());
        operador = "*";
        visor.setText("");
    });

    botaoRes.addActionListener(e -> {
        try {
            double valor2 = Double.parseDouble(visor.getText());
            double resultado = 0;

            if (operador.equals("/") && valor2 == 0) {
                throw new CalculadoraErros("Não é possível dividir por zero!");
            }

            if (operador.equals("+")){
                resultado = valor1 + valor2;
            } else if (operador.equals("-")){
                resultado = valor1 - valor2;
            } else if (operador.equals("*")){
                resultado = valor1 * valor2;
            } else if (operador.equals("/")){
                resultado = valor1 / valor2;
            }

            if (resultado % 1 == 0) {
                visor.setText(String.valueOf((long) resultado)); 
            } else {
                visor.setText(String.valueOf(resultado));
            }

        } catch (NumberFormatException erro) {
            JOptionPane.showMessageDialog(null, "Por favor, digite um número válido!");
            visor.setText("");
        } catch (CalculadoraErros erro) {
            JOptionPane.showMessageDialog(null, erro.getMessage());
            visor.setText("");
        }
    });

    botaolmp.addActionListener(e -> {
        visor.setText("");
    });
    

    add(painelBotoes, BorderLayout.CENTER);
    add(visor, BorderLayout.NORTH);
    }
    public static void main(String[] args) {
        Calculadora calc = new Calculadora();
        calc.setVisible(true);
   
    }
}
