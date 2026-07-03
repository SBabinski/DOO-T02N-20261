import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class SerieTableModel extends AbstractTableModel {

    private static final String[] COLUNAS = {
            "Nome", "Idioma", "Generos", "Nota", "Estado", "Estreia", "Termino", "Emissora"
    };

    private List<Serie> series = new ArrayList<>();

    public void setSeries(List<Serie> series) {
        this.series = (series == null) ? new ArrayList<>() : new ArrayList<>(series);
        fireTableDataChanged();
    }

    public Serie getSerieEm(int linha) {
        return series.get(linha);
    }

    @Override
    public int getRowCount() {
        return series.size();
    }

    @Override
    public int getColumnCount() {
        return COLUNAS.length;
    }

    @Override
    public String getColumnName(int coluna) {
        return COLUNAS[coluna];
    }

    @Override
    public boolean isCellEditable(int linha, int coluna) {
        return false;
    }

    @Override
    public Object getValueAt(int linha, int coluna) {
        Serie s = series.get(linha);
        switch (coluna) {
            case 0:  return s.getNome();
            case 1:  return s.getIdioma();
            case 2:  return s.getGenerosTexto();
            case 3:  return s.getNotaTexto();
            case 4:  return s.getEstado().getDescricao();
            case 5:  return s.getEstreiaTexto();
            case 6:  return s.getTerminoTexto();
            case 7:  return s.getEmissora();
            default: return "";
        }
    }

    public static void ajustarLarguras(JTable tabela) {
        int[] larguras = {200, 90, 200, 55, 140, 100, 100, 140};
        int colunas = tabela.getColumnModel().getColumnCount();
        for (int i = 0; i < larguras.length && i < colunas; i++) {
            tabela.getColumnModel().getColumn(i).setPreferredWidth(larguras[i]);
        }
    }
}