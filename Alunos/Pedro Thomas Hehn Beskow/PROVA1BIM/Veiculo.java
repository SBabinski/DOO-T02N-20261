public class Veiculo {
    
    private String strPlaca;
    private float fltValorDiaria;

    public Veiculo(String pPlaca, float pValorDiaria) {
            this.fltValorDiaria = pValorDiaria;
            this.strPlaca = pPlaca;
        }



    public Float getFltValorDiaria() {
        return fltValorDiaria;
    }

    public void setFltValorDiaria(Float fltValorDiaria) {
        this.fltValorDiaria = fltValorDiaria;
    }

    public String getStrPlaca() {
        return strPlaca;
    }

    public void setStrPlaca(String strPlaca) {
        this.strPlaca = strPlaca;
    }

    
    public String ApresentaVeiculo(){
        return new String();
    }

}
