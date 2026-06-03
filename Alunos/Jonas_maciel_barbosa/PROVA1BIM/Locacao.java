import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Locacao {

    private Cliente cliente;
    private Veiculo veiculo;
    private LocalDate dataRetirada;
    private LocalDate dataDevolucao;
    private boolean devolvido;

    private static final DateTimeFormatter FORMATO = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public Locacao(Cliente cliente, Veiculo veiculo, LocalDate dataRetirada, LocalDate dataDevolucao) {
        this.cliente = cliente;
        this.veiculo = veiculo;
        this.dataRetirada = dataRetirada;
        this.dataDevolucao = dataDevolucao;
        this.devolvido = false;
    }

    public boolean isDevolvido() {
        return devolvido;
    }

    public void realizarDevolucao() {
        this.devolvido = true;
        System.out.println("Devolucao realizada com sucesso!");
    }

public void exibirDados() {

    long dias = dataDevolucao.toEpochDay() - dataRetirada.toEpochDay();
    double total = dias * veiculo.getValorDiaria();

    System.out.println("-----------------------------");
    cliente.exibirInfo();
    veiculo.exibirInfo();
    System.out.println("Retirada : " + dataRetirada.format(FORMATO));
    System.out.println("Devolução: " + dataDevolucao.format(FORMATO));
    System.out.println("Diarias : " + dias);
    System.out.println("Total : R$ " + total);
    System.out.println("Situação : " + (devolvido ? "Devolvido" : "Em aberto"));
    System.out.println("-----------------------------");
        
    }

}


