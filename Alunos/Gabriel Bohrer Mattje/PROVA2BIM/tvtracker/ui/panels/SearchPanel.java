package com.tvtracker.ui.panels;

import com.tvtracker.model.TvSeries;
import com.tvtracker.model.UserProfile;
import com.tvtracker.service.TvMazeApiService;
import com.tvtracker.ui.AppTheme;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;

/**
 * Painel de busca de séries via TvMaze API.
 */
public class SearchPanel extends JPanel {

    private final TvMazeApiService apiService;
    private final UserProfile userProfile;
    private final Runnable onProfileChanged;

    private JTextField searchField;
    private JButton searchBtn;
    private JPanel resultsPanel;
    private JLabel statusLabel;

    public SearchPanel(TvMazeApiService apiService, UserProfile userProfile, Runnable onProfileChanged) {
        this.apiService = apiService;
        this.userProfile = userProfile;
        this.onProfileChanged = onProfileChanged;

        setLayout(new BorderLayout(0, 0));
        setBackground(AppTheme.BG_DARK);
        buildUI();
    }

    private void buildUI() {
        // Topo: área de busca
        JPanel topPanel = new JPanel(new BorderLayout(0, 12));
        topPanel.setBackground(AppTheme.BG_DARK);
        topPanel.setBorder(new EmptyBorder(20, 24, 16, 24));

        JLabel titleLabel = new JLabel("Buscar Séries");
        titleLabel.setFont(AppTheme.FONT_TITLE);
        titleLabel.setForeground(AppTheme.TEXT_PRIMARY);
        topPanel.add(titleLabel, BorderLayout.NORTH);

        JPanel searchRow = new JPanel(new BorderLayout(8, 0));
        searchRow.setBackground(AppTheme.BG_DARK);

        searchField = new JTextField();
        styleTextField(searchField);
        searchField.setPreferredSize(new Dimension(0, 42));
        searchField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) performSearch();
            }
        });
        searchRow.add(searchField, BorderLayout.CENTER);

        searchBtn = createButton("Buscar", AppTheme.ACCENT, AppTheme.TEXT_PRIMARY);
        searchBtn.setPreferredSize(new Dimension(120, 42));
        searchBtn.addActionListener(e -> performSearch());
        searchRow.add(searchBtn, BorderLayout.EAST);

        topPanel.add(searchRow, BorderLayout.CENTER);

        statusLabel = new JLabel("Digite o nome de uma série e pressione Buscar.");
        statusLabel.setFont(AppTheme.FONT_SMALL);
        statusLabel.setForeground(AppTheme.TEXT_MUTED);
        topPanel.add(statusLabel, BorderLayout.SOUTH);

        add(topPanel, BorderLayout.NORTH);

        // Área de resultados
        resultsPanel = new JPanel();
        resultsPanel.setLayout(new BoxLayout(resultsPanel, BoxLayout.Y_AXIS));
        resultsPanel.setBackground(AppTheme.BG_DARK);

        JScrollPane scrollPane = new JScrollPane(resultsPanel);
        scrollPane.setBorder(null);
        scrollPane.getViewport().setBackground(AppTheme.BG_DARK);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        add(scrollPane, BorderLayout.CENTER);
    }

    private void performSearch() {
        String query = searchField.getText().trim();
        if (query.isEmpty()) {
            statusLabel.setText("Por favor, digite o nome de uma série.");
            statusLabel.setForeground(AppTheme.WARNING);
            return;
        }

        setSearchEnabled(false);
        statusLabel.setText("Buscando por \"" + query + "\"...");
        statusLabel.setForeground(AppTheme.TEXT_MUTED);
        resultsPanel.removeAll();
        resultsPanel.revalidate();
        resultsPanel.repaint();

        // Busca em thread separada para não travar a UI
        SwingWorker<List<TvSeries>, Void> worker = new SwingWorker<>() {
            @Override
            protected List<TvSeries> doInBackground() throws Exception {
                return apiService.searchByName(query);
            }

            @Override
            protected void done() {
                try {
                    List<TvSeries> results = get();
                    displayResults(results, query);
                } catch (Exception e) {
                    String msg = e.getCause() != null ? e.getCause().getMessage() : e.getMessage();
                    statusLabel.setText("Erro na busca: " + msg);
                    statusLabel.setForeground(AppTheme.DANGER);
                    showError("Não foi possível conectar à API TvMaze.\n\nVerifique sua conexão com a internet.\n\nErro: " + msg);
                } finally {
                    setSearchEnabled(true);
                }
            }
        };
        worker.execute();
    }

    private void displayResults(List<TvSeries> results, String query) {
        resultsPanel.removeAll();

        if (results.isEmpty()) {
            statusLabel.setText("Nenhum resultado para \"" + query + "\".");
            statusLabel.setForeground(AppTheme.WARNING);
            JLabel emptyLabel = new JLabel("Nenhuma série encontrada.", JLabel.CENTER);
            emptyLabel.setFont(AppTheme.FONT_BODY);
            emptyLabel.setForeground(AppTheme.TEXT_MUTED);
            emptyLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            resultsPanel.add(Box.createVerticalStrut(40));
            resultsPanel.add(emptyLabel);
        } else {
            statusLabel.setText(results.size() + " resultado(s) para \"" + query + "\".");
            statusLabel.setForeground(AppTheme.SUCCESS);

            resultsPanel.add(Box.createVerticalStrut(8));
            for (TvSeries s : results) {
                SeriesCard card = new SeriesCard(s, userProfile, onProfileChanged);
                card.setAlignmentX(Component.LEFT_ALIGNMENT);
                JPanel wrapper = new JPanel(new BorderLayout());
                wrapper.setBackground(AppTheme.BG_DARK);
                wrapper.setBorder(new EmptyBorder(4, 24, 4, 24));
                wrapper.add(card, BorderLayout.CENTER);
                resultsPanel.add(wrapper);
            }
            resultsPanel.add(Box.createVerticalGlue());
        }

        resultsPanel.revalidate();
        resultsPanel.repaint();
    }

    private void setSearchEnabled(boolean enabled) {
        searchField.setEnabled(enabled);
        searchBtn.setEnabled(enabled);
        searchBtn.setText(enabled ? "Buscar" : "Buscando...");
    }

    private void showError(String message) {
        JOptionPane.showMessageDialog(this, message, "Erro de Conexão",
                JOptionPane.ERROR_MESSAGE);
    }

    private void styleTextField(JTextField field) {
        field.setBackground(AppTheme.BG_INPUT);
        field.setForeground(AppTheme.TEXT_PRIMARY);
        field.setCaretColor(AppTheme.ACCENT_LIGHT);
        field.setFont(AppTheme.FONT_BODY);
        field.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(AppTheme.BORDER, 1, true),
                new EmptyBorder(6, 12, 6, 12)
        ));
    }

    private JButton createButton(String text, Color bg, Color fg) {
        JButton btn = new JButton(text) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(isEnabled() ? getBackground() : AppTheme.BG_CARD);
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
        return btn;
    }
}
