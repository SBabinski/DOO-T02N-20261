public class Moto extends Veiculo {
    private String cilindradas;

    @Override
    public void exibirInfo(){
        System.out.println("PLACA: "+getPlaca());
        System.out.println("VALOR DA DIARIA: "+getValorDiaria());
        System.out.println("CILINDRADAS: "+cilindradas);
        System.out.println();
    }
    public Moto(String placa, double valorDiaria, String cilindradas) {
        super(placa, valorDiaria);
        this.cilindradas = cilindradas;
    }

    public String getCilindradas() {
        return cilindradas;
    }

    public void setCilindradas(String cilindradas) {
        this.cilindradas = cilindradas;
    }
    
}
