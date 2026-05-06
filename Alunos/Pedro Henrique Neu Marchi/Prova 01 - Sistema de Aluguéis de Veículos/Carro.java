public class Carro extends Veiculo {
    Boolean arCondicionado;
    
    @Override
    public void exibirDados() {
        System.err.println("Placa: " + placa);
        System.out.println("Valor de locação: " + valorLocacao);
      if (arCondicionado) {
            System.out.println("Possui ar condicionado");
        } else {
            System.out.println("Não possui ar condicionado");
        }
    }
    
}
