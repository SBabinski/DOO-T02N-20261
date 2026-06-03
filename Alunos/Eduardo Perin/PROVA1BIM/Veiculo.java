public class Veiculo {
    private String placa;
    private double valorDiaria;
    
    public void exibirInfo(){
        System.out.println("PLACA: "+placa);
        System.out.println("VALOR DA DIARIA: "+valorDiaria);
        System.out.println();
    }
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
    
}
