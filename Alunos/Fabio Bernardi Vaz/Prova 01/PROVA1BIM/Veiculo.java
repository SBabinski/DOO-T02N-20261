package fag;

public abstract class Veiculo {
    protected String placa;
    protected double valorDiaria;

    public Veiculo(String placa, double valorDiaria) {
        this.placa = placa;
        this.valorDiaria = valorDiaria;
    }

    public abstract String getTipo();
    public abstract void apresentarse();

    public String getPlaca() { return placa; }
    public double getValorDiaria() { return valorDiaria; }
}