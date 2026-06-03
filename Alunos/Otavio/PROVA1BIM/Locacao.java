import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Locacao {
    private Cliente cliente;
    private Veiculo veiculo;
    private LocalDate dataRetirada;
    private LocalDate dataDevolucao;
    private boolean situacaoDevolucao;

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }

    public LocalDate getDataRetirada() {
        return dataRetirada;
    }

    public void setDataRetirada(LocalDate dataRetirada) {
        this.dataRetirada = dataRetirada;
    }

    public LocalDate getDataDevolucao() {
        return dataDevolucao;
    }

    public void setDataDevolucao(LocalDate dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }

    public boolean getSituacaoDevolucao() {
        return situacaoDevolucao;
    }

    public void setSituacaoDevolucao(boolean situacaoDevolucao) {
        this.situacaoDevolucao = situacaoDevolucao;
    }

    public Locacao(Cliente cliente, Veiculo veiculo, LocalDate dataRetirada, LocalDate dataDevolucao,
            boolean situacaoDevolucao) {
        setCliente(cliente);
        setVeiculo(veiculo);
        setDataRetirada(dataRetirada);
        setDataDevolucao(dataDevolucao);
        setSituacaoDevolucao(situacaoDevolucao);
    }

    public void mostrarDados() {
        String respostaSituacao = getSituacaoDevolucao() ? "Entregue" : "Não entregue";

        System.out.println("O cliente da locação foi: " + getCliente().getNome() + "\nO veiculo foi: \n" + getVeiculo().mostrarInformacoes() + "\nA data de retirada foi: " + getDataRetirada() + "\nA data de devolução foi: " + getDataDevolucao() + "\nA situação da locação é: " + respostaSituacao + "\nO valor total ficou em: " + retornaValorTotal());
    }

    private double retornaValorTotal() {
        double valorTotal = getDataRetirada().until(dataDevolucao, ChronoUnit.DAYS) * getVeiculo().getValorDiaria();

        if (valorTotal < 0) {
            return 0;
        }

        return valorTotal;
    }
}
