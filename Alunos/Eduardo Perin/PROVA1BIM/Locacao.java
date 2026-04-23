import java.time.LocalDate;

public class Locacao {
    private Cliente cliente;
    private Veiculo veiculo;
    private LocalDate dataRetirada;
    private LocalDate dataDevolucao;
    private Boolean situacaoDevolucao;

    public void exibirInfo(){
        System.out.println("CLIENTE: "+cliente.getNome());
        System.out.println("VEICULO: "+veiculo.getPlaca());
        System.out.println("DATA DE DEVOLUCAO: "+dataDevolucao);
        System.out.println("VALOR A PAGAR: "+dataDevolucao.compareTo(dataRetirada)*veiculo.getValorDiaria());
        System.out.println("SITUACAO DA DEVOLUCAO: "+situacaoDevolucao);
        System.out.println();
    }
    public Locacao(Cliente cliente, Veiculo veiculo, LocalDate dataRetirada, LocalDate dataDevolucao,
            Boolean situacaoDevolucao) {
        this.cliente = cliente;
        this.veiculo = veiculo;
        this.dataRetirada = dataRetirada;
        this.dataDevolucao = dataDevolucao;
        if(situacaoDevolucao==null){situacaoDevolucao=false;}
        this.situacaoDevolucao = situacaoDevolucao;
    }
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
    public Boolean getSituacaoDevolucao() {
        return situacaoDevolucao;
    }
    public void setSituacaoDevolucao(Boolean situacaoDevolucao) {
        this.situacaoDevolucao = situacaoDevolucao;
    }

    
}
