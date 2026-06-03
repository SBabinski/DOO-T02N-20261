package Objetos;

public class Veiculo {

    private String placa;
    private double valorDiaria;

    public Veiculo(){
    }

    public Veiculo(String placa, double valorDiaria){

    }

    public String getPlaca(){
        return placa;
    }

    public double getValorDiaria(){
        return valorDiaria;
    }

    public void setPlaca(String placa){
        if(placa != null && !placa.isBlank()){
            this.placa = placa;
        }
    }

    public void setValorDiaria(double valorDiaria){
        if(valorDiaria > 0){
            this.valorDiaria = valorDiaria;
        }
    }

    public void apresentarSe(){
        System.out.printf("Veículo - Placa: %s | Valor da Diária: %.2f \n", placa, valorDiaria);
    }
}
