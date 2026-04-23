package PROVA1BIM.Objetos;

public class Carro extends Veiculo{
    private boolean arCondicionado;

    public Carro(String placa, double valorDiaria, boolean arCondicionado) {
        super(placa, valorDiaria);
        this.arCondicionado = arCondicionado;
    }
    
    @Override
    public void exibirInfo() {
        System.out.println("Placa: "+ getPlaca()+ " | Valor Diária: R$"+ getValorDiaria()
        + " | Tem ar: "+ (arCondicionado ? "Sim" : "Não"));
    }
}
