import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.RoundRectangle2D;

public class InterfaceGrafica extends JFrame {

    // ── Paleta ──────────────────────────────────────────────
    private static final Color FUNDO_TOPO      = new Color(15, 32, 65);
    private static final Color FUNDO_RODAPE    = new Color(22, 48, 95);
    private static final Color AZUL_DESTAQUE   = new Color(56, 152, 236);
    private static final Color AZUL_HOVER      = new Color(36, 120, 200);
    private static final Color CARD_BG         = new Color(28, 55, 110, 210);
    private static final Color CARD_BORDA      = new Color(56, 152, 236, 80);
    private static final Color TEXTO_PRIMARIO  = new Color(230, 240, 255);
    private static final Color TEXTO_SECUNDARIO = new Color(140, 175, 220);
    private static final Color VERMELHO_ERRO   = new Color(220, 80, 80);

    // ── Fontes ──────────────────────────────────────────────
    private static final Font FONTE_TITULO     = new Font("SansSerif", Font.BOLD, 26);
    private static final Font FONTE_SUBTITULO  = new Font("SansSerif", Font.PLAIN, 13);
    private static final Font FONTE_TEMP_GRANDE = new Font("SansSerif", Font.BOLD, 64);
    private static final Font FONTE_TEMP_UNIT  = new Font("SansSerif", Font.PLAIN, 28);
    private static final Font FONTE_CIDADE     = new Font("SansSerif", Font.BOLD, 20);
    private static final Font FONTE_CONDICAO   = new Font("SansSerif", Font.PLAIN, 15);
    private static final Font FONTE_DETALHE_LABEL = new Font("SansSerif", Font.PLAIN, 11);
    private static final Font FONTE_DETALHE_VALOR = new Font("SansSerif", Font.BOLD, 15);
    private static final Font FONTE_CAMPO      = new Font("SansSerif", Font.PLAIN, 14);
    private static final Font FONTE_BOTAO      = new Font("SansSerif", Font.BOLD, 14);

    // ── Componentes ─────────────────────────────────────────
    private JTextField campoCidade;
    private JButton    botaoBuscar;
    private JPanel     painelResultado;
    private JLabel     lblEmoji;
    private JLabel     lblTempAtual;
    private JLabel     lblCidade;
    private JLabel     lblEndereco;
    private JLabel     lblCondicao;
    private JLabel     lblMaxMin;
    private JLabel     lblUmidade;
    private JLabel     lblPrecip;
    private JLabel     lblVento;
    private JLabel     lblStatus;
    private JPanel     painelCards;

    private ServicoClima servico;

    public InterfaceGrafica() {
        try {
            servico = new ServicoClima();
        } catch (ErroClima e) {
            JOptionPane.showMessageDialog(null,
                    e.getMessage(), "Configuração necessária", JOptionPane.WARNING_MESSAGE);
        }

        configurarJanela();
        construirInterface();
        setVisible(true);
    }

