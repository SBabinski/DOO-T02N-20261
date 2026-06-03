package PROVA1BIM;

public class Carro extends Veiculo {

    private boolean temArCond;

    public Carro(String placa, Boolean temArCond){

        super(placa, 30);
        
        this.temArCond = temArCond;
    }

    public boolean TemArCond() {
        return temArCond;
    }

    public void setTemArCond(boolean temArCond) {
        this.temArCond = temArCond;
    }
}
