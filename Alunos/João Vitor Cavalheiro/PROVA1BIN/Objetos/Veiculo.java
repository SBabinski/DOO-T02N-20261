package Objetos;

import java.util.ArrayList;
import java.util.List;

public abstract class Veiculo {
    String placa;
    double valorDiario;
    
    public Veiculo(String placa, double valorDiario) {
        this.placa = placa;
        this.valorDiario = valorDiario;
    }

       List <Veiculo> veiculos = new ArrayList<>();

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public double getValorDiario() {
        return valorDiario;
    }

    public void setValorDiario(double valorDiario) {
        this.valorDiario = valorDiario;
    }

    public void apresentarVeiculo(){
        System.out.println("==========Veículo==========");
        System.out.println("Placa: "+getPlaca());
        System.out.println("Valor Diário: R$"+getValorDiario());
    }

    public void AddVeiculo(Veiculo veiculo){
        veiculos.add(veiculo);
    }

    public abstract double getValorDiaria();

}
