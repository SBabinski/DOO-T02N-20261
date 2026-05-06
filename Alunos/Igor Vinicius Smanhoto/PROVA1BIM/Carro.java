package objetos;

public class Carro extends Veiculo {

    private boolean arCondicionado;

    public Carro(String placa, double valorDiaria, boolean arCondicionado) {
        super(placa, valorDiaria);
        this.arCondicionado = arCondicionado;
    }

    @Override
    public String getDescricao() {
        return "Carro | Placa: " + placa + 
               " | Ar: " + (arCondicionado ? "Sim" : "Não");
    }
}