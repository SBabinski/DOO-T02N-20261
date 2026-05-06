public class Carro extends Veiculo {

    boolean arCondicionado;

    public Carro(String nome, String placa, double valorDiaria, boolean arCondicionado) {
        super(nome, placa, valorDiaria);
        this.arCondicionado = arCondicionado;
    }

    void mostrarDados() {
        super.mostrarDados();
        System.out.println("Ar condicionado: " + arCondicionado);
    }
}