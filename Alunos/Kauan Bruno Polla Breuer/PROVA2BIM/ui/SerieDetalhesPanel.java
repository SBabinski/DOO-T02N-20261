package ui;

import model.Serie;
import model.UserData;
import service.PersistenceService;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.IOException;

/**
 * Painel que exibe todas as informações de uma série e os botões de ação
 * (adicionar/remover de favoritos, já assistidas e quero assistir).
 */
public class SerieDetalhesPanel extends JPanel {

    // ── Paleta Catppuccin Mocha ───────────────────────────────────────────────
    static final Color BG        = new Color(30,  30,  46);
    static final Color SURFACE   = new Color(49,  50,  68);
    static final Color SURFACE1  = new Color(69,  71,  90);
    static final Color PURPLE    = new Color(203, 166, 247);
    static final Color BLUE      = new Color(137, 180, 250);
    static final Color GREEN     = new Color(166, 227, 161);
    static final Color RED       = new Color(243, 139, 168);
    static final Color YELLOW    = new Color(249, 226, 175);
    static final Color TEXT      = new Color(205, 214, 244);
    static final Color SUBTEXT   = new Color(166, 173, 200);

    private final UserData userData;
    private final PersistenceService persistence;
    private final MainFrame mainFrame;
    private Serie serie;

    // Botões de ação
    private JButton btnFavorito;
    private JButton btnAssistida;
    private JButton btnQuerAssistir;

    public SerieDetalhesPanel(UserData userData, PersistenceService persistence, MainFrame mainFrame) {
        this.userData    = userData;
        this.persistence = persistence;
        this.mainFrame   = mainFrame;
        setBackground(BG);
        setLayout(new BorderLayout());
        mostrarVazio();
    }

    /** Exibe uma mensagem de boas-vindas quando nenhuma série está selecionada. */
    public void mostrarVazio() {
        removeAll();
        JLabel lbl = new JLabel("Selecione ou busque uma série para ver os detalhes.",
                                 JLabel.CENTER);
        lbl.setForeground(SUBTEXT);
        lbl.setFont(new Font("Segoe UI", Font.ITALIC, 14));
        add(lbl, BorderLayout.CENTER);
        revalidate();
        repaint();
    }

