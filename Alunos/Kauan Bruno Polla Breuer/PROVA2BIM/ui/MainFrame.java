package ui;

import model.UserData;
import service.PersistenceService;
import service.TVMazeService;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.IOException;

/**
 * Janela principal do SeriesTracker.
 * Contém uma barra lateral com navegação e um painel de conteúdo central.
 */
public class MainFrame extends JFrame {

    private final UserData userData;
    private final PersistenceService persistence;
    private final TVMazeService tvMaze;

    // Painéis de conteúdo
    private BuscaPanel  buscaPanel;
    private ListaPanel  favoritosPanel;
    private ListaPanel  assistidasPanel;
    private ListaPanel  querAssistirPanel;

    private JPanel contentArea;
    private CardLayout cardLayout;

    // Botões sidebar
    private JButton btnBusca;
    private JButton btnFavoritos;
    private JButton btnAssistidas;
    private JButton btnQuerAssistir;
    private JButton btnAtivo;

    // Labels dinâmicas de contagem
    private JLabel lblContFavoritos;
    private JLabel lblContAssistidas;
    private JLabel lblContQuerAssistir;

    private static final String CARD_BUSCA         = "BUSCA";
    private static final String CARD_FAVORITOS      = "FAVORITOS";
    private static final String CARD_ASSISTIDAS     = "ASSISTIDAS";
    private static final String CARD_QUER_ASSISTIR  = "QUER_ASSISTIR";

    public MainFrame(UserData userData, PersistenceService persistence, TVMazeService tvMaze) {
        super("SeriesTracker");
        this.userData    = userData;
        this.persistence = persistence;
        this.tvMaze      = tvMaze;
        initUI();
    }

