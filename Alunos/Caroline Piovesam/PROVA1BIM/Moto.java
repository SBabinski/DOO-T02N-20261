public class Moto extends Veiculo {

    private int cilindrada;

    public Moto(String placa, double valorDiaria, int cilindrada) {

        super(placa, valorDiaria);
        this.cilindrada = cilindrada;
    }

    public int getCilindrada() {
        return this.cilindrada;
    }

    @Override
    public void mostrarDados() {

        System.out.println("Placa: " + getPlaca() + " | " + "Valor Diária: R$ " +
                getValorDiaria() + " | " + "Cilindrada da moto:  "
                + getCilindrada());

    }
}
