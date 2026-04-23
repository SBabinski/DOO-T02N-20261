public abstract class Veiculos{
    protected String placa;
    protected double valorDiaria;

    public Veiculos(String placa, double valorDiaria){
        this.placa = placa;
        this.valorDiaria = valorDiaria;
    }

    public double getValorDiaria(){
        return valorDiaria;
    }

    public abstract void mostrar();

}