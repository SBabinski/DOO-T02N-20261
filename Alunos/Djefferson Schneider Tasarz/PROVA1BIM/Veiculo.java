abstract class Veiculo {
    String placa;
    double diaria;

    Veiculo(String p, double d) {
        placa = p;
        diaria = d;
    }

    abstract void mostrar();
}