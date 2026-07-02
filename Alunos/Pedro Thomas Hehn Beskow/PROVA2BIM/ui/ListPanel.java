package tvtracker.ui;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import tvtracker.model.TvShow;
import tvtracker.model.UserData;
import tvtracker.service.DataService;

public class ListPanel extends JPanel {

    public enum ListType { FAVORITES, WATCHED, WATCHLIST }

    private final ListType listType;
    private final UserData userData;
    private final DataService dataService;
    private final Runnable onDataChanged;

    private ShowTableModel tableModel;
    private JTable table;
    private JLabel countLabel;
    private JComboBox<String> sortCombo;

    private static final String[] SORT_OPTIONS = {
            "Ordem original",
            "Nome (A-Z)",
            "Nome (Z-A)",
            "Nota (maior primeiro)",
            "Nota (menor primeiro)",
            "Estado",
            "Data de estreia (mais recente)",
            "Data de estreia (mais antiga)"
    };

    public ListPanel(ListType listType, UserData userData,
                     DataService dataService, Runnable onDataChanged) {
        this.listType      = listType;
        this.userData      = userData;
        this.dataService   = dataService;
        this.onDataChanged = onDataChanged;
        buildUi();
        refresh();
    }

    private void buildUi() {
        setLayout(new BorderLayout(0, 8));
        setBorder(new EmptyBorder(12, 12, 12, 12));

        // Toolbar
        JPanel toolbar = new JPanel(new BorderLayout(8, 0));
        toolbar.setOpaque(false);

        JPanel leftBar = new JPanel(new FlowLayout(FlowLayout.LEFT, 6, 0));
        leftBar.setOpaque(false);
        leftBar.add(new JLabel("Ordenar por:"));
        sortCombo = new JComboBox<>(SORT_OPTIONS);
        sortCombo.addActionListener(e -> refresh());
        leftBar.add(sortCombo);
        toolbar.add(leftBar, BorderLayout.WEST);

        countLabel = new JLabel();
        countLabel.setFont(countLabel.getFont().deriveFont(12f));
        countLabel.setForeground(Color.DARK_GRAY);
        toolbar.add(countLabel, BorderLayout.EAST);

        add(toolbar, BorderLayout.NORTH);

        // Tabela
        tableModel = new ShowTableModel();
        table = SearchPanel.buildTable(tableModel);
        table.addMouseListener(new MouseAdapter() {
            @Override public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) openDetails();
            }
        });

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createTitledBorder(getListName()));
        add(scrollPane, BorderLayout.CENTER);

        // Botões
        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 6, 0));
        btnPanel.setOpaque(false);

        JButton detailsBtn = new JButton("📋 Detalhes");
        detailsBtn.addActionListener(e -> openDetails());

        JButton removeBtn = new JButton("🗑 Remover");
        removeBtn.addActionListener(e -> removeSelected());

        btnPanel.add(detailsBtn);
        btnPanel.add(removeBtn);
        add(btnPanel, BorderLayout.SOUTH);
    }

    public void refresh() {
        List<TvShow> source = getSourceList();
        List<TvShow> sorted = sortShows(source);
        tableModel.setShows(sorted);
        countLabel.setText(sorted.size() + " série(s)  ");
    }

    private List<TvShow> getSourceList() {
        return switch (listType) {
            case FAVORITES -> new ArrayList<>(userData.getFavorites());
            case WATCHED   -> new ArrayList<>(userData.getWatched());
            case WATCHLIST -> new ArrayList<>(userData.getWatchlist());
        };
    }

    private List<TvShow> sortShows(List<TvShow> shows) {
        int option = sortCombo.getSelectedIndex();
        return switch (option) {
            case 1 -> shows.stream()
                    .sorted(Comparator.comparing(s -> s.getName() != null ? s.getName() : ""))
                    .collect(Collectors.toList());
            case 2 -> shows.stream()
                    .sorted(Comparator.comparing((TvShow s) -> s.getName() != null ? s.getName() : "").reversed())
                    .collect(Collectors.toList());
            case 3 -> shows.stream()
                    .sorted(Comparator.comparingDouble(TvShow::getRating).reversed())
                    .collect(Collectors.toList());
            case 4 -> shows.stream()
                    .sorted(Comparator.comparingDouble(TvShow::getRating))
                    .collect(Collectors.toList());
            case 5 -> shows.stream()
                    .sorted(Comparator.comparing(s -> s.getStatus() != null ? s.getStatus() : ""))
                    .collect(Collectors.toList());
            case 6 -> shows.stream()
                    .sorted(Comparator.comparing(
                            (TvShow s) -> s.getPremiered() != null ? s.getPremiered() : "").reversed())
                    .collect(Collectors.toList());
            case 7 -> shows.stream()
                    .sorted(Comparator.comparing(s -> s.getPremiered() != null ? s.getPremiered() : ""))
                    .collect(Collectors.toList());
            default -> shows; // Ordem original
        };
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
            refresh();
            if (onDataChanged != null) onDataChanged.run();
        }).setVisible(true);
    }

    private void removeSelected() {
        TvShow show = getSelectedShow();
        if (show == null) {
            JOptionPane.showMessageDialog(this, "Selecione uma série para remover.",
                    "Nenhuma seleção", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        int confirm = JOptionPane.showConfirmDialog(this,
                "Remover \"" + show.getName() + "\" de " + getListName() + "?",
                "Confirmar remoção", JOptionPane.YES_NO_OPTION);
        if (confirm != JOptionPane.YES_OPTION) return;

        switch (listType) {
            case FAVORITES -> userData.removeFavorite(show);
            case WATCHED   -> userData.removeWatched(show);
            case WATCHLIST -> userData.removeFromWatchlist(show);
        }
        try { dataService.save(userData); }
        catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao salvar: " + ex.getMessage(),
                    "Erro", JOptionPane.ERROR_MESSAGE);
        }
        refresh();
        if (onDataChanged != null) onDataChanged.run();
    }

    private TvShow getSelectedShow() {
        int viewRow = table.getSelectedRow();
        if (viewRow < 0) return null;
        int modelRow = table.convertRowIndexToModel(viewRow);
        return tableModel.getShowAt(modelRow);
    }

    private String getListName() {
        return switch (listType) {
            case FAVORITES -> "Favoritos";
            case WATCHED   -> "Já Assistidas";
            case WATCHLIST -> "Quero Assistir";
        };
    }
}
