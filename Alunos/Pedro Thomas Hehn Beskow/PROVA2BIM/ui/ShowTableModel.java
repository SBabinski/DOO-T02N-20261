package tvtracker.ui;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import tvtracker.model.TvShow;


public class ShowTableModel extends AbstractTableModel {

    private static final String[] COLUMNS = {
            "Nome", "Idioma", "Gêneros", "Nota", "Estado", "Estreia", "Emissora"
    };

    private List<TvShow> shows;

    public ShowTableModel() {
        this.shows = new ArrayList<>();
    }

    public ShowTableModel(List<TvShow> shows) {
        this.shows = new ArrayList<>(shows);
    }

    public void setShows(List<TvShow> shows) {
        this.shows = new ArrayList<>(shows);
        fireTableDataChanged();
    }

    public TvShow getShowAt(int row) {
        if (row < 0 || row >= shows.size()) return null;
        return shows.get(row);
    }

    public List<TvShow> getShows() {
        return new ArrayList<>(shows);
    }

    @Override public int getRowCount()    { return shows.size(); }
    @Override public int getColumnCount() { return COLUMNS.length; }
    @Override public String getColumnName(int col) { return COLUMNS[col]; }

    @Override
    public Object getValueAt(int row, int col) {
        if (row < 0 || row >= shows.size()) return "";
        TvShow s = shows.get(row);
        return switch (col) {
            case 0 -> s.getName()      != null ? s.getName()      : "";
            case 1 -> s.getLanguage()  != null ? s.getLanguage()  : "N/A";
            case 2 -> s.getGenresDisplay();
            case 3 -> s.getRatingDisplay();
            case 4 -> s.getStatusDisplay();
            case 5 -> s.getPremiered() != null ? s.getPremiered() : "N/A";
            case 6 -> s.getNetwork()   != null ? s.getNetwork()   : "N/A";
            default -> "";
        };
    }

    @Override
    public Class<?> getColumnClass(int col) {
        return String.class;
    }

    @Override
    public boolean isCellEditable(int row, int col) {
        return false;
    }
}
