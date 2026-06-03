package com.example;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class TelaClima extends JFrame {

    private JTextField campoCidade;
    private JTextArea areaResultado;

    public TelaClima() {

        setTitle("Consulta Climática");
        setSize(700, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel painelPrincipal = new JPanel();
        painelPrincipal.setLayout(new BorderLayout());
        painelPrincipal.setBackground(new Color(220, 240, 255));
        painelPrincipal.setBorder(new EmptyBorder(35,25,25,25));

        // TOPO

        JPanel painelTopo = new JPanel();
        painelTopo.setBackground(new Color(220, 240, 255));
        painelTopo.setLayout(new BoxLayout(painelTopo, BoxLayout.Y_AXIS));

        painelTopo.setBorder(
            BorderFactory.createEmptyBorder(20, 0, 20, 0));

        JLabel iconeClima = new JLabel("🌤");
        iconeClima.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 90));
        iconeClima.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel titulo = new JLabel("Consulta Climática");
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 28));
        titulo.setForeground(new Color(30, 90, 150));
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);

        painelTopo.add(iconeClima);
        painelTopo.add(Box.createVerticalStrut(5));
        painelTopo.add(titulo);

        // BUSCA

        JPanel painelBusca = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 20));
        painelBusca.setBackground(new Color(220, 240, 255));

        campoCidade = new JTextField(20);
        campoCidade.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        campoCidade.setPreferredSize(new Dimension(280, 45));

        JButton botaoBuscar = new JButton("Buscar Clima");
        botaoBuscar.setFont(new Font("Segoe UI", Font.BOLD, 16));
        botaoBuscar.setPreferredSize(new Dimension(180, 45));
        botaoBuscar.setBackground(new Color(70, 150, 255));
        botaoBuscar.setForeground(Color.WHITE);
        botaoBuscar.setFocusPainted(false);

        painelBusca.add(campoCidade);
        painelBusca.add(botaoBuscar);

        // RESULTADO

        areaResultado = new JTextArea();

        areaResultado.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 18));
        areaResultado.setEditable(false);
        areaResultado.setLineWrap(true);
        areaResultado.setWrapStyleWord(true);

        areaResultado.setBackground(new Color(245, 250, 255));

        areaResultado.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                new Color(180, 210, 240), 2),
                        new EmptyBorder(15, 15, 15, 15)));

        JScrollPane scroll = new JScrollPane(areaResultado);
        scroll.setBorder(null);

        // ORGANIZAÇÃO

        JPanel centro = new JPanel(new BorderLayout());
        centro.setBackground(new Color(220, 240, 255));

        centro.add(painelBusca, BorderLayout.NORTH);
        centro.add(scroll, BorderLayout.CENTER);

        painelPrincipal.add(painelTopo, BorderLayout.NORTH);
        painelPrincipal.add(centro, BorderLayout.CENTER);

        add(painelPrincipal);

        // EVENTO BOTÃO

        botaoBuscar.addActionListener(e -> buscarClima());

        setVisible(true);
    }

    private void buscarClima() {

        String cidade = campoCidade.getText().trim();

        if (!cidade.matches("^[A-Za-zÀ-ÿ\\s]+$")) {

            JOptionPane.showMessageDialog(
                    this,
                    "Digite uma cidade válida!",
                    "Aviso",
                    JOptionPane.WARNING_MESSAGE);

            return;
        }

        try {

            ClimaService service = new ClimaService();

            Clima clima = service.buscarDadosClima(cidade);

            String texto =
                    "📍 Cidade: " + cidade + "\n\n" +

                    "🌡 Temperatura Atual: "
                    + String.format("%.1f", clima.temperaturaAtual)
                    + " °C\n" +

                    "▲ Máxima: "
                    + String.format("%.1f", clima.temperaturaMaxima)
                    + " °C\n" +

                    "▼ Mínima: "
                    + String.format("%.1f", clima.temperaturaMinima)
                    + " °C\n\n" +

                    "💧 Umidade: "
                    + String.format("%.0f", clima.umidade)
                    + "%\n\n" +

                    "☁ Condição: "
                    + clima.condicao
                    + "\n\n" +

                    "🌧 Precipitação: "
                    + String.format("%.1f", clima.precipitacao)
                    + " mm\n\n" +

                    "🌀 Velocidade do Vento: "
                    + String.format("%.1f", clima.velocidadeVento)
                    + " km/h\n\n" +

                    "➜ Direção do Vento: "
                    + traduzirDirecaoVento(clima.direcaoVento);

            areaResultado.setText(texto);

        } catch (Exception e) {

            areaResultado.setText(
                    "Não foi possível consultar os dados climáticos."
            );
        }
    }

    private String traduzirDirecaoVento(double direcao) {

        if (direcao >= 337.5 || direcao < 22.5)
            return "Norte";

        if (direcao < 67.5)
            return "Nordeste";

        if (direcao < 112.5)
            return "Leste";

        if (direcao < 157.5)
            return "Sudeste";

        if (direcao < 202.5)
            return "Sul";

        if (direcao < 247.5)
            return "Sudoeste";

        if (direcao < 292.5)
            return "Oeste";

        return "Noroeste";
    }
}