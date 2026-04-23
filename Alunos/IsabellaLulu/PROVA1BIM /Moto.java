public class Moto extends Veiculos {

    private int cilindrada;

    public Moto(String placa, double valorDiaria, int cilindrada) {
        super(placa, valorDiaria);
        this.cilindrada = cilindrada;
    }

    @Override
    public void mostrar() {
        System.out.println("Moto | Placa: " + placa + 
        " | Diária: " + valorDiaria + 
        " | Cilindrada: " + cilindrada);
    }
}