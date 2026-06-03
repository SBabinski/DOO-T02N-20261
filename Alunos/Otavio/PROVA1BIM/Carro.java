public class Carro extends Veiculo{
    private boolean possuiArCondicionado;

    public boolean getPossuiArCondicionado() {
        return possuiArCondicionado;
    }

    public void setPossuiArCondicionado(boolean possuiArCondicionado) {
        this.possuiArCondicionado = possuiArCondicionado;
    }

    public Carro(String placa, double valorDiaria, boolean possuiArCondicionado) {
        super(placa, valorDiaria);
        setPossuiArCondicionado(possuiArCondicionado);
    }

    @Override
    public String mostrarInformacoes() {
        String respostaPossuiArCondicionado = this.getPossuiArCondicionado() ? "Sim" : "Não";

        return "Placa: " + this.getPlaca() + "\nValor diária:" + this.getValorDiaria()
                            + "\nPossui ar condicionado: " + respostaPossuiArCondicionado;
    }
}
