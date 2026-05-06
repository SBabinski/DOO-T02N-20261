public abstract class Veiculo {

    private String placa;
    private double valorDiaria;

    public abstract void mostrarDados();

    public Veiculo(String placa, double valorDiaria) {
        this.placa = placa;
        this.valorDiaria = valorDiaria;
    }

    public String getPlaca() {
        return this.placa;
    }

    public double getValorDiaria() {
        return this.valorDiaria;
    }

}