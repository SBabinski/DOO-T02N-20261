public class Carro extends Veiculos {

    private boolean arCondicionado;

    public Carro(String placa, double valorDiaria, boolean arCondicionado) {
        super(placa, valorDiaria);
        this.arCondicionado = arCondicionado;
    }

    @Override
    public void mostrar() {
        System.out.println("Carro | Placa: " + placa + 
        " | Diária: " + valorDiaria + 
        " | Ar: " + (arCondicionado ? "Sim" : "Não"));
    }
}