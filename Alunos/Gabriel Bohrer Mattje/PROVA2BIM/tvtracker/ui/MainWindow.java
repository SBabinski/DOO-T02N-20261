package com.tvtracker.ui;

import com.tvtracker.model.TvSeries;
import com.tvtracker.model.UserProfile;
import com.tvtracker.repository.UserRepository;
import com.tvtracker.service.TvMazeApiService;
import com.tvtracker.ui.panels.MyListsPanel;
import com.tvtracker.ui.panels.SearchPanel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

public class MainWindow extends JFrame {

    private final UserRepository userRepository;
    private final TvMazeApiService apiService;
    private UserProfile userProfile;

    private JPanel contentPanel;
    private CardLayout cardLayout;
    private SearchPanel searchPanel;
    private MyListsPanel listsPanel;

    private JLabel userGreetingLabel;
    private JButton navSearchBtn;
    private JButton navListsBtn;
    private JButton navProfileBtn;

    private static final String CARD_SEARCH = "search";
    private static final String CARD_LISTS  = "lists";

    public MainWindow() {
        this.userRepository = new UserRepository();
        this.apiService = new TvMazeApiService();

        setTitle("TV Tracker");
        setSize(900, 680);
        setMinimumSize(new Dimension(760, 560));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        getContentPane().setBackground(AppTheme.BG_DARK);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                saveAndExit();
            }
        });

        loadOrCreateProfile();
        buildUI();
        showCard(CARD_SEARCH);
    }

    private void loadOrCreateProfile() {
        if (userRepository.hasExistingData()) {
            userProfile = userRepository.load();
            if (userProfile.getName() == null || userProfile.getName().isBlank()) {
                UserProfile fresh = SetupDialog.showAndGetProfile(null);
                if (fresh != null) {
                    userProfile.setName(fresh.getName());
                    userProfile.setNickname(fresh.getNickname());
                }
            }
        } else {
            UserProfile fresh = SetupDialog.showAndGetProfile(null);
            if (fresh != null) {
                userProfile = fresh;
                preloadDemoData();
            } else {
                userProfile = new UserProfile("Usuario", null);
            }
        }
    }

    private void preloadDemoData() {
        TvSeries breakingBad = new TvSeries(169, "Breaking Bad", "English",
                java.util.List.of("Drama", "Crime", "Thriller"),
                9.3, com.tvtracker.model.SeriesStatus.ENDED,
                "2008-01-20", "2013-09-29", "AMC",
                "A high school chemistry teacher turned methamphetamine manufacturer.", null);

        TvSeries gameOfThrones = new TvSeries(82, "Game of Thrones", "English",
                java.util.List.of("Drama", "Adventure", "Fantasy"),
                8.8, com.tvtracker.model.SeriesStatus.ENDED,
                "2011-04-17", "2019-05-19", "HBO",
                "Noble families vie for control of the mythical land of Westeros.", null);

        TvSeries strangerThings = new TvSeries(2993, "Stranger Things", "English",
                java.util.List.of("Drama", "Fantasy", "Horror"),
                8.7, com.tvtracker.model.SeriesStatus.ENDED,
                "2016-07-15", null, "Netflix",
                "A young boy disappears and his friends uncover supernatural mysteries.", null);

        userProfile.addToFavorites(breakingBad);
        userProfile.addToWatched(gameOfThrones);
        userProfile.addToWatchLater(strangerThings);
    }

    private void buildUI() {
        setLayout(new BorderLayout());
        JPanel sideNav = buildSideNav();
        add(sideNav, BorderLayout.WEST);

        cardLayout = new CardLayout();
        contentPanel = new JPanel(cardLayout);
        contentPanel.setBackground(AppTheme.BG_DARK);

        Runnable onProfileChanged = this::onProfileChanged;
        searchPanel = new SearchPanel(apiService, userProfile, onProfileChanged);
        listsPanel  = new MyListsPanel(userProfile, onProfileChanged);

        contentPanel.add(searchPanel, CARD_SEARCH);
        contentPanel.add(listsPanel,  CARD_LISTS);
        add(contentPanel, BorderLayout.CENTER);
    }

    private JPanel buildSideNav() {
        JPanel nav = new JPanel();
        nav.setLayout(new BoxLayout(nav, BoxLayout.Y_AXIS));
        nav.setBackground(AppTheme.BG_PANEL);
        nav.setPreferredSize(new Dimension(210, 0));

        // ── Logo ──────────────────────────────────────────
        JPanel logoPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 14, 0));
        logoPanel.setBackground(AppTheme.BG_PANEL);
        logoPanel.setBorder(new EmptyBorder(22, 14, 14, 14));
        logoPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 70));
        logoPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel logoIcon = new JLabel("\uD83D\uDCFA"); // 📺
        logoIcon.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 22));
        logoIcon.setForeground(AppTheme.TEXT_PRIMARY);
        logoPanel.add(logoIcon);

        JLabel logoText = new JLabel("TV Tracker");
        logoText.setFont(AppTheme.FONT_SUBTITLE);
        logoText.setForeground(AppTheme.TEXT_PRIMARY);
        logoPanel.add(logoText);
        nav.add(logoPanel);

        nav.add(createNavSeparator());
        nav.add(Box.createVerticalStrut(8));

        // ── Saudacao ──────────────────────────────────────
        JPanel greetPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 6));
        greetPanel.setBackground(AppTheme.BG_PANEL);
        greetPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 52));
        greetPanel.setBorder(new EmptyBorder(0, 10, 0, 10));
        greetPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel avatarLabel = new JLabel("\uD83D\uDC64"); // 👤
        avatarLabel.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 18));
        avatarLabel.setForeground(AppTheme.TEXT_PRIMARY);
        greetPanel.add(avatarLabel);

        JPanel greetTextPanel = new JPanel();
        greetTextPanel.setLayout(new BoxLayout(greetTextPanel, BoxLayout.Y_AXIS));
        greetTextPanel.setBackground(AppTheme.BG_PANEL);

        JLabel helloLabel = new JLabel("Ola,");
        helloLabel.setFont(AppTheme.FONT_SMALL);
        helloLabel.setForeground(AppTheme.TEXT_MUTED);
        greetTextPanel.add(helloLabel);

        userGreetingLabel = new JLabel(userProfile.getDisplayName());
        userGreetingLabel.setFont(AppTheme.FONT_BOLD);
        userGreetingLabel.setForeground(AppTheme.ACCENT_LIGHT);
        greetTextPanel.add(userGreetingLabel);
        greetPanel.add(greetTextPanel);
        nav.add(greetPanel);

        nav.add(Box.createVerticalStrut(12));
        nav.add(createNavSeparator());
        nav.add(Box.createVerticalStrut(16));

        // ── Secao NAVEGACAO ───────────────────────────────
        nav.add(createSectionLabel("NAVEGACAO"));

        navSearchBtn = createNavButton("Buscar Series", true);
        navSearchBtn.addActionListener(e -> showCard(CARD_SEARCH));
        nav.add(navSearchBtn);

        navListsBtn = createNavButton("Minhas Listas", false);
        navListsBtn.addActionListener(e -> {
            listsPanel.refreshList();
            showCard(CARD_LISTS);
        });
        nav.add(navListsBtn);

        nav.add(Box.createVerticalStrut(16));
        nav.add(createNavSeparator());
        nav.add(Box.createVerticalStrut(16));

        // ── Secao RESUMO ──────────────────────────────────
        nav.add(createSectionLabel("RESUMO DAS LISTAS"));

        nav.add(createStatRow("Favoritos",       () -> userProfile.getFavorites().size()));
        nav.add(createStatRow("Assistidas",      () -> userProfile.getWatched().size()));
        nav.add(createStatRow("Para Assistir",   () -> userProfile.getWatchLater().size()));

        nav.add(Box.createVerticalGlue());

        // ── Botoes inferiores ─────────────────────────────
        nav.add(createNavSeparator());
        navProfileBtn = createNavButton("Editar Perfil", false);
        navProfileBtn.addActionListener(e -> showProfileEdit());
        nav.add(navProfileBtn);

        JButton resetBtn = createNavButton("Resetar Dados", false);
        resetBtn.setForeground(AppTheme.DANGER);
        resetBtn.addActionListener(e -> showResetConfirm());
        nav.add(resetBtn);
        nav.add(Box.createVerticalStrut(8));

        return nav;
    }

    /** Label de secao (ex: "NAVEGACAO") alinhada a esquerda */
    private JLabel createSectionLabel(String text) {
        JLabel lbl = new JLabel(text);
        lbl.setFont(new Font("Segoe UI", Font.BOLD, 10));
        lbl.setForeground(AppTheme.TEXT_MUTED);
        lbl.setBorder(new EmptyBorder(0, 20, 6, 0));
        lbl.setAlignmentX(Component.LEFT_ALIGNMENT);
        lbl.setMaximumSize(new Dimension(Integer.MAX_VALUE, 22));
        return lbl;
    }

    private void showCard(String card) {
        cardLayout.show(contentPanel, card);
        navSearchBtn.setBackground(card.equals(CARD_SEARCH) ? AppTheme.ACCENT : AppTheme.BG_PANEL);
        navSearchBtn.setForeground(card.equals(CARD_SEARCH) ? AppTheme.TEXT_PRIMARY : AppTheme.TEXT_SECONDARY);
        navListsBtn.setBackground(card.equals(CARD_LISTS) ? AppTheme.ACCENT : AppTheme.BG_PANEL);
        navListsBtn.setForeground(card.equals(CARD_LISTS) ? AppTheme.TEXT_PRIMARY : AppTheme.TEXT_SECONDARY);
    }

    private void onProfileChanged() {
        saveProfileSilently();
        SwingUtilities.invokeLater(() -> {
            Container nav = navProfileBtn.getParent();
            if (nav != null) { nav.revalidate(); nav.repaint(); }
        });
    }

    private void saveProfileSilently() {
        try { userRepository.save(userProfile); }
        catch (IOException e) { System.err.println("Erro ao salvar: " + e.getMessage()); }
    }

    private void showResetConfirm() {
        JDialog d = new JDialog(this, "Resetar Dados", true);
        d.setSize(340, 170);
        d.setLocationRelativeTo(this);
        d.setResizable(false);
        d.getContentPane().setBackground(AppTheme.BG_PANEL);
        d.setLayout(new BorderLayout());

        JPanel msgPanel = new JPanel();
        msgPanel.setLayout(new BoxLayout(msgPanel, BoxLayout.Y_AXIS));
        msgPanel.setBackground(AppTheme.BG_PANEL);
        msgPanel.setBorder(new EmptyBorder(20, 24, 8, 24));

        JLabel line1 = new JLabel("Isso apagara todas as suas listas");
        line1.setFont(AppTheme.FONT_BODY);
        line1.setForeground(AppTheme.TEXT_PRIMARY);
        line1.setAlignmentX(Component.CENTER_ALIGNMENT);
        msgPanel.add(line1);
        msgPanel.add(Box.createVerticalStrut(4));

        JLabel line2 = new JLabel("e redefinira o perfil. Tem certeza?");
        line2.setFont(AppTheme.FONT_BODY);
        line2.setForeground(AppTheme.TEXT_PRIMARY);
        line2.setAlignmentX(Component.CENTER_ALIGNMENT);
        msgPanel.add(line2);
        d.add(msgPanel, BorderLayout.CENTER);

        JPanel bp = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 12));
        bp.setBackground(AppTheme.BG_PANEL);

        JButton cancelBtn = createDialogButton("Cancelar", AppTheme.BG_CARD, AppTheme.TEXT_PRIMARY);
        cancelBtn.addActionListener(e -> d.dispose());

        JButton confirmBtn = createDialogButton("Sim, resetar", AppTheme.DANGER, AppTheme.TEXT_PRIMARY);
        confirmBtn.addActionListener(e -> {
            d.dispose();
            try {
                // Apaga o arquivo de dados
                java.nio.file.Path dataFile = java.nio.file.Paths.get(
                        System.getProperty("user.home"), ".tvtracker", "userdata.json");
                java.nio.file.Files.deleteIfExists(dataFile);
            } catch (Exception ex) {
                System.err.println("Erro ao deletar dados: " + ex.getMessage());
            }
            // Reinicia o app do zero
            showInfoDialog("Dados resetados! O aplicativo sera reiniciado.");
            dispose();
            SwingUtilities.invokeLater(() -> new MainWindow().setVisible(true));
        });

        bp.add(cancelBtn);
        bp.add(confirmBtn);
        d.add(bp, BorderLayout.SOUTH);
        d.setVisible(true);
    }

    private void saveAndExit() {
        try {
            userRepository.save(userProfile);
            System.exit(0);
        } catch (IOException e) {
            int choice = showConfirmDialog("Nao foi possivel salvar os dados.\n" + e.getMessage()
                    + "\n\nDeseja sair mesmo assim?", "Erro ao Salvar");
            if (choice == JOptionPane.YES_OPTION) System.exit(0);
        }
    }

    private void showProfileEdit() {
        JDialog dialog = new JDialog(this, "Editar Perfil", true);
        dialog.setSize(380, 260);
        dialog.setLocationRelativeTo(this);
        dialog.setResizable(false);
        dialog.getContentPane().setBackground(AppTheme.BG_PANEL);
        dialog.setLayout(new BorderLayout());

        // Formulario com BoxLayout para controlar alturas individualmente
        JPanel form = new JPanel();
        form.setLayout(new BoxLayout(form, BoxLayout.Y_AXIS));
        form.setBackground(AppTheme.BG_PANEL);
        form.setBorder(new EmptyBorder(20, 24, 8, 24));

        JLabel nameLabel = new JLabel("Nome:");
        nameLabel.setFont(AppTheme.FONT_LABEL);
        nameLabel.setForeground(AppTheme.TEXT_MUTED);
        nameLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        form.add(nameLabel);
        form.add(Box.createVerticalStrut(5));

        JTextField nameField = createStyledField(userProfile.getName());
        nameField.setAlignmentX(Component.LEFT_ALIGNMENT);
        nameField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 36));
        form.add(nameField);
        form.add(Box.createVerticalStrut(14));

        JLabel nickLabel = new JLabel("Apelido:");
        nickLabel.setFont(AppTheme.FONT_LABEL);
        nickLabel.setForeground(AppTheme.TEXT_MUTED);
        nickLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        form.add(nickLabel);
        form.add(Box.createVerticalStrut(5));

        JTextField nickField = createStyledField(
                userProfile.getNickname() != null ? userProfile.getNickname() : "");
        nickField.setAlignmentX(Component.LEFT_ALIGNMENT);
        nickField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 36));
        form.add(nickField);

        dialog.add(form, BorderLayout.CENTER);

        // Botoes
        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 12));
        btnPanel.setBackground(AppTheme.BG_PANEL);

        JButton cancelBtn = createDialogButton("Cancelar", AppTheme.BG_CARD, AppTheme.TEXT_PRIMARY);
        cancelBtn.addActionListener(e -> dialog.dispose());

        JButton okBtn = createDialogButton("Salvar", AppTheme.ACCENT, AppTheme.TEXT_PRIMARY);
        okBtn.addActionListener(e -> {
            String name = nameField.getText().trim();
            if (name.isEmpty()) {
                JOptionPane.showMessageDialog(dialog, "O nome nao pode estar vazio.", "Atencao",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }
            userProfile.setName(name);
            String nick = nickField.getText().trim();
            userProfile.setNickname(nick.isEmpty() ? null : nick);
            userGreetingLabel.setText(userProfile.getDisplayName());
            onProfileChanged();
            dialog.dispose();
            showInfoDialog("Perfil atualizado com sucesso!");
        });

        btnPanel.add(cancelBtn);
        btnPanel.add(okBtn);
        dialog.add(btnPanel, BorderLayout.SOUTH);

        // Enter no campo nome passa para apelido; Enter no apelido confirma
        nameField.addActionListener(e -> nickField.requestFocusInWindow());
        nickField.addActionListener(e -> okBtn.doClick());

        dialog.setVisible(true);
    }

    /** JOptionPane de confirmacao com texto claro */
    private int showConfirmDialog(String message, String title) {
        UIManager.put("OptionPane.background", AppTheme.BG_PANEL);
        UIManager.put("Panel.background",      AppTheme.BG_PANEL);
        UIManager.put("OptionPane.messageForeground", AppTheme.TEXT_PRIMARY);
        return JOptionPane.showConfirmDialog(this, message, title,
                JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
    }

    private void showInfoDialog(String message) {
        JDialog d = new JDialog(this, "Sucesso", true);
        d.setSize(300, 140);
        d.setLocationRelativeTo(this);
        d.setResizable(false);
        d.getContentPane().setBackground(AppTheme.BG_PANEL);
        d.setLayout(new BorderLayout());

        JLabel msg = new JLabel(message, JLabel.CENTER);
        msg.setFont(AppTheme.FONT_BODY);
        msg.setForeground(AppTheme.TEXT_PRIMARY);
        msg.setBorder(new EmptyBorder(24, 20, 8, 20));
        d.add(msg, BorderLayout.CENTER);

        JPanel bp = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 10));
        bp.setBackground(AppTheme.BG_PANEL);
        JButton ok = createDialogButton("OK", AppTheme.ACCENT, AppTheme.TEXT_PRIMARY);
        ok.setPreferredSize(new Dimension(90, 34));
        ok.addActionListener(e -> d.dispose());
        bp.add(ok);
        d.add(bp, BorderLayout.SOUTH);

        d.setVisible(true);
    }

    // ── Helpers visuais ───────────────────────────────────────────────────────

    private JButton createNavButton(String text, boolean active) {
        JButton btn = new JButton(text) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(getBackground());
                g2.fillRoundRect(4, 2, getWidth() - 8, getHeight() - 4, 8, 8);
                g2.dispose();
                super.paintComponent(g);
            }
        };
        btn.setFont(AppTheme.FONT_BODY);
        btn.setForeground(active ? AppTheme.TEXT_PRIMARY : AppTheme.TEXT_SECONDARY);
        btn.setBackground(active ? AppTheme.ACCENT : AppTheme.BG_PANEL);
        btn.setBorderPainted(false);
        btn.setFocusPainted(false);
        btn.setOpaque(false);
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btn.setHorizontalAlignment(SwingConstants.LEFT);
        btn.setBorder(new EmptyBorder(10, 20, 10, 16));
        btn.setMaximumSize(new Dimension(Integer.MAX_VALUE, 42));
        btn.setAlignmentX(Component.LEFT_ALIGNMENT);
        return btn;
    }

    private JPanel createStatRow(String label, java.util.function.IntSupplier countFn) {
        JPanel row = new JPanel(new BorderLayout());
        row.setBackground(AppTheme.BG_PANEL);
        row.setBorder(new EmptyBorder(3, 20, 3, 20));
        row.setMaximumSize(new Dimension(Integer.MAX_VALUE, 28));
        row.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel nameLabel = new JLabel(label);
        nameLabel.setFont(AppTheme.FONT_SMALL);
        nameLabel.setForeground(AppTheme.TEXT_SECONDARY);
        row.add(nameLabel, BorderLayout.WEST);

        JLabel countLabel = new JLabel() {
            @Override public String getText() { return String.valueOf(countFn.getAsInt()); }
        };
        countLabel.setFont(AppTheme.FONT_LABEL);
        countLabel.setForeground(AppTheme.ACCENT_LIGHT);
        row.add(countLabel, BorderLayout.EAST);

        return row;
    }

    private JSeparator createNavSeparator() {
        JSeparator sep = new JSeparator(SwingConstants.HORIZONTAL);
        sep.setForeground(AppTheme.BORDER);
        sep.setBackground(AppTheme.BORDER);
        sep.setMaximumSize(new Dimension(Integer.MAX_VALUE, 1));
        sep.setAlignmentX(Component.LEFT_ALIGNMENT);
        return sep;
    }

    private JTextField createStyledField(String value) {
        JTextField field = new JTextField(value);
        field.setBackground(AppTheme.BG_INPUT);
        field.setForeground(AppTheme.TEXT_PRIMARY);
        field.setCaretColor(AppTheme.ACCENT_LIGHT);
        field.setFont(AppTheme.FONT_BODY);
        field.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(AppTheme.BORDER, 1),
                new EmptyBorder(5, 10, 5, 10)));
        return field;
    }

    private JButton createDialogButton(String text, Color bg, Color fg) {
        JButton btn = new JButton(text) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(getBackground());
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 8, 8);
                g2.dispose();
                super.paintComponent(g);
            }
        };
        btn.setBackground(bg);
        btn.setForeground(fg);
        btn.setFont(AppTheme.FONT_BOLD);
        btn.setBorderPainted(false);
        btn.setFocusPainted(false);
        btn.setOpaque(false);
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btn.setBorder(new EmptyBorder(8, 20, 8, 20));
        return btn;
    }
}
