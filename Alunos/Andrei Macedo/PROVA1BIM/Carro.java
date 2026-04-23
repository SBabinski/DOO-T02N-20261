public class Carro extends Veiculo {

    private String arCondicionado;

    
    public Carro(String placa, double valorDiaria, String arCondicionado) {
        super(placa, valorDiaria);
        this.arCondicionado = arCondicionado;
    }


    @Override
    public void exibirVeiculos() {
        // TODO Auto-generated method stub
        super.exibirVeiculos();
        System.out.println("Possui ar condicionado: " + getArCondicionado());
    }


    public String getArCondicionado() {
        return arCondicionado;
    }


    public void setArCondicionado(String arCondicionado) {
        this.arCondicionado = arCondicionado;
    }

}
