import java.time.LocalDate; 
public class Locacao {
    
    Clientes clientes;
    Veiculo veiculo;
    LocalDate dataRetirada;
    LocalDate dataDevolucao;
    int dias;
    boolean devolucaoRealizada;

    public Locacao (Clientes clientes, Veiculo veiculo, LocalDate dataRetirada, LocalDate dataDevolucao, int dias) {
        this.clientes = clientes;
        this.veiculo = veiculo;
        this.dataRetirada = dataRetirada;
        this.dataDevolucao = dataDevolucao;
        this.devolucaoRealizada = false;
        this.dias = dias;
    }
    public double calcularTotal() {
        return dias * veiculo.valorDiaria;
    }
    public void realizarDevolucao() {
        devolucaoRealizada = true;
    }
    public void exibir() {
    System.out.println("Cliente: " + clientes.nome);
    veiculo.exibirDados();
    System.out.println("Retirada: " + dataRetirada);
    System.out.println("Devolução: " + dataDevolucao);
    System.out.println("Total: " + calcularTotal());
    if (devolucaoRealizada) {
        System.out.println("Situação: Realizada");
    } else {
        System.out.println("Situação: Não realizada");
    }
    }
}