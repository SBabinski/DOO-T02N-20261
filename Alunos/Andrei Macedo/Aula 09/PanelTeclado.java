import java.awt.*;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class PanelTeclado extends JPanel {

    private double primeiroNumero;
    private String operacaoGuardada;
    private boolean novoNumero;
    private JTextField visor;
    private JTextArea historico;

    public PanelTeclado(JTextField visor, JTextArea areaHistorico) {
        this.visor = visor; 
        this.historico = areaHistorico;
        this.novoNumero = true;

        setLayout(new GridLayout(5, 4, 5, 5));

        String[] botoes = {
            "7", "8", "9", "/",
            "4", "5", "6", "*",
            "1", "2", "3", "-",
            "0", "C", "DEL", "+",
            "="
        };

        for (String texto : botoes) {
            JButton button = new JButton(texto);
            button.setFocusPainted(false); 
            button.setBorderPainted(false); 
            button.setBackground(new Color(230, 230, 230)); 
            button.setForeground(new Color(30, 30, 30));
            button.setFont(new Font("Segoe UI", Font.PLAIN, 18));
            if (texto.equals("/") || texto.equals("*") || texto.equals("-") 
            || texto.equals("+") || texto.equals("=")) {
                button.setBackground(new Color(255, 159, 10));//laranja
                button.setForeground(Color.WHITE);
            }

            button.setFont(new Font("Segoe UI", Font.BOLD, 18));

            add(button);

            button.addActionListener(e -> executarLogica(texto));
        }
    }

    private void executarLogica(String textoButton) {
        if (textoButton.matches("[0-9]")) {
            if (novoNumero) {
                visor.setText(textoButton);
                novoNumero = false;
            } else {
                visor.setText(visor.getText() + textoButton);
            }
        }

        else if (textoButton.equals("C")) {
            visor.setText("0");
            primeiroNumero = 0;
            novoNumero = true;
        }

        else if (textoButton.equals("=")) {
            try {
                double segundoNumero = Double.parseDouble(visor.getText());
                double resultado = LogicaCalculadora.calcular(primeiroNumero, segundoNumero, operacaoGuardada);

                String resultadoFormatado = EfeitosUI.formatar(resultado);
                visor.setText(resultadoFormatado);
                historico.append(EfeitosUI.formatar(primeiroNumero) + " " + 
                                operacaoGuardada + " " + 
                                EfeitosUI.formatar(segundoNumero) + " = " + 
                                resultadoFormatado + "\n");
                                novoNumero = true;
            } catch (ErroDivisaoPorZeroException ex) {
                visor.setText("ERRO");
                novoNumero = true;
            }
        }

        else if (textoButton.equals("DEL")) {

            String textoAtual = visor.getText();
    
            if (textoAtual.length() > 0 && !textoAtual.equals("0")) {
       
                String novoTexto = textoAtual.substring(0, textoAtual.length() - 1);
        
                if (novoTexto.isEmpty()) {
                    visor.setText("0");
                    novoNumero = true;
                } else {
                visor.setText(novoTexto);
                }
            }
        }

        else { 
            primeiroNumero = Double.parseDouble(visor.getText());
            operacaoGuardada = textoButton;
            novoNumero = true;
        }

        visor.requestFocusInWindow();
    }
}
