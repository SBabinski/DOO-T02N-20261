package tvtracker.ui;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;
import tvtracker.model.ListType;
import tvtracker.model.Show;
import tvtracker.persistence.PersistenceException;
import tvtracker.service.LibraryService;
import tvtracker.service.SortCriteria;


public class ListsPanel extends JPanel {

    private final LibraryService service;
    private final ListType listType;
    private final Window owner;

    private final ShowTableModel tableModel = new ShowTableModel();
    private final JTable table = new JTable(tableModel);
    private final JComboBox<SortCriteria> sortCombo = new JComboBox<>(SortCriteria.values());
    private final JLabel countLabel = new JLabel(" ");

    public ListsPanel(Window owner, LibraryService service, ListType listType) {
        super(new BorderLayout(8, 8));
        this.owner = owner;
        this.service = service;
        this.listType = listType;
        buildUi();
        refresh();
    }

    private void buildUi() {
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel topPanel = new JPanel(new BorderLayout(5, 5));
        JPanel sortRow = new JPanel(new FlowLayout(FlowLayout.LEFT));
        sortRow.add(new JLabel("Ordenar por:"));
        sortRow.add(sortCombo);
        topPanel.add(sortRow, BorderLayout.WEST);
        topPanel.add(countLabel, BorderLayout.EAST);
        add(topPanel, BorderLayout.NORTH);

        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setAutoCreateRowSorter(false);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton detailsButton = new JButton("Ver detalhes");
        JButton removeButton = new JButton("Remover da lista");
        JButton refreshButton = new JButton("Atualizar");
        bottomPanel.add(detailsButton);
        bottomPanel.add(removeButton);
        bottomPanel.add(refreshButton);
        add(bottomPanel, BorderLayout.SOUTH);

        sortCombo.addActionListener(e -> refresh());
        refreshButton.addActionListener(e -> refresh());

        detailsButton.addActionListener(e -> openDetailsForSelectedRow());
        removeButton.addActionListener(e -> removeSelected());

        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2 && table.getSelectedRow() != -1) {
                    openDetailsForSelectedRow();
                }
            }
        });
    }

    
    public void refresh() {
        SortCriteria criteria = (SortCriteria) sortCombo.getSelectedItem();
        if (criteria == null) {
            criteria = SortCriteria.NAME;
        }
        java.util.List<Show> shows = service.getSortedList(listType, criteria);
        tableModel.setShows(shows);
        countLabel.setText(shows.size() + " série(s) em " + listType.getLabel().toLowerCase());
    }

    private void openDetailsForSelectedRow() {
        int row = table.getSelectedRow();
        if (row == -1) {
            return;
        }
        Show show = tableModel.getShowAt(row);
        if (show != null) {
            ShowDetailsDialog dialog = new ShowDetailsDialog(owner, service, show, this::refresh);
            dialog.setVisible(true);
        }
    }

    private void removeSelected() {
        int row = table.getSelectedRow();
        if (row == -1) {
            UiUtils.showInfo(this, "Nenhuma seleção", "Selecione uma série na tabela primeiro.");
            return;
        }
        Show show = tableModel.getShowAt(row);
        if (show == null) {
            return;
        }
        boolean confirmed = UiUtils.confirm(this, "Confirmar remoção",
                "Remover \"" + show.getName() + "\" de " + listType.getLabel() + "?");
        if (!confirmed) {
            return;
        }
        try {
            service.removeFromList(listType, show.getId());
            refresh();
        } catch (PersistenceException ex) {
            UiUtils.showError(this, "Erro ao salvar",
                    "Não foi possível remover a série: " + ex.getMessage());
        }
    }
}
