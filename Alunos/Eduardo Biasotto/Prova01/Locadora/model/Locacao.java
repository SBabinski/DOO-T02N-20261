package Locadora.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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

    public void exibirInformacoes() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        
        System.out.println("\n- Informacoes da Locacao:");
        cliente.apresentarse();
        veiculo.exibirInformacoes();
        System.out.println("\nData de Retirada: " + dataRetirada.format(formatter));
        System.out.println("Data de Devolucao: " + dataDevolucao.format(formatter));
        System.out.println("Devolucao Realizada: " + (devolucaoRealizada ? "Sim" : "Nao"));
        System.out.printf("\nValor Total da Locacao: R$ %.2f%n", calcularValorTotal());
    }

    public double calcularValorTotal() {
        long diasLocacao = java.time.temporal.ChronoUnit.DAYS.between(dataRetirada, dataDevolucao);
        return veiculo.getValorDiaria() * diasLocacao;
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

    public boolean isDevolucaoRealizada() {
        return devolucaoRealizada;
    }

    public void setDevolucaoRealizada(boolean devolucaoRealizada) {
        this.devolucaoRealizada = devolucaoRealizada;
    }

    public void realizarDevolucao() {
        this.devolucaoRealizada = true;
    }


}