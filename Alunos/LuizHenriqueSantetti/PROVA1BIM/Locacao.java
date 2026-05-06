import java.time.LocalDate;
public class Locacao {
    Cliente cliente;
    Veiculo veiculo;
    LocalDate dataRetirada;
    LocalDate dataDevolucao;
    boolean devolucaoRealizada;

    public Locacao(Cliente c, Veiculo v, LocalDate r, LocalDate d) {
        this.cliente = c;
        this.veiculo = v;
        this.dataRetirada = r;
        this.dataDevolucao = d;
         this.devolucaoRealizada = false;
    }
    public void visualizarLocacao() {
        long dias = dataDevolucao.toEpochDay() - dataRetirada.toEpochDay();
        double valorTotal = dias * veiculo.diaria;

        System.out.println("Cliente: " + cliente.nome);
        System.out.println("Veiculo: " + veiculo.placa);
        System.out.println("Valor Total: R$ " + valorTotal);
          System.out.println("Situacao: " + (devolucaoRealizada ? "Realizada" : "Nao realizada"));
        System.out.println("-------------------------");
    }
}