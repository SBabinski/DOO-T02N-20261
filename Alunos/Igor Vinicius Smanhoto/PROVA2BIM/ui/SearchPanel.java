package com.tvtracker.ui;

import com.tvtracker.model.Series;
import com.tvtracker.model.UserData;
import com.tvtracker.service.DataPersistenceService;
import com.tvtracker.service.TVMazeService;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.List;

/**
 * Painel de busca de séries pelo nome usando a API TVMaze.
 * Exibe os resultados em uma lista e permite ver detalhes de cada série.
 */
public class SearchPanel extends JPanel {

    private final TVMazeService tvMazeService;
    private final UserData userData;
    private final DataPersistenceService persistenceService;
    private final Frame parentFrame;

    private JTextField searchField;
    private JButton searchButton;
    private JList<Series> resultsList;
    private DefaultListModel<Series> listModel;
    private JLabel statusLabel;

    public SearchPanel(Frame parentFrame, TVMazeService tvMazeService,
                       UserData userData, DataPersistenceService persistenceService) {
        this.parentFrame = parentFrame;
        this.tvMazeService = tvMazeService;
        this.userData = userData;
        this.persistenceService = persistenceService;

        setBackground(AppTheme.BG_PANEL);
        setLayout(new BorderLayout(0, 12));
        setBorder(new EmptyBorder(16, 16, 16, 16));

        buildUI();
    }

    // Monta todos os componentes visuais do painel de busca
    private void buildUI() {
        add(buildSearchBar(), BorderLayout.NORTH);
        add(buildResultsArea(), BorderLayout.CENTER);
    }

    // Cria a barra de busca com campo de texto e botão
    private JPanel buildSearchBar() {
        JPanel panel = new JPanel(new BorderLayout(8, 0));
        panel.setBackground(AppTheme.BG_PANEL);

        JLabel titleLabel = new JLabel("Buscar Séries");
        titleLabel.setFont(AppTheme.FONT_SUBTITLE);
        titleLabel.setForeground(AppTheme.TEXT_SECONDARY);
        titleLabel.setBorder(new EmptyBorder(0, 0, 8, 0));

        JPanel barPanel = new JPanel(new BorderLayout(8, 0));
        barPanel.setBackground(AppTheme.BG_PANEL);

        searchField = new JTextField();
        searchField.setFont(AppTheme.FONT_BODY);
        searchField.setBackground(AppTheme.BG_CARD);
        searchField.setForeground(AppTheme.TEXT_PRIMARY);
        searchField.setCaretColor(AppTheme.TEXT_PRIMARY);
        searchField.setBorder(BorderFactory.createCompoundBorder(
                AppTheme.BORDER_CARD,
                new EmptyBorder(8, 12, 8, 12)));

        // Permite buscar pressionando Enter no campo de texto
        searchField.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "search");
        searchField.getActionMap().put("search", new AbstractAction() {
            public void actionPerformed(ActionEvent e) { performSearch(); }
        });

        searchButton = AppTheme.createButton("Buscar", AppTheme.ACCENT);
        searchButton.addActionListener(e -> performSearch());

        barPanel.add(searchField, BorderLayout.CENTER);
        barPanel.add(searchButton, BorderLayout.EAST);

        JPanel wrapper = new JPanel(new BorderLayout());
        wrapper.setBackground(AppTheme.BG_PANEL);
        wrapper.add(titleLabel, BorderLayout.NORTH);
        wrapper.add(barPanel, BorderLayout.CENTER);

        return wrapper;
    }

    // Cria a área com a lista de resultados e status de busca
    private JPanel buildResultsArea() {
        JPanel panel = new JPanel(new BorderLayout(0, 8));
        panel.setBackground(AppTheme.BG_PANEL);

        statusLabel = new JLabel("Digite o nome de uma série e pressione Buscar.");
        statusLabel.setFont(AppTheme.FONT_SMALL);
        statusLabel.setForeground(AppTheme.TEXT_MUTED);

        listModel = new DefaultListModel<>();
        resultsList = new JList<>(listModel);
        resultsList.setCellRenderer(new SeriesListRenderer());
        resultsList.setFixedCellHeight(56);
        resultsList.setBackground(AppTheme.BG_PANEL);
        resultsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // Duplo clique abre o diálogo de detalhes da série selecionada
        resultsList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent e) {
                if (e.getClickCount() == 2) openDetails();
            }
        });

        JButton detailsBtn = AppTheme.createButton("Ver Detalhes / Gerenciar Listas", AppTheme.BG_CARD);
        detailsBtn.addActionListener(e -> openDetails());

        JScrollPane scrollPane = new JScrollPane(resultsList);
        scrollPane.setBorder(AppTheme.BORDER_CARD);
        scrollPane.setBackground(AppTheme.BG_PANEL);
        scrollPane.getViewport().setBackground(AppTheme.BG_PANEL);

        panel.add(statusLabel, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(detailsBtn, BorderLayout.SOUTH);

        return panel;
    }

    /**
     * Executa a busca na API em uma SwingWorker para não travar a interface.
     * Exibe feedback de carregamento e trata erros de rede.
     */
    private void performSearch() {
        String query = searchField.getText().trim();
        if (query.isEmpty()) {
            JOptionPane.showMessageDialog(parentFrame,
                    "Por favor, digite o nome de uma série para buscar.",
                    "Campo vazio", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Desativa o botão durante a busca para evitar requisições duplicadas
        searchButton.setEnabled(false);
        searchButton.setText("Buscando...");
        statusLabel.setText("Buscando por \"" + query + "\"...");
        listModel.clear();

        // SwingWorker executa a chamada de rede em thread separada
        new SwingWorker<List<Series>, Void>() {
            @Override
            protected List<Series> doInBackground() throws Exception {
                return tvMazeService.searchByName(query);
            }

            @Override
            protected void done() {
                searchButton.setEnabled(true);
                searchButton.setText("Buscar");
                try {
                    List<Series> results = get();
                    if (results.isEmpty()) {
                        statusLabel.setText("Nenhuma série encontrada para \"" + query + "\".");
                    } else {
                        statusLabel.setText(results.size() + " resultado(s) encontrado(s). "
                                + "Clique duas vezes para ver detalhes.");
                        for (Series s : results) listModel.addElement(s);
                    }
                } catch (Exception e) {
                    // getCause() pode ser null se a exceção não tiver causa encadeada
                    Throwable causa = e.getCause() != null ? e.getCause() : e;
                    statusLabel.setText("Erro na busca. Verifique sua conexão.");
                    JOptionPane.showMessageDialog(parentFrame,
                            "Não foi possível conectar à API TVMaze.\n" + causa.getMessage(),
                            "Erro de conexão", JOptionPane.ERROR_MESSAGE);
                }
            }
        }.execute();
    }

    // Abre o diálogo de detalhes para a série selecionada na lista
    private void openDetails() {
        Series selected = resultsList.getSelectedValue();
        if (selected == null) {
            JOptionPane.showMessageDialog(parentFrame,
                    "Selecione uma série na lista para ver os detalhes.",
                    "Nenhuma seleção", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        SeriesDetailDialog dialog = new SeriesDetailDialog(
                parentFrame, selected, userData, persistenceService);
        dialog.setVisible(true);
    }
}
