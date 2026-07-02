package com.tvtracker.service;

import com.tvtracker.model.TvSeries;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Serviço responsável por gerenciar e ordenar listas de séries.
 */
public class SeriesListService {

    public enum SortOrder {
        NAME_ASC("Nome (A-Z)"),
        NAME_DESC("Nome (Z-A)"),
        RATING_DESC("Melhor Nota"),
        RATING_ASC("Pior Nota"),
        STATUS("Estado"),
        PREMIERE_DATE_ASC("Data de Estreia (Antiga)"),
        PREMIERE_DATE_DESC("Data de Estreia (Recente)");

        private final String label;

        SortOrder(String label) {
            this.label = label;
        }

        public String getLabel() {
            return label;
        }

        @Override
        public String toString() {
            return label;
        }
    }

    /**
     * Ordena uma lista de séries conforme o critério especificado.
     * Retorna uma nova lista sem modificar a original.
     */
    public List<TvSeries> sort(List<TvSeries> series, SortOrder order) {
        if (series == null) return new ArrayList<>();
        List<TvSeries> sorted = new ArrayList<>(series);

        Comparator<TvSeries> comparator = switch (order) {
            case NAME_ASC -> Comparator.comparing(
                    s -> s.getName() != null ? s.getName().toLowerCase() : "",
                    Comparator.naturalOrder()
            );
            case NAME_DESC -> Comparator.comparing(
                    s -> s.getName() != null ? s.getName().toLowerCase() : "",
                    Comparator.reverseOrder()
            );
            case RATING_DESC -> Comparator.comparingDouble(TvSeries::getRating).reversed();
            case RATING_ASC -> Comparator.comparingDouble(TvSeries::getRating);
            case STATUS -> Comparator.comparing(
                    s -> s.getStatus() != null ? s.getStatus().name() : "",
                    Comparator.naturalOrder()
            );
            case PREMIERE_DATE_ASC -> Comparator.comparing(
                    s -> s.getPremiereDate() != null ? s.getPremiereDate() : "",
                    Comparator.naturalOrder()
            );
            case PREMIERE_DATE_DESC -> Comparator.comparing(
                    s -> s.getPremiereDate() != null ? s.getPremiereDate() : "",
                    Comparator.reverseOrder()
            );
        };

        sorted.sort(comparator);
        return sorted;
    }
}
