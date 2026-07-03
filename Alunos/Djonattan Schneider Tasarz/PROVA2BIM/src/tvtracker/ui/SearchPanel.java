package tvtracker.ui;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import tvtracker.api.ApiException;
import tvtracker.model.Show;
import tvtracker.service.LibraryService;


public class SearchPanel extends JPanel {

    private final LibraryService service;
    private final Window owner;

    private final JTextField searchField = new JTextField();
    private final JButton searchButton = new JButton("Buscar");
    private final JButton detailsButton = new JButton("Ver detalhes / adicionar a uma lista");
    private final JLabel statusLabel = new JLabel(" ");
    private final ShowTableModel tableModel = new ShowTableModel();
    private final JTable table = new JTable(tableModel);

    public SearchPanel(Window owner, LibraryService service) {
        super(new BorderLayout(8, 8));
        this.owner = owner;
        this.service = service;
        buildUi();
    }

    private void buildUi() {
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel topPanel = new JPanel(new BorderLayout(5, 5));
        JLabel instructions = new JLabel("Digite o nome (total ou parcial) de uma série e clique em Buscar:");
        topPanel.add(instructions, BorderLayout.NORTH);

        JPanel inputRow = new JPanel(new BorderLayout(5, 0));
        inputRow.add(searchField, BorderLayout.CENTER);
        inputRow.add(searchButton, BorderLayout.EAST);
        topPanel.add(inputRow, BorderLayout.CENTER);

        add(topPanel, BorderLayout.NORTH);

        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setAutoCreateRowSorter(true);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel(new BorderLayout(5, 5));
        detailsButton.setEnabled(false);
        JPanel detailsRow = new JPanel(new FlowLayout(FlowLayout.LEFT));
        detailsRow.add(detailsButton);
        bottomPanel.add(detailsRow, BorderLayout.NORTH);
        bottomPanel.add(statusLabel, BorderLayout.SOUTH);
        add(bottomPanel, BorderLayout.SOUTH);

        searchButton.addActionListener(e -> performSearch());
        searchField.addActionListener(e -> performSearch());

        table.getSelectionModel().addListSelectionListener((ListSelectionEvent e) -> {
            if (!e.getValueIsAdjusting()) {
                detailsButton.setEnabled(table.getSelectedRow() != -1);
            }
        });

        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2 && table.getSelectedRow() != -1) {
                    openDetailsForSelectedRow();
                }
            }
        });

        detailsButton.addActionListener(e -> openDetailsForSelectedRow());
    }

    private void performSearch() {
        String query = searchField.getText();
        if (query == null || query.isBlank()) {
            statusLabel.setText("Digite algo para buscar.");
            return;
        }

        searchButton.setEnabled(false);
        statusLabel.setText("Buscando \"" + query.trim() + "\" na API do TVMaze...");
        tableModel.setShows(java.util.Collections.emptyList());


        SwingWorker<List<Show>, Void> worker = new SwingWorker<>() {
            private ApiException error;

            @Override
            protected List<Show> doInBackground() {
                try {
                    return service.search(query);
                } catch (ApiException e) {
                    error = e;
                    return java.util.Collections.emptyList();
                }
            }

            @Override
            protected void done() {
                searchButton.setEnabled(true);
                if (error != null) {
                    statusLabel.setText("Falha na busca.");
                    UiUtils.showError(SearchPanel.this, "Erro ao buscar séries", error.getMessage());
                    return;
                }
                try {
                    List<Show> results = get();
                    tableModel.setShows(results);
                    statusLabel.setText(results.isEmpty()
                            ? "Nenhuma série encontrada para \"" + query.trim() + "\"."
                            : results.size() + " série(s) encontrada(s).");
                } catch (Exception ex) {
                    statusLabel.setText("Erro inesperado ao processar os resultados.");
                    UiUtils.showError(SearchPanel.this, "Erro inesperado", ex.getMessage());
                }
            }
        };
        worker.execute();
    }

    private void openDetailsForSelectedRow() {
        int viewRow = table.getSelectedRow();
        if (viewRow == -1) {
            return;
        }
        int modelRow = table.convertRowIndexToModel(viewRow);
        Show show = tableModel.getShowAt(modelRow);
        if (show != null) {
            ShowDetailsDialog dialog = new ShowDetailsDialog(owner, service, show, () -> { /* nada a atualizar aqui */ });
            dialog.setVisible(true);
        }
    }
}
