package tvtracker.service;

import java.util.Comparator;
import tvtracker.model.Show;


public enum SortCriteria {

    NAME("Nome (A-Z)") {
        @Override
        public Comparator<Show> comparator() {
            return Comparator.comparing(
                    (Show s) -> s.getName() == null ? "" : s.getName().toLowerCase());
        }
    },
    RATING("Nota geral (maior primeiro)") {
        @Override
        public Comparator<Show> comparator() {
            return Comparator.comparing(
                    (Show s) -> s.getRating() == null ? -1.0 : s.getRating(),
                    Comparator.reverseOrder());
        }
    },
    STATUS("Estado da série") {
        @Override
        public Comparator<Show> comparator() {
            return Comparator.comparing((Show s) -> s.getStatus() == null ? "" : s.getStatus());
        }
    },
    PREMIERE_DATE("Data de estreia (mais antiga primeiro)") {
        @Override
        public Comparator<Show> comparator() {
            return Comparator.comparing(
                    (Show s) -> s.getPremiered() == null ? "9999-99-99" : s.getPremiered());
        }
    };

    private final String label;

    SortCriteria(String label) {
        this.label = label;
    }

    public abstract Comparator<Show> comparator();

    @Override
    public String toString() {
        return label;
    }
}
