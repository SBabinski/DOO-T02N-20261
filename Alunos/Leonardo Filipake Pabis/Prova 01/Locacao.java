import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Locacao {
    private Veiculo veiculo;
    private LocalDate dataRetirada;
    private LocalDate dataDevolucao;
    private Cliente cliente;
    private boolean devolvido;
    private double valorTotal;

    public Locacao(){

    }

    public Locacao(Veiculo veiculo, Cliente cliente, String dataRetirada, String dataDevolucao){
        setCliente(cliente);
        setVeiculo(veiculo);
        setDataRetirada(dataRetirada);
        setDataDevolucao(dataDevolucao);
        setValorTotal(veiculo.getValorDiaria()*diasParaDevolucao());
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

    public void setDataRetirada(String dataRetirada) {
        LocalDate dataRetiradaDate = Date.stringToDate(dataRetirada);
        this.dataRetirada = dataRetiradaDate;
    }

    public LocalDate getDataDevolucao() {
        return dataDevolucao;
    }

    public void setDataDevolucao(String dataDevolucao) {
        LocalDate dataDevolucaoDate = Date.stringToDate(dataDevolucao);
        this.dataDevolucao = dataDevolucaoDate;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public boolean isDevolvido() {
        return devolvido;
    }

    public void setDevolvido(boolean devolvido) {
        this.devolvido = devolvido;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public long diasParaDevolucao(){
        LocalDate start = dataRetirada;
        LocalDate finish = dataDevolucao;
        long diasParaDevolucao = ChronoUnit.DAYS.between(start, finish);
        return diasParaDevolucao;
    }


    public String informacoes(){
        return String.format("Tipo: "+veiculo.getTipo()+" | Placa: "+veiculo.getPlaca()+" | Cliente: "+cliente.getNome()+" | Data da retirada: "+dataRetirada+" | Data para devolução: "+dataDevolucao+" | Devolvido?: "+isDevolvido())+" | Valor total: "+getValorTotal();
    }


    
}
