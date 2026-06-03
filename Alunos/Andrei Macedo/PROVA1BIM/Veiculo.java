public class Veiculo {

    private String placa;
    private double valorDiaria;

    public Veiculo(String placa, double valorDiaria) {
        this.placa = placa;
        this.valorDiaria = valorDiaria;
    }

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

    public void exibirVeiculos() {
        System.out.println("Número da placa: " + getPlaca());
        System.out.printf("Valor da diária: %.2f\n", getValorDiaria());
    }
    
}
