package tvtracker.model;

public enum ListType {
    FAVORITES("Favoritos"),
    WATCHED("Já assistidas"),
    TO_WATCH("Quero assistir");

    private final String label;

    ListType(String label) {
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
