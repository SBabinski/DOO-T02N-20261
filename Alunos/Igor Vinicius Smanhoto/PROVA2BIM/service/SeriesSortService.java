package com.tvtracker.service;

import com.tvtracker.model.Series;
import com.tvtracker.model.SortOption;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Serviço de ordenação para listas de séries.
 * Centraliza a lógica de comparação para manter as outras classes limpas.
 */
public class SeriesSortService {

    // Ordem de prioridade para o campo "status" (Running primeiro, Ended por último)
    private static final Map<String, Integer> STATUS_ORDER = new HashMap<>();

    static {
        STATUS_ORDER.put("Running", 1);
        STATUS_ORDER.put("To Be Determined", 2);
        STATUS_ORDER.put("In Development", 3);
        STATUS_ORDER.put("Ended", 4);
    }

    /**
     * Retorna uma nova lista ordenada de acordo com a opção escolhida.
     * Não modifica a lista original.
     */
    public List<Series> sort(List<Series> original, SortOption option) {
        List<Series> sorted = new ArrayList<>(original);
        Comparator<Series> comparator;

        if (option == null) option = SortOption.ALPHABETICAL;

        switch (option) {
            case RATING:        comparator = buildRatingComparator();       break;
            case STATUS:        comparator = buildStatusComparator();       break;
            case PREMIERE_DATE: comparator = buildPremiereDateComparator(); break;
            default:            comparator = buildAlphabeticalComparator(); break;
        }

        sorted.sort(comparator);
        return sorted;
    }

    // Ordena pelo nome em ordem crescente, ignorando maiúsculas/minúsculas
    private Comparator<Series> buildAlphabeticalComparator() {
        return new Comparator<Series>() {
            @Override
            public int compare(Series a, Series b) {
                return a.getName().compareToIgnoreCase(b.getName());
            }
        };
    }

    // Ordena pela nota da maior para a menor (séries sem nota ficam por último)
    private Comparator<Series> buildRatingComparator() {
        return new Comparator<Series>() {
            @Override
            public int compare(Series a, Series b) {
                return Double.compare(b.getRating(), a.getRating()); // decrescente
            }
        };
    }

    // Ordena pelo estado: em exibição, a determinar, em dev, encerradas
    private Comparator<Series> buildStatusComparator() {
        return new Comparator<Series>() {
            @Override
            public int compare(Series a, Series b) {
                int orderA = STATUS_ORDER.containsKey(a.getStatus()) ? STATUS_ORDER.get(a.getStatus()) : 99;
                int orderB = STATUS_ORDER.containsKey(b.getStatus()) ? STATUS_ORDER.get(b.getStatus()) : 99;
                return Integer.compare(orderA, orderB);
            }
        };
    }

    // Ordena pela data de estreia (mais antigas primeiro; sem data vão para o final)
    private Comparator<Series> buildPremiereDateComparator() {
        return new Comparator<Series>() {
            @Override
            public int compare(Series a, Series b) {
                String dateA = a.getPremiered() != null ? a.getPremiered() : "9999-99-99";
                String dateB = b.getPremiered() != null ? b.getPremiered() : "9999-99-99";
                return dateA.compareTo(dateB);
            }
        };
    }
}
