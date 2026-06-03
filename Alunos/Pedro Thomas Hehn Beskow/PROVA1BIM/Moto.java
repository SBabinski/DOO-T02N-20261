public class Moto extends Veiculo {

    private String strCilindrada;

public Moto(String pPlaca, float pValorDiaria, String pCilindrada){
    super(pPlaca, pValorDiaria);
    this.strCilindrada = pCilindrada;
}

public String getStrCilindrada() {
    return strCilindrada;
}

public void setStrCilindrada(String strCilindrada) {
    this.strCilindrada = strCilindrada;
}

   @Override
    public String ApresentaVeiculo(){
        return String.format("Carro: %s \nValor da Diaria: %s \nAr condicionado: %s", 
        getStrPlaca(), getFltValorDiaria(), strCilindrada);
    }

}
