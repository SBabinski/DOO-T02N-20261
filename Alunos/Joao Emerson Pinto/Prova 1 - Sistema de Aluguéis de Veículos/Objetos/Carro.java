package Objetos;

public class Carro extends Veiculo {

    private int possui_ar;

    public Carro() {

    }

    public Carro(String tipo_veiculo, String placa, double valor_diaria, int possui_ar) {
        super(tipo_veiculo, placa, valor_diaria);
        setPossuiAr(possui_ar);
    }

    public void setPossuiAr(int possui_ar) {

        if (possui_ar != 1 && possui_ar != 0) { // CORREÇÃO
            System.out.println("Valor invalido");
            System.out.println("O Veiculo possui ar-condicionado ?");
            System.out.println("( 1 ) Sim | ( 0 ) Nao");
            System.out.printf("Escolha: ");
            possui_ar = scan.nextInt();
        }

        this.possui_ar = possui_ar;
    }

    public int getPossuiAr() {
        return possui_ar;
    }

    public void infoCarro() {
        System.out.println("=================================");
        System.out.printf("Tipo de veiculo: %s \n", getTipoVeiculo());
        System.out.printf("Placa: %s \n", getPlaca());
        System.out.printf("Valor diaria R$: %.2f \n", getValorDiaria());
        if (possui_ar == 1) {
            System.out.printf("Possui ar-condicionado: Sim \n");
        } else {
            System.out.printf("Possui ar-condicionado: Nao \n");
        }
    }

}