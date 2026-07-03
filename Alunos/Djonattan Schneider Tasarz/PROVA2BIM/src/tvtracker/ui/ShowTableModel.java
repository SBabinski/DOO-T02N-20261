package tvtracker.ui;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import tvtracker.model.Show;


public class ShowTableModel extends AbstractTableModel {

    private static final String[] COLUMNS = {
            "Nome", "Idioma", "Gêneros", "Nota", "Estado", "Estreia", "Término", "Emissora"
    };

    private List<Show> shows = new ArrayList<>();

    public void setShows(List<Show> shows) {
        this.shows = shows != null ? shows : new ArrayList<>();
        fireTableDataChanged();
    }

    public Show getShowAt(int row) {
        if (row < 0 || row >= shows.size()) {
            return null;
        }
        return shows.get(row);
    }

    @Override
    public int getRowCount() {
        return shows.size();
    }

    @Override
    public int getColumnCount() {
        return COLUMNS.length;
    }

    @Override
    public String getColumnName(int column) {
        return COLUMNS[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Show s = shows.get(rowIndex);
        switch (columnIndex) {
            case 0: return s.getName() != null ? s.getName() : "—";
            case 1: return s.getLanguage() != null ? s.getLanguage() : "—";
            case 2: return s.getGenresAsText();
            case 3: return s.getRatingAsText();
            case 4: return s.getStatus() != null ? s.getStatus() : "—";
            case 5: return s.getPremiered() != null ? s.getPremiered() : "—";
            case 6: return s.getEnded() != null ? s.getEnded() : "—";
            case 7: return s.getNetwork() != null ? s.getNetwork() : "—";
            default: return "";
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }
}
