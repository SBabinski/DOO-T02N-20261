package Objetos;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Locacao {
    
    private Cliente cliente;
    private Veiculo veiculo;
    private LocalDate dataRetirada;
    private LocalDate dataDevolucao;
    private boolean devolucao;

    public Locacao(){
    } 

    public Locacao(Cliente cliente, Veiculo veiculo, LocalDate dataRetirada, LocalDate dataDevolucao, boolean devolucao){

    }

    public String getCliente(){
        return cliente.getNome();
    }

    public String getVeiculo(){
        return veiculo.getPlaca();
    }

    public LocalDate getDataRetirada(){
        return dataRetirada;
    }

    public LocalDate getDataDevolucao(){
        return dataDevolucao;
    }

    public boolean getDevolucao(){
        return devolucao;
    }

    public void setCliente(Cliente cliente){
        this.cliente = cliente;
    }

    public void setVeiculo(Veiculo veiculo){
        this.veiculo = veiculo;
    }

    public void setDataRetirada(LocalDate dataRetirada){
        this.dataRetirada = dataRetirada;
    }

    public void setDataDevolucao(LocalDate dataDevolucao){
        this.dataDevolucao = dataDevolucao;
    }

    public void setDevolucao(boolean devolucao){
        this.devolucao = devolucao;
    }

    public void mostrarLocacao(){
        String situacao;
        String dataDevolucaoFormatada;
        DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String dataRetiradaFormatada = dataRetirada.format(formatador);
        double diasTotais = ChronoUnit.DAYS.between(dataRetirada, dataDevolucao);
        if(dataDevolucao == null){
            dataDevolucaoFormatada = LocalDate.now().format(formatador);
        }else{
            dataDevolucaoFormatada = dataDevolucao.format(formatador);
        }
        if(devolucao == true){
            situacao = "Devolvido";
        }else{
            situacao = "Não Devolvido";
        }
        System.out.printf("Veículo - %s | Data de Retirada: %s | Devolucao: %s | Situação: %s | Total R$%.2f\n",
        veiculo.getClass(), dataRetiradaFormatada, dataDevolucaoFormatada,
        situacao, diasTotais*veiculo.getValorDiaria());
    }

}