    private void initUI() {
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setSize(1050, 680);
        setMinimumSize(new Dimension(820, 520));
        setLocationRelativeTo(null);
        getContentPane().setBackground(SerieDetalhesPanel.BG);

        // Confirmar fechamento e salvar
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent e) {
                fecharAplicacao();
            }
        });

        setLayout(new BorderLayout());
        add(criarSidebar(),    BorderLayout.WEST);
        add(criarConteudo(),   BorderLayout.CENTER);
    }

    // ─── Sidebar ──────────────────────────────────────────────────────────────

    private JPanel criarSidebar() {
        JPanel sidebar = new JPanel();
        sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));
        sidebar.setBackground(SerieDetalhesPanel.SURFACE);
        sidebar.setPreferredSize(new Dimension(200, 0));
        sidebar.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, SerieDetalhesPanel.SURFACE1));

        // Logo
        JLabel lblApp = new JLabel("SeriesTracker");
        lblApp.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblApp.setForeground(SerieDetalhesPanel.PURPLE);
        lblApp.setAlignmentX(CENTER_ALIGNMENT);
        lblApp.setBorder(new EmptyBorder(20, 0, 4, 0));

        // Saudação ao usuário
        JLabel lblUsuario = new JLabel("Ola, " + userData.getNomeUsuario() + "!");
        lblUsuario.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        lblUsuario.setForeground(SerieDetalhesPanel.SUBTEXT);
        lblUsuario.setAlignmentX(CENTER_ALIGNMENT);

        sidebar.add(lblApp);
        sidebar.add(lblUsuario);
        sidebar.add(Box.createVerticalStrut(24));
        sidebar.add(criarSeparadorSidebar("MENU"));

        // Botões de navegação
        btnBusca        = criarBotaoNav("  Buscar Series",       "busca");
        btnFavoritos    = criarBotaoNav("  Favoritos",            "fav");
        btnAssistidas   = criarBotaoNav("  Ja Assistidas",        "asst");
        btnQuerAssistir = criarBotaoNav("  Quero Assistir",       "want");

        btnBusca.addActionListener(e        -> navegarPara(CARD_BUSCA,         btnBusca));
        btnFavoritos.addActionListener(e    -> navegarPara(CARD_FAVORITOS,     btnFavoritos));
        btnAssistidas.addActionListener(e   -> navegarPara(CARD_ASSISTIDAS,    btnAssistidas));
        btnQuerAssistir.addActionListener(e -> navegarPara(CARD_QUER_ASSISTIR, btnQuerAssistir));

        sidebar.add(btnBusca);
        sidebar.add(Box.createVerticalStrut(2));
        sidebar.add(btnFavoritos);

        // Badge de contagem ao lado do botão favoritos
        lblContFavoritos   = criarBadge();
        lblContAssistidas  = criarBadge();
        lblContQuerAssistir = criarBadge();

        sidebar.add(criarLinhaComBadge(btnAssistidas,   lblContAssistidas));
        sidebar.add(criarLinhaComBadge(btnQuerAssistir, lblContQuerAssistir));

        sidebar.add(Box.createVerticalGlue());
        sidebar.add(criarSeparadorSidebar("DADOS"));

        JButton btnSalvar = criarBotaoAcao("Salvar agora", SerieDetalhesPanel.BLUE);
        btnSalvar.addActionListener(e -> salvarManualmente());
        sidebar.add(btnSalvar);
        sidebar.add(Box.createVerticalStrut(16));

        // Ativar busca por padrão
        ativarBotao(btnBusca);
        atualizarContagens();

        return sidebar;
    }

    private JPanel criarLinhaComBadge(JButton btn, JLabel badge) {
        // O badge fica sobreposto; usamos o botão diretamente para simplificar
        return new JPanel(new BorderLayout()) {{
            setBackground(SerieDetalhesPanel.SURFACE);
            add(btn, BorderLayout.CENTER);
        }};
    }

    private JLabel criarBadge() {
        JLabel lbl = new JLabel("0");
        lbl.setFont(new Font("Segoe UI", Font.BOLD, 10));
        lbl.setForeground(SerieDetalhesPanel.BG);
        lbl.setBackground(SerieDetalhesPanel.SUBTEXT);
        lbl.setOpaque(true);
        lbl.setBorder(new EmptyBorder(1, 5, 1, 5));
        return lbl;
    }

    private JButton criarBotaoNav(String texto, String id) {
        JButton btn = new JButton(texto);
        btn.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        btn.setBackground(SerieDetalhesPanel.SURFACE);
        btn.setForeground(SerieDetalhesPanel.TEXT);
        btn.setHorizontalAlignment(SwingConstants.LEFT);
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btn.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        btn.setBorder(new EmptyBorder(8, 20, 8, 20));
        return btn;
    }

    private JButton criarBotaoAcao(String texto, Color cor) {
        JButton btn = new JButton(texto);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btn.setBackground(cor);
        btn.setForeground(SerieDetalhesPanel.BG);
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btn.setAlignmentX(CENTER_ALIGNMENT);
        btn.setMaximumSize(new Dimension(170, 34));
        return btn;
    }

    private JLabel criarSeparadorSidebar(String titulo) {
        JLabel lbl = new JLabel("  " + titulo);
        lbl.setFont(new Font("Segoe UI", Font.BOLD, 10));
        lbl.setForeground(SerieDetalhesPanel.SUBTEXT);
        lbl.setMaximumSize(new Dimension(Integer.MAX_VALUE, 24));
        lbl.setBorder(new EmptyBorder(4, 0, 4, 0));
        return lbl;
    }

    // ─── Área de conteúdo ─────────────────────────────────────────────────────

    private JPanel criarConteudo() {
        cardLayout = new CardLayout();
        contentArea = new JPanel(cardLayout);
        contentArea.setBackground(SerieDetalhesPanel.BG);

        buscaPanel       = new BuscaPanel(tvMaze, userData, persistence, this);
        favoritosPanel   = new ListaPanel(ListaPanel.TipoLista.FAVORITOS,     userData, persistence, this);
        assistidasPanel  = new ListaPanel(ListaPanel.TipoLista.JA_ASSISTIDAS, userData, persistence, this);
        querAssistirPanel= new ListaPanel(ListaPanel.TipoLista.QUER_ASSISTIR, userData, persistence, this);

        contentArea.add(buscaPanel,        CARD_BUSCA);
        contentArea.add(favoritosPanel,    CARD_FAVORITOS);
        contentArea.add(assistidasPanel,   CARD_ASSISTIDAS);
        contentArea.add(querAssistirPanel, CARD_QUER_ASSISTIR);

        return contentArea;
    }

    // ─── Navegação ────────────────────────────────────────────────────────────

    private void navegarPara(String card, JButton btn) {
        cardLayout.show(contentArea, card);
        ativarBotao(btn);

        // Recarregar listas ao entrar nelas
        if (card.equals(CARD_FAVORITOS))      favoritosPanel.recarregar();
        if (card.equals(CARD_ASSISTIDAS))     assistidasPanel.recarregar();
        if (card.equals(CARD_QUER_ASSISTIR))  querAssistirPanel.recarregar();
    }

    private void ativarBotao(JButton btn) {
        if (btnAtivo != null) {
            btnAtivo.setBackground(SerieDetalhesPanel.SURFACE);
            btnAtivo.setForeground(SerieDetalhesPanel.TEXT);
            btnAtivo.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        }
        btnAtivo = btn;
        btn.setBackground(SerieDetalhesPanel.SURFACE1);
        btn.setForeground(SerieDetalhesPanel.PURPLE);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 13));
    }

    // ─── Callbacks ────────────────────────────────────────────────────────────

    /** Chamado pelos painéis de detalhes sempre que uma lista é alterada. */
    public void notificarAlteracaoListas() {
        atualizarContagens();
        // Recarrega o painel de lista atualmente visível (se for um ListaPanel)
        favoritosPanel.recarregar();
        assistidasPanel.recarregar();
        querAssistirPanel.recarregar();
    }

    private void atualizarContagens() {
        int nFav  = userData.getFavoritos().size();
        int nAsst = userData.getJaAssistidas().size();
        int nWant = userData.getQuerAssistir().size();

        btnFavoritos.setText("  Favoritos (" + nFav + ")");
        btnAssistidas.setText("  Ja Assistidas (" + nAsst + ")");
        btnQuerAssistir.setText("  Quero Assistir (" + nWant + ")");
    }

    // ─── Salvar / Fechar ──────────────────────────────────────────────────────

    private void salvarManualmente() {
        try {
            persistence.salvar(userData);
            JOptionPane.showMessageDialog(this,
                "Dados salvos com sucesso!",
                "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this,
                "Erro ao salvar: " + ex.getMessage(),
                "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void fecharAplicacao() {
        try {
            persistence.salvar(userData);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this,
                "Aviso: nao foi possivel salvar os dados antes de fechar.\n" + ex.getMessage(),
                "Aviso", JOptionPane.WARNING_MESSAGE);
        }
        dispose();
        System.exit(0);
    }
}
