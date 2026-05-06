public class Carro extends Veiculo {

    private boolean arCondicionado;

    public Carro(String placa, double valorDiaria, boolean arCondicionado) {
        setPlaca(placa);
        setValorDiaria(valorDiaria);
        this.arCondicionado = arCondicionado;
    }

    public boolean isArCondicionado() {
        return arCondicionado;
    }
  
@Override
    public void exibirInfo() {
        String ar = arCondicionado ? "Sim" : "Nao";
        System.out.println("Carro | Placa: " + getPlaca()
                + " | Diaria: R$ " + getValorDiaria()
                + " | Ar-condicionado: " + ar);
    }
}
