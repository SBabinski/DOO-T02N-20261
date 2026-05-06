public class Carros extends Veiculos {
    private boolean arCondicionado;

    public Carros(String placa, double valorDiaria, boolean arCondicionado) {
        super(placa, valorDiaria);
        this.arCondicionado = arCondicionado;
    }

    public boolean isArCondicionado() {
        return arCondicionado;
    }
}
