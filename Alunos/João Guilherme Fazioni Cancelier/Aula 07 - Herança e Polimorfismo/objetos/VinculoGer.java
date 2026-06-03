package objetos;

public class VinculoGer {
    private int idGer;
    private int idLo;

    public VinculoGer() {
    }

    public VinculoGer(int idGer, int idLo) {
        this.idGer = idGer;
        this.idLo = idLo;
    }

    public int getIdGer() {
        return idGer;
    }

    public void setIdGer(int idGer) {
        this.idGer = idGer;
    }

    public int getIdLo() {
        return idLo;
    }

    public void setIdLo(int idLo) {
        this.idLo = idLo;
    }
}
