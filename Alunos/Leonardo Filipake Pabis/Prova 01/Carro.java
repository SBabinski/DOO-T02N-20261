public class Carro extends Veiculo {
    private boolean ar;

    public Carro(){

    }

    public Carro(String placa, double valor, boolean ar){
        super(placa, valor);
        setAr(ar);
    }

    public boolean isAr() {
        return ar;
    }

    public void setAr(boolean ar) {
        this.ar = ar;
    }

    public String getTipo(){
        return String.format("carro");
    }

    public String informacoes(){
        return String.format("Tipo: "+getTipo()+" | Placa: "+getPlaca()+" | Valor da diária: "+getValorDiaria()+" | Ar condicionado?: "+ar);
    }
}
