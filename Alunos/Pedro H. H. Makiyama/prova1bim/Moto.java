package prova1bim;

public class Moto extends Veiculo{

    private double cilindrada;

    public Moto(String placa, double cilindrada){

        super(placa, 20);

        this.cilindrada = cilindrada;
    }

    public double getCilindrada() {
        return cilindrada;
    }

    public void setCilindrada(double cilindrada) {
        this.cilindrada = cilindrada;
    }
}
