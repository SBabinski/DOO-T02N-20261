package tvtracker.ui;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import tvtracker.model.UserData;
import tvtracker.service.DataService;
import tvtracker.service.TvMazeService;

public class MainWindow extends JFrame {

    private final UserData userData;
    private final DataService dataService;
    private final TvMazeService tvMazeService;

    private JLabel greetingLabel;
    private JTabbedPane tabbedPane;
    private ListPanel favoritesPanel;
    private ListPanel watchedPanel;
    private ListPanel watchlistPanel;

    public MainWindow(UserData userData, DataService dataService, TvMazeService tvMazeService) {
        this.userData      = userData;
        this.dataService   = dataService;
        this.tvMazeService = tvMazeService;
        buildUi();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 680);
        setMinimumSize(new Dimension(800, 560));
        setLocationRelativeTo(null);
    }

    private void buildUi() {
        setTitle("TV Tracker");
        setLayout(new BorderLayout());

        // Header
        JPanel header = buildHeader();
        add(header, BorderLayout.NORTH);

        // Abas
        tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        tabbedPane.setFont(tabbedPane.getFont().deriveFont(13f));

        // Aba de busca
        SearchPanel searchPanel = new SearchPanel(tvMazeService, userData, dataService,
                this::refreshLists);
        tabbedPane.addTab("Buscar Séries", searchPanel);

        // Aba de favoritos
        favoritesPanel = new ListPanel(ListPanel.ListType.FAVORITES, userData,
                dataService, this::refreshLists);
        tabbedPane.addTab("⭐  Favoritos", favoritesPanel);

        // Aba de assistidas
        watchedPanel = new ListPanel(ListPanel.ListType.WATCHED, userData,
                dataService, this::refreshLists);
        tabbedPane.addTab("Já Assistidas", watchedPanel);

        // Aba "quero assistir"
        watchlistPanel = new ListPanel(ListPanel.ListType.WATCHLIST, userData,
                dataService, this::refreshLists);
        tabbedPane.addTab("Quero Assistir", watchlistPanel);

        // Atualizar badges nas abas ao trocar
        tabbedPane.addChangeListener(e -> updateTabBadges());

        add(tabbedPane, BorderLayout.CENTER);

        // Status bar
        JPanel statusBar = buildStatusBar();
        add(statusBar, BorderLayout.SOUTH);

        updateGreeting();
        updateTabBadges();
    }

    private JPanel buildHeader() {
        JPanel header = new JPanel(new BorderLayout(0, 0));
        header.setBackground(new Color(0x1A1A2E));
        header.setBorder(new EmptyBorder(10, 16, 10, 16));

        JPanel leftPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 0));
        leftPanel.setOpaque(false);

        JLabel logoLabel = new JLabel("📺");
        logoLabel.setFont(logoLabel.getFont().deriveFont(28f));
        leftPanel.add(logoLabel);

        JLabel titleLabel = new JLabel("TV Tracker");
        titleLabel.setFont(titleLabel.getFont().deriveFont(Font.BOLD, 22f));
        titleLabel.setForeground(Color.WHITE);
        leftPanel.add(titleLabel);

        header.add(leftPanel, BorderLayout.WEST);

        // Greeting + botão de perfil
        JPanel rightPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 8, 0));
        rightPanel.setOpaque(false);

        greetingLabel = new JLabel();
        greetingLabel.setFont(greetingLabel.getFont().deriveFont(13f));
        greetingLabel.setForeground(new Color(0xCCCCCC));
        rightPanel.add(greetingLabel);

        JButton profileBtn = new JButton("👤 Perfil");
        profileBtn.setFocusPainted(false);
        profileBtn.setBackground(new Color(0x16213E));
        profileBtn.setForeground(Color.WHITE);
        profileBtn.setBorderPainted(false);
        profileBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        profileBtn.addActionListener(e -> openProfileDialog());
        rightPanel.add(profileBtn);

        header.add(rightPanel, BorderLayout.EAST);

        return header;
    }

    private JPanel buildStatusBar() {
        JPanel bar = new JPanel(new FlowLayout(FlowLayout.LEFT, 12, 2));
        bar.setBackground(new Color(0xF0F0F0));
        bar.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, new Color(0xCCCCCC)));

        JLabel apiLabel = new JLabel("Dados: TVMaze API (tvmaze.com)");
        apiLabel.setFont(apiLabel.getFont().deriveFont(11f));
        apiLabel.setForeground(Color.GRAY);
        bar.add(apiLabel);

        JLabel sepLabel = new JLabel("|");
        sepLabel.setForeground(Color.GRAY);
        bar.add(sepLabel);

        JLabel storageLabel = new JLabel("Dados salvos em: ~/.tvtracker/data.json");
        storageLabel.setFont(storageLabel.getFont().deriveFont(11f));
        storageLabel.setForeground(Color.GRAY);
        bar.add(storageLabel);

        return bar;
    }

    private void openProfileDialog() {
        new ProfileDialog(this, userData, dataService, () -> {
            updateGreeting();
        }).setVisible(true);

        // Se ainda não tem perfil configurado após fechar, mostra novamente
        if (!userData.getProfile().isConfigured()) {
            int opt = JOptionPane.showConfirmDialog(this,
                    "Você ainda não configurou seu perfil.\nDeseja configurar agora?",
                    "Perfil", JOptionPane.YES_NO_OPTION);
            if (opt == JOptionPane.YES_OPTION) openProfileDialog();
        }
    }

    private void updateGreeting() {
        String name = userData.getProfile().getDisplayName();
        greetingLabel.setText("Olá, " + name + "!");
        setTitle("TV Tracker — " + name);
    }

    private void refreshLists() {
        if (favoritesPanel != null) favoritesPanel.refresh();
        if (watchedPanel   != null) watchedPanel.refresh();
        if (watchlistPanel != null) watchlistPanel.refresh();
        updateTabBadges();
    }

    private void updateTabBadges() {
        int favCount     = userData.getFavorites().size();
        int watchedCount = userData.getWatched().size();
        int wlCount      = userData.getWatchlist().size();

        if (tabbedPane.getTabCount() >= 4) {
            tabbedPane.setTitleAt(1, "Favoritos (" + favCount + ")");
            tabbedPane.setTitleAt(2, "Já Assistidas (" + watchedCount + ")");
            tabbedPane.setTitleAt(3, "Quero Assistir (" + wlCount + ")");
        }
    }

    public void promptProfileIfNeeded() {
        if (!userData.getProfile().isConfigured()) {
            SwingUtilities.invokeLater(() -> {
                JOptionPane.showMessageDialog(this,
                        "Bem-vindo ao TV Tracker!\nPor favor, configure seu perfil para começar.",
                        "Bem-vindo!", JOptionPane.INFORMATION_MESSAGE);
                openProfileDialog();
            });
        }
    }
}
