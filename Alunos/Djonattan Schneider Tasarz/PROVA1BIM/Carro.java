
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
    public void exibirInformacoes() {
        System.out.println("Carro - Placa: " + placa +
                ", Valor da diária: R$" + valorDiaria +
                ", Ar-condicionado: " + (arCondicionado ? "Sim" : "Não"));
    }
}