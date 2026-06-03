package Objetos;

public class Moto extends Veiculo {
    int cilindradas;

    public Moto(String placa, double valorDiario, int cilindradas) {
        super(placa, valorDiario);
        this.cilindradas = cilindradas;
    }

    public int getCilindradas() {
        return cilindradas;
    }

    public void setCilindradas(int cilindradas) {
        this.cilindradas = cilindradas;
    }

    @Override
     public void apresentarVeiculo(){
        System.out.println("==========Veículo==========");
        System.out.println("Placa: "+getPlaca());
        System.out.println("Valor Diário: R$"+getValorDiario());
        System.out.println("Esta moto possui "+getCilindradas()+" cilindradas");
    }

    @Override
    public double getValorDiaria() {
        return valorDiario;
    }

}
