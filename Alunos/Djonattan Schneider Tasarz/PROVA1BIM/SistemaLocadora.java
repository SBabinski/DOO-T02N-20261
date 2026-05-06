import java.time.LocalDate;

public class SistemaLocadora {
    private Cliente[] clientes;
    private Veiculo[] veiculos;
    private Locacao[] locacoes;
    private int qtdClientes;
    private int qtdVeiculos;
    private int qtdLocacoes;

    public SistemaLocadora() {
        this.clientes = new Cliente[10];
        this.veiculos = new Veiculo[10];
        this.locacoes = new Locacao[10];
        this.qtdClientes = 0;
        this.qtdVeiculos = 0;
        this.qtdLocacoes = 0;
    }

    public Cliente[] getClientes() {
        return clientes;
    }

    public Veiculo[] getVeiculos() {
        return veiculos;
    }

    public Locacao[] getLocacoes() {
        return locacoes;
    }

    public void cadastrarCliente(Cliente cliente) {
        if (qtdClientes < 10) {
            clientes[qtdClientes++] = cliente;
        }
    }

    public void cadastrarVeiculo(Veiculo veiculo) {
        if (qtdVeiculos < 10) {
            veiculos[qtdVeiculos++] = veiculo;
        }
    }

    public boolean cadastrarLocacao(Cliente cliente, Veiculo veiculo, LocalDate retirada, LocalDate devolucao) {
        if (qtdLocacoes >= 10) {
            return false;
        }

        locacoes[qtdLocacoes++] = new Locacao(cliente, veiculo, retirada, devolucao);
        return true;
    }

    public void realizarDevolucao(int indice) {
        if (indice >= 0 && indice < qtdLocacoes && locacoes[indice] != null) {
            if (!locacoes[indice].isDevolucaoRealizada()) {
                locacoes[indice].realizarDevolucao();
                System.out.println("Devolução realizada com sucesso.");
            } else {
                System.out.println("Essa locação já teve a devolução realizada.");
            }
        } else {
            System.out.println("Índice de locação inválido.");
        }
    }

    public void listarLocacoesSemDevolucao() {
        System.out.println("\n--- LOCAÇÕES SEM DEVOLUÇÃO ---");
        boolean existeAtiva = false;

        for (int i = 0; i < qtdLocacoes; i++) {
            Locacao loc = locacoes[i];
            if (loc != null && !loc.isDevolucaoRealizada()) {
                System.out.println("Índice " + i + ": " + loc);
                existeAtiva = true;
            }
        }

        if (!existeAtiva) {
            System.out.println("Nenhuma locação ativa encontrada.");
        }
    }
}