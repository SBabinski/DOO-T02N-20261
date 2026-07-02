package com.tvtracker.ui.panels;

import com.tvtracker.model.TvSeries;
import com.tvtracker.model.UserProfile;
import com.tvtracker.service.SeriesListService;
import com.tvtracker.service.SeriesListService.SortOrder;
import com.tvtracker.ui.AppTheme;
import com.tvtracker.ui.SeriesDetailDialog;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.List;

/**
 * Painel que exibe e gerencia as listas do usuário (favoritos, assistidas, assistir depois).
 */
public class MyListsPanel extends JPanel {

    public enum ListType {
        FAVORITES("Favoritos"),
        WATCHED("Ja Assistidas"),
        WATCH_LATER("Assistir Depois");

        private final String label;
        ListType(String label) { this.label = label; }
        public String getLabel() { return label; }
    }

    private final UserProfile userProfile;
    private final SeriesListService listService;
    private final Runnable onProfileChanged;

    private ListType currentList = ListType.FAVORITES;
    private SortOrder currentSort = SortOrder.NAME_ASC;

    private JPanel listPanel;
    private JLabel listTitleLabel;
    private JLabel countLabel;
    private JComboBox<SortOrder> sortCombo;
    private final JToggleButton[] listButtons = new JToggleButton[3];

    public MyListsPanel(UserProfile userProfile, Runnable onProfileChanged) {
        this.userProfile = userProfile;
        this.listService = new SeriesListService();
        this.onProfileChanged = onProfileChanged;

        setLayout(new BorderLayout(0, 0));
        setBackground(AppTheme.BG_DARK);
        buildUI();
    }

