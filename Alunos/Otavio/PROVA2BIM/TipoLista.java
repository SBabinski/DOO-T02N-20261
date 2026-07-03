public enum TipoLista {

    FAVORITOS("Favoritos"),
    ASSISTIDAS("Ja assistidas"),
    DESEJA_ASSISTIR("Quero assistir");

    private final String descricao;

    TipoLista(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    @Override
    public String toString() {
        return descricao;
    }
}