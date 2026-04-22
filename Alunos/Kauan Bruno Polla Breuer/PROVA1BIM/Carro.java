public class Carro extends Veiculo {
    private boolean arCondicionado;

    public Carro(String placa, double valorDiaria, boolean arCondicionado) {
        super(placa, valorDiaria);
        this.arCondicionado = arCondicionado;
    }

    public boolean isArCondicionado() {
        return arCondicionado;
    }

    @Override
    public void apresentarInformacoes() {
        System.out.println("Tipo: Carro");
        System.out.println("Placa: " + getPlaca());
        System.out.printf("Valor da diaria: R$ %.2f%n", getValorDiaria());
        System.out.println("Ar-condicionado: " + (arCondicionado ? "Sim" : "Nao"));
    }
}