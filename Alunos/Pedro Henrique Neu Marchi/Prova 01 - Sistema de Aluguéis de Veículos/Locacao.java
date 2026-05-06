import java.time.LocalDate;

public class Locacao {
    Clientes clientes;
    Veiculo veiculo;
    LocalDate dataLocacao;
    LocalDate dataDevolucao;
    int diasLocacao;
    Double valorTotal;
    boolean devolvido;

    public LocalDate calcularDataDevolucao() {
        if (dataLocacao != null) {
            return dataLocacao.plusDays(diasLocacao);
        }
        return null;
    }
    public void exibirLocacao() {
        System.out.println("Cliente: " + clientes.nome);
        System.out.println("Veículo: " + veiculo.placa);
        System.out.println("Data de Locação: " + dataLocacao);
        System.out.println("Data de Devolução: " + calcularDataDevolucao());
        System.out.println("Dias de Locação: " + diasLocacao);
        System.out.println("Devolvido: " + (devolvido ? "Sim" : "Não"));
        
    }
}