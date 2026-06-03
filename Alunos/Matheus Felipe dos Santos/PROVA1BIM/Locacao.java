import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Locacao {
    Cliente cliente;
    Veiculo veiculo;
    LocalDate dataRetirada;
    LocalDate dataDevolucao;
    boolean devolvido;

    double calcularTotal() {
        long dias = ChronoUnit.DAYS.between(dataRetirada, dataDevolucao);
        return dias * veiculo.valorDiaria;
    }

    void exibir() {
        cliente.exibir();
        veiculo.exibir();
        System.out.println("Data retirada: " + dataRetirada);
        System.out.println("Data devolução: " + dataDevolucao);
        System.out.println("Devolvido: " + devolvido);
        System.out.println("Total: " + calcularTotal());
    }
}