public class Moto extends Veiculo {
 
    private int cilindrada;
 
    public Moto(String placa, double valorDiaria, int cilindrada) {
        setPlaca(placa);
        setValorDiaria(valorDiaria);
        this.cilindrada = cilindrada;
    }
 
    public int getCilindrada() {
        return cilindrada;
    }
 
    @Override
    public void exibirInfo() {
        System.out.println("Moto | Placa: " + getPlaca()
                + " | Diaria: R$ " + getValorDiaria()
                + " | Cilindrada: " + cilindrada + "cc");
    }
}