package PROVA1BIM;
public class Veiculo {

    private String placa;
    private double valorDiaria;

    public Veiculo(String placa, int valorDiaria){

        this.placa = placa;
        this.valorDiaria = valorDiaria;
    }

    public String getPlaca() {
        return placa;
    }

    public double getValorDiaria() {
        return valorDiaria;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public void setValorDiaria(double valorDiaria) {
        this.valorDiaria = valorDiaria;
    }
}
