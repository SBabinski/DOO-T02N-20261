package objetos;

public class Moto extends Veiculo {

    private int cilindrada;

    public Moto(String placa, double valorDiaria, int cilindrada) {
        super(placa, valorDiaria);
        this.cilindrada = cilindrada;
    }

    @Override
    public String getDescricao() {
        return "Moto | Placa: " + placa + 
               " | Cilindrada: " + cilindrada;
    }
}