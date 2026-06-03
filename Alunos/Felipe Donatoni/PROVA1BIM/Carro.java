public class Carro extends Veiculo {
    
    boolean arCondicionado;

    public Carro (String placa, double valorDiaria, boolean arCondicionado){
        super(placa, valorDiaria);
        this.arCondicionado = arCondicionado;
    }

    public void exibirDados() {
        System.out.println("Carro com Placa: " + placa);
        System.out.println("Diária: " + valorDiaria);
        if (arCondicionado) {
        System.out.println("Arcondicionado: Sim");
        } else {
        System.out.println("Arcondicionado: Não");
        }
    }
}
