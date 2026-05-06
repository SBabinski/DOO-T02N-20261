package objetos;

public class Veiculo {
    private static int contador = 0;
    private int id;
    private String placa;
    private double valDia;
    public Veiculo(String placa, double valDia) {
        this.id = ++ contador;
        this.placa = placa;
        this.valDia = valDia;
    }
    public int getId() {
        return id;
    }
    public String getPlaca() {
        return placa;
    }
    public void setPlaca(String placa) {
        this.placa = placa;
    }
    public double getValDia() {
        return valDia;
    }
    public void setValDia(double valDia) {
        this.valDia = valDia;
    }
    
    public void exibir(){
        System.out.printf("ID: %d - Placa: %s - Valor diaria: %.2f",id,placa,valDia);
    }

}