    private void buildUI() {
        // Painel topo
        JPanel topPanel = new JPanel(new BorderLayout(0, 12));
        topPanel.setBackground(AppTheme.BG_DARK);
        topPanel.setBorder(new EmptyBorder(20, 24, 0, 24));

        JLabel titleLabel = new JLabel("Minhas Listas");
        titleLabel.setFont(AppTheme.FONT_TITLE);
        titleLabel.setForeground(AppTheme.TEXT_PRIMARY);
        topPanel.add(titleLabel, BorderLayout.NORTH);

        // Botões de abas para selecionar qual lista
        JPanel tabsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 6, 0));
        tabsPanel.setBackground(AppTheme.BG_DARK);

        ButtonGroup group = new ButtonGroup();
        ListType[] types = ListType.values();
        for (int i = 0; i < types.length; i++) {
            final ListType type = types[i];
            JToggleButton btn = createTabButton(type.getLabel(), type == currentList);
            btn.addActionListener(e -> {
                currentList = type;
                refreshList();
            });
            group.add(btn);
            tabsPanel.add(btn);
            listButtons[i] = btn;
        }
        topPanel.add(tabsPanel, BorderLayout.CENTER);

        // Linha de controles (título + ordenação)
        JPanel controlsRow = new JPanel(new BorderLayout(8, 0));
        controlsRow.setBackground(AppTheme.BG_DARK);
        controlsRow.setBorder(new EmptyBorder(12, 0, 12, 0));

        JPanel leftControls = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 0));
        leftControls.setBackground(AppTheme.BG_DARK);

        listTitleLabel = new JLabel("Favoritos");
        listTitleLabel.setFont(AppTheme.FONT_SUBTITLE);
        listTitleLabel.setForeground(AppTheme.TEXT_PRIMARY);
        leftControls.add(listTitleLabel);

        countLabel = new JLabel("(0)");
        countLabel.setFont(AppTheme.FONT_BODY);
        countLabel.setForeground(AppTheme.TEXT_MUTED);
        leftControls.add(countLabel);

        controlsRow.add(leftControls, BorderLayout.WEST);

        // Ordenação
        JPanel sortPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 6, 0));
        sortPanel.setBackground(AppTheme.BG_DARK);

        JLabel sortLabel = new JLabel("Ordenar por:");
        sortLabel.setFont(AppTheme.FONT_SMALL);
        sortLabel.setForeground(AppTheme.TEXT_MUTED);
        sortPanel.add(sortLabel);

        sortCombo = new JComboBox<>(SortOrder.values());
        sortCombo.setFont(AppTheme.FONT_SMALL);
        sortCombo.setBackground(AppTheme.BG_INPUT);
        sortCombo.setForeground(AppTheme.TEXT_PRIMARY);
        sortCombo.setSelectedItem(currentSort);
        sortCombo.addActionListener(e -> {
            currentSort = (SortOrder) sortCombo.getSelectedItem();
            refreshList();
        });
        sortPanel.add(sortCombo);

        controlsRow.add(sortPanel, BorderLayout.EAST);
        topPanel.add(controlsRow, BorderLayout.SOUTH);

        add(topPanel, BorderLayout.NORTH);

        // Área da lista
        listPanel = new JPanel();
        listPanel.setLayout(new BoxLayout(listPanel, BoxLayout.Y_AXIS));
        listPanel.setBackground(AppTheme.BG_DARK);

        JScrollPane scrollPane = new JScrollPane(listPanel);
        scrollPane.setBorder(null);
        scrollPane.getViewport().setBackground(AppTheme.BG_DARK);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        add(scrollPane, BorderLayout.CENTER);

        refreshList();
    }

    /**
     * Atualiza a lista exibida. Deve ser chamado sempre que o perfil for modificado.
     */
    public void refreshList() {
        List<TvSeries> rawList = getCurrentList();
        List<TvSeries> sortedList = listService.sort(rawList, currentSort);

        listTitleLabel.setText(currentList.getLabel());
        countLabel.setText("(" + rawList.size() + ")");

        listPanel.removeAll();

        if (sortedList.isEmpty()) {
            listPanel.add(Box.createVerticalStrut(40));
            JLabel emptyLabel = new JLabel(getEmptyMessage(), JLabel.CENTER);
            emptyLabel.setFont(AppTheme.FONT_BODY);
            emptyLabel.setForeground(AppTheme.TEXT_MUTED);
            emptyLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            listPanel.add(emptyLabel);

            JLabel tipLabel = new JLabel("Busque séries e adicione usando o botão correspondente.", JLabel.CENTER);
            tipLabel.setFont(AppTheme.FONT_SMALL);
            tipLabel.setForeground(AppTheme.TEXT_MUTED);
            tipLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            listPanel.add(Box.createVerticalStrut(8));
            listPanel.add(tipLabel);
        } else {
            listPanel.add(Box.createVerticalStrut(4));
            for (TvSeries s : sortedList) {
                JPanel wrapper = new JPanel(new BorderLayout(8, 0));
                wrapper.setBackground(AppTheme.BG_DARK);
                wrapper.setBorder(new EmptyBorder(4, 24, 4, 24));
                wrapper.setMaximumSize(new Dimension(Integer.MAX_VALUE, 110));

                SeriesCard card = new SeriesCard(s, userProfile, () -> {
                    if (onProfileChanged != null) onProfileChanged.run();
                    refreshList();
                });

                // Botão remover à direita
                JButton removeBtn = createRemoveButton(s);
                wrapper.add(card, BorderLayout.CENTER);
                wrapper.add(removeBtn, BorderLayout.EAST);
                listPanel.add(wrapper);
            }
            listPanel.add(Box.createVerticalGlue());
        }

        listPanel.revalidate();
        listPanel.repaint();
    }

    private List<TvSeries> getCurrentList() {
        return switch (currentList) {
            case FAVORITES -> userProfile.getFavorites();
            case WATCHED -> userProfile.getWatched();
            case WATCH_LATER -> userProfile.getWatchLater();
        };
    }

    private String getEmptyMessage() {
        return switch (currentList) {
            case FAVORITES -> "Nenhum favorito adicionado.";
            case WATCHED -> "Nenhuma série marcada como assistida.";
            case WATCH_LATER -> "Nenhuma série na lista 'Assistir Depois'.";
        };
    }

    private JButton createRemoveButton(TvSeries series) {
        JButton btn = new JButton("X") {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(new Color(AppTheme.DANGER.getRed(), AppTheme.DANGER.getGreen(),
                        AppTheme.DANGER.getBlue(), isEnabled() ? 40 : 20));
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 8, 8);
                g2.dispose();
                super.paintComponent(g);
            }
        };
        btn.setFont(AppTheme.FONT_SMALL);
        btn.setForeground(AppTheme.DANGER);
        btn.setBackground(new Color(0, 0, 0, 0));
        btn.setBorderPainted(false);
        btn.setFocusPainted(false);
        btn.setOpaque(false);
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btn.setPreferredSize(new Dimension(36, 36));
        btn.setToolTipText("Remover da lista");

        btn.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(this,
                    "Remover \"" + series.getName() + "\" da lista?",
                    "Confirmar Remoção", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (confirm == JOptionPane.YES_OPTION) {
                switch (currentList) {
                    case FAVORITES -> userProfile.removeFromFavorites(series);
                    case WATCHED -> userProfile.removeFromWatched(series);
                    case WATCH_LATER -> userProfile.removeFromWatchLater(series);
                }
                if (onProfileChanged != null) onProfileChanged.run();
                refreshList();
            }
        });
        return btn;
    }

    private JToggleButton createTabButton(String text, boolean selected) {
        JToggleButton btn = new JToggleButton(text, selected) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                Color bg = isSelected() ? AppTheme.ACCENT : AppTheme.BG_CARD;
                g2.setColor(bg);
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 10, 10);
                g2.dispose();
                super.paintComponent(g);
            }
        };
        btn.setFont(AppTheme.FONT_SMALL);
        btn.setForeground(selected ? AppTheme.TEXT_PRIMARY : AppTheme.TEXT_SECONDARY);
        btn.setBackground(selected ? AppTheme.ACCENT : AppTheme.BG_CARD);
        btn.setBorderPainted(false);
        btn.setFocusPainted(false);
        btn.setOpaque(false);
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btn.setBorder(new EmptyBorder(8, 16, 8, 16));
        return btn;
    }
}
