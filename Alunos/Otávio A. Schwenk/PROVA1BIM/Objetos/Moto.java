package Objetos;

public class Moto extends Veiculo{
    
    private int cilindradas;

    public Moto(){
        super();
    }

    public Moto(String placa, double valorDiaria, int cilindradas){
        super(placa, valorDiaria);

        setCilindradas(cilindradas);
    }

    public int getCilindradas(){
        return cilindradas;
    }

    public void setCilindradas(int cilindradas){
        if(cilindradas > 0){
            this.cilindradas = cilindradas;
        }
    }

    @Override
    public void apresentarSe(){
        System.out.printf("Veículo - Placa: %s | Valor da Diária: %.2f | Cilindradas: %d \n", getPlaca(), getValorDiaria(), cilindradas);   
    }
}