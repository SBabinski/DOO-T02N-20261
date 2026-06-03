package PROVA1BIM.Objetos;

public class Moto extends Veiculo {

    private String cilindradas;

    public Moto(String placa, double valorDiaria, String cilindradas) {
        super(placa, valorDiaria);
        this.cilindradas = cilindradas;
    }

    @Override
    public void exibirInfo() {
        System.out.println("Placa: " + getPlaca() + " | Valor Diária: R$" + getValorDiaria()
        + " | Cilindradas: " + cilindradas + "cc");
    }
}