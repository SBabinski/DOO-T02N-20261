import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import Objetos.Carro;
import Objetos.Cliente;
import Objetos.Locação;
import Objetos.Moto;
import Objetos.Veiculo;

public class LocadoraVeiculos {

    static Scanner ler = new Scanner(System.in);
    static List<LocalDate> datasDeLocacao = new ArrayList<>();
    static List<LocalDate> datasDeDevolucao = new ArrayList<>();
    static List<Cliente> clientes = new ArrayList<>();
    static List<Veiculo> veiculos = new ArrayList<>();
    static List<Locação> locacoes = new ArrayList<>();
    static DateTimeFormatter formatoBR = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static void main(String[] args) {
        mostrarMenu();
    }

    private static void mostrarMenu() {
        int escolhaM;
        System.out.println("==========Menu==========");
        System.out.println("[1]-Cadastrar Cliente");
        System.out.println("[2]-Cadastrar Veículo");
        System.out.println("[3]-Cadastrar Locação");
        System.out.println("[4]-Realizar Devolução");
        System.out.println("[5]-Listar Locações");
        System.out.println("[6]-Demonstração");
        System.out.println("[0]-Sair");
        escolhaM = ler.nextInt();
        ler.nextLine();

        validarEscolhaMenu(escolhaM);
    }

    private static void validarEscolhaMenu(int escolhaM) {
        switch (escolhaM) {
            case 1 -> cadastrarCliente();
            case 2 -> cadastrarVeiculo();
            case 3 -> cadastrarLocacao();
            case 4 -> realizarDecolucao();
            case 5 -> listarLocacoes();
            case 6 -> demostracao();
            case 0 -> System.out.println("Obrigado por usar nosso sistema❤");
            default -> System.out.println("Escolha uma opção válida do Menu");
        }
    }

    private static void demostracao() {
        Cliente cliente1 = new Cliente("João", "1234", "567");
        Cliente cliente2 = new Cliente("João", "1234", "567");

        Carro carro = new Carro("Ab634", 100, true);
        Moto moto = new Moto("K7R4", 50, 900);

        Locação locacao1 = new Locação(cliente2, moto, LocalDate.of(2006, 02, 10), LocalDate.of(2026, 02, 10), false);
        Locação locacao2 = new Locação(cliente1, carro, LocalDate.of(2026, 02, 10), LocalDate.of(2026, 02, 15), false);
        locacao2.realizarDevolucao();

        locacao1.mostrarLocacao();
        locacao2.mostrarLocacao();

    }

    private static void listarLocacoes() {
        int x = 0;
        for (int i = 0; i < locacoes.size(); i++) {
            x = x + i;
            System.out.println(+x + "° Locação");
            locacoes.get(i).mostrarLocacao();
        }
    }

    private static void realizarDecolucao() {
        int x = 0;
        for (int i = 0; i < locacoes.size(); i++) {
            x = x + i;
            System.out.println(+x + "° Locação");
            locacoes.get(i).mostrarLocacao();
        }
        System.out.println("Escolha uma locação para remover");
        int removLoca = ler.nextInt();
        locacoes.get(removLoca-1).realizarDevolucao();
        locacoes.get(removLoca-1).mostrarLocacao();
    }

    private static void cadastrarLocacao() {
        int x = 0;
        System.out.println("Selecione o cliente que deseja fazer a locação");
        for (int i = 0; i < clientes.size(); i++) {
            x = x + i;
            System.out.println(+x + "° Cliente");
            clientes.get(i).apresentarCliente();
        }
        x = 0;
        System.out.println("Selecione um cliente");
        int escC = ler.nextInt();

        System.out.println("Selecione o Veiculo que deseja utilizar");
        for (int i = 0; i < veiculos.size(); i++) {
            x = x + i;
            System.out.println(+x + "° Veiculo");
            clientes.get(i).apresentarCliente();
        }
        System.out.println("Selecione um Veiculo");
        int escV = ler.nextInt();

        System.out.println("Digite a data de Locação desejada sendo realizada neste formato DD/MM/YYYY");
        String dataL = ler.nextLine();
        LocalDate dataLocacao = LocalDate.parse(dataL, formatoBR);
        datasDeLocacao.add(dataLocacao);

        System.out.println("Digite a data de Devolução desejada sendo realizada neste formato DD/MM/YYYY");
        String dataD = ler.nextLine();
        LocalDate dataDevolucao = LocalDate.parse(dataD, formatoBR);
        datasDeDevolucao.add(dataDevolucao);

        if (locacoes.size() < 10) {
            Locação locacao = new Locação(clientes.get(escC - 1), veiculos.get(escV - 1), dataLocacao, dataDevolucao, false);
            locacoes.add(locacao);
            System.out.println("Locação feita com sucesso");
        } else {
            System.out.println("Limite máximo de locações atingidas");
        }

    }

    private static void cadastrarVeiculo() {
        int escolhaV;
        System.out.println("Digite [1] para cadastrar um carro e [2] para cadastra uma moto");
        escolhaV = ler.nextInt();
        ler.nextLine();
        validarEscolhaVeiculo(escolhaV);
    }

    private static void validarEscolhaVeiculo(int escolhaV) {
        switch (escolhaV) {
            case 1 -> cadastraCarro();
            case 2 -> cadastrarMoto();
            default -> System.out.println("Escolha uma opção válida do Menu");
        }
    }

    private static void cadastrarMoto() {
        System.out.println("Digite a placa da Moto");
        String placa = ler.nextLine();
        System.out.println("Digite o Valor da Diária");
        double valDiario = ler.nextDouble();
        System.out.println("Digite a cilindrada da moto");
        int cilindrada = ler.nextInt();
        Moto moto = new Moto(placa, valDiario, cilindrada);

        moto.AddVeiculo(moto);

    }

    private static void cadastraCarro() {
        System.out.println("Digite a placa do carro");
        String placa = ler.nextLine();
        System.out.println("Digite o Valor da Diária");
        double valDiario = ler.nextDouble();
        System.out.println("Digite a placa do carro");
        boolean yOn = ler.nextBoolean();
        Carro carro = new Carro(placa, valDiario, yOn);

        carro.AddVeiculo(carro);

    }

    private static void cadastrarCliente() {
        System.out.println("Digite o nome do cliente");
        String nome = ler.nextLine();
        System.out.println("Digite o número do CPF do cliente");
        String cpf = ler.nextLine();
        System.out.println("Digite o número da CNH do cliente");
        String cnh = ler.nextLine();
        Cliente cliente = new Cliente(nome, cpf, cnh);

        cliente.AddCliente(cliente);

    }
}
