package fag;

public class Carro extends Veiculo {
    private boolean temArCondicionado;

    public Carro(String placa, double valorDiaria, boolean temArCondicionado) {
        super(placa, valorDiaria);
        this.temArCondicionado = temArCondicionado;
    }

    @Override
    public void apresentarse() {
        System.out.println("CARRO");
        System.out.println("Placa: " + placa);
        System.out.printf("Valor da Diária: R$ %.2f%n", valorDiaria);
        if(temArCondicionado == true){
            System.out.println("Sim");
        } else{
            System.out.println("Não");
        }
    }

    @Override
    public String getTipo(){
        return "Carro";
    }

    public boolean isTemArCondicionado() { 
        return temArCondicionado; 
    }
}