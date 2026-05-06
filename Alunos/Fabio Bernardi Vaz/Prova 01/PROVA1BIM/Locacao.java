package fag;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Locacao {

    private Cliente cliente;
    private Veiculo veiculo;
    private LocalDate dataRetirada;
    private LocalDate dataDevolucao;
    private boolean devolucaoRealizada;

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public Locacao(Cliente cliente, Veiculo veiculo, LocalDate dataRetirada, LocalDate dataDevolucao) {
        this.cliente = cliente;
        this.veiculo = veiculo;
        this.dataRetirada = dataRetirada;
        this.dataDevolucao = dataDevolucao;
        this.devolucaoRealizada = false;
    }

    public double calcularValorTotal() {
        long inicio = dataRetirada.toEpochDay();
        long fim = dataDevolucao.toEpochDay();
        long quantidadeDiarias = fim - inicio;

        return quantidadeDiarias * veiculo.getValorDiaria();
    }

    public void realizarDevolucao() {
        this.devolucaoRealizada = true;
        System.out.println("Devolução do veículo " + veiculo.getPlaca() + " realizada com sucesso!");
    }

    public void apresentarse() {
        long diarias = dataDevolucao.toEpochDay() - dataRetirada.toEpochDay();
        System.out.println("\nLOCAÇÃO");
        System.out.println("Cliente: " + cliente.getNome() + " CPF: " + cliente.getCpf());
        System.out.println("Veículo: " + veiculo.getPlaca());
        System.out.println("Retirada: " + dataRetirada.format(formatter));
        System.out.println("Devolução prevista: " + dataDevolucao.format(formatter));

        if(devolucaoRealizada){
                System.out.println("Devolução realizada: " + dataDevolucao.format(formatter));
        } else{
                System.out.println("Devolução em aberto");
        }

        System.out.println("Quantidade de diárias: " + diarias);
        System.out.printf("Valor total: R$ %.2f", calcularValorTotal());
    }

    public boolean isDevolucaoRealizada() {
        return devolucaoRealizada;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }
}