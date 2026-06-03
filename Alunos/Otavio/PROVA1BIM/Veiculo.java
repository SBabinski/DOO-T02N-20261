public class Veiculo {
    private String placa;
    private double valorDiaria;

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public double getValorDiaria() {
        return valorDiaria;
    }

    public void setValorDiaria(double valorDiaria) {
        this.valorDiaria = valorDiaria;
    }

    public Veiculo(String placa, double valorDiaria) {
        setPlaca(placa);
        setValorDiaria(valorDiaria);
    }

    public String mostrarInformacoes() {
        System.out.println("O veiculo tem a placa: " + this.getPlaca() + "\nE possui um valor de diária igual a:" + this.getValorDiaria());
        return "O veiculo tem a placa: " + this.getPlaca() + "\nE possui um valor de diária igual a:" + this.getValorDiaria();
    }
}