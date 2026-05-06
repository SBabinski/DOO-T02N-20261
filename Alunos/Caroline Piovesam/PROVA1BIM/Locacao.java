import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Locacao {

    private Cliente cliente;
    private Veiculo veiculo;
    private LocalDateTime dataRetirada;
    private LocalDateTime dataDevolucao;
    private boolean devolvido = false;

    public Locacao(Cliente cliente, Veiculo veiculo, LocalDateTime dataRetirada,
            LocalDateTime dataDevolucao) {

        this.cliente = cliente;
        this.veiculo = veiculo;
        this.dataRetirada = dataRetirada;
        this.dataDevolucao = dataDevolucao;

    }

    public Cliente getCliente() {
        return this.cliente;
    }

    public Veiculo getVeiculo() {
        return this.veiculo;
    }

    public LocalDateTime getDataRetirada() {
        return this.dataRetirada;
    }

    public LocalDateTime getDataDevolucao() {
        return this.dataDevolucao;
    }

    public boolean isDevolvido() {
        return this.devolvido;
    }

    public long calcularDiaria() {
        long dias = ChronoUnit.DAYS.between(dataRetirada, dataDevolucao);
        return (dias <= 0) ? 1 : dias;
    }

    public double calcularValorTotal() {
        return calcularDiaria() * veiculo.getValorDiaria();
    }

    public void realizarDevolucao() {
        this.devolvido = true;
        System.out.println("\nLocação finalizada! | Veículo: " + veiculo.getPlaca());
    }

    public void mostrarDados() {

        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

        System.out.println("Cliente: " + cliente.getNome());

        veiculo.mostrarDados();

        System.out.println("Data da Retirada: " + dataRetirada.format(formato));

        System.out.println("Data de Devolução: " + dataDevolucao.format(formato));

        System.out.println("Valor total: R$ " + calcularValorTotal());

        System.out.println("Status: " + (devolvido ? "Devolvido" : "Pendente"));
    }

}