public class Carro extends Veiculo {
    boolean arCondicionado;

    void exibir() {
        super.exibir();
        System.out.println("Ar condicionado: " + arCondicionado);
    }
}