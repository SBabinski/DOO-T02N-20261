package com.tvtracker.ui;

import com.tvtracker.model.Series;
import com.tvtracker.model.UserData;
import com.tvtracker.service.DataPersistenceService;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.net.URL;
import javax.imageio.ImageIO;

/**
 * Diálogo modal que exibe todas as informações detalhadas de uma série,
 * e permite adicionar/remover das três listas do usuário.
 */
public class SeriesDetailDialog extends JDialog {

    private final Series series;
    private final UserData userData;
    private final DataPersistenceService persistenceService;
    private Runnable onDataChanged; // callback para atualizar a lista após mudança

    public SeriesDetailDialog(Frame parent, Series series, UserData userData,
                              DataPersistenceService persistenceService) {
        super(parent, series.getName(), true);
        this.series = series;
        this.userData = userData;
        this.persistenceService = persistenceService;

        setSize(640, 520);
        setLocationRelativeTo(parent);
        setResizable(false);
        getContentPane().setBackground(AppTheme.BG_DARK);
        setLayout(new BorderLayout(0, 0));

        buildUI();
    }

    // Registra callback para ser chamado quando o usuário alterar uma lista
    public void setOnDataChanged(Runnable callback) {
        this.onDataChanged = callback;
    }

    // Monta toda a interface do diálogo
    private void buildUI() {
        JPanel mainPanel = new JPanel(new BorderLayout(16, 0));
        mainPanel.setBackground(AppTheme.BG_DARK);
        mainPanel.setBorder(new EmptyBorder(20, 20, 10, 20));

        // Painel esquerdo: poster da série
        mainPanel.add(buildPosterPanel(), BorderLayout.WEST);

        // Painel direito: informações textuais
        mainPanel.add(buildInfoPanel(), BorderLayout.CENTER);

        add(mainPanel, BorderLayout.CENTER);
        add(buildActionPanel(), BorderLayout.SOUTH);
    }

    // Cria o painel com o poster da série (carregado da URL da API)
    private JPanel buildPosterPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(AppTheme.BG_DARK);
        panel.setPreferredSize(new Dimension(180, 260));

        JLabel posterLabel = new JLabel();
        posterLabel.setHorizontalAlignment(SwingConstants.CENTER);
        posterLabel.setVerticalAlignment(SwingConstants.TOP);
        posterLabel.setBackground(AppTheme.BG_CARD);
        posterLabel.setOpaque(true);

        // Carrega o poster em thread separada para não travar a UI
        if (series.getImageUrl() != null) {
            new Thread(() -> {
                try {
                    BufferedImage img = ImageIO.read(new URL(series.getImageUrl()));
                    if (img != null) {
                        Image scaled = img.getScaledInstance(160, 240, Image.SCALE_SMOOTH);
                        SwingUtilities.invokeLater(() ->
                                posterLabel.setIcon(new ImageIcon(scaled)));
                    }
                } catch (Exception e) {
                    // Falha silenciosa — poster é decorativo, não crítico
                }
            }).start();
        }

