package PROVA1BIM.Objetos;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Locacao {

    private Cliente cliente;
    private Veiculo veiculo;
    private LocalDate dataRetirada;
    private LocalDate dataDevolucao;
    private boolean devolvido;

    public Locacao(Cliente cliente, Veiculo veiculo, LocalDate dataRetirada, LocalDate dataDevolucao) {
        this.cliente = cliente;
        this.veiculo = veiculo;
        this.dataRetirada = dataRetirada;
        this.dataDevolucao = dataDevolucao;
        this.devolvido = false;
    }

    public double calcularTotal() {
        long dias = ChronoUnit.DAYS.between(dataRetirada, dataDevolucao);
        return (dias + 1) * veiculo.getValorDiaria();
    }

    public boolean estaAtiva() {
        return !devolvido;
    }

    public void realizarDevolucao() {
        devolvido = true;
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
}