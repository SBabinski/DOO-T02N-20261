package tela;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import modelo.PerfilUsuario;
import servico.ServicoPerfil;
import servico.ServicoTvMaze;

public class JanelaPrincipal extends JFrame {

    private final PerfilUsuario perfil;
    private final ServicoPerfil servicoPerfil;
    private final ServicoTvMaze servicoTvMaze;

    private TelaBusca    telaBusca;
    private TelaLista    telaFavoritas;
    private TelaLista    telaAssistidas;
    private TelaLista    telaQueroAssistir;

    private final CardLayout layoutCard = new CardLayout();
    private final JPanel     areaConteudo = new JPanel(layoutCard);
    private JButton          botaoAtivo = null;
    private JLabel           lblNomeUsuario;

    public JanelaPrincipal(PerfilUsuario perfil,
                           ServicoPerfil servicoPerfil,
                           ServicoTvMaze servicoTvMaze) {
        this.perfil        = perfil;
        this.servicoPerfil = servicoPerfil;
        this.servicoTvMaze = servicoTvMaze;

        configurarJanela();
        montarInterface();
        setVisible(true);
    }

    private void configurarJanela() {
        setTitle("Séries Online");
        setSize(1020, 700);
        setMinimumSize(new Dimension(780, 520));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override public void windowClosing(WindowEvent e) { salvarESair(); }
        });
        try { UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); }
        catch (Exception ignorado) {}
    }

    private void montarInterface() {
        JPanel raiz = new JPanel(new BorderLayout(0, 0));
        raiz.setBackground(TemaVisual.FUNDO_BASE);
        raiz.add(montarBarraLateral(), BorderLayout.WEST);
        raiz.add(montarAreaConteudo(), BorderLayout.CENTER);
        setContentPane(raiz);
    }

    private JPanel montarBarraLateral() {
        JPanel barra = new JPanel(new BorderLayout(0, 0));
        barra.setBackground(TemaVisual.FUNDO_CABECALHO);
        barra.setPreferredSize(new Dimension(200, 0));
        barra.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, TemaVisual.BORDA));

        JPanel logo = new JPanel(new FlowLayout(FlowLayout.LEFT, 14, 16));
        logo.setBackground(TemaVisual.FUNDO_CABECALHO);
        logo.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, TemaVisual.BORDA));
        JLabel icone = new JLabel("");
        icone.setFont(new Font("Dialog", Font.PLAIN, 26));
        JPanel textoLogo = new JPanel();
        textoLogo.setLayout(new BoxLayout(textoLogo, BoxLayout.Y_AXIS));
        textoLogo.setOpaque(false);
        JLabel lblApp = new JLabel("Séries Online");
        lblApp.setFont(new Font("Dialog", Font.BOLD, 14));
        lblApp.setForeground(TemaVisual.TEXTO_PRINCIPAL);
        JLabel lblVersao = new JLabel("Acompanhe suas séries");
        lblVersao.setFont(TemaVisual.FONTE_PEQUENA);
        lblVersao.setForeground(TemaVisual.TEXTO_DISCRETO);
        textoLogo.add(lblApp);
        textoLogo.add(lblVersao);
        logo.add(icone);
        logo.add(textoLogo);
        barra.add(logo, BorderLayout.NORTH);

        JPanel nav = new JPanel();
        nav.setLayout(new BoxLayout(nav, BoxLayout.Y_AXIS));
        nav.setBackground(TemaVisual.FUNDO_CABECALHO);
        nav.setBorder(new EmptyBorder(14, 0, 14, 0));

        JLabel lblSecao = ComponentesVisuais.labelSecao("MENU");
        lblSecao.setBorder(new EmptyBorder(6, 18, 10, 0));
        lblSecao.setAlignmentX(Component.LEFT_ALIGNMENT);
        nav.add(lblSecao);

        JButton btnBusca      = botaoNavegacao("", "Buscar",         "busca");
        JButton btnAssistidas = botaoNavegacao("", "Já Assistidas",  "assistidas");
        JButton btnQuero      = botaoNavegacao("", "Quero Assistir", "queroAssistir");
        JButton btnFavoritas  = botaoNavegacao("", "Favoritas",      "favoritas");

        nav.add(btnBusca);
        nav.add(btnAssistidas);
        nav.add(btnQuero);
        nav.add(btnFavoritas);

        ativarBotao(btnBusca);

        barra.add(nav, BorderLayout.CENTER);
        barra.add(montarRodapeUsuario(), BorderLayout.SOUTH);
        return barra;
    }

    private JButton botaoNavegacao(String icone, String rotulo, String chaveCard) {
        JButton btn = new JButton(rotulo) {
            @Override protected void paintComponent(Graphics g) {
                if (this == botaoAtivo) {
                    Graphics2D g2 = (Graphics2D) g.create();
                    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                    g2.setColor(new Color(TemaVisual.ACENTO.getRed(),
                                         TemaVisual.ACENTO.getGreen(),
                                         TemaVisual.ACENTO.getBlue(), 40));
                    g2.fillRoundRect(8, 2, getWidth()-16, getHeight()-4, 8, 8);
                    g2.dispose();
                }
                super.paintComponent(g);
            }
        };
        btn.setFont(new Font("Dialog", Font.PLAIN, 13));
        btn.setForeground(TemaVisual.TEXTO_SECUNDARIO);
        btn.setHorizontalAlignment(SwingConstants.LEFT);
        btn.setBorder(new EmptyBorder(10, 18, 10, 12));
        btn.setFocusPainted(false);
        btn.setOpaque(false);
        btn.setContentAreaFilled(false);
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btn.setMaximumSize(new Dimension(Integer.MAX_VALUE, 44));
        btn.setAlignmentX(Component.LEFT_ALIGNMENT);

        btn.addMouseListener(new MouseAdapter() {
            @Override public void mouseEntered(MouseEvent e) {
                if (btn != botaoAtivo) btn.setForeground(TemaVisual.TEXTO_PRINCIPAL);
            }
            @Override public void mouseExited(MouseEvent e) {
                if (btn != botaoAtivo) btn.setForeground(TemaVisual.TEXTO_SECUNDARIO);
            }
        });

        btn.addActionListener(e -> {
            ativarBotao(btn);
            layoutCard.show(areaConteudo, chaveCard);
            switch (chaveCard) {
                case "favoritas"     -> telaFavoritas.atualizar();
                case "assistidas"    -> telaAssistidas.atualizar();
                case "queroAssistir" -> telaQueroAssistir.atualizar();
            }
        });

        return btn;
    }

    private void ativarBotao(JButton btn) {
        if (botaoAtivo != null) {
            botaoAtivo.setFont(new Font("Dialog", Font.PLAIN, 13));
            botaoAtivo.setForeground(TemaVisual.TEXTO_SECUNDARIO);
            botaoAtivo.repaint();
        }
        botaoAtivo = btn;
        btn.setFont(new Font("Dialog", Font.BOLD, 13));
        btn.setForeground(TemaVisual.ACENTO_CLARO);
        btn.repaint();
    }

    private JPanel montarRodapeUsuario() {
        JPanel rodape = new JPanel(new BorderLayout(10, 0));
        rodape.setBackground(TemaVisual.FUNDO_CABECALHO);
        rodape.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(1, 0, 0, 0, TemaVisual.BORDA),
                new EmptyBorder(12, 16, 14, 14)));

        JLabel lblAvatar = new JLabel("") {
            @Override protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(new Color(TemaVisual.ACENTO.getRed(), TemaVisual.ACENTO.getGreen(),
                                      TemaVisual.ACENTO.getBlue(), 55));
                g2.fillOval(0, 0, getWidth()-1, getHeight()-1);
                g2.dispose();
                super.paintComponent(g);
            }
        };
        lblAvatar.setFont(new Font("Dialog", Font.PLAIN, 16));
        lblAvatar.setHorizontalAlignment(SwingConstants.CENTER);
        lblAvatar.setPreferredSize(new Dimension(36, 36));

        JPanel infoUsuario = new JPanel();
        infoUsuario.setLayout(new BoxLayout(infoUsuario, BoxLayout.Y_AXIS));
        infoUsuario.setOpaque(false);

        lblNomeUsuario = new JLabel(perfil.getNome() != null ? perfil.getNome() : "Usuário");
        lblNomeUsuario.setFont(TemaVisual.FONTE_NEGRITO);
        lblNomeUsuario.setForeground(TemaVisual.TEXTO_PRINCIPAL);
        infoUsuario.add(lblNomeUsuario);

        JButton btnEditar = new JButton("Editar perfil");
        btnEditar.setFont(TemaVisual.FONTE_PEQUENA);
        btnEditar.setForeground(TemaVisual.ACENTO_CLARO);
        btnEditar.setBackground(null);
        btnEditar.setBorder(null);
        btnEditar.setOpaque(false);
        btnEditar.setContentAreaFilled(false);
        btnEditar.setHorizontalAlignment(SwingConstants.LEFT);
        btnEditar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnEditar.addActionListener(e -> abrirJanelaPerfil());
        infoUsuario.add(btnEditar);

        rodape.add(lblAvatar,   BorderLayout.WEST);
        rodape.add(infoUsuario, BorderLayout.CENTER);
        return rodape;
    }

    private JPanel montarAreaConteudo() {
        areaConteudo.setBackground(TemaVisual.FUNDO_BASE);

        telaBusca        = new TelaBusca(servicoTvMaze, perfil, servicoPerfil);
        telaFavoritas    = new TelaLista("", "Favoritas",
                                PerfilUsuario::getFavoritas, PerfilUsuario::removerFavorita,
                                perfil, servicoPerfil);
        telaAssistidas   = new TelaLista("", "Já Assistidas",
                                PerfilUsuario::getAssistidas, PerfilUsuario::removerAssistida,
                                perfil, servicoPerfil);
        telaQueroAssistir = new TelaLista("", "Quero Assistir",
                                PerfilUsuario::getQueroAssistir, PerfilUsuario::removerQueroAssistir,
                                perfil, servicoPerfil);

        areaConteudo.add(telaBusca,         "busca");
        areaConteudo.add(telaFavoritas,     "favoritas");
        areaConteudo.add(telaAssistidas,    "assistidas");
        areaConteudo.add(telaQueroAssistir, "queroAssistir");

        layoutCard.show(areaConteudo, "busca");
        return areaConteudo;
    }

    private void abrirJanelaPerfil() {
        JanelaPerfil janela = new JanelaPerfil(this, perfil);
        janela.setVisible(true);
        if (janela.isConfirmado()) {
            perfil.setNome(janela.getNomeDigitado());
            lblNomeUsuario.setText(perfil.getNome());
            salvarPerfil();
        }
    }

    private void salvarESair() {
        salvarPerfil();
        System.exit(0);
    }

    private void salvarPerfil() {
        try { servicoPerfil.salvar(perfil); }
        catch (Exception ex) {
            JOptionPane.showMessageDialog(this,
                    "Erro ao salvar dados: " + ex.getMessage(),
                    "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}