        panel.add(posterLabel, BorderLayout.CENTER);
        return panel;
    }

    // Cria o painel com todas as informações textuais da série
    private JPanel buildInfoPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(AppTheme.BG_DARK);
        panel.setBorder(new EmptyBorder(0, 8, 0, 0));

        // Título da série
        JLabel titleLabel = new JLabel(series.getName());
        titleLabel.setFont(AppTheme.FONT_TITLE);
        titleLabel.setForeground(AppTheme.TEXT_PRIMARY);
        titleLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        // Status com cor contextual
        JLabel statusLabel = new JLabel(series.getStatusInPortuguese());
        statusLabel.setFont(AppTheme.FONT_SUBTITLE);
        statusLabel.setForeground(AppTheme.getStatusColor(series.getStatus()));
        statusLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        panel.add(titleLabel);
        panel.add(Box.createVerticalStrut(4));
        panel.add(statusLabel);
        panel.add(Box.createVerticalStrut(12));

        // Grade de informações estruturadas
        panel.add(createInfoGrid());
        panel.add(Box.createVerticalStrut(12));

        // Sinopse com scroll caso seja longa
        panel.add(createSummaryArea());

        return panel;
    }

    // Cria a grade com campos "Rótulo: Valor" das informações principais
    private JPanel createInfoGrid() {
        JPanel grid = new JPanel(new GridLayout(0, 2, 4, 6));
        grid.setBackground(AppTheme.BG_DARK);
        grid.setAlignmentX(Component.LEFT_ALIGNMENT);
        grid.setMaximumSize(new Dimension(Integer.MAX_VALUE, 200));

        addInfoRow(grid, "Idioma:",      series.getLanguage());
        addInfoRow(grid, "Gêneros:",     series.getGenresAsString());
        addInfoRow(grid, "Nota:",        series.getRatingFormatted() + " / 10");
        addInfoRow(grid, "Emissora:",    series.getNetwork());
        addInfoRow(grid, "Exibição:",    series.getAirPeriod());

        return grid;
    }

    // Adiciona uma linha de rótulo + valor na grade de informações
    private void addInfoRow(JPanel grid, String label, String value) {
        JLabel lbl = new JLabel(label);
        lbl.setFont(AppTheme.FONT_SMALL);
        lbl.setForeground(AppTheme.TEXT_MUTED);

        JLabel val = new JLabel(value != null ? value : "N/A");
        val.setFont(AppTheme.FONT_BODY);
        val.setForeground(AppTheme.TEXT_PRIMARY);

        grid.add(lbl);
        grid.add(val);
    }

    // Área de texto rolável para a sinopse da série
    private JScrollPane createSummaryArea() {
        JTextArea summary = new JTextArea(series.getSummary());
        summary.setFont(AppTheme.FONT_SMALL);
        summary.setForeground(AppTheme.TEXT_SECONDARY);
        summary.setBackground(AppTheme.BG_DARK);
        summary.setEditable(false);
        summary.setLineWrap(true);
        summary.setWrapStyleWord(true);
        summary.setBorder(null);

        JScrollPane scroll = new JScrollPane(summary);
        scroll.setBorder(BorderFactory.createLineBorder(AppTheme.BG_CARD, 1));
        scroll.setBackground(AppTheme.BG_DARK);
        scroll.setPreferredSize(new Dimension(0, 80));
        scroll.setAlignmentX(Component.LEFT_ALIGNMENT);
        scroll.setMaximumSize(new Dimension(Integer.MAX_VALUE, 100));
        return scroll;
    }

    // Painel inferior com botões de gerenciamento das listas
    private JPanel buildActionPanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 12));
        panel.setBackground(AppTheme.BG_DARK);
        panel.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, AppTheme.BG_CARD));

        // Cada botão alterna entre adicionar e remover com base no estado atual
        panel.add(buildToggleButton(
                "Favoritos",
                userData.isInFavorites(series),
                () -> {
                    if (userData.isInFavorites(series)) userData.removeFromFavorites(series);
                    else userData.addToFavorites(series);
                }
        ));

        panel.add(buildToggleButton(
                "Assistidos",
                userData.isInWatched(series),
                () -> {
                    if (userData.isInWatched(series)) userData.removeFromWatched(series);
                    else userData.addToWatched(series);
                }
        ));

        panel.add(buildToggleButton(
                "Quero Assistir",
                userData.isInWantToWatch(series),
                () -> {
                    if (userData.isInWantToWatch(series)) userData.removeFromWantToWatch(series);
                    else userData.addToWantToWatch(series);
                }
        ));

        return panel;
    }

    // Cria um botão que alterna entre "Adicionar a" e "Remover de" uma lista
    private JButton buildToggleButton(String listName, boolean currentlyIn, Runnable toggleAction) {
        String label = (currentlyIn ? "Remover de " : "Adicionar a ") + listName;
        Color color = currentlyIn ? new Color(80, 80, 100) : AppTheme.ACCENT;

        JButton btn = AppTheme.createButton(label, color);
        btn.addActionListener(e -> {
            toggleAction.run();

            // Persiste a mudança imediatamente após cada interação
            try {
                persistenceService.save(userData);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this,
                        "Erro ao salvar: " + ex.getMessage(),
                        "Erro", JOptionPane.ERROR_MESSAGE);
            }

            // Notifica a janela pai para atualizar a lista exibida
            if (onDataChanged != null) onDataChanged.run();

            // Fecha e reabre o diálogo para refletir os botões atualizados
            dispose();
        });

        return btn;
    }
}
