import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class Calculadora extends JFrame {

    private double primeiroNumero;
    private String operador = "";
    private boolean novoNumero = true;

    private JTextField display;
    private JLabel historico;

    // Cores
    private final Color FUNDO = new Color(24,24,24);
    private final Color DISPLAY = new Color(18,18,18);

    private final Color NUMERO = new Color(58,58,58);
    private final Color OPERADOR = new Color(255,140,0);
    private final Color ESPECIAL = new Color(90,90,90);
    private final Color IGUAL = new Color(46,204,113);

    public Calculadora() {

        setTitle("Calculadora");
        setSize(320,500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        criarTela();

        setVisible(true);
    }

    private void criarTela() {

        JPanel painel = new JPanel(new BorderLayout());
        painel.setBackground(FUNDO);

        // Display
        historico = new JLabel(" ", SwingConstants.RIGHT);
        historico.setForeground(Color.LIGHT_GRAY);
        historico.setBackground(DISPLAY);
        historico.setOpaque(true);
        historico.setBorder(new EmptyBorder(10,10,0,10));

        display = new JTextField("0");
        display.setEditable(false);
        display.setHorizontalAlignment(SwingConstants.RIGHT);
        display.setFont(new Font("Arial", Font.BOLD, 40));
        display.setBackground(DISPLAY);
        display.setForeground(Color.WHITE);
        display.setBorder(new EmptyBorder(10,10,10,10));

        JPanel topo = new JPanel(new BorderLayout());
        topo.add(historico, BorderLayout.NORTH);
        topo.add(display, BorderLayout.CENTER);

        // Botões
        JPanel botoes = new JPanel(new GridLayout(5,4,5,5));
        botoes.setBorder(new EmptyBorder(10,10,10,10));
        botoes.setBackground(FUNDO);

        String[] textos = {
                "Clear","+/-","%","÷",
                "7","8","9","×",
                "4","5","6","-",
                "1","2","3","+",
                "0",".","=",""
        };

        for(String t : textos){

            if(t.equals("")){
                botoes.add(new JLabel());
                continue;
            }

            Color cor = NUMERO;

            if("+-×÷".contains(t)) cor = OPERADOR;
            if(t.equals("=")) cor = IGUAL;
            if(t.equals("Clear") || t.equals("+/-") || t.equals("%"))
                cor = ESPECIAL;

            JButton botao = criarBotao(t, cor);

            botoes.add(botao);
        }

        painel.add(topo, BorderLayout.NORTH);
        painel.add(botoes, BorderLayout.CENTER);

        setContentPane(painel);
    }

    private JButton criarBotao(String texto, Color cor){

        JButton b = new JButton(texto);

        b.setFont(new Font("Arial", Font.BOLD, 20));
        b.setBackground(cor);
        b.setForeground(Color.WHITE);

        b.setFocusPainted(false);
        b.setBorder(BorderFactory.createLineBorder(new Color(40,40,40)));

        b.addActionListener(e -> clicar(texto));

        return b;
    }

    private void clicar(String valor){

        try {

            switch (valor){

                case "Clear" -> limpar();

                case "+/-" -> inverter();

                case "%" -> porcentagem();

                case "+", "-", "×", "÷" -> operador(valor);

                case "=" -> resultado();

                default -> numero(valor);
            }

        } catch (CalculadoraException e) {

            display.setText("Erro");
            historico.setText(e.getMessage());
            display.setForeground(Color.RED);
        }
    }

    private void limpar(){

        primeiroNumero = 0;
        operador = "";
        novoNumero = true;

        display.setText("0");
        display.setForeground(Color.WHITE);

        historico.setText(" ");
    }

    private void inverter(){

        String v = display.getText();

        if(v.startsWith("-"))
            display.setText(v.substring(1));

        else if(!v.equals("0"))
            display.setText("-" + v);
    }

    private void porcentagem() throws CalculadoraException {

        double v = converter(display.getText());

        display.setText(String.valueOf(v / 100));
    }

    private void operador(String op) throws CalculadoraException {

        primeiroNumero = converter(display.getText());

        operador = op;

        novoNumero = true;

        historico.setText(primeiroNumero + " " + operador);
    }

    private void resultado() throws CalculadoraException {

        if(operador.isEmpty()) return;

        double segundoNumero = converter(display.getText());

        double resultado = calcular(
                primeiroNumero,
                operador,
                segundoNumero
        );

        historico.setText(
                primeiroNumero + " " +
                        operador + " " +
                        segundoNumero + " ="
        );

        display.setForeground(Color.WHITE);
        display.setText(String.valueOf(resultado));

        operador = "";
        primeiroNumero = resultado;
        novoNumero = true;
    }

    private void numero(String n){

        if(novoNumero){

            display.setText(n);
            novoNumero = false;
            return;
        }

        if(n.equals(".") && display.getText().contains("."))
            return;

        if(display.getText().equals("0"))
            display.setText(n);

        else
            display.setText(display.getText() + n);
    }

    private double calcular(double a, String op, double b)
            throws CalculadoraException {

        return switch (op){

            case "+" -> a + b;

            case "-" -> a - b;

            case "×" -> a * b;

            case "÷" -> {

                if(b == 0)
                    throw new CalculadoraException(
                            "Não é possível dividir por zero."
                    );

                yield a / b;
            }

            default -> throw new CalculadoraException(
                    "Operação inválida."
            );
        };
    }

    private double converter(String valor)
            throws CalculadoraException {

        try {

            return Double.parseDouble(valor);

        } catch (Exception e){

            throw new CalculadoraException(
                    "Digite apenas números."
            );
        }
    }
}