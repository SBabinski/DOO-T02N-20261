public abstract class Veiculo {
    private String placa;
    private double valorDiaria;
    private Cliente cliente;

    public Veiculo(){

    }

    public Veiculo(String placa, double diaria){
        setPlaca(placa);
        setValorDiaria(diaria);
        setCliente(null);
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public double getValorDiaria() {
        return valorDiaria;
    }

    public void setValorDiaria(double valorDiaria) {
        this.valorDiaria = valorDiaria;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public abstract String getTipo();
    
}
