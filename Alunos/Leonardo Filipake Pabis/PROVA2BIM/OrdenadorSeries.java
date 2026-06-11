package app.util;

import app.model.Serie;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;

public class OrdenadorSeries {

    public static void ordenarPorNome(
            List<Serie> lista) {

        lista.sort(
                Comparator.comparing(
                        Serie::getName,
                        String.CASE_INSENSITIVE_ORDER
                )
        );
    }

    public static void ordenarPorNota(
            List<Serie> lista) {

        lista.sort(
                Comparator.comparing(
                        Serie::getRating
                ).reversed()
        );
    }

    public static void ordenarPorStatus(
            List<Serie> lista) {

        lista.sort(
                Comparator.comparingInt(
                        OrdenadorSeries::prioridadeStatus
                )
        );
    }

    public static void ordenarPorEstreia(
            List<Serie> lista) {

        lista.sort(
                Comparator.comparing(
                        OrdenadorSeries::converterData,
                        Comparator.nullsLast(
                                Comparator.reverseOrder()
                        )
                )
        );
    }

    private static int prioridadeStatus(Serie serie) {

        return switch (serie.getStatus()) {
            case "Running" -> 1;
            case "Canceled", "Cancelled" -> 2;
            case "Ended" -> 3;
            default -> 4;
        };
    }

    private static LocalDate converterData(Serie serie) {

        try {
            if (serie.getPremiered() == null ||
                    serie.getPremiered().isBlank()) {
                return null;
            }

            return LocalDate.parse(
                    serie.getPremiered()
            );

        } catch (Exception e) {
            return null;
        }
    }
}
