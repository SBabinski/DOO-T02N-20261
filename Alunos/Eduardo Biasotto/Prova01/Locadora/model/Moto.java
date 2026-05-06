package Locadora.model;

public class Moto extends Veiculo {
    private int cilindrada;

    public Moto(String placa, double valorDiaria, int cilindrada) {
        super(placa, valorDiaria);
        this.cilindrada = cilindrada;
    }

    @Override
    public void exibirInformacoes() {
        System.err.println("\nTipo: Moto");
        System.out.println("Placa: " + getPlaca());
        System.out.printf("Valor da Diaria: R$ %.2f%n", getValorDiaria());
        System.out.println("Cilindrada: " + cilindrada + " cc");
    }

    public int getCilindrada() {
        return cilindrada;
    }

    public void setCilindrada(int cilindrada) {
        this.cilindrada = cilindrada;
    }

    
}
