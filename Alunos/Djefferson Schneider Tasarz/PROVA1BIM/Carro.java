public class Carro extends Veiculo {
    boolean ar;

    Carro(String p, double d, boolean a) {
        super(p, d);
        ar = a;
    }

    void mostrar() {
        System.out.println("Carro " + placa + " R$" + diaria + " ar:" + (ar?"sim":"nao"));
    }
}