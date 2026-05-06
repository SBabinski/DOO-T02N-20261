import java.util.Date;

public class Locacao {
    private Clientes cliente;
    private Veiculos veiculo;
    private Date dataAlugado;
    private int diasAlugado;
    private boolean devolvido;

    public Locacao(Clientes cliente, Veiculos veiculo, Date dataAlugado, int diasAlugado) {
        this.cliente = cliente;
        this.veiculo = veiculo;
        this.dataAlugado = dataAlugado;
        this.diasAlugado = diasAlugado;
        this.devolvido = false;
    }

    public double calcularValorTotal() {
        return veiculo.getValorDiaria() * diasAlugado;
    }

    public void mostrarDetalhes() {
        System.out.println("Cliente: " + cliente.getNome());
        System.out.println("Veículo: " + veiculo.getPlaca());
        System.out.println("Data de Aluguel: " + dataAlugado);
        System.out.println("Dias Alugados: " + diasAlugado);
        System.out.println("Valor Total: R$" + calcularValorTotal());
        if (devolvido) {
            System.out.println("Situação: Devolvido");
        } else {
            System.out.println("Situação: Pendente");
        }
    }

    public void devolver() { this.devolvido = true; }
    public boolean isDevolvido() { return devolvido; }
    public Clientes getCliente() { return cliente; }
    public Veiculos getVeiculo() { return veiculo; }
}