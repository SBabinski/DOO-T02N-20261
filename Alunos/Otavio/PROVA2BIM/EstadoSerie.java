public enum EstadoSerie {

    EM_TRANSMISSAO("Em transmissao", 0),
    EM_DESENVOLVIMENTO("Em desenvolvimento", 1),
    A_DEFINIR("A definir", 2),
    CONCLUIDA("Concluida", 3),
    DESCONHECIDO("Desconhecido", 4);

    private final String descricao;
    private final int ordem;

    EstadoSerie(String descricao, int ordem) {
        this.descricao = descricao;
        this.ordem = ordem;
    }

    public String getDescricao() {
        return descricao;
    }

    public int getOrdem() {
        return ordem;
    }

    public static EstadoSerie deStatusApi(String status) {
        if (status == null) {
            return DESCONHECIDO;
        }
        switch (status.trim().toLowerCase()) {
            case "running":          return EM_TRANSMISSAO;
            case "ended":            return CONCLUIDA;
            case "to be determined": return A_DEFINIR;
            case "in development":   return EM_DESENVOLVIMENTO;
            default:                 return DESCONHECIDO;
        }
    }

    @Override
    public String toString() {
        return descricao;
    }
}