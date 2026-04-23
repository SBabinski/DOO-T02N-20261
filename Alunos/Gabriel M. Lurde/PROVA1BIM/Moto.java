public class Moto extends Veiculo {

    int cilindrada;

    public Moto(String nome, String placa, double valorDiaria, int cilindrada) {
        super(nome, placa, valorDiaria);
        this.cilindrada = cilindrada;
    }

    void mostrarDados() {
        super.mostrarDados();
        System.out.println("Cilindrada: " + cilindrada);
    }
}