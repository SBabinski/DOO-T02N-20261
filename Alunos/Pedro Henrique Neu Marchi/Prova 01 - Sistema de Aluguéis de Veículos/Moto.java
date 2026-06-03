public class Moto extends Veiculo {
    int cilindradas;

    @Override
    public void exibirDados() {
        System.err.println("Placa: " + placa);
        System.out.println("Valor de locação: " + valorLocacao);
        System.out.println("Cilindradas: " + cilindradas);
    }
    
    
}
