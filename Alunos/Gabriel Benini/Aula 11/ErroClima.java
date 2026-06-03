public class ErroClima extends Exception {

    private final int codigoHttp;

    public ErroClima(String mensagem, int codigoHttp) {
        super(mensagem);
        this.codigoHttp = codigoHttp;
    }

    public int getCodigoHttp() {
        return codigoHttp;
    }
}