import java.util.Comparator;

public enum CriterioOrdenacao {

    NOME("Ordem alfabetica (A-Z)"),
    NOTA("Nota geral (maior primeiro)"),
    ESTADO("Estado da serie"),
    ESTREIA("Data de estreia");

    private final String descricao;

    CriterioOrdenacao(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    public Comparator<Serie> getComparador() {
        switch (this) {
            case NOTA:
                Comparator<Double> notaDesc =
                        Comparator.nullsLast(Comparator.<Double>reverseOrder());
                return Comparator.comparing(Serie::getNota, notaDesc);

            case ESTADO:
                return Comparator.comparingInt(s -> s.getEstado().getOrdem());

            case ESTREIA:
                Comparator<String> dataAsc =
                        Comparator.nullsLast(Comparator.<String>naturalOrder());
                return Comparator.comparing(Serie::getDataEstreia, dataAsc);

            case NOME:
            default:
                return Comparator.comparing(s -> s.getNome().toLowerCase());
        }
    }

    @Override
    public String toString() {
        return descricao;
    }
}