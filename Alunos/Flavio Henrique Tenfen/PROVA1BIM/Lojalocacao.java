import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Cliente {
    private String nome;
    private String cpf;
    private String numeroCNH;

    public Cliente(String nome, String cpf, String numeroCNH) {
        this.nome = nome;
        this.cpf = cpf;
        this.numeroCNH = numeroCNH;
    }

    public String getNome() { return nome; }
    public String getCpf() { return cpf; }
    public String getNumeroCNH() { return numeroCNH; }

    @Override
    public String toString() {
        return "Cliente: " + nome + " | CPF: " + cpf + " | CNH: " + numeroCNH;
    }
}
abstract class Veiculo {
    private String placa;
    private double valorDiaria;

    public Veiculo(String placa, double valorDiaria) {
        this.placa = placa;
        this.valorDiaria = valorDiaria;
    }

    public String getPlaca() { return placa; }
    public double getValorDiaria() { return valorDiaria; }

    public abstract void exibirInformacoes();
}

class Carro extends Veiculo {
    private boolean temArCondicionado;

    public Carro(String placa, double valorDiaria, boolean temArCondicionado) {
        super(placa, valorDiaria);
        this.temArCondicionado = temArCondicionado;
    }

    public boolean isTemArCondicionado() { return temArCondicionado; }

    @Override
    public void exibirInformacoes() {
        System.out.println("Tipo: Carro");
        System.out.println("Placa: " + getPlaca());
        System.out.println("Valor da Diaria: R$ " + String.format("%.2f", getValorDiaria()));
        System.out.println("Ar-condicionado: " + (temArCondicionado ? "Sim" : "Nao"));
    }

    @Override
    public String toString() {
        return "Carro [" + getPlaca() + "] - R$ " + String.format("%.2f", getValorDiaria()) + "/dia | AC: " + (temArCondicionado ? "Sim" : "Nao");
    }
}

class Moto extends Veiculo {
    private int cilindrada;

    public Moto(String placa, double valorDiaria, int cilindrada) {
        super(placa, valorDiaria);
        this.cilindrada = cilindrada;
    }

    public int getCilindrada() { return cilindrada; }

    @Override
    public void exibirInformacoes() {
        System.out.println("Tipo: Moto");
        System.out.println("Placa: " + getPlaca());
        System.out.println("Valor da Diaria: R$ " + String.format("%.2f", getValorDiaria()));
        System.out.println("Cilindrada: " + cilindrada + "cc");
    }

    @Override
    public String toString() {
        return "Moto [" + getPlaca() + "] - R$ " + String.format("%.2f", getValorDiaria()) + "/dia | " + cilindrada + "cc";
    }
}

class Locacao {
    private Cliente cliente;
    private Veiculo veiculo;
    private LocalDate dataRetirada;
    private LocalDate dataDevolucao;
    private boolean devolucaoRealizada;

    private static final DateTimeFormatter FORMATO = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public Locacao(Cliente cliente, Veiculo veiculo, LocalDate dataRetirada, LocalDate dataDevolucao) {
        this.cliente = cliente;
        this.veiculo = veiculo;
        this.dataRetirada = dataRetirada;
        this.dataDevolucao = dataDevolucao;
        this.devolucaoRealizada = false;
    }

    public void realizarDevolucao() { this.devolucaoRealizada = true; }
    public boolean isDevolucaoRealizada() { return devolucaoRealizada; }
    public Cliente getCliente() { return cliente; }
    public Veiculo getVeiculo() { return veiculo; }

    public long calcularQuantidadeDiarias() {
        return ChronoUnit.DAYS.between(dataRetirada, dataDevolucao);
    }

    public double calcularValorTotal() {
        return calcularQuantidadeDiarias() * veiculo.getValorDiaria();
    }

    public void exibirDados() {
        System.out.println("========== LOCACAO ==========");
        System.out.println(cliente);
        System.out.println("Veiculo:");
        veiculo.exibirInformacoes();
        System.out.println("Data de Retirada: " + dataRetirada.format(FORMATO));
        System.out.println("Data de Devolucao: " + dataDevolucao.format(FORMATO));
        System.out.println("Quantidade de Diarias: " + calcularQuantidadeDiarias());
        System.out.println("Valor Total: R$ " + String.format("%.2f", calcularValorTotal()));
        System.out.println("Situacao: " + (devolucaoRealizada ? "Devolucao realizada" : "Ativa (aguardando devolucao)"));
        System.out.println("=============================");
    }
}

class Locadora {
    private static final int CAPACIDADE_MAXIMA = 10;
    private Locacao[] locacoes;
    private int totalLocacoes;

