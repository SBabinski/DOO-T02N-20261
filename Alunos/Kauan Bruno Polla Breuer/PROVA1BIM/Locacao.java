import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Locacao {
    private Cliente cliente;
    private Veiculo veiculo;
    private LocalDate dataRetirada;
    private LocalDate dataDevolucao;
    private boolean devolucaoRealizada;

    public Locacao(Cliente cliente, Veiculo veiculo, LocalDate dataRetirada, LocalDate dataDevolucao) {
        this.cliente = cliente;
        this.veiculo = veiculo;
        this.dataRetirada = dataRetirada;
        this.dataDevolucao = dataDevolucao;
        this.devolucaoRealizada = false;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public LocalDate getDataRetirada() {
        return dataRetirada;
    }

    public LocalDate getDataDevolucao() {
        return dataDevolucao;
    }

    public boolean isDevolucaoRealizada() {
        return devolucaoRealizada;
    }

    public void realizarDevolucao(LocalDate dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
        this.devolucaoRealizada = true;
    }

    public double calcularValorTotal() {
        long diarias = ChronoUnit.DAYS.between(dataRetirada, dataDevolucao);
        if (diarias <= 0) {
            diarias = 1;
        }
        return diarias * veiculo.getValorDiaria();
    }

    public void apresentarSe() {
        System.out.println("Cliente: " + cliente.getNome());
        System.out.println("Veiculo:");
        veiculo.apresentarInformacoes();
        System.out.println("Data de retirada: " + dataRetirada);
        System.out.println("Data de devolucaoo: " + dataDevolucao);
        System.out.println("Devolucaoo realizada: " + (devolucaoRealizada ? "Sim" : "Nao"));
        System.out.printf("Valor total: R$ %.2f%n", calcularValorTotal());
    }
}