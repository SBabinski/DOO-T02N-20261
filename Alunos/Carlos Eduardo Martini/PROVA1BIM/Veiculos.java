public class Veiculos {
    public String placa; 
    public double valorDiaria;

    public Veiculos(String placa, double valorDiaria){
        this.placa = placa;
        this.valorDiaria = valorDiaria;
    }

    public void exibirInformacoes(){
        System.out.println("numero da placa: "+placa+" | valor da diaria: "+valorDiaria);
    }
}
