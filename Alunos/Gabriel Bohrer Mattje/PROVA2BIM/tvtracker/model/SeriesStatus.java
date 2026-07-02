package com.tvtracker.model;

/**
 * Enum que representa o estado de uma série de TV.
 */
public enum SeriesStatus {

    RUNNING("Em Transmissão"),
    ENDED("Concluída"),
    CANCELED("Cancelada"),
    IN_DEVELOPMENT("Em Desenvolvimento"),
    UNKNOWN("Desconhecido");

    private final String label;

    SeriesStatus(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    /**
     * Converte a string da API TvMaze para o enum correspondente.
     */
    public static SeriesStatus fromApiString(String value) {
        if (value == null) return UNKNOWN;
        return switch (value.toLowerCase()) {
            case "running" -> RUNNING;
            case "ended" -> ENDED;
            case "to be determined" -> RUNNING;
            case "in development" -> IN_DEVELOPMENT;
            default -> {
                if (value.toLowerCase().contains("cancel")) yield CANCELED;
                yield UNKNOWN;
            }
        };
    }

    @Override
    public String toString() {
        return label;
    }
}
