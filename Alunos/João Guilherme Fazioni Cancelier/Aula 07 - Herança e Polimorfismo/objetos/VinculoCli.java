package objetos;

public class VinculoCli {
    private int idCli;
    private int idLo;

    public VinculoCli() {
    }

    public VinculoCli(int idCli, int idLo) {
        this.idCli = idCli;
        this.idLo = idLo;
    }

    public int getIdCli() {
        return idCli;
    }

    public void setIdCli(int idCli) {
        this.idCli = idCli;
    }

    public int getIdLo() {
        return idLo;
    }

    public void setIdLo(int idLo) {
        this.idLo = idLo;
    }
}
