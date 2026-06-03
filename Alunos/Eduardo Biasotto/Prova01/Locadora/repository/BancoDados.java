package Locadora.repository;

import Locadora.model.Cliente;
import Locadora.model.Veiculo;
import Locadora.model.Locacao;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class BancoDados {

    private Cliente[] clientes = new Cliente[10];
    private int totalClientes = 0;

    private Veiculo[] veiculos = new Veiculo[10];
    private int totalVeiculos = 0;

    private Locacao[] locacoes = new Locacao[10];
    private int totalLocacoes = 0;

    public BancoDados() {
        this.clientes = new Cliente[10];
        this.totalClientes = 0;
        this.veiculos = new Veiculo[10];
        this.totalVeiculos = 0;
        this.locacoes = new Locacao[10];
        this.totalLocacoes = 0;
    }

    public void adicionarCliente(String nome, String cpf, String cnh) {
        if (totalClientes < 10) {
            clientes[totalClientes] = new Cliente(nome, cpf, cnh);
            totalClientes++;
            System.out.println("\nCliente cadastrado com sucesso!");
        } else {
            System.out.println("\nLimite de clientes atingido!");
        }
    }

    public void listarClientes() {
        if (totalClientes == 0) {
            System.out.println("\nNenhum cliente cadastrado.");
            return;
        }
        System.out.println("\nTotal de clientes: " + totalClientes);
        for (int i = 0; i < totalClientes; i++) {
            System.out.println("\n--- Cliente " + (i + 1) + " ---");
            clientes[i].apresentarse();
        }
    }

    public void adicionarVeiculo(Veiculo veiculo) {
        if (totalVeiculos < 10) {
            veiculos[totalVeiculos] = veiculo;
            totalVeiculos++;
            System.out.println("\nVeículo cadastrado com sucesso!");
        } else {
            System.out.println("\nLimite de veículos atingido!");
        }
    }

    public void listarVeiculos() {
        if (totalVeiculos == 0) {
            System.out.println("\nNenhum veiculo cadastrado.");
            return;
        }
        for (int i = 0; i < totalVeiculos; i++) {
            System.out.println("\n--- Veiculo " + (i + 1) + " ---");
            veiculos[i].exibirInformacoes();
        }
    }

    public void adicionarLocacao(Locacao locacao) {
        if (totalLocacoes < 10) {
            locacoes[totalLocacoes] = locacao;
            totalLocacoes++;
            System.out.println("\nLocação adicionada com sucesso!");
        } else {
            System.out.println("\nLocadora cheia! Não é possível adicionar mais locações.");
        }
    }

    public void listarSemDevolucao() {
        System.out.println("\nLocacoes sem devolucao:");
        for (int i = 0; i < totalLocacoes; i++) {
            if (!locacoes[i].isDevolucaoRealizada()) {
                System.out.println("\n--- Locacao " + (i + 1) + " ---"); // ← mostra o numero
                locacoes[i].exibirInformacoes();
            }
        }
    }

    public void realizarDevolucao(int indice) {
        if (indice < 0 || indice >= totalLocacoes) {
            System.out.println("Locacao invalida!");
            return;
        }
        if (locacoes[indice].isDevolucaoRealizada()) {
            System.out.println("Essa locacao ja foi devolvida!");
            return;
        }
        locacoes[indice].realizarDevolucao();
        System.out.println("Devolucao realizada com sucesso!");
    }

    public void adicionarLocacao(int indiceCliente, int indiceVeiculo, String dataRetiradaStr, String dataDevolucaoStr) {
        if (indiceCliente < 0 || indiceCliente >= totalClientes) {
            System.out.println("Cliente invalido!");
            return;
        }
        if (indiceVeiculo < 0 || indiceVeiculo >= totalVeiculos) {
            System.out.println("Veiculo invalido!");
            return;
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate dataRetirada = LocalDate.parse(dataRetiradaStr, formatter);
        LocalDate dataDevolucao = LocalDate.parse(dataDevolucaoStr, formatter);

        Cliente cliente = clientes[indiceCliente];
        Veiculo veiculo = veiculos[indiceVeiculo];
        Locacao locacao = new Locacao(cliente, veiculo, dataRetirada, dataDevolucao);

        if (totalLocacoes < 10) {
            locacoes[totalLocacoes] = locacao;
            totalLocacoes++;
            System.out.println("\nLocacao cadastrada com sucesso!");
        } else {
            System.out.println("\nLocadora cheia!");
        }
    }

    public Cliente[] getClientes() { return clientes; }
    public int getTotalClientes() { return totalClientes; }
    public Veiculo[] getVeiculos() { return veiculos; }
    public int getTotalVeiculos() { return totalVeiculos; }
    public Locacao[] getLocacoes() { return locacoes; }
    public int getTotalLocacoes() { return totalLocacoes; }
}