    public Locadora() {
        this.locacoes = new Locacao[CAPACIDADE_MAXIMA];
        this.totalLocacoes = 0;
    }

    public boolean adicionarLocacao(Locacao locacao) {
        if (totalLocacoes >= CAPACIDADE_MAXIMA) {
            System.out.println("Capacidade maxima de locacoes atingida (" + CAPACIDADE_MAXIMA + ").");
            return false;
        }
        locacoes[totalLocacoes] = locacao;
        totalLocacoes++;
        System.out.println("Locacao registrada com sucesso!");
        return true;
    }

    public void listarLocacoesSemDevolucao() {
        System.out.println("\n===== LOCACOES ATIVAS (sem devolucao) =====");
        boolean encontrou = false;
        for (int i = 0; i < totalLocacoes; i++) {
            if (!locacoes[i].isDevolucaoRealizada()) {
                locacoes[i].exibirDados();
                encontrou = true;
            }
        }
        if (!encontrou) {
            System.out.println("Nenhuma locacao ativa encontrada.");
        }
        System.out.println("===========================================\n");
    }

    public void listarTodasLocacoes() {
        System.out.println("\n===== TODAS AS LOCACOES =====");
        if (totalLocacoes == 0) {
            System.out.println("Nenhuma locacao registrada.");
            return;
        }
        for (int i = 0; i < totalLocacoes; i++) {
            System.out.println("[" + (i + 1) + "]");
            locacoes[i].exibirDados();
        }
    }

    public Locacao getLocacao(int index) {
        if (index < 0 || index >= totalLocacoes) return null;
        return locacoes[index];
    }

    public int getTotalLocacoes() { return totalLocacoes; }
}

public class Lojalocacao {

    static Scanner scanner = new Scanner(System.in);
    static Locadora locadora = new Locadora();
    static List<Cliente> clientes = new ArrayList<>();
    static List<Veiculo> veiculos = new ArrayList<>();
    static DateTimeFormatter FORMATO = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static void main(String[] args) {
        int opcao;
        do {
            exibirMenu();
            opcao = lerInteiro("Escolha uma opcao: ");
            System.out.println();
            switch (opcao) {
                case 1: cadastrarCliente(); break;
                case 2: cadastrarVeiculo(); break;
                case 3: cadastrarLocacao(); break;
                case 4: realizarDevolucao(); break;
                case 5: locadora.listarLocacoesSemDevolucao(); break;
                case 6: demonstracao(); break;
                case 0: System.out.println("Encerrando o sistema. Ate logo!"); break;
                default: System.out.println("Opcao invalida. Tente novamente.");
            }
        } while (opcao != 0);
    }

    static void exibirMenu() {
        System.out.println("\n====== LOCADORA DE VEICULOS ======");
        System.out.println("1. Cadastrar Cliente");
        System.out.println("2. Cadastrar Veiculo");
        System.out.println("3. Cadastrar Locacao");
        System.out.println("4. Realizar Devolucao");
        System.out.println("5. Listar Locacoes sem Devolucao");
        System.out.println("6. Demonstracao");
        System.out.println("0. Sair");
        System.out.println("==================================");
    }

    static void cadastrarCliente() {
        System.out.println("--- Cadastrar Cliente ---");
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("CPF: ");
        String cpf = scanner.nextLine();
        System.out.print("Numero da CNH: ");
        String cnh = scanner.nextLine();
        Cliente c = new Cliente(nome, cpf, cnh);
        clientes.add(c);
        System.out.println("Cliente cadastrado com sucesso!");
        System.out.println(c);
    }

    static void cadastrarVeiculo() {
        System.out.println("--- Cadastrar Veiculo ---");
        System.out.println("Tipo: 1 - Carro | 2 - Moto");
        int tipo = lerInteiro("Escolha: ");
        System.out.print("Placa: ");
        String placa = scanner.nextLine();
        double diaria = lerDouble("Valor da diaria (R$): ");

        if (tipo == 1) {
            System.out.print("Possui ar-condicionado? (s/n): ");
            String ac = scanner.nextLine();
            Carro carro = new Carro(placa, diaria, ac.equalsIgnoreCase("s"));
            veiculos.add(carro);
            System.out.println("Carro cadastrado!");
            carro.exibirInformacoes();
        } else if (tipo == 2) {
            int cilindrada = lerInteiro("Cilindrada (cc): ");
            Moto moto = new Moto(placa, diaria, cilindrada);
            veiculos.add(moto);
            System.out.println("Moto cadastrada!");
            moto.exibirInformacoes();
        } else {
            System.out.println("Tipo invalido.");
        }
    }

