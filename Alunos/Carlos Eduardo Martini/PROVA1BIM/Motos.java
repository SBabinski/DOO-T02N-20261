public class Motos extends Veiculos{
    public int cilindrada;

    public Motos(String placa, double valorDiaria, int cilindrada){
        super(placa, valorDiaria);
        this.cilindrada = cilindrada;
    }

    public void exibirInformacoes(){
        System.out.println("placa: "+placa+" | valor da diaria: "+valorDiaria+" | cilindradas: "+cilindrada);
    }
}
