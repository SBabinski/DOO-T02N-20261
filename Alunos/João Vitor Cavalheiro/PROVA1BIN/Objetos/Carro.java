package Objetos;

public class Carro extends Veiculo {

    boolean arcondicionado;

    public Carro(String placa, double valorDiario, boolean arcondicionado) {
        super(placa, valorDiario);
        this.arcondicionado = arcondicionado;
    }

    public boolean isArcondicionado() {
        return arcondicionado;
    }

    public void setArcondicionado(boolean arcondicionado) {
        this.arcondicionado = arcondicionado;
    }

    @Override
     public void apresentarVeiculo(){
        System.out.println("==========Veículo==========");
        System.out.println("Placa: "+getPlaca());
        System.out.println("Valor Diário: R$"+getValorDiario());
        System.out.println("Este carro possui ar condicionado: "+isArcondicionado());
    }

    @Override
    public double getValorDiaria() {
        return valorDiario;
    }
}
