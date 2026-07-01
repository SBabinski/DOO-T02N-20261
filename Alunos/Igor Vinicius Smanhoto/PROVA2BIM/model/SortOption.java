package com.tvtracker.model;

/**
 * Enum com as opções de ordenação disponíveis para as listas de séries.
 */
public enum SortOption {
    ALPHABETICAL("Nome (A-Z)"),
    RATING("Nota"),
    STATUS("Estado"),
    PREMIERE_DATE("Data de estreia");

    private final String label;

    SortOption(String label) {
        this.label = label;
    }

    // Retorna o rótulo legível para exibição nos componentes Swing
    public String getLabel() {
        return label;
    }

    @Override
    public String toString() {
        return label;
    }
}
