package modelo;

public enum CriterioOrdenacao {
    NOME("Nome (A-Z)"),
    NOTA("Nota"),
    ESTADO("Estado"),
    DATA_ESTREIA("Data de estreia");

    private final String rotulo;

    CriterioOrdenacao(String rotulo) { this.rotulo = rotulo; }

    @Override
    public String toString() { return rotulo; }
}
