import java.util.Scanner;
import java.util.Date;

public class Principal {
    private static Scanner scan = new Scanner(System.in);
    private static Locadora locadora = new Locadora();
    private static Clientes[] clientes = new Clientes[10];
    private static Veiculos[] veiculos = new Veiculos[10];
    private static int totalVeiculos = 0;

    public static void main(String[] args) {
        int opcao;
        do {
            System.out.println("\n---MENU---");
            System.out.println("1. Cadastrar Cliente");
            System.out.println("2. Cadastrar Veículo");
            System.out.println("3. Realizar Locação");
            System.out.println("4. Realizar Devolução");
            System.out.println("5. Mostrar Locações sem Devolução");
            System.out.println("6. Fazer uma demonstração");
            System.out.println("0. Sair");
            opcao = scan.nextInt();
            scan.nextLine();
            switch (opcao) {
                case 1: cadastrarCliente();
                break;
                case 2: cadastrarVeiculo();
                break;
                case 3: realizarLocacao();
                break;
                case 4: realizarDevolucao();
                break;
                case 5: mostrarLocacoesSemDevolucao();
                break;
                case 6: fazerDemonstracao();
                break;
                case 0: System.out.println("Saindo..."); break;
                default: System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 0);
    }

    private static void cadastrarCliente() {
        System.out.print("Digite o nome do cliente: ");
        String nome = scan.nextLine();
        System.out.print("Digite o CPF do cliente: ");
        String cpf = scan.nextLine();
        System.out.print("Digite o número da CNH do cliente: ");
        String numCnh = scan.nextLine();

        Clientes novoCliente = new Clientes(nome, cpf, numCnh);
        for (int i = 0; i < clientes.length; i++) {
            if (clientes[i] == null) {
                clientes[i] = novoCliente;
                System.out.println("Cliente cadastrado com sucesso.");
                return;
            }
        }
        System.out.println("Limite de clientes atingido.");
    }

    private static void cadastrarVeiculo() {
        if (totalVeiculos >= 10) {
            System.out.println("Limite de veículos atingido.");
            return;
        }

        int opcao;
        do {
            System.out.println("\n1. Carro");
            System.out.println("2. Moto");
            System.out.println("0. Voltar");
            opcao = scan.nextInt();
            scan.nextLine();

            switch (opcao) {
                case 1: cadastrarCarro(); return;
                case 2: cadastrarMoto();  return;
                case 0: return;
                default: System.out.println("Opção inválida. Tente novamente.");
            }
        } while (true);
    }

    private static void cadastrarCarro() {
        System.out.print("Digite a placa do carro: ");
        String placa = scan.nextLine();
        System.out.print("Digite o valor da diária: ");
        double valorDiaria = scan.nextDouble();
        System.out.print("Possui ar-condicionado? (1 - Sim / 0 - Não): ");
        boolean arCondicionado = scan.nextInt() == 1;
        scan.nextLine();

        veiculos[totalVeiculos] = new Carros(placa, valorDiaria, arCondicionado);
        totalVeiculos++;
        System.out.println("Carro cadastrado com sucesso.");
    }

    private static void cadastrarMoto() {
        System.out.print("Digite a placa da moto: ");
        String placa = scan.nextLine();
        System.out.print("Digite o valor da diária: ");
        double valorDiaria = scan.nextDouble();
        System.out.print("Digite as cilindradas: ");
        int cilindradas = scan.nextInt();
        scan.nextLine();

        veiculos[totalVeiculos] = new Motos(placa, valorDiaria, cilindradas);
        totalVeiculos++;
        System.out.println("Moto cadastrada com sucesso.");
    }

    private static void realizarLocacao() {
        if (clientes[0] == null || totalVeiculos == 0) {
            System.out.println("Cadastre ao menos um cliente e um veículo antes.");
            return;
        }

        System.out.println("\n--- Clientes ---");
        for (int i = 0; i < clientes.length; i++) {
            if (clientes[i] != null) {
                System.out.println("[" + i + "] " + clientes[i].getNome());
            }
        }
        System.out.print("Escolha o cliente: ");
        int indiceCliente = scan.nextInt();
        scan.nextLine();

        if (indiceCliente < 0 || clientes[indiceCliente] == null) {
            System.out.println("Cliente inválido.");
            return;
        }

        System.out.println("\n--- Veículos ---");
        for (int i = 0; i < totalVeiculos; i++) {
            System.out.println("[" + i + "] Placa: " + veiculos[i].getPlaca() + " | Diária: R$" + veiculos[i].getValorDiaria());
        }
        System.out.print("Escolha o veículo: ");
        int indiceVeiculo = scan.nextInt();
        scan.nextLine();

        if (indiceVeiculo < 0 || indiceVeiculo >= totalVeiculos) {
            System.out.println("Veículo inválido.");
            return;
        }

        System.out.print("Digite a quantidade de dias: ");
        int dias = scan.nextInt();
        scan.nextLine();

        Locacao locacao = new Locacao(clientes[indiceCliente], veiculos[indiceVeiculo], new Date(), dias);
        locadora.adicionarLocacao(locacao);
    }

    private static void realizarDevolucao() {
        int total = locadora.getTotalLocacoes();
        if (total == 0) {
            System.out.println("Nenhuma locação registrada.");
            return;
        }

        System.out.println("\n--- Locações ---");
        Locacao[] locacoes = locadora.getLocacoes();
        for (int i = 0; i < total; i++) {
            System.out.println("[" + i + "] Cliente: " + locacoes[i].getCliente().getNome()
                + " | Placa: " + locacoes[i].getVeiculo().getPlaca()
                + " | Situação: " + (locacoes[i].isDevolvido() ? "Devolvido" : "Pendente"));
        }

        System.out.print("Escolha a locação para devolver: ");
        int indice = scan.nextInt();
        scan.nextLine();
        locadora.devolverLocacao(indice);
    }

    private static void mostrarLocacoesSemDevolucao() {
        locadora.mostrarLocacoesPendentes();
    }

    private static void fazerDemonstracao() {
        System.out.println("\n--- DEMONSTRAÇÃO ---");

        Clientes c1 = new Clientes("Gabriel Menon", "111.111.111-11", "CNH-001");
        Clientes c2 = new Clientes("Samuel Babinski", "222.222.222-22", "CNH-002");

        Carros carro = new Carros("BRA-1234", 150.00, true);
        Motos moto = new Motos("XYZ-5678", 80.00, 600);

        Date hoje = new Date();
        Date haCincoDias = new Date(hoje.getTime() - 5 * 86400000L);

        Locacao loc1 = new Locacao(c1, carro, haCincoDias, 5);
        loc1.devolver();

        Locacao loc2 = new Locacao(c2, moto, hoje, 7);

        locadora.adicionarLocacao(loc1);
        locadora.adicionarLocacao(loc2);

        System.out.println("\n--- Locação 1 ---");
        loc1.mostrarDetalhes();
        System.out.println("\n--- Locação 2 ---");
        loc2.mostrarDetalhes();

        System.out.println("\n--- Locações Pendentes ---");
        locadora.mostrarLocacoesPendentes();
    }
}
