import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Locadora {

    private List<Cliente> clientes = new ArrayList<>();
    private List<Veiculo> veiculos = new ArrayList<>();
    private List<Locacao> locacoes = new ArrayList<>();

    public void cadastrarCliente(String Nome, String cpf, String cnh) {
        Cliente cliente = new Cliente(Nome, cpf, cnh);
        clientes.add(cliente);
        System.out.println(cliente);
    }

    public void cadastrarCarro(String placa, double valorDiaria, String ar ) {
        Carro carro = new Carro(placa, valorDiaria, ar);
        veiculos.add(carro);
    }

    public void cadastrarMoto(String placa, double valorDiaria, String cilindrada) {
        Moto moto = new Moto(placa, valorDiaria, cilindrada);
        veiculos.add(moto);
    }

    public void apresentarCliente() {
        int x = 0;
        if (clientes.size() == 0) {
            System.out.println("Lista de leitores vazia!");
            return;
        }
        for (Cliente cliente : clientes) {
            System.out.printf(" =========== Cliente N° %d =========== \n", (x + 1));
            System.out.println(cliente);
            x++;
        }
    }

    public void apresentarVeiculos() {
        if (veiculos.size() == 0) {
            System.out.println("Lista de intens vazia!");
            return;
        }
        for (int x = 0; x < veiculos.size(); x++){
            System.out.printf(" =========== Item N° %d =========== \n", (x + 1));
            veiculos.get(x).exibirVeiculos();
        }
    }

    public void cadastrarLocacao(int indiceCliente, int indiceVeiculo, int ano, int mes, int dia, int anoDevolucao,
    int mesDevolucao, int diaDevolucao) {
        Cliente cliente = clientes.get(indiceCliente - 1);
        Veiculo veiculo = veiculos.get(indiceVeiculo - 1);

        if(9 < locacoes.size()){
            System.err.println("Limites de locacões excedido, operação cancelada.");
            return;
        }

        if(!verificarData(ano, mes, dia)){
            System.err.println("Data inválida! Operação cancelada!");
            return;
        } 

        if(!verificarData(anoDevolucao, mesDevolucao, diaDevolucao)){
            System.err.println("Data inválida! Operação cancelada!");
            return;
        }

        LocalDate data = LocalDate.of(ano, mes, dia);
        LocalDate dataDevolucao = LocalDate.of(anoDevolucao, mesDevolucao, diaDevolucao);

        boolean devolvido = false;
        Locacao locacao = new Locacao(veiculo, cliente, data, dataDevolucao, devolvido);
        locacoes.add(locacao);
        System.out.println("Locacão efetivada com sucesso!");

    }

    private boolean verificarData(int ano, int mes, int dia) {
        try {
            LocalDate.of(ano, mes, dia);
            return true;
        } catch (DateTimeException e) {
            System.err.println("Erro, data inválida.");
            return false;
        } 
    }

    public void apresentarLocacao() {
        for (int x = 0; x < locacoes.size(); x++) {
            System.out.printf(" =========== Empréstimo N° %d =========== \n", (x + 1));
            locacoes.get(x).exibirLocacao();
        }
    }

    public int retornarListaTam() {
        return clientes.size();
    }

    public int retornarLocacaoTam() {
        return locacoes.size();
    }

    public void removerLocacao(int indice) {
        System.out.println("Emprestimo de " + locacoes.get(indice - 1).getCliente().getNome() + " foi removido!");
        locacoes.remove(indice - 1);
    }

    public void gerarDemonstrativo() {
        Cliente cliente1 = new Cliente("Marcos", "102.455.665-17", "102546525554");
        Cliente cliente2 = new Cliente("Camila", "102.489.865-64", "25154465464");
        clientes.add(cliente1);
        clientes.add(cliente2);
        apresentarCliente();
        Carro carro = new Carro("HXSIJD", 350.00, "Sim");
        Moto moto = new Moto("JFBJFB", 150.00, "1103 cc");
        veiculos.add(carro);
        veiculos.add(moto);
        apresentarLocacao();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate data1 = LocalDate.parse("02/05/2025", formatter);
        LocalDate data2 = LocalDate.parse("03/05/2025", formatter);
        LocalDate data3 = LocalDate.parse("10/05/2025", formatter);
        LocalDate data4 = LocalDate.parse("11/05/2025", formatter);
        Locacao locacao01 = new Locacao(carro, cliente1, data1, data2, false);
        Locacao locacao02 = new Locacao(carro, cliente2, data1, data2, true);
        Locacao locacao03 = new Locacao(moto, cliente1, data3, data4, false);
        Locacao locacao04 = new Locacao(moto, cliente2, data3, data4, true);
        locacoes.add(locacao01);
        locacoes.add(locacao02);
        locacoes.add(locacao03);
        locacoes.add(locacao04);
        apresentarLocacao();
    }

}
