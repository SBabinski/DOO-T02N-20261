import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import javax.swing.JButton;

public class Calculadora {

    static String numeroAtual = "0";
    static double primeiroNumero = 0;
    static String operacao = "";
    static boolean limparVisor = false;

    public static void main(String[] args) {

        Font fonteBotao = new Font("Arial", Font.PLAIN, 24);

        JFrame calculadora = new JFrame("Caculadora");
        calculadora.setSize(420, 660);
        calculadora.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        calculadora.setBackground(Color.BLACK);

        JPanel painel = new JPanel();
        painel.setLayout(null);
        painel.setBackground(Color.BLACK);

        JLabel visor = new JLabel("0");
        visor.setFont(new Font("Arial", Font.PLAIN, 48));
        visor.setBounds(10, 180, 389, 80);
        visor.setHorizontalAlignment(SwingConstants.RIGHT);
        visor.setForeground(Color.WHITE);

        JLabel visorOperacao = new JLabel("");
        visorOperacao.setFont(new Font("Arial", Font.PLAIN, 32));
        visorOperacao.setBounds(10, 100, 389, 80);
        visorOperacao.setHorizontalAlignment(SwingConstants.RIGHT);
        visorOperacao.setForeground(new Color(55, 53, 56));

        JButton botao7 = new JButton("7");
        botao7.setBounds(10, 300, 95, 60);
        botao7.setFont(fonteBotao);

        JButton botao8 = new JButton("8");
        botao8.setBounds(108, 300, 95, 60);
        botao8.setFont(fonteBotao);

        JButton botao9 = new JButton("9");
        botao9.setBounds(206, 300, 95, 60);
        botao9.setFont(fonteBotao);

        JButton botaoDividir = new JButton("/");
        botaoDividir.setBounds(304, 300, 95, 60);
        botaoDividir.setFont(fonteBotao);

        JButton botao4 = new JButton("4");
        botao4.setBounds(10, 363, 95, 60);
        botao4.setFont(fonteBotao);

        JButton botao5 = new JButton("5");
        botao5.setBounds(108, 363, 95, 60);
        botao5.setFont(fonteBotao);

        JButton botao6 = new JButton("6");
        botao6.setBounds(206, 363, 95, 60);
        botao6.setFont(fonteBotao);

        JButton botaoMultiplicar = new JButton("*");
        botaoMultiplicar.setBounds(304, 363, 95, 60);
        botaoMultiplicar.setFont(fonteBotao);

        JButton botao1 = new JButton("1");
        botao1.setBounds(10, 426, 95, 60);
        botao1.setFont(fonteBotao);

        JButton botao2 = new JButton("2");
        botao2.setBounds(108, 426, 95, 60);
        botao2.setFont(fonteBotao);

        JButton botao3 = new JButton("3");
        botao3.setBounds(206, 426, 95, 60);
        botao3.setFont(fonteBotao);

        JButton botaoMenos = new JButton("-");
        botaoMenos.setBounds(304, 426, 95, 60);
        botaoMenos.setFont(fonteBotao);

        JButton botao0 = new JButton("0");
        botao0.setBounds(108, 489, 95, 60);
        botao0.setFont(fonteBotao);

        JButton botaoPonto = new JButton(".");
        botaoPonto.setBounds(206, 489, 95, 60);
        botaoPonto.setFont(fonteBotao);

        JButton botaoC = new JButton("C");
        botaoC.setBounds(10, 489, 95, 60);
        botaoC.setFont(fonteBotao);

        JButton botaoMais = new JButton("+");
        botaoMais.setBounds(304, 489, 95, 60);
        botaoMais.setFont(fonteBotao);

        JButton botaoIgual = new JButton("=");
        botaoIgual.setBounds(206, 552, 193, 60);
        botaoIgual.setFont(fonteBotao);

        JButton botaoDelete = new JButton("Delete");
        botaoDelete.setBounds(10, 552, 193, 60);
        botaoDelete.setFont(fonteBotao);

        JButton[] botoesNumeros = {
            botao7, botao8, botao9,
            botao4, botao5, botao6,
            botao1, botao2, botao3,
            botao0, botaoPonto
        };

        JButton[] botoesOperacoes = {
            botaoIgual, botaoC,
            botaoDelete, botaoMais,
            botaoMenos, botaoMultiplicar,
            botaoDividir
        };

        for (int i = 0; i < botoesNumeros.length; i++) {
            botoesNumeros[i].setBackground(new Color(55, 53, 56));
            botoesNumeros[i].setForeground(Color.WHITE);
            botoesNumeros[i].setFocusPainted(false);
            painel.add(botoesNumeros[i]);
        }

        for (int i = 0; i < botoesOperacoes.length; i++) {
            botoesOperacoes[i].setBackground(new Color(35, 33, 36));
            botoesOperacoes[i].setForeground(Color.WHITE);
            botoesOperacoes[i].setFocusPainted(false);
            painel.add(botoesOperacoes[i]);
        }

        botaoC.setBackground(new Color(142, 128, 255));
        botaoIgual.setBackground(new Color(75, 190, 245));
        botaoDelete.setBackground(new Color(235, 162, 26));

        ActionListener acaoNumeros = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JButton botaoClicado = (JButton) e.getSource();

                if (limparVisor) {
                    numeroAtual = "";
                    visor.setText("0");
                    limparVisor = false;
                }

                if (numeroAtual.equals("0")) {
                    numeroAtual = botaoClicado.getText();
                } else {
                    numeroAtual += botaoClicado.getText();
                }
                visor.setText(numeroAtual);
            }
        };

        botao0.addActionListener(acaoNumeros);
        botao1.addActionListener(acaoNumeros);
        botao2.addActionListener(acaoNumeros);
        botao3.addActionListener(acaoNumeros);
        botao4.addActionListener(acaoNumeros);
        botao5.addActionListener(acaoNumeros);
        botao6.addActionListener(acaoNumeros);
        botao7.addActionListener(acaoNumeros);
        botao8.addActionListener(acaoNumeros);
        botao9.addActionListener(acaoNumeros);

        botaoPonto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!numeroAtual.contains(".")){
                    numeroAtual += ".";
                }
                visor.setText(numeroAtual);
            }
        });

        ActionListener acaoOperacao = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JButton botaoClicado = (JButton) e.getSource();

                primeiroNumero = Double.parseDouble(numeroAtual);
                operacao = botaoClicado.getText();

                limparVisor = true;

                numeroAtual = "";
                visorOperacao.setText(String.valueOf(primeiroNumero) + " " + operacao);
                visor.setText(operacao);
            }
        };

        botaoMais.addActionListener(acaoOperacao);
        botaoMenos.addActionListener(acaoOperacao);
        botaoMultiplicar.addActionListener(acaoOperacao);
        botaoDividir.addActionListener(acaoOperacao);

        botaoIgual.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {

                    double segundoNumero = Double.parseDouble(numeroAtual);
                    double resultado = 0;

                    switch (operacao) {
                        case "+":
                            resultado = primeiroNumero + segundoNumero;
                            break;

                        case "-":
                            resultado = primeiroNumero - segundoNumero;
                            break;

                        case "*":
                            resultado = primeiroNumero * segundoNumero;
                            break;

                        case "/":
                            if (segundoNumero == 0) {
                                throw new DivisaoPorZeroException();
                            } else {
                                resultado = primeiroNumero / segundoNumero;
                                break;
                            }
                    }

                    visor.setText(String.valueOf(resultado));
                    numeroAtual = String.valueOf(resultado);

                    if (resultado == 67) {
                        throw new SixSevenException();
                    }

                    if (resultado == 33) {
                        throw new KiraException();
                    }

                    visorOperacao.setText(
                        String.valueOf(primeiroNumero)
                        + " "
                        + operacao
                        + " "
                        + segundoNumero
                    );

                    limparVisor = true;

                } catch (NumberFormatException erro) {

                    JOptionPane.showMessageDialog(
                        calculadora,
                        "Digite o segundo número antes de apertar igual.",
                        "Entrada inválida",
                        JOptionPane.WARNING_MESSAGE
                    );

                    visor.setText("0");
                    numeroAtual = "0";

                } catch (SixSevenException erro) {

                    JOptionPane.showMessageDialog(
                        calculadora,
                        erro.getMessage(),
                        "67 67 67 67",
                        JOptionPane.WARNING_MESSAGE

                    );

                    numeroAtual = "0";
                    primeiroNumero = 0;
                    operacao = "";
                    visor.setText("0");
                    visorOperacao.setText("");
                    limparVisor = true;

                } catch (KiraException erro) {

                    JOptionPane.showMessageDialog(
                        calculadora,
                        erro.getMessage(),
                        "Você sabe demais!",
                        JOptionPane.ERROR_MESSAGE

                    );

                    System.exit(0);

                }catch (DivisaoPorZeroException erro) {

                    JOptionPane.showMessageDialog(
                        calculadora,
                        erro.getMessage(),
                        "Erro de Divisão",
                        JOptionPane.WARNING_MESSAGE
                    );
                }
            }
        });

        botaoC.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                numeroAtual = "";
                primeiroNumero = 0;
                operacao = "";
                visor.setText("0");
                visorOperacao.setText("");
            }
        });

        botaoDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (numeroAtual.length() > 1) {
                    numeroAtual = numeroAtual.substring(0, numeroAtual.length() - 1);
                } else {
                    numeroAtual = "0";
                }
                visor.setText(numeroAtual);
            }
        });

        painel.add(visor);
        painel.add(visorOperacao);
        calculadora.add(painel);
        calculadora.setVisible(true);
    }
}