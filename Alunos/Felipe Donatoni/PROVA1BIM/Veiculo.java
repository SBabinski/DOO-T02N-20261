public abstract class Veiculo {
    
    String placa;
    Double valorDiaria;

    public Veiculo (String placa, Double valorDiaria) {
        this.placa = placa;
        this.valorDiaria = valorDiaria;
    }
    public abstract void exibirDados();
}
