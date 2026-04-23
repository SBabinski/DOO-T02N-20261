package Locadora.model;

public class Carro extends Veiculo {
    private boolean arCondicionado;

    public Carro(String placa, double valorDiaria, boolean arCondicionado) {
        super(placa, valorDiaria);
        this.arCondicionado = arCondicionado;
    }

    @Override
    public void exibirInformacoes() {
        System.err.println("\nTipo: Carro");
        System.out.println("Placa: " + getPlaca());
        System.out.printf("Valor da Diaria: R$ %.2f%n", getValorDiaria());
        System.out.println("Ar Condicionado: " + (arCondicionado ? "Sim" : "Não"));
    }

    public boolean isArCondicionado() {
        return arCondicionado;
    }

    public void setArCondicionado(boolean arCondicionado) {
        this.arCondicionado = arCondicionado;
    }

}
