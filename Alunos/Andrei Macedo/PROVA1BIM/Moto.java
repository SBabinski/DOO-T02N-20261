public class Moto extends Veiculo{

    private String cilindrada;

    public Moto(String placa, double valorDiaria, String cilindrada) {
        super(placa, valorDiaria);
        this.cilindrada = cilindrada;
    }

    public String getCilindrada() {
        return cilindrada;
    }

    public void setCilindrada(String cilindrada) {
        this.cilindrada = cilindrada;
    }

    @Override
    public void exibirVeiculos() {
        // TODO Auto-generated method stub
        super.exibirVeiculos();
        System.out.println("Informação das cilindrada: " + getCilindrada());
    }

}
