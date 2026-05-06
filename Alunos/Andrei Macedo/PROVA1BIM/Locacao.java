import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Locacao {

    private Veiculo veiculo;
    private Cliente cliente;
    private LocalDate dataLocacao;
    private LocalDate dataDevolucao;
    private boolean devolvido;

    public Locacao(Veiculo veiculo, Cliente cliente, LocalDate dataLocacao, LocalDate dataDevolucao,
    boolean devolvido) {
        this.veiculo = veiculo;
        this.cliente = cliente;
        this.dataLocacao = dataLocacao;
        this.dataDevolucao = dataDevolucao;
        this.devolvido = devolvido;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public LocalDate getDataLocacao() {
        return dataLocacao;
    }

    public void setDataLocacao(LocalDate dataLocacao) {
        this.dataLocacao = dataLocacao;
    }

    public LocalDate getDataDevolucao() {
        return dataDevolucao;
    }

    public void setDataDevolucao(LocalDate dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }

    public boolean isDevolvido() {
        return devolvido;
    }

    public void setDevolvido(boolean devolvido) {
        this.devolvido = devolvido;
    }

    public void exibirLocacao() {
        getVeiculo().exibirVeiculos();
        System.out.println("Cliente : " + getCliente());
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String horarioFormatado = getDataLocacao().format(formato);
        System.out.println("Data de locação: " + horarioFormatado);
        String dataDevolucao = getDataDevolucao().format(formato);
        System.out.println("Data prevista para devolução: " + dataDevolucao);
    }

    

}
