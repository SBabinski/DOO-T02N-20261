public class Moto extends Veiculo {
    private double cilindrada;

    public Moto(){

    }

    public Moto(String placa, double valor, double cilindrada){
        super(placa, valor);
        setCilindrada(cilindrada);
    }

    public double getCilindrada() {
        return cilindrada;
    }

    public void setCilindrada(double cilindrada) {
        this.cilindrada = cilindrada;
    }

    public String getTipo(){
        return String.format("moto");
    }

    public String informacoes(){
        return String.format("Tipo: "+getTipo()+" | Placa: "+getPlaca()+" | Valor da diária: "+getValorDiaria()+" | Cilindrada: "+cilindrada);
    }
}
