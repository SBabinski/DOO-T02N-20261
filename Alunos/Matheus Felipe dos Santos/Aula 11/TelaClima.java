import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class TelaClima extends JFrame {

    private ClimaServico service;

    private JTextField campoCidade;
    private JButton botaoBuscar;

    private JLabel iconeClima;

    private JLabel temperaturaLabel;
    private JLabel climaLabel;
    private JLabel cidadeLabel;

    private JLabel umidadeValor;
    private JLabel ventoValor;
    private JLabel precipitacaoValor;
    private JLabel maximaMinimaValor;

    public TelaClima() {

        service = new ClimaServico();

        UIManager.put("Button.focus", new Color(0, 0, 0, 0));

        setTitle("Weather App");

        setSize(1000, 700);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLocationRelativeTo(null);

      JPanel painelPrincipal = new JPanel() {

    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        GradientPaint gradient =
                new GradientPaint(
                        0,
                        0,
                        new Color(80, 140, 255),
                        getWidth(),
                        getHeight(),
                        new Color(170, 210, 255));

        g2.setPaint(gradient);

        g2.fillRect(
                0,
                0,
                getWidth(),
                getHeight());

    }

};

        painelPrincipal.setLayout(new BorderLayout());

        JPanel painelTopo = new JPanel();

       painelTopo.setOpaque(false);

        painelTopo.setBorder(new EmptyBorder(20, 20, 20, 20));

        painelTopo.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));

JPanel barraBusca = new JPanel();

barraBusca.setLayout(new BorderLayout());

barraBusca.setBackground(Color.WHITE);

barraBusca.setPreferredSize(new Dimension(420, 55));

