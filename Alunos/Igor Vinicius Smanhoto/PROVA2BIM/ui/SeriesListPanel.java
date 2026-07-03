package com.tvtracker.ui;

import com.tvtracker.model.Series;
import com.tvtracker.model.SortOption;
import com.tvtracker.model.UserData;
import com.tvtracker.service.DataPersistenceService;
import com.tvtracker.service.SeriesSortService;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * Painel reutilizável que exibe uma das três listas de séries do usuário
 * (favoritos, assistidos, quero assistir) com suporte a ordenação.
 * Recebe lambdas para obter, adicionar e remover séries, tornando-o genérico.
 */
public class SeriesListPanel extends JPanel {

    private final Frame parentFrame;
    private final UserData userData;
    private final DataPersistenceService persistenceService;
    private final SeriesSortService sortService;

    // Funções injetadas para interagir com a lista correta no UserData
    private final Supplier<List<Series>> listGetter;
    private final Consumer<Series> remover;
    private final String panelTitle;

    private JList<Series> seriesList;
    private DefaultListModel<Series> listModel;
    private JComboBox<SortOption> sortCombo;
    private JLabel countLabel;

    public SeriesListPanel(Frame parentFrame, String title, UserData userData,
                           DataPersistenceService persistenceService,
                           SeriesSortService sortService,
                           Supplier<List<Series>> listGetter,
                           Consumer<Series> remover) {
        this.parentFrame = parentFrame;
        this.panelTitle = title;
        this.userData = userData;
        this.persistenceService = persistenceService;
        this.sortService = sortService;
        this.listGetter = listGetter;
        this.remover = remover;

        setBackground(AppTheme.BG_PANEL);
        setLayout(new BorderLayout(0, 12));
        setBorder(new EmptyBorder(16, 16, 16, 16));

        buildUI();
        refresh();
    }

    // Monta todos os componentes do painel
    private void buildUI() {
        add(buildHeader(), BorderLayout.NORTH);
        add(buildListArea(), BorderLayout.CENTER);
        add(buildButtonBar(), BorderLayout.SOUTH);
    }

    // Cabeçalho com título, contador e seletor de ordenação
    private JPanel buildHeader() {
        JPanel panel = new JPanel(new BorderLayout(8, 4));
        panel.setBackground(AppTheme.BG_PANEL);

        JLabel titleLabel = new JLabel(panelTitle);
        titleLabel.setFont(AppTheme.FONT_SUBTITLE);
        titleLabel.setForeground(AppTheme.TEXT_SECONDARY);

        countLabel = new JLabel("0 séries");
        countLabel.setFont(AppTheme.FONT_SMALL);
        countLabel.setForeground(AppTheme.TEXT_MUTED);

        JPanel titleRow = new JPanel(new BorderLayout(8, 0));
        titleRow.setBackground(AppTheme.BG_PANEL);
        titleRow.add(titleLabel, BorderLayout.WEST);
        titleRow.add(countLabel, BorderLayout.EAST);

        // Seletor de ordenação com todas as opções disponíveis
        sortCombo = new JComboBox<>(SortOption.values());
        sortCombo.setFont(AppTheme.FONT_SMALL);
        sortCombo.setBackground(AppTheme.BG_CARD);
        sortCombo.setForeground(AppTheme.TEXT_PRIMARY);
        sortCombo.setPreferredSize(new Dimension(180, 28));
        sortCombo.addActionListener(e -> refresh());

        JPanel sortRow = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        sortRow.setBackground(AppTheme.BG_PANEL);
        JLabel sortLabel = new JLabel("Ordenar por: ");
        sortLabel.setFont(AppTheme.FONT_SMALL);
        sortLabel.setForeground(AppTheme.TEXT_MUTED);
        sortRow.add(sortLabel);
        sortRow.add(sortCombo);

        panel.add(titleRow, BorderLayout.NORTH);
        panel.add(sortRow, BorderLayout.SOUTH);

        return panel;
    }

    // Área principal com a lista de séries rolável
    private JScrollPane buildListArea() {
        listModel = new DefaultListModel<>();
        seriesList = new JList<>(listModel);
        seriesList.setCellRenderer(new SeriesListRenderer());
        seriesList.setFixedCellHeight(56);
        seriesList.setBackground(AppTheme.BG_PANEL);
        seriesList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // Duplo clique abre o diálogo de detalhes
        seriesList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent e) {
                if (e.getClickCount() == 2) openDetails();
            }
        });

        JScrollPane scroll = new JScrollPane(seriesList);
        scroll.setBorder(AppTheme.BORDER_CARD);
        scroll.setBackground(AppTheme.BG_PANEL);
        scroll.getViewport().setBackground(AppTheme.BG_PANEL);
        return scroll;
    }

    // Barra inferior com botões de ação
    private JPanel buildButtonBar() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 8, 0));
        panel.setBackground(AppTheme.BG_PANEL);

        JButton detailsBtn = AppTheme.createButton("Ver Detalhes", AppTheme.BG_CARD);
        detailsBtn.addActionListener(e -> openDetails());

        JButton removeBtn = AppTheme.createButton("Remover da lista", new Color(100, 40, 40));
        removeBtn.addActionListener(e -> removeSelected());

        panel.add(detailsBtn);
        panel.add(removeBtn);
        return panel;
    }

    /**
     * Recarrega a lista do UserData, aplica a ordenação escolhida
     * e atualiza o modelo do JList. Chamado após qualquer mudança nos dados.
     */
    public void refresh() {
        SortOption selectedSort = (SortOption) sortCombo.getSelectedItem();
        List<Series> sorted = sortService.sort(listGetter.get(), selectedSort);

        listModel.clear();
        for (Series s : sorted) listModel.addElement(s);

        int count = sorted.size();
        countLabel.setText(count + (count == 1 ? " série" : " séries"));
    }

    // Abre o diálogo de detalhes da série selecionada na lista
    private void openDetails() {
        Series selected = seriesList.getSelectedValue();
        if (selected == null) {
            JOptionPane.showMessageDialog(parentFrame,
                    "Selecione uma série na lista.",
                    "Nenhuma seleção", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        SeriesDetailDialog dialog = new SeriesDetailDialog(
                parentFrame, selected, userData, persistenceService);
        // Atualiza a lista quando o usuário volta do diálogo de detalhes
        dialog.setOnDataChanged(this::refresh);
        dialog.setVisible(true);
        refresh();
    }

    // Remove a série selecionada da lista após confirmação do usuário
    private void removeSelected() {
        Series selected = seriesList.getSelectedValue();
        if (selected == null) {
            JOptionPane.showMessageDialog(parentFrame,
                    "Selecione uma série para remover.",
                    "Nenhuma seleção", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(parentFrame,
                "Remover \"" + selected.getName() + "\" de " + panelTitle + "?",
                "Confirmar remoção",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE);

        if (confirm == JOptionPane.YES_OPTION) {
            remover.accept(selected);
            try {
                persistenceService.save(userData);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(parentFrame,
                        "Erro ao salvar: " + e.getMessage(),
                        "Erro", JOptionPane.ERROR_MESSAGE);
            }
            refresh();
        }
    }
}
