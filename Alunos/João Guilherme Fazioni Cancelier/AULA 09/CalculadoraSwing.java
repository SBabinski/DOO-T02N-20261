import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalculadoraSwing extends JFrame {
    private JTextField visor;
    private double num1 = 0;
    private String operacao = "";
    private boolean inicioNovaEntrada = true;
    private String ultimoAlerta = "";

    public CalculadoraSwing() {
        setTitle("Calculadora");
        setSize(300, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        visor = new JTextField("0");
        visor.setEditable(false);
        visor.setHorizontalAlignment(JTextField.RIGHT);
        visor.setFont(new Font("Monospaced", Font.BOLD, 50));
        add(visor, BorderLayout.NORTH);

        JPanel painelBotoes = new JPanel(new GridLayout(5, 4, 5, 5));
        String[] botoes = {
            "7", "8", "9", "/",
            "4", "5", "6", "*",
            "1", "2", "3", "-",
            "0", ".", "=", "+",
            " ", "C", "←", " " 
        };

        for (String texto : botoes) {
            JButton btn = new JButton(texto);
            btn.setFont(new Font("Arial", Font.BOLD, 18));
            btn.setFocusPainted(false); 
            if (texto.matches("[/\\*\\-+= .]")) {
                btn.setBackground(new Color(24, 200, 200));
                btn.setForeground(Color.BLACK);
            }
            if (texto.matches("[C ←]")) {
                btn.setBackground(new Color(25, 25, 200));
                btn.setForeground(Color.BLACK);
            }
            if (texto.trim().isEmpty()) {
                btn.setEnabled(false);
                btn.setOpaque(false);
                btn.setContentAreaFilled(false);
                btn.setBorderPainted(false); 
            }
            
            btn.addActionListener(new BotaoHandler());
            painelBotoes.add(btn);
        }

        add(painelBotoes, BorderLayout.CENTER);
        setLocationRelativeTo(null);
    }

    private class BotaoHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String comando = e.getActionCommand();

            try {
                if (comando.matches("[0-9]")) {
                    processarNumero(comando);
                } else if (comando.equals(".")) {
                    adicionarPonto();
                } else if (comando.equals("C")) {
                    limpar();
                } else if (comando.equals("←")) {
                    backspace();
                } else if (comando.equals("=")) {
                    exibirResultado();
                } else {
                    prepararOperacao(comando);
                }
            } catch (CalculadoraException ex) {
                String tituloPersonalizado = "Alerta";
                int erroPersonalizado = 0;

                if (ex.getMessage().contains("dedo colado")) {
                    tituloPersonalizado = "Cuidado com o Teclado!";
                    erroPersonalizado = JOptionPane.WARNING_MESSAGE;
                } else if (ex.getMessage().contains("JackPot")) {
                    tituloPersonalizado = "SORTE GRANDE!";
                    erroPersonalizado = JOptionPane.INFORMATION_MESSAGE;
                } else if (ex.getMessage().contains("zero")) {
                    tituloPersonalizado = "Erro Matemático";
                    erroPersonalizado = JOptionPane.ERROR_MESSAGE;
                }

               
                JOptionPane.showMessageDialog(null, ex.getMessage(), tituloPersonalizado, erroPersonalizado);
                
                if (ex.getMessage().contains("dedo colado")) {
                    String textoVisor = visor.getText().replace(".", "");
                    if (!textoVisor.isEmpty()) {
                        ultimoAlerta = String.valueOf(textoVisor.charAt(0));
                        
                    }
                }
            }
        }
    }

    private void adicionarPonto() {
        if (inicioNovaEntrada) {
            visor.setText("0.");
            inicioNovaEntrada = false;
        } else if (!visor.getText().contains(".")) {
            visor.setText(visor.getText() + ".");
        }
    }

    private void backspace() {
        String texto = visor.getText();
        if (texto.length() > 0 && !inicioNovaEntrada) {
            texto = texto.substring(0, texto.length() - 1);
            visor.setText(texto.isEmpty() ? "0" : texto);
            if (visor.getText().equals("0")) {
                inicioNovaEntrada = true;
                ultimoAlerta = "";
            }
        }
    }

    private void processarNumero(String num) throws CalculadoraException {
        String textoFuturo;
        if (inicioNovaEntrada) {
            textoFuturo = num;
            inicioNovaEntrada = false;
        } else {
            textoFuturo = visor.getText() + num;
        }

        visor.setText(textoFuturo);
        visor.paintImmediately(visor.getBounds());

        verificarSete(textoFuturo);
        verificarDedoColado(textoFuturo);
    }

    private void verificarDedoColado(String texto) throws CalculadoraException {
        String apenasNumeros = texto.replace(".", "");
        if (apenasNumeros.length() > 3 && apenasNumeros.chars().allMatch(c -> c == apenasNumeros.charAt(0))) {
            String caractereAtual = String.valueOf(apenasNumeros.charAt(0));
            
            if (!caractereAtual.equals(ultimoAlerta)) {
                throw new CalculadoraException("Erro: Tá com o dedo colado no '" + caractereAtual + "'?");
            }
        } else {

            ultimoAlerta = "";
        }
    }

    private void verificarSete(String texto) throws CalculadoraException {
        String apenasNumeros = texto.replace(".", "");
        if (apenasNumeros.equals("777")) {
            throw new CalculadoraException("JackPot! Você digitou 777!");
        }
    }

    private void prepararOperacao(String op) {
        num1 = Double.parseDouble(visor.getText());
        operacao = op;
        inicioNovaEntrada = true;
        ultimoAlerta = ""; 
    }

    private void exibirResultado() throws CalculadoraException {
        if (operacao.isEmpty()) return;

        try {
            double num2 = Double.parseDouble(visor.getText());
            double resultado = 0;

            switch (operacao) {
                case "+": resultado = num1 + num2; break;
                case "-": resultado = num1 - num2; break;
                case "*": resultado = num1 * num2; break;
                case "/": 
                    if (num2 == 0) throw new CalculadoraException("Erro: Divisão por zero!");
                    resultado = num1 / num2; 
                    break;
            }

            if (resultado == (long) resultado) {
                visor.setText(String.valueOf((long) resultado));
            } else {
                visor.setText(String.valueOf(resultado));
            }
            
            operacao = "";
            inicioNovaEntrada = true;
            ultimoAlerta = "";
        } catch (NumberFormatException e) {
            throw new CalculadoraException("Entrada inválida!");
        }
    }

    private void limpar() {
        visor.setText("0");
        num1 = 0;
        operacao = "";
        ultimoAlerta = ""; 
        inicioNovaEntrada = true;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new CalculadoraSwing().setVisible(true));
    }
}
