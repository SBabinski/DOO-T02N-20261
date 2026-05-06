public class Moto extends Veiculo {
    
    int cilindrada;

    public Moto (String placa, double valorDiaria, int cilindrada) {
        super(placa, valorDiaria);
        this.cilindrada = cilindrada;

    }
    public void exibirDados() {
        System.out.println("Moto com Placa: " + placa);
        System.out.println("Diária: " + valorDiaria);
        System.out.println("Cilindrada: " + cilindrada);
    }
}
