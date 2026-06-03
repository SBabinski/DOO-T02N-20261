import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalculadoraInterface {

    CalculadoraFuncionamento calculadorafuncionamento = new CalculadoraFuncionamento();
    
    public void InterfaceCalc(){

        JFrame frame = new JFrame("Calculadora");
        frame.setSize(400, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel_top = new JPanel(new BorderLayout());
        panel_top.setBackground(Color.white);
        JTextField expressao = new JTextField("");
        expressao.setPreferredSize(new Dimension(400, 50));
        panel_top.add(expressao, BorderLayout.NORTH);
        frame.add(panel_top, BorderLayout.NORTH);

        JPanel panel_bottom = new JPanel(new BorderLayout());
        panel_bottom.setBackground(Color.white);
        JTextArea historico = new JTextArea("");
        historico.setPreferredSize(new Dimension(400, 120));
        panel_bottom.add(historico, BorderLayout.SOUTH);
        frame.add(panel_bottom, BorderLayout.SOUTH);

        JPanel panel_symbols = new JPanel();
        panel_symbols.setBackground(Color.gray);
        panel_symbols.setPreferredSize(new Dimension(400, 275));
        panel_symbols.setLayout(new GridLayout(4, 4));
        String [] symbols = {"1", "2", "3", "+", "4", "5", "6", "-","7", "8", "9", "X",
                             "C", "0", "=", "/"};
        for (String symbol : symbols){

            if (symbol != null){

                JButton botao = new JButton(symbol);
            
                panel_symbols.add(botao);

                switch (symbol){

                    case "+":

                        botao.addActionListener(new ActionListener() {
                            
                            @Override
                            public void actionPerformed(ActionEvent e){
                                
                                try{

                                    if (calculadorafuncionamento.operador != null){

                                        throw new Exception();
                                    }

                                    calculadorafuncionamento.num1 = Double.parseDouble(expressao.getText());
                                    calculadorafuncionamento.operador = "+";

                                    expressao.setText("");                                        

                                } catch (Exception exc) {

                                    if (calculadorafuncionamento.operador != null){

                                        throw new OperatorAlreadyDefinedException("O operador já foi definido");
                                    }  

                                    if (!isNumeric(expressao.getText())){

                                        throw new NonDigitCharacterException("O input não é um número");
                                    }
                                }
                            }
                        });

                        break;

                    case "-":

                        botao.addActionListener(new ActionListener() {
                            
                            @Override
                            public void actionPerformed(ActionEvent e){
                                
                                try{

                                    if (calculadorafuncionamento.operador != null){

                                        throw new Exception();
                                    }

                                    calculadorafuncionamento.num1 = Double.parseDouble(expressao.getText());
                                    calculadorafuncionamento.operador = "-";

                                    expressao.setText("");                                       

                                } catch (Exception exc) {

                                    if (calculadorafuncionamento.operador != null){

                                        throw new OperatorAlreadyDefinedException("O operador já foi definido");
                                    }  

                                    if (!isNumeric(expressao.getText())){

                                        throw new NonDigitCharacterException("O input não é um número");
                                    }
                                }
                            }
                        });

                        break;
                    
                    case "X":

                        botao.addActionListener(new ActionListener() {
                            
                            @Override
                            public void actionPerformed(ActionEvent e){
                                
                                try{

                                    if (calculadorafuncionamento.operador != null){

                                        throw new Exception();
                                    }

                                    calculadorafuncionamento.num1 = Double.parseDouble(expressao.getText());
                                    calculadorafuncionamento.operador = "X";

                                    expressao.setText("");
                                        
                                } catch (Exception exc) {

                                    if (calculadorafuncionamento.operador != null){

                                        throw new OperatorAlreadyDefinedException("O operador já foi definido");
                                    }  

                                    if (!isNumeric(expressao.getText())){

                                        throw new NonDigitCharacterException("O input não é um número");
                                    }
                                }
                            }
                        });

                        break;

                    case "/":

                        botao.addActionListener(new ActionListener() {
                            
                            @Override
                            public void actionPerformed(ActionEvent e){
                                
                                try{

                                    if (calculadorafuncionamento.operador != null){

                                        throw new Exception();
                                    }

                                    calculadorafuncionamento.num1 = Double.parseDouble(expressao.getText());
                                    calculadorafuncionamento.operador = "/";

                                        expressao.setText("");

                                } catch (Exception exc) {

                                    if (calculadorafuncionamento.operador != null){

                                        throw new OperatorAlreadyDefinedException("O operador já foi definido");
                                    }   
                                    
                                    if (!isNumeric(expressao.getText())){

                                        throw new NonDigitCharacterException("O input não é um número");
                                    }
                                }
                            }
                        });

                        break;

                    case "C":

                        botao.addActionListener(new ActionListener() {
                            
                            @Override
                            public void actionPerformed(ActionEvent e){
                                
                                calculadorafuncionamento.num1 = Double.NaN;
                                calculadorafuncionamento.num2 = Double.NaN;
                                calculadorafuncionamento.operador = null;

                                expressao.setText("");
                            }
                        });

                        break;

                    case "=":

                        botao.addActionListener(new ActionListener() {
                            
                            @Override
                            public void actionPerformed(ActionEvent e){
                                
                                try {

                                    calculadorafuncionamento.num2 = Double.parseDouble(expressao.getText());
                                    
                                    if (calculadorafuncionamento.num2 == 0 && calculadorafuncionamento.operador.equals("/")){

                                        throw new Exception();
                                    }

                                    historico.setText(calculadorafuncionamento.num1 + " " + calculadorafuncionamento.operador + 
                                                      " " + calculadorafuncionamento.num2 + " = " + calculadorafuncionamento.operacao() + "\n" +
                                                      historico.getText());
                                    
                                    expressao.setText("");
                                    calculadorafuncionamento.num1 = Double.NaN;
                                    calculadorafuncionamento.num2 = Double.NaN;
                                    calculadorafuncionamento.operador = null;

                                } catch (Exception exc) {

                                    if (!isNumeric(expressao.getText())){

                                        throw new NonDigitCharacterException("O input não é número");
                                    }

                                    if (calculadorafuncionamento.operador == null){

                                        throw new NotEnoughValuesException("Digite os dois números necessários para a operação primeiro");
                                    }
                                    
                                    if (calculadorafuncionamento.num2 == 0 && calculadorafuncionamento.operador.equals("/")){

                                        throw new ZeroDivisionException("Não é possível realizar divisão por 0");
                                    }
                                }
                            }
                        });

                        break;

                    default:

                        botao.addActionListener(new ActionListener() {
                                
                            @Override
                            public void actionPerformed(ActionEvent e){
                                    
                                expressao.setText(expressao.getText() + symbol);
                            }
                        });
                }
            }
        }

        frame.add(panel_symbols, BorderLayout.CENTER);

        frame.setVisible(true);
    }

    public static boolean isNumeric(String num){

        try {

            Double.parseDouble(num);
            return true;

        } catch (NumberFormatException e){

            return false;
        }
    }
}
