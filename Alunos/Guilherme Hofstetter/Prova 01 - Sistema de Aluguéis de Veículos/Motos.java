public class Motos extends Veiculos{
    private int cilindradas;

    public Motos(String placa, double valorDiaria, int cilindradas) {
        super(placa, valorDiaria);
        this.cilindradas = cilindradas;
    }

    public int getCilindradas() {
        return cilindradas;
    }

}
