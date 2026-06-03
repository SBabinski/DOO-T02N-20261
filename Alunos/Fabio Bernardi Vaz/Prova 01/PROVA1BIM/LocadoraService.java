package fag;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

public class LocadoraService {
    private Locadora locadora;
    private Scanner scanner;
    private List<Cliente> clientesCadastrados;
    private List<Veiculo> veiculosCadastrados;
    private DateTimeFormatter formatter;

    public LocadoraService(Locadora locadora, Scanner scanner, List<Cliente> clientesCadastrados, List<Veiculo> veiculosCadastrados, DateTimeFormatter formatter) {
        this.locadora = locadora;
        this.scanner = scanner;
        this.clientesCadastrados = clientesCadastrados;
        this.veiculosCadastrados = veiculosCadastrados;
        this.formatter = formatter;
    }

    public void cadastrarCliente() {
        System.out.println("\nCadastro de Cliente");
        scanner.nextLine();

        System.out.print("Nome: ");
        String nomeC = scanner.nextLine();
        System.out.print("CPF: ");
        String cpfC = scanner.nextLine();
        System.out.print("Número da CNH: ");
        String cnhC = scanner.nextLine();

        Cliente novoCliente = new Cliente(nomeC, cpfC, cnhC);
        clientesCadastrados.add(novoCliente);

        System.out.println("Cliente cadastrado com sucesso!");
        novoCliente.apresentarse();
    }

    public void cadastrarVeiculo() {
        System.out.println("\nCadastro de Veículo");
        System.out.println("[1] Carro");
        System.out.println("[2] Moto");
        System.out.print("Escolha o tipo do veículo: ");
        int tipoVeiculo = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Placa: ");
        String placaVeiculo = scanner.nextLine();
        System.out.print("Valor da diária: R$ ");
        double diaria = scanner.nextDouble();

        if (tipoVeiculo == 1) {
            System.out.print("Tem ar-condicionado?(1-Sim ou 2-Não):");
            int arResp = scanner.nextInt();
            boolean temAr = (arResp == 1);
            Carro novoCarro = new Carro(placaVeiculo, diaria, temAr);
            veiculosCadastrados.add(novoCarro);
            System.out.println("Carro cadastrado!");
            novoCarro.apresentarse();

        } else if (tipoVeiculo == 2) {
            System.out.print("Cilindrada: ");
            int cilindradaQtd = scanner.nextInt();
            Moto novaMoto = new Moto(placaVeiculo, diaria, cilindradaQtd);
            veiculosCadastrados.add(novaMoto);
            System.out.println("Moto cadastrada!");
            novaMoto.apresentarse();

        } else {
            System.out.println("Tipo inválido!");
        }
    }

    public void registrarLocacao() {
        System.out.println("\nRegistrar Locação");

        if (clientesCadastrados.isEmpty()) {
            System.out.println("Nenhum cliente cadastrado! Cadastre um primeiro");
            return;
        }
        if (veiculosCadastrados.isEmpty()) {
            System.out.println("Nenhum veículo cadastrado! Cadastre um primeiro");
            return;
        }

        System.out.println("Clientes disponíveis:");
        for (int i = 0; i < clientesCadastrados.size(); i++) {
            System.out.println(i+ " - " + clientesCadastrados.get(i).getNome() + " CPF: " + clientesCadastrados.get(i).getCpf());
        }
        System.out.print("Número do cliente: ");
        int indexCliente = scanner.nextInt();

        System.out.println("\nVeículos disponíveis:");
        for (int i = 0; i < veiculosCadastrados.size(); i++) {
            Veiculo v = veiculosCadastrados.get(i);
            System.out.println(i+ " - " + v.getTipo() + " Placa: " + v.getPlaca() + " Valor da Diária: R$ " + v.getValorDiaria());
        }
        System.out.print("Número do veículo: ");
        int indexVeiculo = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Data de retirada (dd/MM/yyyy): ");
        String dataRetStr = scanner.nextLine();
        System.out.print("Data de devolução (dd/MM/yyyy): ");
        String dataDevStr = scanner.nextLine();

        try {
            LocalDate dataRetirada = LocalDate.parse(dataRetStr, formatter);
            LocalDate dataDevolucao = LocalDate.parse(dataDevStr, formatter);

            if (dataDevolucao.isBefore(dataRetirada) || dataDevolucao.isEqual(dataRetirada)) {
                System.out.println("A data de devolução deve ser uma data posterior de retirada!");
                return;
            }

            Locacao novaLocacao = new Locacao(
                    clientesCadastrados.get(indexCliente),
                    veiculosCadastrados.get(indexVeiculo),
                    dataRetirada,
                    dataDevolucao
            );
            locadora.adicionarLocacao(novaLocacao);

        } catch (DateTimeParseException e) {
            System.out.println("Data inválida, pfv use o formato dd/MM/yyyy");
        }
    }

    public void realizarDevolucao() {
        System.out.println("\nRealizar Devolução");

        List<Locacao> abertas = locadora.obterLocacoesEmAberto();

        if (abertas.isEmpty()) {
            System.out.println("Nenhuma locação em aberto para devolução.");
        } else {
            for (int i = 0; i < abertas.size(); i++) {
                Locacao l = abertas.get(i);
                System.out.println(i+" - " + " Cliente: " + l.getCliente().getNome() + " Veículo: " + l.getVeiculo().getPlaca());
            }

            System.out.print("Digite o número da locação: ");
            int escolha = scanner.nextInt();

            if (escolha >= 0 && escolha < abertas.size()) {
                Locacao selecionada = abertas.get(escolha);
                selecionada.realizarDevolucao();
                System.out.printf("Valor total a ser pago: R$ %.2f", selecionada.calcularValorTotal());
            } else {
                System.out.println("Opção inválida!");
            }
        }
    }

    public void demonstracaoSistema() {
        System.out.println("-------------DEMONSTRAÇÃO DO SISTEMA-------------");

        Cliente clienteFake1 = new Cliente("Ana Paula Souza", "123.456.789-00", "AB-1234567");
        Cliente clienteFake2 = new Cliente("Roberto Mendes", "987.654.321-00", "CD-7654321");

        Carro carroFake = new Carro("ABC-1D23", 150.00, true);
        Moto motoFake = new Moto("XYZ-9E87", 80.00, 300);

        System.out.println("\n--- Clientes criados ---");
        clienteFake1.apresentarse();
        clienteFake2.apresentarse();

        System.out.println("\n--- Veículos criados ---");
        carroFake.apresentarse();
        motoFake.apresentarse();

        LocalDate retirada1 = LocalDate.of(2025, 6, 1);
        LocalDate devolucao1 = LocalDate.of(2025, 6, 5);
        Locacao locacaoDemo1 = new Locacao(clienteFake1, carroFake, retirada1, devolucao1);
        locacaoDemo1.realizarDevolucao();

        LocalDate retirada2 = LocalDate.of(2025, 6, 10);
        LocalDate devolucao2 = LocalDate.of(2025, 6, 14);
        Locacao locacaoDemo2 = new Locacao(clienteFake2, motoFake, retirada2, devolucao2);

        Locadora locadoraDemo = new Locadora("Roda Livre Demo");
        locadoraDemo.adicionarLocacao(locacaoDemo1);
        locadoraDemo.adicionarLocacao(locacaoDemo2);

        System.out.println("\n--- Todas as locações registradas ---");
        locadoraDemo.listarTodasLocacoes();

        System.out.println("\n--- Listando apenas locações SEM devolução ---");
        locadoraDemo.listarLocacoesSemDevolucao();
    }
}