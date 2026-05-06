import java.time.LocalDate;

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

    public void realizarDevolucao() {
        this.devolucaoRealizada = true;
    }

    public double calcularValorTotal() {
        long dias = java.time.temporal.ChronoUnit.DAYS.between(dataRetirada, dataDevolucao);
        return dias * veiculo.getValorDiaria();
    }

    @Override
    public String toString() {
        String statusDevolucao = devolucaoRealizada ? "Devolução realizada" : "Aguardando devolução";
        return "Locação{" +
                "cliente=" + cliente.getNome() +
                ", veiculo=" + veiculo.getPlaca() +
                ", retirada=" + dataRetirada +
                ", devolução=" + dataDevolucao +
                ", status=" + statusDevolucao +
                ", valor total=R$" + String.format("%.2f", calcularValorTotal()) +
                '}';
    }
}