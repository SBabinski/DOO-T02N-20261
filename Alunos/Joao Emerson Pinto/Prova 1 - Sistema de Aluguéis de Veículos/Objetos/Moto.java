package Objetos;

public class Moto extends Veiculo {
    
    private int cilindradas;

    public Moto() {

    }

    public Moto(String tipo_veiculo, String placa, double valor_diaria, int cilindradas) {
        super(tipo_veiculo, placa, valor_diaria);
        setCilindradas(cilindradas);
    }

    public void setCilindradas(int cilindradas) {

        if (cilindradas <= 0) {
            System.out.println("Valor invalido");
            System.out.printf("Informe a cilindrada do veiculo: ");
            cilindradas = scan.nextInt();
        }

        this.cilindradas = cilindradas;
    }

    public int getCilindradas() {
        return cilindradas;
    }

    public void infoMoto() {
        System.out.println("=================================");
        System.out.printf("Tipo de veiculo: %s \n", getTipoVeiculo());
        System.out.printf("Placa: %s \n", getPlaca());
        System.out.printf("Valor diaria R$: %.2f \n", getValorDiaria());
        System.out.printf("Cilindradas: %d \n", cilindradas); // CORREÇÃO
    }

}