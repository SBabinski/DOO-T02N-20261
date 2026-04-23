package Objetos;

public class Carro extends Veiculo{
    
    private boolean arCondicionado;

    public Carro(){
        super();
    }

    public Carro(String placa, double valorDiaria, boolean arCondicionado){
        super(placa, valorDiaria);

        setArCondionado(arCondicionado);
    }

    public boolean getArCondicionado(){
        return arCondicionado;
    }

    public void setArCondionado(boolean arCondicionado){
        this.arCondicionado = arCondicionado;
    }

    @Override
    public void apresentarSe(){
        if(arCondicionado = true){
            System.out.printf("Veículo - Placa: %s | Valor da Diária: %.2f | Possui Ar-Condicionado: Sim \n",
            getPlaca(), getValorDiaria());
        }else{
            System.out.printf("Veículo - Placa: %s | Valor da Diária: %.2f | Possui Ar-Condicionado: Não \n",
            getPlaca(), getValorDiaria());
        }
    }
}