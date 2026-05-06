package objetos;

public class VinculoVen {
    private int idVen;
    private int idLo;

    public VinculoVen() {
    }

    public VinculoVen(int idVen, int idLo) {
        this.idVen = idVen;
        this.idLo = idLo;
    }

    public int getIdVen() {
        return idVen;
    }

    public void setIdVen(int idVen) {
        this.idVen = idVen;
    }

    public int getIdLo() {
        return idLo;
    }

    public void setIdLo(int idLo) {
        this.idLo = idLo;
    }
}
