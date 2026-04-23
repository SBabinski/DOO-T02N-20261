package prova1bim;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Locacao {

    private String cliente;
    private String placa;
    private int diasLocacao;
    private double valorDiaria;
    private int numeroVaga;
    private boolean isDevolvido;

    LocalDate retirada;
    LocalDate devolucao;

    DateTimeFormatter parser = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public Locacao(int numeroVaga){

        this.cliente = null;
        this.placa = null;
        this.diasLocacao = 0;
        this.valorDiaria = 0;
        this.numeroVaga = numeroVaga;
        this.isDevolvido = true;
        this.retirada = LocalDate.parse("01/01/1900", parser);
        this.devolucao = retirada;
    }

    public Locacao(String cliente, String placa, int diasLocacao, int numeroVaga, String retirada, double valorDiaria){

        this.cliente = cliente;
        this.placa = placa;
        this.diasLocacao = diasLocacao;
        this.numeroVaga = numeroVaga;
        this.valorDiaria = valorDiaria;
        this.isDevolvido = false;

        this.retirada = LocalDate.parse(retirada, parser);
        this.devolucao = this.retirada.plusDays(diasLocacao);
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public int getDiasLocacao() {
        return diasLocacao;
    }

    public void setDiasLocacao(int diasLocacao) {
        this.diasLocacao = diasLocacao;
    }

    public double getValorDiaria() {
        return valorDiaria;
    }

    public void setValorDiaria(double valorDiaria) {
        this.valorDiaria = valorDiaria;
    }

    public int getNumeroVaga() {
        return numeroVaga;
    }

    public void setNumeroVaga(int numeroVaga) {
        this.numeroVaga = numeroVaga;
    }

    public boolean getIsDevolvido() {
        return isDevolvido;
    }

    public void setDevolvido(boolean isDevolvido) {
        this.isDevolvido = isDevolvido;
    }

    public LocalDate getRetirada() {
        return retirada;
    }

    public void setRetirada(LocalDate retirada) {
        this.retirada = retirada;
    }

    public LocalDate getDevolucao() {
        return devolucao;
    }

    public void setDevolucao(LocalDate devolucao) {
        this.devolucao = devolucao;
    }

    public double calcularValorTotal(){

        return this.valorDiaria * this.diasLocacao;
    }
}
