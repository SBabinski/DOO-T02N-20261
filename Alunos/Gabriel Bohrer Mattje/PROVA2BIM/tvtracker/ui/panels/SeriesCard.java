package com.tvtracker.ui.panels;

import com.tvtracker.model.TvSeries;
import com.tvtracker.model.UserProfile;
import com.tvtracker.ui.AppTheme;
import com.tvtracker.ui.SeriesDetailDialog;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Componente visual que representa um card de série na lista.
 */
public class SeriesCard extends JPanel {

    private final TvSeries series;
    private final UserProfile userProfile;
    private final Runnable onProfileChanged;
    private boolean hovered = false;

    public SeriesCard(TvSeries series, UserProfile userProfile, Runnable onProfileChanged) {
        this.series = series;
        this.userProfile = userProfile;
        this.onProfileChanged = onProfileChanged;

        setLayout(new BorderLayout(12, 0));
        setBackground(AppTheme.BG_CARD);
        setBorder(new EmptyBorder(12, 14, 12, 14));
        setMaximumSize(new Dimension(Integer.MAX_VALUE, 100));
        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        buildUI();

        // Hover effect
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                hovered = true;
                repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                hovered = false;
                repaint();
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                Window owner = SwingUtilities.getWindowAncestor(SeriesCard.this);
                SeriesDetailDialog dialog = new SeriesDetailDialog(owner, series, userProfile, onProfileChanged);
                dialog.setVisible(true);
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        Color bg = hovered ? new Color(45, 45, 78) : AppTheme.BG_CARD;
        g2.setColor(bg);
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 12, 12);
        g2.dispose();
    }

    private void buildUI() {
        // Ícone (status color indicator)
        JPanel colorBar = new JPanel();
        colorBar.setPreferredSize(new Dimension(4, 0));
        colorBar.setBackground(getStatusColor());
        colorBar.setOpaque(true);
        add(colorBar, BorderLayout.WEST);

        // Info principal
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.setOpaque(false);

        // Nome
        JLabel nameLabel = new JLabel(series.getName() != null ? series.getName() : "Sem nome");
        nameLabel.setFont(AppTheme.FONT_BOLD);
        nameLabel.setForeground(AppTheme.TEXT_PRIMARY);
        nameLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        infoPanel.add(nameLabel);
        infoPanel.add(Box.createVerticalStrut(4));

        // Gêneros + idioma
        String meta = buildMetaString();
        JLabel metaLabel = new JLabel(meta);
        metaLabel.setFont(AppTheme.FONT_SMALL);
        metaLabel.setForeground(AppTheme.TEXT_SECONDARY);
        metaLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        infoPanel.add(metaLabel);
        infoPanel.add(Box.createVerticalStrut(4));

        // Data estreia + status
        String statusInfo = buildStatusString();
        JLabel statusLabel = new JLabel(statusInfo);
        statusLabel.setFont(AppTheme.FONT_SMALL);
        statusLabel.setForeground(AppTheme.TEXT_MUTED);
        statusLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        infoPanel.add(statusLabel);

        add(infoPanel, BorderLayout.CENTER);

        // Painel direito: nota + badges
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        rightPanel.setOpaque(false);
        rightPanel.setPreferredSize(new Dimension(100, 0));

        // Nota
        String ratingText = series.getRating() > 0
                ? "[" + series.getRatingFormatted() + "]"
                : "N/A";
        JLabel ratingLabel = new JLabel(ratingText);
        ratingLabel.setFont(AppTheme.FONT_BOLD);
        ratingLabel.setForeground(series.getRating() > 0 ? AppTheme.STAR_YELLOW : AppTheme.TEXT_MUTED);
        ratingLabel.setAlignmentX(Component.RIGHT_ALIGNMENT);
        rightPanel.add(ratingLabel);
        rightPanel.add(Box.createVerticalStrut(4));

        // Badge de status
        JLabel statusBadge = new JLabel(series.getStatusLabel()) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(new Color(getStatusColor().getRed(), getStatusColor().getGreen(),
                        getStatusColor().getBlue(), 50));
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 8, 8);
                g2.dispose();
                super.paintComponent(g);
            }
        };
        statusBadge.setFont(AppTheme.FONT_SMALL);
        statusBadge.setForeground(getStatusColor());
        statusBadge.setOpaque(false);
        statusBadge.setBorder(new EmptyBorder(2, 6, 2, 6));
        statusBadge.setAlignmentX(Component.RIGHT_ALIGNMENT);
        rightPanel.add(statusBadge);

        // Ícones de lista
        rightPanel.add(Box.createVerticalStrut(4));
        JPanel iconsPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 3, 0));
        iconsPanel.setOpaque(false);
        if (userProfile.isFavorite(series)) {
            JLabel favIcon = new JLabel("[F]");
            favIcon.setForeground(AppTheme.WARNING);
            favIcon.setFont(AppTheme.FONT_SMALL);
            iconsPanel.add(favIcon);
        }
        if (userProfile.isWatched(series)) {
            JLabel watchIcon = new JLabel("[A]");
            watchIcon.setForeground(AppTheme.SUCCESS);
            watchIcon.setFont(AppTheme.FONT_SMALL);
            iconsPanel.add(watchIcon);
        }
        if (userProfile.isInWatchLater(series)) {
            JLabel laterIcon = new JLabel("[+]");
            laterIcon.setFont(AppTheme.FONT_SMALL);
            iconsPanel.add(laterIcon);
        }
        rightPanel.add(iconsPanel);

        add(rightPanel, BorderLayout.EAST);
    }

    private Color getStatusColor() {
        if (series.getStatus() == null) return AppTheme.TEXT_MUTED;
        return switch (series.getStatus()) {
            case RUNNING -> AppTheme.SUCCESS;
            case ENDED -> AppTheme.TEXT_MUTED;
            case CANCELED -> AppTheme.DANGER;
            default -> AppTheme.WARNING;
        };
    }

    private String buildMetaString() {
        StringBuilder sb = new StringBuilder();
        if (series.getLanguage() != null && !series.getLanguage().isBlank()) {
            sb.append(series.getLanguage());
        }
        String genres = series.getGenresAsString();
        if (!genres.equals("N/A") && !genres.isBlank()) {
            if (!sb.isEmpty()) sb.append(" · ");
            sb.append(genres);
        }
        return sb.isEmpty() ? "N/A" : sb.toString();
    }

    private String buildStatusString() {
        StringBuilder sb = new StringBuilder();
        if (series.getPremiereDate() != null && !series.getPremiereDate().isBlank()) {
            sb.append("Estreou: ").append(series.getPremiereDate());
        }
        if (series.getNetworkName() != null && !series.getNetworkName().isBlank()) {
            if (!sb.isEmpty()) sb.append(" · ");
            sb.append(series.getNetworkName());
        }
        return sb.isEmpty() ? "" : sb.toString();
    }
}
