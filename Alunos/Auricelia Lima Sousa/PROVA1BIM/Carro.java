public class Carro extends Veiculo{
    public boolean temArCondicionado;
    @Override

    public void exibirInformacoes(){
            super.exibirInformacoes();
            System.out.println(" | Ar-Condicionado: " + (temArCondicionado ? "Sim" : "Nao"));
           
        }
    }

    
