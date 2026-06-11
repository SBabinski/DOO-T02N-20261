package attDOO.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class AppView {

    public JFrame frame = new JFrame("Aplicativo Clima/Tempo");

    public JPanel mainPanel = new JPanel(new BorderLayout());

    public JLabel tituloApp = new JLabel("Clima Tempo");

    public JLabel tituloCidade = new JLabel("Cidade");

    public JTextField campoCidade = new JTextField();

    public JButton botaoBuscar = new JButton("Buscar");

    public JPanel cardResultado = new JPanel();

    public JLabel lblCidade = new JLabel("--");

    public JLabel lblTemperatura = new JLabel("--");
    public JLabel lblMaxima = new JLabel("--");
    public JLabel lblMinima = new JLabel("--");
    public JLabel lblUmidade = new JLabel("--");
    public JLabel lblCondicao = new JLabel("--");
    public JLabel lblPrecipitacao = new JLabel("--");
    public JLabel lblVento = new JLabel("--");
    public JLabel lblDirecao = new JLabel("--");

    public AppView() {
        construirAplicativo();
    }

    public void construirAplicativo() {

        frame.setSize(1100, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        mainPanel.setBackground(new Color(235, 242, 250));

        JPanel wrapper = new JPanel();
        wrapper.setBackground(new Color(235, 242, 250));
        wrapper.setLayout(new BoxLayout(wrapper, BoxLayout.Y_AXIS));

        JPanel cardBusca = new JPanel();

        cardBusca.setBackground(Color.WHITE);
        cardBusca.setPreferredSize(new Dimension(650, 320));
        cardBusca.setMaximumSize(new Dimension(650, 320));

        cardBusca.setLayout(new BoxLayout(cardBusca, BoxLayout.Y_AXIS));

        cardBusca.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(new Color(220, 220, 220), 1, true),
                new EmptyBorder(30, 35, 30, 35)));

        tituloApp.setFont(new Font("Segoe UI", Font.BOLD, 32));
        tituloApp.setForeground(new Color(45, 62, 80));
        tituloApp.setAlignmentX(JLabel.CENTER_ALIGNMENT);

        tituloCidade.setFont(new Font("Segoe UI", Font.BOLD, 16));
        tituloCidade.setForeground(new Color(70, 70, 70));
        tituloCidade.setAlignmentX(JLabel.CENTER_ALIGNMENT);

        campoCidade.setMaximumSize(new Dimension(500, 45));
        campoCidade.setPreferredSize(new Dimension(500, 45));
        campoCidade.setFont(new Font("Segoe UI", Font.PLAIN, 16));

        campoCidade.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(new Color(200, 200, 200), 1, true),
                new EmptyBorder(10, 12, 10, 12)));

        campoCidade.setText("Digite aqui....");

        botaoBuscar.setAlignmentX(JButton.CENTER_ALIGNMENT);
        botaoBuscar.setFont(new Font("Segoe UI", Font.BOLD, 16));
        botaoBuscar.setBackground(new Color(52, 152, 219));
        botaoBuscar.setForeground(Color.WHITE);
        botaoBuscar.setFocusPainted(false);
        botaoBuscar.setBorder(new EmptyBorder(12, 30, 12, 30));
        botaoBuscar.setMaximumSize(new Dimension(500, 45));

        cardBusca.add(tituloApp);
        cardBusca.add(Box.createRigidArea(new Dimension(0, 40)));
        cardBusca.add(tituloCidade);
        cardBusca.add(Box.createRigidArea(new Dimension(0, 10)));
        cardBusca.add(campoCidade);
        cardBusca.add(Box.createRigidArea(new Dimension(0, 25)));
        cardBusca.add(botaoBuscar);

        cardResultado.setBackground(Color.WHITE);

        cardResultado.setPreferredSize(new Dimension(900, 380));
        cardResultado.setMaximumSize(new Dimension(900, 380));

        cardResultado.setLayout(new BorderLayout(15, 15));

        cardResultado.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(new Color(220, 220, 220), 1, true),
                new EmptyBorder(25, 25, 25, 25)));

        lblCidade.setHorizontalAlignment(SwingConstants.CENTER);
        lblCidade.setFont(new Font("Segoe UI", Font.BOLD, 28));
        lblCidade.setForeground(new Color(45, 62, 80));

        cardResultado.add(lblCidade, BorderLayout.NORTH);

        JPanel painelGrid = new JPanel(new GridLayout(2, 4, 15, 15));
        painelGrid.setBackground(Color.WHITE);

        painelGrid.add(criarInfoCard("Temperatura", lblTemperatura));
        painelGrid.add(criarInfoCard("Máxima", lblMaxima));
        painelGrid.add(criarInfoCard("Mínima", lblMinima));
        painelGrid.add(criarInfoCard("Umidade", lblUmidade));
        painelGrid.add(criarInfoCard("Condição", lblCondicao));
        painelGrid.add(criarInfoCard("Chuva", lblPrecipitacao));
        painelGrid.add(criarInfoCard("Vento", lblVento));
        painelGrid.add(criarInfoCard("Direção", lblDirecao));

        cardResultado.add(painelGrid, BorderLayout.CENTER);

        cardBusca.setAlignmentX(JPanel.CENTER_ALIGNMENT);
        cardResultado.setAlignmentX(JPanel.CENTER_ALIGNMENT);

        wrapper.add(Box.createVerticalGlue());
        wrapper.add(cardBusca);
        wrapper.add(Box.createRigidArea(new Dimension(0, 25)));
        wrapper.add(cardResultado);
        wrapper.add(Box.createVerticalGlue());

        mainPanel.add(wrapper, BorderLayout.CENTER);

        frame.add(mainPanel);
        frame.setVisible(true);
    }

    private JPanel criarInfoCard(String titulo, JLabel valor) {

        JPanel card = new JPanel();

        card.setLayout(new BorderLayout());

        card.setBackground(new Color(245, 248, 252));

        card.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(new Color(220, 220, 220), 1, true),
                new EmptyBorder(15, 15, 15, 15)));

        JLabel lblTitulo = new JLabel(titulo);

        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);

        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 14));

        valor.setHorizontalAlignment(SwingConstants.CENTER);

        valor.setFont(new Font("Segoe UI", Font.BOLD, 18));

        card.add(lblTitulo, BorderLayout.NORTH);

        card.add(valor, BorderLayout.CENTER);

        return card;
    }
}