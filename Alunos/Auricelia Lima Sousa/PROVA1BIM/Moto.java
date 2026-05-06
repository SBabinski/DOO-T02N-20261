public class Moto extends Veiculo{
    public int cilindrada;
    @Override
    public void exibirInformacoes(){
        super.exibirInformacoes();
        System.out.println("| Cilindrada: " + cilindrada + "cc");
    }
}