    /** Carrega e exibe os detalhes da série informada. */
    public void exibir(Serie s) {
        this.serie = s;
        removeAll();

        JPanel container = new JPanel();
        container.setBackground(BG);
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
        container.setBorder(new EmptyBorder(20, 24, 20, 24));

        // ── Nome ──────────────────────────────────────────────────────────────
        JLabel lblNome = new JLabel(s.getNome());
        lblNome.setFont(new Font("Segoe UI", Font.BOLD, 22));
        lblNome.setForeground(PURPLE);
        lblNome.setAlignmentX(LEFT_ALIGNMENT);
        container.add(lblNome);
        container.add(Box.createVerticalStrut(4));

        // ── Emissora ──────────────────────────────────────────────────────────
        JLabel lblEmissora = new JLabel(s.getEmissora());
        lblEmissora.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        lblEmissora.setForeground(SUBTEXT);
        lblEmissora.setAlignmentX(LEFT_ALIGNMENT);
        container.add(lblEmissora);
        container.add(Box.createVerticalStrut(16));

        // ── Grade de informações ──────────────────────────────────────────────
        JPanel grade = new JPanel(new GridLayout(0, 2, 12, 8));
        grade.setBackground(BG);
        grade.setAlignmentX(LEFT_ALIGNMENT);

        addInfo(grade, "Idioma",      s.getIdioma());
        addInfo(grade, "Generos",     s.getGenerosFormatados());
        addInfo(grade, "Nota",        s.getNotaFormatada());
        addInfo(grade, "Estado",      s.getEstadoTraduzido());
        addInfo(grade, "Estreia",     s.getDataEstreiaFormatada());
        addInfo(grade, "Termino",     s.getDataTerminoFormatada());

        container.add(grade);
        container.add(Box.createVerticalStrut(24));

        // ── Separador ─────────────────────────────────────────────────────────
        JSeparator sep = new JSeparator();
        sep.setForeground(SURFACE1);
        sep.setBackground(SURFACE1);
        sep.setMaximumSize(new Dimension(Integer.MAX_VALUE, 1));
        sep.setAlignmentX(LEFT_ALIGNMENT);
        container.add(sep);
        container.add(Box.createVerticalStrut(20));

        // ── Título das ações ──────────────────────────────────────────────────
        JLabel lblAcoes = new JLabel("Minhas listas");
        lblAcoes.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblAcoes.setForeground(SUBTEXT);
        lblAcoes.setAlignmentX(LEFT_ALIGNMENT);
        container.add(lblAcoes);
        container.add(Box.createVerticalStrut(10));

        // ── Botões de listas ──────────────────────────────────────────────────
        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 0));
        painelBotoes.setBackground(BG);
        painelBotoes.setAlignmentX(LEFT_ALIGNMENT);

        btnFavorito     = criarBotaoToggle();
        btnAssistida    = criarBotaoToggle();
        btnQuerAssistir = criarBotaoToggle();

        atualizarBotoes();

        btnFavorito.addActionListener(e -> toggleLista("favorito"));
        btnAssistida.addActionListener(e -> toggleLista("assistida"));
        btnQuerAssistir.addActionListener(e -> toggleLista("queroAssistir"));

        painelBotoes.add(btnFavorito);
        painelBotoes.add(btnAssistida);
        painelBotoes.add(btnQuerAssistir);
        container.add(painelBotoes);

        JScrollPane scroll = new JScrollPane(container);
        scroll.setBorder(null);
        scroll.getViewport().setBackground(BG);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        add(scroll, BorderLayout.CENTER);
        revalidate();
        repaint();
    }

    // ─── Helpers de montagem ──────────────────────────────────────────────────

    private void addInfo(JPanel grade, String rotulo, String valor) {
        JLabel lblRotulo = new JLabel(rotulo + ":");
        lblRotulo.setFont(new Font("Segoe UI", Font.BOLD, 13));
        lblRotulo.setForeground(BLUE);

        JLabel lblValor = new JLabel(valor);
        lblValor.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        lblValor.setForeground(TEXT);

        grade.add(lblRotulo);
        grade.add(lblValor);
    }

    private JButton criarBotaoToggle() {
        JButton btn = new JButton();
        btn.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btn.setPreferredSize(new Dimension(160, 34));
        return btn;
    }

    private void atualizarBotoes() {
        if (serie == null) return;

        configurarBotao(btnFavorito,
            userData.isFavorito(serie),
            "- Favoritos", "+ Favoritos",
            RED, SURFACE);

        configurarBotao(btnAssistida,
            userData.isJaAssistida(serie),
            "- Ja Assistida", "+ Ja Assistida",
            GREEN, SURFACE);

        configurarBotao(btnQuerAssistir,
            userData.isQuerAssistir(serie),
            "- Quero Assistir", "+ Quero Assistir",
            YELLOW, SURFACE);
    }

    private void configurarBotao(JButton btn, boolean ativo,
                                  String textoAtivo, String textoInativo,
                                  Color corAtiva, Color corInativa) {
        if (ativo) {
            btn.setText(textoAtivo);
            btn.setBackground(corAtiva);
            btn.setForeground(BG);
        } else {
            btn.setText(textoInativo);
            btn.setBackground(corInativa);
            btn.setForeground(TEXT);
        }
    }

    // ─── Lógica de toggle ─────────────────────────────────────────────────────

    private void toggleLista(String lista) {
        switch (lista) {
            case "favorito":
                if (userData.isFavorito(serie)) userData.removeFavorito(serie);
                else userData.addFavorito(serie);
                break;
            case "assistida":
                if (userData.isJaAssistida(serie)) userData.removeJaAssistida(serie);
                else userData.addJaAssistida(serie);
                break;
            case "queroAssistir":
                if (userData.isQuerAssistir(serie)) userData.removeQuerAssistir(serie);
                else userData.addQuerAssistir(serie);
                break;
        }
        salvar();
        atualizarBotoes();
        mainFrame.notificarAlteracaoListas();
    }

    private void salvar() {
        try {
            persistence.salvar(userData);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this,
                "Erro ao salvar os dados: " + ex.getMessage(),
                "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}
