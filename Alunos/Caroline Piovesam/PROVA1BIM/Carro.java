public class Carro extends Veiculo {

    private boolean arCondicionado;

    public Carro(String placa, double valorDiaria, boolean arCondicionado) {

        super(placa, valorDiaria);
        this.arCondicionado = arCondicionado;
    }

    public String getArCondicionado() {
        return this.arCondicionado;
    }

    @Override
    public void mostrarDados() {

        System.out.println("Placa: " + getPlaca() + " | " + "Valor Diária: R$ " +
                getValorDiaria() + " | " + "Possui ar condicionado:  "
                + (arCondicionado ? "Sim" : "Não"));

    }
}