import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Locadora {
    private String nome;
    private List<Cliente> clientes;
    private List<Veiculo> veiculos;
    private Locacao[] locacoes;
    private int quantidadeLocacoes;

    public Locadora(String nome) {
        this.nome = nome;
        this.clientes = new ArrayList<>();
        this.veiculos = new ArrayList<>();
        this.locacoes = new Locacao[10];
        this.quantidadeLocacoes = 0;
    }

    public String getNome() {
        return nome;
    }

    public int getQuantidadeClientes() {
        return clientes.size();
    }

    public int getQuantidadeVeiculos() {
        return veiculos.size();
    }

    public int getQuantidadeLocacoes() {
        return quantidadeLocacoes;
    }

    public boolean cadastrarCliente(Cliente cliente) {
        return clientes.add(cliente);
    }

    public boolean cadastrarVeiculo(Veiculo veiculo) {
        return veiculos.add(veiculo);
    }

    public boolean adicionarLocacao(Locacao locacao) {
        if (quantidadeLocacoes >= locacoes.length) {
            System.out.println("Nao ha espaco disponivel para novas locacoes.");
            return false;
        }

        locacoes[quantidadeLocacoes] = locacao;
        quantidadeLocacoes++;
        return true;
    }

    public Cliente getCliente(int indice) {
        if (indice < 0 || indice >= clientes.size()) {
            return null;
        }
        return clientes.get(indice);
    }

    public Veiculo getVeiculo(int indice) {
        if (indice < 0 || indice >= veiculos.size()) {
            return null;
        }
        return veiculos.get(indice);
    }

    public Locacao getLocacao(int indice) {
        if (indice < 0 || indice >= quantidadeLocacoes) {
            return null;
        }
        return locacoes[indice];
    }

    public void listarClientes() {
        if (clientes.isEmpty()) {
            System.out.println("Nenhum cliente cadastrado.");
            return;
        }

        for (int i = 0; i < clientes.size(); i++) {
            System.out.println("Cliente [" + i + "]");
            clientes.get(i).apresentarSe();
            System.out.println("--------------------");
        }
    }

    public void listarVeiculos() {
        if (veiculos.isEmpty()) {
            System.out.println("Nenhum veiculo cadastrado.");
            return;
        }

        for (int i = 0; i < veiculos.size(); i++) {
            System.out.println("Veiculo [" + i + "]");
            veiculos.get(i).apresentarInformacoes();
            System.out.println("--------------------");
        }
    }

    public void listarTodasLocacoes() {
        if (quantidadeLocacoes == 0) {
            System.out.println("Nenhuma locacao cadastrada.");
            return;
        }

        for (int i = 0; i < quantidadeLocacoes; i++) {
            System.out.println("Locacao [" + i + "]");
            locacoes[i].apresentarSe();
            System.out.println("--------------------");
        }
    }

    public void listarLocacoesSemDevolucao() {
        boolean encontrou = false;

        for (int i = 0; i < quantidadeLocacoes; i++) {
            if (!locacoes[i].isDevolucaoRealizada()) {
                System.out.println("Locacao [" + i + "]");
                locacoes[i].apresentarSe();
                System.out.println("--------------------");
                encontrou = true;
            }
        }

        if (!encontrou) {
            System.out.println("Nao ha locacoes ativas.");
        }
    }

    public boolean realizarDevolucao(int indiceLocacao, LocalDate dataDevolucao) {
        Locacao locacao = getLocacao(indiceLocacao);

        if (locacao == null) {
            System.out.println("Locacao invalida.");
            return false;
        }

        if (locacao.isDevolucaoRealizada()) {
            System.out.println("Essa locacao ja foi devolvida.");
            return false;
        }

        locacao.realizarDevolucao(dataDevolucao);
        System.out.println("Devolucao realizada com sucesso.");
        return true;
    }

    public void demonstracao() {
        Locadora demo = new Locadora("demo");

        Cliente cliente1 = new Cliente("Ana Souza", "111.111.111-11", "12345678900");
        Cliente cliente2 = new Cliente("Kauan Bruno", "222.222.222-22", "98765432100");

        Veiculo carro = new Carro("ABC1D23", 120.0, true);
        Veiculo moto = new Moto("XYZ9Z99", 80.0, 160);

        demo.cadastrarCliente(cliente1);
        demo.cadastrarCliente(cliente2);
        demo.cadastrarVeiculo(carro);
        demo.cadastrarVeiculo(moto);

        Locacao locacao1 = new Locacao(cliente1, carro, LocalDate.of(2026, 5, 9), LocalDate.of(2026, 5, 13));
        locacao1.realizarDevolucao(LocalDate.of(2026, 5, 13));

        Locacao locacao2 = new Locacao(cliente2, moto, LocalDate.of(2026, 5, 9), LocalDate.of(2026, 5, 23));

        demo.adicionarLocacao(locacao1);
        demo.adicionarLocacao(locacao2);

        System.out.println("Locacoes ativas na demonstracao:");
        demo.listarLocacoesSemDevolucao();
    }
}