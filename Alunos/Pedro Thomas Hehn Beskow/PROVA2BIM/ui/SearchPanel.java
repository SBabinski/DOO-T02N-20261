package tvtracker.ui;

import java.awt.*;
import java.awt.event.*;
import java.util.List;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.*;
import tvtracker.model.TvShow;
import tvtracker.model.UserData;
import tvtracker.service.DataService;
import tvtracker.service.TvMazeService;

public class SearchPanel extends JPanel {

    private final TvMazeService tvMazeService;
    private final UserData userData;
    private final DataService dataService;
    private final Runnable onDataChanged;

    private JTextField searchField;
    private JButton searchButton;
    private ShowTableModel tableModel;
    private JTable resultTable;
    private JLabel statusLabel;

    public SearchPanel(TvMazeService tvMazeService, UserData userData,
                       DataService dataService, Runnable onDataChanged) {
        this.tvMazeService = tvMazeService;
        this.userData      = userData;
        this.dataService   = dataService;
        this.onDataChanged = onDataChanged;
        buildUi();
    }

    private void buildUi() {
        setLayout(new BorderLayout(0, 8));
        setBorder(new EmptyBorder(12, 12, 12, 12));

        // Barra de busca
        JPanel searchBar = new JPanel(new BorderLayout(8, 0));
        searchBar.setOpaque(false);

        JLabel searchIcon = new JLabel("🔍");
        searchIcon.setFont(searchIcon.getFont().deriveFont(16f));
        searchBar.add(searchIcon, BorderLayout.WEST);

        searchField = new JTextField();
        searchField.setFont(searchField.getFont().deriveFont(14f));
        searchField.setToolTipText("Digite o nome da série");
        searchField.addActionListener(e -> doSearch());
        searchBar.add(searchField, BorderLayout.CENTER);

        searchButton = new JButton("Buscar");
        searchButton.setPreferredSize(new Dimension(100, 32));
        searchButton.addActionListener(e -> doSearch());
        searchBar.add(searchButton, BorderLayout.EAST);

        add(searchBar, BorderLayout.NORTH);

        // Tabela de resultados
        tableModel  = new ShowTableModel();
        resultTable = buildTable(tableModel);

        resultTable.addMouseListener(new MouseAdapter() {
            @Override public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) openDetails();
            }
        });

        JScrollPane scrollPane = new JScrollPane(resultTable);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Resultados da busca"));
        add(scrollPane, BorderLayout.CENTER);

        // Botões de ação
        JPanel actionsPanel = new JPanel(new BorderLayout());
        actionsPanel.setOpaque(false);

        statusLabel = new JLabel(" ");
        statusLabel.setFont(statusLabel.getFont().deriveFont(12f));
        statusLabel.setForeground(Color.DARK_GRAY);
        actionsPanel.add(statusLabel, BorderLayout.WEST);

        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 6, 0));
        btnPanel.setOpaque(false);

        JButton detailsBtn = new JButton("📋 Ver Detalhes");
        detailsBtn.addActionListener(e -> openDetails());

        JButton favBtn = new JButton("⭐ + Favorito");
        favBtn.addActionListener(e -> quickAdd("favorite"));

        JButton watchedBtn = new JButton("✅ + Assistida");
        watchedBtn.addActionListener(e -> quickAdd("watched"));

        JButton wlBtn = new JButton("🔖 + Quero Assistir");
        wlBtn.addActionListener(e -> quickAdd("watchlist"));

        btnPanel.add(detailsBtn);
        btnPanel.add(favBtn);
        btnPanel.add(watchedBtn);
        btnPanel.add(wlBtn);
        actionsPanel.add(btnPanel, BorderLayout.EAST);

        add(actionsPanel, BorderLayout.SOUTH);
    }

    static JTable buildTable(ShowTableModel model) {
        JTable table = new JTable(model);
        table.setRowHeight(24);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
        table.getTableHeader().setReorderingAllowed(false);
        table.setFillsViewportHeight(true);
        table.setGridColor(new Color(0xE0E0E0));
        table.setShowGrid(true);

        // Definir larguras das colunas
        int[] widths = {200, 80, 160, 80, 100, 90, 130};
        for (int i = 0; i < widths.length && i < table.getColumnCount(); i++) {
            TableColumn col = table.getColumnModel().getColumn(i);
            col.setPreferredWidth(widths[i]);
        }

        // Cor alternada nas linhas
        table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable t, Object value,
                    boolean isSelected, boolean hasFocus, int row, int col) {
                Component c = super.getTableCellRendererComponent(t, value, isSelected, hasFocus, row, col);
                if (!isSelected) {
                    c.setBackground(row % 2 == 0 ? Color.WHITE : new Color(0xF5F5F5));
                }
                return c;
            }
        });

        table.setAutoCreateRowSorter(true);
        return table;
    }

    private void doSearch() {
        String query = searchField.getText().trim();
        if (query.isBlank()) {
            JOptionPane.showMessageDialog(this, "Digite um nome para buscar.",
                    "Campo vazio", JOptionPane.WARNING_MESSAGE);
            return;
        }

        searchButton.setEnabled(false);
        statusLabel.setText("Buscando...");
        tableModel.setShows(List.of());

        SwingWorker<List<TvShow>, Void> worker = new SwingWorker<>() {
            @Override
            protected List<TvShow> doInBackground() throws Exception {
                return tvMazeService.searchShows(query);
            }

            @Override
            protected void done() {
                searchButton.setEnabled(true);
                try {
                    List<TvShow> results = get();
                    tableModel.setShows(results);
                    if (results.isEmpty()) {
                        statusLabel.setText("Nenhuma série encontrada para \"" + query + "\".");
                    } else {
                        statusLabel.setText(results.size() + " série(s) encontrada(s).");
                    }
                } catch (Exception ex) {
                    statusLabel.setText("Erro: " + ex.getCause().getMessage());
                    JOptionPane.showMessageDialog(SearchPanel.this,
                            "Erro ao buscar séries:\n" + ex.getCause().getMessage(),
                            "Erro de conexão", JOptionPane.ERROR_MESSAGE);
                }
            }
        };
        worker.execute();
    }

    private void openDetails() {
        TvShow show = getSelectedShow();
        if (show == null) {
            JOptionPane.showMessageDialog(this, "Selecione uma série.",
                    "Nenhuma seleção", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        Frame parent = (Frame) SwingUtilities.getWindowAncestor(this);
        new ShowDetailsDialog(parent, show, userData, dataService, () -> {
            if (onDataChanged != null) onDataChanged.run();
        }).setVisible(true);
    }

    private void quickAdd(String listType) {
        TvShow show = getSelectedShow();
        if (show == null) {
            JOptionPane.showMessageDialog(this, "Selecione uma série primeiro.",
                    "Nenhuma seleção", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        boolean added = switch (listType) {
            case "favorite"  -> userData.addFavorite(show);
            case "watched"   -> userData.addWatched(show);
            case "watchlist" -> userData.addToWatchlist(show);
            default          -> false;
        };
        String listName = switch (listType) {
            case "favorite"  -> "Favoritos";
            case "watched"   -> "Já Assistidas";
            case "watchlist" -> "Quero Assistir";
            default          -> "lista";
        };
        if (added) {
            try { dataService.save(userData); } catch (Exception ex) { /* ignora */ }
            statusLabel.setText("\"" + show.getName() + "\" adicionada a " + listName + ".");
            if (onDataChanged != null) onDataChanged.run();
        } else {
            statusLabel.setText("\"" + show.getName() + "\" já está em " + listName + ".");
        }
    }

    private TvShow getSelectedShow() {
        int viewRow = resultTable.getSelectedRow();
        if (viewRow < 0) return null;
        int modelRow = resultTable.convertRowIndexToModel(viewRow);
        return tableModel.getShowAt(modelRow);
    }
}
