public class Moto extends Veiculo {
    private int cilindrada;

    public Moto(String placa, double valorDiaria, int cilindrada) {
        super(placa, valorDiaria);
        this.cilindrada = cilindrada;
    }

    public int getCilindrada() {
        return cilindrada;
    }

    @Override
    public void exibirInformacoes() {
        System.out.println("Moto - Placa: " + placa +
                ", Valor da diária: R$" + valorDiaria +
                ", Cilindrada: " + cilindrada + "cc");
    }
}