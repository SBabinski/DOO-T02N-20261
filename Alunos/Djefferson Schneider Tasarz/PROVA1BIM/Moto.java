public class Moto extends Veiculo {
    int cilindrada;

    Moto(String p, double d, int c) {
        super(p, d);
        cilindrada = c;
    }

    void mostrar() {
        System.out.println("Moto " + placa + " R$" + diaria + " " + cilindrada + "cc");
    }
}