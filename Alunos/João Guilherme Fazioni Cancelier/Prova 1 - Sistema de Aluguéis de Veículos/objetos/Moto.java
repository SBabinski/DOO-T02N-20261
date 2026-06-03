package objetos;

public class Moto extends Veiculo {
    private int cilindrada;

    public Moto(String placa, double valDia, int cilindrada) {
        super(placa, valDia);
        this.cilindrada = cilindrada;
    }

    public int getCilindrada() {
        return cilindrada;
    }

    public void setCilindrada(int cilindrada) {
        this.cilindrada = cilindrada;
    }

    @Override
    public void exibir(){
        super.exibir();
        System.out.printf(" - Cilindragem: %d - Categoria: Moto.\n",cilindrada);
    }

}
