package objetos;

public class Carro extends Veiculo{
    private boolean arCondi;

    public Carro(String placa, double valDia, boolean arCondi) {
        super(placa, valDia);
        this.arCondi = arCondi;
    }

    public boolean isArCondi() {
        return arCondi;
    }

    public void setArCondi(boolean arCondi) {
        this.arCondi = arCondi;
    }

    @Override
    public void exibir(){
        super.exibir();
        String ar;
        if (arCondi) {
            ar ="Possui";
        }else{
            ar ="Não possui";
        }
        System.out.printf(" - Ar-condicionado: %s - Categoria: Carro.\n",ar);
    }
}
