package Objetos;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Locação {
    Cliente cliente;
    Veiculo veiculo;
    LocalDate dataLocacao;
    LocalDate dataDevolucao;
    boolean devolucao;

    public Locação(Cliente cliente, Veiculo veiculo, LocalDate dataLocacao, LocalDate dataDevolucao,
            boolean devolucao) {
        this.cliente = cliente;
        this.veiculo = veiculo;
        this.dataLocacao = dataLocacao;
        this.dataDevolucao = dataDevolucao;
        this.devolucao = devolucao;
    }

    List<Locação> locacoes = new ArrayList<>();

    public boolean isDevolucao() {
        return devolucao;
    }

    public void setDevolucao(boolean devolucao) {
        this.devolucao = devolucao;
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

    public void AddVeiculo(Locação locacao) {
        locacoes.add(locacao);
    }

    public void valorTotal() {

        long dias = dataDevolucao.toEpochDay() - dataLocacao.toEpochDay();
        double valorTotal = dias * veiculo.getValorDiario();
        System.out.println("O valor total da locação é R$" + valorTotal);

    }

    public void mostrarLocacao() {
        cliente.apresentarCliente();
        veiculo.apresentarVeiculo();
        valorTotal();
        System.out.println("A devolução já foi feita:"+isDevolucao());
    }

    public void realizarDevolucao() {
        this.devolucao = true;
    }
}
