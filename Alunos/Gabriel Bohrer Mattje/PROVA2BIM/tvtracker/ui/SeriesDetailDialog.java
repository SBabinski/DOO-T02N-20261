package com.tvtracker.ui;

import com.tvtracker.model.TvSeries;
import com.tvtracker.model.UserProfile;
import com.tvtracker.service.TvMazeApiService;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.net.URI;
import java.net.URL;
import java.util.function.Supplier;

/**
 * Diálogo que exibe os detalhes de uma série de TV.
 */
public class SeriesDetailDialog extends JDialog {

    private final TvSeries series;
    private final UserProfile userProfile;
    private final Runnable onProfileChanged;

    public SeriesDetailDialog(Window owner, TvSeries series, UserProfile userProfile, Runnable onProfileChanged) {
        super(owner, "Detalhes da Série", ModalityType.APPLICATION_MODAL);
        this.series = series;
        this.userProfile = userProfile;
        this.onProfileChanged = onProfileChanged;

        setSize(640, 620);
        setLocationRelativeTo(owner);
        setResizable(false);
        getContentPane().setBackground(AppTheme.BG_DARK);
        setLayout(new BorderLayout());

        buildUI();
    }

    private void buildUI() {
        // Painel principal com scroll
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(AppTheme.BG_DARK);
        mainPanel.setBorder(new EmptyBorder(20, 24, 20, 24));

        // Cabeçalho: imagem + título
        JPanel headerPanel = new JPanel(new BorderLayout(16, 0));
        headerPanel.setBackground(AppTheme.BG_DARK);
        headerPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 160));

        // Imagem da série
        if (series.getImageUrl() != null && !series.getImageUrl().isBlank()) {
            JLabel imgLabel = loadImage(series.getImageUrl(), 100, 140);
            headerPanel.add(imgLabel, BorderLayout.WEST);
        }

        // Info básica ao lado da imagem
        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.Y_AXIS));
        titlePanel.setBackground(AppTheme.BG_DARK);

        JLabel nameLabel = new JLabel("<html><b>" + escapeHtml(series.getName()) + "</b></html>");
        nameLabel.setFont(AppTheme.FONT_TITLE);
        nameLabel.setForeground(AppTheme.TEXT_PRIMARY);
        nameLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        titlePanel.add(nameLabel);
        titlePanel.add(Box.createVerticalStrut(8));

        // Status badge
        JLabel statusLabel = createBadge(series.getStatusLabel(), getStatusColor(series));
        statusLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        titlePanel.add(statusLabel);
        titlePanel.add(Box.createVerticalStrut(8));

        // Nota
        String ratingText = series.getRating() > 0
                ? "[" + series.getRatingFormatted() + "]" + " / 10"
                : "Sem avaliacao";
        JLabel ratingLabel = new JLabel(ratingText);
        ratingLabel.setFont(AppTheme.FONT_BOLD);
        ratingLabel.setForeground(series.getRating() > 0 ? AppTheme.STAR_YELLOW : AppTheme.TEXT_MUTED);
        ratingLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        titlePanel.add(ratingLabel);

        headerPanel.add(titlePanel, BorderLayout.CENTER);
        mainPanel.add(headerPanel);
        mainPanel.add(Box.createVerticalStrut(20));

        // Separador
        mainPanel.add(createSeparator());
        mainPanel.add(Box.createVerticalStrut(16));

        // Informações detalhadas
        addInfoRow(mainPanel, "Idioma:", series.getLanguage());
        addInfoRow(mainPanel, "Gêneros:", series.getGenresAsString());
        addInfoRow(mainPanel, "Emissora:", series.getNetworkName());
        addInfoRow(mainPanel, "Estreia:", formatDate(series.getPremiereDate()));
        addInfoRow(mainPanel, "Término:", formatDate(series.getEndDate()));

        mainPanel.add(Box.createVerticalStrut(16));
        mainPanel.add(createSeparator());
        mainPanel.add(Box.createVerticalStrut(16));

        // Resumo
        if (series.getSummary() != null && !series.getSummary().isBlank()) {
            JLabel summaryTitle = new JLabel("Sinopse");
            summaryTitle.setFont(AppTheme.FONT_BOLD);
            summaryTitle.setForeground(AppTheme.ACCENT_LIGHT);
            summaryTitle.setAlignmentX(Component.LEFT_ALIGNMENT);
            mainPanel.add(summaryTitle);
            mainPanel.add(Box.createVerticalStrut(8));

            JTextArea summaryArea = new JTextArea(series.getSummary());
            summaryArea.setFont(AppTheme.FONT_BODY);
            summaryArea.setForeground(AppTheme.TEXT_SECONDARY);
            summaryArea.setBackground(AppTheme.BG_CARD);
            summaryArea.setLineWrap(true);
            summaryArea.setWrapStyleWord(true);
            summaryArea.setEditable(false);
            summaryArea.setBorder(new EmptyBorder(10, 12, 10, 12));
            summaryArea.setMaximumSize(new Dimension(Integer.MAX_VALUE, 120));
            summaryArea.setAlignmentX(Component.LEFT_ALIGNMENT);
            mainPanel.add(summaryArea);
            mainPanel.add(Box.createVerticalStrut(16));
        }

        // Botões de ação
        mainPanel.add(createSeparator());
        mainPanel.add(Box.createVerticalStrut(16));
        mainPanel.add(createActionButtons());

        JScrollPane scrollPane = new JScrollPane(mainPanel);
        scrollPane.setBorder(null);
        scrollPane.getViewport().setBackground(AppTheme.BG_DARK);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        add(scrollPane, BorderLayout.CENTER);

        // Botão fechar
        JButton closeBtn = createButton("Fechar", AppTheme.BG_CARD, AppTheme.TEXT_PRIMARY);
        closeBtn.addActionListener(e -> dispose());
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        bottomPanel.setBackground(AppTheme.BG_DARK);
        bottomPanel.setBorder(new EmptyBorder(8, 16, 12, 16));
        bottomPanel.add(closeBtn);
        add(bottomPanel, BorderLayout.SOUTH);
    }

    private JPanel createActionButtons() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 0));
        panel.setBackground(AppTheme.BG_DARK);
        panel.setAlignmentX(Component.LEFT_ALIGNMENT);

        // Favorito
        boolean isFav = userProfile.isFavorite(series);
        JButton favBtn = createButton(isFav ? "Remover Favorito" : "Favoritar",
                isFav ? AppTheme.WARNING : AppTheme.BG_CARD,
                isFav ? AppTheme.BG_DARK : AppTheme.WARNING);
        favBtn.addActionListener(e -> {
            if (userProfile.isFavorite(series)) {
                userProfile.removeFromFavorites(series);
                favBtn.setText("Favoritar");
                favBtn.setBackground(AppTheme.BG_CARD);
                favBtn.setForeground(AppTheme.WARNING);
            } else {
                userProfile.addToFavorites(series);
                favBtn.setText("Remover Favorito");
                favBtn.setBackground(AppTheme.WARNING);
                favBtn.setForeground(AppTheme.BG_DARK);
            }
            if (onProfileChanged != null) onProfileChanged.run();
        });
        panel.add(favBtn);

        // Assistida
        boolean isWatched = userProfile.isWatched(series);
        JButton watchedBtn = createButton(isWatched ? "Ja Assistida" : "Marcar Assistida",
                isWatched ? AppTheme.SUCCESS : AppTheme.BG_CARD,
                isWatched ? AppTheme.BG_DARK : AppTheme.SUCCESS);
        watchedBtn.addActionListener(e -> {
            if (userProfile.isWatched(series)) {
                userProfile.removeFromWatched(series);
                watchedBtn.setText("Marcar Assistida");
                watchedBtn.setBackground(AppTheme.BG_CARD);
                watchedBtn.setForeground(AppTheme.SUCCESS);
            } else {
                userProfile.addToWatched(series);
                userProfile.removeFromWatchLater(series);
                watchedBtn.setText("Ja Assistida");
                watchedBtn.setBackground(AppTheme.SUCCESS);
                watchedBtn.setForeground(AppTheme.BG_DARK);
            }
            if (onProfileChanged != null) onProfileChanged.run();
        });
        panel.add(watchedBtn);

        // Assistir Depois
        boolean isWatchLater = userProfile.isInWatchLater(series);
        JButton laterBtn = createButton(isWatchLater ? "Na Lista" : "Assistir Depois",
                isWatchLater ? AppTheme.ACCENT : AppTheme.BG_CARD,
                isWatchLater ? AppTheme.TEXT_PRIMARY : AppTheme.ACCENT_LIGHT);
        laterBtn.addActionListener(e -> {
            if (userProfile.isInWatchLater(series)) {
                userProfile.removeFromWatchLater(series);
                laterBtn.setText("Assistir Depois");
                laterBtn.setBackground(AppTheme.BG_CARD);
                laterBtn.setForeground(AppTheme.ACCENT_LIGHT);
            } else {
                userProfile.addToWatchLater(series);
                userProfile.removeFromWatched(series);
                laterBtn.setText("Na Lista");
                laterBtn.setBackground(AppTheme.ACCENT);
                laterBtn.setForeground(AppTheme.TEXT_PRIMARY);
            }
            if (onProfileChanged != null) onProfileChanged.run();
        });
        panel.add(laterBtn);

        return panel;
    }

    private void addInfoRow(JPanel parent, String label, String value) {
        if (value == null || value.isBlank() || value.equals("N/A")) return;
        JPanel row = new JPanel(new FlowLayout(FlowLayout.LEFT, 4, 0));
        row.setBackground(AppTheme.BG_DARK);
        row.setMaximumSize(new Dimension(Integer.MAX_VALUE, 28));
        row.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel lblLabel = new JLabel(label);
        lblLabel.setFont(AppTheme.FONT_LABEL);
        lblLabel.setForeground(AppTheme.TEXT_MUTED);
        row.add(lblLabel);

        JLabel valLabel = new JLabel(value);
        valLabel.setFont(AppTheme.FONT_BODY);
        valLabel.setForeground(AppTheme.TEXT_PRIMARY);
        row.add(valLabel);

        parent.add(row);
        parent.add(Box.createVerticalStrut(4));
    }

    private JLabel createBadge(String text, Color bg) {
        JLabel label = new JLabel("  " + text + "  ") {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(bg);
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 12, 12);
                g2.dispose();
                super.paintComponent(g);
            }
        };
        label.setFont(AppTheme.FONT_LABEL);
        label.setForeground(AppTheme.BG_DARK);
        label.setOpaque(false);
        return label;
    }

    private Color getStatusColor(TvSeries s) {
        if (s.getStatus() == null) return AppTheme.TEXT_MUTED;
        return switch (s.getStatus()) {
            case RUNNING -> AppTheme.SUCCESS;
            case ENDED -> AppTheme.TEXT_MUTED;
            case CANCELED -> AppTheme.DANGER;
            default -> AppTheme.WARNING;
        };
    }

    private JLabel loadImage(String imageUrl, int w, int h) {
        JLabel label = new JLabel();
        label.setPreferredSize(new Dimension(w, h));
        label.setBackground(AppTheme.BG_CARD);
        label.setOpaque(true);

        // Carrega a imagem em thread separada
        new Thread(() -> {
            try {
                URL url = URI.create(imageUrl).toURL();
                ImageIcon icon = new ImageIcon(url);
                Image scaled = icon.getImage().getScaledInstance(w, h, Image.SCALE_SMOOTH);
                SwingUtilities.invokeLater(() -> label.setIcon(new ImageIcon(scaled)));
            } catch (Exception e) {
                SwingUtilities.invokeLater(() -> label.setText("[TV]"));
            }
        }).start();

        return label;
    }

    private JSeparator createSeparator() {
        JSeparator sep = new JSeparator();
        sep.setForeground(AppTheme.BORDER);
        sep.setBackground(AppTheme.BORDER);
        sep.setMaximumSize(new Dimension(Integer.MAX_VALUE, 1));
        return sep;
    }

    private JButton createButton(String text, Color bg, Color fg) {
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
        btn.setFont(AppTheme.FONT_SMALL);
        btn.setBorderPainted(false);
        btn.setFocusPainted(false);
        btn.setOpaque(false);
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btn.setBorder(new EmptyBorder(7, 14, 7, 14));
        return btn;
    }

    private String formatDate(String date) {
        if (date == null || date.isBlank()) return "N/A";
        // date vem como YYYY-MM-DD
        if (date.length() == 10) {
            String[] parts = date.split("-");
            if (parts.length == 3) return parts[2] + "/" + parts[1] + "/" + parts[0];
        }
        return date;
    }

    private String escapeHtml(String text) {
        if (text == null) return "";
        return text.replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;");
    }
}
