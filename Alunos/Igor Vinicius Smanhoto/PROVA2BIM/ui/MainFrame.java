package com.tvtracker.ui;

import com.tvtracker.model.UserData;
import com.tvtracker.service.DataPersistenceService;
import com.tvtracker.service.SeriesSortService;
import com.tvtracker.service.TVMazeService;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

// WelcomeDialog é reutilizado no reset para pedir novo nome sem reiniciar o processo

/**
 * Janela principal do pobreflixHD.
 * Orquestra os painéis de busca, listas e perfil do usuário.
 * Usa CardLayout + barra de abas customizada para evitar que o L&F do Windows
 * pinte as abas de branco, mantendo o tema escuro em todos os componentes.
 */
public class MainFrame extends JFrame {

    private final UserData userData;
    private final DataPersistenceService persistenceService;
    private final TVMazeService tvMazeService;
    private final SeriesSortService sortService;

    private SeriesListPanel favoritesPanel;
    private SeriesListPanel watchedPanel;
    private SeriesListPanel wantToWatchPanel;
    private JLabel userGreetLabel;

    // Controla qual painel de conteúdo está visível
    private CardLayout cardLayout;
    private JPanel cardPanel;

    // Rótulos de identificação de cada "aba" no CardLayout
    private static final String TAB_SEARCH   = "SEARCH";
    private static final String TAB_FAV      = "FAV";
    private static final String TAB_WATCHED  = "WATCHED";
    private static final String TAB_WANT     = "WANT";

    public MainFrame(UserData userData, DataPersistenceService persistenceService) {
        this.userData = userData;
        this.persistenceService = persistenceService;
        this.tvMazeService = new TVMazeService();
        this.sortService = new SeriesSortService();

        configureFrame();
        buildUI();

        // Um único WindowAdapter para boas-vindas (opened) e confirmação de saída (closing)
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e) {
                showWelcomeMessage();
            }

