public class Carro extends Veiculo {
    private Boolean blnTemAr;

    public Carro(String pPlaca, float pValorDiaria, Boolean pTemAr){
        super(pPlaca, pValorDiaria);
        this.blnTemAr = pTemAr;
    }

    public Boolean getBlnTemAr() {
        return blnTemAr;
    }

    public void setBlnTemAr(Boolean blnTemAr) {
        this.blnTemAr = blnTemAr;
    }

    @Override
    public String ApresentaVeiculo(){
        return String.format("Carro: %s \nValor da Diaria: %s \nAr condicionado: " + (blnTemAr ? "Disponivel" : "Indisponivel"), 
        getStrPlaca(), getFltValorDiaria(), blnTemAr);
    }
}
