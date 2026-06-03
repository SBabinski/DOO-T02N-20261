public class Carros extends Veiculos{
    public boolean arCondicionado;

    public Carros(String placa, double valorDiaria, boolean arCondicionado){
        super(placa, valorDiaria);
        this.arCondicionado = false;
    }

    public void exibirInformacoes(){
        System.out.println("placa: "+placa+" | valor da diaria: "+valorDiaria);
        if(arCondicionado == true){
            System.out.println(" | possui ar condicionado");
        }else{
            System.out.println(" | não possui ar condicionado");
        }
    }
}
