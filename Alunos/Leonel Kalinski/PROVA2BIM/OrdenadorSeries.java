package util;

import model.Serie;

import java.util.Comparator;
import java.util.List;

public class OrdenadorSeries {

    public static List<Serie> porNome(List<Serie> lista) {
        return lista.stream()
                .sorted(Comparator.comparing(Serie::getNome))
                .toList();
    }

    public static List<Serie> porNota(List<Serie> lista) {
        return lista.stream()
                .sorted(
                        Comparator.comparing(Serie::getNota)
                                .reversed()
                )
                .toList();
    }

    public static List<Serie> porEstado(List<Serie> lista) {
        return lista.stream()
                .sorted(Comparator.comparing(Serie::getEstado))
                .toList();
    }

    public static List<Serie> porEstreia(List<Serie> lista) {
        return lista.stream()
                .sorted(Comparator.comparing(Serie::getEstreia))
                .toList();
    }
}