    static void cadastrarLocacao() {
        System.out.println("--- Cadastrar Locacao ---");
        if (clientes.isEmpty()) { System.out.println("Nenhum cliente cadastrado."); return; }
        if (veiculos.isEmpty()) { System.out.println("Nenhum veiculo cadastrado."); return; }

        System.out.println("Clientes:");
        for (int i = 0; i < clientes.size(); i++)
            System.out.println("[" + (i + 1) + "] " + clientes.get(i));
        int idxCliente = lerInteiro("Escolha o cliente: ") - 1;
        if (idxCliente < 0 || idxCliente >= clientes.size()) { System.out.println("Opcao invalida."); return; }

        System.out.println("Veiculos:");
        for (int i = 0; i < veiculos.size(); i++)
            System.out.println("[" + (i + 1) + "] " + veiculos.get(i));
        int idxVeiculo = lerInteiro("Escolha o veiculo: ") - 1;
        if (idxVeiculo < 0 || idxVeiculo >= veiculos.size()) { System.out.println("Opcao invalida."); return; }

        LocalDate retirada = lerData("Data de retirada (dd/MM/yyyy): ");
        LocalDate devolucao = lerData("Data de devolucao (dd/MM/yyyy): ");
        if (devolucao.isBefore(retirada) || devolucao.isEqual(retirada)) {
            System.out.println("Data de devolucao deve ser posterior a data de retirada.");
            return;
        }

        Locacao loc = new Locacao(clientes.get(idxCliente), veiculos.get(idxVeiculo), retirada, devolucao);
        locadora.adicionarLocacao(loc);
        loc.exibirDados();
    }

    static void realizarDevolucao() {
        System.out.println("--- Realizar Devolucao ---");
        locadora.listarTodasLocacoes();
        if (locadora.getTotalLocacoes() == 0) return;
        int num = lerInteiro("Numero da locacao para devolver: ");
        Locacao loc = locadora.getLocacao(num - 1);
        if (loc == null) { System.out.println("Locacao nao encontrada."); return; }
        if (loc.isDevolucaoRealizada()) { System.out.println("Devolucao ja realizada."); return; }
        loc.realizarDevolucao();
        System.out.println("Devolucao realizada com sucesso!");
        loc.exibirDados();
    }

    static void demonstracao() {
        System.out.println("\n========== DEMONSTRACAO ==========");

        Cliente c1 = new Cliente("Ana Souza", "111.222.333-44", "CNH-001");
        Cliente c2 = new Cliente("Bruno Lima", "555.666.777-88", "CNH-002");
        clientes.add(c1);
        clientes.add(c2);
        System.out.println("Clientes criados:");
        System.out.println(c1);
        System.out.println(c2);

        Carro carro = new Carro("ABC-1234", 150.00, true);
        Moto moto = new Moto("XYZ-5678", 80.00, 300);
        veiculos.add(carro);
        veiculos.add(moto);
        System.out.println("\nVeiculos criados:");
        carro.exibirInformacoes();
        System.out.println("---");
        moto.exibirInformacoes();

        Locacao loc1 = new Locacao(c1, carro, LocalDate.of(2026, 4, 1), LocalDate.of(2026, 4, 5));
        loc1.realizarDevolucao();
        locadora.adicionarLocacao(loc1);

        Locacao loc2 = new Locacao(c2, moto, LocalDate.of(2026, 4, 10), LocalDate.of(2026, 4, 15));
        locadora.adicionarLocacao(loc2);

        System.out.println("\nLocacoes registradas:");
        loc1.exibirDados();
        loc2.exibirDados();

        System.out.println("--- Locacoes ATIVAS ---");
        locadora.listarLocacoesSemDevolucao();
        System.out.println("===================================\n");
    }

    static int lerInteiro(String mensagem) {
        while (true) {
            try {
                System.out.print(mensagem);
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Entrada invalida. Digite um numero inteiro.");
            }
        }
    }

    static double lerDouble(String mensagem) {
        while (true) {
            try {
                System.out.print(mensagem);
                return Double.parseDouble(scanner.nextLine().trim().replace(",", "."));
            } catch (NumberFormatException e) {
                System.out.println("Entrada invalida. Digite um valor numerico.");
            }
        }
    }

    static LocalDate lerData(String mensagem) {
        while (true) {
            try {
                System.out.print(mensagem);
                return LocalDate.parse(scanner.nextLine().trim(), FORMATO);
            } catch (DateTimeParseException e) {
                System.out.println("Data invalida. Use o formato dd/MM/yyyy.");
            }
        }
    }
}