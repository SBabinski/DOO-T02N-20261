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
    public void apresentarInformacoes() {
        System.out.println("Tipo: Moto");
        System.out.println("Placa: " + getPlaca());
        System.out.printf("Valor da diaria: R$ %.2f%n", getValorDiaria());
        System.out.println("Cilindrada: " + cilindrada + " cc");
    }
}