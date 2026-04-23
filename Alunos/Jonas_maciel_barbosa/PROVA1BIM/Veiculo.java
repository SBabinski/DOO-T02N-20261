public class Veiculo {
 
    private String placa;
    private double valorDiaria;
 
    public Veiculo() {
        this.placa = "";
        this.valorDiaria = 0;
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
 
    public void exibirInfo() {
        System.out.println("Veiculo | Placa: " + placa + " | Diaria: R$ " + valorDiaria);
    }
}