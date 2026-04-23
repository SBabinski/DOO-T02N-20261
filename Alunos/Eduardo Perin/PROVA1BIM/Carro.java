public class Carro extends Veiculo {
    private Boolean temAr;

    @Override
    public void exibirInfo(){
        System.out.println("PLACA: "+getPlaca());
        System.out.println("VALOR DA DIARIA: "+getValorDiaria());
        System.out.println("TEM AR CONDICIONADO?: "+temAr);
        System.out.println();
    }
    public Carro(String placa, double valorDiaria, Boolean temAr) {
        super(placa, valorDiaria);
        this.temAr = temAr;
    }

    public Boolean getTemAr() {
        return temAr;
    }

    public void setTemAr(Boolean temAr) {
        this.temAr = temAr;
    }

    
}