            @Override
            public void windowClosing(WindowEvent e) {
                saveAndExit();
            }
        });
    }

    private void configureFrame() {
        setTitle("pobreflixHD");
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setSize(900, 640);
        setMinimumSize(new Dimension(780, 500));
        setLocationRelativeTo(null);
        getContentPane().setBackground(AppTheme.BG_DARK);
    }

    private void buildUI() {
        setLayout(new BorderLayout(0, 0));
        add(buildTopBar(), BorderLayout.NORTH);

        // Painel central = barra de abas customizada (topo) + conteúdo (centro)
        JPanel centerPanel = new JPanel(new BorderLayout(0, 0));
        centerPanel.setBackground(AppTheme.BG_DARK);

        // Cria os painéis de conteúdo antes de criar a barra (ela precisa das referências)
        buildContentPanels();

        centerPanel.add(buildCustomTabBar(), BorderLayout.NORTH);
        centerPanel.add(cardPanel, BorderLayout.CENTER);

        add(centerPanel, BorderLayout.CENTER);
    }

    // Barra superior com logo e perfil do usuário
    private JPanel buildTopBar() {
        JPanel bar = new JPanel(new BorderLayout(12, 0));
        bar.setBackground(AppTheme.BG_DARK);
        bar.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(0, 0, 1, 0, AppTheme.BG_CARD),
                new EmptyBorder(10, 20, 10, 20)));

        // Logo com gradiente visual usando HTML
        JLabel logo = new JLabel("<html><span style='color:#e53935;font-size:17px;'><b>pobreflix</b></span>"
                + "<span style='color:#ffffff;font-size:17px;'><b>HD</b></span></html>");

        userGreetLabel = new JLabel(buildGreeting());
        userGreetLabel.setFont(AppTheme.FONT_SMALL);
        userGreetLabel.setForeground(AppTheme.TEXT_MUTED);

        JButton profileBtn = AppTheme.createButton("Meu Perfil", AppTheme.BG_CARD);
        profileBtn.addActionListener(e -> openProfileDialog());

        JPanel rightPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 12, 0));
        rightPanel.setBackground(AppTheme.BG_DARK);
        rightPanel.add(userGreetLabel);
        rightPanel.add(profileBtn);

        bar.add(logo, BorderLayout.WEST);
        bar.add(rightPanel, BorderLayout.EAST);
        return bar;
    }

    /**
     * Constrói a barra de abas completamente customizada com JPanel e botões.
     * Substitui JTabbedPane para ter controle total sobre as cores,
     * evitando que o L&F do Windows pinte as abas de branco.
     */
    private JPanel buildCustomTabBar() {
        JPanel tabBar = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        tabBar.setBackground(AppTheme.BG_DARK);
        tabBar.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, AppTheme.ACCENT));

        String[] labels = {"Buscar Séries", "Favoritos", "Assistidos", "Quero Assistir"};
        String[] cards  = {TAB_SEARCH, TAB_FAV, TAB_WATCHED, TAB_WANT};

        // Primeiro botão começa selecionado
        JLabel[] tabButtons = new JLabel[labels.length];

        for (int i = 0; i < labels.length; i++) {
            final int idx = i;
            final String card = cards[i];

            JLabel tab = new JLabel(labels[i]);
            tab.setFont(AppTheme.FONT_BODY);
            tab.setOpaque(true);
            tab.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            tab.setBorder(new EmptyBorder(10, 20, 10, 20));

            // Aplica estilo inicial: primeira aba ativa, demais inativas
            applyTabStyle(tab, idx == 0);

            tab.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    // Atualiza estilo de todos os botões
                    for (JLabel t : tabButtons) applyTabStyle(t, false);
                    applyTabStyle(tab, true);

                    // Muda o painel visível e atualiza a lista se necessário
                    cardLayout.show(cardPanel, card);
                    onTabSelected(card);
                }

                @Override
                public void mouseEntered(MouseEvent e) {
                    // Hover só muda cor se a aba não estiver ativa
                    if (!AppTheme.ACCENT.equals(tab.getForeground())) {
                        tab.setBackground(AppTheme.BG_CARD);
                    }
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    if (!AppTheme.ACCENT.equals(tab.getForeground())) {
                        tab.setBackground(AppTheme.BG_DARK);
                    }
                }
            });

            tabButtons[i] = tab;
            tabBar.add(tab);
        }

        return tabBar;
    }

    // Aplica o estilo visual de aba ativa ou inativa
    private void applyTabStyle(JLabel tab, boolean active) {
        if (active) {
            tab.setBackground(AppTheme.BG_PANEL);
            tab.setForeground(AppTheme.ACCENT);
            tab.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createMatteBorder(2, 0, 0, 0, AppTheme.ACCENT),
                    new EmptyBorder(8, 20, 10, 20)));
        } else {
            tab.setBackground(AppTheme.BG_DARK);
            tab.setForeground(AppTheme.TEXT_SECONDARY);
            tab.setBorder(new EmptyBorder(10, 20, 10, 20));
        }
    }

    // Cria os painéis de conteúdo e os adiciona ao CardLayout
    private void buildContentPanels() {
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);
        cardPanel.setBackground(AppTheme.BG_DARK);

        cardPanel.add(new SearchPanel(this, tvMazeService, userData, persistenceService), TAB_SEARCH);

        favoritesPanel = new SeriesListPanel(
                this, "Favoritos", userData, persistenceService, sortService,
                userData::getFavorites, userData::removeFromFavorites);
        cardPanel.add(favoritesPanel, TAB_FAV);

        watchedPanel = new SeriesListPanel(
                this, "Assistidos", userData, persistenceService, sortService,
                userData::getWatched, userData::removeFromWatched);
        cardPanel.add(watchedPanel, TAB_WATCHED);

        wantToWatchPanel = new SeriesListPanel(
                this, "Quero Assistir", userData, persistenceService, sortService,
                userData::getWantToWatch, userData::removeFromWantToWatch);
        cardPanel.add(wantToWatchPanel, TAB_WANT);
    }

    // Atualiza a lista ao trocar de aba para refletir mudanças feitas na busca
    private void onTabSelected(String card) {
        switch (card) {
            case TAB_FAV:     favoritesPanel.refresh();   break;
            case TAB_WATCHED: watchedPanel.refresh();     break;
            case TAB_WANT:    wantToWatchPanel.refresh(); break;
        }
    }

    // Exibe mensagem de boas-vindas personalizada ao abrir o app
    private void showWelcomeMessage() {
        String nome = userData.getUserName();
        String saudacao = (nome != null && !nome.trim().isEmpty())
                ? "Seja bem-vindo, " + nome + "! 🎬"
                : "Seja bem-vindo ao pobreflixHD! 🎬";

        JOptionPane.showMessageDialog(
                this,
                saudacao + "\nBom entretenimento!",
                "pobreflixHD",
                JOptionPane.INFORMATION_MESSAGE);
    }

    private void openProfileDialog() {
        UserProfileDialog dialog = new UserProfileDialog(this, userData, persistenceService);
        dialog.setVisible(true);

        // Se o usuário pediu troca de nome, mostra a tela de cadastro e reinicia
        if (dialog.isResetRequested()) {
            performReset();
            return;
        }

        userGreetLabel.setText(buildGreeting());
    }

    /**
     * Reinicia o fluxo de cadastro sem fechar e reabrir o JAR.
     * Esconde a janela principal, exibe o WelcomeDialog com userData limpo
     * e, ao confirmar, volta a mostrar a janela com o novo nome.
     */
    private void performReset() {
        setVisible(false);

        WelcomeDialog welcome = new WelcomeDialog(userData, persistenceService);
        welcome.setVisible(true);

        if (!welcome.isConfirmed()) {
            // Usuário fechou sem confirmar: encerra o app
            System.exit(0);
        }

        // Atualiza a saudação com o novo nome e reexibe a janela
        userGreetLabel.setText(buildGreeting());
        setVisible(true);
        showWelcomeMessage();
    }

    private String buildGreeting() {
        String name = userData.getUserName();
        return (name == null || name.trim().isEmpty()) ? "Olá!" : "Olá, " + name + "!";
    }

    private void saveAndExit() {
        // Pergunta ao usuário se realmente deseja sair, usando o nome salvo
        String nome = userData.getUserName();
        String nomeParte = (nome != null && !nome.trim().isEmpty()) ? ", " + nome : "";

        int confirm = JOptionPane.showConfirmDialog(
                this,
                "Tem certeza que deseja fechar o aplicativo" + nomeParte + "?",
                "Sair do pobreflixHD",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE);

        if (confirm != JOptionPane.YES_OPTION) return;

        // Salva os dados antes de fechar
        try {
            persistenceService.save(userData);
        } catch (Exception e) {
            int salvarMesmo = JOptionPane.showConfirmDialog(this,
                    "Não foi possível salvar os dados: " + e.getMessage()
                    + "\n\nDeseja fechar mesmo assim?",
                    "Erro ao salvar",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.WARNING_MESSAGE);
            if (salvarMesmo != JOptionPane.YES_OPTION) return;
        }

        // Mensagem de despedida afetiva antes de encerrar
        JOptionPane.showMessageDialog(
                this,
                "Sentiremos saudades, " + (nome != null && !nome.trim().isEmpty() ? nome : "você") + "! 👋",
                "Até logo!",
                JOptionPane.INFORMATION_MESSAGE);

        System.exit(0);
    }
}
