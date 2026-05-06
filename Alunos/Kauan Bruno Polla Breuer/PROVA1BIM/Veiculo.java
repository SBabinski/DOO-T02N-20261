public abstract class Veiculo {
    private String placa;
    private double valorDiaria;

    public Veiculo(String placa, double valorDiaria) {
        this.placa = placa;
        this.valorDiaria = valorDiaria;
    }

    public String getPlaca() {
        return placa;
    }

    public double getValorDiaria() {
        return valorDiaria;
    }

    public abstract void apresentarInformacoes();
}