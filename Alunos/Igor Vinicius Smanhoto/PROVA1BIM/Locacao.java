package objetos;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.time.format.DateTimeFormatter;

public class Locacao {

    private Cliente cliente;
    private Veiculo veiculo;
    private LocalDate dataRetirada;
    private LocalDate dataDevolucao;
    private boolean devolvido;

    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public Locacao(Cliente cliente, Veiculo veiculo, LocalDate dataRetirada) {
        this.cliente = cliente;
        this.veiculo = veiculo;
        this.dataRetirada = dataRetirada;
        this.devolvido = false;
    }

    public void realizarDevolucao(LocalDate dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
        this.devolvido = true;
    }

    public long calcularDiarias() {
        if (dataDevolucao == null) return 0;
        return ChronoUnit.DAYS.between(dataRetirada, dataDevolucao) + 1;
    }

    public double calcularValorTotal() {
        return calcularDiarias() * veiculo.getValorDiaria();
    }

    public String exibirDados() {
        if (!devolvido) {
            return "Cliente: " + cliente.getNome() +
                   "\nVeículo: " + veiculo.getDescricao() +
                   "\nRetirada: " + dataRetirada.format(formatter) +
                   "\nDevolução: Pendente" +
                   "\nValor: Em aberto";
        }

        return "Cliente: " + cliente.getNome() +
               "\nVeículo: " + veiculo.getDescricao() +
               "\nRetirada: " + dataRetirada.format(formatter) +
               "\nDevolução: " + dataDevolucao.format(formatter) +
           
               "\nDiárias: " + calcularDiarias() +
               "\nValor da diária: R$ " + veiculo.getValorDiaria() +
               "\nTotal: R$ " + calcularValorTotal();
    }
    public boolean isDevolvido() {
        return devolvido;
    }
}