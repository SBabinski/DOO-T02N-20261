import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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
            opcao = lerInteiro("Escolha uma opção: ");
            System.out.println();
            switch (opcao) {
                case 1: cadastrarCliente(); break;
                case 2: cadastrarVeiculo(); break;
                case 3: cadastrarLocacao(); break;
                case 4: realizarDevolucao(); break;
                case 5: locadora.listarLocacoesSemDevolucao(); break;
                case 6: demonstracao(); break;
                case 0: System.out.println("Encerrando o sistema. Até logo!"); break;
                default: System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 0);
    }

    static void exibirMenu() {
        System.out.println("\n====== LOCADORA DE VEÍCULOS ======");
        System.out.println("1. Cadastrar Cliente");
        System.out.println("2. Cadastrar Veículo");
        System.out.println("3. Cadastrar Locação");
        System.out.println("4. Realizar Devolução");
        System.out.println("5. Listar Locações sem Devolução");
        System.out.println("6. Demonstração");
        System.out.println("0. Sair");
        System.out.println("==================================");
    }

    static void cadastrarCliente() {
        System.out.println("--- Cadastrar Cliente ---");
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("CPF: ");
        String cpf = scanner.nextLine();
        System.out.print("Número da CNH: ");
        String cnh = scanner.nextLine();

        Cliente c = new Cliente(nome, cpf, cnh);
        clientes.add(c);
        System.out.println("Cliente cadastrado com sucesso!");
        System.out.println(c);
    }

    static void cadastrarVeiculo() {
        System.out.println("--- Cadastrar Veículo ---");
        System.out.println("Tipo: 1 - Carro | 2 - Moto");
        int tipo = lerInteiro("Escolha: ");

        System.out.print("Placa: ");
        String placa = scanner.nextLine();
        double diaria = lerDouble("Valor da diária (R$): ");

        if (tipo == 1) {
            System.out.print("Possui ar-condicionado? (s/n): ");
            String ac = scanner.nextLine();
            boolean temAC = ac.equalsIgnoreCase("s");
            Carro carro = new Carro(placa, diaria, temAC);
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
            System.out.println("Tipo inválido.");
        }
    }

    static void cadastrarLocacao() {
        System.out.println("--- Cadastrar Locação ---");

        if (clientes.isEmpty()) {
            System.out.println("Nenhum cliente cadastrado. Cadastre um cliente primeiro.");
            return;
        }
        if (veiculos.isEmpty()) {
            System.out.println("Nenhum veículo cadastrado. Cadastre um veículo primeiro.");
            return;
        }

        System.out.println("Clientes disponíveis:");
        for (int i = 0; i < clientes.size(); i++) {
            System.out.println("[" + (i + 1) + "] " + clientes.get(i));
        }
        int idxCliente = lerInteiro("Escolha o cliente (número): ") - 1;
        if (idxCliente < 0 || idxCliente >= clientes.size()) {
            System.out.println("Opção inválida.");
            return;
        }

        System.out.println("Veículos disponíveis:");
        for (int i = 0; i < veiculos.size(); i++) {
            System.out.println("[" + (i + 1) + "] " + veiculos.get(i));
        }
        int idxVeiculo = lerInteiro("Escolha o veículo (número): ") - 1;
        if (idxVeiculo < 0 || idxVeiculo >= veiculos.size()) {
            System.out.println("Opção inválida.");
            return;
        }

        LocalDate retirada = lerData("Data de retirada (dd/MM/yyyy): ");
        LocalDate devolucao = lerData("Data de devolução (dd/MM/yyyy): ");

        if (devolucao.isBefore(retirada) || devolucao.isEqual(retirada)) {
            System.out.println("A data de devolução deve ser posterior à data de retirada.");
            return;
        }

        Locacao loc = new Locacao(clientes.get(idxCliente), veiculos.get(idxVeiculo), retirada, devolucao);
        locadora.adicionarLocacao(loc);
        loc.exibirDados();
    }

    static void realizarDevolucao() {
        System.out.println("--- Realizar Devolução ---");
        locadora.listarLocacoesSemDevolucao();

        if (locadora.getTotalLocacoes() == 0) return;

        int num = lerInteiro("Informe o número da locação para devolver (conforme listagem completa): ");
        Locacao loc = locadora.getLocacao(num - 1);
        if (loc == null) {
            System.out.println("Locação não encontrada.");
            return;
        }
        if (loc.isDevolucaoRealizada()) {
            System.out.println("Esta locação já teve devolução realizada.");
            return;
        }
        loc.realizarDevolucao();
        System.out.println("Devolução realizada com sucesso!");
        loc.exibirDados();
    }

    static void demonstracao() {
        System.out.println("\n========== DEMONSTRAÇÃO ==========");

        // Criando clientes
        Cliente c1 = new Cliente("Ana Souza", "111.222.333-44", "CNH-001");
        Cliente c2 = new Cliente("Bruno Lima", "555.666.777-88", "CNH-002");
        clientes.add(c1);
        clientes.add(c2);
        System.out.println("Clientes criados:");
        System.out.println(c1);
        System.out.println(c2);

        // Criando veículos
        Carro carro = new Carro("ABC-1234", 150.00, true);
        Moto moto = new Moto("XYZ-5678", 80.00, 300);
        veiculos.add(carro);
        veiculos.add(moto);
        System.out.println("\nVeículos criados:");
        carro.exibirInformacoes();
        System.out.println("---");
        moto.exibirInformacoes();

        // Criando locações
        LocalDate retirada1 = LocalDate.of(2025, 4, 1);
        LocalDate devolucao1 = LocalDate.of(2025, 4, 5);
        Locacao loc1 = new Locacao(c1, carro, retirada1, devolucao1);
        loc1.realizarDevolucao(); // devolução já realizada
        locadora.adicionarLocacao(loc1);

        LocalDate retirada2 = LocalDate.of(2025, 4, 10);
        LocalDate devolucao2 = LocalDate.of(2025, 4, 15);
        Locacao loc2 = new Locacao(c2, moto, retirada2, devolucao2);
        // devolução NÃO realizada (ativa)
        locadora.adicionarLocacao(loc2);

        System.out.println("\nLocações registradas:");
        loc1.exibirDados();
        loc2.exibirDados();

        System.out.println("\n--- Listando apenas locações ATIVAS ---");
        locadora.listarLocacoesSemDevolucao();
        System.out.println("===================================\n");
    }

    // Utilitários de leitura segura
    static int lerInteiro(String mensagem) {
        while (true) {
            try {
                System.out.print(mensagem);
                int valor = Integer.parseInt(scanner.nextLine().trim());
                return valor;
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Digite um número inteiro.");
            }
        }
    }

    static double lerDouble(String mensagem) {
        while (true) {
            try {
                System.out.print(mensagem);
                return Double.parseDouble(scanner.nextLine().trim().replace(",", "."));
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Digite um valor numérico.");
            }
        }
    }

    static LocalDate lerData(String mensagem) {
        while (true) {
            try {
                System.out.print(mensagem);
                return LocalDate.parse(scanner.nextLine().trim(), FORMATO);
            } catch (DateTimeParseException e) {
                System.out.println("Data inválida. Use o formato dd/MM/yyyy.");
            }
        }
    }
}