barraBusca.setBorder(
        BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(
                        new Color(230, 230, 230),
                        1,
                        true),
                BorderFactory.createEmptyBorder(
                        0,
                        20,
                        0,
                        0)));

        campoCidade = new JTextField();

        campoCidade.setToolTipText(
        "Digite o nome da cidade");

        campoCidade.setBorder(null);

        campoCidade.setOpaque(false);

        campoCidade.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        20));

        barraBusca.add(
                campoCidade,
                BorderLayout.CENTER);

        ImageIcon lupa = new ImageIcon("images/search.png");

        Image img =
        lupa.getImage().getScaledInstance(
                30,
                30,
                Image.SCALE_SMOOTH);

        
        botaoBuscar = new BotaoRedondo(null);

        botaoBuscar.setText("🔍");

        botaoBuscar.setFont(
                new Font(
                        "Segoe UI Emoji",
                        Font.PLAIN,
                        22));

        botaoBuscar.setForeground(Color.WHITE);
                botaoBuscar.setCursor(
                        new Cursor(Cursor.HAND_CURSOR));

        JPanel grupoBusca = new JPanel();

        grupoBusca.setOpaque(false);

        grupoBusca.setLayout(
        new FlowLayout(
                FlowLayout.CENTER,
                5,
                0));

        grupoBusca.add(barraBusca);

        grupoBusca.add(botaoBuscar);

        painelTopo.add(grupoBusca);

        painelTopo.add(Box.createHorizontalStrut(15));

        JPanel painelCentro = new JPanel();

        painelCentro.setLayout(new BorderLayout());

        painelCentro.setOpaque(false);

        JPanel painelEsquerdo = new JPanel();

        painelEsquerdo.setLayout(new BoxLayout(painelEsquerdo, BoxLayout.Y_AXIS));

        painelEsquerdo.setOpaque(false);

        painelEsquerdo.setBorder(new EmptyBorder(40, 40, 40, 40));

        iconeClima = new JLabel();

        iconeClima.setAlignmentX(Component.CENTER_ALIGNMENT);

        mostrarIcone("cloud");

        temperaturaLabel = new JLabel("--°C");

        temperaturaLabel.setFont(new Font("Segoe UI", Font.BOLD, 90));

        temperaturaLabel.setForeground(new Color(30, 30, 30));

        temperaturaLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        climaLabel = new JLabel("Pesquise uma cidade");

        climaLabel.setFont(new Font("Segoe UI", Font.PLAIN, 32));

        climaLabel.setForeground(new Color(90, 90, 90));

        climaLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        cidadeLabel = new JLabel("");

        cidadeLabel.setFont(new Font("Segoe UI", Font.PLAIN, 24));

        cidadeLabel.setForeground(new Color(120, 120, 120));

        cidadeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        painelEsquerdo.add(Box.createVerticalStrut(40));

        painelEsquerdo.add(iconeClima);

        painelEsquerdo.add(Box.createVerticalStrut(30));

        painelEsquerdo.add(temperaturaLabel);

        painelEsquerdo.add(Box.createVerticalStrut(20));

        painelEsquerdo.add(climaLabel);

        painelEsquerdo.add(Box.createVerticalStrut(10));

        painelEsquerdo.add(cidadeLabel);

        JPanel painelDireito = new JPanel();

       painelDireito.setLayout(new GridLayout(2, 2, 25, 25));

        painelDireito.setOpaque(false);

        painelDireito.setBorder(new EmptyBorder(40, 40, 40, 40));

        umidadeValor = new JLabel("--");

        ventoValor = new JLabel("--");

        precipitacaoValor = new JLabel("--");

        maximaMinimaValor = new JLabel("-- / --");

       painelDireito.add(criarCard("Umidade", umidadeValor, "images/humidity.png"));

        painelDireito.add(criarCard("Vento", ventoValor, "images/wind.png"));

        painelDireito.add(criarCard("Precipitação", precipitacaoValor, "images/rain.png"));

        painelDireito.add(criarCard("Máx / Mín", maximaMinimaValor, "images/temp.png"));

        painelCentro.add(painelEsquerdo, BorderLayout.WEST);

        painelCentro.add(painelDireito, BorderLayout.CENTER);

        painelPrincipal.add(painelTopo, BorderLayout.NORTH);

        painelPrincipal.add(painelCentro, BorderLayout.CENTER);

        add(painelPrincipal);

        configurarEventos();

        setVisible(true);

    }

        private String traduzirCondicao(String condicao) {

        String texto = condicao.toLowerCase();

        if (texto.contains("rain"))
                return "Chuva";

        if (texto.contains("storm"))
                return "Tempestade";

        if (texto.contains("snow"))
                return "Neve";

        if (texto.contains("fog"))
                return "Neblina";

        if (texto.contains("mist"))
                return "Névoa";

        if (texto.contains("clear"))
                return "Céu Limpo";

        if (texto.contains("partially"))
                return "Parcialmente Nublado";

        if (texto.contains("cloud"))
                return "Nublado";

        return condicao;
        }

        private JPanel criarCard(
                String titulo,
                JLabel valor,
                String caminhoIcone) {

            JPanel card = new JPanel() {

                @Override
                protected void paintComponent(Graphics g) {

                    Graphics2D g2 = (Graphics2D) g;

                    g2.setRenderingHint(
                            RenderingHints.KEY_ANTIALIASING,
                            RenderingHints.VALUE_ANTIALIAS_ON);

                    g2.setColor(Color.WHITE);

                    g2.fillRoundRect(
                            0,
                            0,
                            getWidth(),
                            getHeight(),
                            35,
                            35);

                    super.paintComponent(g);

                }

            };

            card.setOpaque(false);

            card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));

            card.setBorder(new EmptyBorder(20, 20, 20, 20));

            JLabel icone = new JLabel();

            ImageIcon icon = new ImageIcon(caminhoIcone);

            Image imagem =
                    icon.getImage().getScaledInstance(
                            28,
                            28,
                            Image.SCALE_SMOOTH);

            icone.setIcon(new ImageIcon(imagem));

            icone.setAlignmentX(Component.CENTER_ALIGNMENT);

            JLabel tituloLabel = new JLabel(titulo);

            tituloLabel.setFont(
                    new Font(
                            "Segoe UI",
                            Font.BOLD,
                            18));

            tituloLabel.setForeground(
                    new Color(100, 100, 100));

            valor.setFont(
                    new Font(
                            "Segoe UI",
                            Font.BOLD,
                            26));

            valor.setForeground(
                    new Color(40, 40, 40));

            tituloLabel.setAlignmentX(
                    Component.CENTER_ALIGNMENT);

            valor.setAlignmentX(
                    Component.CENTER_ALIGNMENT);

            card.add(Box.createVerticalGlue());

            card.add(icone);

            card.add(Box.createVerticalStrut(10));

            card.add(tituloLabel);

            card.add(Box.createVerticalStrut(12));

            card.add(valor);

            card.add(Box.createVerticalGlue());

            return card;

        }

        private void mostrarIcone(String tipo) {

                String caminho = "images/cloud.png";

                tipo = tipo.toLowerCase();

                if (tipo.contains("rain")) {

        caminho = "images/rain.png";

        } else if (tipo.contains("storm")
                || tipo.contains("thunder")) {

        caminho = "images/storm.png";

        } else if (tipo.contains("clear")) {

        caminho = "images/sun.png";

        } else if (tipo.contains("partially")) {

        caminho = "images/clearcloud.png";

        } else if (tipo.contains("cloud")) {

        caminho = "images/cloud.png";

        }

        ImageIcon icon = new ImageIcon(caminho);

        Image imagem =
                icon.getImage().getScaledInstance(
                        180,
                        180,
                        Image.SCALE_SMOOTH);

        iconeClima.setIcon(new ImageIcon(imagem));

    }

    private void configurarEventos() {

        botaoBuscar.addActionListener(e -> {

           String cidade = campoCidade.getText().trim();

                if (cidade.isEmpty()) {
                JOptionPane.showMessageDialog(
                        this,
                        "Digite uma cidade.");
                return;
                }

            Clima clima = service.buscarClima(cidade);

                if (clima != null) {

                temperaturaLabel.setText(
                        String.format("%.1f°C",
                                clima.getTemperatura()));


                System.out.println(clima.getCondicao());
                
                climaLabel.setText(
                        traduzirCondicao(
                                clima.getCondicao()));

                cidadeLabel.setText(
                        clima.getCidade());

                umidadeValor.setText(
                        String.format("%.0f%%",
                                clima.getUmidade()));

                ventoValor.setText(
                        String.format("%.1f km/h",
                                clima.getVelocidadeVento()));
                

                precipitacaoValor.setText(
                        String.format("%.1f mm",
                                clima.getPrecipitacao()));

                maximaMinimaValor.setText(
                        String.format("%.1f° / %.1f°",
                                clima.getTempMax(),
                                clima.getTempMin()));

                mostrarIcone(clima.getCondicao());

                } else {

                JOptionPane.showMessageDialog(
                        this,
                        "Cidade não encontrada.");

                }

        });

        }

 }