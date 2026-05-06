package objetos;

public abstract class Veiculo {

    protected String placa;
    protected double valorDiaria;

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

    public abstract String getDescricao();
}