    // ── Configuração da janela ───────────────────────────────
    private void configurarJanela() {
        setTitle("Previsão do Tempo");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(520, 680);
        setMinimumSize(new Dimension(480, 600));
        setLocationRelativeTo(null);
        setBackground(FUNDO_TOPO);

        try { UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); }
        catch (Exception ignored) {}
    }

    // ── Construção da interface ──────────────────────────────
    private void construirInterface() {
        JPanel raiz = new JPanel(new BorderLayout()) {
            @Override protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                GradientPaint degradê = new GradientPaint(
                        0, 0, FUNDO_TOPO,
                        0, getHeight(), FUNDO_RODAPE);
                g2.setPaint(degradê);
                g2.fillRect(0, 0, getWidth(), getHeight());
                g2.dispose();
            }
        };
        raiz.setOpaque(false);

        raiz.add(criarCabecalho(), BorderLayout.NORTH);
        raiz.add(criarAreaCentral(), BorderLayout.CENTER);
        raiz.add(criarRodape(), BorderLayout.SOUTH);

        setContentPane(raiz);
    }

    private JPanel criarCabecalho() {
        JPanel painel = new JPanel();
        painel.setLayout(new BoxLayout(painel, BoxLayout.Y_AXIS));
        painel.setOpaque(false);
        painel.setBorder(new EmptyBorder(30, 30, 16, 30));

        JLabel titulo = new JLabel("☁  Previsão do Tempo");
        titulo.setFont(FONTE_TITULO);
        titulo.setForeground(TEXTO_PRIMARIO);
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel subtitulo = new JLabel("Condições climáticas em tempo real");
        subtitulo.setFont(FONTE_SUBTITULO);
        subtitulo.setForeground(TEXTO_SECUNDARIO);
        subtitulo.setAlignmentX(Component.CENTER_ALIGNMENT);

        JSeparator separador = new JSeparator();
        separador.setForeground(new Color(56, 152, 236, 60));
        separador.setMaximumSize(new Dimension(Integer.MAX_VALUE, 1));

        painel.add(titulo);
        painel.add(Box.createVerticalStrut(4));
        painel.add(subtitulo);
        painel.add(Box.createVerticalStrut(20));
        painel.add(criarBarraDeBusca());
        painel.add(Box.createVerticalStrut(16));
        painel.add(separador);

        return painel;
    }

    private JPanel criarBarraDeBusca() {
        JPanel painel = new JPanel(new BorderLayout(10, 0));
        painel.setOpaque(false);
        painel.setMaximumSize(new Dimension(460, 48));

        campoCidade = new JTextField() {
            @Override protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(new Color(255, 255, 255, 20));
                g2.fill(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), 10, 10));
                g2.setColor(AZUL_DESTAQUE.darker());
                g2.draw(new RoundRectangle2D.Double(0, 0, getWidth()-1, getHeight()-1, 10, 10));
                g2.dispose();
                super.paintComponent(g);
            }
        };
        campoCidade.setFont(FONTE_CAMPO);
        campoCidade.setForeground(TEXTO_PRIMARIO);
        campoCidade.setCaretColor(TEXTO_PRIMARIO);
        campoCidade.setOpaque(false);
        campoCidade.setBorder(new EmptyBorder(8, 14, 8, 14));
        campoCidade.setToolTipText("Ex: São Paulo, BR ou London, UK");
        campoCidade.putClientProperty("JTextField.placeholderText", "Digite o nome da cidade...");

        campoCidade.addActionListener(e -> iniciarBusca());

        botaoBuscar = new JButton("Buscar") {
            private boolean hover = false;
            { addMouseListener(new MouseAdapter() {
                public void mouseEntered(MouseEvent e) { hover = true; repaint(); }
                public void mouseExited(MouseEvent e)  { hover = false; repaint(); }
            }); }
            @Override protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(hover ? AZUL_HOVER : AZUL_DESTAQUE);
                g2.fill(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), 10, 10));
                g2.setColor(TEXTO_PRIMARIO);
                g2.setFont(FONTE_BOTAO);
                FontMetrics fm = g2.getFontMetrics();
                int x = (getWidth()  - fm.stringWidth(getText())) / 2;
                int y = (getHeight() + fm.getAscent() - fm.getDescent()) / 2;
                g2.drawString(getText(), x, y);
                g2.dispose();
            }
        };
        botaoBuscar.setPreferredSize(new Dimension(90, 44));
        botaoBuscar.setContentAreaFilled(false);
        botaoBuscar.setBorderPainted(false);
        botaoBuscar.setFocusPainted(false);
        botaoBuscar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        botaoBuscar.addActionListener(e -> iniciarBusca());

        painel.add(campoCidade, BorderLayout.CENTER);
        painel.add(botaoBuscar, BorderLayout.EAST);
        return painel;
    }

    private JScrollPane criarAreaCentral() {
        painelResultado = new JPanel();
        painelResultado.setLayout(new BoxLayout(painelResultado, BoxLayout.Y_AXIS));
        painelResultado.setOpaque(false);
        painelResultado.setBorder(new EmptyBorder(16, 24, 16, 24));

        // Painel de temperatura principal
        JPanel painelPrincipal = criarPainelTemperatura();
        painelResultado.add(painelPrincipal);
        painelResultado.add(Box.createVerticalStrut(14));

        // Grid de 4 cards de detalhes
        painelCards = new JPanel(new GridLayout(2, 2, 10, 10));
        painelCards.setOpaque(false);
        painelCards.setMaximumSize(new Dimension(Short.MAX_VALUE, 220));
        painelResultado.add(painelCards);

        lblStatus = new JLabel("Informe uma cidade para ver as condições climáticas.", SwingConstants.CENTER);
        lblStatus.setFont(FONTE_CONDICAO);
        lblStatus.setForeground(TEXTO_SECUNDARIO);
        lblStatus.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblStatus.setBorder(new EmptyBorder(20, 0, 0, 0));
        painelResultado.add(lblStatus);

        ocultarResultados();

        JScrollPane scroll = new JScrollPane(painelResultado);
        scroll.setOpaque(false);
        scroll.getViewport().setOpaque(false);
        scroll.setBorder(BorderFactory.createEmptyBorder());
        scroll.getVerticalScrollBar().setUnitIncrement(12);
        return scroll;
    }

    private JPanel criarPainelTemperatura() {
        JPanel painel = new JPanel() {
            @Override protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(CARD_BG);
                g2.fill(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), 16, 16));
                g2.setColor(CARD_BORDA);
                g2.draw(new RoundRectangle2D.Double(0, 0, getWidth()-1, getHeight()-1, 16, 16));
                g2.dispose();
            }
        };
        painel.setLayout(new BoxLayout(painel, BoxLayout.Y_AXIS));
        painel.setOpaque(false);
        painel.setBorder(new EmptyBorder(20, 24, 20, 24));
        painel.setMaximumSize(new Dimension(Short.MAX_VALUE, 260));

        lblEmoji = new JLabel("", SwingConstants.CENTER);
        lblEmoji.setFont(new Font("SansSerif", Font.PLAIN, 52));
        lblEmoji.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel linhaTemp = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        linhaTemp.setOpaque(false);
        lblTempAtual = new JLabel("--");
        lblTempAtual.setFont(FONTE_TEMP_GRANDE);
        lblTempAtual.setForeground(TEXTO_PRIMARIO);
        JLabel lblUnidade = new JLabel("°C");
        lblUnidade.setFont(FONTE_TEMP_UNIT);
        lblUnidade.setForeground(TEXTO_SECUNDARIO);
        lblUnidade.setBorder(new EmptyBorder(14, 4, 0, 0));
        linhaTemp.add(lblTempAtual);
        linhaTemp.add(lblUnidade);

        lblCidade = new JLabel("", SwingConstants.CENTER);
        lblCidade.setFont(FONTE_CIDADE);
        lblCidade.setForeground(TEXTO_PRIMARIO);
        lblCidade.setAlignmentX(Component.CENTER_ALIGNMENT);

        lblEndereco = new JLabel("", SwingConstants.CENTER);
        lblEndereco.setFont(FONTE_DETALHE_LABEL);
        lblEndereco.setForeground(TEXTO_SECUNDARIO);
        lblEndereco.setAlignmentX(Component.CENTER_ALIGNMENT);

        lblCondicao = new JLabel("", SwingConstants.CENTER);
        lblCondicao.setFont(FONTE_CONDICAO);
        lblCondicao.setForeground(new Color(180, 210, 255));
        lblCondicao.setAlignmentX(Component.CENTER_ALIGNMENT);

        painel.add(lblEmoji);
        painel.add(Box.createVerticalStrut(4));
        painel.add(linhaTemp);
        painel.add(Box.createVerticalStrut(2));
        painel.add(lblCidade);
        painel.add(Box.createVerticalStrut(2));
        painel.add(lblEndereco);
        painel.add(Box.createVerticalStrut(6));
        painel.add(lblCondicao);

        return painel;
    }

    private JPanel criarCard(String emoji, String rotulo, String valorInicial) {
        JPanel card = new JPanel() {
            @Override protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(CARD_BG);
                g2.fill(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), 14, 14));
                g2.setColor(CARD_BORDA);
                g2.draw(new RoundRectangle2D.Double(0, 0, getWidth()-1, getHeight()-1, 14, 14));
                g2.dispose();
            }
        };
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setOpaque(false);
        card.setBorder(new EmptyBorder(14, 14, 14, 14));

        JLabel emojiLabel = new JLabel(emoji);
        emojiLabel.setFont(new Font("SansSerif", Font.PLAIN, 22));
        emojiLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel rotuloLabel = new JLabel(rotulo.toUpperCase());
        rotuloLabel.setFont(FONTE_DETALHE_LABEL);
        rotuloLabel.setForeground(TEXTO_SECUNDARIO);
        rotuloLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel valorLabel = new JLabel(valorInicial);
        valorLabel.setFont(FONTE_DETALHE_VALOR);
        valorLabel.setForeground(TEXTO_PRIMARIO);
        valorLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        valorLabel.setName("valor");

        card.add(emojiLabel);
        card.add(Box.createVerticalStrut(6));
        card.add(rotuloLabel);
        card.add(Box.createVerticalStrut(2));
        card.add(valorLabel);

        return card;
    }

    private JPanel criarRodape() {
        JPanel painel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        painel.setOpaque(false);
        painel.setBorder(new EmptyBorder(4, 0, 12, 0));

        JLabel rodape = new JLabel("Dados: Visual Crossing Weather API  •  POO – 2025");
        rodape.setFont(new Font("SansSerif", Font.PLAIN, 11));
        rodape.setForeground(new Color(100, 140, 190));
        painel.add(rodape);
        return painel;
    }

    // ── Lógica de busca ─────────────────────────────────────
    private void iniciarBusca() {
        String cidade = campoCidade.getText().trim();
        if (cidade.isEmpty()) {
            campoCidade.requestFocus();
            return;
        }
        if (servico == null) {
            exibirErro("Serviço indisponível. Configure a chave de API.");
            return;
        }

        botaoBuscar.setEnabled(false);
        campoCidade.setEnabled(false);
        exibirStatus("Consultando " + cidade + "...");

        SwingWorker<DadosClima, Void> worker = new SwingWorker<>() {
            @Override protected DadosClima doInBackground() throws Exception {
                return servico.consultarClima(cidade);
            }
            @Override protected void done() {
                botaoBuscar.setEnabled(true);
                campoCidade.setEnabled(true);
                try {
                    exibirResultado(get());
                } catch (java.util.concurrent.ExecutionException ex) {
                    Throwable causa = ex.getCause();
                    String msg = (causa instanceof ErroClima)
                            ? causa.getMessage()
                            : "Erro inesperado. Tente novamente.";
                    exibirErro(msg);
                } catch (Exception ex) {
                    exibirErro("Operação cancelada.");
                }
            }
        };
        worker.execute();
    }

    // ── Exibição dos dados ───────────────────────────────────
    private void exibirResultado(DadosClima dados) {
        lblStatus.setVisible(false);

        lblEmoji.setText(resolverEmoji(dados.getCondicao()));
        lblTempAtual.setText(String.format("%.1f", dados.getTemperaturaAtual()));
        lblCidade.setText(dados.getCidade());
        lblEndereco.setText(dados.getEnderecoResolvido());
        lblCondicao.setText(dados.getCondicao());
        lblCondicao.setForeground(new Color(180, 210, 255));

        String maxMin = String.format("%.1f° / %.1f°",
                dados.getTemperaturaMaxima(), dados.getTemperaturaMinima());
        String umidade = String.format("%.0f%%", dados.getUmidade());
        String precip = dados.getPrecipitacao() > 0
                ? String.format("%.1f mm", dados.getPrecipitacao())
                : "Sem precipitação";
        String vento = String.format("%.1f km/h  %s", dados.getVelocidadeVento(), dados.getDirecaoCardinal());

        painelCards.removeAll();
        painelCards.add(criarCard("🌡️", "Máx / Mín", maxMin));
        painelCards.add(criarCard("💧", "Umidade", umidade));
        painelCards.add(criarCard("🌧", "Precipitação", precip));
        painelCards.add(criarCard("💨", "Vento", vento));

        painelCards.setVisible(true);
        painelResultado.revalidate();
        painelResultado.repaint();
    }

    private void exibirErro(String mensagem) {
        ocultarResultados();
        lblCondicao.setText("");
        lblCidade.setText("");
        lblEndereco.setText("");
        lblEmoji.setText("⚠️");
        lblTempAtual.setText("--");

        lblStatus.setForeground(VERMELHO_ERRO);
        lblStatus.setText("<html><center>" + mensagem + "</center></html>");
        lblStatus.setVisible(true);
        painelCards.setVisible(false);
    }

    private void exibirStatus(String texto) {
        lblStatus.setForeground(TEXTO_SECUNDARIO);
        lblStatus.setText(texto);
        lblStatus.setVisible(true);
        painelCards.setVisible(false);
        lblTempAtual.setText("--");
        lblEmoji.setText("⏳");
    }

    private void ocultarResultados() {
        lblTempAtual.setText("--");
        lblEmoji.setText("");
        lblCidade.setText("");
        lblEndereco.setText("");
        lblCondicao.setText("");
    }

    private String resolverEmoji(String condicao) {
        if (condicao == null) return "🌤";
        String c = condicao.toLowerCase();
        if (c.contains("tempestade") || c.contains("trovoada") || c.contains("thunder")) return "⛈";
        if (c.contains("neve") || c.contains("granizo") || c.contains("snow"))           return "❄️";
        if (c.contains("garoa") || c.contains("chuvisco") || c.contains("drizzle"))      return "🌦";
        if (c.contains("chuva") || c.contains("rain") || c.contains("shower"))           return "🌧";
        if (c.contains("overcast") || (c.contains("nublado") && !c.contains("parcial"))) return "☁️";
        if (c.contains("parcial") || c.contains("partly"))                               return "⛅";
        if (c.contains("neblina") || c.contains("névoa") || c.contains("fog"))          return "🌫";
        if (c.contains("vento") || c.contains("wind"))                                   return "🌬";
        if (c.contains("sol") || c.contains("ensolarado") || c.contains("clear"))       return "☀️";
        return "🌤";
    }
}
