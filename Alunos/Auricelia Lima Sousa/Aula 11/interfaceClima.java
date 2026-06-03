import javax.swing.*;
import java.awt.*;

public class interfaceClima extends JFrame {
    private JTextField txtCidade;
    private JTextArea areaResultado;
    private ServicoClima servico;

    public interfaceClima() {
        servico = new ServicoClima();
        setTitle("Clima"); 
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10)); 

        txtCidade = new JTextField();
        txtCidade.setFont(new Font("Arial", Font.BOLD, 22));
        txtCidade.setHorizontalAlignment(JTextField.CENTER);
        add(txtCidade, BorderLayout.NORTH);

        JButton btnBuscar = new JButton("CONSULTAR");
        btnBuscar.setFont(new Font("Arial", Font.PLAIN, 18));
        btnBuscar.addActionListener(e -> realizarBusca());
        add(btnBuscar, BorderLayout.CENTER);

        areaResultado = new JTextArea(10, 25);
        areaResultado.setEditable(false);
        areaResultado.setFont(new Font("Monospaced", Font.PLAIN, 14));
        areaResultado.setBorder(BorderFactory.createTitledBorder("Resultado"));
        add(new JScrollPane(areaResultado), BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void realizarBusca() {
        try {
            String cidade = txtCidade.getText();
            if (cidade.isEmpty()) throw new ClimaException("Informe a cidade!");

            areaResultado.setText("Buscando...");
            Clima d = servico.buscarDados(cidade);

            areaResultado.setText("CIDADE: " + d.cidade.toUpperCase() + "\n" +
                                 "CONDICAO: " + d.condicao + "\n" +
                                 "TEMP. ATUAL: " + d.temperatura + "C\n" +
                                 "MAX: " + d.tempMaxima + "C | MIN: " + d.tempMinima + "C\n" +
                                 "UMIDADE: " + d.umidade + "%\n" +
                                 "CHUVA: " + d.precipitacao + "mm\n" +
                                 "VENTO: " + d.velocidadeVento + "km/h | DIR: " + d.direcaoVento);

        } catch (ClimaException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            areaResultado.setText("");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new interfaceClima());
    }
}