package fag;

public class Moto extends Veiculo {
    private int cilindrada;

    public Moto(String placa, double valorDiaria, int cilindrada) {
        super(placa, valorDiaria);
        this.cilindrada = cilindrada;
    }

    @Override
    public void apresentarse() {
        System.out.println("MOTO");
        System.out.println("Placa: " + placa);
        System.out.printf("Valor da Diária: %.2f", valorDiaria);
        System.out.println("\nCilindrada: " + cilindrada + "cc");
    }

    @Override
    public String getTipo(){
        return "Moto";
    }

    public int getCilindrada() { 
        return cilindrada; 
    }
}