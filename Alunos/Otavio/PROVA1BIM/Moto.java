public class Moto extends Veiculo{
    private int cilindrada;

    public int getCilindrada() {
        return cilindrada;
    }

    public void setCilindrada(int cilindrada) {
        this.cilindrada = cilindrada;
    }

    public Moto(String placa, double valorDiaria, int cilindrada) {
        super(placa, valorDiaria);
        setCilindrada(cilindrada);
    }

    
    @Override
    public String mostrarInformacoes() {
        return "Placa: " + this.getPlaca() + ".\nValor diária: " + this.getValorDiaria()
                            + ".\nQuantidade cilindradas: " + this.getCilindrada();
    }
}
