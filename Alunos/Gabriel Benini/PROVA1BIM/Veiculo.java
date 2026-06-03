public class Veiculo {

    private String placa;
    private int valorDiaria;

    public Veiculo() {

    }

    public Veiculo(String placa, int valorDiaria) {

        this.placa = placa;
        this.valorDiaria = valorDiaria;

    }

    public String getPlaca() {
        return placa;
    }

    public int getValorDiaria() {
        return valorDiaria;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public void setValorDiaria(int valorDiaria) {
        this.valorDiaria = valorDiaria;
    }
}
